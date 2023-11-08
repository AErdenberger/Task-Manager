package taskmanager.data;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import taskmanager.models.Status;
import taskmanager.models.Task;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TaskFileRepository implements TaskRepository{

    private static final String DELIMITER = ",";
    private static final String DELIMITER_REPLACEMENT = "@@@";
    private final String filePath;

    public TaskFileRepository(@Value("./data/tasks.csv") String filePath){
        this.filePath = filePath;
    }

    @Override
    public List<Task> findAll() throws DataAccessException {
        //create a list of tasks

        List<Task> result = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(filePath))){

            reader.readLine();

            for(String line = reader.readLine(); line != null; line = reader.readLine()){
                //iterator is a line
                //we're going to read the file until the line we read is null
                //loop through the file line-by-line

                Task task = lineToTask(line);
                result.add(task);
            }

        } catch (FileNotFoundException ex){
            //do nothing
        } catch (IOException ex){
            throw new DataAccessException("Could not open file path: " + filePath);
        }

        return result;
    }

    @Override
    public Task findByID(int taskID) throws DataAccessException {
        List<Task> all = findAll();
        for(Task task : all){
            if(task.getId() == taskID){
                return task;
            }
        }

        return null;
    }

    @Override
    public Task create(Task task) throws DataAccessException {
        List<Task> all = findAll();

        //generate ID
        int nextID = getNextID(all);
        task.setId(nextID);

        all.add(task);

        writeToFile(all);

        return task;
    }

    @Override
    public boolean update(Task task) throws DataAccessException {
        List<Task> all = findAll();
        for(int i = 0; i < all.size(); i++){
            if(all.get(i).getId() == task.getId()){
                all.set(i, task);
                writeToFile(all);
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean delete(int taskID) throws DataAccessException {
        List<Task> all = findAll();
        for(int i = 0; i < all.size(); i++){
            if(all.get(i).getId() == taskID){
                all.remove(i);
                writeToFile(all);
                return true;
            }
        }

        return false;
    }

    //helper methods

    //help clean up Strings for dates because commas occur a lot in English writing
    private String restore(String value){
        return value.replace(DELIMITER_REPLACEMENT, DELIMITER);
    }

    private String clean(String value){
        return value.replace(DELIMITER, DELIMITER_REPLACEMENT);
    }

    //deserialize data
    private Task lineToTask(String line){
        String[] fields = line.split(DELIMITER);
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
        buffer.append(task.getId()).append(DELIMITER);

        /*
        this is the line that would look at each date which is a string
        if there are ',' we would replace that with @@@ here
         */
        buffer.append(clean(task.getCreatedOn())).append(DELIMITER);
        buffer.append(clean(task.getTitle())).append(DELIMITER);
        buffer.append(clean(task.getDescription())).append(DELIMITER);
        buffer.append(clean(task.getDoDate())).append(DELIMITER);
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
