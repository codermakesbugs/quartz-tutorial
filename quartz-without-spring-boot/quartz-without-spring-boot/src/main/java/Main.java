import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

public class Main {
  public static void main(String[] args) {
    try {
      StdSchedulerFactory factory = new StdSchedulerFactory();
      Scheduler scheduler = factory.getScheduler();

      JobDetail jobDetail =
          JobBuilder.newJob()
              .ofType(HelloWorld.class)
              .withIdentity("Hello-World-job-name")
              .withDescription("My Job!")
              .build();

      SimpleTrigger simpleTrigger =
          TriggerBuilder.newTrigger()
              .withIdentity("Hello-World-trigger-name SimpleTrigger")
              .forJob(jobDetail)
              .withSchedule(
                  SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(5).repeatForever())
              .build();

      CronTrigger cronTrigger =
          TriggerBuilder.newTrigger()
              .withIdentity("Hello-World-trigger-name CronTrigger")
              .forJob(jobDetail)
              .withSchedule(CronScheduleBuilder.cronSchedule("0/15 * * ? * * *"))
              .build();
      //      scheduler.scheduleJob(jobDetail, simpleTrigger);
      scheduler.scheduleJob(jobDetail, cronTrigger);
      scheduler.start();

    } catch (SchedulerException e) {
      System.out.println(e.getMessage());
    }
  }
}
