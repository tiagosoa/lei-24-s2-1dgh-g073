package pt.ipp.isep.dei.esoft.project.ui.console.menu;


import pt.ipp.isep.dei.esoft.project.ui.console.*;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Paulo Maio pam@isep.ipp.pt
 */

public class AdminUI implements Runnable {
    public AdminUI() {
    }

    public void run() {
        List<MenuItem> options = new ArrayList<MenuItem>();
        options.add(new MenuItem("Create Skill", new CreateSkillUI()));
        options.add(new MenuItem("Create Job", new CreateJobUI()));
        options.add(new MenuItem("Register Collaborator", new RegisterCollaboratorUI()));
        options.add(new MenuItem("Assign Skill to Collaborator", new AssignSkillUI()));
        options.add(new MenuItem("Generate Team", new GenerateTeamUI()));
        options.add(new MenuItem("Add Vehicle", new AddVehicleUI()));
        options.add(new MenuItem("Register Maintenance", new RegisterMaintenanceUI()));
        options.add(new MenuItem("Produce Maintenance List", new ProduceMaintenanceListUI()));

        int option = 0;
        do {
            option = Utils.showAndSelectIndex(options, "\n\n--- ADMIN MENU -------------------------");

            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        } while (option != -1);
    }
}