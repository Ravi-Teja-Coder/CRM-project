package com.crmsysyem.product_service.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crmsysyem.product_service.product.domain.Feedback;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {

}
