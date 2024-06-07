package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Team;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class TeamRepositoryTest {

    private TeamRepository teamRepository;
    private Collaborator collaborator1;
    private Collaborator collaborator2;
    private Team team;

    @BeforeEach
    void setUp() {
        teamRepository = new TeamRepository();

        // Setup collaborators
        collaborator1 = new Collaborator("Alfredo", "01-10-1964", "01-01-2019", "Porto", 923456789, "alfredo64@gmail.pt", 123456789,"CC", 123456789);
        collaborator2 = new Collaborator("Alberto", "04-01-1974", "02-11-2022", "Gaia", 913456788, "albertoogrande@gmail.pt", 278946139,"CC", 234516799);

        // Setup team
        List<Collaborator> collaborators = new ArrayList<>();
        collaborators.add(collaborator1);
        team = new Team(collaborators, 1);
    }

    @Test
    void ensureGetTeamByIDWorks() {
        teamRepository.addTeam(team);
        Team retrievedTeam = teamRepository.getTeamByID(1);
        assertEquals(team, retrievedTeam);
    }

    @Test
    void ensureGetTeamByIDThrowsExceptionWhenTeamDoesNotExist() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            teamRepository.getTeamByID(999);
        });
        assertEquals("Team does not exist.", exception.getMessage());
    }

    @Test
    void ensureGetTeamByCollaboratorWorks() {
        teamRepository.addTeam(team);
        Team retrievedTeam = teamRepository.getTeamByCollaborator(collaborator1);
        assertEquals(team, retrievedTeam);
    }

    @Test
    void ensureGetTeamByCollaboratorThrowsExceptionWhenCollaboratorIsNotPartOfAnyTeam() {
        Collaborator nonMember = new Collaborator("NonMember", "01-01-1980", "01-01-2020", "Lisbon", 912345678, "nonmember@gmail.com", 987654321, "CC", 987654321);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            teamRepository.getTeamByCollaborator(nonMember);
        });
        assertEquals("Collaborator is not part of any team.", exception.getMessage());
    }

    @Test
    void ensureCreateTeamWorks() {
        List<Collaborator> collaborators = new ArrayList<>();
        collaborators.add(collaborator1);
        collaborators.add(collaborator2);

        Optional<Team> createdTeam = teamRepository.createTeam(collaborators, 2);
        assertTrue(createdTeam.isPresent());
        assertEquals(2, createdTeam.get().getTeamID());
    }

    @Test
    void ensureCreateTeamFailsIfDuplicate() {
        teamRepository.addTeam(team);

        List<Collaborator> newCollaborators = new ArrayList<>();
        newCollaborators.add(collaborator1);
        newCollaborators.add(collaborator2);

        Optional<Team> createdTeam = teamRepository.createTeam(newCollaborators, 1);

        assertFalse(createdTeam.isPresent());

        Team retrievedTeam = teamRepository.getTeamByID(1);
        assertEquals(team, retrievedTeam);
    }

    @Test
    void ensureAddTeamWorks() {
        boolean added = teamRepository.addTeam(team);
        assertTrue(added);
        assertEquals(1, teamRepository.getTeamList().size());
    }

    @Test
    void ensureAddTeamFailsIfDuplicate() {
        teamRepository.addTeam(team);
        boolean addedAgain = teamRepository.addTeam(team);
        assertFalse(addedAgain);
        assertEquals(1, teamRepository.getTeamList().size());
    }

    @Test
    void ensureAddWorks() {
        Optional<Team> addedTeam = teamRepository.add(team);
        assertTrue(addedTeam.isPresent());
        assertEquals(team, addedTeam.get());
        assertEquals(1, teamRepository.getTeamList().size());
    }

    @Test
    void ensureAddFailsIfDuplicate() {
        teamRepository.add(team);
        Optional<Team> addedTeamAgain = teamRepository.add(team);
        assertFalse(addedTeamAgain.isPresent());
        assertEquals(1, teamRepository.getTeamList().size());
    }

    @Test
    void ensureGetTeamListWorks() {
        teamRepository.addTeam(team);
        List<Team> teamList = teamRepository.getTeamList();
        assertEquals(1, teamList.size());
        assertEquals(team, teamList.get(0));
    }

    @Test
    void ensureListMembersWorks() {
        String membersList = team.listMembers();
        assertTrue(membersList.contains("Alfredo"));
        assertFalse(membersList.contains("Alberto"));
    }
}
