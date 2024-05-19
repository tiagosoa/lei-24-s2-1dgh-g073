package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.ProduceMaintenanceListController;
import pt.ipp.isep.dei.esoft.project.domain.Vehicle;
import pt.ipp.isep.dei.esoft.project.repository.VehicleRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProduceMaintenanceListUI implements Runnable {

    private ProduceMaintenanceListController controller;

    private final VehicleRepository vehicleRepository;

    public ProduceMaintenanceListUI() {
        controller = new ProduceMaintenanceListController();
        this.vehicleRepository = new VehicleRepository();
    }

    public List<String> produceMaintenanceList() {
        List<String> maintenanceList = new ArrayList<>();

        // Add header
        maintenanceList.add("Plate Brand Model Curr.Kms Last Next");

        // Retrieve vehicles needing maintenance
        List<Vehicle> vehicles = controller.getVehicles();

        // Iterate over vehicles and add data to the maintenance list
        for (Vehicle vehicle : vehicles) {
            String plate = vehicle.getPlateNumber();
            String brand = vehicle.getBrand();
            String model = vehicle.getModel();
            double currentKms = vehicle.getCurrentKm();
            LocalDate lastMaintenanceDate = vehicle.getLastMaintenanceDate();
            int maintenanceFrequencyKm = vehicle.getMaintenanceFrequencyKm();
            LocalDate nextMaintenanceDate = lastMaintenanceDate.plusDays(maintenanceFrequencyKm);
            double nextMaintenanceKms = lastMaintenanceDate.plusDays(maintenanceFrequencyKm).until(LocalDate.now(), java.time.temporal.ChronoUnit.DAYS);

            if (currentKms >= nextMaintenanceKms) {
                // Format data and add to the maintenance list
                String maintenanceData = String.format("%s %s %s %.0f %s %s",
                        plate, brand, model, currentKms, lastMaintenanceDate.toString(), nextMaintenanceDate.toString());
                maintenanceList.add(maintenanceData);
            }
        }

        return maintenanceList;
    }

    @Override
    public void run() {
        List<String> maintenanceList = produceMaintenanceList();
        for (String maintenanceData : maintenanceList) {
            System.out.println(maintenanceData);
        }
    }
}