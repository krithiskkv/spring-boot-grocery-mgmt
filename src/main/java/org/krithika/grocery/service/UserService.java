package org.krithika.grocery.service;

import org.krithika.grocery.entity.Item;
import org.krithika.grocery.entity.GroceryListItem;
import org.krithika.grocery.entity.User;
import org.krithika.grocery.repository.ItemRepository;
import org.krithika.grocery.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by administrator on 1/10/20.
 */
@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;

    public boolean createUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return true;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUserNameEqualsIgnoreCase(username);
    }

    public User getUserById(Long id) {
        Optional<User> result =  userRepository.findById(id);
        return result.orElse(null);
    }

    public String addItemsToList(Long userId, List<Long> itemIds) {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            return "Invalid UserId: " + userId;
        } else {
            Set<GroceryListItem> groceryList = user.getGroceryList();
            Iterator<Long> itemIterator = itemIds.iterator();
            while (itemIterator.hasNext()) {
                Long itemId = itemIterator.next();
                Item item = itemRepository.findById(itemId).orElse(null);
                if (item == null) {
                    return "Invalid ItemId: " + itemId;
                } else {
                    GroceryListItem groceryListItem = new GroceryListItem(itemId);
                    groceryList.add(groceryListItem);
                }
            }
            userRepository.save(user);
        }
        return "Items added successfully to grocery list";
    }

    public String deleteItemsFromList(Long userId, List<Long> items) {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            return "Invalid UserId: " + userId;
        } else {
            Set<GroceryListItem> groceryList = user.getGroceryList();
            Set<GroceryListItem> test = new TreeSet<>();
            Iterator<Long> itemIterator = items.iterator();
            while (itemIterator.hasNext()) {
                Long itemId = itemIterator.next();
                Item item = itemRepository.findById(itemId).orElse(null);
                if (item == null) {
                    return "Invalid ItemId: " + itemId;
                } else {
                    groceryList.remove(item);
                }
            }
            userRepository.save(user);
        }
        return "Items removed successfully from grocery list";
    }

    public GroceryListItem getItemStatus(Long userId, Long itemId) {
        return null;
    }

}
