package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.CreateSkillController;
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
    private List<String> maintenanceList;

    public ProduceMaintenanceListUI() {
        controller = new ProduceMaintenanceListController();
        this.vehicleRepository = getController().getVehicleRepository();
    }

    private ProduceMaintenanceListController getController() {
        return controller;
    }

    public List<String> produceMaintenanceList() {
        List<String> maintenanceList = new ArrayList<>();

        // Add header
        maintenanceList.add("Plate Brand Model Curr.Kms Last Next");

        maintenanceList = vehicleRepository.produceMaintenanceList();

        return maintenanceList;
    }

    @Override
    public void run() {
        maintenanceList = produceMaintenanceList();
        for (String maintenanceData : maintenanceList) {
            System.out.println(maintenanceData);
        }
    }
}