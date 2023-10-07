import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.slf4j.LoggerFactory;

public class HelloWorld implements Job {
  @Override
  public void execute(JobExecutionContext context) {
    System.out.printf(
        "Job ** %s ** starting @ %s\n",
        context.getJobDetail().getKey().getName(), context.getFireTime());
    System.out.println("Hello World job!");
    System.out.printf(
        "Job ** %s ** completed.  Next job scheduled @ %s\n",
        context.getJobDetail().getKey().getName(), context.getNextFireTime());
  }
}
