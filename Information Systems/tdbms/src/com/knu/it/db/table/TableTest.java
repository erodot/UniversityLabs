package com.knu.it.db.table;

import com.knu.it.HTML;
import com.knu.it.db.database.DatabaseFactory;
import com.knu.it.db.database.IDatabase;
import com.knu.it.db.table.column.ITableColumn;
import com.knu.it.db.table.column.TableColumnFactory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TableTest {
    IDatabase database;
    Table table;

    @Before
    public void setUp() throws Exception {
        // create database
        database = DatabaseFactory.CreateEmpty("Test database", "/Users/tedromanus/Documents/");
        database.save();

        // create table
        List<ITableColumn> tableColumns = new ArrayList<>();
        JSONArray tableContent = new JSONArray();
        table = new Table("Test table", database.getRoot(), "Test table.json", tableColumns, tableContent);
    }

    @After
    public void tearDown() throws Exception {
        File databaseBaseFile = new File(database.getRoot() + "db.json");
        databaseBaseFile.delete();

        File tableFile = new File(table.getRoot() + table.getPath());
        tableFile.delete();
    }

    @Test
    public void project() throws Exception {
        List<ITableColumn> realColumns = new ArrayList<>();
        realColumns.add(TableColumnFactory.Create("Test Column 1", HTML.class));
        realColumns.add(TableColumnFactory.Create("Test Column 2", Integer.class));
        realColumns.add(TableColumnFactory.Create("Test Column 3", Long.class));
        realColumns.add(TableColumnFactory.Create("Test Column 4", Double.class));
        realColumns.add(TableColumnFactory.Create("Test Column 5", Character.class));

        JSONArray realFields = new JSONArray();
        JSONObject realRowOne = new JSONObject();
        realRowOne.put("Test Column 1", "<div></div>");
        realRowOne.put("Test Column 2", 4L);
        realRowOne.put("Test Column 3", 4L);
        realRowOne.put("Test Column 4", 4D);
        realRowOne.put("Test Column 5", "c");
        realFields.add(realRowOne);

        JSONArray expectedFields = new JSONArray();
        JSONObject expectedRowOne = new JSONObject();
        expectedRowOne.put("Test Column 1", "<div></div>");
        expectedRowOne.put("Test Column 3", 4L);
        expectedRowOne.put("Test Column 5", "c");
        expectedFields.add(expectedRowOne);

        List<ITableColumn> expectedColumns = new ArrayList<>();
        expectedColumns.add(TableColumnFactory.Create("Test Column 1", HTML.class));
        expectedColumns.add(TableColumnFactory.Create("Test Column 3", Long.class));
        expectedColumns.add(TableColumnFactory.Create("Test Column 5", Character.class));

        List<String> projectedColumns = new ArrayList<>();
        projectedColumns.add("Test Column 1");
        projectedColumns.add("Test Column 3");
        projectedColumns.add("Test Column 5");

        ITable testTable = TableFactory.Create("Test Table", "no root", "no path", realColumns, realFields);

        ITable obtainedTable = testTable.project(projectedColumns);

        assertTrue(obtainedTable.getColumns().size() == expectedColumns.size());
        assertEquals(obtainedTable.getColumns().get(0).getName(), expectedColumns.get(0).getName());
        assertEquals(obtainedTable.getColumns().get(1).getName(), expectedColumns.get(1).getName());
        assertEquals(obtainedTable.getColumns().get(2).getName(), expectedColumns.get(2).getName());
        assertEquals(obtainedTable.getColumns().get(0).getType(), expectedColumns.get(0).getType());
        assertEquals(obtainedTable.getColumns().get(1).getType(), expectedColumns.get(1).getType());
        assertEquals(obtainedTable.getColumns().get(2).getType(), expectedColumns.get(2).getType());

        assertEquals(obtainedTable.getFields().size(), 1);

        assertEquals(((JSONObject)obtainedTable.getFields().get(0)).get("Test Column 1").toString(), ((JSONObject)expectedFields.get(0)).get("Test Column 1").toString());
        assertNull(((JSONObject)obtainedTable.getFields().get(0)).get("Test Column 2"));
        assertEquals(((JSONObject)obtainedTable.getFields().get(0)).get("Test Column 3").toString(), ((JSONObject)expectedFields.get(0)).get("Test Column 3").toString());
        assertNull(((JSONObject)obtainedTable.getFields().get(0)).get("Test Column 4"));
        assertEquals(((JSONObject)obtainedTable.getFields().get(0)).get("Test Column 5").toString(), ((JSONObject)expectedFields.get(0)).get("Test Column 5").toString());
    }

    @Test
    public void validateValueValid(){

        table.validateValue("<div></div>", TableColumnFactory.Create("Test Column", HTML.class));

        table.validateValue(0L, TableColumnFactory.Create("Test Column", Integer.class));
        table.validateValue(1L, TableColumnFactory.Create("Test Column", Integer.class));
        table.validateValue(-1L, TableColumnFactory.Create("Test Column", Integer.class));

        table.validateValue(0L, TableColumnFactory.Create("Test Column", Long.class));
        table.validateValue(1L, TableColumnFactory.Create("Test Column", Long.class));
        table.validateValue(-1L, TableColumnFactory.Create("Test Column", Long.class));
        table.validateValue(Integer.MAX_VALUE + 1L, TableColumnFactory.Create("Test Column", Long.class));
        table.validateValue(Integer.MIN_VALUE - 1L, TableColumnFactory.Create("Test Column", Long.class));

        table.validateValue(0D, TableColumnFactory.Create("Test Column", Double.class));
        table.validateValue(3.14, TableColumnFactory.Create("Test Column", Double.class));

        table.validateValue("c", TableColumnFactory.Create("Test Column", Character.class));
        table.validateValue("4", TableColumnFactory.Create("Test Column", Character.class));
    }

    @Test(expected = IllegalArgumentException.class)
    public void validateValueInvalid() throws Exception {

        table.validateValue(Integer.MAX_VALUE + 1L, TableColumnFactory.Create("Test Column", Integer.class));
        table.validateValue(Integer.MIN_VALUE - 1L, TableColumnFactory.Create("Test Column", Integer.class));
        table.validateValue(3.14, TableColumnFactory.Create("Test Column", Integer.class));
        table.validateValue("c", TableColumnFactory.Create("Test Column", Integer.class));
        table.validateValue("<div></div>", TableColumnFactory.Create("Test Column", Integer.class));

        table.validateValue(3.14, TableColumnFactory.Create("Test Column", Long.class));
        table.validateValue("c", TableColumnFactory.Create("Test Column", Long.class));
        table.validateValue("<div></div>", TableColumnFactory.Create("Test Column", Long.class));

        table.validateValue(0L, TableColumnFactory.Create("Test Column", Double.class));
        table.validateValue("c", TableColumnFactory.Create("Test Column", Double.class));
        table.validateValue("<div></div>", TableColumnFactory.Create("Test Column", Double.class));

        table.validateValue("<div></div>", TableColumnFactory.Create("Test Column", Character.class));
        table.validateValue(0L, TableColumnFactory.Create("Test Column", Character.class));
        table.validateValue(0D, TableColumnFactory.Create("Test Column", Character.class));
    }

}