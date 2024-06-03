package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.AddEntryAgendaController;
import pt.ipp.isep.dei.esoft.project.domain.AgendaEntry;
import pt.ipp.isep.dei.esoft.project.domain.Vehicle;

import java.util.List;
import java.util.Scanner;

public class AssignVehicleToEntryUI implements Runnable {
    private final AddEntryAgendaController controller;

    public AssignVehicleToEntryUI() {
        this.controller = new AddEntryAgendaController();
    }

    @Override
    public void run() {
        assignVehicleToEntry();
    }

    private void assignVehicleToEntry() {
        AgendaEntry entry = selectAgendaEntry();
        if (entry == null) {
            System.out.println("No valid entry selected.");
            return;
        }

        Vehicle vehicle = selectVehicle();
        if (vehicle == null) {
            System.out.println("No valid vehicle selected.");
            return;
        }

        controller.assignVehicleToEntry(entry, vehicle);
        System.out.println("Vehicle assigned successfully!");
    }

    private AgendaEntry selectAgendaEntry() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Select an agenda entry:");
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

    private Vehicle selectVehicle() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Select a vehicle to assign:");
        List<Vehicle> vehicles = getAvailableVehicles();
        for (int i = 0; i < vehicles.size(); i++) {
            System.out.println((i + 1) + ". " + vehicles.get(i).getPlateNumber());
        }

        int vehicleIndex = scanner.nextInt() - 1;
        scanner.nextLine();  // Consume newline

        if (vehicleIndex < 0 || vehicleIndex >= vehicles.size()) {
            System.out.println("Invalid selection");
            return null;
        }

        return vehicles.get(vehicleIndex);
    }

    private List<Vehicle> getAvailableVehicles() {
        // Implement a method to retrieve the list of available vehicles
        return List.of(new Vehicle("ABC-123"), new Vehicle("XYZ-789"));
    }
}
