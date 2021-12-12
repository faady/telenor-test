package com.telenor.Telenor.controller;


import com.telenor.Telenor.model.Product;
import com.telenor.Telenor.provider.DataProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class HomeController {

    @Autowired
    DataProvider dataProvider;

    @GetMapping(value = "/")
    public List<Product> home() {
        return dataProvider.getAllData();
    }

    @GetMapping(value = "/getdata")
    public List<Product> getData(@RequestParam Map<String,String> allRequestParams) {
        return dataProvider.getFilterdData(allRequestParams);
    }

}
