package com.knu.it.rmi.client;

import com.knu.it.db.database.IDatabase;
import com.knu.it.db.database.rmi.IRMIDatabase;
import com.knu.it.db.table.ITable;
import com.knu.it.rmi.client.javafx.database.chooser.DatabaseChooserController;
import com.knu.it.rmi.server.DatabaseAdapter;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;

public class Client extends Application {

    public Client() {}

    private static DatabaseAdapter databaseAdapter;

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("javafx/database/chooser/chooser.fxml"));
        Parent root = loader.load();
        DatabaseChooserController controller = loader.getController();
        controller.setStageAndSetupListeners(primaryStage, this);
        primaryStage.setTitle("Choose database");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

        List<String> dbs = databaseAdapter.getAvailableDatabases();
        IRMIDatabase rmi_db = databaseAdapter.getDatabaseWithName(dbs.get(0));
        List<ITable> tables = rmi_db.Invoke("getTables");
        System.err.println("First table name: " + tables.get(0).getName());

        IDatabase db = rmi_db.getDatabase();
        System.err.println("Database name: " + db.getName());
    }

    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry(2020);
            databaseAdapter = (DatabaseAdapter) registry.lookup("DatabaseAdapter");
            System.out.println("Connection established");

            launch(args);
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}

