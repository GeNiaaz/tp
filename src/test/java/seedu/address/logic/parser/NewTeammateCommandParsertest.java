package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.TeammateTestUtil.INVALID_TEAMMATE_EMAIL_DESC_A;
import static seedu.address.logic.commands.TeammateTestUtil.INVALID_TEAMMATE_GIT_USERNAME_DESC_A;
import static seedu.address.logic.commands.TeammateTestUtil.INVALID_TEAMMATE_NAME_DESC_A;
import static seedu.address.logic.commands.TeammateTestUtil.INVALID_TEAMMATE_PHONE_DESC_A;
import static seedu.address.logic.commands.TeammateTestUtil.TEAMMATE_ADDRESS_DESC_A;
import static seedu.address.logic.commands.TeammateTestUtil.TEAMMATE_EMAIL_DESC_A;
import static seedu.address.logic.commands.TeammateTestUtil.TEAMMATE_GIT_USERNAME_DESC_A;
import static seedu.address.logic.commands.TeammateTestUtil.TEAMMATE_NAME_DESC_A;
import static seedu.address.logic.commands.TeammateTestUtil.TEAMMATE_PHONE_DESC_A;
import static seedu.address.logic.commands.TeammateTestUtil.VALID_TEAMMATE_ADDRESS_A;
import static seedu.address.logic.commands.TeammateTestUtil.VALID_TEAMMATE_EMAIL_A;
import static seedu.address.logic.commands.TeammateTestUtil.VALID_TEAMMATE_GIT_USERNAME_A;
import static seedu.address.logic.commands.TeammateTestUtil.VALID_TEAMMATE_NAME_A;
import static seedu.address.logic.commands.TeammateTestUtil.VALID_TEAMMATE_PHONE_A;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalPersons.DESC_A;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.project.teammate.NewTeammateCommand;
import seedu.address.logic.parser.teammate.NewTeammateCommandParser;
import seedu.address.model.person.Email;
import seedu.address.model.person.GitUserName;
import seedu.address.model.person.Person;
import seedu.address.model.person.PersonName;
import seedu.address.model.person.Phone;

/**
 * Contains tests for NewTeammateCommandParser (interactions with NewTeammateCommand)
 */
public class NewTeammateCommandParsertest {
    private NewTeammateCommandParser parser = new NewTeammateCommandParser();

    /**
     * Parses all fields
     * Tests that all fields are required
     */
    @Test
    public void parse_allFieldsPresent_success() {
        Person expectedPerson = DESC_A;

        // all fields filled (all compulsory)
        assertParseSuccess(parser, TEAMMATE_NAME_DESC_A + TEAMMATE_GIT_USERNAME_DESC_A + TEAMMATE_PHONE_DESC_A
            + TEAMMATE_EMAIL_DESC_A + TEAMMATE_ADDRESS_DESC_A, new NewTeammateCommand(expectedPerson));
    }

    /**
     * Parses when different fields are missing
     * Checks that the parse fails when each field is missing
     */
    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, NewTeammateCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_TEAMMATE_NAME_A + TEAMMATE_GIT_USERNAME_DESC_A + TEAMMATE_PHONE_DESC_A
            + TEAMMATE_EMAIL_DESC_A + TEAMMATE_ADDRESS_DESC_A, expectedMessage);

        // missing git prefix
        assertParseFailure(parser, TEAMMATE_NAME_DESC_A + VALID_TEAMMATE_GIT_USERNAME_A + TEAMMATE_PHONE_DESC_A
            + TEAMMATE_EMAIL_DESC_A + TEAMMATE_ADDRESS_DESC_A, expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, TEAMMATE_NAME_DESC_A + TEAMMATE_GIT_USERNAME_DESC_A + VALID_TEAMMATE_PHONE_A
            + TEAMMATE_EMAIL_DESC_A + TEAMMATE_ADDRESS_DESC_A, expectedMessage);

        // missing email prefix
        assertParseFailure(parser, TEAMMATE_NAME_DESC_A + TEAMMATE_GIT_USERNAME_DESC_A + TEAMMATE_PHONE_DESC_A
            + VALID_TEAMMATE_EMAIL_A + TEAMMATE_ADDRESS_DESC_A, expectedMessage);

        // missing address prefix
        assertParseFailure(parser, TEAMMATE_NAME_DESC_A + TEAMMATE_GIT_USERNAME_DESC_A + TEAMMATE_PHONE_DESC_A
            + TEAMMATE_EMAIL_DESC_A + VALID_TEAMMATE_ADDRESS_A, expectedMessage);
    }

    /**
     * Parses when different fields give incorrect input
     * Checks that the parse fails when each field is missing
     */
    @Test
    public void parse_invalidValue_failure() {
        // invalid personname
        assertParseFailure(parser, INVALID_TEAMMATE_NAME_DESC_A + TEAMMATE_GIT_USERNAME_DESC_A + TEAMMATE_PHONE_DESC_A
            + TEAMMATE_EMAIL_DESC_A + TEAMMATE_ADDRESS_DESC_A, PersonName.MESSAGE_CONSTRAINTS);

        // invalid gitUserName
        assertParseFailure(parser, TEAMMATE_NAME_DESC_A + INVALID_TEAMMATE_GIT_USERNAME_DESC_A + TEAMMATE_PHONE_DESC_A
            + TEAMMATE_EMAIL_DESC_A + TEAMMATE_ADDRESS_DESC_A, GitUserName.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, TEAMMATE_NAME_DESC_A + TEAMMATE_GIT_USERNAME_DESC_A + INVALID_TEAMMATE_PHONE_DESC_A
            + TEAMMATE_EMAIL_DESC_A + TEAMMATE_ADDRESS_DESC_A, Phone.MESSAGE_CONSTRAINTS);

        // invalid Email
        assertParseFailure(parser, TEAMMATE_NAME_DESC_A + TEAMMATE_GIT_USERNAME_DESC_A + TEAMMATE_PHONE_DESC_A
            + INVALID_TEAMMATE_EMAIL_DESC_A + TEAMMATE_ADDRESS_DESC_A, Email.MESSAGE_CONSTRAINTS);
    }


}























