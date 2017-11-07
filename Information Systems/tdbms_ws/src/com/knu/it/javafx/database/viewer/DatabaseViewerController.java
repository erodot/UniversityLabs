package com.knu.it.javafx.database.viewer;

import com.knu.it.Constants;
import com.knu.it.Function2;
import com.knu.it.db.database.DatabaseFactory;
import com.knu.it.db.table.TableFactory;
import com.knu.it.db.table.column.TableColumnFactory;
import com.knu.it.javafx.table.viewer.TableViewerController;
import com.knu.it.db.database.Database;
import com.knu.it.db.table.Table;
import com.knu.it.db.table.column.TableColumn;
import javafx.application.Application;
import javafx.application.HostServices;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Border;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Pair;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.*;

public class DatabaseViewerController {

    private Stage stage;
    private Database database;
    private Application application;

    @FXML private GridPane tableGrid;
    @FXML private Hyperlink databaseName;

    private void openTableWindow(Table table) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../../table/viewer/viewer.fxml"));
            Parent root = loader.load();
            TableViewerController controller = loader.getController();

            Stage stage = new Stage();
            stage.setTitle(table.getName());
            controller.setStageAndSetupListeners(stage, this, application, database, table);
            stage.setScene(new Scene(root, 600, 400));
            stage.show();
        }
        catch (IOException | NullPointerException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText(ex.getMessage());

            alert.showAndWait();
        }
    }

    private void openFile(String path){
        File file = new File(path);
        HostServices hostServices = application.getHostServices();
        hostServices.showDocument(file.toURI().toString());
    }

    private void refreshTable(){
        databaseName.setText(database.getName());
        databaseName.setVisited(true);
        databaseName.setBorder(Border.EMPTY);
        databaseName.setFont(Font.font("Roboto", FontWeight.BOLD, 20));
        databaseName.setOnAction(event -> {
            openFile(database.getRoot() + "db.json");
        });

        Node node = tableGrid.getChildren().get(0);
        tableGrid.getChildren().clear();
        tableGrid.getChildren().add(0,node);
        tableGrid.setPadding(new Insets(5,5,5,5));
        Label tableHeaderName = new Label("Table Name");
        Label tableHeaderPath = new Label("Table Path");
        tableHeaderName.setPadding(new Insets(5,5,5,5));
        tableHeaderPath.setPadding(new Insets(5,5,5,5));
        tableHeaderName.setFont(Font.font("Roboto", FontWeight.EXTRA_BOLD,14));
        tableGrid.add(tableHeaderName, 0, 0);
        tableGrid.add(tableHeaderPath, 1, 0);
        int row = 1;
        for (Table table : database.getTables()) {
            Hyperlink tableName = new Hyperlink();
            tableName.setText(table.getName());
            tableName.setStyle("-fx-underline: false;");
            tableName.setOnAction(event -> {
                openTableWindow(table);
            });
            tableName.setBorder(Border.EMPTY);
            tableName.setVisited(true);
            tableName.setPadding(new Insets(5, 5, 5, 5));

            Hyperlink tablePath = new Hyperlink();
            tablePath.setText(table.getPath());
            tablePath.setStyle("-fx-underline: false;");
            tablePath.setOnAction(event -> {
                openFile(database.getRoot() + table.getPath());
            });
            tablePath.setBorder(Border.EMPTY);
            tablePath.setVisited(true);
            tablePath.setPadding(new Insets(5, 5, 5, 5));
            tableGrid.add(tableName, 0, row);
            tableGrid.add(tablePath, 1, row);
            row++;
        }
    }

    public void setStageAndSetupListeners(Stage stage, Application application, Database database) {
        this.stage = stage;
        this.application = application;
        this.database = database;

        refreshTable();
    }

    @FXML private void addNewTable(ActionEvent event){
        ((Hyperlink)event.getSource()).setVisited(false);

        Dialog<Pair<String, List<TableColumn>>> dialog = new Dialog<>();
        dialog.setTitle("Adding New Table");
        dialog.setHeaderText("Please enter table info:");

        ButtonType addTableType = new ButtonType("Create table", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(addTableType, ButtonType.CANCEL);

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField tableName = new TextField();
        tableName.setPromptText("Table name");

        grid.add(new Label("Table name:"), 0, 0);
        grid.add(tableName, 1, 0);
        grid.add(new Label("Fields:"), 0, 1);

        Node addTable = dialog.getDialogPane().lookupButton(addTableType);
        addTable.setDisable(true);

        ObservableList<String> availableTypes =
                FXCollections.observableArrayList("int", "long", "char", "double", "html");

        Map<TextField, ComboBox<String>> fieldsMap = new HashMap<>();

        TextField propertyField = new TextField();
        propertyField.setPromptText("Property name");
        propertyField.textProperty().addListener((observable, oldValue, newValue) -> addTable.setDisable(!validateData.apply(tableName, fieldsMap)));
        ComboBox<String> propertyType = new ComboBox<>(availableTypes);
        propertyType.setValue("int");
        fieldsMap.put(propertyField, propertyType);
        grid.addRow(2, propertyField, propertyType);

        Hyperlink addProperty = new Hyperlink("Add property");
        addProperty.setBorder(Border.EMPTY);
        addProperty.setOnAction(event1 -> {
            TextField newPropertyField = new TextField();
            newPropertyField.setPromptText("Property name");
            newPropertyField.textProperty().addListener((observable, oldValue, newValue) -> addTable.setDisable(!validateData.apply(tableName, fieldsMap)));
            ComboBox<String> newPropertyType = new ComboBox<>(availableTypes);
            newPropertyType.setValue("int");
            fieldsMap.put(newPropertyField, newPropertyType);
            try {
                Method method = grid.getClass().getDeclaredMethod("getNumberOfRows");
                method.setAccessible(true);
                int rows = (int) method.invoke(grid);
                grid.add(newPropertyField, 0, rows - 1);
                grid.add(newPropertyType, 1, rows - 1);
                grid.addRow(rows, addProperty);
            }
            catch(Exception ignored){}
            addTable.setDisable(!validateData.apply(tableName, fieldsMap));
            addProperty.setVisited(false);
        });

        grid.add(addProperty, 0, 3);

        tableName.textProperty().addListener((observable, oldValue, newValue) -> addTable.setDisable(!validateData.apply(tableName, fieldsMap)));

        scrollPane.setContent(grid);
        dialog.getDialogPane().setContent(scrollPane);
        Platform.runLater(tableName::requestFocus);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == addTableType) {
                List<TableColumn> columns = new ArrayList<>();
                for(Map.Entry<TextField, ComboBox<String>> entry: fieldsMap.entrySet()){
                    String columnName = entry.getKey().getText();
                    Class<?> columnType = Constants.GetClass(entry.getValue().getValue());
                    columns.add(TableColumnFactory.Create(columnName, columnType));
                }

                return new Pair<>(tableName.getText(), columns);
            }
            return null;
        });

        Optional<Pair<String, List<TableColumn>>> result = dialog.showAndWait();

        result.ifPresent(data -> {
            String tName = data.getKey();
            List<TableColumn> columns = data.getValue();
            Table newTable =  TableFactory.CreateEmpty(tName, database.getRoot(), tName + ".json", columns);
            database.addTable(newTable);
            try {
                newTable.save();
                database.save();
            }
            catch(IOException ex){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText(ex.getMessage());

                alert.showAndWait();
            }

            this.refreshTable();
        });
    }

    private Function2<TextField, Map<TextField, ComboBox<String>>, Boolean> validateData = (TextField tableName, Map<TextField, ComboBox<String>> fields) -> {
        boolean dataValid = true;
        if(tableName.getText().trim().isEmpty())
            dataValid = false;

        for(TextField key: fields.keySet())
            if(key.getText().trim().isEmpty())
                dataValid = false;

        return dataValid;
    };

    @FXML public void refresh(ActionEvent event){
        if(event != null) {
            Hyperlink link = (Hyperlink) event.getSource();
            if(link != null)
                link.setVisited(false);
        }

        try {
            database = DatabaseFactory.CreateFromPath(database.getRoot());
            this.refreshTable();
        }
        catch (IOException | ParseException ex){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText(ex.getMessage());

            alert.showAndWait();
            return;
        }
    }
}
