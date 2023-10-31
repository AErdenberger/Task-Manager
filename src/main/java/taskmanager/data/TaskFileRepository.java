package taskmanager.data;

import taskmanager.models.Status;
import taskmanager.models.Task;

import java.io.IOException;
import java.io.PrintWriter;
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

    //deserialize data
    private Task lineToTask(String line){
        String[] fields = line.split(DELIMETER);
        if(fields.length != 6){
            return null;
        }

        Task task = new Task(
                Integer.parseInt(fields[0]),
                restore(fields[1]),
                restore(fields[2]),
                restore(fields[3]),
                restore(fields[4]),
                Status.valueOf(fields[5])
        );

        return task;
    }

    //serializing tasks
    private String taskToLine(Task task){
        //add a comma after each field
        StringBuilder buffer = new StringBuilder(100);
        buffer.append(task.getId()).append(DELIMETER);

        /*
        this is the line that would look at each date which is a string
        if there are ',' we would replace that with @@@ here
         */
        buffer.append(clean(task.getCreatedOn())).append(DELIMETER);
        buffer.append(clean(task.getTitle())).append(DELIMETER);
        buffer.append(clean(task.getDescription())).append(DELIMETER);
        buffer.append(clean(task.getDoDate())).append(DELIMETER);
        buffer.append(task.getStatus());

        return buffer.toString();
    }

    //write to file
    private void writeToFile(List<Task> tasks) throws DataAccessException {
        try (PrintWriter writer = new PrintWriter(filePath)){
            //print the header
            writer.println("id,createdOn,title,description,doDate,Status");

            for(Task task : tasks){
                String line = taskToLine(task);
                writer.println(line);
            }

        } catch (IOException ex) {
            throw new DataAccessException("Could not write to filepath: " + filePath);
        }
    }

    private int getNextID(List<Task> tasks){
        int maxID = 0;
        for(Task task: tasks){
            if(maxID < task.getId()){
                maxID = task.getId();
            }
        }
        maxID++;
        return maxID;
    }
}
