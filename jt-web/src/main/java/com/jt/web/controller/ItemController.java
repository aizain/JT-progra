package com.jt.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jt.web.pojo.Item;
import com.jt.web.pojo.ItemDesc;
import com.jt.web.service.ItemService;

@Controller
public class ItemController {
    
    @Autowired
    private ItemService itemService;
    
    // www.jd.com/item/itemId.html
    @RequestMapping("/item/{id}")
    public String item(@PathVariable Long id, Model model) {
        Item item = itemService.get(id);
        ItemDesc itemDesc = itemService.getItemDesc(id);
        model.addAttribute("item", item);
        model.addAttribute("itemDesc", itemDesc);
        return "item";
    }
}
