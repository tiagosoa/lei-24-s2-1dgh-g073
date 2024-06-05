package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.CancelEntryAgendaController;
import pt.ipp.isep.dei.esoft.project.domain.AgendaEntry;

import java.util.List;
import java.util.Scanner;

public class CancelEntryAgendaUI implements Runnable {
    private final CancelEntryAgendaController controller;

    private AgendaEntry agendaEntry;

    public CancelEntryAgendaUI() {
        controller = new CancelEntryAgendaController();
    }

    @Override
    public void run() {
        requestData();
        submitData();
    }

    private void submitData() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Selected entry details:");
        System.out.println("Title: " + agendaEntry.getTitle());
        System.out.println("Task Description: " + agendaEntry.getTaskDescription());
        System.out.println("Urgency: " + agendaEntry.getUrgency());
        System.out.println("Duration: " + agendaEntry.getDuration());
        System.out.println("Current startDate: " + agendaEntry.getStartDate());
        System.out.println("Status:"  + agendaEntry.getStatus());
        System.out.println("Do you want to cancel this entry?");
        String yesno;
        do {
            yesno = scanner.nextLine();
            if (yesno.equals("no")) {
                requestData();
            }
        } while (!(yesno.equals("no") || yesno.equals("yes")));
        try {
            controller.cancelEntry(agendaEntry);
            System.out.println("Entry cancelled successfully!");
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }
    }

    private void requestData() {
        agendaEntry = requestAgendaEntry();
        if (agendaEntry == null) {
            System.out.println("No valid entry selected.");
        }
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
}

