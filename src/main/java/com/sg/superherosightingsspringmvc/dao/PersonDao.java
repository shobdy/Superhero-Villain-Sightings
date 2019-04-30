package com.sg.superherosightingsspringmvc.dao;

import com.sg.superherosightingsspringmvc.model.Organization;
import com.sg.superherosightingsspringmvc.model.Person;
import java.util.List;
import com.sg.superherosightingsspringmvc.model.Location;

public interface PersonDao {
    public Person create(Person person);
    public Person read(int personId);
    public void update(Person person);
    public void delete(Person person);
    public List<Person> getAllPersons();
    public List<Person> getPersonsByLocation(Location location);
    public List<Person> getAllPersonsByOrganization(Organization organization);
}
