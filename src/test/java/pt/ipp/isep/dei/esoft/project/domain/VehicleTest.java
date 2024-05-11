package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
}