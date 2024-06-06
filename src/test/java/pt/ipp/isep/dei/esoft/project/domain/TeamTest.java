package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TeamTest {
    private Team team;
    private Collaborator collaborator1;
    private Collaborator collaborator2;

    @BeforeEach
    public void setUp() {
        collaborator1 = new Collaborator("Alfredo", "01-10-1964", "01-01-2019", "Porto", 923456789, "alfredo64@gmail.pt", 123456789,"CC", 123456789);
        collaborator2 = new Collaborator("Alberto", "04-01-1974", "02-11-2022", "Gaia", 913456788, "albertoogrande@gmail.pt", 278946139,"CC", 234516799);
        List<Collaborator> collaborators = new ArrayList<>();
        collaborators.add(collaborator1);

        team = new Team(collaborators, 1);
    }

    @Test
    public void ensureAddCollaboratorWorks() {
        assertTrue(team.addCollaborator(collaborator2));
        assertEquals(2, team.getTeam().size());
        assertTrue(team.getTeam().contains(collaborator2));

        assertFalse(team.addCollaborator(collaborator2));
        assertEquals(2, team.getTeam().size());
    }

    @Test
    public void ensureEqualsWorks() {
        List<Collaborator> collaborators = new ArrayList<>();
        collaborators.add(collaborator1);
        Team sameTeam = new Team(collaborators, 1);

        List<Collaborator> differentCollaborators = new ArrayList<>();
        differentCollaborators.add(collaborator2);
        Team differentTeam = new Team(differentCollaborators, 2);

        assertEquals(team, sameTeam);
        assertNotEquals(team, differentTeam);
        assertNotEquals(team, null);
        assertNotEquals(team, new Object());
    }

    @Test
    public void ensureHashCodeWorks() {
        List<Collaborator> collaborators = new ArrayList<>();
        collaborators.add(collaborator1);
        Team sameTeam = new Team(collaborators, 1);

        assertEquals(team.hashCode(), sameTeam.hashCode());

        List<Collaborator> differentCollaborators = new ArrayList<>();
        differentCollaborators.add(collaborator2);
        Team differentTeam = new Team(differentCollaborators, 2);

        assertNotEquals(team.hashCode(), differentTeam.hashCode());
    }

    @Test
    public void ensureCloneWorks() {
        Team clonedTeam = team.clone();
        assertEquals(team, clonedTeam);
        assertNotSame(team, clonedTeam);
    }

    @Test
    public void ensureGetTeamWorks() {
        List<Collaborator> collaborators = team.getTeam();
        assertEquals(1, collaborators.size());
        assertTrue(collaborators.contains(collaborator1));
    }

    @Test
    public void ensureGetTeamIDWorks() {
        assertEquals(1, team.getTeamID());
    }

    @Test
    public void ensureListMembersWorks() {
        String expectedList = "Team Members:\nAlfredo\n";
        assertEquals(expectedList, team.listMembers());

        team.addCollaborator(collaborator2);
        expectedList = "Team Members:\nAlfredo\nAlberto\n";
        assertEquals(expectedList, team.listMembers());
    }
}
