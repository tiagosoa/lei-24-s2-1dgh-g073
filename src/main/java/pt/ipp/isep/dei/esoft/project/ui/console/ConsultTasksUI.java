package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.ConsultTasksController;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Task;
import pt.ipp.isep.dei.esoft.project.domain.Team;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class ConsultTasksUI implements Runnable {
    private Collaborator collaborator;
    private Team team;
    private LocalDate firstDate;
    private LocalDate secondDate;
    private String status;

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
        if (tasks == null || tasks.isEmpty()) {
            System.out.println("There are currently no tasks assigned to you in the inputted time frame.");
            return;
        }
        System.out.println("Tasks assigned to you:");
        for (Task task : tasks) {
            System.out.println(task.getTitle() + ": " + task.getTaskDescription() + " - " + task.getStartDate() + " - Status: " + task.getStatus());
        }
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
                return null;
            }
        } while (!yesno.equals("yes"));
        String status = null;

        while (!(Objects.equals(status, "Planned") || Objects.equals(status, "Postponed") || Objects.equals(status, "Cancelled") || Objects.equals(status, "Done"))) {
            System.out.println("Please select the task's status:");
            System.out.println("1. Planned");
            System.out.println("2. Postponed");
            System.out.println("3. Cancelled");
            System.out.println("4. Done");
            System.out.print("Enter your choice: ");
            try {
                int type = scanner.nextInt();
                switch (type) {
                    case 1:
                        status = "Planned";
                        break;
                    case 2:
                        status = "Postponed";
                        break;
                    case 3:
                        status = "Cancelled";
                        break;
                    case 4:
                        status = "Done";
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 3.");
                scanner.next(); // Clear the invalid input from the scanner
            }
        }
        return status;
    }
}