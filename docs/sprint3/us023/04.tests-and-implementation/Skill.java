package pt.ipp.isep.dei.esoft.project.domain;

import java.util.Objects;

/**
 * Represents a skill that a collaborator possesses.
 */
public class Skill {
    private final String name;

    /**
     * Creates a new Skill with the given name.
     *
     * @param name the name of the skill
     */
    public Skill(String name) {
        validateSkill(name);
        this.name = name;
    }

    private void validateSkill(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Skill name cannot be null or empty.");
        } else if (!name.matches("[A-Za-z ]+")) {
            throw new IllegalArgumentException("Skill name cannot contain special characters or digits.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Skill)) {
            return false;
        }
        Skill skill = (Skill) o;
        return Objects.equals(name, skill.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    /**
     * Creates a clone of the current Skill instance.
     *
     * @return A clone of the current instance.
     */
    public Skill clone() {
        return new Skill(this.name);
    }

    /**
     * Gets the name of the skill.
     *
     * @return the name of the skill
     */
    public String getName() {
        return name;
    }

    /**
     * Adds the skill to a collaborator.
     *
     * @param collaborator the collaborator to add the skill to
     */
    public void addCollaborator(Collaborator collaborator) {
        collaborator.addSkill(this);
    }
}