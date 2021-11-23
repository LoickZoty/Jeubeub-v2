package com.example.jeubeub.app.inventory.shop.model;

public class ArticleItem {

    private int id;
    private String name;
    private double price;
    private int quantity;

    public ArticleItem(int id, String name, double price, int quantity){
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public int getId(){return this.id;}

    public String getName(){
        return this.name;
    }

    public double getPrice(){
        return this.price;
    }

    public int getQuantity(){return this.quantity;}
}
