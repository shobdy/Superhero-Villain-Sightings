package com.sg.superherosightingsspringmvc.vendingmachine.dao;

public interface VendingMachineAuditDao {
        
    public void writeAuditEntry(String entry) throws VendingMachinePersistenceException;

}
