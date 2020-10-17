package org.krithika.grocery.entity;

import javax.persistence.*;

/**
 * Created by administrator on 1/10/20.
 */
@Embeddable
public class GroceryListItem {

    @Column(name="GROCERY_STORE_ITEM_ID")
    private Long itemId;

    @Column(name="GROCERY_LIST_ITEM_PURCHASE_IND")
    private boolean purchased = false;

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public boolean isPurchased() {
        return purchased;
    }

    public void setPurchased(boolean purchased) {
        this.purchased = purchased;
    }

    public GroceryListItem() {}

    public GroceryListItem(Long itemId) {
        this.itemId = itemId;
    }

}
