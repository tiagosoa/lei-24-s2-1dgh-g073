package pt.ipp.isep.dei.esoft.project.ui.console.menu;


import pt.ipp.isep.dei.esoft.project.ui.console.*;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Paulo Maio pam@isep.ipp.pt
 */

public class GSMUI implements Runnable {
    public GSMUI() {
    }



    public void run() {
        List<MenuItem> options = new ArrayList<MenuItem>();
        options.add(new MenuItem("Register Green Space", new RegisterGSUI()));
        options.add(new MenuItem("Add Entry to To-Do List", new AddEntryToDoListUI()));
        options.add(new MenuItem("Add Entry to Agenda", new AddEntryAgendaUI()));
        options.add(new MenuItem("Assign Team to Agenda Entry", new AssignTeamUI()));
        options.add(new MenuItem("Postpone Agenda Entry", new PostponeEntryAgendaUI()));
        options.add(new MenuItem("Cancel an Agenda Entry", new CancelEntryAgendaUI()));
        options.add(new MenuItem("Assign Vehicle to Agenda Entry", new AssignVehicleUI()));
        options.add(new MenuItem("List Managed Green Spaces", new ListManagedGSUI()));


        int option = 0;
        do {
            option = Utils.showAndSelectIndex(options, "\n\n--- Green Space Manager MENU -------------------------");

            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        } while (option != -1);
    }
}