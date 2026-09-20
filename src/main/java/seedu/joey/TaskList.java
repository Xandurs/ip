package seedu.joey;


import java.util.ArrayList;
import seedu.joey.task.Task;
/**
 * Holds the list of tasks and provides operations to add, delete and acces them
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> initialTasks) {
        this.tasks = initialTasks;
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public Task delete(int index) {
        return tasks.remove(index);
    }

    public Task get(int index) {
        return tasks.get(index);
    }

    public int size() {
        return tasks.size();
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Returns a new TaskList containing only tasks whose description
     * contains the given keyword (case-insensitive substring match).
     */
    public TaskList find(String keyword) {
        ArrayList<Task> matches = new ArrayList<>();
        String needle = keyword.toLowerCase();
        for (Task t : tasks) {
            if (t.getDescription().toLowerCase().contains(needle)) {
                matches.add(t);
            }
        }
        return new TaskList(matches);
    }

}
