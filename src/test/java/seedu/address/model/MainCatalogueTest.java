package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalProjects.ALICE;
import static seedu.address.testutil.TypicalProjects.getTypicalMainCatalogue;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.project.Project;
import seedu.address.model.project.exceptions.DuplicateProjectException;
import seedu.address.testutil.ProjectBuilder;

public class MainCatalogueTest {

    private final MainCatalogue mainCatalogue = new MainCatalogue();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), mainCatalogue.getProjectList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> mainCatalogue.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyMainCatalogue_replacesData() {
        MainCatalogue newData = getTypicalMainCatalogue();
        mainCatalogue.resetData(newData);
        assertEquals(newData, mainCatalogue);
    }

    @Test
    public void resetData_withDuplicateProjects_throwsDuplicateProjectException() {
        // Two projects with the same identity fields
        Project editedAlice = new ProjectBuilder(ALICE).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Project> newProjects = Arrays.asList(ALICE, editedAlice);
        MainCatalogueStub newData = new MainCatalogueStub(newProjects);

        assertThrows(DuplicateProjectException.class, () -> mainCatalogue.resetData(newData));
    }

    @Test
    public void hasProject_nullProject_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> mainCatalogue.hasProject(null));
    }

    @Test
    public void hasProject_projectNotInMainCatalogue_returnsFalse() {
        assertFalse(mainCatalogue.hasProject(ALICE));
    }

    @Test
    public void hasProject_projectInMainCatalogue_returnsTrue() {
        mainCatalogue.addProject(ALICE);
        assertTrue(mainCatalogue.hasProject(ALICE));
    }

    @Test
    public void hasProject_projectWithSameIdentityFieldsInMainCatalogue_returnsTrue() {
        mainCatalogue.addProject(ALICE);
        Project editedAlice = new ProjectBuilder(ALICE).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(mainCatalogue.hasProject(editedAlice));
    }

    @Test
    public void getProjectList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> mainCatalogue.getProjectList().remove(0));
    }

    /**
     * A stub ReadOnlyMainCatalogue whose projects list can violate interface constraints.
     */
    private static class MainCatalogueStub implements ReadOnlyMainCatalogue {
        private final ObservableList<Project> projects = FXCollections.observableArrayList();

        MainCatalogueStub(Collection<Project> projects) {
            this.projects.setAll(projects);
        }

        @Override
        public ObservableList<Project> getProjectList() {
            return projects;
        }
    }

}
