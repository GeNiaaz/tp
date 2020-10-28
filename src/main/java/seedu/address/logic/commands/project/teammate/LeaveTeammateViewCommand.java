package seedu.address.logic.commands.project.teammate;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;

/**
 * Leaves the task view and go back to the project view.
 */
public class LeaveTeammateViewCommand extends Command {

    public static final String COMMAND_WORD = "leaveTeammateView";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Leaves the teammate detail view and go back to the project detail view.\n";

    public static final String MESSAGE_LEAVE_SUCCESS = "Returned to the project page";

    public LeaveTeammateViewCommand() {

    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.quit();
        return new CommandResult(MESSAGE_LEAVE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || other instanceof LeaveTeammateViewCommand; // instanceof handles nulls
    }
}
