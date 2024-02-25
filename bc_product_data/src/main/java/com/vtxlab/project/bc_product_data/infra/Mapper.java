package com.vtxlab.project.bc_product_data.infra;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Mapper {

  @Autowired
  ModelMapper modelMapper;

}

