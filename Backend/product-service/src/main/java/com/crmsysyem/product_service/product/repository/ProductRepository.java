package com.crmsysyem.product_service.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crmsysyem.product_service.product.domain.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

	Product getQuantityById(Long productId);

}
