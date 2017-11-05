package com.knu.it.iiop.server;

import com.knu.it.db.database.DatabaseFactory;
import com.knu.it.db.database.IDatabase;
import com.knu.it.db.database.rmi.IRMIDatabase;
import com.knu.it.db.database.rmi.RMIDatabase;

import java.io.File;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class DatabaseAdapter implements IDatabaseAdapter {

    private String root = "/Users/tedromanus/Documents";

    @Override
    public List<String> getAvailableDatabases() throws RemoteException{
        List<String> databases = new ArrayList<>();

        File rootDirectory = new File(root);
        File[] listOfFiles = rootDirectory.listFiles();

        for (File file : listOfFiles) {
            if (file.isDirectory()) {
                File directory = file;

                if (Arrays.asList(directory.list()).contains("db.json")) {
                    databases.add(directory.getName());
                }
            }
        }

        return databases;
    }

    @Override
    public IRMIDatabase getDatabaseWithName(String name) throws RemoteException{
        String db_path = root + "/" + name + "/";
        IRMIDatabase rmi_db = new RMIDatabase(db_path);
        return rmi_db;
    }

    @Override
    public IRMIDatabase createDatabaseWithName(String name) throws RemoteException{
        String db_path = root + "/" + name + "/";
        File f = new File(db_path);
        f.mkdirs();
        IDatabase db = DatabaseFactory.CreateEmpty(name, db_path);
        db.save();
        return new RMIDatabase(db_path);
    }
}
