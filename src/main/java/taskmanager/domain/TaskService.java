package taskmanager.domain;

import taskmanager.data.DataAccessException;
import taskmanager.data.TaskRepository;
import taskmanager.models.Task;

import java.util.List;

public class TaskService {

    private final TaskRepository repository;

    public TaskService(TaskRepository repository){
        this.repository = repository;
    }

    //read
    public List<Task> findAll() throws DataAccessException {
        return repository.findAll();
    }

    public Task findByID(int taskID) throws DataAccessException {
        return repository.findByID(taskID);
    }
}
