package com.sg.superherosightingsspringmvc.dao;

import com.sg.superherosightingsspringmvc.model.Person;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import com.sg.superherosightingsspringmvc.model.Location;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class LocationDaoJdbcTemplateImpl implements LocationDao {
    
    private JdbcTemplate jdbcTemplate;
    
    private static final String SQL_INSERT_LOCATION
            = "INSERT INTO Locations (Location_Name, Location_Description, Location_Address_ID, Location_Latitude, Location_Longitude) "
            + "VALUES (?, ?, ?, ?, ?)";
    
    private static final String SQL_SELECT_LOCATION
            = "SELECT * FROM Locations WHERE Location_ID = ? AND Location_Active = 1";
    
    private static final String SQL_UPDATE_LOCATION
            = "UPDATE Locations SET Location_Name = ?, Location_Description = ?, Location_Address_ID = ?, "
            + "Location_Latitude = ?, Location_Longitude = ? "
            + "WHERE Location_ID = ?";
    
    private static final String SQL_DELETE_LOCATION
            = "UPDATE Locations SET Location_Active = 0 where Location_ID = ?";
    
    private static final String SQL_SELECT_LOCATION_BY_PERSON
            = "SELECT l.Location_ID, l.Location_Name, l.Location_Description, l.Location_Address_ID, "
            + "l.Location_Latitude, l.Location_Longitude " 
            + "FROM Locations l "
            + "JOIN Person_Location pl ON l.Location_ID = pl.Location_ID "
            + "WHERE pl.Person_ID = ? AND Location_Active = 1";
    
    private static final String SQL_SELECT_ALL_LOCATIONS
            = "SELECT * from Locations WHERE Location_Active = 1";
    
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Location create(Location location) {
        jdbcTemplate.update(SQL_INSERT_LOCATION,
                location.getLocationName(),
                location.getLocationDescription(),
                location.getLocationAddressId(),
                location.getLocationLatitude(),
                location.getLocationLongitude());
        int locationId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
        location.setLocationId(locationId);
        return location;
    }

    @Override
    public Location read(int locationId) {
        try{
            return jdbcTemplate.queryForObject(SQL_SELECT_LOCATION, new LocationMapper(), locationId);
        } catch(EmptyResultDataAccessException ex){
            return null;
        }
    }

    @Override
    public void update(Location location) {
        jdbcTemplate.update(SQL_UPDATE_LOCATION, 
                location.getLocationName(),
                location.getLocationDescription(),
                location.getLocationAddressId(),
                location.getLocationLatitude(),
                location.getLocationLongitude(),
                location.getLocationId());
    }

    @Override
    public void delete(Location location) {
        jdbcTemplate.update(SQL_DELETE_LOCATION, location.getLocationId());
    }

    @Override
    public List<Location> getAllLocations() {
        return jdbcTemplate.query(SQL_SELECT_ALL_LOCATIONS, new LocationMapper());
    }

    @Override
    public List<Location> getLocationsByPerson(Person person) {
        return jdbcTemplate.query(SQL_SELECT_LOCATION_BY_PERSON, new LocationMapper(), person.getPersonId());
    }

    private static final class LocationMapper implements RowMapper<Location>{
        
        @Override
        public Location mapRow(ResultSet rs, int i) throws SQLException{
            Location lo = new Location();
            lo.setLocationName(rs.getString("Location_Name"));
            lo.setLocationDescription(rs.getString("Location_Description"));
            lo.setLocationAddressId(rs.getInt("Location_Address_ID"));
            lo.setLocationLatitude(rs.getString("Location_Latitude"));
            lo.setLocationLongitude(rs.getString("Location_Longitude"));
            lo.setLocationId(rs.getInt("Location_ID"));
            return lo;
        }
    }
}
