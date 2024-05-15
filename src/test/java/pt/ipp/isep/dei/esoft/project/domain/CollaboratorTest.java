package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CollaboratorTest {

    @Test
    void ensureCollaboratorIsCreatedSuccessfully() {
        HRM hrm = new HRM("john.doe@this.company.com");
        Collaborator collaborator = new Collaborator("name", "dd-MM-yyyy", "dd-MM-yyyy", "somewhere", 123456789, "johnlemon@beetle.thing", 123456789,"CC", 123456789);
    }

    @Test
    void ensureCollaboratorNameIsNotNull() {
        //Arrange
        HRM hrm = new HRM("john.doe@this.company.com");


        //Act and Assert
        assertThrows(IllegalArgumentException.class,
                () -> new Collaborator(null, "dd-MM-yyyy", "dd-MM-yyyy", "somewhere", 123456789, "johnlemon@beetle.thing", 123456789,"CC", 123456789));
    }

    @Test
    void testEqualsSameObject() {
        HRM hrm = new HRM("john.doe@this.company.com");
        Collaborator collaborator = new Collaborator("name", "dd-MM-yyyy", "dd-MM-yyyy", "somewhere", 123456789, "johnlemon@beetle.thing", 123456789,"CC", 123456789);
        assertEquals(collaborator, collaborator);
    }

    @Test
    void testEqualsDifferentClass() {
        HRM hrm = new HRM("john.doe@this.company.com");
        Collaborator collaborator = new Collaborator("name", "dd-MM-yyyy", "dd-MM-yyyy", "somewhere", 123456789, "johnlemon@beetle.thing", 123456789,"CC", 123456789);

        assertNotEquals(collaborator, new Object());
    }

    @Test
    void testEqualsNull() {
        HRM hrm = new HRM("john.doe@this.company.com");
        Collaborator collaborator = new Collaborator("name", "dd-MM-yyyy", "dd-MM-yyyy", "somewhere", 123456789, "johnlemon@beetle.thing", 123456789,"CC", 123456789);

        assertNotEquals(collaborator, null);
    }

    @Test
    void testEqualsDifferentObjects() {
        HRM hrm = new HRM("john.doe@this.company.com");
        Collaborator collaborator = new Collaborator("name", "dd-MM-yyyy", "dd-MM-yyyy", "somewhere", 123456789, "johnlemon@beetle.thing", 123456789,"CC", 123456789);
        Collaborator collaborator1 = new Collaborator("nemlei", "dd-MM-yyyy", "dd-MM-yyyy", "somewhere", 123456789, "johnlemon@beetle.thing", 123456799,"CC", 123456799);

        assertNotEquals(collaborator, collaborator1);
    }

    @Test
    void testHashCodeSameObject() {
        HRM hrm = new HRM("john.doe@this.company.com");
        Collaborator collaborator = new Collaborator("name", "dd-MM-yyyy", "dd-MM-yyyy", "somewhere", 123456789, "johnlemon@beetle.thing", 123456789,"CC", 123456789);
        assertEquals(collaborator.hashCode(), collaborator.hashCode());
    }

    @Test
    void testHashCodeDifferentObject() {
        HRM hrm = new HRM("john.doe@this.company.com");
        Collaborator collaborator = new Collaborator("name", "dd-MM-yyyy", "dd-MM-yyyy", "somewhere", 123456789, "johnlemon@beetle.thing", 123456789,"CC", 123456789);
        Collaborator collaborator1 = new Collaborator("nemlei", "dd-MM-yyyy", "dd-MM-yyyy", "somewhere", 123456789, "johnlemon@beetle.thing", 123456799,"CC", 123456799);

        assertNotEquals(collaborator.hashCode(), collaborator1.hashCode());
    }

    @Test
    void ensureCloneWorks() {
        HRM hrm = new HRM("john.doe@this.company.org");
        Collaborator collaborator = new Collaborator("name", "dd-MM-yyyy", "dd-MM-yyyy", "somewhere", 123456789, "johnlemon@beetle.thing", 123456789,"CC", 123456789);
        Collaborator clone = collaborator.clone();
        assertEquals(collaborator, clone);
    }
}