package seedu.joey;

/**
 * represents an error caused by invalid user input to Joey.
 * Throws this whenever the user enters something the chatbot
 * understands is wrong(e.g an empty todo description) so the 
 * main loop can catch it, shows a message and continue running
 */
public class JoeyException extends Exception {
    public JoeyException(String message) {
        super(message);
    }
}
