package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.CreateSkillController;
import pt.ipp.isep.dei.esoft.project.domain.Skill;

import java.util.Optional;
import java.util.Scanner;
import pt.ipp.isep.dei.esoft.project.domain.HRM;

/**
 * Create Task UI (console). This option is only available for administrators for demonstration purposes.
 */
public class CreateSkillUI implements Runnable {

    private final CreateSkillController controller;
    private String skillName;
    private String taskCategoryDescription;
    private String empployeeEmail;

    public CreateSkillUI() {
        controller = new CreateSkillController();
    }

    private CreateSkillController getController() {
        return controller;
    }

    public void run() {
        System.out.println("\n\n--- Create Skill ------------------------");

        requestData();

        submitData();
    }

    private void submitData() {

        HRM hrm = getController().getHRMFromSession();
        Optional<Skill> skill = getController().createSkill(skillName, hrm);

        if (skill.isPresent()) {
            System.out.println("\nSkill successfully created!");
        } else {
            System.out.println("\nSkill not created!");
        }
    }

    private void requestData() {

        //Request the Skill name from the console
        skillName = requestSkillName();
    }


    private String requestSkillName() {
        Scanner input = new Scanner(System.in);
        System.out.print("Skill name: ");
        return input.nextLine();
    }
    }