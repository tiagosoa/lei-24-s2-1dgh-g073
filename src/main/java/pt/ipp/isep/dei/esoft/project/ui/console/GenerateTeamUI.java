package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.GenerateTeamController;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.CollaboratorRepository;
import pt.ipp.isep.dei.esoft.project.repository.SkillRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GenerateTeamUI implements Runnable{

    private final GenerateTeamController controller;
    private CollaboratorRepository collaboratorRepository;

    private SkillRepository skillRepository;
    private final Scanner scanner;

    public GenerateTeamUI() {
        controller = new GenerateTeamController();
        this.collaboratorRepository = new CollaboratorRepository();
        this.skillRepository = new SkillRepository();
        this.scanner = new Scanner(System.in);
    }

    private GenerateTeamController getController() {
        return controller;
    }

    public void run() {
        System.out.println("=== Team Generation ===");

        // Get team size criteria
        System.out.print("Enter minimum team size: ");
        int minTeamSize = scanner.nextInt();
        System.out.print("Enter maximum team size: ");
        int maxTeamSize = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        // Get required skills
        List<Skill> requiredSkills = getRequiredSkills();

        // Generate team
        List<Collaborator> team = controller.generateTeam(maxTeamSize, minTeamSize, requiredSkills);

        // Display team
        System.out.println("\nGenerated Team:");
        for (Collaborator collaborator : team) {
            System.out.println(collaborator.getName());
        }
    }

    private List<Skill> getRequiredSkills() {
        List<Skill> requiredSkills = new ArrayList<>();
        HRM hrm = getController().getHRMFromSession();
        boolean continueInput = true;
        while (continueInput) {
            System.out.println("Enter a required skill (or 'done' to finish): ");
            String skillName = scanner.nextLine().trim();
            if (!skillName.equalsIgnoreCase("done")) {
                Skill skill = new Skill(skillName, hrm);
                requiredSkills.add(skill);
            } else {
                continueInput = false;
            }
        }
        return requiredSkills;
    }
}