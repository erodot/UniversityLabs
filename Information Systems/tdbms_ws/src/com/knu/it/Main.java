package com.knu.it;

import com.knu.it.javafx.database.chooser.DatabaseChooserController;
import com.knu.it.ws.DatabaseAdapterService;
import com.knu.it.ws.IDatabaseAdapter;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("javafx/database/chooser/chooser.fxml"));
        Parent root = loader.load();
        DatabaseChooserController controller = loader.getController();

        DatabaseAdapterService service = new DatabaseAdapterService();
        IDatabaseAdapter databaseAdapter = service.getIDatabaseAdapterPort();

        controller.setStageAndSetupListeners(primaryStage, this, databaseAdapter);
        primaryStage.setTitle("Choose database");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
