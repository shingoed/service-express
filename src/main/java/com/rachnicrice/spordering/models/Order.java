package com.rachnicrice.spordering.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long order_id;

    Date date_created;
    Boolean isSubmitted;

    @ManyToOne
    ApplicationUser user;

    @OneToMany(mappedBy = "order")
    List<LineItem> lineItemsInThisOrder;

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

    public List<LineItem> getItemsInThisOrder() {
        return lineItemsInThisOrder;
    }
}
