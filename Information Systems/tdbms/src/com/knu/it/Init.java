package com.knu.it;

import com.knu.it.db.Database;
import com.knu.it.db.Table;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Init {

    @SuppressWarnings("deprecation")
    public static void main(String[] args){
        try{
            JSONObject jdb = (JSONObject) Constants.jsonParser.parse(new FileReader(Constants.DB_PATH + "/db.json"));

            String dbname = (String)jdb.get("name");

            List<Table> dbtables = new ArrayList<>();
            JSONArray jtables = (JSONArray)jdb.get("tables");
            for (Object otable : jtables) {
                JSONObject jtable = (JSONObject) otable;
                String tname = (String) jtable.get("name");
                String tpath = (String) jtable.get("path");
                Table table = new Table(tname, tpath);
                dbtables.add(table);
            }

            Database db = new Database(dbname);
            db.loadTables(dbtables);
        }
        catch(ParseException | IOException parseException){
            parseException.printStackTrace();
        }

    }
}
