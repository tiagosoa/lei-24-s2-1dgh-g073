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