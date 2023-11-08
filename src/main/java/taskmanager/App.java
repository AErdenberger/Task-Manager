package taskmanager;

import org.springframework.context.ApplicationContext;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import taskmanager.data.DataAccessException;
import taskmanager.data.TaskFileRepository;
import taskmanager.domain.TaskService;
import taskmanager.models.Task;
import taskmanager.ui.Controller;
import taskmanager.ui.View;

import java.util.List;

public class App {
    public static void main(String[] args) {
//        TaskFileRepository repository = new TaskFileRepository("./data/tasks.csv");
//        TaskService service = new TaskService(repository);
//        View view = new View();
//        Controller controller = new Controller(view, service);
//        controller.run();

        ApplicationContext container = new ClassPathXmlApplicationContext("dependency-config.xml");
        Controller controller = container.getBean(Controller.class);
        controller.run();
    }

    //XML DEPENDENCY INJECTION

    //MANUAL DEPENDENCY INJECTION
    private static void configureManuallyAndRun(){

    }
}
