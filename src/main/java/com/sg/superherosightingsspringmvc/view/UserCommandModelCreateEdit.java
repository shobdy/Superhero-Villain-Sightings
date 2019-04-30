package com.sg.superherosightingsspringmvc.view;

import com.sg.superherosightingsspringmvc.commandView.UserCommandModelEdit;
import com.sg.superherosightingsspringmvc.model.User;
import java.util.List;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class UserCommandModelCreateEdit {
    private int id;
    @NotEmpty(message="You must supply a value for Username.")
    @Length(max=20, message="Username can be no longer than 20 characters")
    private String username;
    @NotEmpty(message="You must supply a value for password.")
    @Length(min=4, message="Password must be at least 4 characters.")
    private String password;
    private boolean enabled;
    private List<String> authorities;
    private boolean admin;
    private List<UserCommandModelEdit> userList;
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public List<String> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<String> authorities) {
        this.authorities = authorities;
    }
    
    public void addAuthority(String authority){
        authorities.add(authority);
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public List<UserCommandModelEdit> getUserList() {
        return userList;
    }

    public void setUserList(List<UserCommandModelEdit> userList) {
        this.userList = userList;
    }
}
