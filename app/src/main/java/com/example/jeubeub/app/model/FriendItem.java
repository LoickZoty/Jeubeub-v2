package com.example.jeubeub.app.model;

import android.widget.ImageView;

public class FriendItem {

    private String name;
    private int id;

    public FriendItem(String name, int id){
        this.name = name;
        this.id = id;
    }

    public String getName(){
        return this.name;
    }

    public int getId(){
        return this.id;
    }

}
