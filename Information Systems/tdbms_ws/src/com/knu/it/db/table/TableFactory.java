package com.knu.it.db.table;

import com.knu.it.db.table.column.TableColumn;
import org.json.simple.JSONArray;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.List;

public class TableFactory {

    public static Table Create(String name, String root, String path, List<TableColumn> columns, JSONArray fields){
        return new Table(name, root, path, columns, fields);
    }

    public static Table CreateEmpty(String name, String root, String path, List<TableColumn> columns){
        return new Table(name, root, path, columns, new JSONArray());
    }

    public static Table CreateEmpty(String name, String root, String path){
        return new Table(name, root, path, new ArrayList<>(), new JSONArray());
    }
}
