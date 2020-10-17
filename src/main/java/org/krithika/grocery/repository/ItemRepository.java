package org.krithika.grocery.repository;

import org.krithika.grocery.entity.GroceryListItem;
import org.krithika.grocery.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by administrator on 23/9/20.
 */
public interface ItemRepository extends JpaRepository<Item, Long> {

    List<Item> findAll();

    List<Item> findByInStock(boolean isInStock);

    Item findByName(String name);

    List<Item> findByNameContainsIgnoreCase(String itemString);

}
