package com.knu.it.db;

import com.knu.it.Constants;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Table {
    public String name;
    public String path;
    private List<TableColumn> columns;
    public List<TableRow> rows;

    public Table(String name, String path){
        this.name = name;
        this.path = path;
        columns = new ArrayList<>();
        rows = new ArrayList<>();
    }

    @SuppressWarnings("deprecation")
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
        }
        catch(ParseException | IOException iOException){
            iOException.printStackTrace();
        }
    }
}
