package com.knu.it.db.table.column;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ITableColumn extends Remote {
    String getName() throws RemoteException;
    Class<?> getType() throws RemoteException;
}
