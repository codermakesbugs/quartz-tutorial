package com.example.quartz.tutorial.job;

import com.example.quartz.tutorial.configure.QuartzConfig;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.stereotype.Component;

@Component
@DisallowConcurrentExecution
public class HelloWorld implements Job {
  private final Logger LOGGER = LoggerFactory.getLogger(HelloWorld.class);

  @Override
  public void execute(JobExecutionContext context) {
    LOGGER.info(
        "Job ** {} ** starting @ {}",
        context.getJobDetail().getKey().getName(),
        context.getFireTime());
    System.out.println("Hello World job!");
    LOGGER.info(
        "Job ** {} ** completed.  Next job scheduled @ {}",
        context.getJobDetail().getKey().getName(),
        context.getNextFireTime());
  }

  @Bean
  public JobDetailFactoryBean helloWorldBeanJob() {
    return QuartzConfig.createJobDetail(HelloWorld.class, "Hello World!");
  }

  @Bean
  public CronTriggerFactoryBean triggerHelloWorldJob(
      @Qualifier("helloWorldBeanJob") JobDetail jobDetail) {
    return QuartzConfig.createCronTrigger(jobDetail, "0 * * ? * * *", "trigger Hello World job!");
  }
}
