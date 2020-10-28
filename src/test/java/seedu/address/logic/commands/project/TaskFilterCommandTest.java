package seedu.address.logic.commands.project;

import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.project.task.TaskFilterCommand;


class TaskFilterCommandTest {

    @Test
    void execute_nullPredicate_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TaskFilterCommand(null));
    }

    //TODO: will add more when meeting filter is implemented
}
