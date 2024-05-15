package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.GSM;
import pt.ipp.isep.dei.esoft.project.domain.HRM;
import pt.ipp.isep.dei.esoft.project.domain.Organization;
import pt.ipp.isep.dei.esoft.project.domain.VFM;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Repository class for managing organizations.
 */
public class OrganizationRepository {

    private final List<Organization> organizations;

    /**
     * Constructor for OrganizationRepository.
     */
    public OrganizationRepository() {
        organizations = new ArrayList<>();
    }

    /**
     * Retrieves an organization by its associated HRM.
     *
     * @param hrm The HRM associated with the organization.
     * @return An optional containing the organization if found, or an empty optional otherwise.
     */
    public Optional<Organization> getOrganizationByHRM(HRM hrm) {

        Optional<Organization> returnOrganization = Optional.empty();

        for (Organization organization : organizations) {
            if (organization.employs(hrm)) {
                returnOrganization = Optional.of(organization);
            }
        }

        return returnOrganization;
    }

    /**
     * Retrieves an organization by its associated VFM.
     *
     * @param vfm The VFM associated with the organization.
     * @return An optional containing the organization if found, or an empty optional otherwise.
     */
    public Optional<Organization> getOrganizationByVFM(VFM vfm) {

        Optional<Organization> returnOrganization = Optional.empty();

        for (Organization organization : organizations) {
            if (organization.employs(vfm)) {
                returnOrganization = Optional.of(organization);
            }
        }

        return returnOrganization;
    }

    /**
     * Retrieves an organization by its associated GSM.
     *
     * @param gsm The GSM associated with the organization.
     * @return An optional containing the organization if found, or an empty optional otherwise.
     */
    public Optional<Organization> getOrganizationByGSM(GSM gsm) {

        Optional<Organization> returnOrganization = Optional.empty();

        for (Organization organization : organizations) {
            if (organization.employs(gsm)) {
                returnOrganization = Optional.of(organization);
            }
        }

        return returnOrganization;
    }

    /**
     * Retrieves an organization by the email of one of its HRMs.
     *
     * @param email The email of the HRM associated with the organization.
     * @return An optional containing the organization if found, or an empty optional otherwise.
     */
    public Optional<Organization> getOrganizationByHRMEmail(String email) {

        Optional<Organization> returnOrganization = Optional.empty();

        for (Organization organization : organizations) {
            if (organization.anyHRMHasEmail(email)) {
                returnOrganization = Optional.of(organization);
            }
        }

        return returnOrganization;
    }

    /**
     * Retrieves an organization by the email of one of its GSMs.
     *
     * @param email The email of the GSM associated with the organization.
     * @return An optional containing the organization if found, or an empty optional otherwise.
     */
    public Optional<Organization> getOrganizationByGSMEmail(String email) {

        Optional<Organization> returnOrganization = Optional.empty();

        for (Organization organization : organizations) {
            if (organization.anyGSMHasEmail(email)) {
                returnOrganization = Optional.of(organization);
            }
        }

        return returnOrganization;
    }

    /**
     * Adds a new organization to the repository.
     *
     * @param organization The organization to be added.
     * @return An optional containing the newly added organization if the operation is successful, or an empty optional otherwise.
     */
    public Optional<Organization> add(Organization organization) {

        Optional<Organization> newOrganization = Optional.empty();
        boolean operationSuccess = false;

        if (validateOrganization(organization)) {
            newOrganization = Optional.of(organization.clone());
            operationSuccess = organizations.add(newOrganization.get());
        }

        if (!operationSuccess) {
            newOrganization = Optional.empty();
        }

        return newOrganization;
    }

    /**
     * Validates an organization before adding it to the repository.
     *
     * @param organization The organization to be validated.
     * @return True if the organization is valid and can be added to the repository, false otherwise.
     */
    private boolean validateOrganization(Organization organization) {
        boolean isValid = !organizations.contains(organization);

        return isValid;
    }
}