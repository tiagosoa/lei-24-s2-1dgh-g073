package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.AgendaEntry;
import pt.ipp.isep.dei.esoft.project.domain.Task;
import pt.ipp.isep.dei.esoft.project.domain.Team;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Repository class for managing tasks.
 */
public class TaskRepository {
    private List<Task> tasks;

    /**
     * Constructor for TaskRepository.
     */
    public TaskRepository() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Add a task based on an agenda entry.
     *
     * @param entry the agenda entry to create the task from
     */
    public void addTask(AgendaEntry entry) {
        Task task = new Task(entry.getTitle(), entry.getTaskDescription(), entry.getUrgency(), entry.getDuration(), entry.getStatus(), entry.getStartDate(), entry.getAssociatedTeam());
        tasks.add(task);
    }

    /**
     * Assign a team to a task.
     *
     * @param entry the agenda entry to match with the task
     * @param team  the team to assign to the task
     */
    public void assignTeam(AgendaEntry entry, Team team) {
        for (Task task : tasks) {
            if (entry.getTitle().equals(task.getTitle()) && entry.getStartDate().equals(task.getStartDate()) && entry.getStatus().equals(task.getStatus())) {
                task.setAssociatedTeam(team);
                break;
            }
        }
    }

    /**
     * Get a copy of all tasks.
     *
     * @return a list of all tasks
     */
    public List<Task> getTasks() {
        return new ArrayList<>(tasks);
    }

    /**
     * Get tasks by team and within a date range.
     *
     * @param team      the team to filter tasks by
     * @param startDate the start date of the range
     * @param endDate   the end date of the range
     * @return a list of tasks that match the criteria
     */
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

    /**
     * Filter tasks by status.
     *
     * @param tasks  the list of tasks to filter
     * @param status the status to filter by
     * @return a list of tasks that match the status
     */
    public List<Task> filterTasksByStatus(List<Task> tasks, String status) {
        return tasks.stream()
                .filter(task -> task.getStatus().equalsIgnoreCase(status))
                .collect(Collectors.toList());
    }

    /**
     * Get tasks by team that are in the "Planned" status.
     *
     * @param team the team to filter tasks by
     * @return a list of planned tasks for the team
     */
    public List<Task> getTasksByTeam(Team team) {
        List<Task> teamTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getAssociatedTeam() != null && task.getAssociatedTeam().equals(team) && "Planned".equals(task.getStatus())) {
                teamTasks.add(task);
            }
        }
        return teamTasks;
    }

    /**
     * Update a task in the repository.
     *
     * @param task the task to update
     */
    public void updateTask(Task task) {
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).equals(task)) {
                tasks.set(i, task);
                return;
            }
        }
    }

    public void updateTaskDate(AgendaEntry agendaEntry) {
        for (Task task : tasks) {
            if (task.getTitle().equals(agendaEntry.getTitle()) && task.getTaskDescription().equals(agendaEntry.getTaskDescription()) && task.getDuration().equals(agendaEntry.getDuration()) && task.getUrgency().equals(agendaEntry.getUrgency())) {
                task.getStartDate().equals(agendaEntry.getStartDate());
                task.setStatus("Postponed");
                return;
            }
        }
    }
}