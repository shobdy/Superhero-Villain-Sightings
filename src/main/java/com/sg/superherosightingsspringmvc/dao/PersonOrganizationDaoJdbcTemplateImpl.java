package com.sg.superherosightingsspringmvc.dao;

import com.sg.superherosightingsspringmvc.model.Organization;
import com.sg.superherosightingsspringmvc.model.Person;
import com.sg.superherosightingsspringmvc.model.PersonOrganization;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class PersonOrganizationDaoJdbcTemplateImpl implements PersonOrganizationDao {

    private JdbcTemplate jdbcTemplate;
    
    private static final String SQL_INSERT_PERSON_ORGANIZATION
            = "INSERT INTO Person_Organization (Person_ID, Organization_ID) "
            + "VALUES (?, ?)";
    
    private static final String SQL_SELECT_PERSON_ORGANIZATION
            ="SELECT p.Person_ID, p.Person_Type, p.Person_Name, p.Person_Description, p.Person_Power, "
            + "o.Organization_ID, o.Organization_Name, o.Organization_Description, o.Organization_Address_ID, "
            + "o.Organization_Contact_Name, o.Organization_Contact_Phone, o.Organization_Contact_Email, "
            + "po.Person_Organization_ID, po.Person_ID, po.Organization_ID "
            + "FROM Person_Organization po "
            + "JOIN Person p ON p.Person_ID = po.Person_ID "
            + "JOIN Organizations o ON o.Organization_ID = po.Organization_ID "
            + "WHERE Person_Organization_ID = ?";
    
    private static final String SQL_UPDATE_PERSON_ORGANIZATION
            = "UPDATE Person_Organization SET Person_ID = ?, Organization_ID = ? WHERE Person_Organization_ID = ?";
    
    private static final String SQL_DELETE_PERSON_ORGANIZATION
            = "DELETE FROM Person_Organization WHERE Person_Organization_ID = ?";
    
    private static final String SQL_SELECT_ALL_PERSON_ORGANIZATIONS
            ="SELECT p.Person_ID, p.Person_Type, p.Person_Name, p.Person_Description, p.Person_Power, "
            + "o.Organization_ID, o.Organization_Name, o.Organization_Description, o.Organization_Address_ID, "
            + "o.Organization_Contact_Name, o.Organization_Contact_Phone, o.Organization_Contact_Email, "
            + "po.Person_Organization_ID, po.Person_ID, po.Organization_ID "
            + "FROM Person_Organization po "
            + "JOIN Person p ON p.Person_ID = po.Person_ID "
            + "JOIN Organizations o ON o.Organization_ID = po.Organization_ID ";
    
    private static final String SQL_SELECT_ALL_PERSON_ORGANIZATIONS_BY_PERSON
            ="SELECT p.Person_ID, p.Person_Type, p.Person_Name, p.Person_Description, p.Person_Power, "
            + "o.Organization_ID, o.Organization_Name, o.Organization_Description, o.Organization_Address_ID, "
            + "o.Organization_Contact_Name, o.Organization_Contact_Phone, o.Organization_Contact_Email, "
            + "po.Person_Organization_ID, po.Person_ID, po.Organization_ID "
            + "FROM Person_Organization po "
            + "JOIN Person p ON p.Person_ID = po.Person_ID "
            + "JOIN Organizations o ON o.Organization_ID = po.Organization_ID "
            + "WHERE po.Person_ID = ?";
    
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public PersonOrganization create(PersonOrganization personOrganization) {
        jdbcTemplate.update(SQL_INSERT_PERSON_ORGANIZATION,
            personOrganization.getPerson().getPersonId(),
            personOrganization.getOrganization().getOrganizationId());
        int personOrganizationId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
        personOrganization.setPersonOrganizationId(personOrganizationId);
        return personOrganization;
        
    }

    @Override
    public PersonOrganization read(int personOrganizationId) {
        try{
            return jdbcTemplate.queryForObject(SQL_SELECT_PERSON_ORGANIZATION, new PersonOrganizationMapper(), personOrganizationId);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public void update(PersonOrganization personOrganization) {
        jdbcTemplate.update(SQL_UPDATE_PERSON_ORGANIZATION,
                personOrganization.getPerson().getPersonId(),
                personOrganization.getOrganization().getOrganizationId(),
                personOrganization.getPersonOrganizationId());
    }

    @Override
    public void delete(PersonOrganization personOrganization) {
        jdbcTemplate.update(SQL_DELETE_PERSON_ORGANIZATION, personOrganization.getPersonOrganizationId());
    }

    @Override
    public List<PersonOrganization> getAllPersonOrganizations() {
        return jdbcTemplate.query(SQL_SELECT_ALL_PERSON_ORGANIZATIONS, new PersonOrganizationMapper());
    }
    
    @Override
    public List<PersonOrganization> getAllPersonOrganizationsByPerson(Person person){
        return jdbcTemplate.query(SQL_SELECT_ALL_PERSON_ORGANIZATIONS_BY_PERSON, new PersonOrganizationMapper(), person.getPersonId());
    }
    
    private static final class PersonOrganizationMapper implements RowMapper<PersonOrganization> {
        
        @Override
        public PersonOrganization mapRow(ResultSet rs, int i) throws SQLException {
            PersonOrganization po = new PersonOrganization();
            po.setPersonOrganizationId(rs.getInt("Person_Organization_ID"));
            
            int personId = rs.getInt("Person_ID");
            String personType = rs.getString("Person_Type");
            String personName = rs.getString("Person_Name");
            String personDescription = rs.getString("Person_Description");
            String personPower = rs.getString("Person_Power");
            Person person = new Person();
            person.setPersonId(personId);
            person.setPersonType(personType);
            person.setPersonName(personName);
            person.setPersonDescription(personDescription);
            person.setPersonPower(personPower);
            po.setPerson(person);
            
            int organizationId = rs.getInt("Organization_ID");
            String organizationName = rs.getString("Organization_Name");
            String organizationDescription = rs.getString("Organization_Description");
            int organizationAddressId = rs.getInt("Organization_Address_ID");
            String organizationContactName = rs.getString("Organization_Contact_Name");
            String organizationContactPhone = rs.getString("Organization_Contact_Phone");
            String organizationContactEmail = rs.getString("Organization_Contact_Email");
            Organization org = new Organization();
            org.setOrganizationId(organizationId);
            org.setOrganizationName(organizationName);
            org.setOrganizationDescription(organizationDescription);
            org.setOrganizationAddressId(organizationAddressId);
            org.setOrganizationContactName(organizationContactName);
            org.setOrganizationContactPhone(organizationContactPhone);
            org.setOrganizationContactEmail(organizationContactEmail);
            po.setOrganization(org);
            
            return po;
            
            
        }
    }
}
