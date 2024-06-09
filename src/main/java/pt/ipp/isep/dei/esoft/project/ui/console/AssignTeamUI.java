package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.AssignTeamController;
import pt.ipp.isep.dei.esoft.project.config.Config;
import pt.ipp.isep.dei.esoft.project.config.EmailService;
import pt.ipp.isep.dei.esoft.project.config.EmailServiceFactory;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.AgendaRepository;
import pt.ipp.isep.dei.esoft.project.repository.TeamRepository;
import pt.ipp.isep.dei.esoft.project.ui.console.menu.GSMUI;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

/**
 * User Interface for assigning a team to an agenda entry.
 */
public class AssignTeamUI implements Runnable {
    // Attributes
    private final AssignTeamController controller;
    private Team team;
    private AgendaEntry agendaEntry;
    private AgendaRepository agendaRepository;
    private TeamRepository teamRepository;

    private Agenda agenda;

    /**
     * Constructor for AssignTeamUI.
     */
    public AssignTeamUI() {
        controller = new AssignTeamController();
        this.agendaRepository = getController().getAgendaRepository();
        this.teamRepository = getController().getTeamRepository();
    }

    // Methods

    /**
     * Retrieves the controller.
     *
     * @return the AssignTeamController.
     */
    private AssignTeamController getController() {
        return controller;
    }

    /**
     * Runs the AssignTeamUI.
     */
    public void run() {
        requestData();
        try {
            submitData();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Submits the data for assigning a team to an agenda entry.
     *
     * @throws IOException if an I/O error occurs.
     */
    public void submitData() throws IOException {
        Scanner scanner = new Scanner(System.in);
        assert agendaEntry != null;
        System.out.println("Selected Entry: " + agendaEntry.getTitle());
        System.out.println("Selected Team:" + team.getTeamID() + "- " + team.listMembers());
        System.out.println("Are these correct?");
        String yesno;
        do {
            yesno = scanner.nextLine();
            if (yesno.equals("no")) {
                requestData();
            }
        } while (!(yesno.equals("no") || yesno.equals("yes")));
        Optional<AgendaEntry> check = getController().assignTeam(agendaEntry, team);
        if (check.isPresent()) {
            System.out.println("Team assigned successfully. Sending message to collaborators...");
            sendNotifications(team);
        }
    }

    /**
     * Sends notifications to the team members.
     *
     * @param team the team to notify.
     */
    private void sendNotifications(Team team) {
        try {
            Config config = new Config("config.properties");
            EmailService emailService = EmailServiceFactory.getEmailService(config);
            for (Collaborator member : team.collaborators) {
                emailService.sendEmail(
                        member.getEmail(),
                        "Task Assignment",
                        "You have been assigned to task: " + agendaEntry.getTitle()
                );
            }
            System.out.println("Notifications sent successfully.");
        } catch (IOException e) {
            System.out.println("Failed to send notifications: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Requests data from the user.
     */
    public void requestData() {
        agendaEntry = requestAgendaEntry();
        if (agendaEntry == null) {
            GSMUI gsmUI = new GSMUI();
            gsmUI.run();
        }
        team = requestTeam();
        if (team == null) {
            GSMUI gsmUI = new GSMUI();
            gsmUI.run();
        }
    }

    /**
     * Requests an agenda entry from the user.
     *
     * @return the selected AgendaEntry.
     */
    public AgendaEntry requestAgendaEntry() {
        List<AgendaEntry> entries = controller.getAgendaEntries();
        if (entries.isEmpty()) {
            System.out.println("No entries registered in the Agenda.");
            return null;
        }
        System.out.println("Agenda Entry List:");
        for (int i = 0; i < entries.size(); i++) {
            System.out.println((i + 1) + ". " + entries.get(i).getTitle());
        }
        int entryIndex = readInput(1, entries.size()) - 1;
        return entries.get(entryIndex);
    }

    /**
     * Requests a team from the user.
     *
     * @return the selected Team.
     */
    public Team requestTeam() {
        List<Team> teams = teamRepository.getTeamList();
        if (teams.isEmpty()) {
            System.out.println("No teams registered.");
            return null;
        }
        System.out.println("Team List:");
        for (int i = 0; i < teams.size(); i++) {
            System.out.println((i + 1) + ". " + teams.get(i).getTeamID() + "- " + teams.get(i).listMembers());
        }
        int teamIndex = readInput(1, teams.size()) - 1;
        return teams.get(teamIndex);
    }

    /**
     * Reads the input from the user within the specified range.
     *
     * @param min minimum input value.
     * @param max maximum input value.
     * @return the user input.
     */
    private int readInput(int min, int max) {
        Scanner scanner = new Scanner(System.in);
        int input;
        do {
            System.out.print("Select by entering a number between " + min + " and " + max + ": ");
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