package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.RecordTaskController;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Task;
import pt.ipp.isep.dei.esoft.project.domain.Team;
import pt.ipp.isep.dei.esoft.project.ui.console.menu.CollaboratorUI;

import java.util.List;
import java.util.Scanner;

public class RecordTaskUI implements Runnable{
    private Collaborator collaborator;
    private Team team;
    private Task selectedTask;
    private List<Task> collaboratorTasks;
    private final RecordTaskController controller;

    public RecordTaskUI() {
        controller = new RecordTaskController();
    }

    public void run() {
        requestData();
        displayTasks();
        selectTask();
        submitData();
    }

    private void requestData() {
        collaborator = controller.getCollaboratorFromSession();
        team = controller.getCollaboratorTeam(collaborator);
        collaboratorTasks = controller.getTaskRepository().getTasksByTeam(team);
    }

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

    private void submitData() {
        if (selectedTask != null) {
            controller.markTaskAsDone(selectedTask);
            System.out.println("Task marked as done successfully.");
        } else {
            System.out.println("No task selected.");
        }
    }
}
