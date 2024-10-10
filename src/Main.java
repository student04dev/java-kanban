public class Main {

    public static void main(String[] args) {
        System.out.println("Поехали!");
        TaskManager taskManager = new TaskManager();

        taskManager.createTask(new Task("First", "first"));
        taskManager.createTask(new Task("Second", "second"));
        taskManager.createEpic(new Epic("First", "first"));
        taskManager.createEpic(new Epic("Second", "second"));
        taskManager.createSubtask(new Subtask("First", "first", 3));
        taskManager.createSubtask(new Subtask("Second","second", 4));
        taskManager.createSubtask(new Subtask("Third","third", 4));

        System.out.println(taskManager.tasksList);
        System.out.println(taskManager.epicsList);
        System.out.println(taskManager.subTasksList);
    }
}
