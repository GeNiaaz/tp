package seedu.address.logic.parser.teammate;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.ArgumentMultimapUtil.arePrefixesPresent;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TEAMMATE_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TEAMMATE_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TEAMMATE_GIT_USERNAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TEAMMATE_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TEAMMATE_PHONE;

import seedu.address.logic.commands.project.teammate.NewTeammateCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.ParsePersonUtil;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.GitUserName;
import seedu.address.model.person.Person;
import seedu.address.model.person.PersonName;
import seedu.address.model.person.Phone;

/**
 * Parses input arguments and creates a new AssignCommand object
 */
public class NewTeammateCommandParser implements Parser<NewTeammateCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the NewTeammateCommand
     * and returns a NewTeammateCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public NewTeammateCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
            ArgumentTokenizer.tokenize(args, PREFIX_TEAMMATE_NAME, PREFIX_TEAMMATE_GIT_USERNAME,
                PREFIX_TEAMMATE_PHONE, PREFIX_TEAMMATE_EMAIL, PREFIX_TEAMMATE_ADDRESS);

        if (!arePrefixesPresent(argMultimap, PREFIX_TEAMMATE_NAME, PREFIX_TEAMMATE_GIT_USERNAME,
            PREFIX_TEAMMATE_PHONE, PREFIX_TEAMMATE_EMAIL, PREFIX_TEAMMATE_ADDRESS)
            || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, NewTeammateCommand
                .MESSAGE_USAGE));
        }

        PersonName name = ParsePersonUtil.parsePersonName(argMultimap
            .getValue(PREFIX_TEAMMATE_NAME).get());

        GitUserName gitUserName =
            ParsePersonUtil.parseGitUserName(argMultimap
                .getValue(PREFIX_TEAMMATE_GIT_USERNAME).get());

        Phone phone = ParsePersonUtil.parsePhone(argMultimap
            .getValue(PREFIX_TEAMMATE_PHONE).get());

        Email email = ParsePersonUtil.parseEmail(argMultimap
            .getValue(PREFIX_TEAMMATE_EMAIL).get());

        Address address = ParsePersonUtil.parseAddress(argMultimap
            .getValue(PREFIX_TEAMMATE_ADDRESS).get());

        Person teammate = new Person(name, gitUserName, phone, email, address);
        return new NewTeammateCommand(teammate);
    }

}
