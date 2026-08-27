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
                    System.out.println(" " + (i + 1) + ".[" + tasks[i].getStatusIcon() + "] "
                            + tasks[i].getDescription());
                }
                System.out.println(line);

            } else if (words[0].equalsIgnoreCase("mark")) {
                int index = Integer.parseInt(words[1]) - 1;
                tasks[index].markAsDone();
                System.out.println(line);
                System.out.println("Tasks marked as done:");
                System.out.println("  [" + tasks[index].getStatusIcon() + "] " + tasks[index].getDescription());
                System.out.println(line);

            } else if (words[0].equalsIgnoreCase("unmark")) {
                int index = Integer.parseInt(words[1]) - 1;
                tasks[index].markAsNotDone();
                System.out.println(line);
                System.out.println("Tasks marked as undone:");
                System.out.println("  [" + tasks[index].getStatusIcon() + "] " + tasks[index].getDescription());
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
    
}
