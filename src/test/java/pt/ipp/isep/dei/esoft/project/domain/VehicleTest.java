package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.application.controller.RegisterMaintenanceController;
import pt.ipp.isep.dei.esoft.project.repository.VehicleRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class VehicleTest {

    @Test
    void ensureVehicleIsCreatedSuccessfully() {
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate date1 = LocalDate.parse("30-04-2024", formatter);
        LocalDate date2 = LocalDate.parse("29-04-2024", formatter);
        LocalDate date3 = LocalDate.parse("01-05-2024", formatter);
        Vehicle vehicle = new Vehicle("Toyota", "Avensis", "Van", 1275, 1820, 30000, date1, date2, 1, "00-AA-00", date3);
    }

    @Test
    void ensureVehiclePlateNumberIsNotNull() {
        //Arrange
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate date1 = LocalDate.parse("30-04-2024", formatter);
        LocalDate date2 = LocalDate.parse("29-04-2024", formatter);
        LocalDate date3 = LocalDate.parse("01-05-2024", formatter);
        //Act and Assert
        assertThrows(IllegalArgumentException.class,
                () -> new Vehicle("Toyota", "Avensis", "Van", 1275, 1820, 30000, date1, date2, 1, null, date3));
    }

    @Test
    void testEqualsSameObject() {
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate date1 = LocalDate.parse("30-04-2024", formatter);
        LocalDate date2 = LocalDate.parse("29-04-2024", formatter);
        LocalDate date3 = LocalDate.parse("01-05-2024", formatter);
        Vehicle vehicle = new Vehicle("Toyota", "Avensis", "Van", 1275, 1820, 30000, date1, date2, 1, "00-AA-00", date3);
        assertEquals(vehicle, vehicle);
    }

    @Test
    void testEqualsDifferentClass() {
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate date1 = LocalDate.parse("30-04-2024", formatter);
        LocalDate date2 = LocalDate.parse("29-04-2024", formatter);
        LocalDate date3 = LocalDate.parse("01-05-2024", formatter);
        Vehicle vehicle = new Vehicle("Toyota", "Avensis", "Van", 1275, 1820, 30000, date1, date2, 1, "00-AA-00", date3);
        assertNotEquals(vehicle, new Object());
    }

    @Test
    void testEqualsNull() {
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate date1 = LocalDate.parse("30-04-2024", formatter);
        LocalDate date2 = LocalDate.parse("29-04-2024", formatter);
        LocalDate date3 = LocalDate.parse("01-05-2024", formatter);
        Vehicle vehicle = new Vehicle("Toyota", "Avensis", "Van", 1275, 1820, 30000, date1, date2, 1, "00-AA-00", date3);
        assertNotEquals(vehicle, null);
    }

    @Test
    void testEqualsDifferentObjects() {
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate date1 = LocalDate.parse("30-04-2024", formatter);
        LocalDate date2 = LocalDate.parse("29-04-2024", formatter);
        LocalDate date3 = LocalDate.parse("01-05-2024", formatter);
        Vehicle vehicle = new Vehicle("Toyota", "Avensis", "Van", 1275, 1820, 30000, date1, date2, 1, "00-AA-00", date3);
        Vehicle vehicle1 = new Vehicle("Ford", "Focus", "Car", 1275, 1820, 30000, date1, date2, 1, "01-AB-01", date3);
        assertNotEquals(vehicle, vehicle1);
    }

    @Test
    void testHashCodeSameObject() {
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate date1 = LocalDate.parse("30-04-2024", formatter);
        LocalDate date2 = LocalDate.parse("29-04-2024", formatter);
        LocalDate date3 = LocalDate.parse("01-05-2024", formatter);
        Vehicle vehicle = new Vehicle("Toyota", "Avensis", "Van", 1275, 1820, 30000, date1, date2, 1, "00-AA-00", date3);
        assertEquals(vehicle.hashCode(), vehicle.hashCode());
    }

    @Test
    void testHashCodeDifferentObject() {
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate date1 = LocalDate.parse("30-04-2024", formatter);
        LocalDate date2 = LocalDate.parse("29-04-2024", formatter);
        LocalDate date3 = LocalDate.parse("01-05-2024", formatter);
        Vehicle vehicle = new Vehicle("Toyota", "Avensis", "Van", 1275, 1820, 30000, date1, date2, 1, "00-AA-00", date3);
        Vehicle vehicle1 = new Vehicle("Ford", "Focus", "Car", 1275, 1820, 30000, date1, date2, 1, "01-AB-01", date3);


        assertNotEquals(vehicle.hashCode(), vehicle1.hashCode());
    }

    @Test
    void ensureCloneWorks() {
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate date1 = LocalDate.parse("30-04-2024", formatter);
        LocalDate date2 = LocalDate.parse("29-04-2024", formatter);
        LocalDate date3 = LocalDate.parse("01-05-2024", formatter);
        Vehicle vehicle = new Vehicle("Toyota", "Avensis", "Van", 1275, 1820, 30000, date1, date2, 1, "00-AA-00", date3);
        Vehicle clone = vehicle.clone();
        assertEquals(vehicle, clone);
    }

    @Test
    void ensureMaintenanceIsNotRegisteredForFutureDate() {
        VehicleRepository vehicleRepository = new VehicleRepository();
        // Arrange
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate acquisitionDate = LocalDate.parse("01-01-2024", formatter);
        LocalDate lastMaintenanceDate = LocalDate.parse("01-04-2024", formatter);
        Vehicle vehicle = new Vehicle("Toyota", "Avensis", "Van", 1275, 1820, 30000,
                LocalDate.now(), acquisitionDate, 5000,
                "00-AA-00", lastMaintenanceDate);

        LocalDate futureDate = LocalDate.now().plusDays(1);
        boolean result = vehicleRepository.registerMaintenance(vehicle.getPlateNumber(), 35000, futureDate);

        // Assert
        assertFalse(result);
        assertNotEquals(futureDate, vehicle.getLastMaintenanceDate());
        assertNotEquals(35000, vehicle.getCurrentKm());
    }

    @Test
    void ensureMaintenanceIsNotRegisteredForInvalidPlateNumber() {
        VehicleRepository vehicleRepository = new VehicleRepository();
        // Arrange
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate acquisitionDate = LocalDate.parse("01-01-2024", formatter);
        LocalDate lastMaintenanceDate = LocalDate.parse("01-04-2024", formatter);
        Vehicle vehicle = new Vehicle("Toyota", "Avensis", "Van", 1275, 1820, 30000,
                LocalDate.now(), acquisitionDate, 5000,
                "00-AA-00", lastMaintenanceDate);

        // Act: Register maintenance with invalid plate number
        String invalidPlateNumber = "INVALID";
        boolean result = vehicleRepository.registerMaintenance(invalidPlateNumber, 35000, LocalDate.now());

        // Assert
        assertFalse(result);
        assertNotEquals(LocalDate.now(), vehicle.getLastMaintenanceDate());
        assertNotEquals(35000, vehicle.getCurrentKm());
    }
}