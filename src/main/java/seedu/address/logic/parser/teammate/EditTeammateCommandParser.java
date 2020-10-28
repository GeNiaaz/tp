package seedu.address.logic.parser.teammate;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TEAMMATE_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TEAMMATE_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TEAMMATE_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TEAMMATE_PHONE;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.GitUserIndex;
import seedu.address.logic.commands.global.EditCommand;
import seedu.address.logic.commands.project.teammate.EditTeammateCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.ParsePersonUtil;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.task.Task;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditTeammateCommandParser implements Parser<EditTeammateCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditTeammateCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
            ArgumentTokenizer.tokenize(args, PREFIX_TEAMMATE_NAME, PREFIX_TEAMMATE_PHONE, PREFIX_TEAMMATE_EMAIL,
                PREFIX_TEAMMATE_ADDRESS);

        GitUserIndex gitUserIndex;

        try {
            gitUserIndex = ParsePersonUtil.parseGitUserIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditTeammateCommand.MESSAGE_USAGE),
                pe);
        }

        EditTeammateCommand.EditTeammateDescriptor editTeammateDescriptor =
            new EditTeammateCommand.EditTeammateDescriptor();
        if (argMultimap.getValue(PREFIX_TEAMMATE_NAME).isPresent()) {
            editTeammateDescriptor.setTeammateName(ParsePersonUtil.parsePersonName(
                argMultimap.getValue(PREFIX_TEAMMATE_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_TEAMMATE_PHONE).isPresent()) {
            editTeammateDescriptor.setPhone(ParsePersonUtil.parsePhone(argMultimap.getValue(PREFIX_TEAMMATE_PHONE)
                .get()));
        }
        if (argMultimap.getValue(PREFIX_TEAMMATE_EMAIL).isPresent()) {
            editTeammateDescriptor.setEmail(ParsePersonUtil.parseEmail(argMultimap
                .getValue(PREFIX_TEAMMATE_EMAIL).get()));
        }
        if (argMultimap.getValue(PREFIX_TEAMMATE_ADDRESS).isPresent()) {
            editTeammateDescriptor.setAddress(ParsePersonUtil.parseAddress(argMultimap
                .getValue(PREFIX_TEAMMATE_ADDRESS).get()));
        }
        if (!editTeammateDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }

        return new EditTeammateCommand(gitUserIndex, editTeammateDescriptor);
    }

    /**
     * Parses {@code Collection<String> tasks} into a {@code Set<Task>} if {@code tasks} is non-empty.
     * If {@code tasks} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Task>} containing zero tasks.
     */
    private Optional<Set<Task>> parseTeammatesForEdit(Collection<String> teammates) {
        assert teammates != null;

        if (teammates.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> teammateSet = teammates.size() == 1 && teammates.contains("") ? Collections.emptySet()
            : teammates;
        return Optional.of(ParserUtil.parseTasks(teammates));
    }
}
