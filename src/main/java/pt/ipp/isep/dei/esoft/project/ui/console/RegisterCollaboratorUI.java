package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.RegisterCollaboratorController;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Job;
import pt.ipp.isep.dei.esoft.project.repository.CollaboratorRepository;
import pt.ipp.isep.dei.esoft.project.repository.JobRepository;
import java.time.format.DateTimeParseException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import static java.awt.SystemColor.menu;
import static java.lang.System.exit;

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
        this.collaboratorRepository = getController().getCollaboratorRepository();
        this.jobRepository = getController().getJobRepository();
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
        Scanner input = new Scanner(System.in);
        Optional<Collaborator> collaborator = getController().registerCollaborator(name, birthdate, admissiondate, address, mobile, email, taxpayer, doctype, IDnumber);

        if (collaborator.isPresent()) {
            String jobname = assignJobToCollaborator(collaborator.get());
            System.out.println("'" + name + "'" + "'" + birthdate + "'" + "'" + admissiondate + "'" + "'" + address + "'" + "'" + mobile + "'" + "'" + email + "'" + "'" + taxpayer + "'" + "'" + doctype + "'" + "'" + IDnumber + "'" + "'" + jobname + "'" + "- is this data correct? (type 'yes' or 'no')");
            String yesno;
            do {
                yesno = input.nextLine();
                if (yesno.equals("no")) {
                    requestData();
                }
            } while (!(yesno.equals("no") || yesno.equals("yes")));
            collaboratorRepository.addCollaborator(collaborator.get());
            System.out.println("\nCollaborator successfully registered!");
        } else {
            System.out.println("\nCollaborator not registered!");
        }
    }

    private void requestData() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        name = requestCollaboratorName();
        while(!name.matches("[a-zA-Z]+")){
            System.out.println("This name " + name + " is not valid.");
            name = requestCollaboratorName();
        }

        birthdate = requestCollaboratorBirthDate();

        while (true) {
            try {
                LocalDate.parse(birthdate, formatter);
                break; // Data válida, sai do loop
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Use the format dd-MM-yyyy.");
            }
            birthdate = requestCollaboratorBirthDate();
        }

        admissiondate = requestCollaboratorAdmissionDate();
        while (true) {
            try {
                LocalDate admissionDateParsed = LocalDate.parse(admissiondate, formatter);
                if (isOlderThan18(admissionDateParsed)) {
                    break; // Data válida, sai do loop
                } else {
                    System.out.println("A data precisa ser superior a 18 anos da data atual.");
                }
            } catch (DateTimeParseException e) {
                System.out.println("Formato de data inválido. Use o formato dd-MM-yyyy.");
            }
            admissiondate = requestCollaboratorAdmissionDate();
        }

        address = requestCollaboratorAddress();
        String pattern = "^(?:[\\p{L}0-9ªº,]+(?:\\s|$))+";
        while (true) {

            Pattern compiledPattern = Pattern.compile(pattern);
            Matcher matcher = compiledPattern.matcher(address);

            if (matcher.matches()) {
                break; // Endereço válido, sai do loop
            } else {
                System.out.println("Invalid address format.");
            }
            address = requestCollaboratorAddress();
        }


        mobile = requestCollaboratorMobile();
        pattern = "^9\\d{8}$"; // Começa com 9 e tem 9 dígitos no total
        while (true) {
            try {

                // Converter para string para validação
                String phoneNumberStr = Integer.toString(mobile);

                // Verifica se a entrada corresponde ao padrão
                Pattern compiledPattern = Pattern.compile(pattern);
                Matcher matcher = compiledPattern.matcher(phoneNumberStr);

                if (matcher.matches()) {
                    break; // Número de telefone válido, sai do loop
                } else {
                    System.out.println("Invalid phone number format. It must have 9 digits and start with 9.");
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a valid 9-digit phone number starting with 9.");
            }
            mobile = requestCollaboratorMobile();
        }

        email = requestCollaboratorEmail();
        pattern = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        while (true) {
            Pattern compiledPattern = Pattern.compile(pattern);
            Matcher matcher = compiledPattern.matcher(email);

            if (matcher.matches()) {
                System.out.println("Valid email address: " + email);
                break; // Endereço de email válido, sai do loop
            } else {
                System.out.println("Invalid email address format.");
            }
            email = requestCollaboratorEmail();
        }

        taxpayer = requestCollaboratorTaxpayerNumber();//rever
        pattern = "^[1-9]\\d{8}$";
        while (true) {
            String taxpayerStr = Integer.toString(taxpayer);

            Pattern compiledPattern = Pattern.compile(pattern);
            Matcher matcher = compiledPattern.matcher(taxpayerStr);

            if (matcher.matches()) {
                break; // NIF válido, sai do loop
            } else {
                System.out.println("Invalid Portuguese taxpayer number format. It must have 9 digits.");
            }
            taxpayer = requestCollaboratorTaxpayerNumber();
        }

        doctype = requestCollaboratorDocType();// validar atravéz de uma lista

        IDnumber = requestCollaboratorIDNumber();//rever
        pattern = "^[1-8]\\d{7}$";
        while (true) {
            String IDnumberStr = Integer.toString(IDnumber);

            Pattern compiledPattern = Pattern.compile(pattern);
            Matcher matcher = compiledPattern.matcher(IDnumberStr);

            if (matcher.matches()) {
                break; // Nº CC válido, sai do loop
            } else {
                System.out.println("Invalid Portuguese ID number format. It must have 9 digits.");
            }
            IDnumber = requestCollaboratorIDNumber();
        }

    }


    // Request collaborator name from the user
    private String requestCollaboratorName() {
        Scanner input = new Scanner(System.in);
        System.out.print("Collaborator name: ");
        return input.nextLine();
    }

    // Request collaborator birthdate from the user
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
    private String assignJobToCollaborator(Collaborator collaborator) {
        Scanner input = new Scanner(System.in);
        List<Job> jobs = jobRepository.getJobs();
        if (jobs.isEmpty()) {
            System.out.println("No jobs registered.");
            //exit(1);
        }
        System.out.println("Select job(s) to assign to " + collaborator.getName() + ":");
        for (int i = 0; i < jobs.size(); i++) {
            System.out.println((i + 1) + ". " + jobs.get(i).getName());
        }
        System.out.println("Enter job name:");
        String jobname = input.nextLine();

        // Assign selected jobs to collaborator
        getController().assignJobToCollaborator(collaborator.getIDNumber(), jobname);
        System.out.println("Jobs assigned successfully to " + collaborator.getName());
        return jobname;
    }
    public static boolean isOlderThan18(LocalDate date) {
        LocalDate today = LocalDate.now();
        Period period = Period.between(date, today);
        return period.getYears() >= 18;
    }
}