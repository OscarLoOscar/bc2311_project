package com.vtxlab.project.bc_product_data.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tproduct_stocks")
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class StockList {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Integer id;

  String stockId;
}
