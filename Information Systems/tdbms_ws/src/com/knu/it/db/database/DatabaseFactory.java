package com.knu.it.db.database;

import com.knu.it.Constants;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class DatabaseFactory {
    public static Database CreateEmpty(String name){
        return new Database(name, "/Users/tedromanus/Documents/" + name + "/");
    }

    public static Database CreateFromPath(String root) throws IOException, ParseException {
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
}
