package com.sg.superherosightingsspringmvc.service;

import com.sg.superherosightingsspringmvc.dao.PersonOrganizationDao;
import com.sg.superherosightingsspringmvc.model.Person;
import com.sg.superherosightingsspringmvc.model.PersonOrganization;
import java.util.List;
import javax.inject.Inject;

public class PersonOrganizationServiceImpl implements PersonOrganizationService{
    
    PersonOrganizationDao personOrganizationDao;
    
    @Inject
    public PersonOrganizationServiceImpl(PersonOrganizationDao personOrganizationDao){
        this.personOrganizationDao = personOrganizationDao;
    }

    @Override
    public PersonOrganization create(PersonOrganization personOrganization) {
        return personOrganizationDao.create(personOrganization);
    }

    @Override
    public PersonOrganization read(int personOrganizationId) {
        return personOrganizationDao.read(personOrganizationId);
    }

    @Override
    public void update(PersonOrganization personOrganization) {
        personOrganizationDao.update(personOrganization);
    }

    @Override
    public void delete(PersonOrganization personOrganization) {
        personOrganizationDao.delete(personOrganization);
    }

    @Override
    public List<PersonOrganization> getAllPersonOrganizations() {
        return personOrganizationDao.getAllPersonOrganizations();
    }

    @Override
    public List<PersonOrganization> getAllPersonOrganizationsByPerson(Person person) {
        return personOrganizationDao.getAllPersonOrganizationsByPerson(person);
    }
}
