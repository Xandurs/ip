/**
 * represents an error caused by invalid user input to Joey.
 * Throws this whenver the user enters somehitng the chatbot
 * understands is wring(e.g an empty todo description) so the 
 * main loop can catch it, shows a message and continue running
 */
public class JoeyException extends Exception {
    public JoeyException(String message) {
        super(message);
    }
}
