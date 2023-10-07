import org.quartz.Job;
import org.quartz.JobExecutionContext;

public class HelloWorld implements Job {
  @Override
  public void execute(JobExecutionContext context) {
    System.out.println("Hello from the Job ! \n" + context.getJobDetail().getDescription());
  }
}
