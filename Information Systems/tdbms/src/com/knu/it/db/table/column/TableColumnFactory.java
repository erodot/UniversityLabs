package com.knu.it.db.table.column;

public class TableColumnFactory {
    public static ITableColumn Create(String name, Class<?> type){
        return new TableColumn(name, type);
    }
}
