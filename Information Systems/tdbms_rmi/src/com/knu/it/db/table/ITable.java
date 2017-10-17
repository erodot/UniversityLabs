package com.knu.it.db.table;

import com.knu.it.db.table.column.ITableColumn;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ITable extends Remote{
    String getName() throws RemoteException;
    String getPath() throws RemoteException;
    String getRoot() throws RemoteException;
    JSONArray getFields() throws RemoteException;
    List<ITableColumn> getColumns() throws RemoteException;
    Table project(List<String> columnNames) throws RemoteException;
    void save() throws RemoteException;
    void update(int row_number, ITableColumn column, Object value) throws RemoteException;
    void loadFromFile() throws RemoteException;
    void validate() throws RemoteException;
}
