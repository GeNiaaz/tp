package seedu.address.logic.commands.project.task;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.project.Project;

/**
 * Shows all the tasks in the current project.
 */
public class AllTasksCommand extends Command {

    public static final String COMMAND_WORD = "allt";

    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": clear task filter and view all the tasks\n";

    public static final String MESSAGE_ALL_TASKS_SUCCESS = "These are all the tasks in this project";

    public AllTasksCommand() {

    }

    @Override
    public CommandResult execute(Model model) {
        assert(model != null);
        Project project = model.getProjectToBeDisplayedOnDashboard().get();
        project.showAllTasks();
        return new CommandResult(MESSAGE_ALL_TASKS_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || other instanceof AllTasksCommand; // instanceof handles nulls
    }
}
