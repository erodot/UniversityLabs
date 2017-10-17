package com.knu.it.db.table;

import com.knu.it.Constants;
import com.knu.it.db.table.column.ITableColumn;
import com.knu.it.db.table.column.TableColumnFactory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

class Table extends UnicastRemoteObject implements ITable {
    protected String name;
    protected String path;
    protected String root;
    protected List<ITableColumn> columns;
    protected JSONArray fields;

    /* PUBLIC METHODS */
    Table(String name, String root, String path, List<ITableColumn> columns, JSONArray fields) throws RemoteException {
        this.name = name;
        this.root = root;
        this.path = path;
        this.columns = columns;
        this.fields = fields;
    }

    @Override
    public String getName() throws RemoteException {
        return name;
    }

    @Override
    public String getPath() throws RemoteException {
        return path;
    }

    @Override
    public String getRoot() throws RemoteException {
        return root;
    }

    @Override
    public JSONArray getFields() throws RemoteException {
        return fields;
    }

    @Override
    public List<ITableColumn> getColumns() throws RemoteException {
        return columns;
    }

    @Override
    public Table project(List<String> columnNames) throws RemoteException{
        String newTableName = this.name + " projected";

        List<ITableColumn> newTableColumns = new ArrayList<>();
        columnNames.forEach(columnName -> {
            this.columns.forEach(tableColumn -> {
                try {
                    if(tableColumn.getName().equals(columnName)){
                        newTableColumns.add(tableColumn);
                    }
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            });
        });

        JSONArray newTableFields = new JSONArray();
        for(Object orow: this.fields){
            JSONObject jrow = (JSONObject)orow;
            JSONObject newjrow = new JSONObject();
            for(ITableColumn tc: newTableColumns){
                newjrow.put(tc.getName(), jrow.get(tc.getName()));
            }
            newTableFields.add(newjrow);
        }

        return new Table(newTableName, null, null, newTableColumns, newTableFields);
    }

    @Override
    public void save() throws RemoteException{
        JSONObject tableInfo = new JSONObject();
        tableInfo.put("fields", this.fields);

        JSONArray columnsInfo = new JSONArray();
        for(ITableColumn tc: this.columns){
            JSONObject j = new JSONObject();
            j.put("name", tc.getName());
            j.put("type", Constants.GetClassName(tc.getType()));
            columnsInfo.add(j);
        }

        tableInfo.put("columns", columnsInfo);

        try (FileWriter file = new FileWriter(this.root + this.path)) {

            file.write(tableInfo.toJSONString());
            file.flush();

        } catch (IOException e) {
            throw new RemoteException(e.getMessage());
        }
    }

    @Override
    public void update(int row_number, ITableColumn column, Object value) throws RemoteException{
        JSONObject row = (JSONObject)this.fields.get(row_number);
        row.put(column.getName(), value);
    }

    /* PACKAGE-PRIVATE METHODS */

    public void loadFromFile() throws RemoteException{
        try {
            // reading table data
            JSONObject jtable = (JSONObject) Constants.jsonParser.parse(new FileReader(root + path));

            // reading table columns
            JSONArray jheaders = (JSONArray) jtable.get("columns");
            for (Object oheader : jheaders) {
                JSONObject jheader = (JSONObject) oheader;
                String hname = (String) jheader.get("name");
                String htype = (String) jheader.get("type");
                this.columns.add(TableColumnFactory.Create(hname, Constants.GetClass(htype)));
            }

            // reading table data
            fields = (JSONArray) jtable.get("fields");
        }
        catch(IOException | ParseException ex){
            throw new RemoteException(ex.getMessage());
        }
    }

    public void validate() throws RemoteException {
        for(Object ofield:fields){
            JSONObject jfield = (JSONObject)ofield;
            for(ITableColumn column: columns){
                Object value = jfield.get(column.getName());
                validateValue(value, column);
            }
        }
    }

    void validateValue(Object value, ITableColumn column) throws RemoteException{
        Class<?> valueClass = value.getClass();

        IllegalArgumentException ex = new IllegalArgumentException("In table \"" + name + "\" field \"" + value + "\" is not type of \"" + column.getType().getSimpleName() + "\"");

        if(column.getType() == Integer.class){
            if(!(valueClass == Long.class && (long)value <= Integer.MAX_VALUE && (long)value >= Integer.MIN_VALUE))
                throw ex;
        }
        else if(column.getType() == Long.class){
            if(!(valueClass == Long.class))
                throw ex;
        }
        else if(column.getType() == Character.class){
            if(!(valueClass == String.class && ((String)value).length() == 1))
                throw ex;
        }
        else if(column.getType() == Double.class){
            if(!(valueClass == Double.class || valueClass == Long.class))
                throw ex;
        }
    }
}