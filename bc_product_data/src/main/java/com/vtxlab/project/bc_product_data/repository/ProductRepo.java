package com.vtxlab.project.bc_product_data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.vtxlab.project.bc_product_data.entity.ProductEntity;

public interface ProductRepo extends JpaRepository<ProductEntity, Integer> {

}
