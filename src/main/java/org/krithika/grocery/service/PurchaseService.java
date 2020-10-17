package org.krithika.grocery.service;

import org.krithika.grocery.entity.GroceryListItem;
import org.krithika.grocery.entity.Item;
import org.krithika.grocery.entity.User;
import org.krithika.grocery.repository.ItemRepository;
import org.krithika.grocery.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by administrator on 1/10/20.
 */
@Service
public class PurchaseService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ItemRepository itemRepository;

    public List<String> buyAllGroceryList(Long userId) {
        List<String> responseList = new ArrayList<>();
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            responseList.add( "Invalid User Id: " + userId);
        } else {
            Set<GroceryListItem> groceryList = user.getGroceryList();
            Iterator<GroceryListItem> iterator = groceryList.iterator();
            while (iterator.hasNext()) {
                GroceryListItem groceryListItem = iterator.next();
                Long itemId = groceryListItem.getItemId();
                String response = buyItem(itemId);
                responseList.add(response);
                groceryListItem.setPurchased(response.contains("successful"));
            }
            userRepository.save(user);
        }
        return responseList;
    }

    public List<String> buyList(Long userId, List<Long> itemIds) {
        List<String> responseList = new ArrayList<>();
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            responseList.add( "Invalid User Id: " + userId);
        } else {
            List<Long> groceryListItemIds = getGroceryListItemIds(user);
            Set<GroceryListItem> groceryList = user.getGroceryList();
            Iterator<Long> idIterator = itemIds.iterator();
            while (idIterator.hasNext()) {
                Long itemId = idIterator.next();
                if (groceryListItemIds.contains(itemId)) {
                    responseList.add(buyItem(itemId));
                    String response = buyItem(itemId);
                    responseList.add(response);
                    if (response.contains("successful")) {
                        setPurchasedInd(user, itemId);
                    }
                }
            }
            userRepository.save(user);
            }

        return responseList;
    }

    private List<Long> getGroceryListItemIds(User user) {
        List<Long> groceryListItemIds = new ArrayList<>();
        Iterator<GroceryListItem> iterator = user.getGroceryList().iterator();
        while (iterator.hasNext()) {
            groceryListItemIds.add(iterator.next().getItemId());
        }
        return groceryListItemIds;
    }

    private void setPurchasedInd(User user, Long itemId) {
        Set<GroceryListItem> groceryList = user.getGroceryList();
        Iterator<GroceryListItem> groceryListItemIterator = groceryList.iterator();
        while (groceryListItemIterator.hasNext()) {
            GroceryListItem groceryListItem = groceryListItemIterator.next();
            if (groceryListItem.getItemId().equals(itemId)) {
                groceryListItem.setPurchased(true);
            }
        }
    }

    private String buyItem(Long itemId) {
        Item item = itemRepository.findById(itemId).orElse(null);
        if (item == null) {
             return "Invalid Item Id: " + itemId;
        } else {
            if (item.getQty() > 0) {
                int qty = item.getQty() - 1;
                item.setQty(qty);
                item.setInStock(qty>0);
                return "Purchase successful for item: " + itemId + "-" + item.getName();
            } else {
                return "Insufficient quantity in stock for item: " + itemId + "-" + item.getName();
            }
        }
    }

}
