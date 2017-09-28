package com.knu.it.db;

class TableColumn {
    public String name;
    public Class<?> type;

    TableColumn(String name, Class<?> type){
        this.name = name;
        this.type = type;
    }
}
