package com.example.inventorymanage.data;

public class StockItem {

    private final String productName;
    private final String price;
    private final int amount;
    private final String factoryName;
    private final String factoryPhone;
    private final String factoryEmail;
    private final String image;

    public StockItem(String productName, String price, int amount, String factoryName, String factoryPhone, String factoryEmail, String image) {
        this.productName = productName;
        this.price = price;
        this.amount = amount;
        this.factoryName = factoryName;
        this.factoryPhone = factoryPhone;
        this.factoryEmail = factoryEmail;
        this.image = image;
    }

    public String getProductName() {
        return productName;
    }

    public String getPrice() {
        return price;
    }

    public int getAmount() {
        return amount;
    }

    public String getFactoryName() {
        return factoryName;
    }

    public String getFactoryPhone() {
        return factoryPhone;
    }

    public String getFactoryEmail() {
        return factoryEmail;
    }

    public String getImage() {
        return image;
    }
    @Override
    public String toString() {
        return "Item: " +
                "productName='" + productName + '|' +
                ", price='" + price + '|' +
                ", quantity=" + amount +
                ", supplierName='" + factoryName + '|' +
                ", supplierPhone='" + factoryPhone + '|' +
                ", supplierEmail='" + factoryEmail + '|';
    }

}
