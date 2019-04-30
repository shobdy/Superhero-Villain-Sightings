package com.sg.superherosightingsspringmvc.service;

import com.sg.superherosightingsspringmvc.dao.PersonDao;
import com.sg.superherosightingsspringmvc.model.Location;
import com.sg.superherosightingsspringmvc.model.Organization;
import com.sg.superherosightingsspringmvc.model.Person;
import java.util.List;
import javax.inject.Inject;

public class PersonServiceImpl implements PersonService{
    
    PersonDao personDao;
    
    @Inject
    public PersonServiceImpl(PersonDao personDao){
        this.personDao = personDao;
    }

    @Override
    public Person create(Person person) {
        return personDao.create(person);
    }

    @Override
    public Person read(int personId) {
        return personDao.read(personId);
    }

    @Override
    public void update(Person person) {
        personDao.update(person);
    }

    @Override
    public void delete(Person person) {
        personDao.delete(person);
    }

    @Override
    public List<Person> getAllPersons() {
        return personDao.getAllPersons();
    }

    @Override
    public List<Person> getPersonsByLocation(Location location) {
        return personDao.getPersonsByLocation(location);
    }

    @Override
    public List<Person> getAllPersonsByOrganization(Organization organization) {
        return personDao.getAllPersonsByOrganization(organization);
    }

}
