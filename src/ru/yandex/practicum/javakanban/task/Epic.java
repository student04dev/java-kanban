package ru.yandex.practicum.javakanban.task;

import java.util.ArrayList;
import java.util.HashMap;

public class Epic extends Task {
    private int allSubtasksQuantity;
    private int newSubtasksQuantity;
    private int inProgressSubtasksQuantity;
    ArrayList<Integer> subtasksList = new ArrayList<>();

    @Override
    public String toString() {
        return super.toString() + "with additional params {\nallSubtasksQuantity=" + allSubtasksQuantity +
                ", \nnewSubtasksQuantity=" + newSubtasksQuantity +
                ", \ninProgressSubtasksQuantity=" + inProgressSubtasksQuantity +
                ", \nsubtasksList=" + subtasksList +
                "}\n";
    }

    public Epic(String name, String description, TaskStatus taskStatus) {
        super(name, description, taskStatus);
    }

    public void addSubtaskToList(int subtaskId) {
        subtasksList.add(subtaskId);
    }

    public void clearStats() {
        this.allSubtasksQuantity = 0;
        this.newSubtasksQuantity = 0;
        this.inProgressSubtasksQuantity = 0;
    }

    public void calculateEpicStatus(int epicId, HashMap<Integer, Subtask> subtasksMap) {
        this.clearStats();
        for (Subtask subtaskId : subtasksMap.values()) {
            if (subtaskId.getParentEpicId() == epicId) {
                this.allSubtasksQuantity++;
                switch (subtaskId.getStatus()) {
                    case TaskStatus.NEW:
                        this.newSubtasksQuantity++;
                        break;
                    case TaskStatus.IN_PROGRESS:
                        this.inProgressSubtasksQuantity++;
                        break;
                }
            }
        }
        if (this.allSubtasksQuantity == this.newSubtasksQuantity) {
            this.setStatus(TaskStatus.NEW);
        } else if (this.inProgressSubtasksQuantity > 0) {
            this.setStatus(TaskStatus.IN_PROGRESS);
        } else {
            this.setStatus(TaskStatus.DONE);
        }
    }
}
