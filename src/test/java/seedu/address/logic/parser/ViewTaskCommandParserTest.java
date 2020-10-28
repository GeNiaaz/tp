package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TASK;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.project.task.ViewTaskCommand;
import seedu.address.logic.parser.task.ViewTaskCommandParser;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the ViewTaskCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the ViewTaskCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class ViewTaskCommandParserTest {

    private ViewTaskCommandParser parser = new ViewTaskCommandParser();

    @Test
    public void parse_validArgs_returnsViewTaskCommand() {
        assertParseSuccess(parser, "1",
                new ViewTaskCommand(INDEX_FIRST_TASK));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewTaskCommand.MESSAGE_USAGE));
    }
}
