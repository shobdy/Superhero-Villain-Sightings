package com.sg.superherosightingsspringmvc.view;

import com.sg.superherosightingsspringmvc.model.Location;
import java.util.List;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class LocationCommandModelCreateEdit {
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
    
    private List<Location> locationList;

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

    public List<Location> getLocationList() {
        return locationList;
    }

    public void setLocationList(List<Location> locationList) {
        this.locationList = locationList;
    }
}
