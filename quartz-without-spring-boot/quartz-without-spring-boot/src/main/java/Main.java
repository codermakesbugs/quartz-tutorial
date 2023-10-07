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
              .withIdentity("Hello-World-trigger-name")
              .forJob(jobDetail)
              .withSchedule(
                  SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(5).repeatForever())
              .build();

      scheduler.scheduleJob(jobDetail, simpleTrigger);
      scheduler.start();

    } catch (SchedulerException e) {
      System.out.println(e.getMessage());
    }
  }
}
