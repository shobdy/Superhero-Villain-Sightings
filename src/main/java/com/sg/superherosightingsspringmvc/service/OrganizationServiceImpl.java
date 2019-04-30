package com.sg.superherosightingsspringmvc.service;

import com.sg.superherosightingsspringmvc.dao.OrganizationDao;
import com.sg.superherosightingsspringmvc.model.Organization;
import com.sg.superherosightingsspringmvc.model.Person;
import java.util.List;
import javax.inject.Inject;

public class OrganizationServiceImpl implements OrganizationService{
    
    OrganizationDao organizationDao;
    
    @Inject
    public OrganizationServiceImpl(OrganizationDao organizationDao){
        this.organizationDao = organizationDao;
    }

    @Override
    public Organization create(Organization organization) {
        return organizationDao.create(organization);
    }

    @Override
    public Organization read(int organizationId) {
        return organizationDao.read(organizationId);
    }

    @Override
    public void update(Organization organization) {
        organizationDao.update(organization);
    }

    @Override
    public void delete(Organization organization) {
        organizationDao.delete(organization);
    }

    @Override
    public List<Organization> getAllOrganizations() {
        return organizationDao.getAllOrganizations();
    }

    @Override
    public List<Organization> getOrganizationsByPerson(Person person) {
        return organizationDao.getOrganizationsByPerson(person);
    }

}
