package com.vtxlab.project.bc_product_quote.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.vtxlab.project.bc_product_quote.entity.CoingeckoEntity;

public interface CoingeckoRepo extends JpaRepository<CoingeckoEntity, Long>{
  
}
