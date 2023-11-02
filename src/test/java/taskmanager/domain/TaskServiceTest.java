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
    public void shouldCreateTask() throws DataAccessException {
        TaskResult actual = service.create(new Task(0,
                "2023-05-09",
                "Prepare Snacks",
                "prepare apple slices and snack for soccer game",
                "2023-05-11",
                Status.COMPLETE));

        assertNotNull(actual.getTask()); //verify payload is set
        assertTrue(actual.isSuccessful());
        assertEquals(99, actual.getTask().getId()); //in the double the ID is set to 99
    }

    //Sad Path
    @Test
    public void shouldNotCreateNullTask() throws DataAccessException {
        TaskResult actual = service.create(null);
        assertFalse(actual.isSuccessful());
        assertNull(actual.getTask());
        assertEquals("Task cannot be null", actual.getMessages().getFirst());
    }

    @Test
    public void ShouldNotCreateTaskWithoutStartDate() throws DataAccessException {
        TaskResult actual = service.create(new Task(0,
                "  ",
                "Prepare Snacks",
                "prepare apple slices and snack for soccer game",
                "2023-05-11",
                Status.COMPLETE));

        assertFalse(actual.isSuccessful());
        assertNull(actual.getTask());
        assertEquals("Created-on date is required", actual.getMessages().getFirst());
    }

    @Test
    public void shouldNotCreateTaskWithoutTitle() throws DataAccessException {
        TaskResult actual = service.create(new Task(0,
                "2023-05-09",
                "   ",
                "prepare apple slices and snack for soccer game",
                "2023-05-11",
                Status.COMPLETE));

        assertFalse(actual.isSuccessful());
        assertNull(actual.getTask());
        assertEquals("Title is required and cannot be longer than 50 characters", actual.getMessages().getFirst());
    }

    @Test
    public void shouldNotCreateTaskWithTitleLongerThan50() throws DataAccessException {
        TaskResult actual = service.create(new Task(0,
                "2023-05-09",
                "Prepare Snacksjfldsc;nzjklsmrngfjkslhnmafkldsnzfjklvhdsajl;kfzdslm;c,.a",
                "prepare apple slices and snack for soccer game",
                "2023-05-11",
                Status.COMPLETE));

        assertFalse(actual.isSuccessful());
        assertNull(actual.getTask());
        assertEquals("Title is required and cannot be longer than 50 characters", actual.getMessages().getFirst());
    }

    @Test
    public void shouldNotCreateTaskWithoutDescription() throws DataAccessException {
        TaskResult actual = service.create(new Task(0,
                "2023-05-09",
                "Prepare Snacks",
                "   ",
                "2023-05-11",
                Status.COMPLETE));

        assertFalse(actual.isSuccessful());
        assertNull(actual.getTask());
        assertEquals("Description is required and must be longer than 20 characters", actual.getMessages().getFirst());
    }

    @Test
    public void shouldNotCreateTaskWithDescriptionLessThan20() throws DataAccessException {
        TaskResult actual = service.create(new Task(0,
                "2023-05-09",
                "Prepare Snacks",
                "do it.",
                "2023-05-11",
                Status.COMPLETE));

        assertFalse(actual.isSuccessful());
        assertNull(actual.getTask());
        assertEquals("Description is required and must be longer than 20 characters", actual.getMessages().getFirst());
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
    public void shouldFindExistingID() throws DataAccessException {
        Task task = service.findByID(1);
        assertNotNull(task);

        assertEquals(1, task.getId());
        assertEquals("2024-08-01", task.getCreatedOn());
        assertEquals("Bake Cake", task.getTitle());
        assertEquals("bake a cake for rover's birthday", task.getDescription());
        assertEquals("2024-08-22", task.getDoDate());
        assertEquals(Status.TODO, task.getStatus());
    }

    //Sad Path
    @Test
    public void shouldNotFindNonExistingID() throws DataAccessException {
        Task actual = service.findByID(9999);
        assertNull(actual);
    }

    //UPDATE
    //Happy Path
    @Test
    public void shouldUpdateExistingTask() throws DataAccessException {
        List<Task> all = service.findAll();
        Task toUpdate = all.getFirst();
        toUpdate.setDescription("This is an updated task description - testing 1-2-3");

        TaskResult actual = service.update(toUpdate);
        assertTrue(actual.isSuccessful());
        assertEquals(0, actual.getMessages().size()); //no error messages
        assertEquals("This is an updated task description - testing 1-2-3", all.getFirst().getDescription());
    }

    //Sad Path
    @Test
    public void shouldNotUpdateNonExistingTask() throws DataAccessException {
        Task task = new Task(0,
                "2024-08-01",
                "Fake Task",
                "This is a fake task description that has at least 20 characters",
                "2024-08-02",
                Status.TODO);

        TaskResult actual = service.update(task);
        assertFalse(actual.isSuccessful());
        assertEquals(1, actual.getMessages().size());
        assertTrue(actual.getMessages().getFirst().contains("does not exist"));
    }

    @Test
    public void shouldNotUpdateNullTask() throws DataAccessException {
        TaskResult actual = service.update(null);
        assertFalse(actual.isSuccessful());
        assertEquals(1, actual.getMessages().size());
        assertEquals("Task cannot be null", actual.getMessages().getFirst());
    }

    //DELETE
    //Happy Path
    @Test
    public void shouldDeleteExistingTask() throws DataAccessException {
        TaskResult actual = service.deleteByID(1);

        assertTrue(actual.isSuccessful());
    }

    //Sad Path
    @Test
    public void shouldNotDeleteNonExistingTask() throws DataAccessException {
        TaskResult actual = service.deleteByID(999);

        assertFalse(actual.isSuccessful());
        assertEquals(1, actual.getMessages().size());
        assertTrue(actual.getMessages().getFirst().contains("does not exist"));
    }

}