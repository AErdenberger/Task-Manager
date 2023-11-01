package taskmanager.domain;

import org.junit.jupiter.api.Test;
import taskmanager.data.DataAccessException;
import taskmanager.data.TaskRepository;
import taskmanager.data.TaskRepositoryDouble;
import taskmanager.models.Status;
import taskmanager.models.Task;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TaskServiceTest {

    TaskRepository repository = new TaskRepositoryDouble();
    TaskService service = new TaskService(repository);

    //CREATE
    //Happy Path
    @Test
    public void shouldCreateTask(){

    }

    //READ
    //Happy Path
    @Test
    public void shouldFindAll() throws DataAccessException {
        List<Task> actual = service.findAll();

        assertEquals(actual.size(), 3);

        //1,
        //"2024-08-01",
        //"Bake Cake",
        //"bake a cake for rover's birthday",
        // "2024-08-22",
        //Status.TODO

        Task task = actual.get(0);
        assertEquals(1, task.getId());
        assertEquals("2024-08-01", task.getCreatedOn());
        assertEquals("Bake Cake", task.getTitle());
        assertEquals("bake a cake for rover's birthday", task.getDescription());
        assertEquals("2024-08-22", task.getDoDate());
        assertEquals(Status.TODO, task.getStatus());

    }

    @Test
    public void shouldFindExistingID(){

    }

    //Sad Path
    @Test
    public void shouldNotFindNonExistingID(){

    }

    //UPDATE

    //DELETE

}