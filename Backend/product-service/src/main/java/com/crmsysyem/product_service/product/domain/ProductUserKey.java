package com.crmsysyem.product_service.product.domain;
import java.io.Serializable;
import java.util.Objects;

public class ProductUserKey implements Serializable {
    
    private Integer product;
    private Integer userId;

    // Default constructor
    public ProductUserKey() {}

    public ProductUserKey(Integer product, Integer userId) {
        this.product = product;
        this.userId = userId;
    }

    // Getters, Setters, equals() and hashCode() methods
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductUserKey that = (ProductUserKey) o;
        return Objects.equals(product, that.product) &&
               Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product, userId);
    }
}
