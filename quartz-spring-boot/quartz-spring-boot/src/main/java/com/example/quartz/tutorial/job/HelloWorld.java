package com.example.quartz.tutorial.job;

import com.example.quartz.tutorial.configure.QuartzConfig;
import org.quartz.*;
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

    JobKey key = context.getJobDetail().getKey();

    JobDataMap dataMap = context.getJobDetail().getJobDataMap();

    System.out.printf(
        "Hello World job key: %s - text: %s - number: %s !\n",
        key.getName(), dataMap.get("text"), dataMap.get("number"));

    LOGGER.info(
        "Job ** {} ** completed.  Next job scheduled @ {}",
        context.getJobDetail().getKey().getName(),
        context.getNextFireTime());
  }

  @Bean
  public JobDetailFactoryBean helloWorldBeanJob() {
    LOGGER.debug(
        "createJobDetail(jobClass={}, jobName={})", HelloWorld.class.getName(), "Hello World!");

    JobDetailFactoryBean factoryBean = new JobDetailFactoryBean();
    factoryBean.setName("Hello World!");
    factoryBean.setJobClass(HelloWorld.class);

    JobDataMap jobDataMap = new JobDataMap();
    jobDataMap.put("text", "develop");
    jobDataMap.put("number", 6969);
    factoryBean.setJobDataMap(jobDataMap);

    factoryBean.setDurability(true);

    return factoryBean;
  }

  @Bean
  public CronTriggerFactoryBean triggerHelloWorldJob(
      @Qualifier("helloWorldBeanJob") JobDetail jobDetail) {
    CronTriggerFactoryBean factoryBean = new CronTriggerFactoryBean();
    factoryBean.setJobDetail(jobDetail);
    factoryBean.setCronExpression("0/15 * * ? * * *");
    factoryBean.setName("triggerHelloWorld");

    return factoryBean;
  }
}
