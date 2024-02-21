package com.vtxlab.project.bc_product_quote.entity;

import java.sql.Timestamp;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "tproduct_")
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private String username;
  private String role;
  private Timestamp createdAt;

}
