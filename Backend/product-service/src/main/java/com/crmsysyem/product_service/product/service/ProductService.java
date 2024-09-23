package com.crmsysyem.product_service.product.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crmsysyem.product_service.product.domain.Feedback;
import com.crmsysyem.product_service.product.domain.Product;
import com.crmsysyem.product_service.product.domain.ProductResp;
import com.crmsysyem.product_service.product.domain.ProductsItemsCountResponse;
import com.crmsysyem.product_service.product.domain.QuantityCountResponse;
import com.crmsysyem.product_service.product.domain.UserQuantity;
import com.crmsysyem.product_service.product.repository.FeedbackRepository;
import com.crmsysyem.product_service.product.repository.ProductRepository;
import com.crmsysyem.product_service.product.repository.UserQuantityRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private FeedbackRepository feedbackRepository;
	
	@Autowired
	private UserQuantityRepository userQuantityRepository;
	
	public Product createProduct(Product product) throws Exception {
		if (product.equals(null)) {
			throw new Exception("please enter the customer details");
		}
		return productRepository.save(product);
	}
	
	public List<Product> getAllProduct() {
		return productRepository.findAll();

	}
	
	public Feedback addFeedbackAndReview(Feedback feedback) throws Exception {
		
			 Product product = productRepository.findById(feedback.getProduct())
                     .orElseThrow(() -> new RuntimeException("Product not found"));

        feedback.setProduct(product.getId());
		
		return feedbackRepository.save(feedback);

	}
	
	public QuantityCountResponse addOrRemoveQuantity(Boolean boolean1, Long productId, Long userId) throws Exception {

		Map<String, Long> response = new HashMap<>();
		Product product = productRepository.findById(productId).orElseThrow();

		if (product.getQuantity() > 0 && boolean1) {

			List<UserQuantity> quantityList = userQuantityRepository.findByProductAndUserId(productId, userId);
			if (quantityList.isEmpty()) {
				UserQuantity quantity1 = new UserQuantity();
				quantity1.setQuantity(1l);
				quantity1.setProduct(productId);
				quantity1.setUserId(userId);

				product.setQuantity(product.getQuantity() - 1);
				productRepository.save(product);

				userQuantityRepository.save(quantity1);
				response.put("quantity", quantity1.getQuantity());
				QuantityCountResponse countResponse = new QuantityCountResponse();
				countResponse.setCount(response);
				return countResponse;
			}
			for (UserQuantity quantity : quantityList) {
				quantity.setQuantity(quantity.getQuantity() + 1);

				product.setQuantity(product.getQuantity() - 1);
				productRepository.save(product);

				userQuantityRepository.save(quantity);
				response.put("quantity", quantity.getQuantity());
				QuantityCountResponse countResponse = new QuantityCountResponse();
				countResponse.setCount(response);
				return countResponse;
			}
		}
		if (!boolean1) {
			Map<String, Long> response1 = new HashMap<>();
			List<UserQuantity> quantityList1 = userQuantityRepository.findByProductAndUserId(productId, userId);

			for (UserQuantity quantity : quantityList1) {
				if (quantity.getQuantity()>0) {
					
				quantity.setQuantity(quantity.getQuantity() - 1);

				product.setQuantity(product.getQuantity() + 1);
				productRepository.save(product);

				userQuantityRepository.save(quantity);
				response1.put("quantity", quantity.getQuantity());
				QuantityCountResponse countResponse = new QuantityCountResponse();
				countResponse.setCount(response1);
				return countResponse;
				}
			}
		}
		throw new Exception("no quantity on this product");
	}
	
	public List<ProductResp> getAllUserRelatedProducts(Long userId){
		
		List<ProductResp> productRespList = new ArrayList<>();
		List<UserQuantity> quantityList = userQuantityRepository.findByUserId(userId);
		if (!quantityList.isEmpty()) {
			
			Map<Long, Long> productQuantityMap = quantityList.stream()
				    .collect(Collectors.groupingBy(
				        UserQuantity::getProduct,            
				        Collectors.summingLong(UserQuantity::getQuantity)  
				    ));	
		List<Long> productIds =quantityList.stream().map(UserQuantity::getProduct).collect(Collectors.toList());
		List<Product> productList = productRepository.findAllById(productIds);
		
		for(Product product:productList) {
			
			ProductResp resp = new ProductResp();
			resp.setId(product.getId());
			resp.setName(product.getName());
			resp.setQuantity(product.getQuantity());
			resp.setUrl(product.getUrl());
			if (productQuantityMap.containsKey(product.getId())) {
			    Long quantity = productQuantityMap.get(product.getId()); 
			    resp.setUserQuantity(quantity);  
			}
			
			productRespList.add(resp);
		}
		
		
		return productRespList;
		}
		return new ArrayList<>();
	}
}
