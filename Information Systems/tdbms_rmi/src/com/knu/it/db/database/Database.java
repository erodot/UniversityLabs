package com.knu.it.db.database;

import com.knu.it.db.table.ITable;
import com.knu.it.db.table.TableFactory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

class Database extends UnicastRemoteObject implements IDatabase {
    protected String name;
    protected String root;
    protected List<ITable> tables = new ArrayList<>();

    Database(String name, String root) throws RemoteException {
        this.name = name;
        this.root = root;
    }

    @Override
    public void save() throws RemoteException{
        JSONObject databaseInfo = new JSONObject();
        databaseInfo.put("name", this.name);

        JSONArray tablesInfo = new JSONArray();
        for(ITable t: this.tables){
            JSONObject j = new JSONObject();
            j.put("name", t.getName());
            j.put("path", t.getPath());
            tablesInfo.add(j);
        }
        databaseInfo.put("table", tablesInfo);

        try (FileWriter file = new FileWriter(this.root + "db.json")) {

            file.write(databaseInfo.toJSONString());
            file.flush();

        } catch (IOException e) {
            throw new RemoteException(e.getMessage());
        }
    }

    void loadTables(JSONArray jtablesinfo){
        // loading table data from json object
        List<ITable> tables = new ArrayList<>();
        for (Object otableinfo : jtablesinfo) {
            JSONObject jtableinfo = (JSONObject) otableinfo;
            String tname = (String) jtableinfo.get("name");
            String tpath = (String) jtableinfo.get("path");
            try{
                ITable table = TableFactory.CreateEmpty(tname, this.root, tpath);
                tables.add(table);
            }
            catch (RemoteException ignored){}
        }

        this.tables.addAll(tables);

        // populate table with data
        this.tables.forEach(table -> {
            try {
                table.loadFromFile();
            }
            catch (RemoteException ex){
                ex.printStackTrace();
            }
        });

        // validate table data
        try{
            for (ITable table : this.tables) {
                table.validate();
            }
        }
        catch (RemoteException ex){
            ex.printStackTrace();
        }
    }

    @Override
    public ITable getTableByName(String tableName) throws RemoteException {
        for (ITable table : tables)
            try{
                if (table.getName().equals(tableName))
                    return table;
            }
            catch(RemoteException ignored){}

        // no table found
        throw new RemoteException("Database " + name + ": table with name " + tableName + " not found.");
    }

    @Override
    public void addTable(ITable table) throws RemoteException {
        tables.add(table);
        table.save();
        this.save();
    }

    @Override
    public String getName() throws RemoteException {
        return name;
    }

    @Override
    public String getRoot() throws RemoteException {
        return root;
    }

    @Override
    public List<ITable> getTables() throws RemoteException {
        return tables;
    }
}