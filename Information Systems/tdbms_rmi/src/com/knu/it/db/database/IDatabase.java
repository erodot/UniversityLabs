package com.knu.it.db.database;

import com.knu.it.db.table.ITable;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface IDatabase extends Remote{
    String getName() throws RemoteException;
    String getRoot() throws RemoteException;
    List<ITable> getTables() throws RemoteException;

    void save()  throws RemoteException;
    ITable getTableByName(String tableName) throws RemoteException;
    void addTable(ITable table) throws RemoteException;
}
