import java.util.ArrayList;
import java.util.HashMap;

public class TaskManager {
    private static int id = 1;
    HashMap<Integer, Task> tasksList = new HashMap<>();
    HashMap<Integer, Epic> epicsList = new HashMap<>();
    HashMap<Integer, Subtask> subTasksList = new HashMap<>();

    public TaskManager() {
    }

    public ArrayList<Task> getTasksList() {
        return new ArrayList<>(tasksList.values());
    }

    public ArrayList<Epic> getEpicsList() {
        return new ArrayList<>(epicsList.values());
    }

    public ArrayList<Subtask> getSubTasksList() {
        return new ArrayList<>(subTasksList.values());
    }

    public static int getAndIncreaseId() {
        return id++;
    }

    public Task getTaskById(int id) {
        return tasksList.get(id);
    }

    public Epic getEpicById(int id) {
        return epicsList.get(id);
    }

    public Subtask getSubTaskById(int id) {
        return subTasksList.get(id);
    }

    public ArrayList<Subtask> getSubtasksByEpicId(int id) {
        ArrayList<Subtask> subtasksByEpicId = new ArrayList<>();
        for (Subtask subtask : subTasksList.values()) {
            if (subtask.getParentEpicId() == id) {
                subtasksByEpicId.add(subtask);
            }
        }
        return subtasksByEpicId;
    }

    public void clearTasksList() {
        tasksList.clear();
    }

    public void clearEpicsList() {
        epicsList.clear();
    }

    public void clearSubtasksList() {
        subTasksList.clear();
    }

    public void deleteTaskById(int id) {
        tasksList.remove(id);
    }

    public void deleteEpicById(int id) {
        epicsList.remove(id);
    }

    public void deleteSubtaskById(int id) {
        subTasksList.remove(id);
    }

    public void createTask(Task inputTask) {
        tasksList.put(getAndIncreaseId(), inputTask);
    }

    public void createEpic(Epic inputTask) {
        epicsList.put(getAndIncreaseId(), inputTask);
    }

    public void createSubtask(Subtask inputTask) {
        int parentEpicId = inputTask.getParentEpicId();
        int subtaskId = getAndIncreaseId();
        subTasksList.put(subtaskId, inputTask);
        epicsList.get(parentEpicId).addSubtaskToList(subtaskId);
        calculateEpicStatus(inputTask.getParentEpicId());
    }

    public void updateTaskById(int id, Task inputTask) {
        tasksList.put(id, inputTask);
    }

    public void updateEpicById(int id, Epic inputTask) {
        epicsList.put(id, inputTask);
    }

    public void updateSubtaskById(int id, Subtask inputTask) {
        subTasksList.put(id, inputTask);
        calculateEpicStatus(inputTask.getParentEpicId());
    }

    public void calculateEpicStatus(int epicId) {
        Epic epic = epicsList.get(epicId);
        epic.clearStats();
        for (Subtask subtaskId : subTasksList.values()) {
            if (subtaskId.getParentEpicId() == epicId) {
                epic.allSubtasksQuantity++;
                switch (subtaskId.getStatus()) {
                    case TaskStatus.NEW:
                        epic.newSubtasksQuantity++;
                        break;
                    case TaskStatus.IN_PROGRESS:
                        epic.inProgressSubtasksQuantity++;
                        break;
                }
            }
        }
        if (epic.allSubtasksQuantity == epic.newSubtasksQuantity) {
            epic.setStatus(TaskStatus.NEW);
        } else if (epic.inProgressSubtasksQuantity > 0) {
            epic.setStatus(TaskStatus.IN_PROGRESS);
        } else {
            epic.setStatus(TaskStatus.DONE);
        }
    }
}
