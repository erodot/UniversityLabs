package com.knu.it.db.table;

import com.knu.it.db.table.column.ITableColumn;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.List;

public interface ITable {
    String getName();
    String getPath();
    String getRoot();
    JSONArray getFields();
    List<ITableColumn> getColumns();
    Table project(List<String> columnNames);
    void save() throws IOException;
    void update(int row_number, ITableColumn column, Object value);
    void loadFromFile() throws ParseException, IOException;
    void validate() throws IllegalArgumentException;
}
