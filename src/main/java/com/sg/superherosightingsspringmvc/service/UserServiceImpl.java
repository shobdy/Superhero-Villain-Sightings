package com.sg.superherosightingsspringmvc.service;

import com.sg.superherosightingsspringmvc.model.User;
import java.util.List;
import javax.inject.Inject;
import com.sg.superherosightingsspringmvc.dao.UserDao;

public class UserServiceImpl implements UserService{
    
    UserDao userDao;
    
    @Inject
    public UserServiceImpl(UserDao userDao){
        this.userDao = userDao;
    }

    @Override
    public User create(User user) {
        return userDao.create(user);
    }

    @Override
    public User read(int userId) {
        return userDao.read(userId);
    }

    @Override
    public void update(User user) {
        userDao.update(user);
    }

    @Override
    public void delete(User user) {
        userDao.delete(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public void changePassword(User user) {
        userDao.changePassword(user);
    }
}
