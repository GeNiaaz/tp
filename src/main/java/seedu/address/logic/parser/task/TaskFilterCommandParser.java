package seedu.address.logic.parser.task;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.ArgumentMultimapUtil.isOnlyOnePrefixPresent;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_ASSIGNEE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_IS_DONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_PROGRESS;

import java.util.function.Predicate;

import seedu.address.logic.commands.project.task.TaskFilterCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.ParsePersonUtil;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.GitUserName;
import seedu.address.model.project.Deadline;
import seedu.address.model.task.Task;

/**
 * Parses input {@code String} and creates a TaskFilterCommand object.
 */
public class TaskFilterCommandParser implements Parser<TaskFilterCommand> {

    /**
     * Parses the given input {@code String}.
     * @param args  the user input
     * @return      the filter command whose predicate corresponds to the user input
     * @throws ParseException   if the user input does not follow the format
     */
    @Override
    public TaskFilterCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
            ArgumentTokenizer.tokenize(args, PREFIX_TASK_ASSIGNEE,
                PREFIX_TASK_DEADLINE, PREFIX_TASK_NAME, PREFIX_TASK_PROGRESS, PREFIX_TASK_IS_DONE);

        if (!isOnlyOnePrefixPresent(argMultimap, PREFIX_TASK_ASSIGNEE,
            PREFIX_TASK_DEADLINE, PREFIX_TASK_NAME, PREFIX_TASK_PROGRESS, PREFIX_TASK_IS_DONE)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, TaskFilterCommand.MESSAGE_USAGE));
        }

        Predicate<Task> predicate = task -> true;

        if (argMultimap.getValue(PREFIX_TASK_ASSIGNEE).isPresent()) {
            GitUserName assigneeGitUserName = ParsePersonUtil.parseGitUserName(argMultimap
                .getValue(PREFIX_TASK_ASSIGNEE).get());
            predicate = task -> task.hasAssigneeWhoseGitNameIs(assigneeGitUserName);
        }
        if (argMultimap.getValue(PREFIX_TASK_DEADLINE).isPresent()) {
            Deadline deadline = ParserUtil.parseDeadline(argMultimap.getValue(PREFIX_TASK_DEADLINE).get());
            predicate = task -> task.isDueOn(deadline);
        }
        if (argMultimap.getValue(PREFIX_TASK_NAME).isPresent()) {
            predicate = task -> task.getTaskName()
                .contains(argMultimap.getValue(PREFIX_TASK_NAME).get());
        }
        if (argMultimap.getValue(PREFIX_TASK_PROGRESS).isPresent()) {
            double progress = Double.parseDouble(
                ParserUtil.parseTaskBasicInformation(argMultimap.getValue(PREFIX_TASK_PROGRESS).get()));
            predicate = task -> task.getProgress() == progress;
        }
        if (argMultimap.getValue(PREFIX_TASK_IS_DONE).isPresent()) {
            boolean isDone = Boolean.parseBoolean(
                ParserUtil.parseTaskBasicInformation(argMultimap.getValue(PREFIX_TASK_IS_DONE).get()));
            predicate = task -> task.isDone() == isDone;
        }

        return new TaskFilterCommand(predicate);
    }
}
