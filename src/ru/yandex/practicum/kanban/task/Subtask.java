package ru.yandex.practicum.kanban.task;

public class Subtask extends Task {
    private final int parentEpicId;

    @Override
    public String toString() {
        return super.toString() + "with additional parameters {\nparentEpicId=" + parentEpicId + "}\n";
    }

    public Subtask(int id, String name, String description, int parentEpicId, TaskStatus taskStatus) {
        super(id, name, description, taskStatus);
        this.parentEpicId = parentEpicId;
    }

    public int getParentEpicId() {
        return parentEpicId;
    }
}
