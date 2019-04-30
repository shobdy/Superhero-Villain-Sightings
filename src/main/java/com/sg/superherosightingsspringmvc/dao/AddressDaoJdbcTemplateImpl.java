package com.sg.superherosightingsspringmvc.dao;

import com.sg.superherosightingsspringmvc.model.Address;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class AddressDaoJdbcTemplateImpl implements AddressDao{
    
    private JdbcTemplate jdbcTemplate;
    
    private static final String SQL_INSERT_ADDRESS
            = "INSERT INTO Addresses (Address_Line1, Address_Line2, Address_City, Address_State, Address_Zip, Address_Country) "
            + "VALUES (?, ?, ?, ?, ?, ?)";
    
    private static final String SQL_SELECT_ADDRESS
            = "SELECT * FROM Addresses WHERE Address_ID = ?";
    
    private static final String SQL_UPDATE_ADDRESS
            = "UPDATE Addresses "
            + "SET Address_Line1 = ?, Address_Line2 = ?, Address_City = ?, Address_State = ?, Address_Zip = ?, Address_Country = ? "
            + "WHERE Address_ID = ?";
    
    private static final String SQL_DELETE_ADDRESS
            = "DELETE FROM Addresses WHERE Address_ID = ?";
    
    private static final String SQL_SELECT_ALL_ADDRESSES
            = "SELECT * FROM Addresses";
    
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Address create(Address address) {
        jdbcTemplate.update(SQL_INSERT_ADDRESS,
                address.getAddressLine1(),
                address.getAddressLine2(),
                address.getAddressCity(),
                address.getAddressState(),
                address.getAddressZip(),
                address.getAddressCountry());
        int addressId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
        address.setAddressId(addressId);
        return address;
    }

    @Override
    public Address read(int addressId) {
        try{
            return jdbcTemplate.queryForObject((SQL_SELECT_ADDRESS), new AddressMapper(), addressId);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public void update(Address address) {
        jdbcTemplate.update(SQL_UPDATE_ADDRESS,
                address.getAddressLine1(),
                address.getAddressLine2(),
                address.getAddressCity(),
                address.getAddressState(),
                address.getAddressZip(),
                address.getAddressCountry(),
                address.getAddressId());
    }

    @Override
    public void delete(Address address) {
        jdbcTemplate.update(SQL_DELETE_ADDRESS, address.getAddressId());
    }

    @Override
    public List<Address> getAllAddresses() {
        return jdbcTemplate.query(SQL_SELECT_ALL_ADDRESSES, new AddressMapper());
    }
    
    private static final class AddressMapper implements RowMapper<Address> {
        
        @Override
        public Address mapRow(ResultSet rs, int i) throws SQLException {
            Address ad = new Address();
            ad.setAddressLine1(rs.getString("Address_Line1"));
            ad.setAddressLine2(rs.getString("Address_Line2"));
            ad.setAddressCity(rs.getString("Address_City"));
            ad.setAddressState(rs.getString("Address_State"));
            ad.setAddressZip(rs.getString("Address_Zip"));
            ad.setAddressCountry(rs.getString("Address_Country"));
            ad.setAddressId(rs.getInt("Address_ID"));
            return ad;
        }
    }
    
}
