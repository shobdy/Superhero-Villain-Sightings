package com.sg.superherosightingsspringmvc.model;

import java.util.Objects;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class Person {
    private int personId;
    @NotEmpty(message="You must supply a value for Type.")
    private String personType;
    @NotEmpty(message="You must supply a value for Name.")
    @Length(max=30, message="Name must be no more than 30 characters in length.")
    private String personName;
    @Length(max=1024, message="Description must be no more than 1024 characters in length.")
    private String personDescription;
    @Length(max=256, message="Power must be no more than 256 characters in length.")
    private String personPower;
    private boolean personActive;

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
    
    public boolean isPersonActive() {
        return personActive;
    }

    public void setPersonActive(boolean personActive) {
        this.personActive = personActive;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 43 * hash + this.personId;
        hash = 43 * hash + Objects.hashCode(this.personType);
        hash = 43 * hash + Objects.hashCode(this.personName);
        hash = 43 * hash + Objects.hashCode(this.personDescription);
        hash = 43 * hash + Objects.hashCode(this.personPower);
        hash = 43 * hash + (this.personActive ? 1 : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Person other = (Person) obj;
        if (this.personId != other.personId) {
            return false;
        }
        if (this.personActive != other.personActive) {
            return false;
        }
        if (!Objects.equals(this.personType, other.personType)) {
            return false;
        }
        if (!Objects.equals(this.personName, other.personName)) {
            return false;
        }
        if (!Objects.equals(this.personDescription, other.personDescription)) {
            return false;
        }
        if (!Objects.equals(this.personPower, other.personPower)) {
            return false;
        }
        return true;
    }
}
