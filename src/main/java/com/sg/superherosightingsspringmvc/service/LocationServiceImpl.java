package com.sg.superherosightingsspringmvc.service;

import com.sg.superherosightingsspringmvc.dao.LocationDao;
import com.sg.superherosightingsspringmvc.model.Location;
import com.sg.superherosightingsspringmvc.model.Person;
import java.util.List;
import javax.inject.Inject;

public class LocationServiceImpl implements LocationService{
    
    LocationDao locationDao;
    
    @Inject
    public LocationServiceImpl(LocationDao locationDao){
        this.locationDao = locationDao;
    }

    @Override
    public Location create(Location location) {
        return locationDao.create(location);
    }

    @Override
    public Location read(int locationId) {
        return locationDao.read(locationId);
    }

    @Override
    public void update(Location location) {
        locationDao.update(location);
    }

    @Override
    public void delete(Location location) {
        locationDao.delete(location);
    }

    @Override
    public List<Location> getAllLocations() {
        return locationDao.getAllLocations();
    }

    @Override
    public List<Location> getLocationsByPerson(Person person) {
        return locationDao.getLocationsByPerson(person);
    }

}
