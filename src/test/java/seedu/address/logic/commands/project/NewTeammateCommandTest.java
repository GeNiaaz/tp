package seedu.address.logic.commands.project;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalPersons.DESC_A;
import static seedu.address.testutil.TypicalPersons.DESC_B;
import static seedu.address.testutil.TypicalPersons.DESC_C;
import static seedu.address.testutil.TypicalProjects.AI;
import static seedu.address.testutil.TypicalProjects.getTypicalMainCatalogue;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.project.teammate.NewTeammateCommand;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains tests regarding NewTeammateCommand
 */
public class NewTeammateCommandTest {
    @Test
    public void execute_invalidPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> {
            new NewTeammateCommand(null);
        });
    }

    @Test
    public void execute_invalidModel_throwsNullPointerException() {
        NewTeammateCommand newTeammateCommand = new NewTeammateCommand(DESC_A);
        Model model = null;
        assertThrows(NullPointerException.class, () -> {
            newTeammateCommand.execute(model);
        });
    }

    @Test
    public void execute_validModel() {
        NewTeammateCommand newTeammateCommand = new NewTeammateCommand(DESC_A);
        Model model = new ModelManager(getTypicalMainCatalogue(), new UserPrefs());
        model.updateProjectToBeDisplayedOnDashboard(AI);
        String expectedResult = String.format(NewTeammateCommand.MESSAGE_ASSIGN_TEAMMATE_SUCCESS,
            DESC_A.getGitUserNameString());

        CommandResult commandResult = newTeammateCommand.execute(model);

        assertEquals(expectedResult, commandResult.getFeedbackToUser());

    }

    @Test
    public void execute_equals() {

        NewTeammateCommand newTeammateCommand1 = new NewTeammateCommand(DESC_A);
        NewTeammateCommand newTeammateCommand2 = new NewTeammateCommand(DESC_B);
        NewTeammateCommand newTeammateCommand3 = new NewTeammateCommand(DESC_C);

        // same object -> returns true
        assertTrue(newTeammateCommand1.equals(newTeammateCommand1));

        // same values -> returns true
        NewTeammateCommand newTeammateCommand1copy = new NewTeammateCommand(DESC_A);
        assertTrue(newTeammateCommand1.equals(newTeammateCommand1copy));

        // different types -> returns false
        assertFalse(newTeammateCommand1.equals("this test will return false"));

        // null -> returns false
        assertFalse(newTeammateCommand1.equals(null));

        // different tasks -> returns false
        assertFalse(newTeammateCommand1.equals(newTeammateCommand2));
        assertFalse(newTeammateCommand2.equals(newTeammateCommand3));
    }




}
