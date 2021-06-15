package com.example.shoppingcart_demo.controller;

import com.example.shoppingcart_demo.entity.Basket;
import com.example.shoppingcart_demo.entity.Customer;
import com.example.shoppingcart_demo.entity.Items;
import com.example.shoppingcart_demo.entity.ShoppingCart;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;

@Controller
@RequestMapping("/shopping")
public class HomeController {

    ShoppingCart shoppingCart = new ShoppingCart("Big Bazaar");

    @PostConstruct
    public void addcustomers(){
        shoppingCart.add_item(new Items("Juice", 45.5, 120));
        shoppingCart.add_item(new Items("Biscuits", 10.0, 299));
        shoppingCart.add_item(new Items("Chips", 99.99, 10));
        shoppingCart.add_customer(new Customer("Harsha","9999999999",new Basket()));
        shoppingCart.add_customer(new Customer("Preetham","7777777777",new Basket()));
        shoppingCart.add_customer(new Customer("Chay","8888888888",new Basket()));
    }

    @GetMapping("/init")
    public String homepage(Model model){
        model.addAttribute("customers", shoppingCart.getCustomers());
        return "home";
    }
}
