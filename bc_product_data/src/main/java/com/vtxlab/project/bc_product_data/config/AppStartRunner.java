package com.vtxlab.project.bc_product_data.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AppStartRunner implements CommandLineRunner {

  @Override
  public void run(String... args) throws Exception {
    System.out.println("AppStartRunner.run()");
    // throw new RuntimeException();
  }

}
