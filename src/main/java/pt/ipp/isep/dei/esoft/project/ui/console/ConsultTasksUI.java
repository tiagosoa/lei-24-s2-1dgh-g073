package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.ConsultTasksController;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Task;
import pt.ipp.isep.dei.esoft.project.domain.Team;
import pt.ipp.isep.dei.esoft.project.repository.CollaboratorRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class ConsultTasksUI {
    private Collaborator collaborator;
    private Team team;
    private LocalDate firstDate;
    private LocalDate secondDate;
    private String status;
    private CollaboratorRepository collaboratorRepository;

    private final ConsultTasksController controller;

    public ConsultTasksUI() {
        controller = new ConsultTasksController();
    }

    public void run() {
        requestData();
        submitData();
    }

    public void requestData() {
        collaborator = controller.getCollaboratorFromSession();
        team = controller.getCollaboratorTeam(collaborator);
        firstDate = requestFirstDate();
        secondDate = requestSecondDate();
        status = requestStatus();
    }

    public void submitData() {
        List<Task> tasks = controller.getTasksForCollaborator(team, firstDate, secondDate, status);
        displayTasks(tasks);
    }

    private LocalDate requestFirstDate() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the first date (dd-MM-yyyy): ");
        String firstDateStr = scanner.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return LocalDate.parse(firstDateStr, formatter);
    }

    private LocalDate requestSecondDate() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the second date (dd-MM-yyyy): ");
        String secondDateStr = scanner.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return LocalDate.parse(secondDateStr, formatter);
    }

    private String requestStatus() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Do you want to filter by task status? (yes/no)");
        String yesno;
        do {
            yesno = scanner.nextLine();
            if (yesno.equals("no")) {
                submitData();
            }
        } while (!(yesno.equals("no") || yesno.equals("yes")));
        if (yesno.equalsIgnoreCase("yes")) {
            System.out.println("Enter the status (Planned, Postponed, Canceled, Done): ");
            return scanner.nextLine();
        }
        return null;
    }

    private void displayTasks(List<Task> tasks) {
        System.out.println("Tasks assigned to you:");
        for (Task task : tasks) {
            System.out.println(task);
        }
    }
}