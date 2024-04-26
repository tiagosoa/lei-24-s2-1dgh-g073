package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SkillTest {

    @Test
    void ensureSkillIsCreatedSuccessfully() {
        HRM hrm = new HRM("john.doe@this.company.com");
        Skill skill = new Skill("name", hrm);
    }

    @Test
    void ensureSkillNameIsNotNull() {
        //Arrange
        HRM hrm = new HRM("john.doe@this.company.com");


        //Act and Assert
        assertThrows(IllegalArgumentException.class,
                () -> new Skill(null, hrm));
    }

    @Test
    void testEqualsSameObject() {
        HRM hrm = new HRM("john.doe@this.company.com");
        Skill skill = new Skill("name", hrm);
        assertEquals(skill, skill);
    }

    @Test
    void testEqualsDifferentClass() {
        HRM hrm = new HRM("john.doe@this.company.com");
        Skill skill = new Skill("name", hrm);

        assertNotEquals(skill, new Object());
    }

    @Test
    void testEqualsNull() {
        HRM hrm = new HRM("john.doe@this.company.com");
        Skill skill = new Skill("name", hrm);

        assertNotEquals(skill, null);
    }

    @Test
    void testEqualsDifferentObjects() {
        HRM hrm = new HRM("john.doe@this.company.com");
        Skill skill = new Skill("name", hrm);
        Skill skill1 = new Skill("name1", hrm);

        assertNotEquals(skill, skill1);
    }

    @Test
    void testEqualsSameObjects() {
        HRM hrm = new HRM("john.doe@this.company.com");
        Skill skill = new Skill("name", hrm);
        Skill skill1 = new Skill("name", hrm);

        assertNotEquals(skill, skill1);
    }

    @Test
    void testHashCodeSameObject() {
        HRM hrm = new HRM("john.doe@this.company.com");
        Skill skill = new Skill("name", hrm);
        assertEquals(skill.hashCode(), skill.hashCode());
    }

    @Test
    void testHashCodeDifferentObject() {
        HRM hrm = new HRM("john.doe@this.company.com");
        Skill skill = new Skill("name", hrm);
        Skill skill1 = new Skill("name1", hrm);

        assertNotEquals(skill.hashCode(), skill1.hashCode());
    }

    @Test
    void ensureCloneWorks() {
        HRM hrm = new HRM("john.doe@this.company.org");
        Skill skill = new Skill("name", hrm);
        Skill clone = skill.clone();
        assertEquals(skill, clone);
    }
}