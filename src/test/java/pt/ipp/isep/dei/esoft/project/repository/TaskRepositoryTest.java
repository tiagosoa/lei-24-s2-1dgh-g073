package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TaskRepositoryTest {
    private TaskRepository taskRepository;
    private Team team;
    private Task task;

    @BeforeEach
    void setUp() {
        taskRepository = new TaskRepository();

        List<Collaborator> collaborators = new ArrayList<>();
        collaborators.add(new Collaborator("Alfredo", "01-10-1964", "01-01-2019", "Porto", 923456789, "alfredo64@gmail.pt", 123456789,"CC", 123456789));
        collaborators.add(new Collaborator("Alberto", "04-01-1974", "02-11-2022", "Gaia", 913456788, "albertoogrande@gmail.pt", 278946139,"CC", 234516799));
        team = new Team(collaborators, 1);
        task = new Task("Podar árvores", "Podar todas as árvores no parque.", "Medium", "3 weeks", "Planned", LocalDate.now().plusDays(3), team);
    }

    @Test
    void ensureAddTaskWorks() {
        AgendaEntry entry = new AgendaEntry("Podar árvores", "Podar todas as árvores no parque.", "Medium", "3 weeks", "Planned", LocalDate.now().plusDays(3));
        taskRepository.addTask(entry);

        List<Task> tasks = taskRepository.getTasks();
        assertEquals(1, tasks.size());
        assertEquals("Podar árvores", tasks.get(0).getTitle());
    }

    @Test
    void ensureAssignTeamWorks() {
        AgendaEntry entry = new AgendaEntry("Podar árvores", "Podar todas as árvores no parque.", "Medium", "3 weeks", "Planned", LocalDate.now().plusDays(3));
        taskRepository.addTask(entry);

        List<Collaborator> collaborators = new ArrayList<>();
        collaborators.add(new Collaborator("Jonas", "25-09-1994", "08-07-2023", "Rio Tinto", 93761984, "jonas@gmail.pt", 275315949,"CC", 734691829));
        Team newTeam = new Team(collaborators, 2);

        taskRepository.assignTeam(entry, newTeam);

        List<Task> tasks = taskRepository.getTasks();
        assertEquals(newTeam, tasks.get(0).getAssociatedTeam());
    }

    @Test
    void ensureGetTasksWorks() {
        AgendaEntry entry1 = new AgendaEntry("Podar árvores", "Podar todas as árvores no parque.", "Medium", "3 weeks", "Planned", LocalDate.now().plusDays(3));
        AgendaEntry entry2 = new AgendaEntry("Cortar relva", "Cortar a relva do parque", "Low", "2 weeks", "Postponed", LocalDate.now().plusDays(4));
        taskRepository.addTask(entry1);
        taskRepository.addTask(entry2);

        List<Task> tasks = taskRepository.getTasks();
        assertEquals(2, tasks.size());
    }

    @Test
    void ensureGetTasksByTeamAndDateRangeWorks() {
        AgendaEntry entry1 = new AgendaEntry("Podar árvores", "Podar todas as árvores no parque.", "Medium", "3 weeks", "Planned", LocalDate.now().plusDays(3));
        AgendaEntry entry2 = new AgendaEntry("Cortar relva", "Cortar a relva do parque", "Low", "2 weeks", "Postponed", LocalDate.now().plusDays(4));
        taskRepository.addTask(entry1);
        taskRepository.addTask(entry2);
        taskRepository.assignTeam(entry1, team);
        taskRepository.assignTeam(entry2, team);

        LocalDate startDate = LocalDate.now().plusDays(2);
        LocalDate endDate = LocalDate.now().plusDays(5);
        List<Task> tasksInRange = taskRepository.getTasksByTeamAndDateRange(team, startDate, endDate);

        assertEquals(2, tasksInRange.size());
        assertTrue(tasksInRange.stream().anyMatch(task -> task.getTitle().equals("Podar árvores")));
        assertTrue(tasksInRange.stream().anyMatch(task -> task.getTitle().equals("Cortar relva")));
    }

    @Test
    void ensureFilterTasksByStatusWorks() {
        AgendaEntry entry1 = new AgendaEntry("Podar árvores", "Podar todas as árvores no parque.", "Medium", "3 weeks", "Planned", LocalDate.now().plusDays(3));
        AgendaEntry entry2 = new AgendaEntry("Cortar relva", "Cortar a relva do parque", "Low", "2 weeks", "Postponed", LocalDate.now().plusDays(4));
        taskRepository.addTask(entry1);
        taskRepository.addTask(entry2);

        List<Task> tasks = taskRepository.filterTasksByStatus(taskRepository.getTasks(), "Planned");
        assertEquals(1, tasks.size());
        assertEquals("Podar árvores", tasks.get(0).getTitle());
    }

    @Test
    void ensureGetTasksByTeamWorks() {
        AgendaEntry entry1 = new AgendaEntry("Podar árvores", "Podar todas as árvores no parque.", "Medium", "3 weeks", "Planned", LocalDate.now().plusDays(3));
        AgendaEntry entry2 = new AgendaEntry("Cortar relva", "Cortar a relva do parque", "Low", "2 weeks", "Postponed", LocalDate.now().plusDays(4));
        taskRepository.addTask(entry1);
        taskRepository.addTask(entry2);
        taskRepository.assignTeam(entry1, team);
        taskRepository.assignTeam(entry2, team);

        List<Task> tasksByTeam = taskRepository.getTasksByTeam(team);
        assertEquals(1, tasksByTeam.size());
        assertEquals("Podar árvores", tasksByTeam.get(0).getTitle());
    }

    @Test
    void ensureUpdateTaskWorks() {
        taskRepository.addTask(new AgendaEntry("Podar árvores", "Podar todas as árvores no parque.", "Medium", "3 weeks", "Planned", LocalDate.now().plusDays(3)));

        Task updatedTask = new Task("Podar árvores", "Podar todas as árvores no parque.", "Medium", "3 weeks", "Postponed", LocalDate.now().plusDays(7), team);
        taskRepository.updateTask(updatedTask);

        List<Task> tasks = taskRepository.getTasks();
        assertEquals(1, tasks.size());
        assertEquals("Postponed", tasks.get(0).getStatus());
    }
}
