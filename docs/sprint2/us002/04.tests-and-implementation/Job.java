package pt.ipp.isep.dei.esoft.project.domain;

import java.util.Objects;

/**
 * Represents a job that can be assigned to a collaborator.
 */
public class Job {
    private final String name;
    private Collaborator collaborator;

    /**
     * Creates a new Job with the given name.
     *
     * @param name the name of the job
     */
    public Job(String name) {
        validateJob(name);
        this.name = name;
    }

    /**
     * Validates the job name to ensure it is not null, empty, or contains special characters or digits.
     *
     * @param name the name of the job to validate
     * @throws IllegalArgumentException if the name is null, empty, or contains special characters or digits
     */
    private void validateJob(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Job name cannot be null or empty.");
        } else if (!name.matches("[a-zA-Z ]+")) {
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
        Job job = (Job) o;
        return name.equals(job.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    /**
     * Creates a copy of the Job with the same name.
     *
     * @return a new Job instance with the same name
     */
    public Job clone() {
        return new Job(this.name);
    }

    /**
     * Gets the name of the job.
     *
     * @return the name of the job
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the collaborator assigned to the job.
     *
     * @return the collaborator assigned to the job
     */
    public Collaborator getCollaborator() {
        return collaborator;
    }

    /**
     * Adds a collaborator to the job and assigns this job to the collaborator.
     *
     * @param collaborator the collaborator to add to the job
     */
    public void addCollaborator(Collaborator collaborator) {
        collaborator.addJob(this);
    }

    /**
     * Sets the collaborator assigned to the job.
     *
     * @param collaborator the collaborator to assign to the job
     */
    public void setCollaborator(Collaborator collaborator) {
        this.collaborator = collaborator;
    }
}