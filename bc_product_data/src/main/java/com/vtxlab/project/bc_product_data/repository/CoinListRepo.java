package com.vtxlab.project.bc_product_data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.vtxlab.project.bc_product_data.entity.CoinList;

public interface CoinListRepo extends JpaRepository<CoinList, Integer> {

  void deleteByCoinId(String coinId);
}
