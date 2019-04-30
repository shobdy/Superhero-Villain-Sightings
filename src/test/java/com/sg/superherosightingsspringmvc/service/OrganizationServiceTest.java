package com.sg.superherosightingsspringmvc.service;

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

public class OrganizationServiceTest {
    
    @Inject
    OrganizationService organizationService;
    
    @Inject 
    AddressService addressService;
    
    @Inject
    PersonService personService;
    
    @Inject
    PersonOrganizationService personOrganizationService;

    public OrganizationServiceTest() {
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
        addressService.create(addr);
        Address readAddr = addressService.read(addr.getAddressId());
        
        Organization org = new Organization();
        org.setOrganizationName("Lair");
        org.setOrganizationDescription("Evil Lair for Bob.");
        org.setOrganizationAddressId(readAddr.getAddressId());
        org.setOrganizationContactName("Bob");
        org.setOrganizationContactPhone("270-555-1234");
        org.setOrganizationContactEmail("bob@evilLair.com");
        
        Organization createOrg = organizationService.create(org);
        
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
        addressService.create(addr);
        Address readAddr = addressService.read(addr.getAddressId());
        
        Organization org = new Organization();
        org.setOrganizationName("Lair");
        org.setOrganizationDescription("Evil Lair for Bob.");
        org.setOrganizationAddressId(readAddr.getAddressId());
        org.setOrganizationContactName("Bob");
        org.setOrganizationContactPhone("270-555-1234");
        org.setOrganizationContactEmail("bob@evilLair.com");
        
        Organization createOrg = organizationService.create(org);
        
        Organization readOrg = organizationService.read(createOrg.getOrganizationId());
        
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
        addressService.create(addr);
        Address readAddr = addressService.read(addr.getAddressId());
        
        Organization org = new Organization();
        org.setOrganizationName("Lair");
        org.setOrganizationDescription("Evil Lair for Bob.");
        org.setOrganizationAddressId(readAddr.getAddressId());
        org.setOrganizationContactName("Bob");
        org.setOrganizationContactPhone("270-555-1234");
        org.setOrganizationContactEmail("bob@evilLair.com");
        
        Organization createOrg = organizationService.create(org);
        
        createOrg.setOrganizationContactName("Jeff");
        createOrg.setOrganizationDescription("Evil Lair II");
        
        organizationService.update(createOrg);
        
        Organization readOrg = organizationService.read(createOrg.getOrganizationId());
        
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
        addressService.create(addr);
        Address readAddr = addressService.read(addr.getAddressId());
        
        Organization org = new Organization();
        org.setOrganizationName("Lair");
        org.setOrganizationDescription("Evil Lair for Bob.");
        org.setOrganizationAddressId(readAddr.getAddressId());
        org.setOrganizationContactName("Bob");
        org.setOrganizationContactPhone("270-555-1234");
        org.setOrganizationContactEmail("bob@evilLair.com");
        Organization createOrg = organizationService.create(org);
        
        assert "Bob".equals(createOrg.getOrganizationContactName());
        assert createOrg != null;
        
        organizationService.delete(createOrg);
        
        Organization readOrg = organizationService.read(createOrg.getOrganizationId());
        
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
        addressService.create(addr);
        Address readAddr = addressService.read(addr.getAddressId());
        
        Organization org = new Organization();
        org.setOrganizationName("Lair");
        org.setOrganizationDescription("Evil Lair for Bob.");
        org.setOrganizationAddressId(readAddr.getAddressId());
        org.setOrganizationContactName("Bob");
        org.setOrganizationContactPhone("270-555-1234");
        org.setOrganizationContactEmail("bob@evilLair.com");
        Organization createOrg = organizationService.create(org);
        
        Organization org2 = new Organization();
        org2.setOrganizationName("Super Lair");
        org2.setOrganizationDescription("Evil Lair for Andrew.");
        org2.setOrganizationAddressId(readAddr.getAddressId());
        org2.setOrganizationContactName("Andrew");
        org2.setOrganizationContactPhone("270-555-6660");
        org2.setOrganizationContactEmail("andy@superEvilLair.org");
        Organization createOrg2 = organizationService.create(org2);
        
        List<Organization> orgList = organizationService.getAllOrganizations();
        
        assert orgList.size() == 2;
        
        organizationService.delete(org2);
        orgList = organizationService.getAllOrganizations();
        
        assert orgList.size() == 1;
    }

    @Test
    public void testGetOrganizationsByPersons() {
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
        
        Organization org = new Organization();
        org.setOrganizationName("Lair");
        org.setOrganizationDescription("Evil Lair for Bob.");
        org.setOrganizationAddressId(createAddr.getAddressId());
        org.setOrganizationContactName("Bob");
        org.setOrganizationContactPhone("270-555-1234");
        org.setOrganizationContactEmail("bob@evilLair.com");
        Organization createOrg = organizationService.create(org);
        
        Organization org2 = new Organization();
        org2.setOrganizationName("Super Lair");
        org2.setOrganizationDescription("Evil Lair for Andrew.");
        org2.setOrganizationAddressId(createAddr.getAddressId());
        org2.setOrganizationContactName("Andrew");
        org2.setOrganizationContactPhone("270-555-6660");
        org2.setOrganizationContactEmail("andy@superEvilLair.org");
        Organization createOrg2 = organizationService.create(org2);
        
        List<Organization> orgByPerson = organizationService.getOrganizationsByPerson(person1);
        assert orgByPerson.size() == 0;
        
        PersonOrganization po1 = new PersonOrganization();
        po1.setPerson(person1);
        po1.setOrganization(org);
        PersonOrganization createpo1 = personOrganizationService.create(po1);
        orgByPerson = organizationService.getOrganizationsByPerson(person1);
        assert orgByPerson.size() == 1;
        
        PersonOrganization po2 = new PersonOrganization();
        po2.setPerson(person1);
        po2.setOrganization(org);
        PersonOrganization createpo2 = personOrganizationService.create(po2);
        orgByPerson = organizationService.getOrganizationsByPerson(person1);
        assert orgByPerson.size() == 2;
    }
}