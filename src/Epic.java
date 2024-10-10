import java.util.ArrayList;

public class Epic extends Task {
    int allSubtasksQuantity;
    int newSubtasksQuantity;
    int inProgressSubtasksQuantity;
    ArrayList<Integer> subtasksList = new ArrayList<>();

    @Override
    public String toString() {
        return "Epic{" +
                "allSubtasksQuantity=" + allSubtasksQuantity +
                ", newSubtasksQuantity=" + newSubtasksQuantity +
                ", inProgressSubtasksQuantity=" + inProgressSubtasksQuantity +
                ", subtasksList=" + subtasksList +
                '}';
    }

    public Epic(String name, String description) {
        super(name, description);
    }

    public void addSubtaskToList(int subtaskId) {
        subtasksList.add(subtaskId);
    }

    public void clearStats() {
        this.allSubtasksQuantity = 0;
        this.newSubtasksQuantity = 0;
        this.inProgressSubtasksQuantity = 0;
    }
}
