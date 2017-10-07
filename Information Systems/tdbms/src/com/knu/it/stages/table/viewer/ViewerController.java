package com.knu.it.stages.table.viewer;

import com.knu.it.db.Database;
import com.knu.it.db.Table;
import javafx.application.Application;
import javafx.stage.Stage;

public class ViewerController {

    private Stage stage;
    private Application application;
    private Database database;
    private Table table;

    public void setStageAndSetupListeners(Stage stage, Application application, Database database, Table table){
        this.stage = stage;
        this.application = application;
        this.database = database;
        this.table = table;
    }
}
