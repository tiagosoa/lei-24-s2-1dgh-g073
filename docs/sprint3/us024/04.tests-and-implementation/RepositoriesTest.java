package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RepositoriesTest {

    @Test
    void testGetInstance() {
        Repositories instance = Repositories.getInstance();
        assertNotNull(instance);
    }

    @Test
    void testGetOrganizationRepository() {
        Repositories instance = Repositories.getInstance();
        assertNotNull(instance.getOrganizationRepository());
    }
    @Test
    void testGetAuthenticationRepository() {
        Repositories instance = Repositories.getInstance();
        assertNotNull(instance.getAuthenticationRepository());
    }

    @Test
    void testGetSkillRepository() {
        Repositories instance = Repositories.getInstance();
        assertNotNull(instance.getSkillRepository());
    }
    @Test
    void testGetJobRepository() {
        Repositories instance = Repositories.getInstance();
        assertNotNull(instance.getJobRepository());
    }
    @Test
    void testGetCollaboratorRepository() {
        Repositories instance = Repositories.getInstance();
        assertNotNull(instance.getCollaboratorRepository());
    }
    @Test
    void testGetVehicleRepository() {
        Repositories instance = Repositories.getInstance();
        assertNotNull(instance.getVehicleRepository());
    }
}