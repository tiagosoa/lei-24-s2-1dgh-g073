package pt.ipp.isep.dei.esoft.project.domain;

import java.util.Objects;

public class Job {
    private final String name;
    private Collaborator collaborator;

    public Job(String name) {
        validateJob(name);
        this.name = name;
    }

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

    public Job clone() {
        return new Job(this.name);
    }

    public String getName() {
        return name;
    }

    public Collaborator getCollaborator() {
        return collaborator;
    }

    public void addCollaborator(Collaborator collaborator) {
        collaborator.addJob(this);
    }

    public void setCollaborator(Collaborator collaborator) {
        this.collaborator = collaborator;
    }
}