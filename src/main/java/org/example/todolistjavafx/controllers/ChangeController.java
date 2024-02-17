package org.example.todolistjavafx.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.util.Optional;

public class ChangeController {

    @FXML @Getter
    private TextField textFieldChangeName;

    @FXML @Getter
    private TextField textFieldChangeNumber;

    @FXML @Getter
    private TextArea textFieldChangeNote;

    @FXML
    private Button buttonChangeOk;

    @FXML
    private Button buttonChangeChanel;

    @Getter @Setter
    private NotebookController parent;


    @FXML
    public void btnActionChangeOk(ActionEvent event) throws IOException {
        Stage stage = (Stage) buttonChangeOk.getScene().getWindow();
        stage.close();

    }
    @FXML
    public void btnActionChangeChanel(ActionEvent event) {
        Stage stage = (Stage) buttonChangeChanel.getScene().getWindow();
        stage.close();
    }
}
