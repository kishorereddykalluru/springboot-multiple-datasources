package com.dualdb.controller;

import com.dualdb.item.ItemRepository;
import com.dualdb.item.domain.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ItemController {

    @Autowired
    private ItemRepository itemRepository;


    @GetMapping("/items")
    @Transactional("itemTransactionManager")
    public List<Item> getItems() {
      return itemRepository.findAll();
    }
}
