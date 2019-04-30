package com.sg.superherosightingsspringmvc.service;

import com.sg.superherosightingsspringmvc.model.Person;
import com.sg.superherosightingsspringmvc.model.PersonOrganization;
import java.util.List;

public interface PersonOrganizationService {
    public PersonOrganization create(PersonOrganization personOrganization);
    public PersonOrganization read(int personOrganizationId);
    public void update(PersonOrganization personOrganization);
    public void delete(PersonOrganization personOrganization);
    public List<PersonOrganization> getAllPersonOrganizations();
    public List<PersonOrganization> getAllPersonOrganizationsByPerson(Person person);
}
