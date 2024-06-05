package pt.ipp.isep.dei.esoft.project.domain;

import java.time.LocalDate;
import java.util.Objects;

public class Task {
    private final String title, taskDescription, urgency, duration;
    private String status; // Change to non-final
    public Team associatedTeam;
    private LocalDate startDate;

    public Task(String title, String taskDescription, String urgency, String duration, String status, LocalDate startDate, Team associatedTeam) {
        this.title = title;
        this.taskDescription = taskDescription;
        this.urgency = urgency;
        this.duration = duration;
        this.status = status;
        this.startDate = startDate;
        this.associatedTeam = associatedTeam;
    }

    public LocalDate getStartDate() {
        return startDate;
    }
    public Team getAssociatedTeam() {
        return associatedTeam;
    }
    public String getTitle() {
        return title;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public String getUrgency() {
        return urgency;
    }

    public String getDuration() {
        return duration;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Task)) return false;
        Task that = (Task) o;
        return title.equals(that.title) && taskDescription.equals(that.taskDescription) && urgency.equals(that.urgency) && duration.equals(that.duration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, taskDescription, urgency, duration, startDate, associatedTeam);
    }

    public Task clone() {
        return new Task(title, taskDescription, urgency, duration, status, startDate, associatedTeam);
    }
}
