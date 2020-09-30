package org.krithika.grocery.service;

import org.krithika.grocery.entity.Item;
import org.krithika.grocery.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Created by administrator on 23/9/20.
 */
@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    public boolean createItemList(List<Item> itemList) {
        itemRepository.saveAll(itemList);
        return true;
    }

    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    public Optional<Item> getItemById(Long id) {
        return itemRepository.findById(id);
    }

    public List<Item> getItems(String itemString) {
        return itemRepository.findByNameContainsIgnoreCase(itemString);
    }

    public boolean procureItem(Long itemId, Integer qty) {

        Item toBeProcured = itemRepository.findById(itemId).isPresent() ? itemRepository.findById(itemId).get() : null;

        if (toBeProcured != null) {
            toBeProcured.setInStock(true);
            toBeProcured.setQty(toBeProcured.getQty() + qty);
            itemRepository.save(toBeProcured);
            return true;
        } else {
            return false;
        }

    }

    public void deleteItem(Long itemId) {
        itemRepository.deleteById(itemId);
    }

}
