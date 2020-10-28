package seedu.address.logic.commands.project;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PROJECT;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TASK;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalProjects.getTypicalMainCatalogue;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.project.task.AssignCommand;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.project.Project;

public class AddCommandTest {

    @Test
    public void execute_validIndexInvalidPerson_throwsCommandException() {
        Model model = new ModelManager(getTypicalMainCatalogue(), new UserPrefs());
        Project project = model.getFilteredProjectList().get(INDEX_FIRST_PROJECT.getZeroBased());
        model.enter(project);
        project.deleteParticipation(ALICE.getGitUserNameString());

        AssignCommand assignCommand = new AssignCommand(INDEX_FIRST_TASK, ALICE.getGitUserNameString());
        assertCommandFailure(assignCommand, model,
            String.format(Messages.MESSAGE_MEMBER_NOT_PRESENT, ALICE.getGitUserName()));
    }
}
