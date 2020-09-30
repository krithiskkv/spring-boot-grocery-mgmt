package org.krithika.grocery.controller;

import org.krithika.grocery.entity.Item;
import org.krithika.grocery.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by administrator on 26/9/20.
 */
@RestController
@RequestMapping("/items")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @PostMapping
    private boolean addItemList(@RequestBody List<Item> itemList) {
        return itemService.createItemList(itemList);
    }

    @GetMapping
    private List<Item> getAllItems() {
        return itemService.getAllItems();
    }

    @GetMapping("/{id}")
    private Item getItemById(@PathVariable Long id) {
         return itemService.getItemById(id).isPresent() ? itemService.getItemById(id).get() : null ;
    }

    @GetMapping("/search")
    private List<Item> getItem(@RequestParam("itemName") String itemName){
        return itemService.getItems(itemName);
    }

    @PutMapping("/procure/{itemId}/{qty}")
    private boolean procureItem(@PathVariable("itemId") Long itemId, @PathVariable("qty") Integer qty) {
        return itemService.procureItem(itemId, qty);
    }

    @DeleteMapping("/{itemId}")
    private boolean deleteById(@PathVariable Long itemId) {
        itemService.deleteItem(itemId);
        return true;
    }

}
