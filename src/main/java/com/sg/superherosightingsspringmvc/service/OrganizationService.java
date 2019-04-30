package com.sg.superherosightingsspringmvc.service;

import com.sg.superherosightingsspringmvc.model.Organization;
import com.sg.superherosightingsspringmvc.model.Person;
import java.util.List;

public interface OrganizationService {
    public Organization create(Organization organization);
    public Organization read(int organizationId);
    public void update(Organization organization);
    public void delete(Organization organization);
    public List<Organization> getAllOrganizations();
    public List<Organization> getOrganizationsByPerson(Person person);
}
