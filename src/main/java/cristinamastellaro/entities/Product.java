package cristinamastellaro.entities;

import java.util.Random;

public class Product {
    private final long id;
    private String name;
    private String category;
    private double price;

    public Product(String name, String category, double price) {
        Random r = new Random();
        id = r.nextInt(1000000, 999999999);
        this.name = name;
        this.category = category;
        this.price = price;
    }

    // Getters and setters

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        try {
            if (price > 0) this.price = price;
            else throw new Exception("Prezzo inserito non valido");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }


    }

    @Override
    public String toString() {
        return "\nNome:" + name +
                ", id: " + id +
                ", categoria: " + category +
                ", prezzo:" + price + "€";
    }
}
