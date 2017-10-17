package com.knu.it.db.database;

import com.knu.it.Constants;
import com.knu.it.db.database.rmi.RMIDatabase;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.rmi.RemoteException;

public class DatabaseFactory {
    public static IDatabase CreateEmpty(String name, String root) throws RemoteException{
        return new Database(name, root);
    }

    public static IDatabase CreateFromPath(String root) throws RemoteException {
        try {
            JSONObject jdb = (JSONObject) Constants.jsonParser.parse(new FileReader(root + "db.json"));

            String db_name_string = (String) jdb.get("name");
            if (db_name_string == null)
                throw new RemoteException("db.json file does not contain field \"name\"");
            Database db = new Database(db_name_string, root);

            JSONArray db_tables_array = (JSONArray) jdb.get("tables");
            if (db_tables_array == null)
                throw new RemoteException("db.json file does not contain field \"table\"");
            db.loadTables(db_tables_array);

            return db;
        }
        catch (ParseException | IOException ex){
            throw new RemoteException(ex.getMessage());
        }
    }

    public static IDatabase FromRMI(RMIDatabase rmi_db){
        try {
            return CreateFromPath(rmi_db.root);
        }
        catch(RemoteException ex){
            return null;
        }
    }
}
