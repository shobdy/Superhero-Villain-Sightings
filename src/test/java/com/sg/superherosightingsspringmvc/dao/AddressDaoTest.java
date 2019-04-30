package com.sg.superherosightingsspringmvc.dao;

import com.sg.superherosightingsspringmvc.model.Address;
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

public class AddressDaoTest {
    
    @Inject
    AddressDao addressDao;

    public AddressDaoTest() {
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
        Address address = new Address();
        address.setAddressLine1("123 Main St.");
        address.setAddressCity("Owensboro");
        address.setAddressState("KY");
        address.setAddressZip("42301");
        address.setAddressCountry("USA");
        
        Address createAddress = addressDao.create(address);
        
        assert "Owensboro".equals(createAddress.getAddressCity());
        assert "USA".equals(createAddress.getAddressCountry());
        
    }

    @Test
    public void testRead() {
        Address address = new Address();
        address.setAddressLine1("123 Main St.");
        address.setAddressCity("Owensboro");
        address.setAddressState("KY");
        address.setAddressZip("42301");
        address.setAddressCountry("USA");
        
        Address createAddress = addressDao.create(address);
        Address readAddress = addressDao.read(createAddress.getAddressId());
        
        assert "123 Main St.".equals(readAddress.getAddressLine1());
        assert "42301".equals(readAddress.getAddressZip());
    }

    @Test
    public void testUpdate() {
        Address address = new Address();
        address.setAddressLine1("123 Main St.");
        address.setAddressCity("Owensboro");
        address.setAddressState("KY");
        address.setAddressZip("42301");
        address.setAddressCountry("USA");
        
        Address createAddress = addressDao.create(address);
        Address readAddress = addressDao.read(createAddress.getAddressId());
        
        createAddress.setAddressCity("Beaver Dam");
        createAddress.setAddressZip("42320");
        
        assert "42301".equals(readAddress.getAddressZip());
        
        addressDao.update(createAddress);
        readAddress = addressDao.read(createAddress.getAddressId());
        
        assert "42320".equals(readAddress.getAddressZip());
    }

    @Test
    public void testDelete() {
        Address address = new Address();
        address.setAddressLine1("123 Main St.");
        address.setAddressCity("Owensboro");
        address.setAddressState("KY");
        address.setAddressZip("42301");
        address.setAddressCountry("USA");
        
        Address createAddress = addressDao.create(address);
        
        Address readAddress = addressDao.read(createAddress.getAddressId());
        
        assert "KY".equals(readAddress.getAddressState());
        
        addressDao.delete(createAddress);
        
        readAddress = addressDao.read(createAddress.getAddressId());
        
        assert readAddress == null;
        
    }

    @Test
    public void testGetAllAddresses() {
        Address address = new Address();
        address.setAddressLine1("123 Main St.");
        address.setAddressCity("Owensboro");
        address.setAddressState("KY");
        address.setAddressZip("42301");
        address.setAddressCountry("USA");
        Address createAddress = addressDao.create(address);
        
        Address address2 = new Address();
        address2.setAddressLine1("333 Diamond Ave.");
        address2.setAddressCity("Evansville");
        address2.setAddressState("IN");
        address2.setAddressZip("34250");
        address2.setAddressCountry("USA");
        Address createAddress1 = addressDao.create(address2);
        
        List<Address> addressList = addressDao.getAllAddresses();
        
        assert addressList.size() == 2;
        
        addressDao.delete(address);
        addressList = addressDao.getAllAddresses();
        
        assert addressList.size() == 1;
        
    }

}