package com.crmsysyem.product_service.product.domain;

import lombok.Data;

@Data
public class ProductResp {

	  private Long id;

	   private String name;

	   private Integer quantity;
	    
	   private String url;
	   
	   private Long UserQuantity;
}
