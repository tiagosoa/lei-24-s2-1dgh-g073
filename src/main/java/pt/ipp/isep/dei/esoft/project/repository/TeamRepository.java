package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Team;
import pt.ipp.isep.dei.esoft.project.domain.Skill;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Repository class for managing Team objects.
 */
public class TeamRepository {

    private List<Team> teams;

    /**
     * Constructor for TeamRepository.
     * Initializes the list of teams.
     */
    public TeamRepository() {
        this.teams = new ArrayList<>();
    }

    /**
     * Retrieves a Team by its ID number.
     *
     * @param IDnumber The ID number of the team to retrieve.
     * @return The Team with the specified ID.
     * @throws IllegalArgumentException if the team does not exist.
     */
    public Team getTeamByID(int IDnumber) {
        for (Team existingTeam : teams) {
            if (existingTeam.getTeamID() == IDnumber) {
                return existingTeam;
            }
        }
        throw new IllegalArgumentException("Team does not exist.");
    }

    /**
     * Retrieves a Team by a Collaborator.
     *
     * @param collaborator The collaborator to find the team for.
     * @return The Team that the collaborator is part of.
     * @throws IllegalArgumentException if the collaborator is not part of any team.
     */
    public Team getTeamByCollaborator(Collaborator collaborator) {
        for (Team team : teams) {
            if (team.getTeam().contains(collaborator)) {
                return team;
            }
        }
        throw new IllegalArgumentException("Collaborator is not part of any team.");
    }

    /**
     * Registers a new team with the organization.
     *
     * @param collaborators the collaborators to add to the team
     * @param teamID the ID number of the team
     * @return an optional containing the registered team, or empty if registration fails
     */
    public Optional<Team> createTeam(List<Collaborator> collaborators, int teamID) {
        Team team = new Team(collaborators, teamID);
        if (addTeam(team)) {
            return Optional.of(team);
        }
        return Optional.empty();
    }

    public boolean addTeam(Team team) {
        if (validateTeam(team)) {
            return teams.add(team.clone());
        }
        return false;
    }

    /**
     * Adds a new team to the list.
     *
     * @param team The team to add.
     * @return An optional containing the added team if successful, empty optional otherwise.
     */
    public Optional<Team> add(Team team) {
        Optional<Team> newTeam = Optional.empty();
        boolean operationSuccess = false;

        if (validateTeam(team)) {
            newTeam = Optional.of(team.clone());
            operationSuccess = teams.add(newTeam.get());
        }

        if (!operationSuccess) {
            newTeam = Optional.empty();
        }

        return newTeam;
    }

    /**
     * Validates a team to avoid duplicates.
     *
     * @param team The team to validate.
     * @return True if the team is valid (not a duplicate).
     */
    private boolean validateTeam(Team team) {
        return !teams.contains(team);
    }

    /**
     * Returns a defensive (immutable) copy of the list of teams.
     *
     * @return The list of teams.
     */
    public List<Team> getTeamList() {
        // This is a defensive copy, so that the repository cannot be modified from the outside.
        return List.copyOf(teams);
    }
}