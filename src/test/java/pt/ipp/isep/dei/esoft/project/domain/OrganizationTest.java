package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class OrganizationTest {

    @Test
    void testEqualsSameObject() {
        Organization organization = new Organization("123456789");
        assertEquals(organization, organization);
    }

    @Test
    void testEqualsDifferentClass() {
        Organization organization = new Organization("123456789");
        assertNotEquals("", organization);
    }

    @Test
    void testEqualsNull() {
        Organization organization = new Organization("123456789");
        assertNotEquals(null, organization);
    }

    @Test
    void testEqualsDifferentObject() {
        Organization organization = new Organization("123456789");
        Organization organization1 = new Organization("123456789");
        assertEquals(organization, organization1);
    }

    @Test
    void testHashCodeSameObject() {
        Organization organization = new Organization("123456789");
        assertEquals(organization.hashCode(), organization.hashCode());
    }

    @Test
    void testHashCodeDifferentObject() {
        Organization organization = new Organization("123456789");
        Organization organization1 = new Organization("123456789");
        assertEquals(organization.hashCode(), organization1.hashCode());
    }

    @Test
        //same hashcode
    void testHashCodeSameObjectSameVATNumber() {
        Organization organization = new Organization("123456789");
        Organization organization1 = new Organization("123456789");
        assertEquals(organization.hashCode(), organization1.hashCode());
    }

    @Test
    void ensureHashCodeFailsForDifferentVatNumbers() {
        Organization organization = new Organization("123456789");
        Organization organization1 = new Organization("123456788");
        assertNotEquals(organization.hashCode(), organization1.hashCode());
    }

    @Test
    void ensureEqualsFailsForDifferentObjectType() {
        Organization organization = new Organization("123456789");
        Organization organization1 = new Organization("123456788");
        assertNotEquals(organization, organization1);
    }

    @Test
    void ensureEqualsFailsWhenComparingNull() {
        Organization organization = new Organization("123456789");
        assertNotEquals(organization, null);
    }

    @Test
    void ensureEqualsSuccessWhenComparingSameObject() {
        Organization organization = new Organization("123456789");
        assertEquals(organization, organization);
    }

    @Test
    void testThatRegisterCollaboratorWorks() {
        Organization organization = new Organization("123456789");

        HRM hrm = new HRM("john.doe@this.company.com");

        Collaborator expected = new Collaborator("name", "dd-MM-yyyy", "dd-MM-yyyy", "somewhere", 123456789, "johnlemon@beetle.thing", 123456789,"CC", 123456789, hrm);

        Optional<Collaborator> collaborator =
                organization.registerCollaborator("name", "dd-MM-yyyy", "dd-MM-yyyy", "somewhere", 123456789, "johnlemon@beetle.thing", 123456789,"CC", 123456789, hrm);

        assertNotNull(collaborator);
        assertTrue(collaborator.isPresent());
        assertEquals(expected, collaborator.get());
    }

    @Test
    void ensureAddingDuplicateCollaboratorFails() {
        //Arrange
        Organization organization = new Organization("123456789");
        HRM hrm = new HRM("john.doe@this.company.com");
        //Add the first collaborator
        Optional<Collaborator> originalCollaborator =
                organization.registerCollaborator("name", "dd-MM-yyyy", "dd-MM-yyyy", "somewhere", 123456789, "johnlemon@beetle.thing", 123456789,"CC", 123456789, hrm);

        //Act
        Optional<Collaborator> duplicateCollaborator =
                organization.registerCollaborator("name", "dd-MM-yyyy", "dd-MM-yyyy", "somewhere", 123456789, "johnlemon@beetle.thing", 123456789,"CC", 123456789, hrm);

        //Assert
        assertTrue(duplicateCollaborator.isEmpty());
    }

    @Test
    void testThatCreateVehicleWorks() {
        Organization organization = new Organization("123456789");

        HRM hrm = new HRM("john.doe@this.company.com");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate date1 = LocalDate.parse("30-04-2024", formatter);
        LocalDate date2 = LocalDate.parse("29-04-2024", formatter);
        LocalDate date3 = LocalDate.parse("01-05-2024", formatter);

        Vehicle expected = new Vehicle("Toyota", "Avensis", "Van", 1275, 1820, 30000, date1, date2, 1, "00-AA-00", date3);

        Optional<Vehicle> vehicle =
                organization.createVehicle("Toyota", "Avensis", "Van", 1275, 1820, 30000, date1, date2, 1, "00-AA-00", date3);

        assertNotNull(vehicle);
        assertTrue(vehicle.isPresent());
        assertEquals(expected, vehicle.get());
    }

    @Test
    void ensureAddingDuplicateVehicleFails() {
        //Arrange
        Organization organization = new Organization("123456789");
        HRM hrm = new HRM("john.doe@this.company.com");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate date1 = LocalDate.parse("30-04-2024", formatter);
        LocalDate date2 = LocalDate.parse("29-04-2024", formatter);
        LocalDate date3 = LocalDate.parse("01-05-2024", formatter);

        //Add the first vehicle
        Optional<Vehicle> originalVehicle =
                organization.createVehicle("Toyota", "Avensis", "Van", 1275, 1820, 30000, date1, date2, 1, "00-AA-00", date3);

        //Act
        Optional<Vehicle> duplicateVehicle =
                organization.createVehicle("Toyota", "Avensis", "Van", 1275, 1820, 30000, date1, date2, 1, "00-AA-00", date3);

        //Assert
        assertTrue(duplicateVehicle.isEmpty());
    }


    @Test
    void ensureHRMEmploysFails() {
        Organization organization = new Organization("123456789");
        HRM hrm = new HRM("john.doe@this.company.com");

        assertFalse(organization.employs(hrm));

    }

    @Test
    void ensureHRMEmploysSuccess() {
        Organization organization = new Organization("123456789");
        HRM hrm = new HRM("john.doe@this.company.com");
        organization.addHRM(hrm);
        assertTrue(organization.employs(hrm));
    }

    @Test
    void ensureAnyHRMHasEmailFails() {
        Organization organization = new Organization("123456789");
        HRM hrm = new HRM("john.doe@this.company.com");
        organization.addHRM(hrm);
        assertFalse(organization.anyHRMHasEmail("jane.doe@this.company.com"));
    }

    @Test
    void ensureAnyHRMHasEmailWorks() {
        Organization organization = new Organization("123456789");
        HRM hrm = new HRM("john.doe@this.company.com");
        organization.addHRM(hrm);
        assertTrue(organization.anyHRMHasEmail("john.doe@this.company.com"));
    }

    @Test
    void ensureAddDuplicateHRMFails() {
        Organization organization = new Organization("123456789");
        HRM hrm = new HRM("john.doe@this.company.com");
        assertTrue(organization.addHRM(hrm));
        assertFalse(organization.addHRM(hrm));
    }

    @Test
    void ensureAddHRMWorks() {
        Organization organization = new Organization("123456789");
        HRM hrm = new HRM("john.doe@this.company.com");
        assertTrue(organization.addHRM(hrm));
    }

    @Test
    void ensureHRMCloneWorks() {
        Organization organization = new Organization("123456789");
        HRM hrm = new HRM("john.doe@this.company.com");
        organization.addHRM(hrm);

        Organization clone = organization.clone();
        assertEquals(organization, clone);
    }
    @Test
    void ensureVFMEmploysFails() {
        Organization organization = new Organization("123456789");
        VFM vfm = new VFM("john.doe@this.company.com");

        assertFalse(organization.employs(vfm));

    }

    @Test
    void ensureVFMEmploysSuccess() {
        Organization organization = new Organization("123456789");
        VFM vfm = new VFM("john.doe@this.company.com");
        organization.addVFM(vfm);
        assertTrue(organization.employs(vfm));
    }

    @Test
    void ensureAnyVFMHasEmailFails() {
        Organization organization = new Organization("123456789");
        VFM vfm = new VFM("john.doe@this.company.com");
        organization.addVFM(vfm);
        assertFalse(organization.anyVFMHasEmail("jane.doe@this.company.com"));
    }

    @Test
    void ensureAnyVFMHasEmailWorks() {
        Organization organization = new Organization("123456789");
        VFM vfm = new VFM("john.doe@this.company.com");
        organization.addVFM(vfm);
        assertTrue(organization.anyVFMHasEmail("john.doe@this.company.com"));
    }

    @Test
    void ensureAddDuplicateVFMFails() {
        Organization organization = new Organization("123456789");
        VFM vfm = new VFM("john.doe@this.company.com");
        assertTrue(organization.addVFM(vfm));
        assertFalse(organization.addVFM(vfm));
    }

    @Test
    void ensureAddVFMWorks() {
        Organization organization = new Organization("123456789");
        VFM vfm = new VFM("john.doe@this.company.com");
        assertTrue(organization.addVFM(vfm));
    }

    @Test
    void ensureVFMCloneWorks() {
        Organization organization = new Organization("123456789");
        VFM vfm = new VFM("john.doe@this.company.com");
        organization.addVFM(vfm);

        Organization clone = organization.clone();
        assertEquals(organization, clone);
    }
}