package org.krithika.grocery.controller;

import org.krithika.grocery.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by administrator on 1/10/20.
 */
@RestController
@RequestMapping("/groceryMgmt/purchase")
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    @PostMapping("/{userId}/groceryList")
    public List<String> purchaseAll(@PathVariable Long userId) {
        return purchaseService.buyAllGroceryList(userId);
    }

    @PostMapping("/{userId}/items")
    public List<String> purchaseItems(@PathVariable Long userId, @RequestBody List<Long> itemIds) {
        return null;
    }

}
