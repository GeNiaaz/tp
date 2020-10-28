package seedu.address.logic.commands.project.teammate;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.GitUserIndex;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.project.Participation;
import seedu.address.model.project.Project;

/**
 * Deletes a project identified using it's displayed index from the main catalogue.
 */
public class DeleteTeammateCommand extends Command {

    public static final String COMMAND_WORD = "deleteteammate";

    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": Deletes the teammate identified by the Git Username in the current project.\n"
        + "Parameters: Git UserName (must be a single word)\n"
        + "Example: " + COMMAND_WORD + " LucasTai98";

    public static final String MESSAGE_DELETE_TEAMMATE_SUCCESS = "Deleted Teammate: %1$s";

    private final GitUserIndex gitUserIndex;

    public DeleteTeammateCommand(GitUserIndex gitUserIndex) {
        this.gitUserIndex = gitUserIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Project project = model.getProjectToBeDisplayedOnDashboard().get();
        List<Participation> lastShownList = project.getTeammates();

        if (!project.hasParticipation(gitUserIndex.getGitUserName())) {
            throw new CommandException(Messages.MESSAGE_INVALID_TEAMMATE_DISPLAYED_NAME);
        }

        Participation participation = project.getParticipation(gitUserIndex.getGitUserName());
        Person personToDelete = Person.getPersonFromList(gitUserIndex);

        project.removeParticipation(participation);
        model.deleteParticipation(participation);
        model.deletePerson(personToDelete);

        return new CommandResult(String.format(MESSAGE_DELETE_TEAMMATE_SUCCESS, personToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof DeleteTeammateCommand // instanceof handles nulls
            && gitUserIndex.equals(((DeleteTeammateCommand) other).gitUserIndex)); // state check
    }
}
