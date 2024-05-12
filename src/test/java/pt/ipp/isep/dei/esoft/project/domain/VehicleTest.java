package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.application.controller.RegisterMaintenanceController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class VehicleTest {

    @Test
    void ensureVehicleIsCreatedSuccessfully() {
        VFM vfm = new VFM("john.doe@this.company.com");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate date1 = LocalDate.parse("30-04-2024", formatter);
        LocalDate date2 = LocalDate.parse("29-04-2024", formatter);
        LocalDate date3 = LocalDate.parse("01-05-2024", formatter);
        Vehicle vehicle = new Vehicle("Toyota", "Avensis", "Van", 1275, 1820, 30000, date1, date2, 1, "00-AA-00", date3);
    }

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

    @Test
    void testEqualsSameObject() {
        VFM vfm = new VFM("john.doe@this.company.com");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate date1 = LocalDate.parse("30-04-2024", formatter);
        LocalDate date2 = LocalDate.parse("29-04-2024", formatter);
        LocalDate date3 = LocalDate.parse("01-05-2024", formatter);
        Vehicle vehicle = new Vehicle("Toyota", "Avensis", "Van", 1275, 1820, 30000, date1, date2, 1, "00-AA-00", date3);
        assertEquals(vehicle, vehicle);
    }

    @Test
    void testEqualsDifferentClass() {
        VFM vfm = new VFM("john.doe@this.company.com");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate date1 = LocalDate.parse("30-04-2024", formatter);
        LocalDate date2 = LocalDate.parse("29-04-2024", formatter);
        LocalDate date3 = LocalDate.parse("01-05-2024", formatter);
        Vehicle vehicle = new Vehicle("Toyota", "Avensis", "Van", 1275, 1820, 30000, date1, date2, 1, "00-AA-00", date3);
        assertNotEquals(vehicle, new Object());
    }

    @Test
    void testEqualsNull() {
        VFM vfm = new VFM("john.doe@this.company.com");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate date1 = LocalDate.parse("30-04-2024", formatter);
        LocalDate date2 = LocalDate.parse("29-04-2024", formatter);
        LocalDate date3 = LocalDate.parse("01-05-2024", formatter);
        Vehicle vehicle = new Vehicle("Toyota", "Avensis", "Van", 1275, 1820, 30000, date1, date2, 1, "00-AA-00", date3);
        assertNotEquals(vehicle, null);
    }

    @Test
    void testEqualsDifferentObjects() {
        VFM vfm = new VFM("john.doe@this.company.com");
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
        VFM vfm = new VFM("john.doe@this.company.com");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate date1 = LocalDate.parse("30-04-2024", formatter);
        LocalDate date2 = LocalDate.parse("29-04-2024", formatter);
        LocalDate date3 = LocalDate.parse("01-05-2024", formatter);
        Vehicle vehicle = new Vehicle("Toyota", "Avensis", "Van", 1275, 1820, 30000, date1, date2, 1, "00-AA-00", date3);
        assertEquals(vehicle.hashCode(), vehicle.hashCode());
    }

    @Test
    void testHashCodeDifferentObject() {
        VFM vfm = new VFM("john.doe@this.company.com");
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
        VFM vfm = new VFM("john.doe@this.company.com");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate date1 = LocalDate.parse("30-04-2024", formatter);
        LocalDate date2 = LocalDate.parse("29-04-2024", formatter);
        LocalDate date3 = LocalDate.parse("01-05-2024", formatter);
        Vehicle vehicle = new Vehicle("Toyota", "Avensis", "Van", 1275, 1820, 30000, date1, date2, 1, "00-AA-00", date3);
        Vehicle clone = vehicle.clone();
        assertEquals(vehicle, clone);
    }
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

    @Test
    void ensureMaintenanceIsNotRegisteredForFutureDate() {
        // Arrange
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate acquisitionDate = LocalDate.parse("01-01-2024", formatter);
        LocalDate lastMaintenanceDate = LocalDate.parse("01-04-2024", formatter);
        Vehicle vehicle = new Vehicle("Toyota", "Avensis", "Van", 1275, 1820, 30000,
                LocalDate.now(), acquisitionDate, 5000,
                "00-AA-00", lastMaintenanceDate);

        RegisterMaintenanceController controller = new RegisterMaintenanceController();
        LocalDate futureDate = LocalDate.now().plusDays(1);
        Optional<Boolean> result = controller.registerMaintenance(vehicle.getPlateNumber(), 35000, futureDate);

        // Assert
        assertFalse(result.isPresent());
        assertNotEquals(futureDate, vehicle.getLastMaintenanceDate());
        assertNotEquals(35000, vehicle.getCurrentKm());
    }

    @Test
    void ensureMaintenanceIsNotRegisteredForInvalidPlateNumber() {
        // Arrange
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate acquisitionDate = LocalDate.parse("01-01-2024", formatter);
        LocalDate lastMaintenanceDate = LocalDate.parse("01-04-2024", formatter);
        Vehicle vehicle = new Vehicle("Toyota", "Avensis", "Van", 1275, 1820, 30000,
                LocalDate.now(), acquisitionDate, 5000,
                "00-AA-00", lastMaintenanceDate);

        // Act: Register maintenance with invalid plate number
        RegisterMaintenanceController controller = new RegisterMaintenanceController();
        String invalidPlateNumber = "INVALID";
        Optional<Boolean> result = controller.registerMaintenance(invalidPlateNumber, 35000, LocalDate.now());

        // Assert
        assertFalse(result.isPresent());
        assertNotEquals(LocalDate.now(), vehicle.getLastMaintenanceDate());
        assertNotEquals(35000, vehicle.getCurrentKm());
    }
}