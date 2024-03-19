package com.vtxlab.project.bc_product_data.controller.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.vtxlab.project.bc_product_data.controller.CoinOperation;
import com.vtxlab.project.bc_product_data.infra.Mapper;
import com.vtxlab.project.bc_product_data.model.CoinMarketRespDto;
import com.vtxlab.project.bc_product_data.model.CoingeckoDTO;
import com.vtxlab.project.bc_product_data.service.CoingeckoService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1")
public class CoinController implements CoinOperation {

  @Autowired
  private Mapper mapper;

  @Autowired
  private CoingeckoService coingeckoService;

  @Override
  public ModelAndView getAllCoinData() {
    ModelAndView modelAndView = new ModelAndView("coin");
    List<CoingeckoDTO> result = coingeckoService.getAllCoinData();
    modelAndView.addObject("coin", result);
    return modelAndView;
  }

  @Override
  public String getAllCoinData(Model model) {
    List<CoingeckoDTO> result = coingeckoService.getAllCoinData();
    model.addAttribute("coin", result);
    return "coin";
  }

  @Override
  public List<CoinMarketRespDto> getMarketData() {
    return coingeckoService.getAllCoinData().stream()//
        .map(e -> mapper.map2(e))//
        .collect(Collectors.toList());
  }
}
