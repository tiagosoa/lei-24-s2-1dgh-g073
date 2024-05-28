package pt.ipp.isep.dei.esoft.project.domain;

import java.util.Date;
import java.util.Objects;

public class TDLEntry {

    private final String title, taskDescription, urgency, duration;
    private GreenSpace associatedGreenSpace;

    public TDLEntry(String title, String taskDescription, String urgency, String duration) {
        this.title = title;
        this.taskDescription = taskDescription;
        this.urgency = urgency;
        this.duration = duration;
    }

    public boolean addGreenSpace(GreenSpace greenSpace) {
        if (associatedGreenSpace == null) {
            this.associatedGreenSpace = greenSpace;
            return true;
        }
        return false;
    }

//    public void setDeadline(Date deadline) {
//        this.deadline = deadline;
//    }

//    public Date getDeadline() {
//        return deadline;
//    }

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
        if (!(o instanceof TDLEntry)) return false;
        TDLEntry that = (TDLEntry) o;
        return title.equals(that.title) && taskDescription.equals(that.taskDescription) && urgency.equals(that.urgency) && duration.equals(that.duration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, taskDescription, urgency, duration);
    }

    public TDLEntry clone() {
        return new TDLEntry(title, taskDescription, urgency, duration);
    }
}
