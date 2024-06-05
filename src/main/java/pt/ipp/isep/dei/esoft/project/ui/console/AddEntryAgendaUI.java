package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.AddEntryAgendaController;
import pt.ipp.isep.dei.esoft.project.domain.AgendaEntry;
import pt.ipp.isep.dei.esoft.project.domain.TDLEntry;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;
import pt.ipp.isep.dei.esoft.project.repository.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class AddEntryAgendaUI implements Runnable {
    private final AddEntryAgendaController controller;

    private TDLEntry toDoListEntry;
    private String entryStatus;
    private LocalDate entryStartDate;
    private GreenSpace associatedGreenSpace;

    private ToDoListRepository toDoListRepository;
    private GreenSpaceRepository greenSpaceRepository;
    private AgendaRepository agendaRepository;
    private TaskRepository taskRepository;
    private AuthenticationRepository authenticationRepository;

    public AddEntryAgendaUI() {
        this.controller = new AddEntryAgendaController();
        this.toDoListRepository = getController().getToDoListRepository();
        this.greenSpaceRepository = getController().getGreenSpaceRepository();
        this.agendaRepository =  getController().getAgendaRepository();
        this.authenticationRepository = getController().getAuthenticationRepository();
        this.taskRepository = getController().getTaskRepository();
    }

    private AddEntryAgendaController getController() {
        return controller;
    }

    @Override
    public void run() {
        requestData();
        submitData();
    }

    public void requestData() {
        this.toDoListEntry = requestToDoListEntry();
        if (this.toDoListEntry == null) return;

        this.associatedGreenSpace = requestAssociatedGreenSpace();
        if (this.associatedGreenSpace == null) return;

        this.entryStatus = "Planned";

        this.entryStartDate = requestEntryStartingDate();
    }

    public void submitData() {
        try {
            if (controller.addEntry(toDoListEntry, entryStatus, entryStartDate).isPresent()) {
                System.out.println("Entry added to agenda successfully!");
            } else {
                System.out.println("Failed to add entry to the agenda.");
            }
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }
    }

    public TDLEntry requestToDoListEntry() {
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
            return null;
        }

        return entries.get(entryIndex);
    }

    public GreenSpace requestAssociatedGreenSpace() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Select a green space to associate:");
        List<GreenSpace> greenSpaces = controller.getManagedGreenSpaces();
        for (int i = 0; i < greenSpaces.size(); i++) {
            System.out.println((i + 1) + ". " + greenSpaces.get(i).getName());
        }

        int greenSpaceIndex = scanner.nextInt() - 1;
        scanner.nextLine();  // Consume newline

        if (greenSpaceIndex < 0 || greenSpaceIndex >= greenSpaces.size()) {
            System.out.println("Invalid selection");
            return null;
        }

        return greenSpaces.get(greenSpaceIndex);
    }

    public LocalDate requestEntryStartingDate() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the starting date (dd-MM-yyyy):");
        String dateStr = scanner.nextLine();

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            return LocalDate.parse(dateStr, formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format");
            return null;
        }
    }
}
