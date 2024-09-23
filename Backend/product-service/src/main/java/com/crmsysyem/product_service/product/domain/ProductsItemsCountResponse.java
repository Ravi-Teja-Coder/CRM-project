package com.crmsysyem.product_service.product.domain;

import java.util.List;

import lombok.Data;

@Data
public class ProductsItemsCountResponse {

	Long totalCount;
	
	List<Product> products;
}
