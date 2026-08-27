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
        
        System.out.println(line);
        System.out.println(banner);
        System.out.println("Hello! I'm Joey");
        System.out.println("What can I do for you?");
        System.out.println(line);

        while(true){
            String command = in.nextLine().trim();
            if(command.equalsIgnoreCase("bye")){
                System.out.println(line);
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(line);
                break;

            }
            System.out.println(line);
            System.out.println(" " + command);
            System.out.println(line);
        }
        
    }
    
}
