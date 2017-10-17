package com.knu.it.db.table;

import com.knu.it.db.table.column.ITableColumn;
import org.json.simple.JSONArray;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class TableFactory {

    public static ITable Create(String name, String root, String path, List<ITableColumn> columns, JSONArray fields) throws RemoteException{
        return new Table(name, root, path, columns, fields);
    }

    public static ITable CreateEmpty(String name, String root, String path, List<ITableColumn> columns)throws RemoteException{
        return new Table(name, root, path, columns, new JSONArray());
    }

    public static ITable CreateEmpty(String name, String root, String path)throws RemoteException{
        return new Table(name, root, path, new ArrayList<>(), new JSONArray());
    }
}
