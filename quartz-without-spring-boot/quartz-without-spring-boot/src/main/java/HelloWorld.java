import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloWorld implements Job {
  private static final Logger LOGGER = LoggerFactory.getLogger(HelloWorld.class);

  @Override
  public void execute(JobExecutionContext context) {
    LOGGER.info(
        "Job ** {} ** starting @ {}",
        context.getJobDetail().getKey().getName(),
        context.getFireTime());

    LOGGER.info("Hello World job! ----- {}", context.getJobDetail().getDescription());

    LOGGER.info(
        "Job ** {} ** completed.  Next job scheduled @ {}",
        context.getJobDetail().getKey().getName(),
        context.getNextFireTime());
  }
}
