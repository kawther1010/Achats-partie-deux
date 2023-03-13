package com.example.appexo02tp03;
public class Achat {
    private final String name;
    private final double quantity;

    public Achat(String name, double quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public double getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return name + " - " + quantity;
    }
}