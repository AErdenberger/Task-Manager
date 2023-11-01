package taskmanager.domain;

import taskmanager.models.Task;

import java.util.ArrayList;

public class TaskResult {

    //holds all the error messages
    private final ArrayList<String> messages = new ArrayList<>();
    private Task task; //payload

    public ArrayList<String> getMessages() {
        return messages;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public void addMessage(String message){
        messages.add(message);
    }

    public boolean isSuccessful(){
        return messages.isEmpty();
    }
}
