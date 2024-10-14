package ru.yandex.practicum.kanban.task;

import java.util.Objects;

public class Task {
    private int id;
    private final String name;
    private final String description;
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

    public Task(int id, String name, String description, TaskStatus taskStatus) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = taskStatus;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Task task = (Task) object;
        return id == task.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
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
