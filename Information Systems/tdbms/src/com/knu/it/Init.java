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
            JSONObject jdb = (JSONObject) Constants.jsonParser.parse(new FileReader(Constants.DB_PATH + "db.json"));

            Database db = new Database((String)jdb.get("name"));
            db.loadTables((JSONArray)jdb.get("tables"));

            List<String> proj_columns = new ArrayList<>();
            proj_columns.add("filepath");
            proj_columns.add("timestamp");
            db
                    .getTableByName("table2")
                    .project(proj_columns)
                    .show();
        }
        catch(ParseException | IOException parseException){
            parseException.printStackTrace();
        }
    }
}
