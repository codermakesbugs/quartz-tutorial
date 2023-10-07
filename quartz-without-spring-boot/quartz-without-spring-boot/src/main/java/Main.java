import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

public class Main {
  public static void main(String[] args) {
    try {
      Scheduler scheduler = new StdSchedulerFactory().getScheduler();

      JobDetail jobDetailSimple =
          JobBuilder.newJob()
              .ofType(HelloWorld.class)
              .withIdentity("Hello-World-job-name 1")
              .withDescription("My Job 1!")
              .build();

      JobDetail jobDetailCron =
          JobBuilder.newJob()
              .ofType(HelloWorld.class)
              .withIdentity("Hello-World-job-name 2")
              .withDescription("My Job 2!")
              .build();

      SimpleTrigger simpleTrigger =
          TriggerBuilder.newTrigger()
              .withIdentity("Hello-World-trigger-name SimpleTrigger")
              .forJob(jobDetailSimple)
              .withSchedule(
                  SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(15).repeatForever())
              .build();

      CronTrigger cronTrigger =
          TriggerBuilder.newTrigger()
              .withIdentity("Hello-World-trigger-name CronTrigger")
              .forJob(jobDetailCron)
              .withSchedule(CronScheduleBuilder.cronSchedule("0/30 * * ? * * *"))
              .build();

      scheduler.scheduleJob(jobDetailSimple, simpleTrigger);
      scheduler.scheduleJob(jobDetailCron, cronTrigger);
      scheduler.start();

    } catch (SchedulerException e) {
      System.out.println(e.getMessage());
    }
  }
}
