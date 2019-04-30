package com.sg.superherosightingsspringmvc.service;

import com.sg.superherosightingsspringmvc.model.Location;
import com.sg.superherosightingsspringmvc.model.Person;
import java.util.List;

public interface LocationService {
    public Location create(Location location);
    public Location read(int locationId);
    public void update(Location location);
    public void delete(Location location);
    public List<Location> getAllLocations();
    public List<Location> getLocationsByPerson(Person person);
}
