package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.AddEntryAgendaController;
import pt.ipp.isep.dei.esoft.project.domain.AgendaEntry;

import java.util.List;
import java.util.Scanner;

public class CancelEntryAgendaUI implements Runnable {
    private final AddEntryAgendaController controller;

    public CancelEntryAgendaUI() {
        this.controller = new AddEntryAgendaController();
    }

    @Override
    public void run() {
        requestData();
    }

    private void requestData() {
        AgendaEntry entryToCancel = requestAgendaEntry();
        if (entryToCancel == null) {
            System.out.println("No valid entry selected.");
            return;
        }

        cancelEntry(entryToCancel);
    }

    private AgendaEntry requestAgendaEntry() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Select an entry from the Agenda to cancel:");
        List<AgendaEntry> entries = controller.getAgendaEntries();
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

    private void cancelEntry(AgendaEntry entry) {
        try {
            controller.cancelEntry(entry);
            System.out.println("Entry canceled successfully!");
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }
    }
}

