package pt.ipp.isep.dei.esoft.project.domain;

import java.util.Objects;

public class Job {
    private final String name;

    private HRM hrm;

    public Job(String name, HRM hrm) {

        validateJob(name);
        this.name = name;
        this.hrm = hrm;
    }

    private void validateJob(String name) {
        //TODO: missing from the diagrams
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Job name cannot be null or empty.");

        } else if (!name.matches("[a-zA-Z]+")) {
            throw new IllegalArgumentException("Job name cannot contain special characters or digits.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Job)) {
            return false;
        }
        Job skill = (Job) o;
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
    public Job clone() {
        return new Job(this.name, this.hrm);
    }

    public String getName() {
        return name;
    }
}