package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Organization;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrganizationRepository {

    private final List<Organization> organizations;

    public OrganizationRepository() {
        organizations = new ArrayList<>();
    }

    public Optional<Organization> getOrganizationByHRM(HRM hrm) {

        Optional<Organization> returnOrganization = Optional.empty();

        for (Organization organization : organizations) {
            if (organization.employs(hrm)) {
                returnOrganization = Optional.of(organization);
            }
        }

        return returnOrganization;
    }

    public Optional<Organization> getOrganizationByHRMEmail(String email) {

        Optional<Organization> returnOrganization = Optional.empty();

        for (Organization organization : organizations) {
            if (organization.anyHRMHasEmail(email)) {
                returnOrganization = Optional.of(organization);
            }
        }

        return returnOrganization;
    }

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

    private boolean validateOrganization(Organization organization) {
        boolean isValid = !organizations.contains(organization);

        return isValid;
    }
}