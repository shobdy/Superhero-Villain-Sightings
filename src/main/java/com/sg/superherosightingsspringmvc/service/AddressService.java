package com.sg.superherosightingsspringmvc.service;

import com.sg.superherosightingsspringmvc.model.Address;
import java.util.List;

public interface AddressService {
    public Address create(Address address);
    public Address read(int addressId);
    public void update(Address address);
    public void delete(Address address);
    public List<Address> getAllAddresses();
}
