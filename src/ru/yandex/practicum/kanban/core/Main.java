package ru.yandex.practicum.kanban.core;

import ru.yandex.practicum.kanban.task.*;

public class Main {

    public static void main(String[] args) {
        System.out.println("Поехали!");
        TaskManager taskManager = Managers.getDefault();

        taskManager.createTask(new Task(0, "Task 1 name", "task 1 text", TaskStatus.NEW)); // id=1
        taskManager.createTask(new Task(0, "Task 2 name", "task 2 text", TaskStatus.NEW)); // id=2
        taskManager.createEpic(new Epic(0, "Epic 1 name", "epic 1 text", TaskStatus.NEW)); // id=3
        taskManager.createEpic(new Epic(0, "Epic 2 name", "epic 2 text", TaskStatus.NEW)); // id=4
        taskManager.createSubtask(new Subtask(0, "Subtask 1 name", "subtask 1 text",
                3, TaskStatus.NEW)); // id=5
        taskManager.createSubtask(new Subtask(0, "Subtask 2 name", "subtask 2 text",
                4, TaskStatus.NEW)); // id=6
        taskManager.createSubtask(new Subtask(0, "Subtask 3 name", "subtask 3 text",
                4, TaskStatus.NEW)); // id=7

        taskManager.updateSubtask(new Subtask(6, "Subtask 2 name", "subtask 2 text",
                4, TaskStatus.IN_PROGRESS));
        System.out.println("\nСтатус эпика id=4 должен быть IN_PROGRESS после перевода подзадачи в IN_PROGRESS:");
        System.out.println(taskManager.getEpicById(4));

        taskManager.removeSubtaskById(6);
        System.out.println("\nСтатус эпика id=4 должен быть NEW " +
                "после удаления последней оставшейся подзадачи в IN_PROGRESS:");
        System.out.println(taskManager.getEpicById(4));

        taskManager.getTaskById(1);
        taskManager.getEpicById(3);
        taskManager.getSubtaskById(7);

        printAllTasks(taskManager);

    }

    private static void printAllTasks(TaskManager taskManager) {
        System.out.println("Задачи:");
        for (Task task : taskManager.getTasksMap()) {
            System.out.println(task);
        }
        System.out.println("Эпики:");
        for (Task epic : taskManager.getEpicsMap()) {
            System.out.println(epic);

            for (Subtask subtask : taskManager.getSubtasksMap()) {
                if (subtask.getParentEpicId() == epic.getId())
                    System.out.println("--> " + subtask);
            }
        }
        System.out.println("Подзадачи:");
        for (Task subtask : taskManager.getSubtasksMap()) {
            System.out.println(subtask);
        }

        System.out.println("История:");
        for (Task task : taskManager.getHistory()) {
            System.out.println(task);
        }
    }
}
