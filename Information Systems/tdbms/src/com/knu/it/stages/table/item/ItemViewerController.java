package com.knu.it.stages.table.item;

import com.knu.it.Constants;
import com.knu.it.HTML;
import com.knu.it.db.Table;
import com.knu.it.db.TableColumn;
import com.knu.it.stages.table.viewer.TableViewerController;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class ItemViewerController {
    private Stage stage;
    private TableViewerController tablecontroller;
    private Table table;
    private int row;
    private TableColumn column;

    @FXML private TextField propertyType;
    @FXML private TextField propertyValue;
    @FXML private TextArea propertyValueArea;
    @FXML private Button showHTMLButton;
    @FXML private GridPane grid;

    public void setStageAndSetupListeners(Stage stage, TableViewerController tablecontroller, Table table, String cellContent, int row, TableColumn column){
        this.stage = stage;
        this.tablecontroller = tablecontroller;
        this.table = table;
        this.row = row;
        this.column = column;

        grid.setPadding(new Insets(5,5,5,5));

        propertyType.setText(Constants.GetClassName(column.type));

        if(column.type == HTML.class){
            propertyValue.setVisible(false);
            propertyValueArea.setVisible(true);
            propertyValueArea.setManaged(true);
            propertyValueArea.setText(cellContent);
            showHTMLButton.setVisible(true);
        }
        else {
            propertyValue.setText(cellContent);
        }
    }

    @FXML private void updateProperty(){
        try{
            Object value = validateProperty((column.type == HTML.class ? propertyValueArea.getText() : propertyValue.getText()), column.type);
            table.update(row, column, value);
            table.save();
            tablecontroller.refresh(null);
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

    @FXML private void showHTML(){
        Alert alert = new Alert(Alert.AlertType.NONE);
        alert.setTitle("HTML Viewer");
        alert.setHeaderText(null);

        WebView content = new WebView();
        content.getEngine().loadContent(propertyValueArea.getText());

        alert.getDialogPane().setContent(content);
        alert.getDialogPane().getButtonTypes().add(ButtonType.OK);

        alert.showAndWait();
    }
}
