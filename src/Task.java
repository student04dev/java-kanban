import java.util.Objects;

public class Task {
    private String name;
    private String description;
    private TaskStatus status;

    @Override
    public String toString() {
        return "Task{" +
                "type='" + this.getClass() + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                '}';
    }

    public Task() {
    }

    public Task(String name, String description) {
        this.name = name;
        this.description = description;
        status = TaskStatus.NEW;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus taskStatus) {
        this.status = taskStatus;
    }
}
