# US007 - Assign a Skill

## 4. Tests

# Test 1: Maintenance Registration

Ensure that it is possible to register a maintenance date to an existing Vehicle.

    @Test
    void ensureMaintenanceIsRegisteredSuccessfully() {
        // Arrange
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate acquisitionDate = LocalDate.parse("01-01-2024", formatter);
        LocalDate lastMaintenanceDate = LocalDate.parse("01-04-2024", formatter);
        Vehicle vehicle = new Vehicle("Toyota", "Avensis", "Van", 1275, 1820, 30000,
                LocalDate.now(), acquisitionDate, 5000,
                "00-AA-00", lastMaintenanceDate);



        RegisterMaintenanceController controller = new RegisterMaintenanceController();
        LocalDate maintenanceDate = LocalDate.now();
        Optional<Boolean> result = controller.registerMaintenance(vehicle.getPlateNumber(), 35000, maintenanceDate);

        // Assert
        assertTrue(result.isPresent());
        assertTrue(result.get());
        assertEquals(maintenanceDate, vehicle.getLastMaintenanceDate());
        assertEquals(35000, vehicle.getCurrentKm());
    }


## 5. Construction (Implementation)
* Class RegisterMaintenanceController


    public class RegisterMaintenanceController {

    private OrganizationRepository organizationRepository;
    private VehicleRepository vehicleRepository;
    private AuthenticationRepository authenticationRepository;

    // Constructors and methods omitted for brevity...

    public Optional<Boolean> registerMaintenance(String plateNumber, int currentKm, LocalDate maintenanceDate) {
        try {
            Vehicle vehicle = vehicleRepository.getVehicleByPlateNumber(plateNumber);

            if (maintenanceDate.isAfter(LocalDate.now())) {
                System.out.println("Maintenance date cannot be in the future.");
                return Optional.empty();
            } else {
                vehicle.setCurrentKilometers(currentKm);
                vehicle.setMaintenanceDate(maintenanceDate);
                return Optional.of(true);
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return Optional.empty();
        }
    }

    // Other methods omitted for brevity
    }

* Class RegisterMaintenanceUI


    public class RegisterMaintenanceUI implements Runnable {

    private final RegisterMaintenanceController controller;
    private String vehiclePlateNumber;
    private int currentKM;
    private LocalDate maintenanceDate;

    // Constructors and methods omitted for brevity...

    private void submitData() {
        Optional<Boolean> result = getController().registerMaintenance(vehiclePlateNumber, currentKM, maintenanceDate);
        System.out.println(result.orElse(false) ? "\nMaintenance successfully registered!" : "\nFailed to register maintenance!");
    }

    // Other methods omitted for brevity...
    }

* Class Collaborator


    public class Vehicle {
    private final String model;
    private final String brand;
    private final String type;
    private final double tareWeight;
    private final double grossWeight;
    private double currentKm;
    private final LocalDate registerDate;
    private final LocalDate acquisitionDate;
    private final int maintenanceFrequencyKm;
    private final String plateNumber;
    private LocalDate lastMaintenanceDate;

    // Constructors, getters, and other methods omitted for brevity...

    public Vehicle(String model, String brand, String type, double tareWeight, double grossWeight,
                   double currentKm, LocalDate registerDate, LocalDate acquisitionDate, int maintenanceFrequencyKm, String plateNumber, LocalDate lastMaintenanceDate) {
        validateTrait(model);
        validateTrait(brand);
        validateTrait(type);
        validateTrait(tareWeight);
        validateTrait(grossWeight);
        validateTrait(currentKm);
        validateTrait(registerDate);
        validateTrait(acquisitionDate);
        validateTrait(maintenanceFrequencyKm);
        validateTrait(plateNumber);
        validateTrait(lastMaintenanceDate);

        this.model = model;
        this.brand = brand;
        this.type = type;
        this.tareWeight = tareWeight;
        this.grossWeight = grossWeight;
        this.currentKm = currentKm;
        this.registerDate = registerDate;
        this.acquisitionDate = acquisitionDate;
        this.maintenanceFrequencyKm = maintenanceFrequencyKm;
        this.plateNumber = plateNumber;
        this.lastMaintenanceDate = lastMaintenanceDate;
    }

        public void setMaintenanceDate(LocalDate lastMaintenanceDate) {
        this.lastMaintenanceDate = lastMaintenanceDate;
    }

    // Other methods omitted for brevity...
    }

## 6. Integration and Demo

* The Maintenance Registration functionality has been integrated into the application.
* Demo purposes: Maintenance Registration can be accessed via the UI to register maintenances to vehicles.

## 7. Observations

n/a