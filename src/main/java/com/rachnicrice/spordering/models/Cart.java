package com.rachnicrice.spordering.models;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cart_id;

    Date date_created;

    @OneToOne
    ApplicationUser user;

    @ManyToMany
    @JoinTable(name = "carts_items",
            joinColumns = { @JoinColumn(name = "cart_id")},
            inverseJoinColumns = { @JoinColumn(name = "item_id")}
    )
    List<Product> itemsInThisCart;

    public Cart(ApplicationUser user, Date date_created) {
        this.user = user;
        this.date_created = date_created;
    }
}
