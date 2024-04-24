package pt.ipp.isep.dei.esoft.project.domain;

import java.util.Objects;

public class Skill {
    private final String name;

    private HRM hrm;

    public Skill(String name, HRM hrm) {

        validateName(name);
        this.name = name;
        this.hrm = this.hrm;
    }

    private void validateName(String name) {
        //TODO: missing from the diagrams
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Skill name cannot be null or empty.");
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
        Skill task = (Skill) o;
        return name.equals(task.name) && hrm.equals(task.hrm);
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
}