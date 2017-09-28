package com.knu.it.db;

import com.knu.it.Constants;
import com.knu.it.HTML;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("deprecation")
public class Table {
    public String name;
    public String path;
    private List<TableColumn> columns;
    public JSONArray fields;

    public Table(String name, String path){
        this.name = name;
        this.path = path;
        columns = new ArrayList<>();
    }

    void load(){
        try {
            // reading table data
            JSONObject jtable = (JSONObject) Constants.jsonParser.parse(new FileReader(Constants.DB_PATH + path));

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
        catch(ParseException | IOException iOException){
            iOException.printStackTrace();
        }
    }

    void validate() throws IllegalArgumentException {
        for(Object ofield:fields){
            JSONObject jfield = (JSONObject)ofield;
            for(TableColumn column: columns){
                Object value = jfield.get(column.name);
                if(column.type == HTML.class){
                    new HTML((String)value).validate();
                }
                else {
                    try {
                        column.type.cast(value);
                    }
                    catch(Exception e){
                        throw(new IllegalArgumentException("In table \"" + name + "\" field \"" + value + "\" is not type of \"" + column.type.getSimpleName() + "\""));
                    }
                }
            }
        }
    }
}
