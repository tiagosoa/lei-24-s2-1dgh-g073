package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.AssignTeamController;
import pt.ipp.isep.dei.esoft.project.application.controller.AssignVehicleController;
import pt.ipp.isep.dei.esoft.project.domain.AgendaEntry;
import pt.ipp.isep.dei.esoft.project.domain.Vehicle;
import pt.ipp.isep.dei.esoft.project.repository.AgendaRepository;
import pt.ipp.isep.dei.esoft.project.repository.VehicleRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

/**
 * User Interface for assigning vehicles to agenda entries.
 */
public class AssignVehicleUI implements Runnable {
    private final AssignVehicleController controller;

    private AgendaEntry agendaEntry;
    private List<Vehicle> selectedVehicles;
    private VehicleRepository vehicleRepository;
    private AgendaRepository agendaRepository;

    /**
     * Constructor for AssignVehicleUI.
     */
    public AssignVehicleUI() {
        controller = new AssignVehicleController();
        this.vehicleRepository = getController().getVehicleRepository();
        this.agendaRepository = getController().getAgendaRepository();
    }

    private AssignVehicleController getController() {
        return controller;
    }

    @Override
    public void run() {
        requestData();
        submitData();
    }

    /**
     * Request data from the user.
     */
    public void requestData() {
        agendaEntry = selectAgendaEntry();
        selectedVehicles = selectVehicle();
    }

    /**
     * Submit the collected data.
     */
    public void submitData() {
        Scanner scanner = new Scanner(System.in);
        assert agendaEntry != null;
        System.out.println("Selected Entry: " + agendaEntry.getTitle());
        System.out.println("Selected Vehicles:");
        for (Vehicle vehicle : selectedVehicles) {
            System.out.println("\n- " + vehicle.getModel() + vehicle.getBrand() + vehicle.getPlateNumber());
        }
        System.out.println("Are these correct?");
        String yesno;
        do {
            yesno = scanner.nextLine();
            if (yesno.equals("no")) {
                requestData();
            }
        } while (!(yesno.equals("no") || yesno.equals("yes")));
        try {
            boolean check = getController().assignVehicleToEntry(agendaEntry, selectedVehicles);
            if (check) {
                System.out.println("Vehicles assigned successfully.");
            }
        } catch (Exception e) {
            System.out.println("Operation unsuccessful.");
        }
    }

    /**
     * Select an agenda entry from a list.
     *
     * @return the selected agenda entry
     */
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

    /**
     * Select vehicles from a list to assign to the agenda entry.
     *
     * @return the list of selected vehicles
     */
    private List<Vehicle> selectVehicle() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Select a vehicle to assign:");
        List<Vehicle> vehicles = vehicleRepository.getVehicles();
        for (int i = 0; i < vehicles.size(); i++) {
            System.out.println((i + 1) + ". " + vehicles.get(i).getModel() + vehicles.get(i).getBrand() + vehicles.get(i).getPlateNumber());
        }
        System.out.println("Enter vehicle number(s) separated by commas (e.g., 1,2,3):");
        String vehicleIndexInput = scanner.nextLine();
        String[] vehicleIndices = vehicleIndexInput.split(",");
        List<Vehicle> selectedVehicles = new ArrayList<>();
        for (String index : vehicleIndices) {
            int vehicleIndex = Integer.parseInt(index.trim()) - 1;
            selectedVehicles.add(vehicles.get(vehicleIndex));
        }
        return selectedVehicles;
    }
}