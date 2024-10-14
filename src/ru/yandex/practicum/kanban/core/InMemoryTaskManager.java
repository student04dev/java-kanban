package ru.yandex.practicum.kanban.core;

import ru.yandex.practicum.kanban.task.*;

import java.util.ArrayList;
import java.util.HashMap;

public class InMemoryTaskManager implements TaskManager {
    private int nextId = 1;
    private final HashMap<Integer, Task> tasksMap = new HashMap<>();
    private final HashMap<Integer, Epic> epicsMap = new HashMap<>();
    private final HashMap<Integer, Subtask> subtasksMap = new HashMap<>();
    private final String ERROR_INCORRECT_NEW_TASK_ID = "Для добавления новой записи используйте taskId=0.";
    HistoryManager historyManager = Managers.getDefaultHistory();

    public InMemoryTaskManager() {
    }

    int getAndIncreaseId() {
        return nextId++;
    }

    @Override
    public void createTask(Task inputTask) {
        if (inputTask.getId() == 0) {
            int taskId = getAndIncreaseId();
            inputTask.setId(taskId);
            tasksMap.put(taskId, inputTask);
        } else {
            System.out.println(ERROR_INCORRECT_NEW_TASK_ID);
        }
    }

    @Override
    public void createEpic(Epic inputEpic) {
        if (inputEpic.getId() == 0) {
            int taskId = getAndIncreaseId();
            inputEpic.setId(taskId);
            epicsMap.put(taskId, inputEpic);
        } else {
            System.out.println(ERROR_INCORRECT_NEW_TASK_ID);
        }
    }

    @Override
    public void createSubtask(Subtask inputSubtask) {
        if (inputSubtask.getId() == 0) {
            int subtaskId = getAndIncreaseId();
            inputSubtask.setId(subtaskId);
            subtasksMap.put(subtaskId, inputSubtask);
            int parentEpicId = inputSubtask.getParentEpicId();
            epicsMap.get(parentEpicId).addSubtaskToList(subtaskId);
            epicsMap.get(parentEpicId).calculateEpicStatus(subtasksMap);
        } else {
            System.out.println(ERROR_INCORRECT_NEW_TASK_ID);
        }
    }

    @Override
    public Task getTaskById(int id) {
        if (isIdBelongsToTasks(id)) {
            historyManager.add(tasksMap.get(id));
            return tasksMap.get(id);
        }
        return null;
    }

    @Override
    public Epic getEpicById(int id) {
        if (isIdBelongsToEpics(id)) {
            historyManager.add(epicsMap.get(id));
            return epicsMap.get(id);
        }
        return null;
    }

    @Override
    public Subtask getSubtaskById(int id) {
        if (isIdBelongsToSubtasks(id)) {
            historyManager.add(subtasksMap.get(id));
            return subtasksMap.get(id);
        }
        return null;
    }

    @Override
    public ArrayList<Subtask> getAllSubtasksByEpicId(int epicId) {
        ArrayList<Subtask> subtasksByEpicId = new ArrayList<>();
        for (int subtaskId : epicsMap.get(epicId).getSubtasksList()) {
            subtasksByEpicId.add(subtasksMap.get(subtaskId));
        }
        return subtasksByEpicId;
    }

    @Override
    public ArrayList<Task> getTasksMap() {
        return new ArrayList<>(tasksMap.values());
    }

    @Override
    public ArrayList<Epic> getEpicsMap() {
        return new ArrayList<>(epicsMap.values());
    }

    @Override
    public ArrayList<Subtask> getSubtasksMap() {
        return new ArrayList<>(subtasksMap.values());
    }

    @Override
    public void clearTasksMap() {
        tasksMap.clear();
    }

    @Override
    public void clearEpicsMap() {
        epicsMap.clear();
    }

    @Override
    public void clearSubtasksMap() {
        subtasksMap.clear();
    }

    @Override
    public void removeTaskById(int taskId) {
        tasksMap.remove(taskId);
    }

    @Override
    public void removeEpicById(int epicId) {
        epicsMap.remove(epicId);
    }

    @Override
    public void removeSubtaskById(int subtaskId) {
        int parentEpicId = subtasksMap.get(subtaskId).getParentEpicId();
        subtasksMap.remove(subtaskId);
        epicsMap.get(parentEpicId).removeSubtaskFromList(subtaskId);
        epicsMap.get(parentEpicId).calculateEpicStatus(subtasksMap);
    }

    @Override
    public void updateTask(Task inputTask) {
        int taskId = inputTask.getId();
        if (isIdBelongsToTasks(taskId)) {
            tasksMap.put(taskId, inputTask);
        }
    }

    @Override
    public void updateEpic(Epic inputEpic) {
        int epicId = inputEpic.getId();
        if (isIdBelongsToEpics(epicId)) {
            epicsMap.put(epicId, inputEpic);
        }
    }

    @Override
    public void updateSubtask(Subtask inputSubtask) {
        int subtaskId = inputSubtask.getId();
        if (isIdBelongsToSubtasks(subtaskId)) {
            subtasksMap.put(subtaskId, inputSubtask);
            int parentEpicId = inputSubtask.getParentEpicId();
            epicsMap.get(parentEpicId).calculateEpicStatus(subtasksMap);
        }
    }

    @Override
    public ArrayList<Task> getHistory() {
        return historyManager.getHistory();
    }

    @Override
    public boolean isIdBelongsToTasks(int id) {
        return tasksMap.containsKey(id);
    }

    @Override
    public boolean isIdBelongsToEpics(int id) {
        return epicsMap.containsKey(id);
    }

    @Override
    public boolean isIdBelongsToSubtasks(int id) {
        return subtasksMap.containsKey(id);
    }

    public boolean isFreeId(int taskId) {
        return !(isIdBelongsToTasks(taskId) && isIdBelongsToEpics(taskId) && isIdBelongsToSubtasks(taskId));
    }

}
