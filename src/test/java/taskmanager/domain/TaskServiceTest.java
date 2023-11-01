package taskmanager.domain;

import org.junit.jupiter.api.Test;
import taskmanager.data.TaskRepository;
import taskmanager.data.TaskRepositoryDouble;

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
    public void shouldFindAll(){

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