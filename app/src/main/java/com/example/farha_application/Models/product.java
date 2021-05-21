package com.example.farha_application.Models;

public class product {

    private int id ;
    private String productName;
    private String productPric;
    private String productColor;
    private int productToken;
    private String productSize;
    private String productImageId;
    private String category ;

    public product() { }

    public product(int id ,String productName, String productPric, String productColor, int productToken, String productSize, String productImageId ,String category) {
        this.id=id;
        this.productName = productName;
        this.productPric = productPric;
        this.productColor = productColor;
        this.productToken = productToken;
        this.productSize = productSize;
        this.productImageId = productImageId;
        this.category=category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductPric() {
        return productPric;
    }

    public void setProductPric(String productPric) {
        this.productPric = productPric;
    }

    public String getProductColor() {
        return productColor;
    }

    public void setProductColor(String productColor) {
        this.productColor = productColor;
    }

    public int getProductToken() {
        return productToken;
    }

    public void setProductToken(int productToken) {
        this.productToken = productToken;
    }

    public String getProductSize() {
        return productSize;
    }

    public void setProductSize(String productSize) {
        this.productSize = productSize;
    }

    public String getProductImageId() {
        return productImageId;
    }

    public void setProductImageId(String productImageId) {
        this.productImageId = productImageId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "product{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", productPric='" + productPric + '\'' +
                ", productColor='" + productColor + '\'' +
                ", productToken=" + productToken +
                ", productSize=" + productSize +
                ", productImageId='" + productImageId + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}
