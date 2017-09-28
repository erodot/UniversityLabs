package com.knu.it.db;

class TableColumn {
    private String name;
    private Class<?> type;

    TableColumn(String name, Class<?> type){
        this.name = name;
        this.type = type;
    }
}
