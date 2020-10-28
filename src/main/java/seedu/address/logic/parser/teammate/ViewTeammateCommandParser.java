package seedu.address.logic.parser.teammate;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.project.teammate.ViewTeammateCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
/**
 * Parses input arguments and creates a new ViewCommand object
 */
public class ViewTeammateCommandParser implements Parser<ViewTeammateCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ViewCommand
     * and returns an ViewCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public ViewTeammateCommand parse(String args) throws ParseException {
        requireNonNull(args);

        Index index;

        try {
            index = ParserUtil.parseIndex(args.trim());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    ViewTeammateCommand.MESSAGE_USAGE), pe);
        }

        return new ViewTeammateCommand(index);
    }
}
