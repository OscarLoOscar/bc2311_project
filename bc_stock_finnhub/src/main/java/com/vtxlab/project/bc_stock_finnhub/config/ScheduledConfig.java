package com.vtxlab.project.bc_stock_finnhub.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.vtxlab.project.bc_stock_finnhub.infra.RedisHelper;

@Component
@EnableScheduling
public class ScheduledConfig {

  @Autowired
  RedisHelper redisHelper;

  @Autowired
  AppRunner appRunner;

  @Scheduled(fixedRate = 60000)
  public void scheduleFixedRateTask() throws Exception {
    // make System.currentTimeMillis() to seconds
    appRunner.run();
  }


  // @Scheduled(fixedDelay = 3000)
  public void scheduleFixedDelayTask() {
    System.out
        .println("Fixed delay task - " + System.currentTimeMillis() / 1000);
  }


  // @Scheduled(cron = "*/5 * * * * ?")
  @Async
  public void scheduleTaskUsingCronExpression() throws InterruptedException {
    System.out.println("Cron Task - " + System.currentTimeMillis() / 1000);
    // Thread.sleep(10000);
    System.out.println("End Cron ");
  }

  // @Scheduled(cron = "10 * * * * ?")
  @Async
  public void cronTask() throws InterruptedException {
    System.out.println("Cron Task - " + System.currentTimeMillis() / 1000);
    // Thread.sleep(10000);
    System.out.println("End Cron ");
  }


}
