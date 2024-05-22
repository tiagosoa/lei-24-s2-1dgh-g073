package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.AddEntryToDoListController;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;
import pt.ipp.isep.dei.esoft.project.repository.GreenSpaceRepository;
import pt.ipp.isep.dei.esoft.project.repository.ToDoListRepository;

import java.util.*;

public class AddEntryToDoListUI implements Runnable {

    private final AddEntryToDoListController controller;
    private String name;

    private String entryTitle, entryDescription, entryUrgency, entryDuration;

    private Scanner scanner;

    private ToDoListRepository toDoListRepository;
    private GreenSpaceRepository greenSpaceRepository;

    public AddEntryToDoListUI() {

        controller = new AddEntryToDoListController();
        this.toDoListRepository = getController().getToDoListRepository();
        this.greenSpaceRepository = getController().getGreenSpaceRepository();
    }

    private AddEntryToDoListController getController() {
        return controller;
    }

    public void run() {
        System.out.println("\n\n--- Add Entry to To Do List ------------------------");

        requestData();

        submitData();
    }

    private void submitData() {
        Optional<Collaborator> collaborator = getController().registerCollaborator(name);

        if (GreenSpace.isPresent()) {
            GreenSpace greenSpacenname = assignGreenSpaceToDoList(collaborator.get());
            System.out.println("'" + name + "'" + "- is this data correct? (type 'yes' or 'no')");
            String yesno;
            do {
                yesno = scanner.nextLine();
                if (yesno.equals("no")) {
                    requestData();
                }
            } while (!(yesno.equals("no") || yesno.equals("yes")));
            collaboratorRepository.addCollaborator(collaborator.get());
            System.out.println("\nCollaborator successfully registered!");
        } else {
            System.out.println("\nCollaborator not registered!");
        }
    }

    private void requestData() {

        entryTitle = requestEntryTitle();
        entryDescription = requestEntryDescription();
        entryUrgency = requestEntryUrgency();
        entryDuration = requestEntryDuration();
        associateGreenSpace();
    }


    // Request collaborator name from the user
    private String requestEntryTitle() {
        System.out.print("Entry Title: ");
        return scanner.nextLine();
    }

    private String requestEntryDescription() {
        System.out.print("Entry Description: ");
        return scanner.nextLine();
    }

    private String requestEntryUrgency() {
        String urgency = null;

        while (!(Objects.equals(urgency, "High") || Objects.equals(urgency, "Medium") || Objects.equals(urgency, "Low"))) {
            System.out.println("Please select the status of urgency:");
            System.out.println("1. High");
            System.out.println("2. Medium");
            System.out.println("3. Low");
            System.out.print("Enter your choice: ");
            try {
                int type = scanner.nextInt();
                switch (type) {
                    case 1:
                        urgency = "High";
                        break;
                    case 2:
                        urgency = "Medium";
                        break;
                    case 3:
                        urgency = "Low";
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch(InputMismatchException e){
                System.out.println("Invalid input. Please enter a number between 1 and 3.");
                scanner.next(); // Clear the invalid input from the scanner
            }
        }
        return urgency;
    }


    private String requestEntryDuration() {
        System.out.print("Entry Approximate Duration: ");
        return scanner.nextLine();
    }
    private GreenSpace associateGreenSpace() {
        System.out.println("Select a green space for the new entry:");
        List<GreenSpace> greenSpaces = greenSpaceRepository.getGreenSpaceList();

        for (int i = 0; i < greenSpaces.size(); i++) {
            System.out.println((i + 1) + ". " + greenSpaces.get(i).getName());
        }

        int selectedIndex = readInput(1, greenSpaces.size()) - 1;
        return greenSpaces.get(selectedIndex);
    }

    private int readInput(int min, int max) {
        int input;
        do {
            System.out.print("Select the green space by entering a number between " + min + " and " + max + ": ");
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next();
            }
            input = scanner.nextInt();
        } while (input < min || input > max);
        scanner.nextLine(); // Consume newline character
        return input;
    }
}
