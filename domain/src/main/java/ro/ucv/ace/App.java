package ro.ucv.ace;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import ro.ucv.ace.config.DomainConfig;
import ro.ucv.ace.model.AutomaticTestedTask;
import ro.ucv.ace.model.ManualTestedTask;
import ro.ucv.ace.model.Subject;
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

        app.saveTasks();
    }

    private void delete() {
        System.out.println(taskService.delete(1));
    }

    private void findOne() {
        try {
            Task t = taskService.findOne(3);
            System.out.println(t);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


    }


    public void saveTasks() {
        Task t1 = new AutomaticTestedTask("at", "descriere at", "java", "nada nada", "potato potato");
        Task t2 = new ManualTestedTask("mt", "descriere mt", "java");

        t1.addSubject(new Subject(1, "fef", 5));

        try {
            taskService.save(t1);
            taskService.save(t2);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void getTasks() {
        List<Task> tasks = taskService.findAll();
        tasks.forEach(System.out::println);
    }

    public void findTaskByName() {
        List<Task> tasks = taskService.findByName("mt");
        tasks.forEach(System.out::println);
    }
}
