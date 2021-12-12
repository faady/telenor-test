package com.telenor.Telenor.provider;

import com.telenor.Telenor.model.Product;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.Mockito.*;

public class DataProviderTest {

    @Test
    public void getFilterdData() {

        List<Product> allData = Stream.of(new Product("subscription","gb_limit:50",415,"Odell gatan", "Stockholm")
                        ,new Product("phone","color:guld",271,"Nilsson allén", "Stockholm")
                        ,new Product("subscription","gb_limit:10",467,"Kirsten gränden", "Karlskrona"))
                        .collect(Collectors.toList());

        DataProvider dataProvider = Mockito.spy(new DataProvider());
        doReturn(allData).when(dataProvider).readData();

        Map<String, String> allRequestParamsTest1 = Map.of(
                "property:gb_limit_min", "20",
                "city", "Stockholm"
        );
        assert(dataProvider.getFilterdData(allRequestParamsTest1).size() == 1);

        Map<String, String> allRequestParamsTest2 = Map.of(
                "property:gb_limit_min", "10",
                "city", "Stockholm"
        );
        assert(dataProvider.getFilterdData(allRequestParamsTest2).size() == 1);

        Map<String, String> allRequestParamsTest3 = Map.of(
                "city", "Stockholm"
        );
        assert(dataProvider.getFilterdData(allRequestParamsTest3).size() == 2);

        Map<String, String> allRequestParamsTest4 = Map.of(
                "city", "Karlskrona"
        );
        assert(dataProvider.getFilterdData(allRequestParamsTest1).size() == 1);
    }
}
