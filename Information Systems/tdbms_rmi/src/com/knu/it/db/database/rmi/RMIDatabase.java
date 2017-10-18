package com.knu.it.db.database.rmi;

import com.knu.it.db.database.DatabaseFactory;
import com.knu.it.db.database.IDatabase;
import com.sun.xml.internal.bind.v2.model.core.ID;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RMIDatabase extends UnicastRemoteObject implements IRMIDatabase{
    public String root;

    public RMIDatabase(String root) throws RemoteException{
        this.root = root;
    }

    @Override
    public <T extends Object> T Invoke(String methodName, Object... params) throws RemoteException{
        IDatabase db = DatabaseFactory.FromRMI(this);

        for (Method method : IDatabase.class.getDeclaredMethods()) {
            if(method.getName().equals(methodName))
                try {
                    return (T)method.invoke(db, params);
                }
                catch(IllegalAccessException | InvocationTargetException ex){
                    throw new RemoteException(ex.getMessage());
                }
        }

        throw new RemoteException("No such method: " + methodName);
    }

    @Override
    public IDatabase getDatabase() throws RemoteException {
        return DatabaseFactory.FromRMI(this);
    }
}
