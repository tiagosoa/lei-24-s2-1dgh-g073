package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.RegisterCollaboratorController;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.HRM;
import pt.ipp.isep.dei.esoft.project.domain.Job;
import pt.ipp.isep.dei.esoft.project.repository.CollaboratorRepository;
import pt.ipp.isep.dei.esoft.project.repository.JobRepository;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

/**
 * Register Collaborator UI (console). This option is only available for administrators for demonstration purposes.
 */
public class RegisterCollaboratorUI implements Runnable {

    private final RegisterCollaboratorController controller;
    private String name;

    private String birthdate;
    private String admissiondate;
    private String address;

    private int mobile;
    private String email;

    private int taxpayer;
    private String doctype;
    private int IDnumber;
    private CollaboratorRepository collaboratorRepository;
    private JobRepository jobRepository;

    public RegisterCollaboratorUI() {

        controller = new RegisterCollaboratorController();
        this.collaboratorRepository = new CollaboratorRepository();
        this.jobRepository = new JobRepository();
    }

    private RegisterCollaboratorController getController() {
        return controller;
    }

    public void run() {
        System.out.println("\n\n--- Register Collaborator ------------------------");

        requestData();

        submitData();
    }

    private void submitData() {

        HRM hrm = getController().getHRMFromSession();
        Optional<Collaborator> collaborator = getController().registerCollaborator(name, birthdate, admissiondate, address, mobile, email, taxpayer, doctype, IDnumber, hrm);

        if (collaborator.isPresent()) {
            assignJobToCollaborator(collaborator.get());
            System.out.println("\nCollaborator successfully registered!");
        } else {
            System.out.println("\nCollaborator not registered!");
        }
    }

    private void requestData() {

        // Request the necessary data from the console
        name = requestCollaboratorName();
        birthdate = requestCollaboratorBirthDate();
        admissiondate = requestCollaboratorAdmissionDate();
        address = requestCollaboratorAddress();
        mobile = requestCollaboratorMobile();
        email = requestCollaboratorEmail();
        taxpayer = requestCollaboratorTaxpayerNumber();
        doctype = requestCollaboratorDocType();
        IDnumber = requestCollaboratorIDNumber();
    }


    // Request collaborator name from the user
    private String requestCollaboratorName() {
        Scanner input = new Scanner(System.in);
        System.out.print("Collaborator name: ");
        return input.nextLine();
    }

    // Request collaborator birth date from the user
    private String requestCollaboratorBirthDate() {
        Scanner input = new Scanner(System.in);
        System.out.print("Collaborator birth date: ");
        return input.nextLine();
    }

    // Request collaborator admission date from the user
    private String requestCollaboratorAdmissionDate() {
        Scanner input = new Scanner(System.in);
        System.out.print("Collaborator admission date: ");
        return input.nextLine();
    }

    // Request collaborator address from the user
    private String requestCollaboratorAddress() {
        Scanner input = new Scanner(System.in);
        System.out.print("Collaborator address: ");
        return input.nextLine();
    }

    // Request collaborator mobile number from the user
    private int requestCollaboratorMobile() {
        Scanner input = new Scanner(System.in);
        System.out.print("Collaborator mobile number: ");
        return input.nextInt();
    }

    // Request collaborator email from the user
    private String requestCollaboratorEmail() {
        Scanner input = new Scanner(System.in);
        System.out.print("Collaborator email: ");
        return input.nextLine();
    }

    // Request collaborator taxpayer number from the user
    private int requestCollaboratorTaxpayerNumber() {
        Scanner input = new Scanner(System.in);
        System.out.print("Collaborator Taxpayer number: ");
        return input.nextInt();
    }

    // Request collaborator documentation type from the user
    private String requestCollaboratorDocType() {
        Scanner input = new Scanner(System.in);
        System.out.print("Collaborator documentation type: ");
        return input.nextLine();
    }

    // Request collaborator ID number from the user
    private int requestCollaboratorIDNumber() {
        Scanner input = new Scanner(System.in);
        System.out.print("Collaborator ID number: ");
        return input.nextInt();
    }

    // Assign job(s) to a collaborator
    private void assignJobToCollaborator(Collaborator collaborator) {
        Scanner input = new Scanner(System.in);
        List<Job> jobs = jobRepository.getJobs();
        if (jobs.isEmpty()) {
            System.out.println("No jobs registered.");
            return;
        }
        System.out.println("Select job(s) to assign to " + collaborator.getName() + ":");
        for (int i = 0; i < jobs.size(); i++) {
            System.out.println((i + 1) + ". " + jobs.get(i).getName());
        }
        System.out.println("Enter job number:");
        String jobname = input.nextLine();

        // Assign selected jobs to collaborator
        getController().assignJobToCollaborator(collaborator.getIDNumber(), jobname);
        System.out.println("Jobs assigned successfully to " + collaborator.getName());
    }
}