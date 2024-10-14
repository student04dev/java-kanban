package ru.yandex.practicum.kanban.core;

import ru.yandex.practicum.kanban.task.*;

import java.util.ArrayList;

public interface HistoryManager {
    public void add(Task task);

    public ArrayList<Task> getHistory();
}
