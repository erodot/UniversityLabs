package com.knu.it.db.table;

import com.knu.it.Constants;
import com.knu.it.db.table.column.TableColumn;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Table{
    private String name;
    private String path;
    private String root;
    private List<TableColumn> columns;
    private JSONArray fields;

    /* PUBLIC METHODS */
    public Table(String name, String root, String path, List<TableColumn> columns, JSONArray fields){
        this.name = name;
        this.root = root;
        this.path = path;
        this.columns = columns;
        this.fields = fields;
    }

    public Table(){}

    public void setName(String name) { this.name = name; }
    public String getName() {
        return name;
    }

    public void setPath(String path) { this.path = path; }
    public String getPath() {
        return path;
    }

    public void setRoot(String root){ this. root = root; }
    public String getRoot() {
        return root;
    }

    public void setFields(JSONArray fields){ this.fields = fields; }
    public JSONArray getFields() {
        return fields;
    }

    public void setColumns(TableColumn[] columns) { this.columns = new ArrayList<>(Arrays.asList(columns)); }
    public TableColumn[] getColumns() {
        return columns.toArray(new TableColumn[columns.size()]);
    }

    public Table project(List<String> columnNames){
        String newTableName = this.name + " projected";

        List<TableColumn> newTableColumns = new ArrayList<>();
        columnNames.forEach(columnName -> {
            this.columns.forEach(tableColumn -> {
                if(tableColumn.getName().equals(columnName)){
                    newTableColumns.add(tableColumn);
                }
            });
        });

        JSONArray newTableFields = new JSONArray();
        for(Object orow: this.fields){
            JSONObject jrow = (JSONObject)orow;
            JSONObject newjrow = new JSONObject();
            for(TableColumn tc: newTableColumns){
                newjrow.put(tc.getName(), jrow.get(tc.getName()));
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
            j.put("name", tc.getName());
            j.put("type", Constants.GetClassName(tc.getType()));
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
        row.put(column.getName(), value);
    }

    /* PACKAGE-PRIVATE METHODS */

    public void loadFromFile() throws ParseException, IOException{
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
                Object value = jfield.get(column.getName());
                validateValue(value, column);
            }
        }
    }

    void validateValue(Object value, TableColumn column) throws IllegalArgumentException{
        Class<?> valueClass = value.getClass();

        IllegalArgumentException ex = new IllegalArgumentException("In table \"" + name + "\" field \"" + value + "\" is not type of \"" + column.getType().getSimpleName() + "\"");

        if(column.getType() == Integer.class){
            if(!(valueClass == Long.class && (long)value <= Integer.MAX_VALUE && (long)value >= Integer.MIN_VALUE))
                throw ex;
        }
        else if(column.getType() == Long.class){
            if(!(valueClass == Long.class))
                throw ex;
        }
        else if(column.getType() == Character.class){
            if(!(valueClass == String.class && ((String)value).length() == 1))
                throw ex;
        }
        else if(column.getType() == Double.class){
            if(!(valueClass == Double.class || valueClass == Long.class))
                throw ex;
        }
    }
}