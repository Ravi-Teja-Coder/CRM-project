package com.crmsysyem.product_service.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.crmsysyem.product_service.product.domain.Feedback;
import com.crmsysyem.product_service.product.domain.Product;
import com.crmsysyem.product_service.product.domain.ProductResp;
import com.crmsysyem.product_service.product.domain.ProductsItemsCountResponse;
import com.crmsysyem.product_service.product.domain.QuantityCountResponse;
import com.crmsysyem.product_service.product.service.ProductService;

@RestController
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@PostMapping("/register")
	public Product createUser(@RequestBody Product product) throws Exception  {
		return productService.createProduct(product);
	}
	
	@GetMapping("/viewAll")
	public List<Product> getAllUsers() {

		List<Product> list = productService.getAllProduct();
		return list;

	}
	
	@PostMapping("/add-feedback-review")
	public Feedback addFeedback(@RequestBody Feedback feedback) throws Exception {
		return productService.addFeedbackAndReview(feedback);
	}
	
	@GetMapping("/manage-quantity")
	public QuantityCountResponse addOrDeleteQuantity(@RequestParam Boolean boolean1 ,@RequestParam Long productId ,@RequestParam Long userId) throws Exception {
		QuantityCountResponse countResponse = productService.addOrRemoveQuantity(boolean1, productId, userId);
		return countResponse;
	}
	
	@GetMapping("/user-products")
	public List<ProductResp> getAllUserRelatedProducts(@RequestParam Long userId) {
		
		List<ProductResp> productResp = productService.getAllUserRelatedProducts(userId);
		return productResp;
	}



}
