package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.CreateSkillController;
import pt.ipp.isep.dei.esoft.project.domain.Skill;

import java.util.Optional;
import java.util.Scanner;
import pt.ipp.isep.dei.esoft.project.domain.HRM;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.SkillRepository;

/**
 * Create Skill UI (console). This class provides a console interface for creating a new skill.
 */
public class CreateSkillUI implements Runnable {

    private final CreateSkillController controller;
    private String skillName;
    private SkillRepository skillRepository;

    /**
     * Constructor for CreateSkillUI.
     */
    public CreateSkillUI() {
        controller = new CreateSkillController();
        this.skillRepository = getController().getSkillRepository();
    }

    /**
     * Get the CreateSkillController associated with this UI.
     *
     * @return the CreateSkillController
     */
    private CreateSkillController getController() {
        return controller;
    }

    /**
     * Run the Create Skill UI.
     */
    public void run() {
        System.out.println("\n\n--- Create Skill ------------------------");

        requestData();

        submitData();
    }

    /**
     * Submit the data entered by the user to create a new skill.
     */
    private void submitData() {
        Optional<Skill> skill = getController().createSkill(skillName);

        if (skill.isPresent()) {
            skillRepository.add(skill.get());

            System.out.println(skillRepository.getSkillList());

            System.out.println("\nSkill successfully created!");
        } else {
            System.out.println("\nSkill not created!");
        }
    }

    /**
     * Request data from the user to create a new skill.
     */
    private void requestData() {
        //Request the Skill name from the console
        skillName = requestSkillName();
    }

    /**
     * Request the Skill name from the user via console input.
     *
     * @return the Skill name entered by the user
     */
    private String requestSkillName() {
        Scanner input = new Scanner(System.in);
        System.out.print("Skill name: ");
        return input.nextLine();
    }
}