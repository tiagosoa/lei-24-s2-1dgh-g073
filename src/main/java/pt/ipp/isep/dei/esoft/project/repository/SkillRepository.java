package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Organization;
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
        this.skills = Organization.getSkillList();
        if (this.skills == null) {
            this.skills = new ArrayList<>();
        }
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
     * Adds a new Skill to the repository.
     *
     * @param skill The Skill to add.
     * @return An Optional containing the added Skill if successful, empty otherwise.
     */
    public Optional<Skill> add(Skill skill) {
        Optional<Skill> newSkill = Optional.empty();
        boolean operationSuccess = false;

        if (validateSkill(skill)) {
            newSkill = Optional.of(skill.clone());
            operationSuccess = skills.add(newSkill.get());//
        }

        if (!operationSuccess) {
            newSkill = Optional.empty();
        }

        return newSkill;
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
     * Returns a defensive (immutable) copy of the list of skills.
     *
     * @return A copy of the list of skills.
     */
    public List<Skill> getSkillList() {
        return List.copyOf(skills);
    }
}