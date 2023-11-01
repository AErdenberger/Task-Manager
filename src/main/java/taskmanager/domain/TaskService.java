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

    /*
    VALIDATION RULES
    1 - We cannot add a task with an id > 0
    2 - We need to check that createdON exists (cannot be blank)
    3 - we need to check that a title exists, and it cannot be greater than 50 characters
    4 - We need to make sure there is a description and the description cannot be less than 20 characters
    5 - We need to make sure there is a doDate (cannot be blank)
    6 - We need to ensure the task has a status

    If anything fails we need to let the user know
     */

    //helper method
    public TaskResult validate(Task task){
        TaskResult result = new TaskResult();

        if(task == null){
            result.addMessage("Task cannot be null");
            return result;
        }

        if(task.getCreatedOn().isBlank() || task.getCreatedOn() == null) {
            result.addMessage("Created on date is required");
            return result;
        }

        if(task.getTitle() == null ||
                task.getTitle().isBlank() ||
                task.getTitle().length() > 50) {

            result.addMessage("Title is required and cannot be longer than 50 characters");
            return result;

        }

        if(task.getDescription() == null ||
                task.getDescription().isBlank() ||
                task.getDescription().length() < 20) {

            result.addMessage("Description is required and must be longer than 20 characters");
            return result;

        }

        if(task.getDoDate() == null || task.getDoDate().isBlank()) {
            result.addMessage("Due date is required");
            return result;
        }

        if (task.getStatus() == null){
            result.addMessage("Status is required");
            return result;
        }

        return result;
    }
}
