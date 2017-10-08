package com.knu.it.db;

import com.knu.it.Constants;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("deprecation")
public class Table {
    public String name;
    public String path;
    public String root;
    public List<TableColumn> columns;
    public JSONArray fields;

    /* PUBLIC METHODS */
    public Table(String name, String root, String path){ // initialization from file
        this.name = name;
        this.path = path;
        this.root = root;
        this.columns = new ArrayList<>();
        this.fields = new JSONArray();
    }

    public Table(String name, String root, String path, List<TableColumn> columns, JSONArray fields){
        this.name = name;
        this.root = root;
        this.path = path;
        this.columns = columns;
        this.fields = fields;
    }

    public Table project(List<String> columnNames){
        String newTableName = this.name + " projected";

        List<TableColumn> newTableColumns = new ArrayList<>();
        columnNames.forEach(columnName -> {
            this.columns.forEach(tableColumn -> {
                if(tableColumn.name.equals(columnName)){
                    newTableColumns.add(tableColumn);
                }
            });
        });

        JSONArray newTableFields = new JSONArray();
        for(Object orow: this.fields){
            JSONObject jrow = (JSONObject)orow;
            JSONObject newjrow = new JSONObject();
            for(TableColumn tc: newTableColumns){
                newjrow.put(tc.name, jrow.get(tc.name));
            }
            newTableFields.add(newjrow);
        }

        return new Table(newTableName, null, null, newTableColumns, newTableFields);
    }

    public void save() throws IOException{
        JSONObject tableInfo = new JSONObject();
        tableInfo.put("fields", this.fields);

        JSONArray columnsInfo = new JSONArray();
        for(TableColumn tc: this.columns){
            JSONObject j = new JSONObject();
            j.put("name", tc.name);
            j.put("type", Constants.GetClassName(tc.type));
            columnsInfo.add(j);
        }

        tableInfo.put("columns", columnsInfo);

        try (FileWriter file = new FileWriter(this.root + this.path)) {

            file.write(tableInfo.toJSONString());
            file.flush();

        } catch (IOException e) {
            throw e;
        }
    }

    public void update(int row_number, TableColumn column, Object value){
        JSONObject row = (JSONObject)this.fields.get(row_number);
        row.put(column.name, value);
    }

    /* PACKAGE-PRIVATE METHODS */

    void loadFromFile() throws ParseException, IOException{
        // reading table data
        JSONObject jtable = (JSONObject) Constants.jsonParser.parse(new FileReader(root + path));

        // reading table columns
        JSONArray jheaders = (JSONArray) jtable.get("columns");
        for(Object oheader: jheaders){
            JSONObject jheader = (JSONObject)oheader;
            String hname = (String)jheader.get("name");
            String htype = (String)jheader.get("type");
            this.columns.add(new TableColumn(hname, Constants.GetClass(htype)));
        }

        // reading table data
        fields = (JSONArray)jtable.get("fields");
    }

    public void validate() throws IllegalArgumentException {
        for(Object ofield:fields){
            JSONObject jfield = (JSONObject)ofield;
            for(TableColumn column: columns){
                Object value = jfield.get(column.name);
                validateValue(value, column);
            }
        }
    }

    /* PRIVATE METHODS */
    public void validateValue(Object value, TableColumn column) throws IllegalArgumentException{
        Class<?> valueClass = value.getClass();

        IllegalArgumentException ex = new IllegalArgumentException("In table \"" + name + "\" field \"" + value + "\" is not type of \"" + column.type.getSimpleName() + "\"");

        if(column.type == Integer.class){
            if(!(valueClass == Long.class && (long)value <= Integer.MAX_VALUE && (long)value >= Integer.MIN_VALUE))
                throw ex;
        }
        else if(column.type == Long.class){
            if(!(valueClass == Long.class))
                throw ex;
        }
        else if(column.type == Character.class){
            if(!(valueClass == String.class && ((String)value).length() == 1))
                throw ex;
        }
        else if(column.type == Double.class){
            if(!(valueClass == Double.class || valueClass == Long.class))
                throw ex;
        }
    }
}