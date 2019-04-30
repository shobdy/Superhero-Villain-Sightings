package com.sg.superherosightingsspringmvc.controller;

import com.sg.superherosightingsspringmvc.commandView.UserCommandModelEdit;
import com.sg.superherosightingsspringmvc.model.User;
import com.sg.superherosightingsspringmvc.view.UserCommandModelCreateEdit;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import com.sg.superherosightingsspringmvc.model.Authority;
import com.sg.superherosightingsspringmvc.service.AuthorityService;
import com.sg.superherosightingsspringmvc.service.UserService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="/Admin") // Sets the base folder this controller works from.
public class UserController {
    private UserService userService;
    private AuthorityService authorityService;
    private PasswordEncoder encoder;
    
    @Inject
    public UserController(UserService userService, AuthorityService authorityService, PasswordEncoder encoder){
        this.userService = userService;
        this.authorityService = authorityService;
        this.encoder = encoder;
    }
    
    @RequestMapping(value="/displayUserList", method = RequestMethod.GET)
    public String displayUserList(Model model){
        UserCommandModelCreateEdit commandModel = new UserCommandModelCreateEdit();
        // Create users list
        List<UserCommandModelEdit> userList = new ArrayList<>();
        List<User> users = userService.getAllUsers();
        for(User currentUser : users){
            UserCommandModelEdit tempUser = new UserCommandModelEdit();
            tempUser.setUserId(currentUser.getId());
            tempUser.setUsername(currentUser.getUsername());
            tempUser.setEnabled(currentUser.isEnabled());
            boolean isAdmin = false;
            List<Authority> authorityList = authorityService.getAuthoritiesByUser(currentUser);
            for(Authority currentAuthority : authorityList){
                if(currentAuthority.getAuthority().equals("ROLE_ADMIN")){
                    isAdmin = true;
                }
            }
            tempUser.setAdmin(isAdmin);
            userList.add(tempUser);
        }
        
        
        commandModel.setUserList(userList);
        
        model.addAttribute("commandModel", commandModel);
        return "Admin/displayUserList";
    }
    
    @RequestMapping(value="/addUser", method = RequestMethod.POST)
    public String addUser(@Valid @ModelAttribute("commandModel") UserCommandModelCreateEdit commandModel, BindingResult result, HttpServletRequest req, Model model){
        
        // Created this block to check for duplicate username and pass custom error message back to 'result'.
        List<User> userDuplicateCheck = userService.getAllUsers();
        for(User currentUser : userDuplicateCheck){
            if(currentUser.getUsername().equals(req.getParameter("username"))){
                // result.rejectValue(String field, String errorCode, String defaultMessage);
                result.rejectValue("username", "DuplicateUser", "Duplicate user, please select another username.");
            }
        }
        
        if(result.hasErrors()){
            // Create users list
            List<UserCommandModelEdit> userList = new ArrayList<>();
            List<User> users = userService.getAllUsers();
            for(User currentUser : users){
                UserCommandModelEdit tempUser = new UserCommandModelEdit();
                tempUser.setUserId(currentUser.getId());
                tempUser.setUsername(currentUser.getUsername());
                tempUser.setEnabled(currentUser.isEnabled());
                boolean isAdmin = false;
                List<Authority> authorityList = authorityService.getAuthoritiesByUser(currentUser);
                for(Authority currentAuthority : authorityList){
                    if(currentAuthority.getAuthority().equals("ROLE_ADMIN")){
                        isAdmin = true;
                    }
                }
                tempUser.setAdmin(isAdmin);
                userList.add(tempUser);
            }
            commandModel.setUserList(userList);
            model.addAttribute("commandModel", commandModel);
            return "Admin/displayUserList";
        }
        
        User newUser = new User();
        newUser.setUsername(req.getParameter("username"));
        String clearPw = req.getParameter("password");
        String hashPw = encoder.encode(clearPw);
        newUser.setPassword(hashPw);
        userService.create(newUser);
        
        Authority newAuthority = new Authority();
        newAuthority.setUserId(newUser.getId());
        newAuthority.setAuthority("ROLE_USER");
        authorityService.create(newAuthority);

        if(req.getParameter("admin") != null){
            newAuthority.setAuthority("ROLE_ADMIN");
            authorityService.create(newAuthority);
        }
        return "redirect:displayUserList";
    }
    
    @RequestMapping(value="/deleteUser", method = RequestMethod.GET)
    public String deleteUser(HttpServletRequest request){
        int userId = Integer.parseInt(request.getParameter("id"));
        User user = userService.read(userId);
        List<Authority> authorityList = authorityService.getAuthoritiesByUser(user);
        for( Authority currentAuthority : authorityList){
            authorityService.delete(currentAuthority);
        }
        userService.delete(user);
        return "redirect:displayUserList";
    }
    
    @RequestMapping(value="/displayEditUserPage", method=RequestMethod.GET)
    public String displayEditUserPage(HttpServletRequest request, Model model){
        boolean isAdmin = false;
        UserCommandModelCreateEdit commandModel = new UserCommandModelCreateEdit();
        
        // Add user info to commandModel
         User user = userService.read(Integer.parseInt(request.getParameter("id")));
         commandModel.setId(user.getId());
         commandModel.setUsername(user.getUsername());
         commandModel.setPassword(user.getPassword());
         commandModel.setEnabled(user.isEnabled());
         
         List<Authority> authorityList = authorityService.getAuthoritiesByUser(user);
         for(Authority currentAuthority : authorityList){
            if(currentAuthority.getAuthority().equals("ROLE_ADMIN")){
                isAdmin = true;
            }
         }
        commandModel.setAdmin(isAdmin);
         
         model.addAttribute("commandModel", commandModel);
        return "Admin/editUserPage";
    }
    
    @RequestMapping(value="/editUser", method = RequestMethod.POST)
    public String editUser(@Valid @ModelAttribute("commandModel") UserCommandModelCreateEdit commandModel, BindingResult result, Model model){
        if(result.hasErrors()){
            // Create users list
            List<UserCommandModelEdit> userList = new ArrayList<>();
            List<User> users = userService.getAllUsers();
            for(User currentUser : users){
                UserCommandModelEdit tempUser = new UserCommandModelEdit();
                tempUser.setUserId(currentUser.getId());
                tempUser.setUsername(currentUser.getUsername());
                tempUser.setEnabled(currentUser.isEnabled());
                boolean isAdmin = false;
                List<Authority> authorityList = authorityService.getAuthoritiesByUser(currentUser);
                for(Authority currentAuthority : authorityList){
                    if(currentAuthority.getAuthority().equals("ROLE_ADMIN")){
                        isAdmin = true;
                    }
                }
                tempUser.setAdmin(isAdmin);
                userList.add(tempUser);
            }
            commandModel.setUserList(userList);
            model.addAttribute("commandModel", commandModel);
            return "Admin/editUserPage";
        }
        User user = new User();
        user.setId(commandModel.getId());
        user.setUsername(commandModel.getUsername());
        user.setEnabled(commandModel.isEnabled());
        userService.update(user);
        
        Authority authority = new Authority();
        authority.setUserId(commandModel.getId());
        List<Authority> authorityList = authorityService.getAuthoritiesByUser(user);
        for( Authority currentAuthority : authorityList){
            if(currentAuthority.getAuthority().equals("ROLE_ADMIN")){
                authorityService.delete(currentAuthority);
            }
        }
        
        if(commandModel.isAdmin()){
            authority.setAuthority("ROLE_ADMIN");
            authorityService.create(authority);
        }
        return "redirect:displayUserList";
    }
    
    @RequestMapping(value="/displayChangePasswordPage", method=RequestMethod.GET)
    public String displayChangePasswordPage(HttpServletRequest request, Model model){
        boolean isAdmin = false;
        UserCommandModelCreateEdit commandModel = new UserCommandModelCreateEdit();
        
        // Add user info to commandModel
         User user = userService.read(Integer.parseInt(request.getParameter("id")));
         commandModel.setId(user.getId());
         commandModel.setUsername(user.getUsername());
         commandModel.setEnabled(user.isEnabled());
         
         List<Authority> authorityList = authorityService.getAuthoritiesByUser(user);
         for(Authority currentAuthority : authorityList){
            if(currentAuthority.getAuthority().equals("ROLE_ADMIN")){
                isAdmin = true;
            }
         }
        commandModel.setAdmin(isAdmin);
         
         model.addAttribute("commandModel", commandModel);
        return "Admin/changePassword";
    }
    
    @RequestMapping(value="/changePassword", method = RequestMethod.POST)
    public String changePassword(@Valid @ModelAttribute("commandModel") UserCommandModelCreateEdit commandModel, BindingResult result, Model model){
        if(result.hasErrors()){
            // Create users list
            List<UserCommandModelEdit> userList = new ArrayList<>();
            List<User> users = userService.getAllUsers();
            for(User currentUser : users){
                UserCommandModelEdit tempUser = new UserCommandModelEdit();
                tempUser.setUserId(currentUser.getId());
                tempUser.setUsername(currentUser.getUsername());
                tempUser.setEnabled(currentUser.isEnabled());
                boolean isAdmin = false;
                List<Authority> authorityList = authorityService.getAuthoritiesByUser(currentUser);
                for(Authority currentAuthority : authorityList){
                    if(currentAuthority.getAuthority().equals("ROLE_ADMIN")){
                        isAdmin = true;
                    }
                }
                tempUser.setAdmin(isAdmin);
                userList.add(tempUser);
            }
            commandModel.setUserList(userList);
            model.addAttribute("commandModel", commandModel);
            return "Admin/changePassword";
        }
        User user = new User();
        user.setId(commandModel.getId());
        user.setUsername(commandModel.getUsername());
        String clearPw = commandModel.getPassword();
        String hashPw = encoder.encode(clearPw);
        user.setPassword(hashPw);
        user.setEnabled(commandModel.isEnabled());
        userService.changePassword(user);
        
        return "redirect:displayUserList";
    }
    
}
