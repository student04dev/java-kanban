package ru.yandex.practicum.kanban.core;

import ru.yandex.practicum.kanban.task.*;

import java.util.ArrayList;

public interface TaskManager {

    void createTask(Task inputTask);

    void createEpic(Epic inputTask);

    void createSubtask(Subtask inputTask);

    Task getTaskById(int id);

    Epic getEpicById(int id);

    Subtask getSubtaskById(int id);

    ArrayList<Subtask> getAllSubtasksByEpicId(int id);

    ArrayList<Task> getTasksMap();

    ArrayList<Epic> getEpicsMap();

    ArrayList<Subtask> getSubtasksMap();

    void clearTasksMap();

    void clearEpicsMap();

    void clearSubtasksMap();

    void removeTaskById(int id);

    void removeEpicById(int id);

    void removeSubtaskById(int id);

    void updateTaskById(int id, Task inputTask);

    void updateEpicById(int id, Epic inputTask);

    void updateSubtaskById(int id, Subtask inputTask);

    ArrayList<Task> getHistory();

    boolean isIdBelongsToTasks(int id);

    boolean isIdBelongsToEpics(int id);

    boolean isIdBelongsToSubtasks(int id);

    boolean isFreeId(int taskId);
}
