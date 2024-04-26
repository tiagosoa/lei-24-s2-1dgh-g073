package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.HRM;
import pt.ipp.isep.dei.esoft.project.domain.Organization;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class OrganizationRepositoryTest {

    @Test
    void testAddOrganization() {
        OrganizationRepository organizationRepository = new OrganizationRepository();
        Organization organization = new Organization("123456789");

        Optional<Organization> returnOrganization = organizationRepository.add(organization);

        assertEquals(organization, returnOrganization.get());
    }

    @Test
    void ensureGetOrganizationByHRMWorks() {
        OrganizationRepository organizationRepository = new OrganizationRepository();
        Organization organization = new Organization("123456789");
        HRM hrm = new HRM("john.doe@this.company.com");
        organization.addHRM(hrm);
        organizationRepository.add(organization);

        Optional<Organization> result = organizationRepository.getOrganizationByHRM(hrm);

        assertEquals(organization, result.get());
    }

    @Test
    void ensureGetOrganizationByEmployeeFails() {
        OrganizationRepository organizationRepository = new OrganizationRepository();
        Organization organization = new Organization("123456789");
        HRM hrm = new HRM("john.doe@this.company.com");
        organization.addHRM(hrm);
        organizationRepository.add(organization);

        HRM hrm2 = new HRM("jane.doe@this.company.com");
        Optional<Organization> result = organizationRepository.getOrganizationByHRM(hrm2);

        assertTrue(result.isEmpty());
    }

    @Test
    void ensureGetOrganizationByEmailWorks() {
        OrganizationRepository organizationRepository = new OrganizationRepository();
        Organization organization = new Organization("123456789");
        HRM hrm = new HRM("john.doe@this.company.com");
        organization.addHRM(hrm);
        organizationRepository.add(organization);

        Optional<Organization> result =
                organizationRepository.getOrganizationByHRMEmail("john.doe@this.company.com");

        assertEquals(organization, result.get());
    }

    @Test
    void ensureAddOrganizationWorks() {
        OrganizationRepository organizationRepository = new OrganizationRepository();
        Organization organization = new Organization("123456789");
        HRM hrm = new HRM("john.doe@this.company.com");
        organization.addHRM(hrm);

        organizationRepository.add(organization);

        Optional<Organization> returnOrganization =
                organizationRepository.getOrganizationByHRMEmail("john.doe@this" + ".company.com");

        //Assert
        //Make sure both represents the same object
        assertEquals(organization, returnOrganization.get());
        //Make sure it is a clone (different memory addresses)
        assertNotSame(organization, returnOrganization.get());
    }

    @Test
    void ensureAddOrganizationDuplicateFails() {
        OrganizationRepository organizationRepository = new OrganizationRepository();
        Organization organization = new Organization("123456789");
        HRM hrm = new HRM("john.doe@this.company.com");
        organization.addHRM(hrm);
        organizationRepository.add(organization);

        Optional<Organization> result = organizationRepository.add(organization);

        assertTrue(result.isEmpty());
    }
}