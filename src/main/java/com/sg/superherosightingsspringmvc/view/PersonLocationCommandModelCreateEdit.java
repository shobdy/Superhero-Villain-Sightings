package com.sg.superherosightingsspringmvc.view;

import com.sg.superherosightingsspringmvc.model.Location;
import com.sg.superherosightingsspringmvc.model.Person;
import com.sg.superherosightingsspringmvc.model.PersonLocation;
import java.time.LocalDate;
import java.util.List;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

public class PersonLocationCommandModelCreateEdit {
    
    private int personLocationId;
    private int personId;
    private int locationId;
    
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @NotNull(message="You must supply a value for Date.")
    private LocalDate dateSighted;
    private List<Person> personList;
    private List<Location> locationList;
    private List<PersonLocation> personLocationList;

    public int getPersonLocationId() {
        return personLocationId;
    }

    public void setPersonLocationId(int personLocationId) {
        this.personLocationId = personLocationId;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public LocalDate getDateSighted() {
        return dateSighted;
    }

    public void setDateSighted(LocalDate dateSighted) {
        this.dateSighted = dateSighted;
    }

    public List<Person> getPersonList() {
        return personList;
    }

    public void setPersonList(List<Person> personList) {
        this.personList = personList;
    }

    public List<Location> getLocationList() {
        return locationList;
    }

    public void setLocationList(List<Location> locationList) {
        this.locationList = locationList;
    }

    public List<PersonLocation> getPersonLocationList() {
        return personLocationList;
    }

    public void setPersonLocationList(List<PersonLocation> personLocationList) {
        this.personLocationList = personLocationList;
    }
}
