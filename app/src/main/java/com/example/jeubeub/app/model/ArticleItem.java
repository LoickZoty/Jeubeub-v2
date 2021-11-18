package com.example.jeubeub.app.model;

public class ArticleItem {

    private String name;
    private double price;

    public ArticleItem(String name, double price){
        this.name = name;
        this.price = price;
    }

    public String getName(){
        return this.name;
    }

    public double getPrice(){
        return this.price;
    }
}
