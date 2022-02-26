package controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import exception.RecordNotFoundException;
import model.Shop;
import service.ShopService;

@Controller
public class ShopController {

    @Autowired
    private ShopService service;
    @GetMapping("")
    public String home(Shop shop, Model model) {
        List<Shop> list = service.getAllShops();
        model.addAttribute("list", list);
        return "index";
    }
    @GetMapping("/add")
    public String showAddForm(Shop shop, Model model) {
        return "add-shop";
    }
    @PostMapping("/save")
    public String create(Shop shop, Model model) {
        service.saveOrUpdateShop(shop);
        return "redirect:/";
    }
    @RequestMapping(path = { "/update","/update/{id}"})
    public String update(Model model,@PathVariable("id") Integer id) throws RecordNotFoundException {
        if(id!=null) {
            Shop shop2 = service.getShopById(id);
            model.addAttribute("shop", shop2);
        }else {
            model.addAttribute("shop", new Shop());
        }
        return "add-shop";
    }
    @RequestMapping(path = { "/delete/{id}"})
    public String delete(Model model, @PathVariable("id") Integer id) {
        service.deleteShop(id);
        return "redirect:/";
    }
}