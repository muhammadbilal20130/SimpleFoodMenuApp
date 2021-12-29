package com.example.foodmenuapp.model;

public class OrdersModel {
    int Image;
    String orderDishName,orderDishID,orderDishPrice;

    public OrdersModel(int image, String orderDishName, String orderDishID, String orderDishPrice) {
        Image = image;
        this.orderDishName = orderDishName;
        this.orderDishID = orderDishID;
        this.orderDishPrice = orderDishPrice;
    }

    public int getImage() {
        return Image;
    }

    public String getOrderDishName() {
        return orderDishName;
    }

    public String getOrderDishID() {
        return orderDishID;
    }

    public String getOrderDishPrice() {
        return orderDishPrice;
    }
}
