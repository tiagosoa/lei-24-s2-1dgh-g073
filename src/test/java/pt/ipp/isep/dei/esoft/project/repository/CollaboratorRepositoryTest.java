package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.HRM;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class CollaboratorRepositoryTest {

    @Test
    void getCollaboratorByNameEmptyList() {
        CollaboratorRepository collaboratorRepository = new CollaboratorRepository();
        String collaboratorName = "Collaborator Name";
        HRM hrm = new HRM("john.doe@this.company.com");
        assertThrows(IllegalArgumentException.class,
                () -> collaboratorRepository.getCollaboratorByName(collaboratorName, hrm));
    }

    @Test
    void getCollaboratorByNameNullList() {
        CollaboratorRepository collaboratorRepository = new CollaboratorRepository();
        String collaboratorName = "Task Category Description";
        HRM hrm = new HRM("john.doe@this.company.com");
        assertThrows(IllegalArgumentException.class,
                () -> collaboratorRepository.getCollaboratorByName(collaboratorName, hrm));
    }

    @Test
    void ensureNewCollaboratorSuccessfullyAdded() {
        CollaboratorRepository collaboratorRepository = new CollaboratorRepository();
        String collaboratorName = "Collaborator Name";
        HRM hrm = new HRM("john.doe@this.company.com");
        Collaborator collaborator = new Collaborator(collaboratorName, hrm);
        collaboratorRepository.add(collaborator);
    }

    @Test
    void ensureGetCollaboratorForExistingCollaborator() {
        CollaboratorRepository collaboratorRepository = new CollaboratorRepository();
        String collaboratorName = "Collaborator Name";
        HRM hrm = new HRM("john.doe@this.company.com");
        Collaborator collaborator = new Collaborator(collaboratorName, hrm);
        collaboratorRepository.add(collaborator);
        Collaborator collaborator1 = collaboratorRepository.getCollaboratorByName(collaboratorName, hrm);
        assertEquals(collaborator, collaborator1);
    }

    @Test
    void ensureGetCollaboratorFailsForNonExistingCollaborator() {
        CollaboratorRepository collaboratorRepository = new CollaboratorRepository();
        String collaboratorName = "Collaborator Name";
        HRM hrm = new HRM("john.doe@this.company.com");
        Collaborator collaborator = new Collaborator(collaboratorName, hrm);
        collaboratorRepository.add(collaborator);
        String collaboratorName1 = "Collaborator Name 1";
        assertThrows(IllegalArgumentException.class,
                () -> collaboratorRepository.getCollaboratorByName(collaboratorName1, hrm));

    }

    @Test
    void ensureGetCollaboratorReturnsAnImmutableList() {
        CollaboratorRepository collaboratorRepository = new CollaboratorRepository();
        String collaboratorName = "Collaborator Name";
        HRM hrm = new HRM("john.doe@this.company.com");
        Collaborator collaborator = new Collaborator(collaboratorName, hrm);
        collaboratorRepository.add(collaborator);

        assertThrows(UnsupportedOperationException.class,
                () -> collaboratorRepository.getCollaborators().add(new Collaborator("Collaborator Name 1", hrm)));

    }

    @Test
    void ensureGetCollaboratorReturnsTheCorrectList() {
        //Arrange
        CollaboratorRepository collaboratorRepository = new CollaboratorRepository();
        HRM hrm = new HRM("john.doe@this.company.com");
        String collaboratorName = "Collaborator Name";
        Collaborator collaborator = new Collaborator(collaboratorName, hrm);
        collaboratorRepository.add(collaborator);
        int expectedSize = 1;

        //Act
        int size = collaboratorRepository.getCollaborators().size();

        //Assert
        assertEquals(expectedSize, size);
        assertEquals(collaborator, collaboratorRepository.getCollaborators().get(size - 1));
    }

    @Test
    void ensureAddingDuplicateCollaboratorFails() {
        //Arrange
        CollaboratorRepository collaboratorRepository = new CollaboratorRepository();
        HRM hrm = new HRM("john.doe@this.company.com");
        Collaborator collaborator = new Collaborator("Collaborator Name", hrm);
        //Add the first collaborator
        collaboratorRepository.add(collaborator);

        //Act
        Optional<Collaborator> duplicateCollaborator = collaboratorRepository.add(collaborator);

        //Assert
        assertTrue(duplicateCollaborator.isEmpty());
    }

    @Test
    void ensureAddingDifferentCollaboratorsWorks() {
        //Arrange
        CollaboratorRepository collaboratorRepository = new CollaboratorRepository();
        HRM hrm = new HRM("john.doe@this.company.com");
        Collaborator collaboratorOne = new Collaborator("Collaborator Name One", hrm);
        Collaborator collaboratorTwo = new Collaborator("Collaborator Name Two", hrm);
        //Add the first task
        collaboratorRepository.add(collaboratorOne);

        //Act
        Optional<Collaborator> result = collaboratorRepository.add(collaboratorTwo);

        //Assert
        assertEquals(collaboratorTwo, result.get());
    }
}