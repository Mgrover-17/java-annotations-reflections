import java.lang.annotation.*;
import java.lang.reflect.Method;

// Create the container annotation
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface BugReports {
    BugReport[] value();
}

// Create the repeatable annotation
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Repeatable(BugReports.class)
@interface BugReport {
    String description();
    String reportedBy() default "Unknown";
    String date() default "N/A";
}

// Apply the annotation multiple times on a method
class IssueTracker {

    @BugReport(description = "Null pointer when input is null", reportedBy = "Ankit", date = "2025-04-10")
    @BugReport(description = "Fails when ID is negative", reportedBy = "Riya", date = "2025-04-08")
    public void processUser() {
        System.out.println("Processing user...");
    }
}

// Retrieve and print all bug reports using Reflection
public class RepeatableAnnotation {
    public static void main(String[] args) throws Exception {
        Method method = IssueTracker.class.getMethod("processUser");

        if (method.isAnnotationPresent(BugReports.class)) {
            BugReports bugReports = method.getAnnotation(BugReports.class);
            for (BugReport br : bugReports.value()) {
                System.out.println("Bug Description: " + br.description());
                System.out.println("Reported Date: " + br.date());
                System.out.println("Reported By: " + br.reportedBy());
                System.out.println();
            }
        }
    }
}
