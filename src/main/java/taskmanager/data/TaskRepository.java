package taskmanager.data;

import taskmanager.models.Task;

import java.util.List;

public interface TaskRepository {

    //CRUD = Create Read Update Destroy

    List<Task> findAll() throws DataAccessException;
    Task findByID(int taskID) throws DataAccessException;
    Task create(Task task) throws DataAccessException;
    boolean update(Task task) throws DataAccessException;
    boolean delete(int taskID) throws DataAccessException;
}
