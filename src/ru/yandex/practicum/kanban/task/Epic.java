package ru.yandex.practicum.kanban.task;

import java.util.ArrayList;
import java.util.HashMap;

public class Epic extends Task {
    private int allSubtasksQuantity;
    private int newSubtasksQuantity;
    private int inProgressSubtasksQuantity;
    private final ArrayList<Integer> subtasksList;

    @Override
    public String toString() {
        return super.toString() + "with additional parameters {\nallSubtasksQuantity=" + allSubtasksQuantity +
                ", \nnewSubtasksQuantity=" + newSubtasksQuantity +
                ", \ninProgressSubtasksQuantity=" + inProgressSubtasksQuantity +
                ", \nsubtasksList=" + subtasksList +
                "}\n";
    }

    public Epic(int id, String name, String description, TaskStatus taskStatus) {
        super(id, name, description, taskStatus);
        this.subtasksList = new ArrayList<>();
    }

    public void addSubtaskToList(int subtaskId) {
        subtasksList.add(subtaskId);
    }

    public ArrayList<Integer> getSubtasksList() {
        return subtasksList;
    }

    public void removeSubtaskFromList(int subtaskId) {
        subtasksList.remove((Integer) subtaskId);
    }

    private void clearStats() {
        this.allSubtasksQuantity = 0;
        this.newSubtasksQuantity = 0;
        this.inProgressSubtasksQuantity = 0;
    }

    public void calculateEpicStatus(HashMap<Integer, Subtask> subtasksMap) {
        this.clearStats();
        for (Subtask subtaskId : subtasksMap.values()) {
            if (subtaskId.getParentEpicId() == this.getId()) {
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
