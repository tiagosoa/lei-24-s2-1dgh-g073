package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.domain.HRM;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SkillRepository {

    private final List<Skill> skills;
    public SkillRepository() {
        skills = new ArrayList<>();
    }

    /**
     * This method returns an exsiting Skill by its name.
     *
     * @param name The name of the skill category to be created.
     * @return The skill name
     * @throws IllegalArgumentException if the task category does not exist, which should never happen.
     */
    public Skill getSkillByName(String name, HRM hrm) {
        Skill newSkill = new Skill(name, hrm);
        Skill skill = null;
        if (skills.contains(newSkill)) {
            skill = skills.get(skills.indexOf(newSkill));
        }
        if (skill == null) {
            throw new IllegalArgumentException(
                    "Skill name requested for [" + name + "] does not exist.");
        }
        return skill;
    }

    public Optional<Skill> add(Skill skill) {

        Optional<Skill> newTaskCategory = Optional.empty();
        boolean operationSuccess = false;

        if (validateSkill(skill)) {
            newTaskCategory = Optional.of(skill.clone());
            operationSuccess = skills.add(newTaskCategory.get());
        }

        if (!operationSuccess) {
            newTaskCategory = Optional.empty();
        }

        return newTaskCategory;
    }

    private boolean validateSkill(Skill skill) {
        boolean isValid = !skills.contains(skill);
        return isValid;
    }

    /**
     * This method returns a defensive (immutable) copy of the list of task categories.
     *
     * @return The list of task categories.
     */
    public List<Skill> getSkills() {
        //This is a defensive copy, so that the repository cannot be modified from the outside.
        return List.copyOf(skills);
    }
}