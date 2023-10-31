package taskmanager.data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import taskmanager.models.Status;
import taskmanager.models.Task;

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

}