package com.crmsysyem.product_service.product.repository;

import java.lang.StackWalker.Option;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crmsysyem.product_service.product.domain.UserQuantity;
import java.util.List;


@Repository
public interface UserQuantityRepository extends JpaRepository<UserQuantity, Long> {

	 List<UserQuantity> findByProductAndUserId(Long product, Long userId);

	 List<UserQuantity>  findByUserId(Long userId);
}
