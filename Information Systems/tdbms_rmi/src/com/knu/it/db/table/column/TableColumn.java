package com.knu.it.db.table.column;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

class TableColumn extends UnicastRemoteObject implements ITableColumn {
    protected String name;
    protected Class<?> type;

    TableColumn(String name, Class<?> type) throws RemoteException{
        this.name = name;
        this.type = type;
    }

    @Override
    public String getName() throws RemoteException{
        return name;
    }

    @Override
    public Class<?> getType()throws RemoteException{
        return type;
    }
}
