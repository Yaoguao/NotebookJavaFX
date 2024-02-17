package org.example.todolistjavafx.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.example.todolistjavafx.NotebookApplication;
import org.example.todolistjavafx.model.Person;
import org.example.todolistjavafx.model.db.NotebookHandler;

import java.io.IOException;
import java.sql.SQLException;

public class MyEditController {

    @FXML
    private Button buttonOk;

    @FXML
    private Button buttonChanel;

    @FXML
    private TextField fieldEditName;

    @FXML
    private TextField fieldEditNumber;

    @FXML
    private TextField fieldEditNote;

    @FXML
    private void btnActionOk(ActionEvent event) throws IOException {
        if(fieldEditName.getText().isEmpty() || fieldEditNumber.getText().isEmpty() || fieldEditNote.getText().isEmpty()){
            createWarningWindow();
        } else {
            NotebookHandler notebookHandler = null;
            try {
                notebookHandler = NotebookHandler.getInstance();
                notebookHandler.addPerson(new Person(fieldEditName.getText(), fieldEditNumber.getText(), fieldEditNote.getText()));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            System.out.println(event.getEventType());
            Stage stage = (Stage) buttonOk.getScene().getWindow();

            System.out.println(stage.toString());
            stage.close();
        }
    }

    @FXML
    private void btnActionChanel(ActionEvent event){
        Stage stage = (Stage) buttonChanel.getScene().getWindow();
        stage.close();
    }

    private void createWarningWindow() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(NotebookApplication.class.getResource("warningWindow.fxml"));
        Parent root = fxmlLoader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));

        stage.initModality(Modality.WINDOW_MODAL);

        stage.initOwner(this.buttonChanel.getScene().getWindow());
        stage.setTitle("Add");

        stage.setResizable(false);
        stage.showAndWait();
    }
}
