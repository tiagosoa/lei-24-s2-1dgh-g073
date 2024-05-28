package pt.ipp.isep.dei.esoft.project.domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class AgendaEntry {

    private final String title, taskDescription, urgency, duration, status;
    private GreenSpace associatedGreenSpace;
    private Team associatedTeam;
    private List<Vehicle> associatedVehicles;
    private LocalDate startDate;

    public AgendaEntry(String title, String taskDescription, String urgency, String duration, String status, LocalDate startDate) {
        this.title = title;
        this.taskDescription = taskDescription;
        this.urgency = urgency;
        this.duration = duration;
        this.status = status;
        this.startDate = startDate;
    }

    public boolean addGreenSpace(GreenSpace greenSpace) {
        if (associatedGreenSpace == null) {
            this.associatedGreenSpace = greenSpace;
            return true;
        }
        return false;
    }

    public void setDeadline(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getDeadline() {
        return startDate;
    }

    public GreenSpace getAssociatedGreenSpace() {
        return associatedGreenSpace;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AgendaEntry)) return false;
        AgendaEntry that = (AgendaEntry) o;
        return title.equals(that.title) && taskDescription.equals(that.taskDescription) && urgency.equals(that.urgency) && duration.equals(that.duration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, taskDescription, urgency, duration);
    }

    public AgendaEntry clone() {
        return new AgendaEntry(title, taskDescription, urgency, duration, status, startDate);
    }
}
