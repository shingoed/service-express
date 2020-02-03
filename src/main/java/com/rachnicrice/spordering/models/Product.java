package com.rachnicrice.spordering.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long item_id;

    private String itemCode;
    private String itemName;
    private int width;
    private int length;
    private String color;
    private String style;
    private String type;

    @ManyToMany(mappedBy = "itemsInThisOrder")
    List<Order> ordersThatHaveThisItem;

    public Product() { }

    public Product(String itemCode, String itemName, int width, int length, String color, String style, String type) {
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.width = width;
        this.length = length;
        this.color = color;
        this.style = style;
        this.type = type;
    }

    public Product(String itemCode, String itemName, int length, String color, String type) {
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.length = length;
        this.color = color;
        this.type = type;
    }

    public Product(String itemCode, String itemName, int length, int width, String style, String type) {
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.width = width;
        this.length = length;
        this.style = style;
        this.type = type;
    }


    public Long getId() {
        return item_id;
    }

    public void setId(Long id) {
        this.item_id = id;
    }

    public String getName() {
        return itemName;
    }

    public void setName(String name) {
        this.itemName = name;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Product{" +
                "itemCode='" + itemCode + '\'' +
                ", name='" + itemName + '\'' +
                ", width=" + width +
                ", length=" + length +
                ", color='" + color + '\'' +
                ", style='" + style + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
