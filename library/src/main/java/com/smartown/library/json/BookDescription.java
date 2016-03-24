package com.smartown.library.json;

/**
 * Created by Tiger on 2016-03-24.
 */
public class BookDescription {

    @JsonDescription(key = "price")
    private double price;

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "BookDescription{" +
                "price=" + price +
                '}';
    }
}
