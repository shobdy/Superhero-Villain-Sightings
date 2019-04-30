package com.sg.superherosightingsspringmvc.dao;

import com.sg.superherosightingsspringmvc.model.Address;
import com.sg.superherosightingsspringmvc.model.Location;
import com.sg.superherosightingsspringmvc.model.Organization;
import com.sg.superherosightingsspringmvc.model.Person;
import com.sg.superherosightingsspringmvc.model.PersonLocation;
import com.sg.superherosightingsspringmvc.model.PersonOrganization;
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

public class PersonDaoTest {
    
    @Inject
    PersonDao personDao;
    
    @Inject
    LocationDao locationDao;
    
    @Inject
    AddressDao addressDao;
    
    @Inject
    PersonLocationDao personLocationDao;
    
    @Inject
    OrganizationDao organizationDao;
    
    @Inject
    PersonOrganizationDao personOrganizationDao;

    public PersonDaoTest() {
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
        Person createPerson1 = personDao.create(person1);
        
        assert "Villian".equals(createPerson1.getPersonType());
        assert "SuperBob".equals(createPerson1.getPersonDescription());
        
    }

    @Test
    public void testRead() {
        Person person1 = new Person();
        person1.setPersonType("Villian");
        person1.setPersonName("Bob");
        person1.setPersonDescription("SuperBob");
        person1.setPersonPower("Flight");
        Person createPerson1 = personDao.create(person1);
        
        Person readPerson = personDao.read(createPerson1.getPersonId());
        
        assert "Bob".equals(readPerson.getPersonName());
        assert "Flight".equals(readPerson.getPersonPower());
        
    }

    @Test
    public void testUpdate() {
        Person person1 = new Person();
        person1.setPersonType("Villian");
        person1.setPersonName("Bob");
        person1.setPersonDescription("SuperBob");
        person1.setPersonPower("Flight");
        Person createPerson1 = personDao.create(person1);
        
        createPerson1.setPersonName("Jack");
        createPerson1.setPersonDescription("Captain Jack!");
        
        personDao.update(createPerson1);
        
        assert "Jack".equals(createPerson1.getPersonName());
        assert "Captain Jack!".equals(createPerson1.getPersonDescription());
    }

    @Test
    public void testDelete() {
        Person person1 = new Person();
        person1.setPersonType("Villian");
        person1.setPersonName("Bob");
        person1.setPersonDescription("SuperBob");
        person1.setPersonPower("Flight");
        Person createPerson1 = personDao.create(person1);
        
        assert "Bob".equals(createPerson1.getPersonName());
        assert createPerson1 != null;
        
        personDao.delete(createPerson1);
        
        Person readPerson = personDao.read(createPerson1.getPersonId());
        
        assert readPerson == null;
        
        
    }

    @Test
    public void testGetAllPersons() {
        Person person1 = new Person();
        person1.setPersonType("Villian");
        person1.setPersonName("Bob");
        person1.setPersonDescription("SuperBob");
        person1.setPersonPower("Flight");
        person1.setPersonActive(true);
        Person createPerson1 = personDao.create(person1);
        
        Person person2 = new Person();
        person2.setPersonType("Hero");
        person2.setPersonName("John");
        person2.setPersonDescription("ElastiJohn");
        person2.setPersonPower("Stretches");
        person2.setPersonActive(true);
        Person createPerson2 = personDao.create(person2);
        
        List<Person> personList = personDao.getAllPersons();
        
        assert personList.size() == 2;
        
        personDao.delete(person2);
        personList = personDao.getAllPersons();
        
        assert personList.size() == 1;
    }

    @Test
    public void testGetPersonsByLocation() {
        Person person1 = new Person();
        person1.setPersonType("Villian");
        person1.setPersonName("Bob");
        person1.setPersonDescription("SuperBob");
        person1.setPersonPower("Flight");
        Person createPerson1 = personDao.create(person1);
        
        Person person2 = new Person();
        person2.setPersonType("Hero");
        person2.setPersonName("Peter");
        person2.setPersonDescription("Spiderman");
        person2.setPersonPower("Wall crawling");
        Person createPerson2 = personDao.create(person2);
        
        Address a1 = new Address();
        a1.setAddressLine1("Address Line 1");
        a1.setAddressLine2("Address Line 2");
        a1.setAddressCity("Address City 1");
        a1.setAddressState("KY");
        a1.setAddressZip("42301");
        a1.setAddressCountry("USA");
        
        Address createdAddress = addressDao.create(a1);
        
        Location l1 = new Location();
        l1.setLocationName("Location 1");
        l1.setLocationDescription("Location Description 1");
        l1.setLocationAddressId(a1.getAddressId());
        l1.setLocationLatitude("Location 1 Latitude");
        l1.setLocationLongitude("Location 1 Longitude");
        
        Location createdLocation1 = locationDao.create(l1);
        
        Location l2 = new Location();
        l2.setLocationName("Location 2");
        l2.setLocationDescription("Location Description 2");
        l2.setLocationAddressId(a1.getAddressId());
        l2.setLocationLatitude("Location 2 Latitude");
        l2.setLocationLongitude("Location 2 Longitude");
        
        Location createdLocation2 = locationDao.create(l2);
        
        LocalDate date = LocalDate.parse("2018-10-02");
        List<Person> personsByLocation = personDao.getPersonsByLocation(l1);
        assert personsByLocation.size() == 0;
        
        PersonLocation pl1 = new PersonLocation();
        pl1.setPerson(person1);
        pl1.setLocation(l1);
        pl1.setDateSighted(date);
        PersonLocation createPersonLocation1 = personLocationDao.create(pl1);
        personsByLocation = personDao.getPersonsByLocation(l1);
        assert personsByLocation.size() == 1;
        
        PersonLocation pl2 = new PersonLocation();
        pl2.setPerson(person2);
        pl2.setLocation(l1);
        pl2.setDateSighted(date);
        PersonLocation createPersonLocation2 = personLocationDao.create(pl2);
        personsByLocation = personDao.getPersonsByLocation(l1);
        assert personsByLocation.size() == 2;
    }

    @Test
    public void testGetAllPersonsByOrganization() {
        Person person1 = new Person();
        person1.setPersonType("Villian");
        person1.setPersonName("Bob");
        person1.setPersonDescription("SuperBob");
        person1.setPersonPower("Flight");
        Person createPerson1 = personDao.create(person1);
        
        Person person2 = new Person();
        person2.setPersonType("Hero");
        person2.setPersonName("Peter");
        person2.setPersonDescription("Spiderman");
        person2.setPersonPower("Wall crawling");
        Person createPerson2 = personDao.create(person2);
        
        Address addr = new Address();
        addr.setAddressLine1("666 Evil Lane");
        addr.setAddressCity("Owensboro");
        addr.setAddressState("KY");
        addr.setAddressZip("42303");
        addr.setAddressCountry("USA");
        Address createAddr = addressDao.create(addr);
        
        Organization org = new Organization();
        org.setOrganizationName("Lair");
        org.setOrganizationDescription("Evil Lair for Bob.");
        org.setOrganizationAddressId(createAddr.getAddressId());
        org.setOrganizationContactName("Bob");
        org.setOrganizationContactPhone("270-555-1234");
        org.setOrganizationContactEmail("bob@evilLair.com");
        Organization createOrg = organizationDao.create(org);
        
        List<Person> personsByOrgs = personDao.getAllPersonsByOrganization(createOrg);
        assert personsByOrgs.size() == 0;
        
        PersonOrganization po1 = new PersonOrganization();
        po1.setPerson(person1);
        po1.setOrganization(createOrg);
        PersonOrganization createpo1 = personOrganizationDao.create(po1);
        personsByOrgs = personDao.getAllPersonsByOrganization(createOrg);
        assert personsByOrgs.size() == 1;
        
        PersonOrganization po2 = new PersonOrganization();
        po2.setPerson(person2);
        po2.setOrganization(createOrg);
        PersonOrganization createpo2 = personOrganizationDao.create(po2);
        personsByOrgs = personDao.getAllPersonsByOrganization(createOrg);
        assert personsByOrgs.size() == 2;
    }
}