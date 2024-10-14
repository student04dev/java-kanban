package ru.yandex.practicum.kanban.task;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.kanban.task.Epic;
import ru.yandex.practicum.kanban.task.Subtask;
import ru.yandex.practicum.kanban.task.Task;
import ru.yandex.practicum.kanban.task.TaskStatus;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {
    @Test
    void TasksWithSameIdShouldBeEqual() {
        Task task = new Task(1, "Name", "Description", TaskStatus.NEW);
        assertEquals(task, new Task(1, "abc", "def", TaskStatus.IN_PROGRESS));
    }

    @Test
    void TasksWithNotSameIdShouldNotBeEqual() {
        Task task = new Task(1, "Name", "Description", TaskStatus.NEW);
        assertNotEquals(task, new Task(2, "Name", "Description", TaskStatus.NEW));
    }

    @Test
    void EpicsWithSameIdShouldBeEqual() {
        Epic epic = new Epic(1, "Name", "Description", TaskStatus.NEW);
        assertEquals(epic, new Epic(1, "abc", "def", TaskStatus.IN_PROGRESS));
    }

    @Test
    void EpicsWithNotSameIdShouldNotBeEqual() {
        Epic epic = new Epic(1, "Name", "Description", TaskStatus.NEW);
        assertNotEquals(epic, new Epic(2, "Name", "Description", TaskStatus.NEW));
    }

    @Test
    void SubtasksWithSameIdShouldBeEqual() {
        Subtask subtask = new Subtask(1, "Name", "Description", 1, TaskStatus.NEW);
        assertEquals(subtask, new Subtask(1, "abc", "def", 2, TaskStatus.IN_PROGRESS));
    }

    @Test
    void SubtasksWithNotSameIdShouldNotBeEqual() {
        Subtask subtask = new Subtask(1, "Name", "Description", 1, TaskStatus.NEW);
        assertNotEquals(subtask, new Subtask(2, "Name", "Description", 1, TaskStatus.NEW));
    }

}
