package cristinamastellaro.entities;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

public class Order {
    private final long id;
    private String status;
    private LocalDate orderDate;
    private LocalDate deliveryDate;
    private List<Product> products;
    private Customer customer;

    public Order(List<Product> products, Customer customer) {
        Random r = new Random();
        id = r.nextLong(100000000, 999999999);
        this.status = "Request_Sent";
        this.orderDate = LocalDate.now();
        this.deliveryDate = LocalDate.now().plusDays(5);
        this.products = products;
        this.customer = customer;
    }

    public Order(LocalDate orderDate, List<Product> products, Customer customer) {
        this(products, customer);
        this.orderDate = orderDate;
        deliveryDate = orderDate.plusDays(5);
    }

    // Getters and setters

    public long getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDate deliveryDate) {
        if (deliveryDate.isBefore(orderDate)) System.err.println("Data selezionata non valida!");
        else this.deliveryDate = deliveryDate;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "\nProdotti: " + products +
                ", id:" + id +
                ", status: " + status +
                ", data dell'ordine: " + orderDate +
                ", data di consegna:" + deliveryDate +
                ", cliente: " + customer;
    }
}
