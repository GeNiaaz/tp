package seedu.address.commons.core;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_PROJECT_DISPLAYED_INDEX = "The project index provided is     invalid";
    public static final String MESSAGE_INVALID_TEAMMATE_DISPLAYED_NAME = "The name provided for the teammate is not " +
        "found in the project";
    public static final String MESSAGE_INVALID_TASK_DISPLAYED_INDEX = "The task index provided is invalid";
    public static final String MESSAGE_PROJECTS_LISTED_OVERVIEW = "%1$d projects listed!";
    public static final String MESSAGE_INVALID_SCOPE_COMMAND = "The scope status is expected to be %s but you are"
            + " now in %s";
    public static final String MESSAGE_MEMBER_NOT_PRESENT = "%s is not a team member of this project.";
    public static final String MESSAGE_REASSIGNMENT_OF_SAME_TASK_TO_SAME_PERSON =
            "%s is already an assignee of the task.";
}
