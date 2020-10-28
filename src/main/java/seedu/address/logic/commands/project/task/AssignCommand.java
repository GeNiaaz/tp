package seedu.address.logic.commands.project.task;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.project.Participation;
import seedu.address.model.project.Project;
import seedu.address.model.task.Task;

/**
 * Assigns a task to a person with participation in the current project.
 */
public class AssignCommand extends Command {

    public static final String COMMAND_WORD = "assign";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Assigns the task identified by the index number used in the displayed task list to the team member"
            + "that participates in the current project with his/her name.\n"
            + "Parameters: INDEX (must be a positive integer), NAME (must be present in the project)\n"
            + "Example: " + COMMAND_WORD + " 1 Lucas";

    public static final String MESSAGE_ASSIGN_TASK_SUCCESS = "Assigns task: %1$s to %s";

    private final Index targetIndex;
    private final String assignee;

    /**
     * Creates an AssignCommand that assigns the task of the given index to the intended assignee.
     */
    public AssignCommand(Index targetIndex, String assignee) {
        this.targetIndex = targetIndex;
        this.assignee = assignee;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Project project = model.getProjectToBeDisplayedOnDashboard().get();
        List<Task> lastShownTaskList = project.getFilteredTaskList();

        if (targetIndex.getZeroBased() >= lastShownTaskList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }

        Task taskToAssociate = lastShownTaskList.get(targetIndex.getZeroBased());

        if (!project.hasParticipation(assignee)) {
            throw new CommandException(String.format(Messages.MESSAGE_MEMBER_NOT_PRESENT, assignee));
        }

        Participation assignee = project.getParticipation(this.assignee);

        if (assignee.hasTask(taskToAssociate) || taskToAssociate.hasAssignee(assignee)) {
            throw new CommandException(String.format(Messages.MESSAGE_REASSIGNMENT_OF_SAME_TASK_TO_SAME_PERSON,
                    assignee.getPerson().getGitUserName()));
        }
        model.deleteParticipation(assignee);

        assignee.addTask(taskToAssociate);
        model.addParticipation(assignee);
        taskToAssociate.addAssignee(assignee.getAssigneeName().toString());

        return new CommandResult(String.format(MESSAGE_ASSIGN_TASK_SUCCESS, taskToAssociate, assignee));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AssignCommand // instanceof handles nulls
                && targetIndex.equals(((AssignCommand) other).targetIndex)
                && assignee.equals(((AssignCommand) ((AssignCommand) other)).assignee)); // state check
    }
}
