package com.knu.it.stages.database.viewer;

import com.knu.it.db.Database;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ViewerController {

    private Stage stage;
    private Database database;

    @FXML private Label hello;

    public void setStageAndSetupListeners(Stage stage, Database database) {
        this.stage = stage;
        this.database = database;

        hello.setText("Hello from " + database.name);
    }
}
