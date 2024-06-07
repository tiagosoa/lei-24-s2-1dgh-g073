package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RepositoriesTest {

    @Test
    void testGetInstance() {
        Repositories instance = Repositories.getInstance();
        assertNotNull(instance);
        assertSame(instance, Repositories.getInstance(), "getInstance should return the same instance");
    }

    @Test
    void testGetOrganizationRepository() {
        Repositories instance = Repositories.getInstance();
        assertNotNull(instance.getOrganizationRepository());
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
    void testGetTeamRepository() {
        Repositories instance = Repositories.getInstance();
        assertNotNull(instance.getTeamRepository());
    }

    @Test
    void testGetAuthenticationRepository() {
        Repositories instance = Repositories.getInstance();
        assertNotNull(instance.getAuthenticationRepository());
    }

    @Test
    void testGetVehicleRepository() {
        Repositories instance = Repositories.getInstance();
        assertNotNull(instance.getVehicleRepository());
    }

    @Test
    void testGetGreenSpaceRepository() {
        Repositories instance = Repositories.getInstance();
        assertNotNull(instance.getGreenSpaceRepository());
    }

    @Test
    void testGetToDoListRepository() {
        Repositories instance = Repositories.getInstance();
        assertNotNull(instance.getToDoListRepository());
    }

    @Test
    void testGetAgendaRepository() {
        Repositories instance = Repositories.getInstance();
        assertNotNull(instance.getAgendaRepository());
    }

    @Test
    void testGetTaskRepository() {
        Repositories instance = Repositories.getInstance();
        assertNotNull(instance.getTaskRepository());
    }
}
