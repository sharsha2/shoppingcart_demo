package com.example.shoppingcart_demo.controller;

import com.example.shoppingcart_demo.entity.Basket;
import com.example.shoppingcart_demo.entity.Customer;
import com.example.shoppingcart_demo.entity.Items;
import com.example.shoppingcart_demo.entity.ShoppingCart;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/shop")
    public String cust_items(@RequestParam("customer")String name,Model model){

        int pos=shoppingCart.check_cust(name);
        if(pos>=0){

            model.addAttribute("customer",name);
            model.addAttribute("items",shoppingCart.getItems());
            return "cust_list";
        }
        return "redirect:init";
    }

    @GetMapping("/addcustomer")
    public String add_cust(Model model){
        model.addAttribute("customer", new Customer());
        return "cust_form";
    }

    @PostMapping("/savecust")
    public String save_cust(@ModelAttribute("customer") Customer customer){
        if(customer.getName()!=null && customer.getContact()!=null){
            int pos = shoppingCart.check_cust(customer.getName());
            Customer tosave = null;
            if(pos>=0){
                tosave=shoppingCart.getCustomers().get(pos);
                tosave.setName(customer.getName());
                tosave.setContact(customer.getContact());
                return "redirect:init";
            }
            else{
                tosave=customer;
                shoppingCart.add_customer(tosave);
            }
        }
        return "redirect:init";
    }
}
