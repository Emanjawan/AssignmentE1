package com.example.assignmente1;

public class itemsCart {

    private int image;
    private String nameItem;


    private String total;
    private String counter;
    private String price;

    public itemsCart(int image, String nameItem, String total, String counter,String price) {
        this.image = image;
        this.nameItem = nameItem;
        this.total = total;
        this.counter = counter;
        this.price=price;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

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

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getCounter() {
        return counter;
    }

    public void setCounter(String counter) {
        this.counter = counter;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        itemsCart otherItem = (itemsCart) obj;
        return nameItem != null ? nameItem.equals(otherItem.nameItem) : otherItem.nameItem == null;
    }
}
