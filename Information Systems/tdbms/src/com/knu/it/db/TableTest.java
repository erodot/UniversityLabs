package com.knu.it.db;

import com.knu.it.HTML;
import org.hamcrest.CoreMatchers;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@SuppressWarnings("deprecation")
public class TableTest {
    Database database;
    Table table;

    @Before
    public void setUp() throws Exception {
        // create database
        database = new Database("Test database", "/Users/tedromanus/Documents/");
        database.save();

        // create table
        List<TableColumn> tableColumns = new ArrayList<>();
        JSONArray tableContent = new JSONArray();
        table = new Table("Test table", database.root, "Test table.json", tableColumns, tableContent);
    }

    @After
    public void tearDown() throws Exception {
        File databaseBaseFile = new File(database.root + "db.json");
        databaseBaseFile.delete();

        File tableFile = new File(table.root + table.path);
        tableFile.delete();
    }

    @Test
    public void project() throws Exception {
        List<TableColumn> realColumns = new ArrayList<>();
        realColumns.add(new TableColumn("Test Column 1", HTML.class));
        realColumns.add(new TableColumn("Test Column 2", Integer.class));
        realColumns.add(new TableColumn("Test Column 3", Long.class));
        realColumns.add(new TableColumn("Test Column 4", Double.class));
        realColumns.add(new TableColumn("Test Column 5", Character.class));

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

        List<TableColumn> expectedColumns = new ArrayList<>();
        expectedColumns.add(new TableColumn("Test Column 1", HTML.class));
        expectedColumns.add(new TableColumn("Test Column 3", Long.class));
        expectedColumns.add(new TableColumn("Test Column 5", Character.class));

        List<String> projectedColumns = new ArrayList<>();
        projectedColumns.add("Test Column 1");
        projectedColumns.add("Test Column 3");
        projectedColumns.add("Test Column 5");

        Table testTable = new Table("Test Table", "no root", "no path", realColumns, realFields);

        Table obtainedTable = testTable.project(projectedColumns);

        assertTrue(obtainedTable.columns.size() == expectedColumns.size());
        assertEquals(obtainedTable.columns.get(0).name, expectedColumns.get(0).name);
        assertEquals(obtainedTable.columns.get(1).name, expectedColumns.get(1).name);
        assertEquals(obtainedTable.columns.get(2).name, expectedColumns.get(2).name);
        assertEquals(obtainedTable.columns.get(0).type, expectedColumns.get(0).type);
        assertEquals(obtainedTable.columns.get(1).type, expectedColumns.get(1).type);
        assertEquals(obtainedTable.columns.get(2).type, expectedColumns.get(2).type);

        assertEquals(obtainedTable.fields.size(), 1);

        assertEquals(((JSONObject)obtainedTable.fields.get(0)).get("Test Column 1").toString(), ((JSONObject)expectedFields.get(0)).get("Test Column 1").toString());
        assertNull(((JSONObject)obtainedTable.fields.get(0)).get("Test Column 2"));
        assertEquals(((JSONObject)obtainedTable.fields.get(0)).get("Test Column 3").toString(), ((JSONObject)expectedFields.get(0)).get("Test Column 3").toString());
        assertNull(((JSONObject)obtainedTable.fields.get(0)).get("Test Column 4"));
        assertEquals(((JSONObject)obtainedTable.fields.get(0)).get("Test Column 5").toString(), ((JSONObject)expectedFields.get(0)).get("Test Column 5").toString());
    }

    @Test
    public void validateValueValid(){

        table.validateValue("<div></div>", new TableColumn("Test Column", HTML.class));

        table.validateValue(0L, new TableColumn("Test Column", Integer.class));
        table.validateValue(1L, new TableColumn("Test Column", Integer.class));
        table.validateValue(-1L, new TableColumn("Test Column", Integer.class));

        table.validateValue(0L, new TableColumn("Test Column", Long.class));
        table.validateValue(1L, new TableColumn("Test Column", Long.class));
        table.validateValue(-1L, new TableColumn("Test Column", Long.class));
        table.validateValue(Integer.MAX_VALUE + 1L, new TableColumn("Test Column", Long.class));
        table.validateValue(Integer.MIN_VALUE - 1L, new TableColumn("Test Column", Long.class));

        table.validateValue(0D, new TableColumn("Test Column", Double.class));
        table.validateValue(3.14, new TableColumn("Test Column", Double.class));

        table.validateValue("c", new TableColumn("Test Column", Character.class));
        table.validateValue("4", new TableColumn("Test Column", Character.class));
    }

    @Test(expected = IllegalArgumentException.class)
    public void validateValueInvalid() throws Exception {

        table.validateValue(Integer.MAX_VALUE + 1L, new TableColumn("Test Column", Integer.class));
        table.validateValue(Integer.MIN_VALUE - 1L, new TableColumn("Test Column", Integer.class));
        table.validateValue(3.14, new TableColumn("Test Column", Integer.class));
        table.validateValue("c", new TableColumn("Test Column", Integer.class));
        table.validateValue("<div></div>", new TableColumn("Test Column", Integer.class));

        table.validateValue(3.14, new TableColumn("Test Column", Long.class));
        table.validateValue("c", new TableColumn("Test Column", Long.class));
        table.validateValue("<div></div>", new TableColumn("Test Column", Long.class));

        table.validateValue(0L, new TableColumn("Test Column", Double.class));
        table.validateValue("c", new TableColumn("Test Column", Double.class));
        table.validateValue("<div></div>", new TableColumn("Test Column", Double.class));

        table.validateValue("<div></div>", new TableColumn("Test Column", Character.class));
        table.validateValue(0L, new TableColumn("Test Column", Character.class));
        table.validateValue(0D, new TableColumn("Test Column", Character.class));
    }

}