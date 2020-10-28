package seedu.address.logic.parser.teammate;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.GitUserIndex;
import seedu.address.logic.commands.project.teammate.DeleteTeammateCommand;
import seedu.address.logic.parser.ParsePersonUtil;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteTeammateCommand object
 */
public class DeleteTeammateCommandParser implements Parser<DeleteTeammateCommand> {

    /**
     * Parses {@code userInput} into a command and returns it.
     * @param args input after "deleteteammate" command from user
     * @return instance of DeleteTeammateCommandParser
     * @throws ParseException if {@code userInput} does not conform the expected format
     */
    public DeleteTeammateCommand parse(String args) throws ParseException {
        try {
            GitUserIndex gitUserIndex = ParsePersonUtil.parseGitUserIndex(args);
            return new DeleteTeammateCommand(gitUserIndex);
        } catch (ParseException e) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteTeammateCommand.MESSAGE_USAGE), e);
        }
    }
}
