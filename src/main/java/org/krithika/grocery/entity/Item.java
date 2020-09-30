package org.krithika.grocery.entity;

import javax.persistence.*;

/**
 * Created by administrator on 23/9/20.
 */
@Entity
@Table(name="T_ITEM")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ITEM_ID")
    private Long id;

    @Column(name="ITEM_NAME")
    private String name;

    @Column(name="ITEM_PRICE")
    private float price;

    @Column(name="ITEM_IN_STOCK_IND")
    private Boolean inStock = true;

    @Column(name="ITEM_QUANTITY")
    private Integer qty = 0;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public boolean isInStock() {
        return inStock;
    }

    public void setInStock(boolean inStock) {
        this.inStock = inStock;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

}
