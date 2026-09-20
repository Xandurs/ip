package seedu.joey;

import java.util.Scanner;
import seedu.joey.task.Task;
/**
 * Handles all interaction with the user: reading input and printing output
 */
public class Ui {
    private static final String LINE = "----------------------------------------";
    private static final String BANNER = "     _  ___  _______   __\n"
            + "    | |/ _ \\| ____\\ \\ / /\n"
            + " _  | | | | |  _|  \\ V / \n"
            + "| |_| | |_| | |___  | |  \n"
            + " \\___/ \\___/|_____| |_|  \n";
    
    private final Scanner in;

    public Ui() {
        this.in = new Scanner(System.in);
    }

    public String readCommand() {
        return in.nextLine().trim();
    }

    public void showLine(){
        System.out.println(LINE);
    }

    public void showWelcome() {
        System.out.println(LINE);
        System.out.println(BANNER);
        System.out.println("Hello! I'm Joey");
        System.out.println("What can I do for you?");
        System.out.println(LINE);
    }

    public void showGoodbye() {
        System.out.println(LINE);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(LINE);
    }

    public void showError(String message) {
        System.out.println(LINE);
        System.out.println(message);
        System.out.println(LINE);
    }

    public void showTaskList(TaskList tasks){
        System.out.println(LINE);
        System.out.println("To Do List:");
        for(int i = 0; i < tasks.size(); i++) {
            System.out.println(" " + (i + 1) + " . " + tasks.get(i));
        }
        System.out.println(LINE);
    }

    public void showMarked(Task task) {
        System.out.println(LINE);
        System.out.println("Tasks marked as done:");
        System.out.println(" " + task);
        System.out.println(LINE);
    }

    public void showUnmarked(Task task){
        System.out.println(LINE);
        System.out.println("Tasks marked as undone:");
        System.out.println(" " + task);
        System.out.println(LINE);
    }

    public void showAdded(Task task, int total) {
        System.out.println(LINE);
        System.out.println("Got it. I've added this task:");
        System.out.println(" " + task);
        System.out.println("now you have " + total + " tasks in the list.");
        System.out.println(LINE);
    }

    public void showRemoved(Task task, int total) {
        System.out.println(LINE);
        System.out.println("Okay, I've removed this task");
        System.out.println("Now you have " + total + " tasks in the list");
        System.out.println(LINE);
    }
}
