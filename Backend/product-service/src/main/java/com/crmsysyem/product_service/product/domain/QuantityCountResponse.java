package com.crmsysyem.product_service.product.domain;

import java.util.Map;

import lombok.Data;

@Data
public class QuantityCountResponse {

	Map<String, Long> count;
}
