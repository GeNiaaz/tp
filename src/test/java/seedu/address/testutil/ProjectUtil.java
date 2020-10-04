package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PROJECT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK;

import java.util.Set;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.EditCommand.EditProjectDescriptor;
import seedu.address.model.project.Project;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Task;

/**
 * A utility class for Project.
 */
public class ProjectUtil {

    /**
     * Returns an add command string for adding the {@code project}.
     */
    public static String getAddCommand(Project project) {
        return AddCommand.COMMAND_WORD + " " + getProjectDetails(project);
    }

    /**
     * Returns the part of command string for the given {@code project}'s details.
     */
    public static String getProjectDetails(Project project) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_PROJECT_NAME + project.getProjectName().fullProjectName + " ");
        sb.append(PREFIX_PHONE + project.getPhone().value + " ");
        sb.append(PREFIX_EMAIL + project.getEmail().value + " ");
        sb.append(PREFIX_ADDRESS + project.getAddress().value + " ");
        project.getTags().stream().forEach(
            s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        project.getTasks().stream().forEach(
            s -> sb.append(PREFIX_TASK + s.taskName + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditProjectDescriptor}'s details.
     */
    public static String getEditProjectDescriptorDetails(EditProjectDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getProjectName().ifPresent(name -> sb.append(PREFIX_PROJECT_NAME).append(name.fullProjectName).
            append(" "));
        descriptor.getPhone().ifPresent(phone -> sb.append(PREFIX_PHONE).append(phone.value).append(" "));
        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email.value).append(" "));
        descriptor.getAddress().ifPresent(address -> sb.append(PREFIX_ADDRESS).append(address.value).append(" "));
        if (descriptor.getTags().isPresent()) {
            Set<Tag> tags = descriptor.getTags().get();
            if (tags.isEmpty()) {
                sb.append(PREFIX_TAG);
            } else {
                tags.forEach(s -> sb.append(PREFIX_TAG).append(s.tagName).append(" "));
            }
        }
        sb.append(" ");
        if (descriptor.getTasks().isPresent()) {
            Set<Task> tasks = descriptor.getTasks().get();
            if (tasks.isEmpty()) {
                sb.append(PREFIX_TASK);
            } else {
                tasks.forEach(s -> sb.append(PREFIX_TASK).append(s.taskName).append(" "));
            }
        }
        return sb.toString();
    }
}
