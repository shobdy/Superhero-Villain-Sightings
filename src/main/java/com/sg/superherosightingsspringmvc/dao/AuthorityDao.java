package com.sg.superherosightingsspringmvc.dao;

import com.sg.superherosightingsspringmvc.model.Authority;
import com.sg.superherosightingsspringmvc.model.User;
import java.util.List;

public interface AuthorityDao {
    public Authority create(Authority authority);
    public Authority read(int authorityId);
    public void update(Authority authority);
    public void delete(Authority authority);
    public List<Authority> getAllAuthorities();
    public List<Authority> getAuthoritiesByUser(User user);
}
