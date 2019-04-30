package com.sg.superherosightingsspringmvc.dao;

import com.sg.superherosightingsspringmvc.model.PersonLocation;
import java.time.LocalDate;
import java.util.List;

public interface PersonLocationDao {
    public PersonLocation create(PersonLocation personLocation);
    public PersonLocation read(int personLocationId);
    public void update(PersonLocation personLocation);
    public void delete(PersonLocation personLocation);
    public List<PersonLocation> getAllPersonLocations();
    public List<PersonLocation> getAllSightingsByDate(LocalDate dateSighted);
    public List<PersonLocation> getLastTenSightings();
}
