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

public class PersonLocationServiceTest {
    
    @Inject
    PersonService personService;
    
    @Inject
    LocationService locationService;
    
    @Inject
    PersonLocationService personLocationService;
    
    @Inject
    AddressService addressService;

    public PersonLocationServiceTest() {
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
        Person person1 = new Person();
        person1.setPersonType("Villian");
        person1.setPersonName("Bob");
        person1.setPersonDescription("SuperBob");
        person1.setPersonPower("Flight");
        Person createPerson1 = personService.create(person1);
        
        Address addr = new Address();
        addr.setAddressLine1("666 Evil Lane");
        addr.setAddressCity("Owensboro");
        addr.setAddressState("KY");
        addr.setAddressZip("42303");
        addr.setAddressCountry("USA");
        Address createAddr = addressService.create(addr);
        
        Location l1 = new Location();
        l1.setLocationName("Evil Lair");
        l1.setLocationDescription("Bob's Evil House");
        l1.setLocationAddressId(createAddr.getAddressId());
        l1.setLocationLatitude("Location 1 Latitude");
        l1.setLocationLongitude("Location 1 Longitude");
        Location createdLocation1 = locationService.create(l1);
        
        LocalDate date = LocalDate.parse("2018-10-02");
        PersonLocation pl1 = new PersonLocation();
        pl1.setPerson(person1);
        pl1.setLocation(l1);
        pl1.setDateSighted(date);
        PersonLocation createPersonLocation1 = personLocationService.create(pl1);
        
        assert "Bob".equals(createPersonLocation1.getPerson().getPersonName());
        assert "Evil Lair".equals(createPersonLocation1.getLocation().getLocationName());
    }

    @Test
    public void testRead() {
        Person person1 = new Person();
        person1.setPersonType("Villian");
        person1.setPersonName("Bob");
        person1.setPersonDescription("SuperBob");
        person1.setPersonPower("Flight");
        Person createPerson1 = personService.create(person1);
        
        Address addr = new Address();
        addr.setAddressLine1("666 Evil Lane");
        addr.setAddressCity("Owensboro");
        addr.setAddressState("KY");
        addr.setAddressZip("42303");
        addr.setAddressCountry("USA");
        Address createAddr = addressService.create(addr);
        
        Location l1 = new Location();
        l1.setLocationName("Evil Lair");
        l1.setLocationDescription("Bob's Evil House");
        l1.setLocationAddressId(createAddr.getAddressId());
        l1.setLocationLatitude("Location 1 Latitude");
        l1.setLocationLongitude("Location 1 Longitude");
        Location createdLocation1 = locationService.create(l1);
        
        LocalDate date = LocalDate.parse("2018-10-02");
        PersonLocation pl1 = new PersonLocation();
        pl1.setPerson(person1);
        pl1.setLocation(l1);
        pl1.setDateSighted(date);
        PersonLocation createPersonLocation1 = personLocationService.create(pl1);
        PersonLocation readPersonLocation = personLocationService.read(createPersonLocation1.getPersonLocationId());
        
        assert "Bob".equals(readPersonLocation.getPerson().getPersonName());
        assert "Evil Lair".equals(readPersonLocation.getLocation().getLocationName());
    }

    @Test
    public void testUpdate() {
        Person person1 = new Person();
        person1.setPersonType("Villian");
        person1.setPersonName("Bob");
        person1.setPersonDescription("SuperBob");
        person1.setPersonPower("Flight");
        Person createPerson1 = personService.create(person1);
        
        Person person2 = new Person();
        person2.setPersonType("Villian");
        person2.setPersonName("Kate");
        person2.setPersonDescription("Katezilla");
        person2.setPersonPower("Fire breather");
        Person createPerson2 = personService.create(person2);
        
        Address addr = new Address();
        addr.setAddressLine1("666 Evil Lane");
        addr.setAddressCity("Owensboro");
        addr.setAddressState("KY");
        addr.setAddressZip("42303");
        addr.setAddressCountry("USA");
        Address createAddr = addressService.create(addr);
        
        Location l1 = new Location();
        l1.setLocationName("Evil Lair");
        l1.setLocationDescription("Bob's Evil House");
        l1.setLocationAddressId(createAddr.getAddressId());
        l1.setLocationLatitude("Location 1 Latitude");
        l1.setLocationLongitude("Location 1 Longitude");
        Location createdLocation1 = locationService.create(l1);
        
        LocalDate date = LocalDate.parse("2018-10-02");
        PersonLocation pl1 = new PersonLocation();
        pl1.setPerson(person1);
        pl1.setLocation(l1);
        pl1.setDateSighted(date);
        PersonLocation createPersonLocation1 = personLocationService.create(pl1);
        
        createPersonLocation1.setPerson(person2);
        
        personLocationService.update(createPersonLocation1);
        
        assert "Fire breather".equals(createPersonLocation1.getPerson().getPersonPower());
        assert "Kate".equals(createPersonLocation1.getPerson().getPersonName());
    }

    @Test
    public void testDelete() {
        Person person1 = new Person();
        person1.setPersonType("Villian");
        person1.setPersonName("Bob");
        person1.setPersonDescription("SuperBob");
        person1.setPersonPower("Flight");
        Person createPerson1 = personService.create(person1);
        
        Address addr = new Address();
        addr.setAddressLine1("666 Evil Lane");
        addr.setAddressCity("Owensboro");
        addr.setAddressState("KY");
        addr.setAddressZip("42303");
        addr.setAddressCountry("USA");
        Address createAddr = addressService.create(addr);
        
        Location l1 = new Location();
        l1.setLocationName("Evil Lair");
        l1.setLocationDescription("Bob's Evil House");
        l1.setLocationAddressId(createAddr.getAddressId());
        l1.setLocationLatitude("Location 1 Latitude");
        l1.setLocationLongitude("Location 1 Longitude");
        Location createdLocation1 = locationService.create(l1);
        
        LocalDate date = LocalDate.parse("2018-10-02");
        PersonLocation pl1 = new PersonLocation();
        pl1.setPerson(person1);
        pl1.setLocation(l1);
        pl1.setDateSighted(date);
        PersonLocation createPersonLocation1 = personLocationService.create(pl1);
        
        assert "Evil Lair".equals(createPersonLocation1.getLocation().getLocationName());
        assert createPersonLocation1 != null;
        
        personLocationService.delete(createPersonLocation1);
        
        PersonLocation readPersonLocation = personLocationService.read(createPersonLocation1.getPersonLocationId());
        
        assert readPersonLocation == null;
    }

    @Test
    public void testGetAllPersonLocations() {
        Person person1 = new Person();
        person1.setPersonType("Villian");
        person1.setPersonName("Bob");
        person1.setPersonDescription("SuperBob");
        person1.setPersonPower("Flight");
        Person createPerson1 = personService.create(person1);
        
        Person person2 = new Person();
        person2.setPersonType("Villian");
        person2.setPersonName("Kate");
        person2.setPersonDescription("Katezilla");
        person2.setPersonPower("Fire breather");
        Person createPerson2 = personService.create(person2);
        
        Address addr = new Address();
        addr.setAddressLine1("666 Evil Lane");
        addr.setAddressCity("Owensboro");
        addr.setAddressState("KY");
        addr.setAddressZip("42303");
        addr.setAddressCountry("USA");
        Address createAddr = addressService.create(addr);
        
        Location l1 = new Location();
        l1.setLocationName("Evil Lair");
        l1.setLocationDescription("Bob's Evil House");
        l1.setLocationAddressId(createAddr.getAddressId());
        l1.setLocationLatitude("Location 1 Latitude");
        l1.setLocationLongitude("Location 1 Longitude");
        Location createdLocation1 = locationService.create(l1);
        
        LocalDate date = LocalDate.parse("2018-10-02");
        PersonLocation pl1 = new PersonLocation();
        pl1.setPerson(person1);
        pl1.setLocation(l1);
        pl1.setDateSighted(date);
        PersonLocation createPersonLocation1 = personLocationService.create(pl1);
        
        PersonLocation pl2 = new PersonLocation();
        pl2.setPerson(person2);
        pl2.setLocation(l1);
        pl2.setDateSighted(date);
        PersonLocation createPersonLocation2 = personLocationService.create(pl2);
        
        List<PersonLocation> personLocationList = personLocationService.getAllPersonLocations();
        
        assert personLocationList.size() == 2;
        
        personLocationService.delete(createPersonLocation2);
        personLocationList = personLocationService.getAllPersonLocations();
        
        assert personLocationList.size() == 1;
    }

    @Test
    public void testGetAllSightingsByDate() {
        Person person1 = new Person();
        person1.setPersonType("Villian");
        person1.setPersonName("Bob");
        person1.setPersonDescription("SuperBob");
        person1.setPersonPower("Flight");
        Person createPerson1 = personService.create(person1);
        
        Person person2 = new Person();
        person2.setPersonType("Villian");
        person2.setPersonName("Kate");
        person2.setPersonDescription("Katezilla");
        person2.setPersonPower("Fire breather");
        Person createPerson2 = personService.create(person2);
        
        Address addr = new Address();
        addr.setAddressLine1("666 Evil Lane");
        addr.setAddressCity("Owensboro");
        addr.setAddressState("KY");
        addr.setAddressZip("42303");
        addr.setAddressCountry("USA");
        Address createAddr = addressService.create(addr);
        
        Location l1 = new Location();
        l1.setLocationName("Evil Lair");
        l1.setLocationDescription("Bob's Evil House");
        l1.setLocationAddressId(createAddr.getAddressId());
        l1.setLocationLatitude("Location 1 Latitude");
        l1.setLocationLongitude("Location 1 Longitude");
        Location createdLocation1 = locationService.create(l1);
        
        LocalDate date = LocalDate.parse("2018-10-02");
        PersonLocation pl1 = new PersonLocation();
        pl1.setPerson(person1);
        pl1.setLocation(l1);
        pl1.setDateSighted(date);
        PersonLocation createPersonLocation1 = personLocationService.create(pl1);
        
        PersonLocation pl2 = new PersonLocation();
        pl2.setPerson(person2);
        pl2.setLocation(l1);
        pl2.setDateSighted(date);
        PersonLocation createPersonLocation2 = personLocationService.create(pl2);
        
        List<PersonLocation> sightingsList = personLocationService.getAllSightingsByDate(date);
        
        assert sightingsList.size() == 2;
        
        LocalDate date2 = LocalDate.parse("2018-10-03");
        
        pl2.setDateSighted(date2);
        personLocationService.update(createPersonLocation2);
        sightingsList = personLocationService.getAllSightingsByDate(date);
        
        assert sightingsList.size() == 1;
    }
}