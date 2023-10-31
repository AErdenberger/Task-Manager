package taskmanager.data;

import taskmanager.models.Task;

import java.util.List;

public interface TaskRepository {

    //CRUD = Create Read Update Destroy

    List<Task> findAll() throws DataAccessException;
    Task findByID(int taskID) throws DataAccessException;
    Task create(Task task);
    boolean update(Task task);
    boolean delete(int taskID);
}
