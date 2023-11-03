package taskmanager.ui;

import taskmanager.data.DataAccessException;
import taskmanager.domain.TaskResult;
import taskmanager.domain.TaskService;
import taskmanager.models.Task;

import java.util.List;

public class Controller {

    /*
    Mediates between the view and the domain layer
    It has 2 dependencies view and service
    you will NEVER use the console inside of your controller
     */

    public final View view;
    private final TaskService taskService;

    public Controller(View view, TaskService taskService) {
        this.view = view;
        this.taskService = taskService;
    }

    //to run our app
    public void run() {

    }

    private void runMenu() throws DataAccessException {
        boolean exit = false;
        do {
            int selection = view.getMenuOption();
            switch (selection){
                case 1:
                    addTask();
                    break;
                case 2:
                    viewTasks();
                    break;
                case 3:
                    updateTask();
                    break;
                case 4:
                    deleteTask();
                    break;
                case 5:
                    exit = true;
                    break;
            }

        } while(!exit);
    }

    //CREATE
    private void addTask() throws DataAccessException {
        Task task = view.makeTask();

        TaskResult result = taskService.create(task);

        if(result.isSuccessful()){
            view.displayText("Your Task was created successfully!");
        } else {
            view.displayErrors(result.getMessages());
        }


    }

    //READ
    private void viewTasks() throws DataAccessException {
        List<Task> tasks = taskService.findAll();
        view.displayTasks(tasks);
    }

    //UPDATE
    private void updateTask() throws DataAccessException {
        view.displayHeader("Update a Task");
        int id = view.updateByID();
        Task task = taskService.findByID(id);
        if(task != null){
            Task updatedTask = view.makeTask();
            updatedTask.setId((task.getId()));
            TaskResult result = taskService.update(updatedTask);
            if(result.isSuccessful()){
                view.displayText("Success, your task has been updated!");
            } else {
                view.displayErrors(List.of(String.format("There is no Task with id %s", id)));
            }
        }
    }

    //DELETE
    private void deleteTask() throws DataAccessException {
        view.displayHeader("Delete a Task");
        Task task = taskService.findByID(view.updateByID());

        if(task != null){
            TaskResult result = taskService.deleteByID(task.getId());
            if(result.isSuccessful()){
                view.displayText("Your task was successfully deleted!");
            } else {
                view.displayErrors(List.of("There are no tasks with that id."));
            }
        }
    }
}
