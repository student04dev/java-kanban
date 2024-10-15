package ru.yandex.practicum.kanban.task;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.kanban.core.Managers;
import ru.yandex.practicum.kanban.core.TaskManager;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class EpicTest {
    TaskManager taskManager;

    @BeforeEach
    void beforeEach() {
        taskManager = Managers.getDefault();
    }

    @Test
    void ensureEpicSubtasksListContainsAllSubtasks() {
        taskManager.createEpic(new Epic(0, "Epic 1 name", "epic 1 text", TaskStatus.NEW));
        taskManager.createSubtask(new Subtask(0, "Subtask 1 name", "subtask 1 text",
                1, TaskStatus.NEW));
        taskManager.createSubtask(new Subtask(0, "Subtask 2 name", "subtask 2 text",
                1, TaskStatus.NEW));
        assertEquals(taskManager.getEpicById(1).getSubtasksList(), Arrays.asList(2, 3));
    }

    @Test
    void ensureEpicGetsInProgressWhenSubtaskGetsInProgress() {
        taskManager.createEpic(new Epic(0, "Epic 1 name", "epic 1 text", TaskStatus.NEW));
        taskManager.createSubtask(new Subtask(0, "Subtask 1 name", "subtask 1 text",
                1, TaskStatus.NEW));
        taskManager.updateSubtask(new Subtask(2, "Subtask 1 name", "subtask 1 text",
                1, TaskStatus.IN_PROGRESS));
        assertEquals(taskManager.getEpicById(1).getStatus(), TaskStatus.IN_PROGRESS);
    }

    @Test
    void ensureEpicGetsNewWhenRemoveLastSubtaskInProgress() {
        taskManager.createEpic(new Epic(0, "Epic 1 name", "epic 1 text", TaskStatus.NEW));
        taskManager.createSubtask(new Subtask(0, "Subtask 1 name", "subtask 1 text",
                1, TaskStatus.IN_PROGRESS));
        taskManager.removeSubtaskById(2);
        assertEquals(taskManager.getEpicById(1).getStatus(), TaskStatus.NEW);
    }

}