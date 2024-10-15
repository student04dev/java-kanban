package ru.yandex.practicum.kanban.core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.kanban.task.Epic;
import ru.yandex.practicum.kanban.task.Subtask;
import ru.yandex.practicum.kanban.task.Task;
import ru.yandex.practicum.kanban.task.TaskStatus;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryTaskManagerTest {
    TaskManager taskManager;

    @BeforeEach
    void beforeEach() {
        taskManager = Managers.getDefault();
    }

    @Test
    void ensureAutoincrementSetsIdsOneByOne() {
        taskManager.createTask(new Task(0, "Task 1 name", "task 1 text", TaskStatus.NEW));
        taskManager.createTask(new Task(0, "Task 2 name", "task 2 text", TaskStatus.NEW));
        assertEquals(taskManager.getTaskById(1).getId(), taskManager.getTaskById(2).getId() - 1);
    }

    @Test
    void taskCreatedShouldBeAccessibleById() {
        taskManager.createTask(new Task(0, "Task 1 name", "task 1 text", TaskStatus.NEW));
        assertNotNull(taskManager.getTaskById(1));
    }

    @Test
    void epicCreatedShouldBeAccessibleById() {
        taskManager.createEpic(new Epic(0, "Epic 1 name", "epic 1 text", TaskStatus.NEW));
        assertNotNull(taskManager.getEpicById(1));
    }

    @Test
    void subtaskCreatedShouldBeAccessibleById() {
        taskManager.createEpic(new Epic(0, "Epic 1 name", "epic 1 text", TaskStatus.NEW));
        taskManager.createSubtask(new Subtask(0, "Subtask 1 name", "subtask 1 text",
                1, TaskStatus.NEW));
        assertNotNull(taskManager.getSubtaskById(2));
    }

    @Test
    void taskCreatedShouldBeTheSameAsInitialOne() {
        taskManager.createTask(new Task(0, "Task 1 name", "task 1 text", TaskStatus.NEW));
        assertEquals("Task 1 name", taskManager.getTaskById(1).getName());
        assertEquals("task 1 text", taskManager.getTaskById(1).getDescription());
        assertEquals(TaskStatus.NEW, taskManager.getTaskById(1).getStatus());
    }

    @Test
    void epicCreatedShouldBeTheSameAsInitialOne() {
        taskManager.createEpic(new Epic(0, "Epic 1 name", "epic 1 text", TaskStatus.NEW));
        assertEquals("Epic 1 name", taskManager.getEpicById(1).getName());
        assertEquals("epic 1 text", taskManager.getEpicById(1).getDescription());
        assertEquals(TaskStatus.NEW, taskManager.getEpicById(1).getStatus());
    }

    @Test
    void subtaskCreatedShouldBeTheSameAsInitialOne() {
        taskManager.createEpic(new Epic(0, "Epic 1 name", "epic 1 text", TaskStatus.NEW));
        taskManager.createSubtask(new Subtask(0, "Subtask 1 name", "subtask 1 text",
                1, TaskStatus.NEW));
        assertEquals("Subtask 1 name", taskManager.getSubtaskById(2).getName());
        assertEquals("subtask 1 text", taskManager.getSubtaskById(2).getDescription());
        assertEquals(TaskStatus.NEW, taskManager.getSubtaskById(2).getStatus());
    }
}
