package taskmanager.data;

import taskmanager.models.Status;
import taskmanager.models.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskRepositoryDouble implements TaskRepository{
    @Override
    public List<Task> findAll() throws DataAccessException {
        List<Task> all = new ArrayList<>();
        all.add(new Task(1,
                "2024-08-01",
                "Bake Cake",
                "bake a cake for rover's birthday",
                "2024-08-22",
                Status.TODO));

        all.add(new Task(2,
                "2023-11-02",
                "Send Invitations",
                "send invitations for holiday party",
                "11-10-2023",
                Status.IN_PROGRESS));

        all.add(new Task(3,
                "2023-06-02",
                "Buy Party Decorations",
                "buy decorations for fourth of July BBQ",
                "2023-07-02",
                Status.COMPLETE));

        return all;
    }

    @Override
    public Task findByID(int taskID) throws DataAccessException {
        for(Task task: findAll()){
            if(task.getId() == taskID){
                return task;
            }
        }
        return null;
    }

    @Override
    public Task create(Task task) throws DataAccessException {
        task.setId(99);
        return task;
    }

    @Override
    public boolean update(Task task) throws DataAccessException {
        return task.getId() > 0;
    }

    @Override
    public boolean delete(int taskID) throws DataAccessException {
        return taskID != 999;
    }
}
