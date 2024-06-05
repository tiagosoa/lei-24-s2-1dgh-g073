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

    public void assignTeam(AgendaEntry entry, Team team){
        for (Task task : tasks) {
            if (entry.getTitle().equals(task.getTitle()) && entry.getStartDate().equals(task.getStartDate()) && entry.getStatus().equals(task.getStatus())) {
                task.associatedTeam = team;
                break;
            }
        }
    }

    public List<Task> getTasks() {
        return new ArrayList<>(tasks);
    }
    /**
     * Retrieves a list of tasks assigned to a specific team within a given date range.
     *
     * @param team The team to retrieve tasks for.
     * @param startDate The start date of the range.
     * @param endDate The end date of the range.
     * @return A list of tasks assigned to the team within the date range.
     */
    public List<Task> getTasksByTeamAndDateRange(Team team, LocalDate startDate, LocalDate endDate) {
        return tasks.stream()
                .filter(task -> task.getAssociatedTeam().equals(team) &&
                        (task.getStartDate().isEqual(startDate) || task.getStartDate().isAfter(startDate)) &&
                        (task.getStartDate().isEqual(endDate) || task.getStartDate().isBefore(endDate)))
                .collect(Collectors.toList());
    }

    /**
     * Filters a list of tasks by their status.
     *
     * @param tasks The list of tasks to filter.
     * @param status The status to filter by.
     * @return A list of tasks that match the specified status.
     */
    public List<Task> filterTasksByStatus(List<Task> tasks, String status) {
        return tasks.stream()
                .filter(task -> task.getStatus().equalsIgnoreCase(status))
                .collect(Collectors.toList());
    }
}





