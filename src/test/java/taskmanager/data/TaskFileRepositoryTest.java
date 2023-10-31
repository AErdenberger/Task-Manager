package taskmanager.data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import taskmanager.models.Status;
import taskmanager.models.Task;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TaskFileRepositoryTest {

    private static final String SEED_FILE = "C:\\Users\\erden\\IdeaProjects\\TaskManager\\data\\tasks-seed.csv";
    private static final String TEST_FILE = "C:\\Users\\erden\\IdeaProjects\\TaskManager\\data\\tasks-test.csv";

    private TaskFileRepository repository = new TaskFileRepository(TEST_FILE);

    //known good state
    @BeforeEach
    public void setup() throws IOException {
        Path seedPath = Paths.get(SEED_FILE);
        Path testPath = Paths.get(TEST_FILE);

        Files.copy(seedPath, testPath, StandardCopyOption.REPLACE_EXISTING);
    }

    @Test
    public void shouldFindAll() throws DataAccessException {
        List<Task> actual = repository.findAll();
        assertEquals(5, actual.size());

        //1,2023-10-08,Review Curriculum,check content for spelling and grammar,2023-10-10,TODO
        Task task = actual.get(0);
        assertEquals(1, task.getId());
        assertEquals("2023-10-08", task.getCreatedOn());
        assertEquals("Review Curriculum", task.getTitle());
        assertEquals("check content for spelling and grammar", task.getDescription());
        assertEquals("2023-10-10", task.getDoDate());
        assertEquals(Status.TODO, task.getStatus());

    }

    @Test
    public void shouldFindByExistingID() throws DataAccessException{
        Task taskOne = repository.findByID(1);

        assertNotNull(taskOne);
        assertEquals(1, taskOne.getId());
        assertEquals("2023-10-08", taskOne.getCreatedOn());
        assertEquals("Review Curriculum", taskOne.getTitle());
        assertEquals("check content for spelling and grammar", taskOne.getDescription());
        assertEquals("2023-10-10", taskOne.getDoDate());
        assertEquals(Status.TODO, taskOne.getStatus());
    }

    @Test
    public void shouldNotFindNonExistingID() throws DataAccessException {
        Task notValid = repository.findByID(9999);
        assertNull(notValid);
    }

    //create
    @Test
    public void shouldCreate() throws DataAccessException {
        Task task = new Task(
                0,
                "2024-02-01",
                "Mom's Birthday Reminder",
                "Order and send flowers to mom",
                "2024-02-05",
                Status.TODO
        );

        Task actual = repository.create(task);
        assertEquals(6, actual.getId());

        List<Task> all = repository.findAll();

        assertEquals(6, all.size());

        assertEquals("2024-02-01", actual.getCreatedOn());
        assertEquals("Mom's Birthday Reminder", actual.getTitle());
        assertEquals("Order and send flowers to mom", actual.getDescription());
        assertEquals("2024-02-05", actual.getDoDate());
        assertEquals(Status.TODO, actual.getStatus());
    }
    //create with commas
    @Test
    public void shouldCreateWithCommas() throws DataAccessException {
        Task task = new Task(
                0,
                "2024-02-01",
                "Book venues, wedding, reception, bridal shower",
                "Book venues for upcoming events - wedding, reception, and bridal shower",
                "2024-05-05",
                Status.IN_PROGRESS
        );

        Task actual = repository.create(task);
        assertEquals(6, actual.getId());

        List<Task> all = repository.findAll();

        assertEquals(6, all.size());
        assertEquals("2024-02-01", actual.getCreatedOn());
        assertEquals("Book venues, wedding, reception, bridal shower", actual.getTitle());
        assertEquals("Book venues for upcoming events - wedding, reception, and bridal shower", actual.getDescription());
        assertEquals("2024-05-05", actual.getDoDate());
        assertEquals(Status.IN_PROGRESS, actual.getStatus());
    }
}