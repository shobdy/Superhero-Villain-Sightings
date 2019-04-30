package com.sg.superherosightingsspringmvc.view;

import com.sg.superherosightingsspringmvc.model.Organization;
import com.sg.superherosightingsspringmvc.model.Person;
import java.util.List;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class PersonCommandModelCreateEdit {
    
    private int personId;
    @NotEmpty(message="You must supply a value for Type.")
    @Length(max=20, message="Type must be no more than 20 characters in length.")
    private String personType;
    @NotEmpty(message="You must supply a value for Name.")
    @Length(max=20, message="Name must be no more than 30 characters in length.")
    private String personName;
    @Length(max=1024, message="Name must be no more than 1024 characters in length.")
    private String personDescription;
    @Length(max=256, message="Power must be no more than 256 characters in length.")
    private String personPower;
    private List<Person> personList;
    
    private List<Organization> organizationList;
    
    private int[] organizationIds;

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public String getPersonType() {
        return personType;
    }

    public void setPersonType(String personType) {
        this.personType = personType;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getPersonDescription() {
        return personDescription;
    }

    public void setPersonDescription(String personDescription) {
        this.personDescription = personDescription;
    }

    public String getPersonPower() {
        return personPower;
    }

    public void setPersonPower(String personPower) {
        this.personPower = personPower;
    }

    public List<Person> getPersonList() {
        return personList;
    }

    public void setPersonList(List<Person> personList) {
        this.personList = personList;
    }

    public List<Organization> getOrganizationList() {
        return organizationList;
    }

    public void setOrganizationList(List<Organization> organizationList) {
        this.organizationList = organizationList;
    }
    
    public int[] getOrganizationIds() {
        return organizationIds;
    }

    public void setOrganizationIds(int[] organizationIds) {
        this.organizationIds = organizationIds;
    }
    
}
