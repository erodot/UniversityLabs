package com.knu.it.stages.table.viewer;

import com.knu.it.db.Database;
import com.knu.it.db.Table;
import com.knu.it.db.TableColumn;
import com.knu.it.stages.database.viewer.DatabaseViewerController;
import com.knu.it.stages.table.item.ItemViewerController;
import javafx.application.Application;
import javafx.application.HostServices;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.IOException;

@SuppressWarnings("deprecation")
public class TableViewerController {

    private Stage stage;
    private DatabaseViewerController databasecontroller;
    private Application application;
    private Database database;
    private Table table;

    @FXML private Hyperlink tableName;
    @FXML private GridPane tableGrid;

    private void openFile(String path){
        File file = new File(path);
        HostServices hostServices = application.getHostServices();
        hostServices.showDocument(file.toURI().toString());
    }

    private void refreshTable(){
        tableName.setText(table.name);
        tableName.setVisited(true);
        tableName.setBorder(Border.EMPTY);
        tableName.setFont(Font.font("Roboto", FontWeight.BOLD, 20));
        tableName.setOnAction(event -> {
            openFile(table.root + table.path);
        });

        Node node = tableGrid.getChildren().get(0);
        tableGrid.getChildren().clear();
        tableGrid.getChildren().add(0,node);
        tableGrid.setPadding(new Insets(5,5,5,5));
        for(int i=0; i<table.columns.size(); i++){
            TableColumn column = table.columns.get(i);
            Label headerLabel = new Label(column.name);
            headerLabel.setPadding(new Insets(5,5,5,5));
            tableGrid.add(headerLabel, i, 0);
        }

        int gridRow = 1;
        for(Object orow: table.fields){
            JSONObject jrow = (JSONObject) orow;
            for(int i=0; i<table.columns.size(); i++){
                final int rowNum = gridRow - 1;
                TableColumn column = table.columns.get(i);
                Hyperlink cell = new Hyperlink();
                String cellContent = jrow.get(column.name).toString();
                cell.setText(cellContent);
                cell.setStyle("-fx-underline: false;");
                cell.setOnAction(event -> {
                    openCellInfo(cellContent, rowNum, column);
                });
                cell.setBorder(Border.EMPTY);
                cell.setVisited(true);
                cell.setPadding(new Insets(5, 5, 5, 5));

                tableGrid.add(cell, i, gridRow);
            }
            gridRow++;
        }
    }

    public void setStageAndSetupListeners(Stage stage, DatabaseViewerController databasecontroller, Application application, Database database, Table table){
        this.stage = stage;
        this.databasecontroller = databasecontroller;
        this.application = application;
        this.database = database;
        this.table = table;

        this.refreshTable();
    }

    @FXML public void refresh(ActionEvent event){
        if(event != null) {
            Hyperlink link = (Hyperlink) event.getSource();
            if (link != null)
                link.setVisited(false);
        }

        try {
            databasecontroller.refresh(null);
            database = Database.createFromPath(database.root);
            table = database.getTableByName(table.name);
        }
        catch(Exception ex){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText(ex.getMessage());

            alert.showAndWait();
            return;
        }

        this.refreshTable();
    }

    @FXML private void addNewRow(ActionEvent event){

    }

    private void openCellInfo(String cellContent, int row, TableColumn column){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../item/itemviewer.fxml"));
            Parent root = loader.load();
            ItemViewerController controller = loader.getController();

            Stage stage = new Stage();
            stage.setTitle("\"" + cellContent + "\" cell editing");
            controller.setStageAndSetupListeners(stage, this, table, cellContent, row, column);
            stage.setScene(new Scene(root, 450, 300));
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
}
