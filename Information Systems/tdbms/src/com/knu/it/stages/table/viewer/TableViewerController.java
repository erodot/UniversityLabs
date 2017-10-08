package com.knu.it.stages.table.viewer;

import com.knu.it.Constants;
import com.knu.it.Function2;
import com.knu.it.HTML;
import com.knu.it.db.Database;
import com.knu.it.db.Table;
import com.knu.it.db.TableColumn;
import com.knu.it.stages.database.viewer.DatabaseViewerController;
import com.knu.it.stages.table.item.ItemViewerController;
import javafx.application.Application;
import javafx.application.HostServices;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Border;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Pair;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SuppressWarnings("deprecation")
public class TableViewerController {

    private Stage stage;
    private DatabaseViewerController databasecontroller;
    private Application application;
    private Database database;
    private Table table;

    @FXML private Hyperlink tableName;
    @FXML private GridPane tableGrid;

    private void openFile(String path){
        File file = new File(path);
        HostServices hostServices = application.getHostServices();
        hostServices.showDocument(file.toURI().toString());
    }

    private void refreshTable(){
        tableName.setText(table.name);
        tableName.setVisited(true);
        tableName.setBorder(Border.EMPTY);
        tableName.setFont(Font.font("Roboto", FontWeight.BOLD, 20));
        tableName.setOnAction(event -> {
            openFile(table.root + table.path);
        });

        Node node = tableGrid.getChildren().get(0);
        tableGrid.getChildren().clear();
        tableGrid.getChildren().add(0,node);
        tableGrid.setPadding(new Insets(5,5,5,5));
        for(int i=0; i<table.columns.size(); i++){
            TableColumn column = table.columns.get(i);
            Label headerLabel = new Label(column.name);
            headerLabel.setPadding(new Insets(5,5,5,5));
            tableGrid.add(headerLabel, i, 0);
        }

        int gridRow = 1;
        for(Object orow: table.fields){
            JSONObject jrow = (JSONObject) orow;
            for(int i=0; i<table.columns.size(); i++){
                final int rowNum = gridRow - 1;
                TableColumn column = table.columns.get(i);
                Hyperlink cell = new Hyperlink();
                String cellContent = jrow.get(column.name).toString();
                cell.setText(cellContent.length() > 30 ? cellContent.substring(0,30) + "..." : cellContent);
                cell.setStyle("-fx-underline: false;");
                cell.setOnAction(event -> {
                    openCellInfo(cellContent, rowNum, column);
                });
                cell.setBorder(Border.EMPTY);
                cell.setVisited(true);
                cell.setPadding(new Insets(5, 5, 5, 5));

                tableGrid.add(cell, i, gridRow);
            }
            gridRow++;
        }
    }

    public void setStageAndSetupListeners(Stage stage, DatabaseViewerController databasecontroller, Application application, Database database, Table table){
        this.stage = stage;
        this.databasecontroller = databasecontroller;
        this.application = application;
        this.database = database;
        this.table = table;

        this.refreshTable();
    }

    @FXML public void refresh(ActionEvent event){
        if(event != null) {
            Hyperlink link = (Hyperlink) event.getSource();
            if (link != null)
                link.setVisited(false);
        }

        try {
            databasecontroller.refresh(null);
            database = Database.createFromPath(database.root);
            table = database.getTableByName(table.name);
        }
        catch(Exception ex){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText(ex.getMessage());

            alert.showAndWait();
            return;
        }

        this.refreshTable();
    }

    @FXML private void addNewRow(ActionEvent event){
        if(event != null) {
            Hyperlink link = (Hyperlink) event.getSource();
            if (link != null)
                link.setVisited(false);
        }

        Dialog<JSONObject> dialog = new Dialog<>();
        dialog.setTitle("Adding New Row");
        dialog.setHeaderText("Please enter row info:");

        ButtonType addRowType = new ButtonType("Add row", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(addRowType, ButtonType.CANCEL);

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        Node addRow = dialog.getDialogPane().lookupButton(addRowType);
        addRow.setDisable(true);

        List<Pair<TableColumn, TextField>> textFieldInputs = new ArrayList<>();
        List<Pair<TableColumn, TextArea>> textAreaInputs = new ArrayList<>();

        for(int i=0; i<table.columns.size(); i++){
            TableColumn column = table.columns.get(i);

            Label label = new Label(column.name);
            grid.add(label, 0, i);

            if(column.type == HTML.class){
                TextArea textArea = new TextArea();
                textArea.setPromptText("typeof \"" + Constants.GetClassName(column.type) + "\"");
                textArea.textProperty().addListener((observable, oldValue, newValue) -> addRow.setDisable(!validateData.apply(textFieldInputs, textAreaInputs)));
                grid.add(textArea, 1, i);
                textAreaInputs.add(new Pair<>(column, textArea));
            }
            else{
                TextField textField = new TextField();
                textField.setPromptText("typeof \"" + Constants.GetClassName(column.type) + "\"");
                textField.textProperty().addListener((observable, oldValue, newValue) -> addRow.setDisable(!validateData.apply(textFieldInputs, textAreaInputs)));
                grid.add(textField, 1, i);
                textFieldInputs.add(new Pair<>(column, textField));
            }
        }

        scrollPane.setContent(grid);
        dialog.getDialogPane().setContent(scrollPane);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == addRowType) {
                JSONObject array = new JSONObject();
                try {
                    for (Pair<TableColumn, TextField> pair : textFieldInputs) {
                        Class<?> type = pair.getKey().type;
                        Object value = validateProperty(pair.getValue().getText(), type);
                        array.put(pair.getKey().name, value);
                    }
                    for (Pair<TableColumn, TextArea> pair : textAreaInputs) {
                        Class<?> type = pair.getKey().type;
                        Object value = validateProperty(pair.getValue().getText(), type);
                        array.put(pair.getKey().name, value);
                    }
                }
                catch(Exception ex){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText(ex.getMessage());

                    alert.showAndWait();
                    return null;
                }
                return array;
            }
            return null;
        });

        Optional<JSONObject> result = dialog.showAndWait();

        result.ifPresent(row -> {
            try{
                table.fields.add(row);
                table.save();
                this.refreshTable();
            }
            catch (IOException ex){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText(ex.getMessage());

                alert.showAndWait();
            }
        });
    }

    private void openCellInfo(String cellContent, int row, TableColumn column){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../item/itemviewer.fxml"));
            Parent root = loader.load();
            ItemViewerController controller = loader.getController();

            Stage stage = new Stage();
            stage.setTitle("Cell editing");
            controller.setStageAndSetupListeners(stage, this, table, cellContent, row, column);
            stage.setScene(new Scene(root));
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

    @FXML private void deleteTable(ActionEvent event){
        if(event != null) {
            Hyperlink link = (Hyperlink) event.getSource();
            if (link != null)
                link.setVisited(false);
        }

        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setTitle("Delete");
        confirmationAlert.setHeaderText(null);
        confirmationAlert.setContentText("Are you sure you want to delete table?");

        Optional<ButtonType> result = confirmationAlert.showAndWait();

        if (result.get() == ButtonType.OK){
            try{
                database.tables.remove(table);
                database.save();

                File tableFile = new File(database.root + table.path);
                tableFile.delete();

                stage.hide();
            }
            catch(Exception ex){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText(ex.getMessage());

                alert.showAndWait();
                return;
            }

            databasecontroller.refresh(null);
        }
    }

    private Object validateProperty(String str, Class<?> type) throws IllegalArgumentException{
        Object value;
        if(type == HTML.class) {
            value = str;
        }
        else if (type == Integer.class){
            value = new Integer(str);
        }
        else if (type == Long.class){
            value = new Long(str);
        }
        else if (type == Character.class){
            if(str.length()!= 1)
                throw new IllegalArgumentException("Please enter exactly 1 symbol.");
            value = str.substring(0, 1);
        }
        else if (type == Double.class){
            value = new Double(str);
        }
        else
            value = null;

        return value;
    }

    private Function2<List<Pair<TableColumn, TextField>>, List<Pair<TableColumn, TextArea>>, Boolean> validateData = (List<Pair<TableColumn, TextField>> textFields, List<Pair<TableColumn, TextArea>> textAreas) -> {
        boolean dataValid = true;

        for(Pair<TableColumn, TextField> pair: textFields)
            if(pair.getValue().getText().trim().isEmpty())
                dataValid = false;

        for(Pair<TableColumn, TextArea> pair: textAreas)
            if(pair.getValue().getText().trim().isEmpty())
                dataValid = false;

        return dataValid;
    };

    @FXML private void project(ActionEvent event){
        if(event != null) {
            Hyperlink link = (Hyperlink) event.getSource();
            if (link != null)
                link.setVisited(false);
        }

        Dialog<List<String>> dialog = new Dialog<>();

        dialog.setTitle("Project Table");
        dialog.setHeaderText("Please select columns:");

        ButtonType projectButtonType = new ButtonType("Project", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(projectButtonType, ButtonType.CANCEL);

        List<CheckBox> checkBoxes = new ArrayList<>();
        VBox vbox = new VBox();

        for(TableColumn column: table.columns){
            CheckBox checkBox = new CheckBox(column.name);
            checkBox.setPadding(new Insets(5,5,5,5));
            checkBoxes.add(checkBox);
            vbox.getChildren().add(checkBox);
        }

        dialog.getDialogPane().setContent(vbox);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == projectButtonType) {
                List<String> checked = new ArrayList<>();
                checkBoxes.forEach(checkBox -> {
                    if(checkBox.isSelected())
                        checked.add(checkBox.getText());
                });
                return checked;
            }
            return null;
        });

        Optional<List<String>> result = dialog.showAndWait();

        result.ifPresent(columnNames -> {
            Table table = this.table.project(columnNames);

            Alert alert = new Alert(Alert.AlertType.NONE);
            alert.setTitle("Table Projection");
            alert.setHeaderText(null);

            GridPane grid = new GridPane();
            grid.setGridLinesVisible(true);
            for(int i=0; i<table.columns.size(); i++){
                TableColumn column = table.columns.get(i);
                Label headerLabel = new Label(column.name);
                headerLabel.setPadding(new Insets(5,5,5,5));
                grid.add(headerLabel, i, 0);
            }

            int gridRow = 1;
            for(Object orow: table.fields){
                JSONObject jrow = (JSONObject) orow;
                for(int i=0; i<table.columns.size(); i++){
                    final int rowNum = gridRow - 1;
                    TableColumn column = table.columns.get(i);
                    Label cell = new Label();
                    String cellContent = jrow.get(column.name).toString();
                    cell.setText(cellContent.length() > 30 ? cellContent.substring(0,30) + "..." : cellContent);
                    cell.setPadding(new Insets(5, 5, 5, 5));

                    grid.add(cell, i, gridRow);
                }
                gridRow++;
            }

            alert.getDialogPane().setContent(grid);
            alert.getDialogPane().getButtonTypes().add(ButtonType.OK);

            alert.showAndWait();
        });
    }
}
