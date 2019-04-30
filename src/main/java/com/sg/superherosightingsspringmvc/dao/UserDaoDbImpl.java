package com.sg.superherosightingsspringmvc.dao;

import com.sg.superherosightingsspringmvc.model.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class UserDaoDbImpl implements UserDao {

    private static final String SQL_INSERT_USER
            = "INSERT INTO users (username, password, enabled) values (?, ?, 1)";
    private static final String SQL_SELECT_USER
            = "SELECT * FROM users WHERE user_id = ?";
    private static final String SQL_UPDATE_USER
            = "UPDATE users SET username=?, enabled = ? WHERE user_id=?";
    private static final String SQL_DELETE_USER
            = "DELETE FROM users WHERE user_id=?";
    private static final String SQL_GET_ALL_USERS
            = "SELECT * FROM users";
    private static final String SQL_UPDATE_USER_PASSWORD
            = "UPDATE users SET password = ? WHERE user_id = ?";

    JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public User create(User user) {
        jdbcTemplate.update(SQL_INSERT_USER, user.getUsername(), user.getPassword());
        user.setId(jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class));
        return user;
    }
    
    @Override
    public User read(int userId){
        try{
            return jdbcTemplate.queryForObject(SQL_SELECT_USER, new UserMapper(), userId);
        } catch(EmptyResultDataAccessException ex){
            return null;
        }
    }
    
    @Override
    public void update(User user){
        jdbcTemplate.update(SQL_UPDATE_USER,
                user.getUsername(),
                user.isEnabled(),
                user.getId());
    }

    @Override
    public void delete(User user) {
        jdbcTemplate.update(SQL_DELETE_USER, user.getId());
    }

    @Override
    public List<User> getAllUsers() {
        return jdbcTemplate.query(SQL_GET_ALL_USERS, new UserMapper());
    }
    
    @Override
    public void changePassword(User user){
        jdbcTemplate.update(SQL_UPDATE_USER_PASSWORD, 
                user.getPassword(),
                user.getId());
    }
    
    private static final class UserMapper implements RowMapper<User>{
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setId(rs.getInt("user_id"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setEnabled(rs.getBoolean("enabled"));
            return user;
        }
    }
}
