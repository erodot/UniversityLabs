package com.knu.it.db;

import com.knu.it.Constants;
import com.knu.it.HTML;
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
    String path;
    private String root;
    private List<TableColumn> columns;
    public JSONArray fields;

    /* PUBLIC METHODS */
    public Table(String name, String path, String root){ // initialization from file
        this.name = name;
        this.path = path;
        this.root = root;
        this.columns = new ArrayList<>();
        this.fields = new JSONArray();
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

        return new Table(newTableName, newTableColumns, newTableFields);
    }

    public void show(){
        final int column_width = Constants.COLUMN_WIDTH;
        int columns_length = this.columns.size();
        String horizontal_line = new String(new char[columns_length * (column_width + 1)]).replace("\0", "-") + "\n";;

        StringBuilder table = new StringBuilder();
        table.append(horizontal_line);
        this.columns.forEach(col -> {
            StringBuilder field = new StringBuilder(col.name + ", " + col.type.getSimpleName());
            while(field.length() < column_width)
                field.append(" ");
            table.append("|" + field.toString());
        });
        table.append("|\n");

        for(Object orow: fields){
            JSONObject jrow = (JSONObject)orow;
            table.append(horizontal_line);
            this.columns.forEach(col -> {
                StringBuilder field = new StringBuilder(jrow.get(col.name).toString());
                while(field.length() < column_width)
                    field.append(" ");
                table.append("|" + field.toString());
            });
            table.append("|\n");
        }
        table.append(horizontal_line);
        System.out.println(table.toString());
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

    /* PACKAGE-PRIVATE METHODS */
    private Table(String name, List<TableColumn> columns, JSONArray fields){
        this.name = name;
        this.path = "";
        this.columns = columns;
        this.fields = fields;
    }

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
    private void validateValue(Object value, TableColumn column) throws IllegalArgumentException{
        Class<?> valueClass = value.getClass();

        IllegalArgumentException ex = new IllegalArgumentException("In table \"" + name + "\" field \"" + value + "\" is not type of \"" + column.type.getSimpleName() + "\"");

        if(column.type == HTML.class){
            new HTML(this.root, (String) value).validate();
        }
        else if(column.type == Integer.class){
            if(!(valueClass == Long.class && (long)value <= Integer.MAX_VALUE))
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