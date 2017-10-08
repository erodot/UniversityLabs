package com.knu.it.db;

import com.knu.it.Constants;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
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

    public void save() throws IOException{
        JSONObject databaseInfo = new JSONObject();
        databaseInfo.put("name", this.name);

        JSONArray tablesInfo = new JSONArray();
        for(Table t: this.tables){
            JSONObject j = new JSONObject();
            j.put("name", t.name);
            j.put("path", t.path);
            tablesInfo.add(j);
        }
        databaseInfo.put("tables", tablesInfo);

        try (FileWriter file = new FileWriter(this.root + "db.json")) {

            file.write(databaseInfo.toJSONString());
            file.flush();

        } catch (IOException e) {
            throw e;
        }
    }

    public static Database loadFromPath(String root) throws IOException, ParseException{
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
            Table table = new Table(tname, this.root, tpath);
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