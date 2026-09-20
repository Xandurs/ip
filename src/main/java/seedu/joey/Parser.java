package seedu.joey;

import seedu.joey.task.Deadline;
import seedu.joey.task.Event;
import seedu.joey.task.Task;
import seedu.joey.task.Todo;
/**
 * Makes sense of the user's input by extrracting keywords, task indices,
 * and building Task objects from typed commands.
 */
public class Parser {
    /**
     * returns the first word of the command in lowercase
     */
    public static String getKeyword(String command) {
        return command.split(" ")[0].toLowerCase();
    }

    /**
     * Parses the 1-based task number from the command retunrs it as a 0-based
     * 
     * @param command the full command line (e.g. "mark 2").
     * @param listSize the current size of the task list for bounds checking.
     */
    public static int parseTaskIndex(String command, int listSize) throws JoeyException {
        String[] words = command.split(" ");
        if(words.length < 2) {
            throw new JoeyException("Sorry, task number is missing. Please try again");
        }
        int index;
        try {
            index = Integer.parseInt(words[1]) - 1;
        } catch (NumberFormatException e) {
            throw new JoeyException(words[1] + " is not a number. Please try again. ");
        }
        if (index < 0 || index >= listSize) {
            throw new JoeyException("Sorry. There is no task with that number. Please try again.");
        }
        return index;
    }

    /**
     * Builds a Todo, Deadline or event from the commad 
     */
    public static Task parseTask(String command) throws JoeyException {
        String[] parts = command.split(" ", 2);
        String type = parts[0].toLowerCase();

        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new JoeyException("Hmm, that " + type + " is missing a description. Please try again. ");
        }
        String details = parts[1].trim();

        if (type.equals("todo")) {
            return new Todo(details);
        } else if (type.equals("deadline")) {
            int byIndex = details.indexOf("/by");
            if (byIndex == -1) {
                throw new JoeyException("Sorry, you need a /by input. Please try again. ");
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
                throw new JoeyException("Sorry, you need a /from input. PLease try again. ");
            }
            if (toIndex == -1) {
                throw new JoeyException("Sorry, you need a /to input. Please try again. ");
            }
            if (fromIndex > toIndex) {
                throw new JoeyException("/from must come before /to. PLease try again. ");
            }
            String description = details.substring(0, fromIndex).trim();
            String from = details.substring(fromIndex + "/from".length(), toIndex).trim();
            String to = details.substring(toIndex + "/to".length()).trim();
            if (description.isEmpty()) {
                throw new JoeyException("Sorry, your description is missing. Please try again. ");
            }
            if (from.isEmpty()) {
                throw new JoeyException("Sorry, your start time is missing. Please try again. ");
            }
            if(to.isEmpty()) {
                throw new JoeyException("Sorry, your end time is missing. Please try again. ");
            }
            return new Event(description, from, to);
        }
         

    }

}

