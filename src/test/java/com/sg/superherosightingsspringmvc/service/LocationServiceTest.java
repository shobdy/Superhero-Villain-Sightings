package com.sg.superherosightingsspringmvc.service;

import com.sg.superherosightingsspringmvc.model.Address;
import com.sg.superherosightingsspringmvc.model.Location;
import com.sg.superherosightingsspringmvc.model.Person;
import com.sg.superherosightingsspringmvc.model.PersonLocation;
import java.time.LocalDate;
import java.util.List;
import javax.inject.Inject;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/test-applicationContext.xml"})
@Rollback
@Transactional

public class LocationServiceTest {

    @Inject
    LocationService locationService;
    
    @Inject
    AddressService addressService;
    
    @Inject
    PersonService personService;
    
    @Inject
    PersonLocationService personLocationService;
    
    public LocationServiceTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testCreate() {
        Address a1 = new Address();
        a1.setAddressLine1("Address Line 1");
        a1.setAddressLine2("Address Line 2");
        a1.setAddressCity("Address City 1");
        a1.setAddressState("KY");
        a1.setAddressZip("42301");
        a1.setAddressCountry("USA");
        
        Address createdAddress = addressService.create(a1);
        
        Location l1 = new Location();
        l1.setLocationName("Location 1");
        l1.setLocationDescription("Location Description 1");
        l1.setLocationAddressId(a1.getAddressId());
        l1.setLocationLatitude("Location 1 Latitude");
        l1.setLocationLongitude("Location 1 Longitude");
        
        Location createdLocation = locationService.create(l1);
        
        assert "Location 1".equals(l1.getLocationName());
        assert "Location 1 Latitude".equals(l1.getLocationLatitude());
    }

    @Test
    public void testRead() {
        Address a1 = new Address();
        a1.setAddressLine1("Address Line 1");
        a1.setAddressLine2("Address Line 2");
        a1.setAddressCity("Address City 1");
        a1.setAddressState("KY");
        a1.setAddressZip("42301");
        a1.setAddressCountry("USA");
        
        Address createdAddress = addressService.create(a1);
        
        Location l1 = new Location();
        l1.setLocationName("Location 1");
        l1.setLocationDescription("Location Description 1");
        l1.setLocationAddressId(a1.getAddressId());
        l1.setLocationLatitude("Location 1 Latitude");
        l1.setLocationLongitude("Location 1 Longitude");
        
        Location createdLocation = locationService.create(l1);
        
        Location readLocation = locationService.read(createdLocation.getLocationId());
        
        assert "Location 1".equals(readLocation.getLocationName());
        assert "Location 1 Latitude".equals(readLocation.getLocationLatitude());
    }

    @Test
    public void testUpdate() {
        Address a1 = new Address();
        a1.setAddressLine1("Address Line 1");
        a1.setAddressLine2("Address Line 2");
        a1.setAddressCity("Address City 1");
        a1.setAddressState("KY");
        a1.setAddressZip("42301");
        a1.setAddressCountry("USA");
        
        Address createdAddress = addressService.create(a1);
        
        Location l1 = new Location();
        l1.setLocationName("Location 1");
        l1.setLocationDescription("Location Description 1");
        l1.setLocationAddressId(a1.getAddressId());
        l1.setLocationLatitude("Location 1 Latitude");
        l1.setLocationLongitude("Location 1 Longitude");
        
        Location createdLocation = locationService.create(l1);
        
        Location readLocation = locationService.read(createdLocation.getLocationId());
        
        readLocation.setLocationName("Location 2");
        readLocation.setLocationDescription("Location Description 2");
        
        locationService.update(readLocation);
        
        Location updateLocation = locationService.read(readLocation.getLocationId());
        
        assert "Location 2".equals(updateLocation.getLocationName());
        assert "Location Description 2".equals(updateLocation.getLocationDescription());
    }

    @Test
    public void testDelete() {
        Address a1 = new Address();
        a1.setAddressLine1("Address Line 1");
        a1.setAddressLine2("Address Line 2");
        a1.setAddressCity("Address City 1");
        a1.setAddressState("KY");
        a1.setAddressZip("42301");
        a1.setAddressCountry("USA");
        
        Address createdAddress = addressService.create(a1);
        
        Location l1 = new Location();
        l1.setLocationName("Location 1");
        l1.setLocationDescription("Location Description 1");
        l1.setLocationAddressId(a1.getAddressId());
        l1.setLocationLatitude("Location 1 Latitude");
        l1.setLocationLongitude("Location 1 Longitude");
        
        Location createdLocation = locationService.create(l1);
        
        assert "Location 1".equals(createdLocation.getLocationName());
        
        locationService.delete(createdLocation);
        
        Location readLocation = locationService.read(createdLocation.getLocationId());
        
        assert readLocation == null;
    }

    @Test
    public void testGetAllLocations() {
        Address a1 = new Address();
        a1.setAddressLine1("Address Line 1");
        a1.setAddressLine2("Address Line 2");
        a1.setAddressCity("Address City 1");
        a1.setAddressState("KY");
        a1.setAddressZip("42301");
        a1.setAddressCountry("USA");
        
        Address createdAddress = addressService.create(a1);
        
        Location l1 = new Location();
        l1.setLocationName("Location 1");
        l1.setLocationDescription("Location Description 1");
        l1.setLocationAddressId(a1.getAddressId());
        l1.setLocationLatitude("Location 1 Latitude");
        l1.setLocationLongitude("Location 1 Longitude");
        
        Location createdLocation1 = locationService.create(l1);
        
        Location l2 = new Location();
        l2.setLocationName("Location 2");
        l2.setLocationDescription("Location Description 2");
        l2.setLocationAddressId(a1.getAddressId());
        l2.setLocationLatitude("Location 2 Latitude");
        l2.setLocationLongitude("Location 2 Longitude");
        
        Location createdLocation2 = locationService.create(l2);
        
        List<Location> locationList = locationService.getAllLocations();
        
        assert locationList.size() == 2;
        
        locationService.delete(l2);
        locationList = locationService.getAllLocations();
        
        assert locationList.size() == 1;
    }

    @Test
    public void testGetLocationsByPerson() {
        Person person1 = new Person();
        person1.setPersonType("Villian");
        person1.setPersonName("Bob");
        person1.setPersonDescription("SuperBob");
        person1.setPersonPower("Flight");
        
        Person createPerson1 = personService.create(person1);
        
        Address a1 = new Address();
        a1.setAddressLine1("Address Line 1");
        a1.setAddressLine2("Address Line 2");
        a1.setAddressCity("Address City 1");
        a1.setAddressState("KY");
        a1.setAddressZip("42301");
        a1.setAddressCountry("USA");
        
        Address createdAddress = addressService.create(a1);
        
        Location l1 = new Location();
        l1.setLocationName("Location 1");
        l1.setLocationDescription("Location Description 1");
        l1.setLocationAddressId(a1.getAddressId());
        l1.setLocationLatitude("Location 1 Latitude");
        l1.setLocationLongitude("Location 1 Longitude");
        
        Location createdLocation1 = locationService.create(l1);
        
        Location l2 = new Location();
        l2.setLocationName("Location 2");
        l2.setLocationDescription("Location Description 2");
        l2.setLocationAddressId(a1.getAddressId());
        l2.setLocationLatitude("Location 2 Latitude");
        l2.setLocationLongitude("Location 2 Longitude");
        
        Location createdLocation2 = locationService.create(l2);
        
        LocalDate date = LocalDate.parse("2018-10-02");
        List<Location> locationsByPerson = locationService.getLocationsByPerson(person1);
        assert locationsByPerson.size() == 0;
        
        PersonLocation pl1 = new PersonLocation();
        pl1.setPerson(person1);
        pl1.setLocation(l1);
        pl1.setDateSighted(date);
        PersonLocation createPersonLocation1 = personLocationService.create(pl1);
        locationsByPerson = locationService.getLocationsByPerson(person1);
        assert locationsByPerson.size() == 1;
        
        PersonLocation pl2 = new PersonLocation();
        pl2.setPerson(person1);
        pl2.setLocation(l2);
        pl2.setDateSighted(date);
        PersonLocation createPersonLocation2 = personLocationService.create(pl2);
        locationsByPerson = locationService.getLocationsByPerson(person1);
        assert locationsByPerson.size() == 2;
    }
}