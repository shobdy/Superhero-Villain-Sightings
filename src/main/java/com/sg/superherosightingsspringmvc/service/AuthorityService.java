package com.sg.superherosightingsspringmvc.service;

import com.sg.superherosightingsspringmvc.model.Authority;
import com.sg.superherosightingsspringmvc.model.User;
import java.util.List;

public interface AuthorityService {
    public Authority create(Authority authority);
    public Authority read(int authorityId);
    public void update(Authority authority);
    public void delete(Authority authority);
    public List getAllAuthorities();
    public List getAuthoritiesByUser(User user);
}
