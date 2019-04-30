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

public class PersonOrganizationDaoTest {
    
    @Inject
    PersonDao personDao;
    
    @Inject
    AddressDao addressDao;
    
    @Inject
    OrganizationDao organizationDao;
    
    @Inject
    PersonOrganizationDao personOrganizationDao;
    

    public PersonOrganizationDaoTest() {
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
        
        Address addr = new Address();
        addr.setAddressLine1("666 Evil Lane");
        addr.setAddressCity("Owensboro");
        addr.setAddressState("KY");
        addr.setAddressZip("42303");
        addr.setAddressCountry("USA");
        Address createAddr = addressDao.create(addr);
        
        Organization org = new Organization();
        org.setOrganizationName("Evil Org");
        org.setOrganizationDescription("Group of evildoers");
        org.setOrganizationAddressId(addr.getAddressId());
        org.setOrganizationContactName("Phil");
        org.setOrganizationContactPhone("123-555-6660");
        org.setOrganizationContactEmail("phil@evilOrg.org");
        Organization createOrg = organizationDao.create(org);
        
        PersonOrganization po1 = new PersonOrganization();
        po1.setPerson(person1);
        po1.setOrganization(createOrg);
        PersonOrganization createPo1 = personOrganizationDao.create(po1);
        
        assert "Bob".equals(createPo1.getPerson().getPersonName());
        assert "Evil Org".equals(createPo1.getOrganization().getOrganizationName());
    }

    @Test
    public void testRead() {
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
        org.setOrganizationName("Evil Org");
        org.setOrganizationDescription("Group of evildoers");
        org.setOrganizationAddressId(addr.getAddressId());
        org.setOrganizationContactName("Phil");
        org.setOrganizationContactPhone("123-555-6660");
        org.setOrganizationContactEmail("phil@evilOrg.org");
        Organization createOrg = organizationDao.create(org);
        
        PersonOrganization po1 = new PersonOrganization();
        po1.setPerson(person1);
        po1.setOrganization(createOrg);
        PersonOrganization createPo1 = personOrganizationDao.create(po1);
        PersonOrganization readPo1 = personOrganizationDao.read(createPo1.getPersonOrganizationId());
        
        assert "Bob".equals(readPo1.getPerson().getPersonName());
        assert "Evil Org".equals(readPo1.getOrganization().getOrganizationName());
    }

    @Test
    public void testUpdate() {
        Person person1 = new Person();
        person1.setPersonType("Villian");
        person1.setPersonName("Bob");
        person1.setPersonDescription("SuperBob");
        person1.setPersonPower("Flight");
        Person createPerson1 = personDao.create(person1);
        
        Person person2 = new Person();
        person2.setPersonType("Villian");
        person2.setPersonName("Jack");
        person2.setPersonDescription("Jumping Jack");
        person2.setPersonPower("Jumping");
        Person createPerson2 = personDao.create(person2);
        
        Address addr = new Address();
        addr.setAddressLine1("666 Evil Lane");
        addr.setAddressCity("Owensboro");
        addr.setAddressState("KY");
        addr.setAddressZip("42303");
        addr.setAddressCountry("USA");
        Address createAddr = addressDao.create(addr);
        
        Organization org = new Organization();
        org.setOrganizationName("Evil Org");
        org.setOrganizationDescription("Group of evildoers");
        org.setOrganizationAddressId(addr.getAddressId());
        org.setOrganizationContactName("Phil");
        org.setOrganizationContactPhone("123-555-6660");
        org.setOrganizationContactEmail("phil@evilOrg.org");
        Organization createOrg = organizationDao.create(org);
        
        PersonOrganization po1 = new PersonOrganization();
        po1.setPerson(person1);
        po1.setOrganization(createOrg);
        PersonOrganization createPo1 = personOrganizationDao.create(po1);
        
        assert "Bob".equals(po1.getPerson().getPersonName());
        
        createPo1.setPerson(person2);
        personOrganizationDao.update(createPo1);
        
        assert "Jack".equals(po1.getPerson().getPersonName());
        assert "Evil Org".equals(po1.getOrganization().getOrganizationName());
        
    }

    @Test
    public void testDelete() {
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
        org.setOrganizationName("Evil Org");
        org.setOrganizationDescription("Group of evildoers");
        org.setOrganizationAddressId(addr.getAddressId());
        org.setOrganizationContactName("Phil");
        org.setOrganizationContactPhone("123-555-6660");
        org.setOrganizationContactEmail("phil@evilOrg.org");
        Organization createOrg = organizationDao.create(org);
        
        PersonOrganization po1 = new PersonOrganization();
        po1.setPerson(person1);
        po1.setOrganization(createOrg);
        PersonOrganization createPo1 = personOrganizationDao.create(po1);
        
        assert "Phil".equals(po1.getOrganization().getOrganizationContactName());
        assert createPo1 != null;
        
        personOrganizationDao.delete(createPo1);
        
        PersonOrganization readPo1 = personOrganizationDao.read(createPo1.getPersonOrganizationId());
        
        assert readPo1 == null;
    }

    @Test
    public void testGetAllPersonOrganizations() {
        Person person1 = new Person();
        person1.setPersonType("Villian");
        person1.setPersonName("Bob");
        person1.setPersonDescription("SuperBob");
        person1.setPersonPower("Flight");
        Person createPerson1 = personDao.create(person1);
        
        Person person2 = new Person();
        person2.setPersonType("Villian");
        person2.setPersonName("Jack");
        person2.setPersonDescription("Jumping Jack");
        person2.setPersonPower("Jumping");
        Person createPerson2 = personDao.create(person2);
        
        Address addr = new Address();
        addr.setAddressLine1("666 Evil Lane");
        addr.setAddressCity("Owensboro");
        addr.setAddressState("KY");
        addr.setAddressZip("42303");
        addr.setAddressCountry("USA");
        Address createAddr = addressDao.create(addr);
        
        Organization org = new Organization();
        org.setOrganizationName("Evil Org");
        org.setOrganizationDescription("Group of evildoers");
        org.setOrganizationAddressId(addr.getAddressId());
        org.setOrganizationContactName("Phil");
        org.setOrganizationContactPhone("123-555-6660");
        org.setOrganizationContactEmail("phil@evilOrg.org");
        Organization createOrg = organizationDao.create(org);
        
        PersonOrganization po1 = new PersonOrganization();
        po1.setPerson(person1);
        po1.setOrganization(createOrg);
        PersonOrganization createPo1 = personOrganizationDao.create(po1);
        
        PersonOrganization po2 = new PersonOrganization();
        po2.setPerson(person1);
        po2.setOrganization(createOrg);
        PersonOrganization createPo2 = personOrganizationDao.create(po2);
        
        List<PersonOrganization> poList = personOrganizationDao.getAllPersonOrganizations();
        
        assert poList.size() == 2;
        
        personOrganizationDao.delete(createPo2);
        poList = personOrganizationDao.getAllPersonOrganizations();
        
        assert poList.size() == 1;
    }
}