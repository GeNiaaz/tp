package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEETING;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PROJECT_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PROJECT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PROJECT_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REPOURL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK;

import java.util.HashMap;
import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.commands.global.AddCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.project.Deadline;
import seedu.address.model.project.Project;
import seedu.address.model.project.ProjectDescription;
import seedu.address.model.project.ProjectName;
import seedu.address.model.project.RepoUrl;
import seedu.address.model.tag.ProjectTag;
import seedu.address.model.task.Task;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_PROJECT_NAME, PREFIX_DEADLINE, PREFIX_REPOURL,
                        PREFIX_PROJECT_DESCRIPTION, PREFIX_PROJECT_TAG, PREFIX_TASK);

        if (!arePrefixesPresent(argMultimap, PREFIX_PROJECT_NAME,
                PREFIX_PROJECT_DESCRIPTION, PREFIX_DEADLINE, PREFIX_REPOURL)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        ProjectName projectName = ParserUtil.parseProjectName(argMultimap.getValue(PREFIX_PROJECT_NAME).get());
        Deadline deadline = ParserUtil.parseDeadline(argMultimap.getValue(PREFIX_DEADLINE).get());
        RepoUrl repoUrl = ParserUtil.parseRepoUrl(argMultimap.getValue(PREFIX_REPOURL).get());
        ProjectDescription projectDescription = ParserUtil.projectDescription(argMultimap.getValue(
                PREFIX_PROJECT_DESCRIPTION).get());
        Set<ProjectTag> projectTagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_PROJECT_TAG));
        Set<Task> taskList = ParserUtil.parseTasks(argMultimap.getAllValues(PREFIX_TASK));
        Set<Meeting> meetingList = ParserUtil.parseMeetings(argMultimap.getAllValues(PREFIX_MEETING));

        Project project = new Project(projectName, deadline, repoUrl, projectDescription, projectTagList,
                new HashMap<>(), taskList, meetingList);

        return new AddCommand(project);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
