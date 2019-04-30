package com.sg.superherosightingsspringmvc.dao;

import com.sg.superherosightingsspringmvc.model.Organization;
import com.sg.superherosightingsspringmvc.model.Person;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class OrganizationDaoJdbcTemplateImpl implements OrganizationDao {
    
    private JdbcTemplate jdbcTemplate;
    
    private static final String SQL_INSERT_ORGANIZATION
            = "INSERT INTO Organizations (Organization_Name, Organization_Description, Organization_Address_ID, Organization_Contact_Name, "
            + "Organization_Contact_Phone, Organization_Contact_Email) "
            + "VALUES (?, ?, ?, ?, ?, ?)";
    
    private static final String SQL_SELECT_ORGANIZATION
            = "SELECT * FROM Organizations WHERE Organization_ID = ? AND Organization_Active = 1";
    
    private static final String SQL_UPDATE_ORGANIZATION
            = "UPDATE Organizations SET Organization_Name = ?, Organization_Description = ?, Organization_Address_ID = ?, Organization_Contact_Name = ?, "
            + "Organization_Contact_Phone = ?, Organization_Contact_Email = ? "
            + "WHERE Organization_ID = ?";
    
    private static final String SQL_DELETE_ORGANIZATION
            = "UPDATE Organizations SET Organization_Active = 0 WHERE Organization_ID = ?";
    
    private static final String SQL_SELECT_ALL_ORGANIZATIONS
            = "SELECT * FROM Organizations WHERE Organization_Active = 1";
    
    private static final String SQL_SELECT_ORGANIZATIONS_BY_PERSON
            = "SELECT o.Organization_Name, o.Organization_Description, o.Organization_Address_ID, o.Organization_Contact_Name, "
            + "o.Organization_Contact_Phone, o.Organization_Contact_Email, o.Organization_ID, po.Organization_ID "
            + "FROM Organizations o "
            + "JOIN Person_Organization po ON o.Organization_ID = po.Organization_ID "
            + "WHERE po.Person_ID = ? AND Organization_Active = 1";
    
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Organization create(Organization organization) {
        jdbcTemplate.update(SQL_INSERT_ORGANIZATION,
                organization.getOrganizationName(),
                organization.getOrganizationDescription(),
                organization.getOrganizationAddressId(),
                organization.getOrganizationContactName(),
                organization.getOrganizationContactPhone(),
                organization.getOrganizationContactEmail());
        int organizationId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
        organization.setOrganizationId(organizationId);
        return organization;
    }

    @Override
    public Organization read(int organizationId) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_ORGANIZATION, new OrganizationMapper(), organizationId);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public void update(Organization organization) {
        jdbcTemplate.update(SQL_UPDATE_ORGANIZATION, 
                organization.getOrganizationName(),
                organization.getOrganizationDescription(),
                organization.getOrganizationAddressId(),
                organization.getOrganizationContactName(),
                organization.getOrganizationContactPhone(),
                organization.getOrganizationContactEmail(),
                organization.getOrganizationId());
    }

    @Override
    public void delete(Organization organization) {
        jdbcTemplate.update(SQL_DELETE_ORGANIZATION, organization.getOrganizationId());
    }

    @Override
    public List<Organization> getAllOrganizations() {
        return jdbcTemplate.query(SQL_SELECT_ALL_ORGANIZATIONS, new OrganizationMapper());
    }

    @Override
    public List<Organization> getOrganizationsByPerson(Person person) {
        return jdbcTemplate.query(SQL_SELECT_ORGANIZATIONS_BY_PERSON, new OrganizationMapper(), person.getPersonId());
    }
    
    private static final class OrganizationMapper implements RowMapper<Organization> {
        @Override
        public Organization mapRow(ResultSet rs, int i) throws SQLException {
            Organization org = new Organization();
            org.setOrganizationId(rs.getInt("Organization_ID"));
            org.setOrganizationName(rs.getString("Organization_Name"));
            org.setOrganizationDescription(rs.getString("Organization_Description"));
            org.setOrganizationAddressId(rs.getInt("Organization_Address_ID"));
            org.setOrganizationContactName(rs.getString("Organization_Contact_Name"));
            org.setOrganizationContactPhone(rs.getString("Organization_Contact_Phone"));
            org.setOrganizationContactEmail(rs.getString("Organization_Contact_Email"));
            return org;
        }
    }
    
}
