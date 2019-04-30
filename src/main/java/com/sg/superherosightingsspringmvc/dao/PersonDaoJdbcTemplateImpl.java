package com.sg.superherosightingsspringmvc.dao;

import com.sg.superherosightingsspringmvc.model.Organization;
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

public class PersonDaoJdbcTemplateImpl implements PersonDao {
    
    private JdbcTemplate jdbcTemplate;
    
    private static final String SQL_INSERT_PERSON
            = "INSERT INTO Person (Person_Type, Person_Name, Person_Description, Person_Power) "
            + "VALUES (?, ?, ?, ?)";
    
    private static final String SQL_SELECT_PERSON
            = "SELECT * FROM Person WHERE Person_ID = ? AND Person_Active = 1";
    
    private static final String SQL_UPDATE_PERSON
            = "UPDATE Person SET Person_Type = ?, Person_Name = ?, Person_Description = ?, Person_Power = ? "
            + "WHERE Person_ID = ?";
    
    private static final String SQL_DELETE_PERSON
            = "UPDATE Person SET Person_Active = 0 WHERE Person_ID = ?";
    
    private static final String SQL_SELECT_ALL_PERSONS
            = "SELECT * FROM Person WHERE Person_Active = 1";
    
    private static final String SQL_SELECT_PERSON_BY_LOCATION
            = "SELECT p.Person_ID, p.Person_Type, p.Person_Name, p.Person_Description, p.Person_Power, p.Person_Active, pl.Person_ID "
            + "FROM Person p "
            + "JOIN Person_Location pl ON p.Person_ID = pl.Person_ID "
            + "WHERE pl.Location_ID = ? AND p.Person_Active = 1";
    
    private static final String SQL_SELECT_PERSON_BY_ORGANIZATION  
            = "SELECT p.Person_ID, p.Person_Type, p.Person_Name, p.Person_Description, p.Person_Power, p.Person_ID, p.Person_Active, po.Person_ID "
            + "FROM Person p "
            + "JOIN Person_Organization po ON p.Person_ID = po.Person_ID "
            + "WHERE po.Organization_ID = ? AND p.Person_Active = 1";
    
    
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Person create(Person person) {
        jdbcTemplate.update(SQL_INSERT_PERSON,
                person.getPersonType(),
                person.getPersonName(),
                person.getPersonDescription(),
                person.getPersonPower());
        int personId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
        person.setPersonId(personId);
        return person;
    }

    @Override
    public Person read(int personId) {
        try{
            return jdbcTemplate.queryForObject(SQL_SELECT_PERSON, new PersonMapper(), personId);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public void update(Person person) {
        jdbcTemplate.update(SQL_UPDATE_PERSON, 
                person.getPersonType(),
                person.getPersonName(),
                person.getPersonDescription(),
                person.getPersonPower(),
                person.getPersonId());
    }

    @Override
    public void delete(Person person) {
        // Change personActive to false takes them out of the Person list without having to delete the bridge table data.
        person.setPersonActive(false);
        jdbcTemplate.update(SQL_DELETE_PERSON, person.getPersonId());
    }

    @Override
    public List<Person> getAllPersons() {
        List<Person> test = jdbcTemplate.query(SQL_SELECT_ALL_PERSONS, new PersonMapper());
        return test;
    }

    @Override
    public List<Person> getPersonsByLocation(Location location) {
        return jdbcTemplate.query(SQL_SELECT_PERSON_BY_LOCATION, new PersonMapper(), location.getLocationId());
    }

    @Override
    public List<Person> getAllPersonsByOrganization(Organization organization) {
        return jdbcTemplate.query(SQL_SELECT_PERSON_BY_ORGANIZATION, new PersonMapper(), organization.getOrganizationId());
    }
    
    private static final class PersonMapper implements RowMapper<Person> {
        @Override
        public Person mapRow(ResultSet rs, int i) throws SQLException {
            Person pe = new Person();
            pe.setPersonType(rs.getString("Person_Type"));
            pe.setPersonName(rs.getString("Person_Name"));
            pe.setPersonDescription(rs.getString("Person_Description"));
            pe.setPersonPower(rs.getString("Person_Power"));
            pe.setPersonId(rs.getInt("Person_ID"));
            return pe;
        }
    }
}
