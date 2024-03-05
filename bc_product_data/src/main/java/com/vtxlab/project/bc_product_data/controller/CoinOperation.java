package com.vtxlab.project.bc_product_data.controller;

import java.util.List;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import com.vtxlab.project.bc_product_data.model.CoingeckoDTO;

public interface CoinOperation {

  // @GetMapping("/coin")
  ModelAndView getAllCoinData();
  
  @GetMapping("/coin")
  String getAllCoinData(Model model);
}
