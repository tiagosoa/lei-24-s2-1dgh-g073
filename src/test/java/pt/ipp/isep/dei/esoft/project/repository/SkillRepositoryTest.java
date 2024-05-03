package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.domain.HRM;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class SkillRepositoryTest {

    @Test
    void getSkillByNameEmptyList() {
        SkillRepository skillRepository = new SkillRepository();
        String skillName = "Skill Name";
        HRM hrm = new HRM("john.doe@this.company.com");
        assertThrows(IllegalArgumentException.class,
                () -> skillRepository.getSkillByName(skillName));
    }

    @Test
    void getSkillByNameNullList() {
        SkillRepository skillRepository = new SkillRepository();
        String skillName = "Task Category Description";
        HRM hrm = new HRM("john.doe@this.company.com");
        assertThrows(IllegalArgumentException.class,
                () -> skillRepository.getSkillByName(skillName));
    }

    @Test
    void ensureNewSkillSuccessfullyAdded() {
        SkillRepository skillRepository = new SkillRepository();
        String skillName = "Skill Name";
        HRM hrm = new HRM("john.doe@this.company.com");
        Skill skill = new Skill(skillName);
        skillRepository.add(skill);
    }

    @Test
    void ensureGetSkillForExistingSkill() {
        SkillRepository skillRepository = new SkillRepository();
        String skillName = "Skill Name";
        HRM hrm = new HRM("john.doe@this.company.com");
        Skill skill = new Skill(skillName);
        skillRepository.add(skill);
        Skill skill1 = skillRepository.getSkillByName(skillName);
        assertEquals(skill, skill1);
    }

    @Test
    void ensureGetSkillFailsForNonExistingSkill() {
        SkillRepository skillRepository = new SkillRepository();
        String skillName = "Skill Name";
        HRM hrm = new HRM("john.doe@this.company.com");
        Skill skill = new Skill(skillName);
        skillRepository.add(skill);
        String skillName1 = "Skill Name 1";
        assertThrows(IllegalArgumentException.class,
                () -> skillRepository.getSkillByName(skillName1));

    }

    @Test
    void ensureGetSkillReturnsAnImmutableList() {
        SkillRepository skillRepository = new SkillRepository();
        String skillName = "Skill Name";
        HRM hrm = new HRM("john.doe@this.company.com");
        Skill skill = new Skill(skillName);
        skillRepository.add(skill);

        assertThrows(UnsupportedOperationException.class,
                () -> skillRepository.getSkills().add(new Skill("Skill Name 1")));

    }

    @Test
    void ensureGetSkillReturnsTheCorrectList() {
        //Arrange
        SkillRepository skillRepository = new SkillRepository();
        HRM hrm = new HRM("john.doe@this.company.com");
        String skillName = "Skill Name";
        Skill skill = new Skill(skillName);
        skillRepository.add(skill);
        int expectedSize = 1;

        //Act
        int size = skillRepository.getSkills().size();

        //Assert
        assertEquals(expectedSize, size);
        assertEquals(skill, skillRepository.getSkills().get(size - 1));
    }

    @Test
    void ensureAddingDuplicateSkillFails() {
        //Arrange
        SkillRepository skillRepository = new SkillRepository();
        HRM hrm = new HRM("john.doe@this.company.com");
        Skill skill = new Skill("Skill Name");
        //Add the first skill
        skillRepository.add(skill);

        //Act
        Optional<Skill> duplicateSkill = skillRepository.add(skill);

        //Assert
        assertTrue(duplicateSkill.isEmpty());
    }

    @Test
    void ensureAddingDifferentSkillsWorks() {
        //Arrange
        SkillRepository skillRepository = new SkillRepository();
        HRM hrm = new HRM("john.doe@this.company.com");
        Skill skillOne = new Skill("Skill Name One");
        Skill skillTwo = new Skill("Skill Name Two");
        //Add the first task
        skillRepository.add(skillOne);

        //Act
        Optional<Skill> result = skillRepository.add(skillTwo);

        //Assert
        assertEquals(skillTwo, result.get());
    }
}