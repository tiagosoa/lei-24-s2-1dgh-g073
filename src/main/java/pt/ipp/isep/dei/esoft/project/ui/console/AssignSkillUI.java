package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.AssignSkillController;
import pt.ipp.isep.dei.esoft.project.domain.HRM;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.SkillRepository;
import pt.ipp.isep.dei.esoft.project.repository.CollaboratorRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Assign Skill UI (console). This UI is only available for administrators for demonstration purposes.
 */
public class AssignSkillUI implements Runnable {

    private AssignSkillController controller;
    private CollaboratorRepository collaboratorRepository;

    private SkillRepository skillRepository;
    private Scanner scanner;


    /**
     * Constructs a new AssignSkillUI and initializes the necessary repositories and scanner.
     */
    public AssignSkillUI() {
        controller = new AssignSkillController();
        this.collaboratorRepository = new CollaboratorRepository();
        this.skillRepository = Repositories.getInstance().getSkillRepository();
        this.scanner = new Scanner(System.in);
    }



    /**
     * Retrieves the AssignSkillController instance.
     *
     * @return AssignSkillController instance
     */

    private AssignSkillController getController() {
        return controller;
    }

    /**
     * Runs the Assign Skill UI flow, allowing administrators to assign skills to collaborators.
     */
    public void run() {
        System.out.println("\n\n--- Assign Skill ------------------------");
        List<Collaborator> collaborators = getController().getCollaboratorList();
        // Show list of registered collaborators
        if (collaborators.isEmpty()) {
            System.out.println("No collaborators registered.");
            return;
        }
        System.out.println("Collaborator List:");
        for (int i = 0; i < collaborators.size(); i++) {
            System.out.println((i + 1) + ". " + collaborators.get(i).getName());
        }
        int collaboratorIndex = readInput(1, collaborators.size()) - 1;
        Collaborator selectedCollaborator = collaborators.get(collaboratorIndex);

        // Show list of skills
        List<Skill> skills = skillRepository.getSkillList();
        if (skills.isEmpty()) {
            System.out.println("No skills registered.");
            return;
        }
        System.out.println("Select skill(s) to assign to " + selectedCollaborator.getName() + ":");
        for (int i = 0; i < skills.size(); i++) {
            System.out.println((i + 1) + ". " + skills.get(i).getName());
        }
        System.out.println("Enter skill number(s) separated by commas (e.g., 1,2,3):");
        String skillIndexInput = scanner.nextLine();
        String[] skillIndices = skillIndexInput.split(",");
        List<String> selectedSkillNames = new ArrayList<>();
        for (String index : skillIndices) {
            int skillIndex = Integer.parseInt(index.trim()) - 1;
            selectedSkillNames.add(skills.get(skillIndex).getName().trim());
        }
        HRM hrm = getController().getHRMFromSession();

        // Assign selected skills to collaborator
        getController().assignSkillsToCollaborator(selectedCollaborator.getIDNumber(), selectedSkillNames);
        System.out.println("Skills assigned successfully to " + selectedCollaborator.getName());
    }

    /**
     * Reads the input from the user within the specified range of collaborators.
     *
     * @param min minimum number of collaborators.
     * @param max maximum number of collaborators.
     * @return the input from the user.
     */
    private int readInput(int min, int max) {
        int input;
        do {
            System.out.print("Select the collaborator by entering a number between " + min + " and " + max + ": ");
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next();
            }
            input = scanner.nextInt();
        } while (input < min || input > max);
        scanner.nextLine(); // Consume newline character
        return input;
    }
}