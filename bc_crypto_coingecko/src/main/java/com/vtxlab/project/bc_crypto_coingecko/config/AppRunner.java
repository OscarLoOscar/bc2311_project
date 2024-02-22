package com.vtxlab.project.bc_crypto_coingecko.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AppRunner implements CommandLineRunner {

  // ready the element to start the sever
  // main testk in AppRunner:
  // 1.Check on configuration - DB or yml
  // 2.Invoke API to get data(i.e. configuration , raw data)
  @Override
  public void run(String... args) throws Exception {
    System.out.println("Hello World");
  }

}
