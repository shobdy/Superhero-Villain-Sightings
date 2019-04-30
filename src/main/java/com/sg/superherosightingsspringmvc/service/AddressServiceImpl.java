package com.sg.superherosightingsspringmvc.service;

import com.sg.superherosightingsspringmvc.dao.AddressDao;
import com.sg.superherosightingsspringmvc.model.Address;
import java.util.List;
import javax.inject.Inject;

public class AddressServiceImpl implements AddressService{

    AddressDao addressDao;
    
    @Inject
    public AddressServiceImpl(AddressDao addressDao){
        this.addressDao = addressDao;
    }
    
    @Override
    public Address create(Address address) {
        return addressDao.create(address);
    }

    @Override
    public Address read(int addressId) {
        return addressDao.read(addressId);
    }

    @Override
    public void update(Address address) {
        addressDao.update(address);
    }

    @Override
    public void delete(Address address) {
        addressDao.delete(address);
    }

    @Override
    public List<Address> getAllAddresses() {
        return addressDao.getAllAddresses();
    }

}
