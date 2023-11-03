package taskmanager.ui;

import taskmanager.domain.TaskService;

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

    private void runMenu(){
        boolean exit = false;
        do {
            int selection = view.getMenuOption();
            switch (selection){
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    exit = true;
                    break;
            }

        } while(!exit);
    }

    //CREATE
    private void addTask(){

    }

    //READ
    private void viewTasks(){

    }

    //UPDATE
    private void updateTask(){

    }

    //DELETE
    private void deleteTask(){

    }
}
