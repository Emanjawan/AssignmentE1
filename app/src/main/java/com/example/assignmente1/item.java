package com.example.assignmente1;

import android.graphics.drawable.Drawable;

public class item {
    private int image;
    private String nameItem;


    private String price;
    private String counter;

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getNameItem() {
        return nameItem;
    }

    public void setNameItem(String nameItem) {
        this.nameItem = nameItem;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCounter() {
        return counter;
    }

    public void setCounter(String counter) {
        this.counter = counter;
    }

//    public double getTotal() {
//        return total;
//    }
//
//    public void setTotal(double total) {
//        this.total = total;
//    }

    item() {

    }

     item(int image, String nameItem, String price, String counter) {
        this.image = image;
        this.nameItem = nameItem;
        this.price = price;
        this.counter = counter;
    }


}
