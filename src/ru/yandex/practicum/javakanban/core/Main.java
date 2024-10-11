package ru.yandex.practicum.javakanban.core;

import ru.yandex.practicum.javakanban.task.*;

public class Main {

    public static void main(String[] args) {
        System.out.println("Поехали!");
        TaskManager taskManager = new TaskManager();

        taskManager.createTask(new Task("Task_1_name", "task_1_text", TaskStatus.NEW));
        taskManager.createTask(new Task("Task_2_name", "task_2_text", TaskStatus.NEW));
        taskManager.createEpic(new Epic("Epic_1_name", "epic_1_text", TaskStatus.NEW));
        taskManager.createEpic(new Epic("Epic_2_name", "epic_2_text", TaskStatus.NEW));
        taskManager.createSubtask(new Subtask("Subtask_1_name", "subtask_1_text",
                3, TaskStatus.NEW));
        taskManager.createSubtask(new Subtask("Subtask_2_name", "subtask_2_text",
                4, TaskStatus.NEW));
        taskManager.createSubtask(new Subtask("Subtask_3_name", "subtask_3_text",
                4, TaskStatus.NEW));

        System.out.println(taskManager.tasksMap);
        System.out.println(taskManager.epicsMap);
        System.out.println(taskManager.subtasksMap);

        taskManager.updateSubtaskById(6, new Subtask("Subtask_2_name", "subtask_2_text",
                4, TaskStatus.IN_PROGRESS));
        System.out.println("\nEpic task_id=4 status after subtask status change:");
        System.out.println(taskManager.epicsMap.get(4));

        taskManager.deleteSubtaskById(6);
        System.out.println("\nEpic task_id=4 status after subtask delete:");
        System.out.println(taskManager.epicsMap.get(4));

    }
}
