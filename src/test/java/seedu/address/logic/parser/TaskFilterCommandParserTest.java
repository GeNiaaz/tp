package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.project.task.TaskFilterCommand;
import seedu.address.logic.parser.task.TaskFilterCommandParser;

class TaskFilterCommandParserTest {
    private static final String MESSAGE_INVALID_FORMAT =
        String.format(MESSAGE_INVALID_COMMAND_FORMAT, TaskFilterCommand.MESSAGE_USAGE);

    private TaskFilterCommandParser parser = new TaskFilterCommandParser();
    @Test
    public void parse_missingParts_failure() {
        // empty user input
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

}
