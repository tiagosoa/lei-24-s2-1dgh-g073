package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.GSM;
import pt.ipp.isep.dei.esoft.project.domain.HRM;
import pt.ipp.isep.dei.esoft.project.domain.Organization;
import pt.ipp.isep.dei.esoft.project.domain.VFM;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class OrganizationRepositoryTest {

    private OrganizationRepository organizationRepository;
    private Organization organization;
    private HRM hrm;
    private VFM vfm;
    private GSM gsm;

    @BeforeEach
    void setUp() {
        organizationRepository = new OrganizationRepository();
        organization = new Organization("MusgoSublime");
        hrm = new HRM("hrm@gmail.pt");
        vfm = new VFM("vfm@gmail.pt");
        gsm = new GSM("gsm@gmail.pt");

        organization.addHRM(hrm);
        organization.addVFM(vfm);
        organization.addGSM(gsm);
        organizationRepository.add(organization);
    }

    @Test
    void testAddOrganization() {
        OrganizationRepository organizationRepository = new OrganizationRepository();
        Organization organization = new Organization("SusgoMublime");

        Optional<Organization> returnOrganization = organizationRepository.add(organization);

        assertEquals(organization, returnOrganization.get());
    }

    @Test
    void ensureGetOrganizationByHRMWorks() {
        Optional<Organization> result = organizationRepository.getOrganizationByHRM(hrm);

        assertEquals(organization, result.get());
    }

    @Test
    void ensureGetOrganizationByHRMFails() {
        HRM hrm2 = new HRM("hrm2@gmail.pt");
        Optional<Organization> result = organizationRepository.getOrganizationByHRM(hrm2);

        assertTrue(result.isEmpty());
    }

    @Test
    void ensureGetOrganizationByHRMEmailWorks() {
        Optional<Organization> result =
                organizationRepository.getOrganizationByHRMEmail("hrm@gmail.pt");

        assertEquals(organization, result.get());
    }

    @Test
    void ensureGetOrganizationByHRMEmailFails() {
        Optional<Organization> result =
                organizationRepository.getOrganizationByHRMEmail("");

        assertTrue(result.isEmpty());
    }

    @Test
    void ensureGetOrganizationByVFMWorks() {
        Optional<Organization> result = organizationRepository.getOrganizationByVFM(vfm);

        assertEquals(organization, result.get());
    }

    @Test
    void ensureGetOrganizationByVFMEmailWorks() {
        Optional<Organization> result =
                organizationRepository.getOrganizationByVFMEmail("vfm@gmail.pt");

        assertEquals(organization, result.get());
    }

    @Test
    void ensureAddOrganizationByHRMWorks() {
        OrganizationRepository organizationRepository = new OrganizationRepository();
        Organization organization = new Organization("MusgoSublime");
        HRM hrm = new HRM("hrm@gmail.pt");
        organization.addHRM(hrm);

        organizationRepository.add(organization);

        Optional<Organization> returnOrganization =
                organizationRepository.getOrganizationByHRMEmail("hrm@gmail.pt");

        assertEquals(organization, returnOrganization.get());

        assertNotSame(organization, returnOrganization.get());
    }

    @Test
    void ensureAddOrganizationByVFMWorks() {
        OrganizationRepository organizationRepository = new OrganizationRepository();
        Organization organization = new Organization("MusgoSublime");
        VFM vfm = new VFM("hrm@gmail.pt");
        organization.addVFM(vfm);

        organizationRepository.add(organization);

        Optional<Organization> returnOrganization =
                organizationRepository.getOrganizationByHRMEmail("hrm@gmail.pt");

        assertEquals(organization, returnOrganization.get());
        assertNotSame(organization, returnOrganization.get());
    }

    @Test
    void ensureAddOrganizationDuplicateByHRMFails() {
        OrganizationRepository organizationRepository = new OrganizationRepository();
        Organization organization = new Organization("MusgoSublime");
        HRM hrm = new HRM("hrm@gmail.pt");
        organization.addHRM(hrm);
        organizationRepository.add(organization);

        Optional<Organization> result = organizationRepository.add(organization);

        assertTrue(result.isEmpty());
    }

    @Test
    void ensureAddOrganizationDuplicateByVFMFails() {
        OrganizationRepository organizationRepository = new OrganizationRepository();
        Organization organization = new Organization("MusgoSublime");
        VFM vfm = new VFM("hrm@gmail.pt");
        organization.addVFM(vfm);
        organizationRepository.add(organization);

        Optional<Organization> result = organizationRepository.add(organization);

        assertTrue(result.isEmpty());
    }

    @Test
    void ensureGetOrganizationByGSMWorks() {
        Optional<Organization> result = organizationRepository.getOrganizationByGSM(gsm);

        assertEquals(organization, result.get());
    }

    @Test
    void ensureGetOrganizationByGSMFails() {
        GSM gsm2 = new GSM("gsm2@gmail.pt");
        Optional<Organization> result = organizationRepository.getOrganizationByGSM(gsm2);

        assertTrue(result.isEmpty());
    }

    @Test
    void ensureValidateOrganizationWorks() {
        Organization organization2 = new Organization("SusgoMublime");
        assertTrue(organizationRepository.add(organization2).isPresent());

        Organization duplicateOrganization = new Organization("MusgoSublime");
        assertTrue(organizationRepository.add(duplicateOrganization).isEmpty());
    }
}
