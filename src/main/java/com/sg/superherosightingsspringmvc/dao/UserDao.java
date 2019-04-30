package com.sg.superherosightingsspringmvc.dao;

import com.sg.superherosightingsspringmvc.model.User;
import java.util.List;

public interface UserDao {
    public User create(User user);
    public User read(int userId);
    public void update(User user);
    public void delete(User user);
    public List<User> getAllUsers();
    public void changePassword(User user);
}
