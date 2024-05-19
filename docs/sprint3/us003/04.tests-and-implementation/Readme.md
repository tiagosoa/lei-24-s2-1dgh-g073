# US003 - Register a Collaborator

## 4. Tests

# Test 1: Collaborator Creation

Ensure that it is possible to create a new Collaborator with a valid name.

    @Test
    void ensureCollaboratorIsCreatedSuccessfully() {
        HRM hrm = new HRM("john.doe@this.company.com");
        Collaborator collaborator = new Collaborator("name", "dd-MM-yyyy", "dd-MM-yyyy", "somewhere", 123456789, "johnlemon@beetle.thing", 123456789,"CC", 123456789, hrm);
    }



# Test 2: Collaborator Name Validation

Check that an IllegalArgumentException is thrown when attempting to create a Collaborator with a null or empty name.

        @Test
    void ensureCollaboratorNameIsNotNull() {
        //Arrange
        HRM hrm = new HRM("john.doe@this.company.com");


        //Act and Assert
        assertThrows(IllegalArgumentException.class,
                () -> new Collaborator(null, "dd-MM-yyyy", "dd-MM-yyyy", "somewhere", 123456789, "johnlemon@beetle.thing", 123456789,"CC", 123456789, hrm));
    }

## 5. Construction (Implementation)
* Class RegisterCollaboratorController


    public class RegisterCollaboratorController {

    private OrganizationRepository organizationRepository;
    private CollaboratorRepository collaboratorRepository;
    private AuthenticationRepository authenticationRepository;
    private JobRepository jobRepository;

    // Constructors and methods omitted for brevity...

    public Optional<Collaborator> registerCollaborator(String name, String birthdate, String admissiondate,
                                                       String address, int mobile, String email, int taxpayer, String doctype,
                                                       int IDnumber, HRM hrm) {
        Optional<Organization> organization = organizationRepository.getOrganizationByHRM(hrm);

        Optional<Collaborator> newCollaborator = Optional.empty();

        if (organization.isPresent()) {
            newCollaborator = organization.get()
                    .registerCollaborator(name, birthdate, admissiondate, address, mobile, email, taxpayer, doctype, IDnumber, hrm);
            if (newCollaborator.isPresent()) {
                newCollaborator.get().setJobs(new ArrayList<>());
            }
        }

        if (organization.isPresent()) {
            newCollaborator.ifPresent(collaboratorRepository::add);
            return newCollaborator;
        }

        return Optional.empty();
    }

    // Other methods omitted for brevity
    }

* Class RegisterCollaboratorUI


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

    // Constructors and methods omitted for brevity...

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

    // Other methods omitted for brevity...
    }

* Class Organization



    public class Organization {

    // Constructors, getters, and other methods omitted for brevity...

    public Optional<Collaborator> registerCollaborator(String name, String birthdate, String admissiondate, String address, int mobile, String email, int taxpayer, String doctype, int IDnumber, HRM hrm) {
        Collaborator collaborator = new Collaborator(name, birthdate, admissiondate, address, mobile, email, taxpayer, doctype, IDnumber, hrm);
        if (addCollaborator(collaborator)) {
            return Optional.of(collaborator);
        }
        return Optional.empty();
    }

    private boolean addCollaborator(Collaborator collaborator) {
        if (validateCollaborator(collaborator)) {
            return collaborators.add(collaborator.clone());
        }
        return false;
    }

    private boolean validateCollaborator(Collaborator collaborator) {
        return !collaborators.contains(collaborator);
    }

    // Other methods omitted for brevity...
    }

* Class Collaborator


    public class Collaborator {

    // Constructors, getters, and other methods omitted for brevity...

    public Collaborator(String name, String birthdate, String admissiondate, String address, int mobile, String email, int taxpayer, String doctype, int IDnumber, HRM hrm) {
        validateCollaborator(name, birthdate, admissiondate, address, mobile, email, taxpayer, doctype, IDnumber);
        this.name = name;
        this.birthdate = birthdate;
        this.admissiondate = admissiondate;
        this.address = address;
        this.mobile = mobile;
        this.email = email;
        this.taxpayer = taxpayer;
        this.doctype = doctype;
        this.IDnumber = IDnumber;
        this.hrm = hrm;
    }

    private void validateCollaborator(String name, String birthdate, String admissiondate, String address, int mobile, String email, int taxpayer, String doctype, int IDnumber) {
        if (name == null || name.isEmpty() || !name.matches("[a-zA-Z]+")) {
            throw new IllegalArgumentException("Collaborator name cannot be null, empty, or contain special characters or digits.");
        }
        if (birthdate == null || birthdate.isEmpty()) {
            throw new IllegalArgumentException("Collaborator birth date cannot be null or empty.");
        }
        if (admissiondate == null || admissiondate.isEmpty()) {
            throw new IllegalArgumentException("Collaborator admission date cannot be null or empty.");
        }
        if (address == null || address.isEmpty()) {
            throw new IllegalArgumentException("Collaborator address cannot be null or empty.");
        }
        if (mobile <= 0) {
            throw new IllegalArgumentException("Collaborator mobile number must be a positive number.");
        }
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Collaborator email cannot be empty.");
        }
        if (taxpayer <= 0) {
            throw new IllegalArgumentException("Collaborator taxpayer number must be a positive number.");
        }
        if (doctype == null || doctype.isEmpty()) {
            throw new IllegalArgumentException("Collaborator documentation type cannot be empty.");
        }
        if (IDnumber <= 0) {
            throw new IllegalArgumentException("Collaborator ID number must be a positive number.");
        }
    }

    // Other methods omitted for brevity...
    }

* Class CollaboratorRepository


    public class CollaboratorRepository {

    // Constructors and other methods omitted for brevity...

    public Optional<Collaborator> add(Collaborator collaborator) {
        Optional<Collaborator> newCollaborator = Optional.empty();
        boolean operationSuccess = false;

        if (validateCollaborator(collaborator)) {
            newCollaborator = Optional.of(collaborator.clone());
            operationSuccess = collaborators.add(newCollaborator.get());
        }

        if (!operationSuccess) {
            newCollaborator = Optional.empty();
        }

        return newCollaborator;
    }

    private boolean validateCollaborator(Collaborator collaborator) {
        return !collaborators.contains(collaborator);
    }

    // Other methods omitted for brevity...
    }

## 6. Integration and Demo

* The Collaborator registration functionality has been integrated into the application.
* Demo purposes: Collaborator registration can be accessed via the UI to register new collaborators.

## 7. Observations

n/a