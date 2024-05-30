package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.AddEntryAgendaController;
import pt.ipp.isep.dei.esoft.project.domain.AgendaEntry;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class PostponeEntryAgendaUI implements Runnable {
    private final AddEntryAgendaController controller;

    public PostponeEntryAgendaUI() {
        this.controller = new AddEntryAgendaController();
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n\n--- Postpone Agenda Entry ------------------------");

        System.out.println("Select an entry to postpone:");
        List<AgendaEntry> entries = controller.getAgendaEntries();
        for (int i = 0; i < entries.size(); i++) {
            System.out.println((i + 1) + ". " + entries.get(i).getTitle() + " - Deadline: " + entries.get(i).getDeadline());
        }

        int entryIndex = scanner.nextInt() - 1;
        scanner.nextLine();  // Consume newline

        if (entryIndex < 0 || entryIndex >= entries.size()) {
            System.out.println("Invalid selection");
            return;
        }

        AgendaEntry selectedEntry = entries.get(entryIndex);
        System.out.println("Selected entry details:");
        System.out.println("Title: " + selectedEntry.getTitle());
        System.out.println("Task Description: " + selectedEntry.getTaskDescription());
        System.out.println("Urgency: " + selectedEntry.getUrgency());
        System.out.println("Duration: " + selectedEntry.getDuration());
        System.out.println("Current Deadline: " + selectedEntry.getDeadline());

        System.out.println("Enter new deadline (dd-MM-yyyy):");
        String newDeadlineStr = scanner.nextLine();
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate newDeadline = LocalDate.parse(newDeadlineStr, formatter);

            if (controller.postponeEntry(selectedEntry, newDeadline)) {
                System.out.println("Entry postponed successfully!");
            } else {
                System.out.println("New deadline must be later than the current deadline.");
            }
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format.");
        }
    }
}

