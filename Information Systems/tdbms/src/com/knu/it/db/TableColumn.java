package com.knu.it.db;

public class TableColumn {
    public String name;
    public Class<?> type;

    public TableColumn(String name, Class<?> type){
        this.name = name;
        this.type = type;
    }
}
