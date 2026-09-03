import java.util.Scanner;

public class Joey {
    private static final int MAX_TASKS = 100;

    public static void main(String[] args) {
        String banner = "     _  ___  _______   __\n"
                + "    | |/ _ \\| ____\\ \\ / /\n"
                + " _  | | | | |  _|  \\ V / \n"
                + "| |_| | |_| | |___  | |  \n"
                + " \\___/ \\___/|_____| |_|  \n";
        String line = "----------------------------------------";

        // build a scanner object that scans and read from system.in (keyboard)
        Scanner in = new Scanner(System.in);
        Task[] tasks = new Task[MAX_TASKS];
        int count = 0;

        System.out.println(line);
        System.out.println(banner);
        System.out.println("Hello! I'm Joey");
        System.out.println("What can I do for you?");
        System.out.println(line);

        while (true) {
            String command = in.nextLine().trim();
            String[] words = command.split(" ");

            if (command.equalsIgnoreCase("bye")) {
                System.out.println(line);
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(line);
                break;

            } else if (command.equalsIgnoreCase("list")) {
                System.out.println(line);
                System.out.println("To Do List:");
                for (int i = 0; i < count; i++) {
                    System.out.println(" " + (i + 1) + "." + tasks[i]);
                }
                System.out.println(line);

            } else if (words[0].equalsIgnoreCase("mark")) {
                int index = Integer.parseInt(words[1]) - 1;
                tasks[index].markAsDone();
                System.out.println(line);
                System.out.println("Tasks marked as done:");
                System.out.println("  " + tasks[index]);
                System.out.println(line);

            } else if (words[0].equalsIgnoreCase("unmark")) {
                int index = Integer.parseInt(words[1]) - 1;
                tasks[index].markAsNotDone();
                System.out.println(line);
                System.out.println("Tasks marked as undone:");
                System.out.println("  " + tasks[index]);
                System.out.println(line);

            } else if (words[0].equalsIgnoreCase("todo")
                    || words[0].equalsIgnoreCase("deadline")
                    || words[0].equalsIgnoreCase("event")) {
                tasks[count] = createTask(command);
                count++;
                System.out.println(line);
                System.out.println("Got it. I've added this task:");
                System.out.println("  " + tasks[count - 1]);
                System.out.println("Now you have " + count + " tasks in the list.");
                System.out.println(line);

            } else {
                tasks[count] = new Task(command);
                count++;
                System.out.println(line);
                System.out.println("added: " + command);
                System.out.println(line);
            }
        }
    }

    /**
     * Creates a task of the type specified by the first word of the command.
     *
     * @param command The full command entered by the user.
     * @return A Todo, Deadline or Event built from the given command.
     */
    private static Task createTask(String command) {
        String[] parts = command.split(" ", 2);
        String type = parts[0].toLowerCase();
        String details = parts[1].trim();

        if (type.equals("todo")) {
            return new Todo(details);
        } else if (type.equals("deadline")) {
            int byIndex = details.indexOf("/by");
            String description = details.substring(0, byIndex).trim();
            String by = details.substring(byIndex + "/by".length()).trim();
            return new Deadline(description, by);
        } else {
            int fromIndex = details.indexOf("/from");
            int toIndex = details.indexOf("/to");
            String description = details.substring(0, fromIndex).trim();
            String from = details.substring(fromIndex + "/from".length(), toIndex).trim();
            String to = details.substring(toIndex + "/to".length()).trim();
            return new Event(description, from, to);
        }
    }
}