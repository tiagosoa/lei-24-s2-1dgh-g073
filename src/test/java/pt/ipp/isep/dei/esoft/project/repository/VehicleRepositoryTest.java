package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.HRM;
import pt.ipp.isep.dei.esoft.project.repository.VehicleRepository;
import pt.ipp.isep.dei.esoft.project.domain.VFM;
import pt.ipp.isep.dei.esoft.project.domain.Vehicle;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class VehicleRepositoryTest {

    @Test
    void getVehicleByPlateNumberEmptyList() {
        VehicleRepository vehicleRepository = new VehicleRepository();
        String vehiclePlateNumber = "00-AA-00";
        
        assertThrows(IllegalArgumentException.class,
                () -> vehicleRepository.getVehicleByPlateNumber(vehiclePlateNumber));
    }

    @Test
    void getVehiclebyPlateNumberNullList() {
        VehicleRepository vehicleRepository = new VehicleRepository();
        String vehiclePlateNumber = "00-AA-00";
        
        assertThrows(IllegalArgumentException.class,
                () -> vehicleRepository.getVehicleByPlateNumber(vehiclePlateNumber));
    }

    @Test
    void ensureNewVehicleSuccessfullyAdded() {
        VehicleRepository vehicleRepository = new VehicleRepository();
        String vehiclePlateNumber = "00-AA-00";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate date1 = LocalDate.parse("30-04-2024", formatter);
        LocalDate date2 = LocalDate.parse("29-04-2024", formatter);
        LocalDate date3 = LocalDate.parse("01-05-2024", formatter);
        Vehicle vehicle = new Vehicle("Toyota", "Avensis", "Van", 1275, 1820, 30000, date1, date2, 1, vehiclePlateNumber, date3);
        vehicleRepository.addVehicle(vehicle);
    }

    @Test
    void testThatCreateVehicleWorks() {
        VehicleRepository vehicleRepository = new VehicleRepository();

        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate date1 = LocalDate.parse("30-04-2024", formatter);
        LocalDate date2 = LocalDate.parse("29-04-2024", formatter);
        LocalDate date3 = LocalDate.parse("01-05-2024", formatter);

        Vehicle expected = new Vehicle("Toyota", "Avensis", "Van", 1275, 1820, 30000, date1, date2, 1, "00-AA-00", date3);

        Optional<Vehicle> vehicle =
                vehicleRepository.createVehicle("Toyota", "Avensis", "Van", 1275, 1820, 30000, date1, date2, 1, "00-AA-00", date3);

        assertNotNull(vehicle);
        assertTrue(vehicle.isPresent());
        assertEquals(expected, vehicle.get());
    }
    @Test
    void ensureGetVehicleForExistingVehicle() {
        VehicleRepository vehicleRepository = new VehicleRepository();
        String vehiclePlateNumber = "00-AA-00";
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate date1 = LocalDate.parse("30-04-2024", formatter);
        LocalDate date2 = LocalDate.parse("29-04-2024", formatter);
        LocalDate date3 = LocalDate.parse("01-05-2024", formatter);
        Vehicle vehicle = new Vehicle("Toyota", "Avensis", "Van", 1275, 1820, 30000, date1, date2, 1, "00-AA-00", date3);
        vehicleRepository.addVehicle(vehicle);
        Vehicle vehicle1 = vehicleRepository.getVehicleByPlateNumber(vehiclePlateNumber);
        assertEquals(vehicle, vehicle1);
    }

    @Test
    void ensureGetVehicleFailsForNonExistingVehicle() {
        VehicleRepository vehicleRepository = new VehicleRepository();
        String vehiclePlateNumber = "00-AA-00";
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate date1 = LocalDate.parse("30-04-2024", formatter);
        LocalDate date2 = LocalDate.parse("29-04-2024", formatter);
        LocalDate date3 = LocalDate.parse("01-05-2024", formatter);
        Vehicle vehicle = new Vehicle("Toyota", "Avensis", "Van", 1275, 1820, 30000, date1, date2, 1, "00-AA-00", date3);
        vehicleRepository.addVehicle(vehicle);
        String vehiclePlateNumber1 = "01-AB-01";
        assertThrows(IllegalArgumentException.class,
                () -> vehicleRepository.getVehicleByPlateNumber(vehiclePlateNumber1));

    }

    @Test
    void ensureGetVehicleReturnsAnImmutableList() {
        VehicleRepository vehicleRepository = new VehicleRepository();
        String vehiclePlateNumber = "00-AA-00";
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate date1 = LocalDate.parse("30-04-2024", formatter);
        LocalDate date2 = LocalDate.parse("29-04-2024", formatter);
        LocalDate date3 = LocalDate.parse("01-05-2024", formatter);
        Vehicle vehicle = new Vehicle("Toyota", "Avensis", "Van", 1275, 1820, 30000, date1, date2, 1, "00-AA-00", date3);
        vehicleRepository.addVehicle(vehicle);

        assertThrows(UnsupportedOperationException.class,
                () -> vehicleRepository.getVehicles().add(new Vehicle("Toyota", "Avensis", "Van", 1275, 1820, 30000, date1, date2, 1, "00-AA-00", date3)));

    }

    @Test
    void ensureGetVehicleReturnsTheCorrectList() {
        //Arrange
        VehicleRepository vehicleRepository = new VehicleRepository();
        
        String vehiclePlateNumber = "00-AA-00";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate date1 = LocalDate.parse("30-04-2024", formatter);
        LocalDate date2 = LocalDate.parse("29-04-2024", formatter);
        LocalDate date3 = LocalDate.parse("01-05-2024", formatter);
        Vehicle vehicle = new Vehicle("Toyota", "Avensis", "Van", 1275, 1820, 30000, date1, date2, 1, "00-AA-00", date3);
        vehicleRepository.addVehicle(vehicle);
        int expectedSize = 1;

        //Act
        int size = vehicleRepository.getVehicles().size();

        //Assert
        assertEquals(expectedSize, size);
        assertEquals(vehicle, vehicleRepository.getVehicles().get(size - 1));
    }

    @Test
    void ensureAddingDuplicateVehicleFails() {
        //Arrange
        VehicleRepository vehicleRepository = new VehicleRepository();
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate date1 = LocalDate.parse("30-04-2024", formatter);
        LocalDate date2 = LocalDate.parse("29-04-2024", formatter);
        LocalDate date3 = LocalDate.parse("01-05-2024", formatter);
        Vehicle vehicle = new Vehicle("Toyota", "Avensis", "Van", 1275, 1820, 30000, date1, date2, 1, "00-AA-00", date3);
        //Add the first vehicle
        vehicleRepository.addVehicle(vehicle);

        //Act
        boolean duplicateVehicle = vehicleRepository.addVehicle(vehicle);

        //Assert
        assertFalse(duplicateVehicle);
    }

    @Test
    void ensureMaintenanceIsRegisteredSuccessfully() {
        VehicleRepository vehicleRepository = new VehicleRepository();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate acquisitionDate = LocalDate.parse("01-01-2024", formatter);
        LocalDate lastMaintenanceDate = LocalDate.parse("01-04-2024", formatter);
        Vehicle vehicle = new Vehicle("Toyota", "Avensis", "Van", 1275, 1820, 30000, LocalDate.now(), acquisitionDate, 5000, "00-AA-00", lastMaintenanceDate);
        vehicleRepository.addVehicle(vehicle);
        LocalDate maintenanceDate = LocalDate.now();

        // Act
        boolean result = vehicleRepository.registerMaintenance(vehicle.getPlateNumber(), 35000, maintenanceDate);

        // Assert
        assertTrue(result);
        assertEquals(maintenanceDate, vehicle.getLastMaintenanceDate());
        assertEquals(35000, vehicle.getCurrentKm());
    }

    @Test
    void ensureAddingDifferentVehiclesWorks() {
        //Arrange
        VehicleRepository vehicleRepository = new VehicleRepository();
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate date1 = LocalDate.parse("30-04-2024", formatter);
        LocalDate date2 = LocalDate.parse("29-04-2024", formatter);
        LocalDate date3 = LocalDate.parse("01-05-2024", formatter);
        Vehicle vehicleOne = new Vehicle("Toyota", "Avensis", "Van", 1275, 1820, 30000, date1, date2, 1, "00-AA-00", date3);
        Vehicle vehicleTwo = new Vehicle("Ford", "Focus", "Car", 1275, 1820, 30000, date1, date2, 1, "01-AB-01", date3);
        //Add the first task
        vehicleRepository.addVehicle(vehicleOne);

        //Act
        boolean result = vehicleRepository.addVehicle(vehicleTwo);

        //Assert
        assertTrue(result);
    }
}