package com.knu.it.rmi.server;

import com.knu.it.db.database.rmi.IRMIDatabase;
import com.knu.it.db.database.rmi.RMIDatabase;

import java.io.File;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Server implements DatabaseAdapter {

    public Server() {}

    public static void main(String args[]) {

        try {
            Server obj = new Server();
            DatabaseAdapter databaseAdapter = (DatabaseAdapter) UnicastRemoteObject.exportObject(obj, 0);

            // Bind the remote object's stub in the registry
            Registry registry;
            try {
                registry = LocateRegistry.createRegistry(2020);
            }
            catch(Exception ex){
                registry = LocateRegistry.getRegistry(2020);
            }

            registry.bind("DatabaseAdapter", databaseAdapter);

            System.out.println("Server ready");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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
        IRMIDatabase rmi_db = new RMIDatabase(name, db_path);
        return rmi_db;
    }
}
