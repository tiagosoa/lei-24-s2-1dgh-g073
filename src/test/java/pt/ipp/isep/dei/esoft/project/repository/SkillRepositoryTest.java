package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.repository.SkillRepository;
import pt.ipp.isep.dei.esoft.project.domain.Skill;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class SkillRepositoryTest {

    @Test
    void getSkillByNameEmptyList() {
        SkillRepository skillRepository = new SkillRepository();
        String skillName = "Skill Name";
        
        assertThrows(IllegalArgumentException.class,
                () -> skillRepository.getSkillByName(skillName));
    }

    @Test
    void getSkillByNameNullList() {
        SkillRepository skillRepository = new SkillRepository();
        String skillName = "Task Category Description";
        
        assertThrows(IllegalArgumentException.class,
                () -> skillRepository.getSkillByName(skillName));
    }

    @Test
    void ensureNewSkillSuccessfullyAdded() {
        SkillRepository skillRepository = new SkillRepository();
        String skillName = "Skill Name";
        
        Skill skill = new Skill(skillName);
        skillRepository.addSkill(skill);
    }

    @Test
    void testThatCreateSkillWorks() {
        SkillRepository skillRepository = new SkillRepository();

        Skill expected = new Skill("Skill Name");

        Optional<Skill> skill =
                skillRepository.createSkill("Skill Name");

        assertNotNull(skill);
        assertTrue(skill.isPresent());
        assertEquals(expected, skill.get());
    }

    @Test
    void ensureGetSkillForExistingSkill() {
        SkillRepository skillRepository = new SkillRepository();
        String skillName = "Skill Name";
        
        Skill skill = new Skill(skillName);
        skillRepository.addSkill(skill);
        Skill skill1 = skillRepository.getSkillByName(skillName);
        assertEquals(skill, skill1);
    }

    @Test
    void ensureGetSkillFailsForNonExistingSkill() {
        SkillRepository skillRepository = new SkillRepository();
        String skillName = "Skill Name";
        
        Skill skill = new Skill(skillName);
        skillRepository.addSkill(skill);
        String skillName1 = "Skill Name 1";
        assertThrows(IllegalArgumentException.class,
                () -> skillRepository.getSkillByName(skillName1));

    }

    @Test
    void ensureGetSkillReturnsAnImmutableList() {
        SkillRepository skillRepository = new SkillRepository();
        String skillName = "Skill Name";
        
        Skill skill = new Skill(skillName);
        skillRepository.addSkill(skill);

        assertThrows(UnsupportedOperationException.class,
                () -> skillRepository.getSkillList().add(new Skill("Skill Name One")));

    }

    @Test
    void ensureGetSkillReturnsTheCorrectList() {
        //Arrange
        SkillRepository skillRepository = new SkillRepository();
        
        String skillName = "Skill Name";
        Skill skill = new Skill(skillName);
        skillRepository.addSkill(skill);
        int expectedSize = 1;

        //Act
        int size = skillRepository.getSkillList().size();

        //Assert
        assertEquals(expectedSize, size);
        assertEquals(skill, skillRepository.getSkillList().get(size - 1));
    }

    @Test
    void ensureAddingDuplicateSkillFails() {
        //Arrange
        SkillRepository skillRepository = new SkillRepository();
        
        Skill skill = new Skill("Skill Name");
        //Add the first skill
        skillRepository.addSkill(skill);

        //Act
        boolean duplicateSkill = skillRepository.addSkill(skill);

        //Assert
        assertFalse(duplicateSkill);
    }

    @Test
    void ensureAddingDifferentSkillsWorks() {
        //Arrange
        SkillRepository skillRepository = new SkillRepository();
        
        Skill skillOne = new Skill("Skill Name One");
        Skill skillTwo = new Skill("Skill Name Two");
        //Add the first task
        skillRepository.addSkill(skillOne);

        //Act
        boolean expected = skillRepository.addSkill(skillTwo);

        //Assert
        assertTrue(expected);
    }
}