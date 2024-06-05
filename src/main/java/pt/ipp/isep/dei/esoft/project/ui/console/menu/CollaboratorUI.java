package pt.ipp.isep.dei.esoft.project.ui.console.menu;


import pt.ipp.isep.dei.esoft.project.ui.console.AddVehicleUI;
import pt.ipp.isep.dei.esoft.project.ui.console.ProduceMaintenanceListUI;
import pt.ipp.isep.dei.esoft.project.ui.console.RegisterMaintenanceUI;
import pt.ipp.isep.dei.esoft.project.ui.console.ShowTextUI;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Paulo Maio pam@isep.ipp.pt
 */

public class CollaboratorUI implements Runnable {
    public CollaboratorUI() {
    }

    public void run() {
        List<MenuItem> options = new ArrayList<MenuItem>();
        options.add(new MenuItem("Add Vehicle", new AddVehicleUI()));
        options.add(new MenuItem("Register Maintenance", new RegisterMaintenanceUI()));

        int option = 0;
        do {
            option = Utils.showAndSelectIndex(options, "\n\n--- Collaborator MENU -------------------------");

            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        } while (option != -1);
    }
}