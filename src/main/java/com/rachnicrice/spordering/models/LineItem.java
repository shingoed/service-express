package com.rachnicrice.spordering.models;

import javax.persistence.*;
import java.util.Date;

@Entity
public class LineItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lineItem_id;

    @OneToOne
    Product lineItem;

    int quantity;

    @ManyToOne
    Order order;

    public LineItem(Order order, Product lineItem, int quantity) {
        this.order = order;
        this.lineItem = lineItem;
        this.quantity = quantity;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getLineItem() {
        return lineItem;
    }

    public void setLineItem(Product lineItem) {
        this.lineItem = lineItem;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
