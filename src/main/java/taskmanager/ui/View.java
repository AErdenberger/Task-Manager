package taskmanager.ui;

import taskmanager.models.Status;
import taskmanager.models.Task;

import java.util.List;
import java.util.Scanner;

public class View {

    Scanner console = new Scanner(System.in);

    //get MenuOption
    public int getMenuOption(){
        displayHeader("Main Menu");
        displayText("1: Add a Task");
        displayText("2: View all Tasks");
        displayText("3: Update a Task");
        displayText("4: Delete a Task");
        displayText("5: Exit Program");
        return readInt("Select an option [1-5]", 1, 5);
    }

    //CREATE
    public Task makeTask(){
        Task result = new Task();
        result.setCreatedOn(readString("Enter today's date: "));
        result.setTitle(readString("Enter the title of the task: "));
        result.setDescription(readString("Enter the description: "));
        result.setDoDate(readString("Enter the due date: "));
        result.setStatus(readStatus("Enter the Status: "));

        return result;
    }

    //READ
    public void displayTasks(List<Task> tasks){
        for(Task task : tasks){
            displayText(String.format("id: %s, Date: %s, Title: %s, Description: %s, Due Date: %s, Status %s%n",
                    task.getId(),
                    task.getCreatedOn(),
                    task.getTitle(),
                    task.getDescription(),
                    task.getDoDate(),
                    task.getStatus()));
        }
    }

    //UPDATE and DELETE
    public int updateByID(){
        displayText("Input Task ID to modify.");
        int id = readInt("Enter the ID: ", 1, 999999999);
        return id;
    }

    //helper methods
    public void displayHeader(String header){
        System.out.println();
        System.out.println(header);
        System.out.println("*".repeat(header.length()));
    }

    public void displayText(String line){
        System.out.println();
        System.out.println(line);
    }

    public void displayErrors(List<String> errors){
        displayHeader("Errors: ");
        for(String error : errors){
            displayText(error);
        }
        displayText("");
    }

    public String readString(String prompt){
        displayText(prompt);
        String input = console.nextLine();
        if(input == null || input.isBlank()){
            displayText("You must enter a value: ");
            input = readString(prompt);
        }

        return input;
    }

    public int readInt(String prompt, int min, int max){
        do {
            String value = readString(prompt);
            try{
                int intValue = Integer.parseInt(value);
                if(intValue < min || intValue > max){
                    System.out.printf("Choice must be between %s and %s%n", min, max);
                } else {
                    return intValue;
                }
            } catch (NumberFormatException e){
                System.out.printf("%s is not a valid number.%n", value);
            }
        } while (true);
    }

    public Status readStatus(String prompt){
        displayHeader("Task Status:");
        for(Status status : Status.values()){
            displayText(status.getDisplayText());
        }

        do{
            String selection = readString(prompt);
            //take user input and make it uppercase to match enum
            selection = selection.toUpperCase();
            try{
                return Status.valueOf(selection);
            } catch (IllegalArgumentException ex) {
                System.out.printf("%s is not a status%n", selection);
            }
        } while (true);
    }
}
