package taskmanager;

import org.springframework.context.ApplicationContext;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import taskmanager.data.DataAccessException;
import taskmanager.data.TaskFileRepository;
import taskmanager.domain.TaskService;
import taskmanager.models.Task;
import taskmanager.ui.Controller;
import taskmanager.ui.View;

import java.util.List;

@ComponentScan
public class App {
    public static void main(String[] args) {

        ApplicationContext container = new AnnotationConfigApplicationContext(App.class);
    }

    //XML DEPENDENCY INJECTION
    private static void configureWithXMLAndRun(){
        ApplicationContext container = new ClassPathXmlApplicationContext("dependency-config.xml");
        Controller controller = container.getBean(Controller.class);
        controller.run();
    }

    //MANUAL DEPENDENCY INJECTION
    private static void configureManuallyAndRun(){
        TaskFileRepository repository = new TaskFileRepository("./data/tasks.csv");
        TaskService service = new TaskService(repository);
        View view = new View();
        Controller controller = new Controller(view, service);
        controller.run();
    }
}
