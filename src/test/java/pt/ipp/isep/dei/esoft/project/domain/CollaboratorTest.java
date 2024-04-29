package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CollaboratorTest {

    @Test
    void ensureCollaboratorIsCreatedSuccessfully() {
        HRM hrm = new HRM("john.doe@this.company.com");
        Collaborator collaborator = new Collaborator("name", hrm);
    }

    @Test
    void ensureCollaboratorNameIsNotNull() {
        //Arrange
        HRM hrm = new HRM("john.doe@this.company.com");


        //Act and Assert
        assertThrows(IllegalArgumentException.class,
                () -> new Collaborator(null, hrm));
    }

    @Test
    void testEqualsSameObject() {
        HRM hrm = new HRM("john.doe@this.company.com");
        Collaborator collaborator = new Collaborator("name", hrm);
        assertEquals(collaborator, collaborator);
    }

    @Test
    void testEqualsDifferentClass() {
        HRM hrm = new HRM("john.doe@this.company.com");
        Collaborator collaborator = new Collaborator("name", hrm);

        assertNotEquals(collaborator, new Object());
    }

    @Test
    void testEqualsNull() {
        HRM hrm = new HRM("john.doe@this.company.com");
        Collaborator collaborator = new Collaborator("name", hrm);

        assertNotEquals(collaborator, null);
    }

    @Test
    void testEqualsDifferentObjects() {
        HRM hrm = new HRM("john.doe@this.company.com");
        Collaborator collaborator = new Collaborator("name", hrm);
        Collaborator collaborator1 = new Collaborator("name1", hrm);

        assertNotEquals(collaborator, collaborator1);
    }

    @Test
    void testHashCodeSameObject() {
        HRM hrm = new HRM("john.doe@this.company.com");
        Collaborator collaborator = new Collaborator("name", hrm);
        assertEquals(collaborator.hashCode(), collaborator.hashCode());
    }

    @Test
    void testHashCodeDifferentObject() {
        HRM hrm = new HRM("john.doe@this.company.com");
        Collaborator collaborator = new Collaborator("name", hrm);
        Collaborator collaborator1 = new Collaborator("name1", hrm);

        assertNotEquals(collaborator.hashCode(), collaborator1.hashCode());
    }

    @Test
    void ensureCloneWorks() {
        HRM hrm = new HRM("john.doe@this.company.org");
        Collaborator collaborator = new Collaborator("name", hrm);
        Collaborator clone = collaborator.clone();
        assertEquals(collaborator, clone);
    }
}