package com.knu.it.stages.database.chooser;

import com.knu.it.db.Database;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class ChooserController {

    /* CREATE DATABASE */
    @FXML private TextField newDatabaseName;
    @FXML private Label newDatabasePath;
    @FXML private Button newDatabaseDirectoryChooser;
    @FXML private Button createDatabaseButton;

    private File newDatabasePathDirectory;

    public void setStageAndSetupListeners(Stage stage){
        /* CREATE DATABASE */
        newDatabaseDirectoryChooser.setOnAction(event -> {
            DirectoryChooser directoryChooser = new DirectoryChooser();
            newDatabasePathDirectory =
                    directoryChooser.showDialog(stage);

            if(newDatabasePathDirectory == null){
                newDatabasePath.setText("");
            }else{
                newDatabasePath.setText(newDatabasePathDirectory.getAbsolutePath());
            }
        });

        createDatabaseButton.setOnAction(event -> {
            if(newDatabaseName.getText().isEmpty()){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Name of database cannot be empty");

                alert.showAndWait();
                return;
            }

            if(newDatabasePathDirectory == null){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Please select path to database");

                alert.showAndWait();
                return;
            }

            String dbname = newDatabaseName.getText();

            File path = new File(newDatabasePathDirectory.getAbsolutePath() + "/" + dbname);
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

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText(null);
                alert.setContentText("Нову базу \"" + db.name + "\" створено!");
                alert.showAndWait();
            }
            catch(IOException ex){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText(ex.getMessage());

                alert.showAndWait();
            }
        });
    }
}
