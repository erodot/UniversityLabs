package com.knu.it.db;

import java.util.ArrayList;
import java.util.List;

public class Database {
    private String name;
    private List<Table> tables;

    public Database(String name){
        this.name = name;
        this.tables = new ArrayList<>();
    }

    public void loadTables(List<Table> tables){
        this.tables.addAll(tables);
        this.tables.forEach(Table::load);
        try{
            this.tables.forEach(Table::validate);
            System.out.println("Validation successful.");
        }
        catch (IllegalArgumentException illegalArgumentException){
            System.out.println("Validation failed: " + illegalArgumentException.getMessage());
        }
    }
}
