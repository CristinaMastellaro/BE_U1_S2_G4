package cristinamastellaro.entities;

import java.util.Random;

public class Customer {
    private final long id;
    private String name;
    private int tier;

    public Customer(String name, int tier) {
        Random r = new Random();
        id = r.nextInt(1000000, 999999999);
        this.name = name;
        this.tier = tier;
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

    public int getTier() {
        return tier;
    }

    public void setTier(int tier) {
        if (tier != 1 && tier != 2 && tier != 3 && tier != 4) System.err.println("Valore non accettabile");
        else this.tier = tier;
    }

    @Override
    public String toString() {
        return "Nome cliente: " + name +
                ", tier: " + tier +
                ", id: " + id;
    }
}
