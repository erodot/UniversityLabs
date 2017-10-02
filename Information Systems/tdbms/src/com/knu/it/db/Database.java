package com.knu.it.db;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileNotFoundException;
import java.io.IOException;
import org.json.simple.parser.ParseException;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("deprecation")
public class Database {
    private String name;
    private List<Table> tables;

    public Database(String name){
        this.name = name;
        this.tables = new ArrayList<>();
    }

    public void loadTables(JSONArray jtablesinfo){
        // loading table data from json object
        List<Table> tables = new ArrayList<>();
        for (Object otableinfo : jtablesinfo) {
            JSONObject jtableinfo = (JSONObject) otableinfo;
            String tname = (String) jtableinfo.get("name");
            String tpath = (String) jtableinfo.get("path");
            Table table = new Table(tname, tpath);
            tables.add(table);
        }

        this.tables.addAll(tables);

        // populate tables with data
        this.tables.forEach(table -> {
            try {
                table.loadFromFile();
            }
            catch (ParseException | IOException ex){
                ex.printStackTrace();
            }
        });

        // validate tables data
        try{
            this.tables.forEach(Table::validate);
            System.out.println("Validation successful.");
        }
        catch (IllegalArgumentException ex){
            ex.printStackTrace();
        }
    }

    public Table getTableByName(String tableName) throws FileNotFoundException {
        for(Table table: tables)
            if(table.name.equals(tableName))
                return table;

        // no table found
        throw new FileNotFoundException("Database " + name + ": table with name " + tableName + " not found.");
    }
}
