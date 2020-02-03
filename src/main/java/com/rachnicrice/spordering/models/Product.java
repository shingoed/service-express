package com.rachnicrice.spordering.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String itemCode;
    private String name;
    private int width;
    private int length;
    private String color;
    private String style;
    private String type;


    public Product() { }

    public Product(String itemCode, String name, int width, int length, String color, String style, String type) {
        this.itemCode = itemCode;
        this.name = name;
        this.width = width;
        this.length = length;
        this.color = color;
        this.style = style;
        this.type = type;
    }
    public Product(String itemCode, String name, int length, String color, String type) {
        this.itemCode = itemCode;
        this.name = name;
        this.length = length;
        this.color = color;
        this.type = type;
    }

    public Product(String itemCode, String name, int length, int width, String style, String type) {
        this.itemCode = itemCode;
        this.name = name;
        this.width = width;
        this.length = length;
        this.style = style;
        this.type = type;
    }


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
                ", name='" + name + '\'' +
                ", width=" + width +
                ", length=" + length +
                ", color='" + color + '\'' +
                ", style='" + style + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}