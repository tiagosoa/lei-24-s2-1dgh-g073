package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TaskTest {
    private Task task;
    private Team team;

    @BeforeEach
    public void setUp() {
        List<Collaborator> collaborators = new ArrayList<>();
        collaborators.add(new Collaborator("Alfredo", "01-10-1964", "01-01-2019", "Porto", 923456789, "alfredo64@gmail.pt", 123456789,"CC", 123456789));
        collaborators.add(new Collaborator("Alberto", "04-01-1974", "02-11-2022", "Gaia", 913456788, "albertoogrande@gmail.pt", 278946139,"CC", 234516799));
        team = new Team(collaborators, 1);
        task = new Task("Podar árvores", "Podar todas as árvores no parque.", "Medium", "3 weeks", "Planned", LocalDate.now().plusDays(3), team);
    }

    @Test
    public void ensureGetStartDateWorks() {
        assertEquals(LocalDate.now(), task.getStartDate());
    }

    @Test
    public void ensureGetAssociatedTeamWorks() {
        assertEquals(team, task.getAssociatedTeam());
    }

    @Test
    public void ensureGetTitleWorks() {
        assertEquals("Podar árvores", task.getTitle());
    }

    @Test
    public void ensureGetTaskDescriptionWorks() {
        assertEquals("Podar todas as árvores no parque.", task.getTaskDescription());
    }

    @Test
    public void ensureGetUrgencyWorks() {
        assertEquals("Medium", task.getUrgency());
    }

    @Test
    public void ensureGetDurationWorks() {
        assertEquals("3 weeks", task.getDuration());
    }

    @Test
    public void ensureGetStatusWorks() {
        assertEquals("Planned", task.getStatus());
    }

    @Test
    public void ensureSetStatusWorks() {
        task.setStatus("Postponed");
        assertEquals("Postponed", task.getStatus());
    }

    @Test
    public void ensureEqualsWorks() {
        Task sameTask = new Task("Podar árvores", "Podar todas as árvores no parque.", "Medium", "3 weeks", "Planned", LocalDate.now().plusDays(3), team);
        Task differentTask = new Task("Cortar relva", "Cortar a relva do parque", "Low", "2 weeks", "Postponed", LocalDate.now().plusDays(4), team);

        assertEquals(task, sameTask);
        assertNotEquals(task, differentTask);
        assertNotEquals(task, null);
        assertNotEquals(task, new Object());
    }

    @Test
    public void ensureHashCodeWorks() {
        Task sameTask = new Task("Podar árvores", "Podar todas as árvores no parque.", "Medium", "3 weeks", "Planned", LocalDate.now().plusDays(3), team);
        assertEquals(task.hashCode(), sameTask.hashCode());

        Task differentTask = new Task("Cortar relva", "Cortar a relva do parque", "Low", "2 weeks", "Postponed", LocalDate.now().plusDays(4), team);
        assertNotEquals(task.hashCode(), differentTask.hashCode());
    }

    @Test
    public void ensureCloneWorks() {
        Task clonedTask = task.clone();
        assertEquals(task, clonedTask);
        assertNotSame(task, clonedTask);
    }
}
