package com.sg.superherosightingsspringmvc.model;

import java.util.Objects;

public class PersonOrganization {
    
    private int personOrganizationId;
    private Person person;
    private Organization organization;

    public int getPersonOrganizationId() {
        return personOrganizationId;
    }

    public void setPersonOrganizationId(int personOrganizationId) {
        this.personOrganizationId = personOrganizationId;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + this.personOrganizationId;
        hash = 71 * hash + Objects.hashCode(this.person);
        hash = 71 * hash + Objects.hashCode(this.organization);
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
        final PersonOrganization other = (PersonOrganization) obj;
        if (this.personOrganizationId != other.personOrganizationId) {
            return false;
        }
        if (!Objects.equals(this.person, other.person)) {
            return false;
        }
        if (!Objects.equals(this.organization, other.organization)) {
            return false;
        }
        return true;
    }
}
