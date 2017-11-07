package com.knu.it.ws;

import com.knu.it.db.database.Database;
import com.knu.it.db.database.DatabaseFactory;
import com.knu.it.db.table.Table;

import java.io.IOException;

public class Client {
    public static void main(String[] args){
        DatabaseAdapterService service = new DatabaseAdapterService();
        IDatabaseAdapter databaseAdapter = service.getIDatabaseAdapterPort();

        // 1. get databases
        Database database = databaseAdapter.getDatabases()[0];

        // 2. get tables
        Table table = database.getTables()[0];
        System.out.println("2. Table name: " + table.getName());

        // 3. create database
        Database newDatabase = DatabaseFactory.CreateEmpty("db3");
        try {
            newDatabase.save();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("3. New database name: " + newDatabase.getName());
    }
}
