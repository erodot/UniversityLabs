package com.knu.it.ws;

import com.knu.it.HTML;
import com.knu.it.db.database.Database;
import com.knu.it.db.database.DatabaseFactory;
import com.knu.it.db.table.Table;
import com.knu.it.db.table.TableFactory;
import com.knu.it.db.table.column.TableColumn;
import com.knu.it.db.table.column.TableColumnFactory;
import org.json.simple.parser.ParseException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.xml.ws.Endpoint;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ClientTests {

    private final static String global_path = "/Users/tedromanus/Documents/";

    private IDatabaseAdapter databaseAdapter;
    private Endpoint endpoint;

    @Before
    public void setUp(){
        endpoint = Endpoint.publish("http://localhost:9999/", new DatabaseAdapter());
        databaseAdapter = new DatabaseAdapterService().getIDatabaseAdapterPort();
    }

    @After
    public void tearDown(){
        endpoint.stop();
    }

    @Test
    public void getDatabases(){
        // 1. Get databases
        Database[] databases = databaseAdapter.getDatabases();
        assertNotNull(databases);
        assertTrue(databases.length > 0);
    }

    @Test
    public void createDatabase() throws IOException, ParseException{
        // 2. Create database

        // create
        String db_name = "db3";
        Database newDatabase = DatabaseFactory.CreateEmpty(db_name);
        newDatabase.save();

        // check
        Database createdDatabase = DatabaseFactory.CreateFromPath(global_path + db_name + "/");
        assertNotNull(createdDatabase);

        // delete
        assertTrue(new File(newDatabase.getRoot() + "db.json").delete());
        assertTrue(new File(newDatabase.getRoot()).delete());
    }

    @Test
    public void getTables(){
        // 3. Get database tables
        Database database = databaseAdapter.getDatabases()[0];
        Table[] tables = database.getTables();
        assertNotNull(tables);
        assertTrue(tables.length > 0);
    }

    @Test
    public void addTable() throws IOException{
        // 4. Adding table

        // testing data
        String t_name = "test_table";
        List<TableColumn> cols = new ArrayList<>();
        cols.add(TableColumnFactory.Create("test_column_1", Integer.class));
        cols.add(TableColumnFactory.Create("test_column_2", HTML.class));

        // get random database
        Database database = databaseAdapter.getDatabases()[0];

        // add table
        Table table = TableFactory.CreateEmpty(t_name, database.getRoot(), "/" + t_name + ".json", cols);
        database.addTable(table);
        table.save();
        database.save();

        // checking
        Table createdTable = database.getTableByName(t_name);
        assertNotNull(createdTable);
        assertEquals(table, createdTable);
        assertEquals(createdTable.getName(), table.getName());
        assertEquals(createdTable.getRoot(), table.getRoot());
        assertEquals(createdTable.getPath(), table.getPath());
        assertEquals(createdTable.getColumns(), table.getColumns());
        assertEquals(createdTable.getFields(), table.getFields());

        // remove table
        database.removeTable(table);
        database.save();
        assertTrue(new File(table.getRoot() + table.getPath()).delete());
    }
}
