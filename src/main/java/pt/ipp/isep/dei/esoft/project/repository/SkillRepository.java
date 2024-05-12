package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Organization;
import pt.ipp.isep.dei.esoft.project.domain.Skill;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SkillRepository {

    private List<Skill> skills;
    public SkillRepository() {
            this.skills = Organization.getSkillList();
            if (this.skills == null) {
                // Initialize the skills list if it's null
                this.skills = new ArrayList<>();
            }
    }

    /**
     * This method returns an existing Skill by its name.
     *
     * @param name The name of the skill to be created.
     * @return The skill name
     * @throws IllegalArgumentException if the skill does not exist, which should never happen.
     */
    public Skill getSkillByName(String name) {
        for (Skill existingSkill : skills) {
            if (existingSkill.getName().equals(name)) {
                return existingSkill;
            }
        }
        throw new IllegalArgumentException("Skill does not exist.");
    }

    public Optional<Skill> add(Skill skill) {

        Optional<Skill> newSkill = Optional.empty();
        boolean operationSuccess = false;

        if (validateSkill(skill)) {
            newSkill = Optional.of(skill.clone());
            operationSuccess = skills.add(newSkill.get());
        }

        if (!operationSuccess) {
            newSkill = Optional.empty();
        }

        return newSkill;
    }

    private boolean validateSkill(Skill skill) {
        boolean isValid = !skills.contains(skill);
        return isValid;
    }

    /**
     * This method returns a defensive (immutable) copy of the list of skills.
     *
     * @return The list of skills.
     */
    public List<Skill> getSkillList() {
        //This is a defensive copy, so that the repository cannot be modified from the outside.
        return List.copyOf(skills);
    }
}