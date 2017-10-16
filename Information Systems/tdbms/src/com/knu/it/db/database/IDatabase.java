package com.knu.it.db.database;

import com.knu.it.db.table.ITable;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface IDatabase {
    String getName();
    String getRoot();
    List<ITable> getTables();

    void save() throws IOException;
    ITable getTableByName(String tableName) throws FileNotFoundException;
}
