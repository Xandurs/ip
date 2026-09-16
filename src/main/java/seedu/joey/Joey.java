package seedu.joey;
import java.util.Scanner;
import seedu.joey.task.Task;
import seedu.joey.task.Todo;
import seedu.joey.task.Deadline;
import seedu.joey.task.Event;
import java.util.ArrayList;


public class Joey {
    private static final String LINE = "----------------------------------------";
    private static final String BANNER = "     _  ___  _______   __\n"
            + "    | |/ _ \\| ____\\ \\ / /\n"
            + " _  | | | | |  _|  \\ V / \n"
            + "| |_| | |_| | |___  | |  \n"
            + " \\___/ \\___/|_____| |_|  \n";

    private static ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        tasks = Storage.load();
        printWelcome();

        while (true) {
            String command = in.nextLine().trim();
            if (command.equalsIgnoreCase("bye")) {
                printGoodbye();
                break;
            }
            handleCommand(command);
        }
    }

    /**
     * Dispatches a single user command to the matching handler.
     *
     * @param command The full command line entered by the user.
     */
    private static void handleCommand(String command) {
        String[] words = command.split(" ");
        String keyword = words[0].toLowerCase();

        try {
            switch (keyword) {
        case "list":
            printList();
            break;
        case "mark":
            markTask(parseTaskIndex(words));
            break;
        case "unmark":
            unmarkTask(parseTaskIndex(words));
            break;
        case "delete":
            deleteTask(parseTaskIndex(words));
            break;
        case "todo":
        case "deadline":
        case "event":
            addTypedTask(command);
            break;
        default:
            throw new JoeyException("Sorry, I don't recognise that command.");
            }
        } catch (JoeyException e) {
            System.out.println(LINE);
            System.out.println(e.getMessage());
            System.out.println(LINE);
        }
       
        
    }

    private static void printWelcome() {
        System.out.println(LINE);
        System.out.println(BANNER);
        System.out.println("Hello! I'm Joey");
        System.out.println("What can I do for you?");
        System.out.println(LINE);
    }

    private static void printGoodbye() {
        System.out.println(LINE);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(LINE);
    }

    private static void printList() {
        System.out.println(LINE);
        System.out.println("To Do List:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(" " + (i + 1) + "." + tasks.get(i));
        }
        System.out.println(LINE);
    }

    private static int parseTaskIndex(String[] words) throws JoeyException {
        if (words.length < 2 ) {
            throw new JoeyException("Sorry, task number is missing. Please try again");
        }
        int index;
        try {
            index = Integer.parseInt(words[1]) - 1;
        } catch (NumberFormatException e) {
            throw new JoeyException(words[1] + " is not a number. Please try again. ");
        }
        if (index < 0 || index >= tasks.size()) {
            throw new JoeyException("Sorry. There is no task with that number. Please try again.");
        }
        return index;
    }

    private static void markTask(int index) {
        tasks.get(index).markAsDone();
        System.out.println(LINE);
        System.out.println("Tasks marked as done:");
        System.out.println("  " + tasks.get(index));
        System.out.println(LINE);
    }

    private static void unmarkTask(int index) {
        tasks.get(index).markAsNotDone();
        System.out.println(LINE);
        System.out.println("Tasks marked as undone:");
        System.out.println("  " + tasks.get(index));
        System.out.println(LINE);
    }

    /**
     * Removes the task at the given index and reports what was removed
     * @param index Xero-based index of the task to delete 
     */
    private static void deleteTask(int index) {
        Task removed = tasks.remove(index);
        Storage.save(tasks);
        System.out.println(LINE);
        System.out.println("Okay. I've removed this task");
        System.out.println(" " + removed);
        System.out.println("Now you have " + tasks.size() + " tasks in the list");
        System.out.println(LINE);
    }

    /**
     * Adds a typed task (todo/deadline/event) parsed from the command,
     * then prints the standard confirmation block.
     */
    private static void addTypedTask(String command) throws JoeyException {
        Task task = createTask(command);
        tasks.add(task);
        Storage.save(tasks);
        
        System.out.println(LINE);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        System.out.println(LINE);
    }

    

    
    /**
     * Creates a task of the type specified by the first word of the command.
     *
     * @param command The full command entered by the user.
     * @return A Todo, Deadline or Event built from the given command.
     */
    private static Task createTask(String command) throws JoeyException {
        String[] parts = command.split(" ", 2);
        String type = parts[0].toLowerCase();

        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new JoeyException("Hmm, that " + type + " is missing a description. Please try again.");
        }
        String details = parts[1].trim();

        if (type.equals("todo")) {
            return new Todo(details);
        } else if (type.equals("deadline")) {
            int byIndex = details.indexOf("/by");
            if (byIndex == -1) {
                throw new JoeyException("Sorry, you need a /by input Please try again.");
            }
            String description = details.substring(0, byIndex).trim();
            String by = details.substring(byIndex + "/by".length()).trim();
            if (description.isEmpty()) {
                throw new JoeyException("Sorry, your description is missing. Please try again.");
            }
            if (by.isEmpty()) {
                throw new JoeyException("Sorry, when is your task due?");
            }
            return new Deadline(description, by);
        } else {
            int fromIndex = details.indexOf("/from");
            int toIndex = details.indexOf("/to");
            if (fromIndex == -1) {
                throw new JoeyException("Sorry, you need a /from input. Please try again.");
            }
            if (toIndex == -1) {
                throw new JoeyException("Sorry, you need a /to input. Please try again. ");
            }
            if (fromIndex > toIndex) {
                throw new JoeyException("/from must come before /to. Please try again.");
            }
            String description = details.substring(0, fromIndex).trim();
            String from = details.substring(fromIndex + "/from".length(), toIndex).trim();
            String to = details.substring(toIndex + "/to".length()).trim();
            if (description.isEmpty()) {
                throw new JoeyException("Sorry, your description is missing. Please try again.");
            }
            if (from.isEmpty()) {
                throw new JoeyException("Sorry, your start time is missing. Please try again.");
            }
            if (to.isEmpty()) {
                throw new JoeyException("Sorry, your end time is missing. Please try again.");
            }
            return new Event(description, from, to);
        }
    }
}