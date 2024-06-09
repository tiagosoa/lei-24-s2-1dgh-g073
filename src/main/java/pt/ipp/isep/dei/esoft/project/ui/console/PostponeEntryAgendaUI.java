package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.PostponeEntryAgendaController;
import pt.ipp.isep.dei.esoft.project.domain.AgendaEntry;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

/**
 * User interface for postponing an agenda entry.
 */
public class PostponeEntryAgendaUI implements Runnable {
    private final PostponeEntryAgendaController controller;

    private AgendaEntry agendaEntry;
    private LocalDate postponeDate;

    /**
     * Constructor that initializes the controller.
     */
    public PostponeEntryAgendaUI() {
        this.controller = new PostponeEntryAgendaController();
    }

    @Override
    public void run() {
        System.out.println("\n\n--- Postpone Agenda Entry ------------------------");
        requestData();
        submitData();
    }

    /**
     * Requests the necessary data for postponing an agenda entry.
     */
    public void requestData() {
        agendaEntry = requestAgendaEntry();
        postponeDate = requestPostponeDate();
    }

    /**
     * Submits the data for postponing the agenda entry.
     */
    public void submitData() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("'" + agendaEntry.getTitle() + "' : " + agendaEntry.getStartDate() + " --> " + postponeDate + "- is this data correct? (type 'yes' or 'no')");
        String yesno;
        do {
            yesno = scanner.nextLine();
            if (yesno.equals("no")) {
                requestData();
            }
        } while (!(yesno.equals("no") || yesno.equals("yes")));
        boolean check = controller.postponeEntry(agendaEntry, postponeDate);
        if (check) {
            System.out.println("Entry postponed successfully!");
        } else {
            System.out.println("New deadline must be later than the current deadline.");
        }
    }

    /**
     * Requests the user to select an agenda entry to postpone.
     *
     * @return The selected agenda entry.
     */
    public AgendaEntry requestAgendaEntry() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Select an entry to postpone:");
        List<AgendaEntry> entries = controller.getAgendaEntries();
        for (int i = 0; i < entries.size(); i++) {
            System.out.println((i + 1) + ". " + entries.get(i).getTitle() + " - startDate: " + entries.get(i).getStartDate());
        }

        int entryIndex = scanner.nextInt() - 1;
        scanner.nextLine();  // Consume newline

        if (entryIndex < 0 || entryIndex >= entries.size()) {
            System.out.println("Invalid selection");
            return null;
        }
        return entries.get(entryIndex);
    }

    /**
     * Requests the user to enter a new starting date for the selected agenda entry.
     *
     * @return The new starting date.
     */
    public LocalDate requestPostponeDate() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Selected entry details:");
        System.out.println("Title: " + agendaEntry.getTitle());
        System.out.println("Task Description: " + agendaEntry.getTaskDescription());
        System.out.println("Urgency: " + agendaEntry.getUrgency());
        System.out.println("Duration: " + agendaEntry.getDuration());
        System.out.println("Current startDate: " + agendaEntry.getStartDate());
        System.out.println("Status:"  + agendaEntry.getStatus());

        System.out.println("Enter new starting date (dd-MM-yyyy):");
        String newstartDateStr = scanner.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return LocalDate.parse(newstartDateStr, formatter);
    }
}