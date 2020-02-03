package com.rachnicrice.spordering.models;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long order_id;

    Date date_created;
    Boolean isSubmitted;

    @ManyToOne
    ApplicationUser user;

    @ManyToMany
    @JoinTable(name = "orders_items",
            joinColumns = { @JoinColumn(name = "order_id")},
            inverseJoinColumns = { @JoinColumn(name = "item_id")}
    )
    List<Product> itemsInThisOrder;

    public Order(ApplicationUser user, Date date_created, Boolean isSubmitted) {
        this.user = user;
        this.date_created = date_created;
        this.isSubmitted = isSubmitted;
    }

    public Long getOrder_id() {
        return order_id;
    }

    public Date getDate_created() {
        return date_created;
    }

    public Boolean getSubmitted() {
        return isSubmitted;
    }

    public ApplicationUser getUser() {
        return user;
    }

    public List<Product> getItemsInThisOrder() {
        return itemsInThisOrder;
    }
}
