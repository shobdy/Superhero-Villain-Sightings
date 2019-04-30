package com.sg.superherosightingsspringmvc.dao;

import com.sg.superherosightingsspringmvc.model.Address;
import com.sg.superherosightingsspringmvc.model.Organization;
import com.sg.superherosightingsspringmvc.model.Person;
import com.sg.superherosightingsspringmvc.model.PersonOrganization;
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

public class OrganizationDaoTest {
    
    @Inject
    OrganizationDao organizationDao;
    
    @Inject 
    AddressDao addressDao;
    
    @Inject
    PersonDao personDao;
    
    @Inject
    PersonOrganizationDao personOrganizationDao;

    public OrganizationDaoTest() {
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
        Address addr = new Address();
        addr.setAddressLine1("666 Evil Lane");
        addr.setAddressCity("Owensboro");
        addr.setAddressState("KY");
        addr.setAddressZip("42303");
        addr.setAddressCountry("USA");
        addressDao.create(addr);
        Address readAddr = addressDao.read(addr.getAddressId());
        
        Organization org = new Organization();
        org.setOrganizationName("Lair");
        org.setOrganizationDescription("Evil Lair for Bob.");
        org.setOrganizationAddressId(readAddr.getAddressId());
        org.setOrganizationContactName("Bob");
        org.setOrganizationContactPhone("270-555-1234");
        org.setOrganizationContactEmail("bob@evilLair.com");
        
        Organization createOrg = organizationDao.create(org);
        
        assert "Lair".equals(createOrg.getOrganizationName());
        assert "Bob".equals(createOrg.getOrganizationContactName());
        
    }

    @Test
    public void testRead() {
        Address addr = new Address();
        addr.setAddressLine1("666 Evil Lane");
        addr.setAddressCity("Owensboro");
        addr.setAddressState("KY");
        addr.setAddressZip("42303");
        addr.setAddressCountry("USA");
        addressDao.create(addr);
        Address readAddr = addressDao.read(addr.getAddressId());
        
        Organization org = new Organization();
        org.setOrganizationName("Lair");
        org.setOrganizationDescription("Evil Lair for Bob.");
        org.setOrganizationAddressId(readAddr.getAddressId());
        org.setOrganizationContactName("Bob");
        org.setOrganizationContactPhone("270-555-1234");
        org.setOrganizationContactEmail("bob@evilLair.com");
        
        Organization createOrg = organizationDao.create(org);
        
        Organization readOrg = organizationDao.read(createOrg.getOrganizationId());
        
        assert "Lair".equals(readOrg.getOrganizationName());
        assert "Bob".equals(readOrg.getOrganizationContactName());
    }

    @Test
    public void testUpdate() {
        Address addr = new Address();
        addr.setAddressLine1("666 Evil Lane");
        addr.setAddressCity("Owensboro");
        addr.setAddressState("KY");
        addr.setAddressZip("42303");
        addr.setAddressCountry("USA");
        addressDao.create(addr);
        Address readAddr = addressDao.read(addr.getAddressId());
        
        Organization org = new Organization();
        org.setOrganizationName("Lair");
        org.setOrganizationDescription("Evil Lair for Bob.");
        org.setOrganizationAddressId(readAddr.getAddressId());
        org.setOrganizationContactName("Bob");
        org.setOrganizationContactPhone("270-555-1234");
        org.setOrganizationContactEmail("bob@evilLair.com");
        
        Organization createOrg = organizationDao.create(org);
        
        createOrg.setOrganizationContactName("Jeff");
        createOrg.setOrganizationDescription("Evil Lair II");
        
        organizationDao.update(createOrg);
        
        Organization readOrg = organizationDao.read(createOrg.getOrganizationId());
        
        assert "Lair".equals(readOrg.getOrganizationName());
        assert "Jeff".equals(readOrg.getOrganizationContactName());
    }

    @Test
    public void testDelete() {
        Address addr = new Address();
        addr.setAddressLine1("666 Evil Lane");
        addr.setAddressCity("Owensboro");
        addr.setAddressState("KY");
        addr.setAddressZip("42303");
        addr.setAddressCountry("USA");
        addressDao.create(addr);
        Address readAddr = addressDao.read(addr.getAddressId());
        
        Organization org = new Organization();
        org.setOrganizationName("Lair");
        org.setOrganizationDescription("Evil Lair for Bob.");
        org.setOrganizationAddressId(readAddr.getAddressId());
        org.setOrganizationContactName("Bob");
        org.setOrganizationContactPhone("270-555-1234");
        org.setOrganizationContactEmail("bob@evilLair.com");
        Organization createOrg = organizationDao.create(org);
        
        assert "Bob".equals(createOrg.getOrganizationContactName());
        assert createOrg != null;
        
        organizationDao.delete(createOrg);
        
        Organization readOrg = organizationDao.read(createOrg.getOrganizationId());
        
        assert readOrg == null;
    }

    @Test
    public void testGetAllOrganizations() {
        Address addr = new Address();
        addr.setAddressLine1("666 Evil Lane");
        addr.setAddressCity("Owensboro");
        addr.setAddressState("KY");
        addr.setAddressZip("42303");
        addr.setAddressCountry("USA");
        addressDao.create(addr);
        Address readAddr = addressDao.read(addr.getAddressId());
        
        Organization org = new Organization();
        org.setOrganizationName("Lair");
        org.setOrganizationDescription("Evil Lair for Bob.");
        org.setOrganizationAddressId(readAddr.getAddressId());
        org.setOrganizationContactName("Bob");
        org.setOrganizationContactPhone("270-555-1234");
        org.setOrganizationContactEmail("bob@evilLair.com");
        Organization createOrg = organizationDao.create(org);
        
        Organization org2 = new Organization();
        org2.setOrganizationName("Super Lair");
        org2.setOrganizationDescription("Evil Lair for Andrew.");
        org2.setOrganizationAddressId(readAddr.getAddressId());
        org2.setOrganizationContactName("Andrew");
        org2.setOrganizationContactPhone("270-555-6660");
        org2.setOrganizationContactEmail("andy@superEvilLair.org");
        Organization createOrg2 = organizationDao.create(org2);
        
        List<Organization> orgList = organizationDao.getAllOrganizations();
        
        assert orgList.size() == 2;
        
        organizationDao.delete(org2);
        orgList = organizationDao.getAllOrganizations();
        
        assert orgList.size() == 1;
    }

    @Test
    public void testGetOrganizationsByPersons() {
        Person person1 = new Person();
        person1.setPersonType("Villian");
        person1.setPersonName("Bob");
        person1.setPersonDescription("SuperBob");
        person1.setPersonPower("Flight");
        Person createPerson1 = personDao.create(person1);
        
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
        
        Organization org2 = new Organization();
        org2.setOrganizationName("Super Lair");
        org2.setOrganizationDescription("Evil Lair for Andrew.");
        org2.setOrganizationAddressId(createAddr.getAddressId());
        org2.setOrganizationContactName("Andrew");
        org2.setOrganizationContactPhone("270-555-6660");
        org2.setOrganizationContactEmail("andy@superEvilLair.org");
        Organization createOrg2 = organizationDao.create(org2);
        
        List<Organization> orgByPerson = organizationDao.getOrganizationsByPerson(person1);
        assert orgByPerson.size() == 0;
        
        PersonOrganization po1 = new PersonOrganization();
        po1.setPerson(person1);
        po1.setOrganization(org);
        PersonOrganization createpo1 = personOrganizationDao.create(po1);
        orgByPerson = organizationDao.getOrganizationsByPerson(person1);
        assert orgByPerson.size() == 1;
        
        PersonOrganization po2 = new PersonOrganization();
        po2.setPerson(person1);
        po2.setOrganization(org);
        PersonOrganization createpo2 = personOrganizationDao.create(po2);
        orgByPerson = organizationDao.getOrganizationsByPerson(person1);
        assert orgByPerson.size() == 2;
    }

}