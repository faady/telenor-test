package com.telenor.Telenor.model;

public class Product {
    String productType;
    String productProperties;
    float price;
    String storeAddress;
    String city;

    public Product(String productType, String productProperties, float price, String storeAddress, String city) {
        this.productType = productType;
        this.productProperties = productProperties;
        this.price = price;
        this.storeAddress = storeAddress;
        this.city = city;
    }

    public Product() {
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getProductProperties() {
        return productProperties;
    }

    public void setProductProperties(String productProperties) {
        this.productProperties = productProperties;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getStoreAddress() {
        return storeAddress;
    }

    public void setStoreAddress(String storeAddress) {
        this.storeAddress = storeAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
