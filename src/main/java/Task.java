/**
 * Represents a task in the task list.
 * A task has a descriptopn and a status indicating whether it is done.
 */




public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description){
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the icon representing the completion status of this task.
     * 
     * @return "X" if the task is done, a blank space otherwise.
     */
    public String getStatusIcon(){
        return (isDone ? "X" : " ");
    }

    public String getDescription(){
        return description;
    }

    /**
     * Marks this task as done.
     */
    public void markAsDone(){
        this.isDone = true;
    }

    /**
     * Marks this task as not done yet
     */
    public void markAsNotDone(){
        this.isDone = false;
    }

}
