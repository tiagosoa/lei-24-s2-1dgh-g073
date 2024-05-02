package pt.ipp.isep.dei.esoft.project.domain;

import java.util.Objects;

public class Skill {
    private final String name;

    private HRM hrm;

    public Skill(String name, HRM hrm) {

        validateSkill(name);
        this.name = name;
        this.hrm = hrm;
    }

    private void validateSkill(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Skill name cannot be null or empty.");
        } else if (!name.matches("[a-zA-Z]+")) {
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
        return name.equals(skill.name) && hrm.equals(skill.hrm);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, hrm);
    }


    /**
     * Clone method.
     *
     * @return A clone of the current instance.
     */
    public Skill clone() {
        return new Skill(this.name, this.hrm);
    }

    public String getName() {
        return name;
    }
}