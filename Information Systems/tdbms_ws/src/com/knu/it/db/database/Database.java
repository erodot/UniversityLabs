package com.knu.it.db.database;

import com.knu.it.db.table.Table;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Database {
    private String name;
    private String root;
    private List<Table> tables = new ArrayList<>();

    public Database(String name, String root){
        this.name = name;
        this.root = root;
    }

    public Database(){}

    public void save() throws IOException{
        JSONObject databaseInfo = new JSONObject();
        databaseInfo.put("name", this.name);

        JSONArray tablesInfo = new JSONArray();
        for(Table t: this.tables){
            JSONObject j = new JSONObject();
            j.put("name", t.getName());
            j.put("path", t.getPath());
            tablesInfo.add(j);
        }
        databaseInfo.put("tables", tablesInfo);

        File rootDirectory = new File(root);
        rootDirectory.mkdirs();

        try (FileWriter file = new FileWriter(this.root + "db.json")) {

            file.write(databaseInfo.toJSONString());
            file.flush();

        } catch (IOException e) {
            throw e;
        }
    }

    public void loadTables(JSONArray jtablesinfo){
        // loading table data from json object
        List<Table> tables = new ArrayList<>();
        for (Object otableinfo : jtablesinfo) {
            JSONObject jtableinfo = (JSONObject) otableinfo;
            String tname = (String) jtableinfo.get("name");
            String tpath = (String) jtableinfo.get("path");
            Table table =  new Table(tname, this.root, tpath, new ArrayList<>(), new JSONArray());
            tables.add(table);
        }

        this.tables = tables;

        // populate table with data
        for (Table table : this.tables) {
            try {
                table.loadFromFile();
            } catch (ParseException | IOException ex) {
                ex.printStackTrace();
            }
        }

        // validate table data
        try{
            for (Table table : this.tables) {
                table.validate();
            }
        }
        catch (IllegalArgumentException ex){
            ex.printStackTrace();
        }
    }

    public Table getTableByName(String tableName) throws FileNotFoundException {
        for(Table table: tables)
            if(table.getName().equals(tableName))
                return table;

        // no table found
        throw new FileNotFoundException("Database " + name + ": table with name " + tableName + " not found.");
    }

    public void setName(String name){ this.name = name; }
    public String getName() {
        return name;
    }

    public void setRoot(String root){ this.root = root; }
    public String getRoot() {
        return root;
    }

    public void addTable(Table t) {
        this.tables.add(t);
    }
    public void removeTable(Table t) {
        this.tables.remove(t);
    }

    public void setTables(Table[] tables){ this.tables = new ArrayList<>(Arrays.asList(tables)); }
    public Table[] getTables() { return tables.toArray(new Table[tables.size()]); }
}