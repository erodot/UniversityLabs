package com.knu.it.db.database;

import com.knu.it.db.table.ITable;
import com.knu.it.db.table.TableFactory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class Database implements IDatabase {
    protected String name;
    protected String root;
    protected List<ITable> tables = new ArrayList<>();

    Database(String name, String root){
        this.name = name;
        this.root = root;
    }

    @Override
    public void save() throws IOException{
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
            throw e;
        }
    }

    void loadTables(JSONArray jtablesinfo){
        // loading table data from json object
        List<ITable> tables = new ArrayList<>();
        for (Object otableinfo : jtablesinfo) {
            JSONObject jtableinfo = (JSONObject) otableinfo;
            String tname = (String) jtableinfo.get("name");
            String tpath = (String) jtableinfo.get("path");
            ITable table = TableFactory.CreateEmpty(tname, this.root, tpath);
            tables.add(table);
        }

        this.tables.addAll(tables);

        // populate table with data
        this.tables.forEach(table -> {
            try {
                table.loadFromFile();
            }
            catch (ParseException | IOException ex){
                ex.printStackTrace();
            }
        });

        // validate table data
        try{
            this.tables.forEach(ITable::validate);
        }
        catch (IllegalArgumentException ex){
            ex.printStackTrace();
        }
    }

    @Override
    public ITable getTableByName(String tableName) throws FileNotFoundException {
        for(ITable table: tables)
            if(table.getName().equals(tableName))
                return table;

        // no table found
        throw new FileNotFoundException("Database " + name + ": table with name " + tableName + " not found.");
    }


    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getRoot() {
        return root;
    }

    @Override
    public List<ITable> getTables() {
        return tables;
    }
}