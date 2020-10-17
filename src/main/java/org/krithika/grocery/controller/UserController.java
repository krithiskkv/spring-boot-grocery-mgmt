package org.krithika.grocery.controller;

import org.krithika.grocery.entity.GroceryListItem;
import org.krithika.grocery.entity.User;
import org.krithika.grocery.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by administrator on 1/10/20.
 */
@RestController
@RequestMapping("/groceryMgmt/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{userId}")
    public User getUserById(@PathVariable Long userId) {
        return userService.getUserById(userId);
    }

    @PostMapping("/register")
    public boolean createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @PostMapping("/{userId}/addItems")
    public String addItems(@PathVariable("userId") Long userId, @RequestBody List<Long> itemIds) {
        return userService.addItemsToList(userId, itemIds);
    }

    @PostMapping("/{userId}/removeItems")
    public String removeItems(@PathVariable("userId") Long userId, @RequestBody List<Long> itemIds) {
        return userService.deleteItemsFromList(userId, itemIds);
    }

    @GetMapping("/{userId}/itemStatus/{itemId}")
    public GroceryListItem getItemStatus(@PathVariable Long userId, @PathVariable Long itemId) {
        return null;
    }

}
