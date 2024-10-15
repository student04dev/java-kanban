package ru.yandex.practicum.kanban.task;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.kanban.task.Epic;
import ru.yandex.practicum.kanban.task.Subtask;
import ru.yandex.practicum.kanban.task.Task;
import ru.yandex.practicum.kanban.task.TaskStatus;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {
    @Test
    void tasksWithSameIdShouldBeEqual() {
        Task task = new Task(1, "Name", "Description", TaskStatus.NEW);
        assertEquals(task, new Task(1, "abc", "def", TaskStatus.IN_PROGRESS));
    }

    @Test
    void tasksWithNotSameIdShouldNotBeEqual() {
        Task task = new Task(1, "Name", "Description", TaskStatus.NEW);
        assertNotEquals(task, new Task(2, "Name", "Description", TaskStatus.NEW));
    }

    @Test
    void epicsWithSameIdShouldBeEqual() {
        Epic epic = new Epic(1, "Name", "Description", TaskStatus.NEW);
        assertEquals(epic, new Epic(1, "abc", "def", TaskStatus.IN_PROGRESS));
    }

    @Test
    void epicsWithNotSameIdShouldNotBeEqual() {
        Epic epic = new Epic(1, "Name", "Description", TaskStatus.NEW);
        assertNotEquals(epic, new Epic(2, "Name", "Description", TaskStatus.NEW));
    }

    @Test
    void subtasksWithSameIdShouldBeEqual() {
        Subtask subtask = new Subtask(1, "Name", "Description", 1, TaskStatus.NEW);
        assertEquals(subtask, new Subtask(1, "abc", "def", 2, TaskStatus.IN_PROGRESS));
    }

    @Test
    void subtasksWithNotSameIdShouldNotBeEqual() {
        Subtask subtask = new Subtask(1, "Name", "Description", 1, TaskStatus.NEW);
        assertNotEquals(subtask, new Subtask(2, "Name", "Description", 1, TaskStatus.NEW));
    }

}
