package ru.yandex.practicum.javakanban.task;

public class Subtask extends Task {
    private final int parentEpicId;

    @Override
    public String toString() {
        return super.toString() + "with additional params {\nparentEpicId=" + parentEpicId + "}\n";
    }

    public Subtask(String name, String description, int parentEpicId, TaskStatus taskStatus) {
        super(name, description, taskStatus);
        this.parentEpicId = parentEpicId;
    }

    public int getParentEpicId() {
        return parentEpicId;
    }
}
