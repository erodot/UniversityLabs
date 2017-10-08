package com.knu.it.stages.database.chooser;

import com.knu.it.Function2;
import com.knu.it.db.Database;
import com.knu.it.stages.database.viewer.DatabaseViewerController;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.IOException;

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
            stage.setTitle(db.name);
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

    private Stage stage;
    private Application application;

    /* CREATE DATABASE */
    @FXML private TextField newDatabaseName;
    @FXML private Label newDatabasePath;
    @FXML private Button newDatabaseDirectoryChooser;
    @FXML private void onDatabaseCreate(){
        if(newDatabaseName.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Name of database cannot be empty");

            alert.showAndWait();
            return;
        }

        String root = newDatabasePath.getText();

        if(root.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please select path to database");

            alert.showAndWait();
            return;
        }

        root = root + "/";
        String dbname = newDatabaseName.getText();

        File path = new File(root + dbname);
        path.mkdirs();

        File dbfilepath = new File(path.getAbsolutePath() + "/db.json");
        if(dbfilepath.exists()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Database already exists on this path. Please try another path.");

            alert.showAndWait();
            return;
        }

        try {
            Database db = new Database(dbname, path.getAbsolutePath() + "/");
            db.save();
            openDatabaseWindow(db);
        }
        catch(IOException ex){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText(ex.getMessage());

            alert.showAndWait();
        }
    };

    /* CHOOSE EXISTING DATABASE */
    @FXML private Label existingDatabasePath;
    @FXML private Button existingDatabaseDirectoryChooser;
    @FXML private Button openExistingDatabaseButton;
    @FXML private void onDatabaseOpenExisting(){
        String root = existingDatabasePath.getText();

        if(root.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please select path to database");

            alert.showAndWait();
            return;
        }
        root = root + "/";

        try{
            Database db = Database.createFromPath(root);
            openDatabaseWindow(db);
        }
        catch(IOException | ParseException ex){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText(ex.getMessage());

            alert.showAndWait();
        }
    };

    public void setStageAndSetupListeners(Stage stage, Application application){
        this.stage = stage;
        this.application = application;

        /* CREATE DATABASE */
        newDatabaseDirectoryChooser.setOnAction(onDirectoryChoose.apply(stage, newDatabasePath));

        /* CHOOSE EXISTING DATABASE */
        existingDatabaseDirectoryChooser.setOnAction(onDirectoryChoose.apply(stage, existingDatabasePath));
    }
}