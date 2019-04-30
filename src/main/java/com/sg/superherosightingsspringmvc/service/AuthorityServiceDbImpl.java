package com.sg.superherosightingsspringmvc.service;

import com.sg.superherosightingsspringmvc.dao.AuthorityDao;
import com.sg.superherosightingsspringmvc.model.Authority;
import com.sg.superherosightingsspringmvc.model.User;
import java.util.List;
import javax.inject.Inject;

public class AuthorityServiceDbImpl implements AuthorityService{
    
    AuthorityDao authorityDao;
    
    @Inject
    public AuthorityServiceDbImpl(AuthorityDao authorityDao){
        this.authorityDao = authorityDao;
    }

    @Override
    public Authority create(Authority authority) {
        return authorityDao.create(authority);
    }

    @Override
    public Authority read(int authorityId) {
        return authorityDao.read(authorityId);
    }

    @Override
    public void update(Authority authority) {
        authorityDao.update(authority);
    }

    @Override
    public void delete(Authority authority) {
        authorityDao.delete(authority);
    }

    @Override
    public List getAllAuthorities() {
        return authorityDao.getAllAuthorities();
    }

    @Override
    public List getAuthoritiesByUser(User user) {
        return authorityDao.getAuthoritiesByUser(user);
    }

}
