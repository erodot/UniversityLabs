package com.knu.it.db.table.column;

class TableColumn implements ITableColumn {
    protected String name;
    protected Class<?> type;

    TableColumn(String name, Class<?> type){
        this.name = name;
        this.type = type;
    }

    @Override
    public String getName(){
        return name;
    }

    @Override
    public Class<?> getType(){
        return type;
    }
}
