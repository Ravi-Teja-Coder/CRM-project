package com.crmsysyem.product_service.product.domain;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class UserQuantity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long product;

    private Long userId;
    
    private Long quantity;

}
