package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.AgendaEntry;
import pt.ipp.isep.dei.esoft.project.domain.Task;
import pt.ipp.isep.dei.esoft.project.domain.Team;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TaskRepository {
    private List<Task> tasks;

    public TaskRepository() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(AgendaEntry entry) {
        Task task = new Task(entry.getTitle(), entry.getTaskDescription(), entry.getUrgency(), entry.getDuration(), entry.getStatus(), entry.getStartDate(), entry.getAssociatedTeam());
        tasks.add(task);
    }

    public void assignTeam(AgendaEntry entry, Team team) {
        for (Task task : tasks) {
            if (entry.getTitle().equals(task.getTitle()) && entry.getStartDate().equals(task.getStartDate()) && entry.getStatus().equals(task.getStatus())) {
                task.setAssociatedTeam(team);
                break;
            }
        }
    }

    public List<Task> getTasks() {
        return new ArrayList<>(tasks);
    }

    public List<Task> getTasksByTeamAndDateRange(Team team, LocalDate startDate, LocalDate endDate) {
        List<Task> result = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getAssociatedTeam() != null && task.getAssociatedTeam().equals(team) &&
                    (task.getStartDate().isEqual(startDate) || task.getStartDate().isAfter(startDate)) &&
                    (task.getStartDate().isEqual(endDate) || task.getStartDate().isBefore(endDate))) {
                result.add(task);
            }
        }
        return result;
    }

    public List<Task> filterTasksByStatus(List<Task> tasks, String status) {
        return tasks.stream()
                .filter(task -> task.getStatus().equalsIgnoreCase(status))
                .collect(Collectors.toList());
    }

    public List<Task> getTasksByTeam(Team team) {
        List<Task> teamTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getAssociatedTeam() != null && task.getAssociatedTeam().equals(team) && "Planned".equals(task.getStatus())) {
                teamTasks.add(task);
            }
        }
        return teamTasks;
    }

    public void updateTask(Task task) {
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).equals(task)) {
                tasks.set(i, task);
                return;
            }
        }
    }
}
