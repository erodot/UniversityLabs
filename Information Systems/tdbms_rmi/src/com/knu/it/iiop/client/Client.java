package com.knu.it.iiop.client;

import com.knu.it.iiop.server.IDatabaseAdapter;
import com.knu.it.rmi.client.javafx.database.chooser.DatabaseChooserController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;
import java.util.Properties;

public class Client extends Application {

    public Client() {}

    private static IDatabaseAdapter databaseAdapter;

    public static void main(String[] args) {
        try {
            Properties props = new Properties();
            props.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
            props.setProperty(Context.URL_PKG_PREFIXES, "org.jboss.naming:org.jnp.interfaces");
            InitialContext IC = new InitialContext(props);
            Object obj = IC.lookup("DatabaseAdapter");
            databaseAdapter = (IDatabaseAdapter) PortableRemoteObject.narrow(obj,IDatabaseAdapter.class);

            System.out.println("Connection established");

            launch(args);
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("javafx/database/chooser/chooser.fxml"));
        Parent root = loader.load();
        DatabaseChooserController controller = loader.getController();
        controller.setStageAndSetupListeners(primaryStage, this, (com.knu.it.rmi.server.DatabaseAdapter)databaseAdapter);
        primaryStage.setTitle("Choose database");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}

