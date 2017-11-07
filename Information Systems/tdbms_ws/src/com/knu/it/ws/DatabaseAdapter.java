package com.knu.it.ws;

import com.knu.it.db.database.Database;
import com.knu.it.db.database.DatabaseFactory;
import org.json.simple.parser.ParseException;

import javax.jws.WebService;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@WebService(endpointInterface = "com.knu.it.ws.IDatabaseAdapter")
public class DatabaseAdapter implements IDatabaseAdapter {

    @Override
    public Database[] getDatabases(){

        List<Database> databases = new ArrayList<>();

        File rootDirectory = new File("/Users/tedromanus/Documents");
        File[] listOfFiles = rootDirectory.listFiles();

        for (File file : listOfFiles) {
            if (file.isDirectory()) {
                File directory = file;

                if (Arrays.asList(directory.list()).contains("db.json")) {
                    try {
                        Database database = DatabaseFactory.CreateFromPath(rootDirectory.getAbsolutePath() + "/" + directory.getName() + "/");
                        databases.add(database);
                    }
                    catch(IOException | ParseException ex){
                        ex.printStackTrace();
                    }
                }
            }
        }

        return databases.toArray(new Database[databases.size()]);
    }

    @Override
    public Database createDatabase(String name){
        return DatabaseFactory.CreateEmpty(name);
    }
}
