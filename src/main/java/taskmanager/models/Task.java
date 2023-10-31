package taskmanager.models;

public class Task {
    private int id;
    private String createdOn; //date type is better
    private String title;
    private String description;
    private String doDate; //date type is better here as well
    private Status status;

    public Task(int id, String createdOn, String title, String description, String doDate, Status status) {
        this.id = id;
        this.createdOn = createdOn;
        this.title = title;
        this.description = description;
        this.doDate = doDate;
        this.status = status;
    }

    public Task(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDoDate() {
        return doDate;
    }

    public void setDoDate(String doDate) {
        this.doDate = doDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Task{");
        sb.append("id=").append(id);
        sb.append(", createdOn='").append(createdOn).append('\'');
        sb.append(", title='").append(title).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", doDate='").append(doDate).append('\'');
        sb.append(", status=").append(status);
        sb.append('}');
        return sb.toString();
    }
}
