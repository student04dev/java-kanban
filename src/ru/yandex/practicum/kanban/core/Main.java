package ru.yandex.practicum.kanban.core;

import ru.yandex.practicum.kanban.task.*;

public class Main {

    public static void main(String[] args) {
        System.out.println("Поехали!");
        TaskManager taskManager = new TaskManager();

        taskManager.createTask(new Task("Task 1 name", "task 1 text", TaskStatus.NEW));
        taskManager.createTask(new Task("Task 2 name", "task 2 text", TaskStatus.NEW));
        taskManager.createEpic(new Epic("Epic 1 name", "epic 1 text", TaskStatus.NEW));
        taskManager.createEpic(new Epic("Epic 2 name", "epic 2 text", TaskStatus.NEW));
        taskManager.createSubtask(new Subtask("Subtask 1 name", "subtask 1 text",
                3, TaskStatus.NEW));
        taskManager.createSubtask(new Subtask("Subtask 2 name", "subtask 2 text",
                4, TaskStatus.NEW));
        taskManager.createSubtask(new Subtask("Subtask 3 name", "subtask 3 text",
                4, TaskStatus.NEW));

        System.out.println(taskManager.tasksMap);
        System.out.println(taskManager.epicsMap);
        System.out.println(taskManager.subtasksMap);

        taskManager.updateSubtaskById(6, new Subtask("Subtask 2 name", "subtask 2 text",
                4, TaskStatus.IN_PROGRESS));
        System.out.println("\nEpic task id=4 status after subtask status change:");
        System.out.println(taskManager.epicsMap.get(4));

        taskManager.deleteSubtaskById(6);
        System.out.println("\nEpic task id=4 status after subtask delete:");
        System.out.println(taskManager.epicsMap.get(4));

    }
}
