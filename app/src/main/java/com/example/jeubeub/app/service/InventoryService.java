package com.example.jeubeub.app.service;

import com.example.jeubeub.app.model.InventoryItem;

import java.util.ArrayList;
import java.util.List;

public class InventoryService {


    public List<InventoryItem> getInventoryPlayer(String id){
        //TODO api.getItemFor(id)

        List<InventoryItem> list = new ArrayList<>();
        list.add(new InventoryItem("Or", 450));
        list.add(new InventoryItem("Gemme", 124));
        return list;
    }
}
