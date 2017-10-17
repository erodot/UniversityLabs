package com.knu.it.db.table.column;

import java.rmi.RemoteException;

public class TableColumnFactory {
    public static ITableColumn Create(String name, Class<?> type) throws RemoteException{
        return new TableColumn(name, type);
    }
}
