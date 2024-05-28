package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.GenerateTeamController;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.CollaboratorRepository;
import pt.ipp.isep.dei.esoft.project.repository.SkillRepository;
import pt.ipp.isep.dei.esoft.project.repository.TeamRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

/**
 * User interface for generating a team based on specified criteria.
 */
public class GenerateTeamUI implements Runnable {

    private final GenerateTeamController controller;
    private CollaboratorRepository collaboratorRepository;
    private SkillRepository skillRepository;
    private TeamRepository teamRepository;
    private final Scanner scanner;
    private List<Skill> requiredSkills;

    private int minTeamSize;

    private int maxTeamSize;

    /**
     * Constructor for the GenerateTeamUI class.
     */
    public GenerateTeamUI() {
        controller = new GenerateTeamController();
        this.collaboratorRepository = getController().getCollaboratorRepository();
        this.skillRepository = getController().getSkillRepository();
        this.teamRepository = getController().getTeamRepository();
        this.scanner = new Scanner(System.in);
    }

    private GenerateTeamController getController() {
        return controller;
    }

    /**
     * Runs the user interface for generating a team.
     */
    public void run() {
        Scanner scanner = new Scanner(System.in);

        requestData();

        submitData();
    }

    private void requestData(){
        System.out.println("Enter the minimum team size:");
        minTeamSize = scanner.nextInt();

        System.out.println("Enter the maximum team size:");
        maxTeamSize = scanner.nextInt();

        scanner.nextLine(); // Consume newline
        if (minTeamSize >= maxTeamSize || minTeamSize <= 0 || maxTeamSize <= 0) {
            throw new IllegalArgumentException("Invalid team size inputs.");
        }
        System.out.println("Enter the required skills separated by semicolons:");
        String inputSkills = scanner.nextLine();
        requiredSkills = skillRepository.parseSkills(inputSkills);
    }

    private void submitData() {
        try {
            List<Collaborator> team = getController().generateTeam(maxTeamSize, minTeamSize, requiredSkills);
            displayTeam(team);
            System.out.print("Do you accept this team? (yes/no): ");
            String yesno;
            do {
                yesno = scanner.nextLine();
            } while (!(yesno.equals("no") || yesno.equals("yes")));
            if (yesno.equals("yes")) {
                Optional<Team> newteam = getController().createTeam(team, 1);
                if (newteam.isPresent()) {
                    teamRepository.addTeam(newteam.get());
                    System.out.println("Team accepted.");
                }
            } else {
                System.out.println("Team rejected.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void displayTeam(List<Collaborator> team) {
        System.out.println("Generated Team:");
        for (Collaborator collaborator : team) {
            System.out.println("- " + collaborator.getName());
        }
    }
}