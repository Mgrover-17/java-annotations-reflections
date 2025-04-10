package com.bridgelabz.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import java.lang.reflect.Method;

//  Define custom annotation
@Retention(RetentionPolicy.RUNTIME)  // Make it available at runtime
@Target(ElementType.METHOD)         // Can be applied to methods
@interface TaskInfo {
    String priority();
    String assignedTo();
}

//  Create a class and use the annotation
class TaskManager {

    @TaskInfo(priority = "High", assignedTo = "Ankit")
    public void completeReport() {
        System.out.println("Completing report task...");
    }

    @TaskInfo(priority = "Medium", assignedTo = "Riya")
    public void updateDatabase() {
        System.out.println("Updating database...");
    }
}

//  Use Reflection to access annotation values
public class CustomAnnotation {
    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();
        Method[] methods = TaskManager.class.getDeclaredMethods();

        for (Method method : methods) {
            if (method.isAnnotationPresent(TaskInfo.class)) {
                TaskInfo taskInfo = method.getAnnotation(TaskInfo.class);
                System.out.println("Method: " + method.getName());
                System.out.println("Priority: " + taskInfo.priority());
                System.out.println("Assigned To: " + taskInfo.assignedTo());
                System.out.println();
            }
        }
    }
}
