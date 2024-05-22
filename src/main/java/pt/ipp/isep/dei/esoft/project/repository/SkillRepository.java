package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Skill;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Repository class for managing skills.
 */
public class SkillRepository {

    private List<Skill> skills;

    /**
     * Constructs a new SkillRepository.
     * Initializes the skills list with the existing skills from the Organization.
     * If the skills list is null, it creates a new empty list.
     */
    public SkillRepository() {
        this.skills = new ArrayList<>();
        }

    /**
     * Retrieves an existing Skill by its name.
     *
     * @param name The name of the skill to retrieve.
     * @return The Skill with the specified name.
     * @throws IllegalArgumentException if the skill does not exist.
     */
    public Skill getSkillByName(String name) {
        for (Skill existingSkill : skills) {
            if (existingSkill.getName().equals(name)) {
                return existingSkill;
            }
        }
        throw new IllegalArgumentException("Skill does not exist.");
    }

    /**
     * Creates a new skill and adds it to the organization.
     *
     * @param name the name of the skill
     * @return an optional containing the created skill, or empty if creation fails
     */
    public Optional<Skill> createSkill(String name) {
        Skill skill = new Skill(name);
        if (addSkill(skill)) {
            return Optional.of(skill);
        }
        return Optional.empty();
    }

    /**
     * Adds a new Skill to the repository.
     *
     * @param skill The Skill to add.
     * @return An Optional containing the added Skill if successful, empty otherwise.
     */
    public boolean addSkill(Skill skill) {
        if (validateSkill(skill)) {
            return skills.add(skill.clone());
        }
        return false;
    }


    /**
     * Validates if a Skill can be added to the repository.
     *
     * @param skill The Skill to validate.
     * @return true if the Skill is valid and can be added, false otherwise.
     */
    private boolean validateSkill(Skill skill) {
        return !skills.contains(skill);
    }

    /**
     * Parses a string containing a list of skills separated by semicolons and creates a list of Skill objects.
     * @param inputSkills The string containing the skills to be parsed
     * @return A list of Skill objects parsed from the input string
     */
    public List<Skill> parseSkills(String inputSkills) {
        String[] skillsArray = inputSkills.split(";");
        List<Skill> skillsList = new ArrayList<>();
        for (String skillName : skillsArray) {
            skillsList.add(new Skill(skillName.trim()));
        }
        return skillsList;
    }

    /**
     * Returns a defensive (immutable) copy of the list of skills.
     *
     * @return A copy of the list of skills.
     */
    public List<Skill> getSkillList() {
        return List.copyOf(skills);
    }
}