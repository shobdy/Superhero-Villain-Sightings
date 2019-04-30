package com.sg.superherosightingsspringmvc.view;

import com.sg.superherosightingsspringmvc.model.Organization;
import java.util.List;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class OrganizationCommandModelCreateEdit {
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
    
    List<Organization> organizationList;

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

    public List<Organization> getOrganizationList() {
        return organizationList;
    }

    public void setOrganizationList(List<Organization> organizationList) {
        this.organizationList = organizationList;
    }

}
