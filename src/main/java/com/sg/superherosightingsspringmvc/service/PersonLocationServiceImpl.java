package com.sg.superherosightingsspringmvc.service;

import com.sg.superherosightingsspringmvc.dao.PersonLocationDao;
import com.sg.superherosightingsspringmvc.model.PersonLocation;
import java.time.LocalDate;
import java.util.List;
import javax.inject.Inject;

public class PersonLocationServiceImpl implements PersonLocationService{
    
    PersonLocationDao personLocationDao;
    
    @Inject
    public PersonLocationServiceImpl(PersonLocationDao personLocationDao){
        this.personLocationDao = personLocationDao;
    }

    @Override
    public PersonLocation create(PersonLocation personLocation) {
        return personLocationDao.create(personLocation);
    }

    @Override
    public PersonLocation read(int personLocationId) {
        return personLocationDao.read(personLocationId);
    }

    @Override
    public void update(PersonLocation personLocation) {
        personLocationDao.update(personLocation);
    }

    @Override
    public void delete(PersonLocation personLocation) {
        personLocationDao.delete(personLocation);
    }

    @Override
    public List<PersonLocation> getAllPersonLocations() {
        return personLocationDao.getAllPersonLocations();
    }

    @Override
    public List<PersonLocation> getAllSightingsByDate(LocalDate dateSighted) {
        return personLocationDao.getAllSightingsByDate(dateSighted);
    }
    
    @Override
    public List<PersonLocation> getLastTenSightings() {
        return personLocationDao.getLastTenSightings();
    }

}
