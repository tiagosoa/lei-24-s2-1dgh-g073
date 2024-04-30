package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.CreateJobController;
import pt.ipp.isep.dei.esoft.project.domain.Job;
import pt.ipp.isep.dei.esoft.project.domain.HRM;

import java.util.Optional;
import java.util.Scanner;

/**
 * Create Job UI (console). This option is only available for administrators for demonstration purposes.
 */
public class CreateJobUI implements Runnable {

    private final CreateJobController controller;
    private String jobName;
    private String taskCategoryDescription;
    private String employeeEmail;

    public CreateJobUI() {

        controller = new CreateJobController();

    }

    private CreateJobController getController() {
        return controller;
    }

    public void run() {
        System.out.println("\n\n--- Create Job ------------------------");

        requestData();

        submitData();
    }

    private void submitData() {

        HRM hrm = getController().getHRMFromSession();
        Optional<Job> job = getController().createJob(jobName, hrm);

        if (job.isPresent()) {
            System.out.println("\nJob successfully created!");
        } else {
            System.out.println("\nJob not created!");
        }
    }

    private void requestData() {

        //Request the Job name from the console
        jobName = requestJobName();
    }


    private String requestJobName() {
        Scanner input = new Scanner(System.in);
        System.out.print("Job name: ");
        return input.nextLine();
    }
    }