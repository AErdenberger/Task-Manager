package taskmanager.models;

public enum Status {
    TODO("To do"),
    IN_PROGRESS("In Progress"),
    COMPLETE("Completed");

    private String displayText;

    Status(String displayText) {
        this.displayText = displayText;
    }

    public String getDisplayText() {
        return displayText;
    }
}
