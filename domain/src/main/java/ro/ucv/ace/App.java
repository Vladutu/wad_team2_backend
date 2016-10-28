package ro.ucv.ace;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import ro.ucv.ace.config.DomainConfig;
import ro.ucv.ace.model.AutomaticTestedTask;
import ro.ucv.ace.model.ManualTestedTask;
import ro.ucv.ace.model.Task;
import ro.ucv.ace.service.TaskService;

import java.util.List;

/**
 * Hello world!
 */
@Component
public class App {

    @Autowired
    private TaskService taskService;

    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(DomainConfig.class);
        App app = ctx.getBean(App.class);

        app.sayHello();
        app.saveTasks();
        app.getTasks();
        app.taskService.updateTask();

    }

    private void sayHello() {
        System.out.println("Hello world! as a bean");
    }

    public void saveTasks() {
        Task t1 = new AutomaticTestedTask("at", "descriere at", "java", "nada nada", "potato potato");
        Task t2 = new ManualTestedTask("mt", "descriere mt", "python");

        taskService.saveTask(t1);
        taskService.saveTask(t2);
    }

    public void getTasks() {
        List<Task> tasks = taskService.findTasks();
        tasks.forEach(System.out::println);
    }
}
