package seedu.address.logic.commands.project;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PROJECT;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TASK;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_TASK;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalProjects.getTypicalMainCatalogue;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.project.task.ViewTaskCommand;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.project.Project;
import seedu.address.model.task.Task;

/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand) and unit tests for
 * {@code DeleteCommand}.
 */
public class ViewTaskCommandTest {
    @Test
    public void execute_invalidIndex_throwsCommandException() {
        Model model = new ModelManager(getTypicalMainCatalogue(), new UserPrefs());
        Project project = model.getFilteredProjectList().get(INDEX_FIRST_PROJECT.getZeroBased());
        model.enter(project);
        Index outOfBoundIndex = Index.fromOneBased(project.getFilteredTaskList().size() + 1);
        ViewTaskCommand viewTaskCommand = new ViewTaskCommand(outOfBoundIndex);

        assertCommandFailure(viewTaskCommand, model, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Model model = new ModelManager(getTypicalMainCatalogue(), new UserPrefs());
        Project project = model.getFilteredProjectList().get(INDEX_FIRST_PROJECT.getZeroBased());
        model.enter(project);
        Task taskToView = project.getFilteredTaskList().get(INDEX_FIRST_TASK.getZeroBased());

        ViewTaskCommand viewTaskCommand = new ViewTaskCommand(INDEX_FIRST_TASK);

        String expectedMessage = String.format(ViewTaskCommand.MESSAGE_VIEW_TASK_SUCCESS, taskToView);
        ModelManager expectedModel = new ModelManager(model.getProjectCatalogue(), new UserPrefs());

        expectedModel.enter(project);
        expectedModel.enterTask(taskToView);

        assertCommandSuccess(viewTaskCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        Model model = new ModelManager(getTypicalMainCatalogue(), new UserPrefs());
        Project project = model.getFilteredProjectList().get(INDEX_FIRST_PROJECT.getZeroBased());
        model.enter(project);
        project.updateTaskFilter(x -> true);
        Task taskToView = project.getFilteredTaskList().get(INDEX_SECOND_TASK.getZeroBased());
        project.updateTaskFilter(task -> task.getTaskName().contains(taskToView.getTaskName()));

        ViewTaskCommand viewTaskCommand = new ViewTaskCommand(INDEX_FIRST_TASK);

        ModelManager expectedModel = new ModelManager(model.getProjectCatalogue(), new UserPrefs());
        String expectedMessage = String.format(ViewTaskCommand.MESSAGE_VIEW_TASK_SUCCESS, taskToView);

        expectedModel.enter(project);
        expectedModel.enterTask(taskToView);

        assertCommandSuccess(viewTaskCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        String name = ALICE.getPersonName().fullPersonName;
        ViewTaskCommand viewTaskFirstCommand = new ViewTaskCommand(INDEX_FIRST_TASK);
        ViewTaskCommand viewTaskSecondCommand = new ViewTaskCommand(INDEX_SECOND_TASK);

        // same object -> returns true
        assertTrue(viewTaskFirstCommand.equals(viewTaskFirstCommand));

        // same values -> returns true
        ViewTaskCommand viewTaskFirstCommandCopy = new ViewTaskCommand(INDEX_FIRST_TASK);
        assertTrue(viewTaskFirstCommand.equals(viewTaskFirstCommandCopy));

        // different types -> returns false
        assertFalse(viewTaskFirstCommand.equals(1));

        // null -> returns false
        assertFalse(viewTaskFirstCommand.equals(null));

        // different task -> returns false
        assertFalse(viewTaskFirstCommand.equals(viewTaskSecondCommand));
    }
}
