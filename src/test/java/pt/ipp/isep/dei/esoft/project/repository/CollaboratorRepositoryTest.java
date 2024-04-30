package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.HRM;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class CollaboratorRepositoryTest {

    @Test
    void getCollaboratorByIDEmptyList() {
        CollaboratorRepository collaboratorRepository = new CollaboratorRepository();
        int collaboratorID = 123456789;
        assertThrows(IllegalArgumentException.class,
                () -> collaboratorRepository.getCollaboratorByID(collaboratorID));
    }

    @Test
    void getCollaboratorByIDNullList() {
        CollaboratorRepository collaboratorRepository = new CollaboratorRepository();
        int collaboratorID = 123456789;
        assertThrows(IllegalArgumentException.class,
                () -> collaboratorRepository.getCollaboratorByID(collaboratorID));
    }

    @Test
    void ensureNewCollaboratorSuccessfullyAdded() {
        CollaboratorRepository collaboratorRepository = new CollaboratorRepository();
        int collaboratorID = 123456789;
        HRM hrm = new HRM("john.doe@this.company.com");
        Collaborator collaborator = new Collaborator("name", "dd-MM-yyyy", "dd-MM-yyyy", "somewhere", 123456789, "johnlemon@beetle.thing", "CC", collaboratorID, hrm);
        collaboratorRepository.add(collaborator);
    }

    @Test
    void ensureGetCollaboratorForExistingCollaborator() {
        CollaboratorRepository collaboratorRepository = new CollaboratorRepository();
        int collaboratorID = 123456789;
        HRM hrm = new HRM("john.doe@this.company.com");
        Collaborator collaborator = new Collaborator("name", "dd-MM-yyyy", "dd-MM-yyyy", "somewhere", 123456789, "johnlemon@beetle.thing", "CC", collaboratorID, hrm);
        collaboratorRepository.add(collaborator);
        Collaborator collaborator1 = collaboratorRepository.getCollaboratorByID(collaboratorID);
        assertEquals(collaborator, collaborator1);
    }

    @Test
    void ensureGetCollaboratorFailsForNonExistingCollaborator() {
        CollaboratorRepository collaboratorRepository = new CollaboratorRepository();
        int collaboratorID = 123456789;
        HRM hrm = new HRM("john.doe@this.company.com");
        Collaborator collaborator = new Collaborator("name", "dd-MM-yyyy", "dd-MM-yyyy", "somewhere", 123456789, "johnlemon@beetle.thing", "CC", collaboratorID, hrm);
        collaboratorRepository.add(collaborator);
        int collaboratorID1 = 123456780;
        assertThrows(IllegalArgumentException.class,
                () -> collaboratorRepository.getCollaboratorByID(collaboratorID1));

    }

    @Test
    void ensureGetCollaboratorReturnsAnImmutableList() {
        CollaboratorRepository collaboratorRepository = new CollaboratorRepository();
        int collaboratorID = 123456789;
        HRM hrm = new HRM("john.doe@this.company.com");
        Collaborator collaborator = new Collaborator("name", "dd-MM-yyyy", "dd-MM-yyyy", "somewhere", 123456789, "johnlemon@beetle.thing", "CC", collaboratorID, hrm);
        collaboratorRepository.add(collaborator);

        assertThrows(UnsupportedOperationException.class,
                () -> collaboratorRepository.getCollaborators().add(new Collaborator("name", "dd-MM-yyyy", "dd-MM-yyyy", "somewhere", 123456789, "johnlemon@beetle.thing", "CC", collaboratorID, hrm)));

    }

    @Test
    void ensureGetCollaboratorReturnsTheCorrectList() {
        //Arrange
        CollaboratorRepository collaboratorRepository = new CollaboratorRepository();
        HRM hrm = new HRM("john.doe@this.company.com");
        int collaboratorID = 123456789;
        Collaborator collaborator = new Collaborator("name", "dd-MM-yyyy", "dd-MM-yyyy", "somewhere", 123456789, "johnlemon@beetle.thing", "CC", collaboratorID, hrm);
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
        int collaboratorID = 123456789;
        Collaborator collaborator = new Collaborator("name", "dd-MM-yyyy", "dd-MM-yyyy", "somewhere", 123456789, "johnlemon@beetle.thing", "CC", collaboratorID, hrm);
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
        int collaboratorID = 123456789;
        int collaboratorID1 = 123456780;
        Collaborator collaboratorOne = new Collaborator("name", "dd-MM-yyyy", "dd-MM-yyyy", "somewhere", 123456789, "johnlemon@beetle.thing", "CC", collaboratorID, hrm);
        Collaborator collaboratorTwo = new Collaborator("nemlei", "dd-MM-yyyy", "dd-MM-yyyy", "somewhere", 123456789, "johnlemon@beetle.thing", "CC", collaboratorID1, hrm);
        //Add the first task
        collaboratorRepository.add(collaboratorOne);

        //Act
        Optional<Collaborator> result = collaboratorRepository.add(collaboratorTwo);

        //Assert
        assertEquals(collaboratorTwo, result.get());
    }
}