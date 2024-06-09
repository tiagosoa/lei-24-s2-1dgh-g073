package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.RecordTaskController;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Task;
import pt.ipp.isep.dei.esoft.project.domain.Team;
import pt.ipp.isep.dei.esoft.project.ui.console.menu.CollaboratorUI;

import java.util.List;
import java.util.Scanner;

/**
 * User interface for recording task completion by a collaborator.
 */
public class RecordTaskUI implements Runnable {
    private Collaborator collaborator;
    private Team team;
    private Task selectedTask;
    private List<Task> collaboratorTasks;
    private final RecordTaskController controller;

    /**
     * Constructor that initializes the controller for recording tasks.
     */
    public RecordTaskUI() {
        controller = new RecordTaskController();
    }

    /**
     * Executes the task recording process.
     */
    public void run() {
        requestData();
        displayTasks();
        selectTask();
        submitData();
    }

    /**
     * Requests necessary data for the task recording process.
     */
    private void requestData() {
        collaborator = controller.getCollaboratorFromSession();
        team = controller.getCollaboratorTeam(collaborator);
        collaboratorTasks = controller.getTaskRepository().getTasksByTeam(team);
    }

    /**
     * Displays the tasks assigned to the collaborator.
     * If no tasks are assigned, redirects to the collaborator menu.
     */
    private void displayTasks() {
        if (collaboratorTasks == null || collaboratorTasks.isEmpty()) {
            System.out.println("There are no tasks assigned to you with the 'Planned' status:");
            CollaboratorUI collaboratorUI = new CollaboratorUI();
            collaboratorUI.run();
        }
        System.out.println("Tasks assigned to you:");
        for (int i = 0; i < collaboratorTasks.size(); i++) {
            Task task = collaboratorTasks.get(i);
            System.out.println((i + 1) + ". " + task.getTitle() + " - " + task.getStatus());
        }
    }

    /**
     * Allows the collaborator to select a task to mark as done.
     */
    private void selectTask() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Select the task number to mark as done:");
        int taskIndex = scanner.nextInt() - 1;
        if (taskIndex >= 0 && taskIndex < collaboratorTasks.size()) {
            selectedTask = collaboratorTasks.get(taskIndex);
        } else {
            System.out.println("Invalid selection.");
            selectTask();
        }
    }

    /**
     * Submits the selected task as completed.
     */
    private void submitData() {
        if (selectedTask != null) {
            controller.markTaskAsDone(selectedTask);
            System.out.println("Task marked as done successfully.");
        } else {
            System.out.println("No task selected.");
        }
    }
}