package com.crmsysyem.product_service.product.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "feedback")
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
   
    private Long userId;
    
    private Integer rating;
    
    private String review;

//    @ManyToOne(fetch = FetchType.LAZY)  // Use lazy loading for better performance
//    @JoinColumn(name = "product_id", nullable = false)  // Foreign key column for Product
    private Long product;
}
