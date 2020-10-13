package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.HashMap;
import java.util.Objects;

import seedu.address.model.project.Participation;
import seedu.address.model.project.Project;
import seedu.address.model.project.ProjectName;

/**
 * Represents a Teammate in the team.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private PersonName personName;
    private Phone phone;
    private Email email;

    // Data fields
    private Address address;
    private HashMap<ProjectName, Participation> listOfParticipations = new HashMap<>();

    /**
     * Every field must be present and not null.
     */
    public Person(PersonName personName, Phone phone, Email email, Address address) {
        requireAllNonNull(personName, phone, email, address);
        this.personName = personName;
        this.phone = phone;
        this.email = email;
        this.address = address;
    }

    /**
     * Overloaded constructor to take in listOfParticipations
     */
    public Person(PersonName personName, Phone phone, Email email, Address address, HashMap<ProjectName,
        Participation> listOfParticipations) {
        requireAllNonNull(personName, phone, email, address);
        this.personName = personName;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.listOfParticipations = listOfParticipations;
    }

    public PersonName getPersonName() {
        return personName;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    public HashMap<ProjectName, Participation> getParticipations() {
        return this.listOfParticipations;
    }

    public void updatePersonName(String newPersonNameStr) {
        personName = new PersonName(newPersonNameStr);
    }

    public void updateAddress(String newAddressStr) {
        address = new Address(newAddressStr);
    }

    public void updatePhone(String newPhonestr) {
        phone = new Phone(newPhonestr);
    }

    public void updateEmail(String newEmailStr) {
        email = new Email(newEmailStr);
    }

    public void addProject(Project p) {
        listOfParticipations.put(p.getProjectName(), new Participation(this, p));
    }

    /**
     * Returns true if both teammates of the same name have at least one other identity field that is the same.
     * This defines a weaker notion of equality between two projects.
     */
    public boolean isSameTeammate(Person otherTeammate) {
        if (otherTeammate == this) {
            return true;
        }

        return otherTeammate != null
                && otherTeammate.getPersonName().equals(getPersonName())
                && (otherTeammate.getPhone().equals(getPhone())
                || otherTeammate.getEmail().equals(getEmail())
                || otherTeammate.getAddress().equals(getAddress()));
    }

    /**
     * Returns true if both projects have the same identity and data fields.
     * This defines a stronger notion of equality between two projects.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Person)) {
            return false;
        }

        Person otherProject = (Person) other;
        return otherProject.getPersonName().equals(getPersonName())
                && otherProject.getPhone().equals(getPhone())
                && otherProject.getEmail().equals(getEmail())
                && otherProject.getAddress().equals(getAddress());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(personName, phone, email, address);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(" Person name: ")
                .append(getPersonName())
                .append(" Phone: ")
                .append(getPhone())
                .append(" Email: ")
                .append(getEmail())
                .append(" Address: ")
                .append(getAddress());
        return builder.toString();
    }

}
