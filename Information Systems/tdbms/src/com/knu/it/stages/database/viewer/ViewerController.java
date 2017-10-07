package com.knu.it.stages.database.viewer;

import com.knu.it.db.Database;
import com.knu.it.db.Table;
import javafx.application.Application;
import javafx.application.HostServices;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;

public class ViewerController {

    private Stage stage;
    private Database database;
    private Application application;

    @FXML private GridPane tableGrid;
    @FXML private Hyperlink databaseName;

    private void openTableWindow(Table table) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../../table/viewer/viewer.fxml"));
            Parent root = loader.load();
            com.knu.it.stages.table.viewer.ViewerController controller = loader.getController();
            controller.setStageAndSetupListeners(stage, application, database, table);

            Stage stage = new Stage();
            stage.setTitle(table.name);
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

    private void setupTable(){

        databaseName.setText(database.name);
        databaseName.setVisited(true);
        databaseName.setBorder(Border.EMPTY);
        databaseName.setFont(Font.font("Roboto", FontWeight.BOLD, 20));
        databaseName.setOnAction(event -> {
            openFile(database.root + "db.json");
        });

        tableGrid.setPadding(new Insets(5,5,5,5));
        Label tableHeaderName = new Label("Table Name");
        Label tableHeaderPath = new Label("Table Path");
        tableHeaderName.setPadding(new Insets(5,5,5,5));
        tableHeaderPath.setPadding(new Insets(5,5,5,5));
        tableHeaderName.setFont(Font.font("Roboto", FontWeight.EXTRA_BOLD,14));
        tableGrid.addRow(0, tableHeaderName, tableHeaderPath);
        int row = 1;
        for (Table table : database.tables) {
            Hyperlink tableName = new Hyperlink();
            tableName.setText(table.name);
            tableName.setOnAction(event -> {
                openTableWindow(table);
            });
            tableName.setBorder(Border.EMPTY);
            tableName.setVisited(true);
            tableName.setPadding(new Insets(5, 5, 5, 5));

            Hyperlink tablePath = new Hyperlink();
            tablePath.setText(table.path);
            tablePath.setOnAction(event -> {
                openFile(database.root + table.path);
            });
            tablePath.setBorder(Border.EMPTY);
            tablePath.setVisited(true);
            tablePath.setPadding(new Insets(5, 5, 5, 5));
            tableGrid.addRow(row, tableName, tablePath);
            row++;
        }
    }

    public void setStageAndSetupListeners(Stage stage, Application application, Database database) {
        this.stage = stage;
        this.application = application;
        this.database = database;

        setupTable();
    }

    @FXML public void addNewTable(){

    }
}
