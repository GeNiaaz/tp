package seedu.address.logic.parser.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PROJECT_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PROJECT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_IS_DONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_PROGRESS;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.global.EditCommand;
import seedu.address.logic.commands.project.task.EditTaskCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.task.Task;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditTaskCommandParser implements Parser<EditTaskCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditTaskCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_PROJECT_NAME, PREFIX_TASK_DEADLINE, PREFIX_PROJECT_DESCRIPTION,
                        PREFIX_TASK_PROGRESS, PREFIX_TASK_IS_DONE);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditTaskCommand.MESSAGE_USAGE), pe);
        }

        EditTaskCommand.EditTaskDescriptor editTaskDescriptor = new EditTaskCommand.EditTaskDescriptor();
        if (argMultimap.getValue(PREFIX_PROJECT_NAME).isPresent()) {
            editTaskDescriptor.setTaskName(ParserUtil.parseTaskBasicInformation(
                    argMultimap.getValue(PREFIX_PROJECT_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_TASK_DEADLINE).isPresent()) {
            editTaskDescriptor.setDeadline(ParserUtil.parseDeadline(argMultimap.getValue(PREFIX_TASK_DEADLINE).get()));
        }
        if (argMultimap.getValue(PREFIX_PROJECT_DESCRIPTION).isPresent()) {
            editTaskDescriptor.setTaskDescription(ParserUtil.parseTaskBasicInformation(argMultimap
                    .getValue(PREFIX_PROJECT_DESCRIPTION).get()));
        }
        if (argMultimap.getValue(PREFIX_TASK_PROGRESS).isPresent()) {
            editTaskDescriptor.setProgress(Double.parseDouble(ParserUtil.parseTaskBasicInformation(argMultimap
                    .getValue(PREFIX_TASK_PROGRESS).get())));
        }
        if (argMultimap.getValue(PREFIX_TASK_IS_DONE).isPresent()) {
            editTaskDescriptor.setIsDone(Boolean.parseBoolean(ParserUtil.parseTaskBasicInformation(argMultimap
                    .getValue(PREFIX_TASK_IS_DONE).get())));
        }
        if (!editTaskDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }

        return new EditTaskCommand(index, editTaskDescriptor);
    }

    /**
     * Parses {@code Collection<String> tasks} into a {@code Set<Task>} if {@code tasks} is non-empty.
     * If {@code tasks} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Task>} containing zero tasks.
     */
    private Optional<Set<Task>> parseTasksForEdit(Collection<String> tasks) {
        assert tasks != null;

        if (tasks.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> taskSet = tasks.size() == 1 && tasks.contains("") ? Collections.emptySet() : tasks;
        return Optional.of(ParserUtil.parseTasks(taskSet));
    }
}
