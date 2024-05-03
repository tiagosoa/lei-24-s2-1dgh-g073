package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SkillTest {

    @Test
    void ensureSkillIsCreatedSuccessfully() {
        HRM hrm = new HRM("john.doe@this.company.com");
        Skill skill = new Skill("name");
    }

    @Test
    void ensureSkillNameIsNotNull() {
        //Arrange
        HRM hrm = new HRM("john.doe@this.company.com");


        //Act and Assert
        assertThrows(IllegalArgumentException.class,
                () -> new Skill(null));
    }

    @Test
    void testEqualsSameObject() {
        HRM hrm = new HRM("john.doe@this.company.com");
        Skill skill = new Skill("name");
        assertEquals(skill, skill);
    }

    @Test
    void testEqualsDifferentClass() {
        HRM hrm = new HRM("john.doe@this.company.com");
        Skill skill = new Skill("name");

        assertNotEquals(skill, new Object());
    }

    @Test
    void testEqualsNull() {
        HRM hrm = new HRM("john.doe@this.company.com");
        Skill skill = new Skill("name");

        assertNotEquals(skill, null);
    }

    @Test
    void testEqualsDifferentObjects() {
        HRM hrm = new HRM("john.doe@this.company.com");
        Skill skill = new Skill("name");
        Skill skill1 = new Skill("namelei");

        assertNotEquals(skill, skill1);
    }

    @Test
    void testHashCodeSameObject() {
        HRM hrm = new HRM("john.doe@this.company.com");
        Skill skill = new Skill("name");
        assertEquals(skill.hashCode(), skill.hashCode());
    }

    @Test
    void testHashCodeDifferentObject() {
        HRM hrm = new HRM("john.doe@this.company.com");
        Skill skill = new Skill("name");
        Skill skill1 = new Skill("namelei");

        assertNotEquals(skill.hashCode(), skill1.hashCode());
    }

    @Test
    void ensureCloneWorks() {
        HRM hrm = new HRM("john.doe@this.company.org");
        Skill skill = new Skill("name");
        Skill clone = skill.clone();
        assertEquals(skill, clone);
    }
}