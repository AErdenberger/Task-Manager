package taskmanager.data;

import taskmanager.models.Task;

import java.util.List;

public class TaskFileRepository implements TaskRepository{

    private static final String DELIMETER = ",";
    private static final String DELIMETER_REPLACEMENT = "@@@";
    private final String filePath;
    public TaskFileRepository(String filePath){
        this.filePath = filePath;
    }

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

    //helper methods

    //help clean up Strings for dates because commas occur a lot in English writing
    private String restore(String value){
        return value.replace(DELIMETER_REPLACEMENT, DELIMETER);
    }

    private String clean(String value){
        return value.replace(DELIMETER, DELIMETER_REPLACEMENT);
    }
}
