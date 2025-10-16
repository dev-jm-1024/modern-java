package com.study.common;

public class Product {

    private int id;
    private String name;
    private int prices;
    private ProductType type; // ✅ enum 타입 필드 추가

    public Product(int id, String name, int prices, ProductType type) {
        this.id = id;
        this.name = name;
        this.prices = prices;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrices() {
        return prices;
    }

    public void setPrices(int prices) {
        this.prices = prices;
    }

    public ProductType getType() {
        return type;
    }

    public void setType(ProductType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", prices=" + prices +
                ", type=" + type +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    // ✅ Enum 정의
    public enum ProductType {
        ELECTRONICS,
        FOOD,
        CLOTHING,
        HOME,
        BOOK,
        BEAUTY
    }
}
