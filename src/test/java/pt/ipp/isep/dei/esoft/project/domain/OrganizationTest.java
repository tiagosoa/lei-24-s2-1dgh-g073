package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;

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
    void testThatCreateSkillWorks() {
        Organization organization = new Organization("123456789");

        HRM hrm = new HRM("john.doe@this.company.com");

        Skill expected = new Skill("Skill Name");

        Optional<Skill> skill =
                organization.createSkill("Skill Name");

        assertNotNull(skill);
        assertTrue(skill.isPresent());
        assertEquals(expected, skill.get());
    }

    @Test
    void ensureAddingDuplicateSkillFails() {
        //Arrange
        Organization organization = new Organization("123456789");
        HRM hrm = new HRM("john.doe@this.company.com");
        //Add the first task
        Optional<Skill> originalSkill =
                organization.createSkill("Skill Name");

        //Act
        Optional<Skill> duplicateSkill =
                organization.createSkill("Skill Name");

        //Assert
        assertTrue(duplicateSkill.isEmpty());
    }


    @Test
    void ensureEmploysFails() {
        Organization organization = new Organization("123456789");
        HRM hrm = new HRM("john.doe@this.company.com");

        assertFalse(organization.employs(hrm));

    }

    @Test
    void ensureEmploysSuccess() {
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
    void ensureCloneWorks() {
        Organization organization = new Organization("123456789");
        HRM hrm = new HRM("john.doe@this.company.com");
        organization.addHRM(hrm);
        organization.createSkill("Skill Name");

        Organization clone = organization.clone();
        assertEquals(organization, clone);
    }
}