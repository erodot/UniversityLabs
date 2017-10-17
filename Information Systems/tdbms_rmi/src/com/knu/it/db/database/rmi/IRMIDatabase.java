package com.knu.it.db.database.rmi;

import com.knu.it.db.database.IDatabase;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IRMIDatabase extends Remote {
    <T extends Object> T Invoke(String methodName, Object... params) throws RemoteException;
    IDatabase getDatabase() throws RemoteException;
}
