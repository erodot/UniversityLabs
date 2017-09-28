package com.knu.it.db;

import java.util.HashMap;
import java.util.Map;

class TableRow {
    private Map<String, Object> cells;

    TableRow(){
        cells = new HashMap<>();
    }

    void add(String key, Object value){
        cells.put(key, value);
    }

    Object get(String key){
        return cells.get(key);
    }
}
