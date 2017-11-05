package com.knu.it.iiop.server;

import com.knu.it.db.database.rmi.IRMIDatabase;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface IDatabaseAdapter extends Remote {

    List<String> getAvailableDatabases() throws RemoteException;
    IRMIDatabase getDatabaseWithName(String name) throws RemoteException;
    IRMIDatabase createDatabaseWithName(String name) throws RemoteException;
}
