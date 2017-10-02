package com.knu.it.db;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

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
        List<Table> tables = new ArrayList<>();
        for (Object otableinfo : jtablesinfo) {
            JSONObject jtableinfo = (JSONObject) otableinfo;
            String tname = (String) jtableinfo.get("name");
            String tpath = (String) jtableinfo.get("path");
            Table table = new Table(tname, tpath);
            tables.add(table);
        }

        this.tables.addAll(tables);
        this.tables.forEach(Table::load);
        try{
            this.tables.forEach(Table::validate);
            System.out.println("Validation successful.");
        }
        catch (IllegalArgumentException illegalArgumentException){
            illegalArgumentException.printStackTrace();
        }
    }
}
