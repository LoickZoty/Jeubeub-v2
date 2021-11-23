package com.example.jeubeub.app.inventory.model;

public class InventoryItem {

    private String name;
    private int quantity;

    public InventoryItem(String name, int quantity){
        this.name = name;
        this.quantity = quantity;
    }

    public String getName(){
        return this.name;
    }

    public int getQuantity(){
        return this.quantity;
    }
}
