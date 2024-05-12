# US006 - Register a Vehicle

## 4. Tests

# Test 1: Vehicle Creation

Ensure that it is possible to create a new Vehicle with a valid name.

    void ensureVehicleIsAdddSuccessfully() {
        VFM vfm = new VFM("john.doe@this.company.com");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate date1 = LocalDate.parse("30-04-2024", formatter);
        LocalDate date2 = LocalDate.parse("29-04-2024", formatter);
        LocalDate date3 = LocalDate.parse("01-05-2024", formatter);
        Vehicle vehicle = new Vehicle("Toyota", "Avensis", "Van", 1275, 1820, 30000, date1, date2, 1, "00-AA-00", date3);
    }

# Test 2: Vehicle Plate Validation

Check that an IllegalArgumentException is thrown when attempting to create a Vehicle with a null or empty plate number.


    @Test
    void ensureVehiclePlateNumberIsNotNull() {
        //Arrange
        VFM vfm = new VFM("john.doe@this.company.com");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate date1 = LocalDate.parse("30-04-2024", formatter);
        LocalDate date2 = LocalDate.parse("29-04-2024", formatter);
        LocalDate date3 = LocalDate.parse("01-05-2024", formatter);
        //Act and Assert
        assertThrows(IllegalArgumentException.class,
                () -> new Vehicle("Toyota", "Avensis", "Van", 1275, 1820, 30000, date1, date2, 1, null, date3));
    }

## 5. Construction (Implementation)
* Class AddVehicleController


    public class AddVehicleController {

    private OrganizationRepository organizationRepository;
    private VehicleRepository vehicleRepository;
    private AuthenticationRepository authenticationRepository;


    // Constructors and methods omitted for brevity...

    public Optional<Vehicle> addVehicle(String model, String brand, String type, double tareWeight,
                                        double grossWeight, double currentKm, LocalDate registerDate,
                                        LocalDate acquisitionDate, int maintenanceFrequencyKm, String plateNumber,
                                        LocalDate lastMaintenanceDate, VFM vfm) {
        Optional<Organization> organization = getOrganizationRepository().getOrganizationByVFM(vfm);
        return organization.flatMap(optionalOrganization -> optionalOrganization.createVehicle(model, brand, type, tareWeight, grossWeight,
                currentKm, registerDate, acquisitionDate, maintenanceFrequencyKm, plateNumber, lastMaintenanceDate));
    }

    // Other methods omitted for brevity
    }

* Class AddVehicleUI


    public class AddVehicleUI implements Runnable {

    private final AddVehicleController controller;
    private String vehicleModel, vehicleBrand, vehicleType, vehiclePlateNumber;
    private double vehicleTareWeight, vehicleGrossWeight, vehicleCurrentKm;
    private LocalDate vehicleRegisterDate, vehicleAcquisitionDate, vehicleLastMaintenanceDate;
    private int vehicleMaintenanceFrequencyKm;
    private final VehicleRepository vehicleRepository;

    // Constructors and methods omitted for brevity...

    private void submitData() {
        VFM vfm = getController().getVFMFromSession();
        Optional<Vehicle> vehicle = getController().addVehicle(vehicleModel, vehicleBrand, vehicleType, vehicleTareWeight, vehicleGrossWeight, vehicleCurrentKm, vehicleRegisterDate, vehicleAcquisitionDate, vehicleMaintenanceFrequencyKm, vehiclePlateNumber, vehicleLastMaintenanceDate, vfm);

        if (vehicle.isPresent()) {
            vehicleRepository.add(vehicle.get());
            System.out.println("\nVehicle successfully created!");
        } else {
            System.out.println("\nVehicle not created!");
        }
    }

    // Other methods omitted for brevity...
    }

* Class Organization



    public class Organization {

    // Constructors, getters, and other methods omitted for brevity...

    public Optional<Vehicle> createVehicle(String name) {
        Vehicle vehicle = new Vehicle(name);
        if (addVehicle(vehicle)) {
            return Optional.of(vehicle);
        }
        return Optional.empty();
    }

    private boolean addVehicle(Vehicle vehicle) {
        if (validateVehicle(vehicle)) {
            return vehicles.add(vehicle.clone());
        }
        return false;
    }

    private boolean validateVehicle(Vehicle vehicle) {
        return !vehicles.contains(vehicle);
    }

    // Other methods omitted for brevity...
    }

* Class Vehicle


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

    private void validateTrait(Object trait) {
        if (trait == null || (trait instanceof String && ((String) trait).isEmpty()) || (trait instanceof Number && ((Number) trait).doubleValue() <= 0)) {
            throw new IllegalArgumentException("Trait cannot be null, empty, or less than or equal to 0.");
        }
    }

    // Other methods omitted for brevity...
    }

* Class VehicleRepository


    public class VehicleRepository {

    private List<Vehicle> vehicles;

    // Constructors and other methods omitted for brevity...

    public Optional<Vehicle> add(Vehicle vehicle) {
        if (validateVehicle(vehicle)) {
            vehicles.add(vehicle.clone());
            return Optional.of(vehicle.clone());
        }
        return Optional.empty();
    }

    private boolean validateVehicle(Vehicle vehicle) {
        return vehicles.stream().noneMatch(v -> v.getPlateNumber().equals(vehicle.getPlateNumber()));
    }

    // Other methods omitted for brevity...
    }

## 6. Integration and Demo

* The Vehicle registration functionality has been integrated into the application.
* Demo purposes: Vehicle registration can be accessed via the UI to register new vehicles.

## 7. Observations

n/a