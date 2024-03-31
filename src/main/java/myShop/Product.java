package myShop;

import java.util.Objects;

public class Product {
    private String name;
    private String producer;
    private double price;
    private int rating;

    public Product (String name, String producer, double price) {
        this.name = name;
        this.producer = producer;
        this.price = price;
    }

    public double getPrice() {
        return this.price;
    }

    public String getName() {
        return this.name;
    }

    public String getProducer() {
        return this.producer;
    }

    public int getRating() {
        return  this.rating;
    }

    public void setRating() {
        rating++;
    }

    public void removeRating() {
        rating--;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, producer);
    }
}
