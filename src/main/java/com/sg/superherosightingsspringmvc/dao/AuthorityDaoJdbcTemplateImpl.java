package com.sg.superherosightingsspringmvc.dao;

import com.sg.superherosightingsspringmvc.model.Authority;
import com.sg.superherosightingsspringmvc.model.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class AuthorityDaoJdbcTemplateImpl implements AuthorityDao{
    
    private JdbcTemplate jdbcTemplate;
    
    private static final String SQL_INSERT_AUTHORITY
            = "INSERT INTO authorities (user_id, authority) "
            + "VALUES (?, ?)";
    private static final String SQL_SELECT_AUTHORITY
            = "SELECT * FROM authorities WHERE authority_id = ?";
    private static final String SQL_UPDATE_AUTHORITY
            = "UPDATE authorities "
            + "SET user_id = ?, authority = ? "
            + "WHERE authority_id = ?";
    private static final String SQL_DELETE_AUTHORITY
            = "DELETE FROM authorities WHERE authority_id = ?";
    private static final String SQL_SELECT_ALL_AUTHORITIES
            = "SELECT * from authorities";
    private static final String SQL_SELECT_AUTHORITIES_BY_USER
            = "SELECT a.authority_id, a.user_id, a.authority "
            + "FROM authorities a "
            + "JOIN users u ON a.user_id = u.user_id "
            + "WHERE u.user_id = ?";
    
    
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Authority create(Authority authority) {
        jdbcTemplate.update(SQL_INSERT_AUTHORITY,
                authority.getUserId(),
                authority.getAuthority());
        int authorityId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        authority.setAuthorityId(authorityId);
        return authority;
    }

    @Override
    public Authority read(int authorityId) {
        try{
            return jdbcTemplate.queryForObject(SQL_SELECT_AUTHORITY, new AuthorityMapper(), authorityId);
        } catch (EmptyResultDataAccessException ex){
            return null;
        }
    }

    @Override
    public void update(Authority authority) {
        jdbcTemplate.update(SQL_UPDATE_AUTHORITY,
                authority.getUserId(),
                authority.getAuthority(),
                authority.getAuthorityId());
    }

    @Override
    public void delete(Authority authority) {
        jdbcTemplate.update(SQL_DELETE_AUTHORITY, authority.getAuthorityId());
    }

    @Override
    public List<Authority> getAllAuthorities() {
        return jdbcTemplate.query(SQL_SELECT_ALL_AUTHORITIES, new AuthorityMapper());
    }

    @Override
    public List<Authority> getAuthoritiesByUser(User user) {
        return jdbcTemplate.query(SQL_SELECT_AUTHORITIES_BY_USER, new AuthorityMapper(), user.getId());
    }
    
    private static final class AuthorityMapper implements RowMapper<Authority>{
        @Override
        public Authority mapRow(ResultSet rs, int i) throws SQLException{
            Authority authority = new Authority();
            authority.setAuthorityId(rs.getInt("authority_id"));
            authority.setUserId(rs.getInt("user_id"));
            authority.setAuthority(rs.getString("authority"));
            return authority;
        }
    }

}
