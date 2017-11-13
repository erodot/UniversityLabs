package com.knu.it.javafx.database.chooser;

import com.knu.it.Function2;
import com.knu.it.db.database.DatabaseFactory;
import com.knu.it.javafx.database.viewer.DatabaseViewerController;
import com.knu.it.db.database.Database;
import com.knu.it.ws.IDatabaseAdapter;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DatabaseChooserController {

    private Function2<Stage, Label, EventHandler<ActionEvent>> onDirectoryChoose = (Stage stage, Label path) -> (EventHandler<ActionEvent>) event -> {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File directory = directoryChooser.showDialog(stage);

        if (directory == null) {
            path.setText("");
        } else {
            path.setText(directory.getAbsolutePath());
        }
    };
    private void openDatabaseWindow(Database db) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../viewer/viewer.fxml"));
            Parent root = loader.load();
            DatabaseViewerController controller = loader.getController();
            controller.setStageAndSetupListeners(stage, application, db);

            Stage stage = new Stage();
            stage.setTitle(db.getName());
            stage.setScene(new Scene(root, 600, 400));
            stage.show();
            this.stage.hide();
        }
        catch (IOException | NullPointerException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText(ex.getMessage());

            alert.showAndWait();
        }
    }

    private Stage stage;
    private Application application;
    private IDatabaseAdapter databaseAdapter;
    private Database[] existingDatabases;

    /* CREATE DATABASE */
    @FXML private TextField newDatabaseName;
    @FXML private void onDatabaseCreate(){
        if(newDatabaseName.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Name of database cannot be empty");

            alert.showAndWait();
            return;
        }

        String dbname = newDatabaseName.getText();

        Database new_db = databaseAdapter.createDatabase(dbname);
        try {
            new_db.save();
            openDatabaseWindow(new_db);
        }
        catch(IOException ex){
            ex.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText(ex.getMessage());

            alert.showAndWait();
        }
    };

    /* CHOOSE EXISTING DATABASE */
    @FXML private ComboBox openExistingDatabaseComboBox;
    @FXML private void onDatabaseOpenExisting(){
        String value = (String)openExistingDatabaseComboBox.getValue();
        for(Database db: existingDatabases)
            if(db.getName().equals(value))
                openDatabaseWindow(db);
    };

    public void setStageAndSetupListeners(Stage stage, Application application, IDatabaseAdapter databaseAdapter){
        this.stage = stage;
        this.application = application;
        this.databaseAdapter = databaseAdapter;

        existingDatabases = databaseAdapter.getDatabases();
        List<String> existingDatabasesNames = Arrays.stream(existingDatabases).map(d -> d.getName()).collect(Collectors.toList());
        openExistingDatabaseComboBox.setItems(FXCollections.observableArrayList(existingDatabasesNames));
        if(existingDatabasesNames.size() > 0)
            openExistingDatabaseComboBox.setValue(existingDatabasesNames.get(0));

    }
}