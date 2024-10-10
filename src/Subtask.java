public class Subtask extends Task {
    private final int parentEpicId;

    @Override
    public String toString() {
        return "Subtask{" +
                "parentEpicId=" + parentEpicId +
                '}';
    }

    public Subtask(String name, String description, int parentEpicId) {
        super(name, description);
        this.parentEpicId = parentEpicId;
    }

    public int getParentEpicId() {
        return parentEpicId;
    }
}
