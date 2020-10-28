package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TASK;
import static seedu.address.testutil.TypicalPersons.ALICE;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.project.task.AssignCommand;
import seedu.address.logic.parser.task.AssignCommandParser;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the AssignCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the AssignCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class AssignCommandParserTest {

    private AssignCommandParser parser = new AssignCommandParser();

    @Test
    public void parse_validArgs_returnsAssignCommand() {
        assertParseSuccess(parser, "1 Allie32",
                new AssignCommand(INDEX_FIRST_TASK, ALICE.getGitUserNameString()));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT, AssignCommand.MESSAGE_USAGE));
    }
}
