package com.example.jeubeub.app.service;

import com.example.jeubeub.app.model.InventoryItem;

import java.util.ArrayList;
import java.util.List;

public class InventoryService {


    public List<InventoryItem> getInventoryPlayer(String id){
        //call vers l'apo pour rechercher dans la base de donnée tous les items qu'a le joueur
        List<InventoryItem> list = new ArrayList<>();
        list.add(new InventoryItem("Or", 450));
        list.add(new InventoryItem("Gemme", 124));
        return list;
    }
}
