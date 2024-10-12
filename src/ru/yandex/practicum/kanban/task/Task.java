package ru.yandex.practicum.kanban.task;

public class Task {
    private String name;
    private String description;
    private TaskStatus status;

    @Override
    public String toString() {
        return "\nTask{" +
                "type='" + this.getClass() + '\'' +
                ", \nname='" + name + '\'' +
                ", \ndescription='" + description + '\'' +
                ", \nstatus=" + status +
                "}\n";
    }

    public Task() {
    }

    public Task(String name, String description, TaskStatus taskStatus) {
        this.name = name;
        this.description = description;
        this.status = taskStatus;
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
