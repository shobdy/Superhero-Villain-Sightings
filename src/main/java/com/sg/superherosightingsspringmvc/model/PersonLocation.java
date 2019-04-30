package com.sg.superherosightingsspringmvc.model;

import java.time.LocalDate;
import java.util.Objects;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

public class PersonLocation {
    private int personLocationId;
    private Person person;
    private Location location;
    
    // THIS IS HOW I GOT THE DATE FORMAT CONSISTENT IN THE SIGHTINGS EDIT JSP.
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @NotNull(message="You must supply a value for Date.")
    private LocalDate dateSighted;

    public int getPersonLocationId() {
        return personLocationId;
    }

    public void setPersonLocationId(int personLocationId) {
        this.personLocationId = personLocationId;
    }
    
    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public LocalDate getDateSighted() {
        return dateSighted;
    }

    public void setDateSighted(LocalDate dateSighted) {
        this.dateSighted = dateSighted;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + this.personLocationId;
        hash = 37 * hash + Objects.hashCode(this.person);
        hash = 37 * hash + Objects.hashCode(this.location);
        hash = 37 * hash + Objects.hashCode(this.dateSighted);
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
        final PersonLocation other = (PersonLocation) obj;
        if (this.personLocationId != other.personLocationId) {
            return false;
        }
        if (!Objects.equals(this.person, other.person)) {
            return false;
        }
        if (!Objects.equals(this.location, other.location)) {
            return false;
        }
        if (!Objects.equals(this.dateSighted, other.dateSighted)) {
            return false;
        }
        return true;
    }
}
