package com.knu.it.db.table.column;

public class TableColumn {
    private String name;
    private Class<?> type;

    public TableColumn(String name, Class<?> type){
        this.name = name;
        this.type = type;
    }

    public void setName(String name) { this.name = name; }
    public String getName(){
        return name;
    }

    public void setType(Class<?> type) { this.type = type; }
    public Class<?> getType(){
        return type;
    }
}
