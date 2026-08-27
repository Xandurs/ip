import java.util.Scanner;
public class Joey {
    public static void main(String[] args) {
        String banner = "     _  ___  _______   __\n"
                + "    | |/ _ \\| ____\\ \\ / /\n"
                + " _  | | | | |  _|  \\ V / \n"
                + "| |_| | |_| | |___  | |  \n"
                + " \\___/ \\___/|_____| |_|  \n";

        String line = "----------------------------------------";
        Scanner in = new Scanner(System.in); //build a scanner object that scans and read from system.in (keyboard)
        String[] tasks = new String[100];
        int count = 0;
        boolean[] isDone = new boolean[100];
        
        System.out.println(line);
        System.out.println(banner);
        System.out.println("Hello! I'm Joey");
        System.out.println("What can I do for you?");
        System.out.println(line);

        while(true){
            String command = in.nextLine().trim();
            String[] words = command.split(" ");
            if(command.equalsIgnoreCase("bye")){
                System.out.println(line);
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(line);
                break;

            }else if (command.equalsIgnoreCase("list")){
                System.out.println(line);
                System.out.println("To Do List:");
                for(int i = 0; i < count; i++){
                    String icon = isDone[i] ? "X" : " ";
                    System.out.println(" " + (i + 1) + ".[" + icon + "] "  + tasks[i]);
                }
                System.out.println(line);

            }else if (words[0].equalsIgnoreCase("mark")){
                int index = Integer.parseInt(words[1]) - 1;
                isDone[index] = true;
                System.out.println(line);
                System.out.println("Tasks marked as done:");
                System.out.println(" [X] " + tasks[index]);
                System.out.println(line);

            }else if(words[0].equalsIgnoreCase("unmark")){
                int index = Integer.parseInt(words[1]) - 1;
                isDone[index] = false;
                System.out.println(line);
                System.out.println("Tasks marked as undone:");
                System.out.println(" [] " + tasks[index]);
                System.out.println(line);



            }else{
                tasks[count] = command;
                count++;
                System.out.println(line);
                System.out.println("added: " + command);
                System.out.println(line);
            }
            
            

            
        }
        
    }
    
}
