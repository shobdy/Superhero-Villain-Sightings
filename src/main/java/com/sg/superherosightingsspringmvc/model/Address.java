package com.sg.superherosightingsspringmvc.model;

import java.util.Objects;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class Address {
    private int addressId;
    @NotEmpty(message="You must supply a value for Address Line 1.")
    @Length(max=50, message="Address Line 1 must be no more than 50 characters in length.")
    private String addressLine1;
    @Length(max=50, message="Address Line 2 must be no more than 50 characters in length.")
    private String addressLine2;
    @NotEmpty(message="You must supply a value for City.")
    @Length(max=30, message="City must be no more than 30 characters in length.")
    private String addressCity;
    @NotEmpty(message="You must supply a value for State.")
    @Length(max=30, message="State must be no more than 30 characters in length.")
    private String addressState;
    @NotEmpty(message="You must supply a value for Zipcode.")
    @Length(max=10, message="Zipcode must be no more than 10 characters in length.")
    private String addressZip;
    @NotEmpty(message="You must supply a value for Country.")
    @Length(max=30, message="Country must be no more than 30 characters in length.")
    private String addressCountry;

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getAddressCity() {
        return addressCity;
    }

    public void setAddressCity(String addressCity) {
        this.addressCity = addressCity;
    }

    public String getAddressState() {
        return addressState;
    }

    public void setAddressState(String addressState) {
        this.addressState = addressState;
    }

    public String getAddressZip() {
        return addressZip;
    }

    public void setAddressZip(String addressZip) {
        this.addressZip = addressZip;
    }

    public String getAddressCountry() {
        return addressCountry;
    }

    public void setAddressCountry(String addressCountry) {
        this.addressCountry = addressCountry;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + this.addressId;
        hash = 67 * hash + Objects.hashCode(this.addressLine1);
        hash = 67 * hash + Objects.hashCode(this.addressLine2);
        hash = 67 * hash + Objects.hashCode(this.addressCity);
        hash = 67 * hash + Objects.hashCode(this.addressState);
        hash = 67 * hash + Objects.hashCode(this.addressZip);
        hash = 67 * hash + Objects.hashCode(this.addressCountry);
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
        final Address other = (Address) obj;
        if (this.addressId != other.addressId) {
            return false;
        }
        if (!Objects.equals(this.addressLine1, other.addressLine1)) {
            return false;
        }
        if (!Objects.equals(this.addressLine2, other.addressLine2)) {
            return false;
        }
        if (!Objects.equals(this.addressCity, other.addressCity)) {
            return false;
        }
        if (!Objects.equals(this.addressState, other.addressState)) {
            return false;
        }
        if (!Objects.equals(this.addressZip, other.addressZip)) {
            return false;
        }
        if (!Objects.equals(this.addressCountry, other.addressCountry)) {
            return false;
        }
        return true;
    }
}
