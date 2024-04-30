package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.RegisterCollaboratorController;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.HRM;

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
    private String doctype;
    private int IDnumber;
    private String taskCategoryDescription;
    private String employeeEmail;

    public RegisterCollaboratorUI() {
        controller = new RegisterCollaboratorController();
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
        Optional<Collaborator> collaborator = getController().registerCollaborator(name, birthdate, admissiondate, address, mobile, email, doctype, IDnumber, hrm);

        if (collaborator.isPresent()) {
            System.out.println("\nCollaborator successfully registered!");
        } else {
            System.out.println("\nCollaborator not registered!");
        }
    }

    private void requestData() {

        //Request the Collaborator name from the console
        name = requestCollaboratorName();
        birthdate = requestCollaboratorBirthDate();
        admissiondate = requestCollaboratorAdmissionDate();
        address = requestCollaboratorAddress();
        mobile = requestCollaboratorMobile();
        email = requestCollaboratorEmail();
        doctype = requestCollaboratorDocType();
        IDnumber = requestCollaboratorIDNumber();
    }


    private String requestCollaboratorName() {
        Scanner input = new Scanner(System.in);
        System.out.print("Collaborator name: ");
        return input.nextLine();
    }
    private String requestCollaboratorBirthDate() {
        Scanner input = new Scanner(System.in);
        System.out.print("Collaborator birth date: ");
        return input.nextLine();
    }
    private String requestCollaboratorAdmissionDate() {
        Scanner input = new Scanner(System.in);
        System.out.print("Collaborator admission date: ");
        return input.nextLine();
    }
    private String requestCollaboratorAddress() {
        Scanner input = new Scanner(System.in);
        System.out.print("Collaborator address: ");
        return input.nextLine();
    }
    private int requestCollaboratorMobile() {
        Scanner input = new Scanner(System.in);
        System.out.print("Collaborator mobile number: ");
        return input.nextInt();
    }
    private String requestCollaboratorEmail() {
        Scanner input = new Scanner(System.in);
        System.out.print("Collaborator email: ");
        return input.nextLine();
    }
    private String requestCollaboratorDocType() {
        Scanner input = new Scanner(System.in);
        System.out.print("Collaborator documentation type: ");
        return input.nextLine();
    }
    private int requestCollaboratorIDNumber() {
        Scanner input = new Scanner(System.in);
        System.out.print("Collaborator ID number: ");
        return input.nextInt();
    }
    }