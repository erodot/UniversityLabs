package com.knu.it.rmi.client.javafx;

import javafx.scene.control.Alert;

public class DialogFactory {
    public static void ShowErrorDialog(String message){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.showAndWait();
    }

    public static void ShowErrorDialog(Exception ex){
        ShowErrorDialog(ex.getMessage());
        ex.printStackTrace();
    }
}
