package com.telenor.Telenor.provider;

import com.telenor.Telenor.model.Product;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DataProvider {

    private static final Logger logger = LoggerFactory.getLogger(DataProvider.class);

    public List<Product> readData() {
        List<Product> records = new ArrayList<>();

        InputStream inputStream = this.getClass().getResourceAsStream("/data/data.csv");
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                float price;
                try{
                    price = Float.parseFloat(values[2]);
                } catch (Exception e) {
                    price = 0;
                }
                records.add(new Product(values[0],values[1],price,values[3].replaceAll("\"",""),values[4].replace("\"","").replace(" ", "")));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return records;
    }

    public List<Product> getAllData() {
        return readData();
    }

    public List<Product> getFilterdData(Map<String,String> allRequestParams) {

        Stream<Product> inputData = readData().parallelStream();

        if(allRequestParams.get("type") != null) {
            inputData = inputData.filter(data -> (data.getProductType().equals(allRequestParams.get("type"))));
        }

        if(allRequestParams.get("city") != null) {
            inputData = inputData.filter(data -> (data.getCity().equals(allRequestParams.get("city"))));
        }

        if(allRequestParams.get("property") != null) {
            inputData = inputData.filter(data -> (data.getProductProperties().contains(allRequestParams.get("property"))));
        }

        if(allRequestParams.get("property:color") != null) {
            inputData = inputData.filter(data -> (data.getProductProperties().contains(allRequestParams.get("property:color"))));
        }

        try {
            Float minPrice = Float.parseFloat(allRequestParams.get("min_price"));
            inputData = inputData.filter(data -> data.getPrice() > minPrice);
        } catch (Exception e) {
            logger.info("No min price for filtrering");
        }
        try {
            Float maxPrice = Float.parseFloat(allRequestParams.get("max_price"));
            inputData = inputData.filter(data -> data.getPrice() < maxPrice);
        } catch (Exception e) {
            logger.info("No max price for filtrering");
        }

        try {
            Integer gbLimitMax = Integer.parseInt(allRequestParams.get("property:gb_limit_max"));
            inputData = inputData.filter(data -> (data.getProductProperties().contains("gb_limit")
                    && Integer.parseInt(data.getProductProperties().replace("gb_limit:", "")) < gbLimitMax));
        } catch (Exception e)  {
            logger.info("No gb_limit_max for filtrering");
        }

        try {
            Integer gbLimitMin = Integer.parseInt(allRequestParams.get("property:gb_limit_min"));
            inputData = inputData.filter(data -> (data.getProductProperties().contains("gb_limit")
                    && Integer.parseInt(data.getProductProperties().replace("gb_limit:", "")) > gbLimitMin));
        } catch (Exception e)  {
            logger.info("No gb_limit_min for filtrering");
        }

        return inputData.collect(Collectors.toList());
    }
}
