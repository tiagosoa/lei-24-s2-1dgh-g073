package pt.ipp.isep.dei.esoft.project.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Represents a Team in the system.
 */
public class Team {

    public List<Collaborator> collaborators;
    private int teamID;

    /**
     * Constructor for Collaborator class.
     *
     * @param collaborators the collaborators that make the team
     * @param teamID        the ID number of the team within the system
     */
    public Team(List<Collaborator> collaborators, int teamID) {
        this.collaborators = new ArrayList<>(collaborators);
        this.teamID = teamID;
    }

    /**
     * Adds a collaborator to the team.
     *
     * @param collaborator the collaborator to be added
     * @return true if the collaborator was added successfully, false otherwise
     */
    public boolean addCollaborator(Collaborator collaborator) {
        if (!collaborators.contains(collaborator)) {
            collaborators.add(collaborator);
            return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Team)) return false;
        Team team = (Team) o;
        return teamID == team.teamID && Objects.equals(collaborators, team.collaborators);
    }

    @Override
    public int hashCode() {
        return Objects.hash(collaborators, teamID);
    }

    public Team clone() {
        return new Team(collaborators, teamID);
    }

    public List<Collaborator> getTeam() {
        return new ArrayList<>(collaborators);
    }

    public int getTeamID() {
        return teamID;
    }

    public String listMembers() {
        StringBuilder sb = new StringBuilder("Team Members:\n");
        for (Collaborator collaborator : collaborators) {
            sb.append(collaborator.getName()).append("\n");
        }
        return sb.toString();
    }
}
