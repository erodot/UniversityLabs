package com.knu.it.rmi.client.javafx.database.chooser;

import com.knu.it.db.database.IDatabase;
import com.knu.it.db.database.rmi.IRMIDatabase;
import com.knu.it.rmi.client.javafx.DialogFactory;
import com.knu.it.rmi.client.javafx.database.viewer.DatabaseViewerController;
import com.knu.it.rmi.server.DatabaseAdapter;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Optional;

public class DatabaseChooserController {

    private void openDatabaseWindow(IDatabase db) {
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
            DialogFactory.ShowErrorDialog(ex);
        }
    }

    private Stage stage;
    private Application application;
    private DatabaseAdapter databaseAdapter;

    /* CREATE DATABASE */
    @FXML private TextField newDatabaseName;
    @FXML private void onDatabaseCreate(){
        if(newDatabaseName.getText().isEmpty()){
            DialogFactory.ShowErrorDialog("Name of database cannot be empty");
            return;
        }

        String dbname = newDatabaseName.getText();

        try {
            IRMIDatabase db = databaseAdapter.createDatabaseWithName(dbname);
            IDatabase database = db.getDatabase();
            openDatabaseWindow(database);
        }
        catch(RemoteException ex){
            DialogFactory.ShowErrorDialog(ex);
        }
    };

    /* CHOOSE EXISTING DATABASE */
    @FXML private ComboBox openExistingDatabaseComboBox;
    @FXML private void onDatabaseOpenExisting(){

        try{
            String value = (String)openExistingDatabaseComboBox.getValue();
            IRMIDatabase db = databaseAdapter.getDatabaseWithName(value);
            IDatabase database = db.getDatabase();
            if(database == null)
                throw new IOException("Error opening database.");
            else
                openDatabaseWindow(database);
        }
        catch(IOException ex){
            DialogFactory.ShowErrorDialog(ex);
        }
    };

    public void setStageAndSetupListeners(Stage stage, Application application, DatabaseAdapter databaseAdapter){
        this.stage = stage;
        this.application = application;
        this.databaseAdapter = databaseAdapter;

        try {
            List<String> existingDatabases = databaseAdapter.getAvailableDatabases();
            openExistingDatabaseComboBox.setItems(FXCollections.observableArrayList(existingDatabases));
            if(existingDatabases.size() > 0)
                openExistingDatabaseComboBox.setValue(existingDatabases.get(0));
        }
        catch(RemoteException ex){
            DialogFactory.ShowErrorDialog(ex);
        }
    }
}
