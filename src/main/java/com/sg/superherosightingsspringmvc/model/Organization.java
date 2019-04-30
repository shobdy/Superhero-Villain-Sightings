package com.sg.superherosightingsspringmvc.model;

import java.util.Objects;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class Organization {
    private int organizationId;
    @NotEmpty(message="You must supply a value for Name.")
    @Length(max=30, message="Name must be no more than 30 characters in length.")
    private String organizationName;
    @NotEmpty(message="You must supply a value for Description.")
    @Length(max=50, message="Description must be no more than 50 characters in length.")
    private String organizationDescription;
    private int organizationAddressId;
    @NotEmpty(message="You must supply a value for Contact Name.")
    @Length(max=30, message="Contact Name must be no more than 30 characters in length.")
    private String organizationContactName;
    @Length(max=20, message="Contact Phone must be no more than 20 characters in length.")
    private String organizationContactPhone;
    @Length(max=50, message="Contact Email must be no more than 50 characters in length.")
    private String organizationContactEmail;
    private boolean organizationActive;

    public int getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(int organizationId) {
        this.organizationId = organizationId;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getOrganizationDescription() {
        return organizationDescription;
    }

    public void setOrganizationDescription(String organizationDescription) {
        this.organizationDescription = organizationDescription;
    }

    public int getOrganizationAddressId() {
        return organizationAddressId;
    }

    public void setOrganizationAddressId(int organizationAddressId) {
        this.organizationAddressId = organizationAddressId;
    }

    public String getOrganizationContactName() {
        return organizationContactName;
    }

    public void setOrganizationContactName(String organizationContactName) {
        this.organizationContactName = organizationContactName;
    }

    public String getOrganizationContactPhone() {
        return organizationContactPhone;
    }

    public void setOrganizationContactPhone(String organizationContactPhone) {
        this.organizationContactPhone = organizationContactPhone;
    }

    public String getOrganizationContactEmail() {
        return organizationContactEmail;
    }

    public void setOrganizationContactEmail(String organizationContactEmail) {
        this.organizationContactEmail = organizationContactEmail;
    }

    public boolean isOrganizationActive() {
        return organizationActive;
    }

    public void setOrganizationActive(boolean organizationActive) {
        this.organizationActive = organizationActive;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + this.organizationId;
        hash = 79 * hash + Objects.hashCode(this.organizationName);
        hash = 79 * hash + Objects.hashCode(this.organizationDescription);
        hash = 79 * hash + this.organizationAddressId;
        hash = 79 * hash + Objects.hashCode(this.organizationContactName);
        hash = 79 * hash + Objects.hashCode(this.organizationContactPhone);
        hash = 79 * hash + Objects.hashCode(this.organizationContactEmail);
        hash = 79 * hash + (this.organizationActive ? 1 : 0);
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
        final Organization other = (Organization) obj;
        if (this.organizationId != other.organizationId) {
            return false;
        }
        if (this.organizationAddressId != other.organizationAddressId) {
            return false;
        }
        if (this.organizationActive != other.organizationActive) {
            return false;
        }
        if (!Objects.equals(this.organizationName, other.organizationName)) {
            return false;
        }
        if (!Objects.equals(this.organizationDescription, other.organizationDescription)) {
            return false;
        }
        if (!Objects.equals(this.organizationContactName, other.organizationContactName)) {
            return false;
        }
        if (!Objects.equals(this.organizationContactPhone, other.organizationContactPhone)) {
            return false;
        }
        if (!Objects.equals(this.organizationContactEmail, other.organizationContactEmail)) {
            return false;
        }
        return true;
    }
}
