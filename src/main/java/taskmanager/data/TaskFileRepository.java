package taskmanager.data;

import taskmanager.models.Task;

import java.util.List;

public class TaskFileRepository implements TaskRepository{

    @Override
    public List<Task> findAll() {
        return null;
    }

    @Override
    public Task findByID(int taskID) {
        return null;
    }

    @Override
    public Task create(Task task) {
        return null;
    }

    @Override
    public boolean update(Task task) {
        return false;
    }

    @Override
    public boolean delete(int taskID) {
        return false;
    }
}
