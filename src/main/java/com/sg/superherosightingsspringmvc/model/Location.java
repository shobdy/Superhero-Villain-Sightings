package com.sg.superherosightingsspringmvc.model;

import java.util.Objects;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class Location {
    private int locationId;
    @NotEmpty(message="You must supply a value for Name.")
    @Length(max=30, message="Name must be no more than 30 characters in length.")
    private String locationName;
    @NotEmpty(message="You must supply a value for Description.")
    @Length(max=1024, message="Description must be no more than 1024 characters in length.")
    private String locationDescription;
    private int locationAddressId;
    @NotEmpty(message="You must supply a value for Latitude.")
    @Length(max=20, message="Latitude must be no more than 20 characters in length.")
    private String locationLatitude;
    @NotEmpty(message="You must supply a value for Longitude.")
    @Length(max=20, message="Longitude must be no more than 20 characters in length.")
    private String locationLongitude;
    private boolean locationActive;

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getLocationDescription() {
        return locationDescription;
    }

    public void setLocationDescription(String locationDescription) {
        this.locationDescription = locationDescription;
    }

    public int getLocationAddressId() {
        return locationAddressId;
    }

    public void setLocationAddressId(int locationAddressId) {
        this.locationAddressId = locationAddressId;
    }

    public String getLocationLatitude() {
        return locationLatitude;
    }

    public void setLocationLatitude(String locationLatitude) {
        this.locationLatitude = locationLatitude;
    }

    public String getLocationLongitude() {
        return locationLongitude;
    }

    public void setLocationLongitude(String locationLongitude) {
        this.locationLongitude = locationLongitude;
    }

    public boolean isLocationActive() {
        return locationActive;
    }

    public void setLocationActive(boolean locationActive) {
        this.locationActive = locationActive;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + this.locationId;
        hash = 29 * hash + Objects.hashCode(this.locationName);
        hash = 29 * hash + Objects.hashCode(this.locationDescription);
        hash = 29 * hash + this.locationAddressId;
        hash = 29 * hash + Objects.hashCode(this.locationLatitude);
        hash = 29 * hash + Objects.hashCode(this.locationLongitude);
        hash = 29 * hash + (this.locationActive ? 1 : 0);
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
        final Location other = (Location) obj;
        if (this.locationId != other.locationId) {
            return false;
        }
        if (this.locationAddressId != other.locationAddressId) {
            return false;
        }
        if (this.locationActive != other.locationActive) {
            return false;
        }
        if (!Objects.equals(this.locationName, other.locationName)) {
            return false;
        }
        if (!Objects.equals(this.locationDescription, other.locationDescription)) {
            return false;
        }
        if (!Objects.equals(this.locationLatitude, other.locationLatitude)) {
            return false;
        }
        if (!Objects.equals(this.locationLongitude, other.locationLongitude)) {
            return false;
        }
        return true;
    }
}
