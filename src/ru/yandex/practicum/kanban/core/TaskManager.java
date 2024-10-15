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

    void updateTask(Task inputTask);

    void updateEpic(Epic inputTask);

    void updateSubtask(Subtask inputTask);

    ArrayList<Task> getHistory();

}
