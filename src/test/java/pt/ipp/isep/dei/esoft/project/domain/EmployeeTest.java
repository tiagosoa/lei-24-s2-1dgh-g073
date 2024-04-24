package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {

    @Test
    void ensureTwoEmployeesWithSameEmailEquals() {
        HRM employee1 = new HRM("john.doe@this.company.com");
        HRM employee2 = new HRM("john.doe@this.company.com");
        assertEquals(employee1, employee2);
    }

    @Test
    void ensureEmployeeWithDifferentEmailNotEquals() {
        HRM employee1 = new HRM("john.doe@this.company.com");
        HRM employee2 = new HRM("jane.doe@this.company.com");
        assertNotEquals(employee1, employee2);
    }

    @Test
    void ensureEmployeeDoesNotEqualNull() {
        HRM employee1 = new HRM("john.doe@this.company.com");
        assertNotEquals(employee1, null);
    }

    @Test
    void ensureEmployeeDoesNotEqualOtherObject() {
        HRM employee1 = new HRM("john.doe@this.company.com");
        assertNotEquals(employee1, new Object());
    }

    @Test
    void ensureTheSameObjectIsEqual() {
        HRM employee1 = new HRM("john.doe@this.company.com");
        assertEquals(employee1, employee1);
    }

    @Test
    void ensureHashCodeIsEqualForEqualObjects() {
        String email = "john.doe@this.company.com";
        HRM employee1 = new HRM(email);
        HRM employee2 = new HRM(email);
        assertEquals(employee1.hashCode(), employee2.hashCode());
    }

    @Test
    void ensureHashCodeIsNotEqualForDifferentObjects() {

        HRM employee1 = new HRM("john.doe@this.company.com");
        HRM employee2 = new HRM("jane.doe@this.company.com");
        assertNotEquals(employee1.hashCode(), employee2.hashCode());
    }

    @Test
    void ensureHasEmailWorksForTheSameEmail() {
        String email = "john.doe@this.compay.org";
        HRM employee = new HRM(email);
        assertTrue(employee.hasEmail(email));

    }

    @Test
    void ensureHasEmailFailsForDifferentEmails() {
        String email = "john.doe@this.company.com";
        HRM employee = new HRM(email);
        assertFalse(employee.hasEmail("jane.doe@this.company.com"));

    }

    @Test
    void ensureCloneWorks() {
        String email = "john.doe@this.company.com";
        HRM employee = new HRM(email);
        HRM clone = employee.clone();
        assertEquals(employee, clone);
    }
}