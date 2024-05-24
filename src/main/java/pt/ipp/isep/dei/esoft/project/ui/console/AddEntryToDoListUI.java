package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.AddEntryToDoListController;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.GreenSpaceRepository;
import pt.ipp.isep.dei.esoft.project.repository.ToDoListRepository;

import java.util.*;

public class AddEntryToDoListUI implements Runnable {

    private final AddEntryToDoListController controller;
    private String entryTitle, entryDescription, entryUrgency, entryDuration;
    private GreenSpace associatedGreenSpace;
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
        Scanner scanner = new Scanner(System.in);
        System.out.println("'" + entryTitle + "'" + "'" + entryDescription + "'" + "'" + entryUrgency + "'" + "'" + entryDuration + "'" + "- is this data correct? (type 'yes' or 'no')");
            String yesno;
            do {
                yesno = scanner.nextLine();
                if (yesno.equals("no")) {
                    requestData();
                }
            } while (!(yesno.equals("no") || yesno.equals("yes")));
            Entry entry = getController().addEntry(entryTitle, entryDescription, entryUrgency, entryDuration);
            if (entry != null) {
                System.out.println("\n Entry successfully added!");
            } else {
                System.out.println("\n Entry not added!");
            }
        }

    private void requestData() {
        associatedGreenSpace = associateGreenSpace();
        entryTitle = requestEntryTitle();
        entryDescription = requestEntryDescription();
        entryUrgency = requestEntryUrgency();
        entryDuration = requestEntryDuration();
    }


    // Request collaborator name from the user
    private String requestEntryTitle() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Entry Title: ");
        return scanner.nextLine();
    }

    private String requestEntryDescription() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Entry Description: ");
        return scanner.nextLine();
    }

    private String requestEntryUrgency() {
        Scanner scanner = new Scanner(System.in);
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
        Scanner scanner = new Scanner(System.in);
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
        Scanner scanner = new Scanner(System.in);
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
