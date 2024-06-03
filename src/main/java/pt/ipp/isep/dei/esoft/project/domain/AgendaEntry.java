package pt.ipp.isep.dei.esoft.project.domain;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.ArrayList;

public class AgendaEntry {
    private final String title, taskDescription, urgency, duration;
    private String status; // Change to non-final
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
        this.associatedVehicles = new ArrayList<>();
    }

    public boolean addGreenSpace(GreenSpace greenSpace) {
        if (associatedGreenSpace == null) {
            this.associatedGreenSpace = greenSpace;
            return true;
        }
        return false;
    }

    public boolean addTeam(Team team) {
        if (associatedTeam == null) {
            this.associatedTeam = team;
            return true;
        } else {
            System.out.println("There is already a team associated to this entry.");
        }
        return false;
    }

    public void setDeadline(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getStartDate() {
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

    public String getStatus() {
        return status;
    }

    public void cancel() {
        this.status = "canceled";
    }
    public void assignVehicle(Vehicle vehicle) {
        if (!associatedVehicles.contains(vehicle)) {
            associatedVehicles.add(vehicle);
        }
    }

    public List<Vehicle> getAssociatedVehicles() {
        return associatedVehicles;
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
