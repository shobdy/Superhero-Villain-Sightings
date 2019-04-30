package com.sg.superherosightingsspringmvc.dao;

import com.sg.superherosightingsspringmvc.model.Person;
import java.util.List;
import com.sg.superherosightingsspringmvc.model.Location;

public interface LocationDao {
    public Location create(Location location);
    public Location read(int locationId);
    public void update(Location location);
    public void delete(Location location);
    public List<Location> getAllLocations();
    public List<Location> getLocationsByPerson(Person person);
}
