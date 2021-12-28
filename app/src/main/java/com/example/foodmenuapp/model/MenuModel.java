package com.example.foodmenuapp.model;

public class MenuModel {
    private int image;
    private String name,price,desciption;

    public MenuModel(int image, String name, String price, String desciption) {
        this.image = image;
        this.name = name;
        this.price = price;
        this.desciption = desciption;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setDesciption(String desciption) {
        this.desciption = desciption;
    }

    public int getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getDesciption() {
        return desciption;
    }
}
