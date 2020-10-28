package seedu.address.logic.commands.project.task;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.project.Project;
import seedu.address.model.task.Task;

/**
 * Requests to view the details of an existing task in the project.
 */
public class ViewTaskCommand extends Command {

    public static final String COMMAND_WORD = "viewtask";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Views the details of the task identified "
            + "by the index number used in the displayed task list. ";

    public static final String MESSAGE_VIEW_TASK_SUCCESS = "Started TASK: %1$s";

    private final Index index;

    /**
     * @param index of the task in the filtered task list to edit
     *
     */
    public ViewTaskCommand(Index index) {
        requireNonNull(index);
        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Project project = model.getProjectToBeDisplayedOnDashboard().get();
        List<Task> lastShownList = project.getFilteredTaskList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }

        Task task = lastShownList.get(index.getZeroBased());
        model.enterTask(task);

        return new CommandResult(String.format(MESSAGE_VIEW_TASK_SUCCESS, task));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ViewTaskCommand)) {
            return false;
        }

        // state check
        ViewTaskCommand e = (ViewTaskCommand) other;
        return index.equals(e.index);
    }
}
