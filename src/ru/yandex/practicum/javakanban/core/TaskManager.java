package ru.yandex.practicum.javakanban.core;

import ru.yandex.practicum.javakanban.task.*;

import java.util.ArrayList;
import java.util.HashMap;

public class TaskManager {
    private static int id = 1;
    HashMap<Integer, Task> tasksMap = new HashMap<>();
    HashMap<Integer, Epic> epicsMap = new HashMap<>();
    HashMap<Integer, Subtask> subtasksMap = new HashMap<>();

    public TaskManager() {
    }

    public ArrayList<Task> getTasksMap() {
        return new ArrayList<>(tasksMap.values());
    }

    public ArrayList<Epic> getEpicsMap() {
        return new ArrayList<>(epicsMap.values());
    }

    public ArrayList<Subtask> getSubtasksMap() {
        return new ArrayList<>(subtasksMap.values());
    }

    public static int getAndIncreaseId() {
        return id++;
    }

    public Task getTaskById(int id) {
        return tasksMap.get(id);
    }

    public Epic getEpicById(int id) {
        return epicsMap.get(id);
    }

    public Subtask getSubTaskById(int id) {
        return subtasksMap.get(id);
    }

    public ArrayList<Subtask> getSubtasksByEpicId(int id) {
        ArrayList<Subtask> subtasksByEpicId = new ArrayList<>();
        for (Subtask subtask : subtasksMap.values()) {
            if (subtask.getParentEpicId() == id) {
                subtasksByEpicId.add(subtask);
            }
        }
        return subtasksByEpicId;
    }

    public void clearTasksList() {
        tasksMap.clear();
    }

    public void clearEpicsList() {
        epicsMap.clear();
    }

    public void clearSubtasksList() {
        subtasksMap.clear();
    }

    public void deleteTaskById(int id) {
        tasksMap.remove(id);
    }

    public void deleteEpicById(int id) {
        epicsMap.remove(id);
    }

    public void deleteSubtaskById(int id) {
        int parentEpicId = subtasksMap.get(id).getParentEpicId();
        subtasksMap.remove(id);
        epicsMap.get(parentEpicId).calculateEpicStatus(parentEpicId, subtasksMap);
    }

    public void createTask(Task inputTask) {
        tasksMap.put(getAndIncreaseId(), inputTask);
    }

    public void createEpic(Epic inputTask) {
        epicsMap.put(getAndIncreaseId(), inputTask);
    }

    public void createSubtask(Subtask inputTask) {
        int parentEpicId = inputTask.getParentEpicId();
        int subtaskId = getAndIncreaseId();
        subtasksMap.put(subtaskId, inputTask);
        epicsMap.get(parentEpicId).addSubtaskToList(subtaskId);
        epicsMap.get(parentEpicId).calculateEpicStatus(inputTask.getParentEpicId(), subtasksMap);
    }

    public void updateTaskById(int id, Task inputTask) {
        tasksMap.put(id, inputTask);
    }

    public void updateEpicById(int id, Epic inputTask) {
        epicsMap.put(id, inputTask);
    }

    public void updateSubtaskById(int id, Subtask inputTask) {
        subtasksMap.put(id, inputTask);
        int parentEpicId = inputTask.getParentEpicId();
        epicsMap.get(parentEpicId).calculateEpicStatus(inputTask.getParentEpicId(), subtasksMap);
    }
}
