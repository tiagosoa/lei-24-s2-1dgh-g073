package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.AddEntryAgendaController;
import pt.ipp.isep.dei.esoft.project.domain.TDLEntry;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;
import pt.ipp.isep.dei.esoft.project.repository.GreenSpaceRepository;
import pt.ipp.isep.dei.esoft.project.repository.ToDoListRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class AddAgendaEntryUI implements Runnable {
    private final AddEntryAgendaController controller;
    private ToDoListRepository toDoListRepository;
    private GreenSpaceRepository greenSpaceRepository;


    public AddAgendaEntryUI() {
        this.controller = new AddEntryAgendaController();
        this.toDoListRepository = getController().getToDoListRepository();
        this.greenSpaceRepository = getController().getGreenSpaceRepository();
;    }

    private AddEntryAgendaController getController() {
        return controller;
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Select an entry from the To-Do List:");
        List<TDLEntry> entries = controller.getToDoListEntries();
        for (int i = 0; i < entries.size(); i++) {
            System.out.println((i + 1) + ". " + entries.get(i).getTitle());
        }

        int entryIndex = scanner.nextInt() - 1;
        scanner.nextLine();  // Consume newline

        if (entryIndex < 0 || entryIndex >= entries.size()) {
            System.out.println("Invalid selection");
            return;
        }

        TDLEntry selectedEntry = entries.get(entryIndex);

        List<GreenSpace> greenSpaces = controller.getManagedGreenSpaces();
        for (int i = 0; i < greenSpaces.size(); i++) {
            System.out.println((i + 1) + ". " + greenSpaces.get(i).getName());
        }

        System.out.println("Select a green space to associate:");
        int greenSpaceIndex = scanner.nextInt() - 1;
        scanner.nextLine();  // Consume newline

        if (greenSpaceIndex < 0 || greenSpaceIndex >= greenSpaces.size()) {
            System.out.println("Invalid selection");
            return;
        }

        GreenSpace selectedGreenSpace = greenSpaces.get(greenSpaceIndex);

        System.out.println("Enter the deadline (dd-MM-yyyy):");
        String deadlineStr = scanner.nextLine();

        try {
            Date deadline = new SimpleDateFormat("dd-MM-yyyy").parse(deadlineStr);
            controller.addEntryToAgenda(selectedEntry, selectedGreenSpace);
            System.out.println("Entry added to agenda successfully!");
        } catch (ParseException e) {
            System.out.println("Invalid date format");
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }
    }
}
