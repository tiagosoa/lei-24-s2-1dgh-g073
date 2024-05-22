package pt.ipp.isep.dei.esoft.project.domain;

import java.util.Objects;

public class Entry {

    private String title, taskDescription, urgency, duration;

    private GreenSpace associatedGreenSpace;

    public Entry(String title, String taskDescription, String urgency, String duration) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Entry)) return false;
        Entry that = (Entry) o;
        return title.equals(that.title) && taskDescription.equals(that.taskDescription) && urgency.equals(that.urgency) && duration.equals(that.duration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, taskDescription, urgency, duration);
    }

    public Entry clone() {
        return new Entry(title, taskDescription, urgency, duration);
    }
}
