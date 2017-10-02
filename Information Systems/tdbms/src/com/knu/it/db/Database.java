package com.knu.it.db;

import com.knu.it.Constants;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.json.simple.parser.ParseException;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("deprecation")
public class Database {
    public String name;
    public String root;
    public List<Table> tables;

    public Database(String name, String root){
        this.name = name;
        this.root = root;
        this.tables = new ArrayList<>();
    }

    public void show(){
        final int column_width = Constants.COLUMN_WIDTH;
        int columns_length = 2;
        String horizontal_line = new String(new char[columns_length * (column_width + 1)]).replace("\0", "-") + "\n";;

        StringBuilder table = new StringBuilder();
        table.append(horizontal_line);
        StringBuilder field = new StringBuilder("Name");
        while(field.length() < column_width)
            field.append(" ");
        table.append("|" + field.toString());
        field = new StringBuilder("Path");
        while(field.length() < column_width)
            field.append(" ");
        table.append("|" + field.toString());
        table.append("|\n");

        for(Table t: tables){
            table.append(horizontal_line);
            field = new StringBuilder(t.name);
            while(field.length() < column_width)
                field.append(" ");
            table.append("|" + field.toString());
            field = new StringBuilder(t.path);
            while(field.length() < column_width)
                field.append(" ");
            table.append("|" + field.toString());
            table.append("|\n");
        }
        table.append(horizontal_line);
        System.out.println(table.toString());
    }

    public static Database createFromPath(String root) throws IOException, ParseException{
        JSONObject jdb = (JSONObject) Constants.jsonParser.parse(new FileReader(root + "db.json"));

        String db_name_string = (String) jdb.get("name");
        if(db_name_string == null)
            throw new IOException("db.json file does not contain field \"name\"");
        Database db = new Database(db_name_string, root);

        JSONArray db_tables_array = (JSONArray) jdb.get("tables");
        if(db_tables_array == null)
            throw new IOException("db.json file does not contain field \"tables\"");
        db.loadTables(db_tables_array);

        return db;
    }

    private void loadTables(JSONArray jtablesinfo){
        // loading table data from json object
        List<Table> tables = new ArrayList<>();
        for (Object otableinfo : jtablesinfo) {
            JSONObject jtableinfo = (JSONObject) otableinfo;
            String tname = (String) jtableinfo.get("name");
            String tpath = (String) jtableinfo.get("path");
            Table table = new Table(tname, tpath, this.root);
            tables.add(table);
        }

        this.tables.addAll(tables);

        // populate tables with data
        this.tables.forEach(table -> {
            try {
                table.loadFromFile();
            }
            catch (ParseException | IOException ex){
                ex.printStackTrace();
            }
        });

        // validate tables data
        try{
            this.tables.forEach(Table::validate);
        }
        catch (IllegalArgumentException ex){
            ex.printStackTrace();
        }
    }

    public Table getTableByName(String tableName) throws FileNotFoundException {
        for(Table table: tables)
            if(table.name.equals(tableName))
                return table;

        // no table found
        throw new FileNotFoundException("Database " + name + ": table with name " + tableName + " not found.");
    }
}
