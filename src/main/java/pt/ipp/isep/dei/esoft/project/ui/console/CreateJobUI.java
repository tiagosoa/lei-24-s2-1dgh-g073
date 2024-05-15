package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.CreateJobController;
import pt.ipp.isep.dei.esoft.project.domain.Job;
import pt.ipp.isep.dei.esoft.project.domain.HRM;
import pt.ipp.isep.dei.esoft.project.repository.JobRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.util.Optional;
import java.util.Scanner;

/**
 * Create Job UI (console). This option is only available for administrators for demonstration purposes.
 */
public class CreateJobUI implements Runnable {

    private final CreateJobController controller;
    private String jobName;

    private JobRepository jobRepository;
    private String taskCategoryDescription;
    private String employeeEmail;

    /**
     * Constructor for CreateJobUI class.
     * Initializes the controller and jobRepository.
     */
    public CreateJobUI() {
        controller = new CreateJobController();
        this.jobRepository = Repositories.getInstance().getJobRepository();
    }

    /**
     * Retrieves the controller instance.
     *
     * @return the CreateJobController instance
     */
    private CreateJobController getController() {
        return controller;
    }

    /**
     * Runs the Create Job UI flow.
     */
    public void run() {
        System.out.println("\n\n--- Create Job ------------------------");

        requestData();

        submitData();
    }

    /**
     * Submits the data input by the user to create a new job.
     */
    private void submitData() {
        HRM hrm = getController().getHRMFromSession();
        Optional<Job> job = getController().createJob(jobName, hrm);

        if (job.isPresent()) {
            jobRepository.add(job.get());
            System.out.println("\nJob successfully created!");
        } else {
            System.out.println("\nJob not created!");
        }
    }

    /**
     * Requests necessary data from the user.
     */
    private void requestData() {
        jobName = requestJobName();
    }

    /**
     * Requests the user to input the job name.
     *
     * @return the job name input by the user
     */
    private String requestJobName() {
        Scanner input = new Scanner(System.in);
        System.out.print("Job name: ");
        return input.nextLine();
    }
}