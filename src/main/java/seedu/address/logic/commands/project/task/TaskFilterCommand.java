package seedu.address.logic.commands.project.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_ASSIGNEE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_IS_DONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_PROGRESS;

import java.util.function.Predicate;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.project.Project;
import seedu.address.model.task.Task;

/**
 * Filters tasks by assignee's name, task name or deadline.
 */
public class TaskFilterCommand extends Command {
    public static final String COMMAND_WORD = "filtert";

    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": Filter and show tasks with given predicate\n"
        + "Parameters: ("
        + PREFIX_TASK_ASSIGNEE + "ASSIGNEE NAME)||("
        + PREFIX_TASK_DEADLINE + "DEADLINE)||("
        + PREFIX_TASK_NAME + "TASK NAME)||("
        + PREFIX_TASK_PROGRESS + "DEADLINE)||("
        + PREFIX_TASK_IS_DONE + "DEADLINE)\n"
        + "Example: " + COMMAND_WORD + " ("
        + PREFIX_TASK_ASSIGNEE + "Alice)||("
        + PREFIX_TASK_DEADLINE + "31-12-2020 10:00:00)||("
        + PREFIX_TASK_NAME + "group meeting)||("
        + PREFIX_TASK_PROGRESS + "50)||("
        + PREFIX_TASK_IS_DONE + "false)\n";
    public static final String MESSAGE_FILTER_TASK_SUCCESS = "Here are the filtered tasks:";
    //TODO: CHOOSE ONE PREDICATE OR MULTIPLE???
    private final Predicate<Task> predicate;

    /**
     * Creates a filter command with the given predicate.
     * @param predicate the predicate used to filter tasks
     */
    public TaskFilterCommand(Predicate<Task> predicate) {
        requireNonNull(predicate);
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Project project = model.getProjectToBeDisplayedOnDashboard().get();
        project.updateTaskFilter(predicate);
        return new CommandResult(String.format(MESSAGE_FILTER_TASK_SUCCESS));
    }

}
