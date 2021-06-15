package com.example.shoppingcart_demo.entity;

import java.util.HashMap;

public class Basket {

    HashMap<Items,Integer> basket;

    public Basket() {
        this.basket=new HashMap<>();
    }

    public HashMap<Items, Integer> getBasket() {
        return basket;
    }
}
