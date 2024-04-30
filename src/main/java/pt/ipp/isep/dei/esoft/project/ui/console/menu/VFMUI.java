package pt.ipp.isep.dei.esoft.project.ui.console.menu;


import pt.ipp.isep.dei.esoft.project.ui.console.CreateSkillUI;
import pt.ipp.isep.dei.esoft.project.ui.console.ShowTextUI;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Paulo Maio pam@isep.ipp.pt
 */

public class VFMUI implements Runnable {
    public VFMUI() {
    }

    public void run() {
        List<MenuItem> options = new ArrayList<MenuItem>();
        options.add(new MenuItem("Add Vehicle", new CreateSkillUI()));
        options.add(new MenuItem("Option 2", new ShowTextUI("You have chosen Option 2.")));
        options.add(new MenuItem("Option 3", new ShowTextUI("You have chosen Option 3.")));
        options.add(new MenuItem("Option 4", new ShowTextUI("You have chosen Option 4.")));

        int option = 0;
        do {
            option = Utils.showAndSelectIndex(options, "\n\n--- Vehicle Fleet Manager MENU -------------------------");

            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        } while (option != -1);
    }
}