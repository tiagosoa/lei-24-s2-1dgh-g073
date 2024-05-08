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
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the minimum team size:");
        int minTeamSize = scanner.nextInt();

        System.out.println("Enter the maximum team size:");
        int maxTeamSize = scanner.nextInt();

        scanner.nextLine(); // Consume newline
        if (minTeamSize >= maxTeamSize || minTeamSize <= 0 || maxTeamSize <= 0) {
            throw new IllegalArgumentException("Invalid team size inputs.");
        }
        System.out.println("Enter the required skills separated by semicolons:");
        String inputSkills = scanner.nextLine();
        List<Skill> requiredSkills = parseSkills(inputSkills);

        try {
            List<Collaborator> team = getController().generateTeam(maxTeamSize, minTeamSize, requiredSkills);
            displayTeam(team);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private List<Skill> parseSkills(String inputSkills) {
        String[] skillsArray = inputSkills.split(";");
        List<Skill> skillsList = new ArrayList<>();
        for (String skillName : skillsArray) {
            skillsList.add(new Skill(skillName.trim()));
        }
        return skillsList;
    }

    private void displayTeam(List<Collaborator> team) {
        System.out.println("Generated Team:");
        for (Collaborator collaborator : team) {
            System.out.println("- " + collaborator.getName());
        }
    }
}