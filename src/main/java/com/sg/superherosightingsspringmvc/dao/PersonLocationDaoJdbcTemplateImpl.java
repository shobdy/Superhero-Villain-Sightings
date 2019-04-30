package com.sg.superherosightingsspringmvc.dao;

import com.sg.superherosightingsspringmvc.model.Location;
import com.sg.superherosightingsspringmvc.model.Person;
import com.sg.superherosightingsspringmvc.model.PersonLocation;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class PersonLocationDaoJdbcTemplateImpl implements PersonLocationDao {
    
    private JdbcTemplate jdbcTemplate;
    
    private static final String SQL_INSERT_PERSON_LOCATION
            = "INSERT INTO Person_Location (Person_ID, Location_ID, Date_Sighted) "
            + "VALUES (?, ?, ?)";
    
    private static final String SQL_SELECT_PERSON_LOCATION
            = "SELECT p.Person_Type, p.Person_Name, p.Person_Description, p.Person_Power, "
            + "l.Location_Name, l.Location_Description, l.Location_Address_ID, l.Location_Latitude, l.Location_Longitude, "
            + "pl.Person_Location_ID, pl.Date_Sighted, p.Person_ID, pl.Person_ID, l.Location_ID, pl.Location_ID "
            + "FROM Person_Location pl "
            + "JOIN Person p ON p.Person_ID = pl.Person_ID "
            + "JOIN Locations l ON l.Location_ID = pl.Location_ID "
            + "WHERE Person_Location_ID = ? ";
    
    private static final String SQL_UPDATE_PERSON_LOCATION
            = "UPDATE Person_Location SET Person_ID = ?, Location_ID = ?, Date_Sighted = ? WHERE Person_Location_ID = ?";
    
    private static final String SQL_DELETE_PERSON_LOCATION
            = "DELETE FROM Person_Location WHERE Person_Location_ID = ?";
    
    private static final String SQL_SELECT_ALL_PERSON_LOCATIONS
            = "SELECT p.Person_Type, p.Person_Name, p.Person_Description, p.Person_Power, "
            + "l.Location_Name, l.Location_Description, l.Location_Address_ID, l.Location_Latitude, l.Location_Longitude, "
            + "pl.Person_Location_ID, pl.Date_Sighted, pl.Person_ID, p.Person_ID, l.Location_ID, pl.Location_ID "
            + "FROM Person_Location pl "
            + "JOIN Person p ON p.Person_ID = pl.Person_ID "
            + "JOIN Locations l ON l.Location_ID = pl.Location_ID "
            + "ORDER BY pl.Date_Sighted DESC";
    
    private static final String SQL_SELECT_SIGHTINGS_BY_DATE
            = "SELECT p.Person_Type, p.Person_Name, p.Person_Description, p.Person_Power, "
            + "l.Location_Name, l.Location_Description, l.Location_Address_ID, l.Location_Latitude, l.Location_Longitude, "
            + "pl.Person_Location_ID, pl.Date_Sighted, p.Person_ID, pl.Person_ID, l.Location_ID, pl.Location_ID "
            + "FROM Person_Location pl "
            + "JOIN Person p ON p.Person_ID = pl.Person_ID "
            + "JOIN Locations l ON l.Location_ID = pl.Location_ID "
            + "WHERE Date_Sighted = ?";
    
    private static final String SQL_SELECT_LAST_TEN_SIGHTINGS
            = "SELECT p.Person_Type, p.Person_Name, p.Person_Description, p.Person_Power, "
            + "l.Location_Name, l.Location_Description, l.Location_Address_ID, l.Location_Latitude, l.Location_Longitude, "
            + "pl.Person_Location_ID, pl.Date_Sighted, p.Person_ID, pl.Person_ID, l.Location_ID, pl.Location_ID "
            + "FROM Person_Location pl "
            + "JOIN Person p ON p.Person_ID = pl.Person_ID "
            + "JOIN Locations l ON l.Location_ID = pl.Location_ID "
            + "ORDER BY Date_Sighted DESC LIMIT 0, 10";
    
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public PersonLocation create(PersonLocation personLocation) {
        jdbcTemplate.update(SQL_INSERT_PERSON_LOCATION,
                personLocation.getPerson().getPersonId(),
                personLocation.getLocation().getLocationId(),
                java.sql.Date.valueOf(personLocation.getDateSighted()));
        int personLocationId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
        personLocation.setPersonLocationId(personLocationId);
        return personLocation;
    }

    @Override
    public PersonLocation read(int personLocationId) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_PERSON_LOCATION, new PersonLocationMapper(), personLocationId);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public void update(PersonLocation personLocation) {
        jdbcTemplate.update(SQL_UPDATE_PERSON_LOCATION,
                personLocation.getPerson().getPersonId(),
                personLocation.getLocation().getLocationId(),
                java.sql.Date.valueOf(personLocation.getDateSighted()),
                personLocation.getPersonLocationId());
    }

    @Override
    public void delete(PersonLocation personLocation) {
        jdbcTemplate.update(SQL_DELETE_PERSON_LOCATION, personLocation.getPersonLocationId());
    }

    @Override
    public List<PersonLocation> getAllPersonLocations() {
        return jdbcTemplate.query(SQL_SELECT_ALL_PERSON_LOCATIONS, new PersonLocationMapper());
    }

    @Override
    public List<PersonLocation> getAllSightingsByDate(LocalDate dateSighted) {
        return jdbcTemplate.query(SQL_SELECT_SIGHTINGS_BY_DATE, new PersonLocationMapper(), java.sql.Date.valueOf(dateSighted));
    }
    
    @Override
    public List<PersonLocation> getLastTenSightings() {
        return jdbcTemplate.query(SQL_SELECT_LAST_TEN_SIGHTINGS, new PersonLocationMapper());
    }
    
    private static final class PersonLocationMapper implements RowMapper<PersonLocation> {
        
        @Override
        public PersonLocation mapRow(ResultSet rs, int i) throws SQLException{
            
            PersonLocation pl = new PersonLocation();
            
            Person person = new Person();
            int personId = rs.getInt("Person_ID");
            String personType = rs.getString("Person_Type");
            String personName = rs.getString("Person_Name");
            String personDescription = rs.getString("Person_Description");
            String personPower = rs.getString("Person_Power");
            person.setPersonId(personId);
            person.setPersonType(personType);
            person.setPersonName(personName);
            person.setPersonDescription(personDescription);
            person.setPersonPower(personPower);
            pl.setPerson(person);
            
            int locationId = rs.getInt("Location_ID");
            String locationName = rs.getString("Location_Name");
            String locationDescription = rs.getString("Location_Description");
            int locationAddressId = rs.getInt("Location_Address_ID");
            String locationLatitude = rs.getString("Location_Latitude");
            String locationLongitude = rs.getString("Location_Longitude");
            Location location = new Location();
            location.setLocationId(locationId);
            location.setLocationName(locationName);
            location.setLocationDescription(locationDescription);
            location.setLocationAddressId(locationAddressId);
            location.setLocationLatitude(locationLatitude);
            location.setLocationLongitude(locationLongitude);
            pl.setLocation(location);
            
            pl.setDateSighted(rs.getDate("Date_Sighted").toLocalDate());
            pl.setPersonLocationId(rs.getInt("Person_Location_ID"));
            return pl;
        }
    }
}
