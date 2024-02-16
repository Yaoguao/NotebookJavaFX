package org.example.todolistjavafx.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class WarningEmptyController {

    @FXML
    private Button buttonOk;

    public void btnActionOk(ActionEvent event) {
        Stage stage = (Stage) buttonOk.getScene().getWindow();
        stage.close();
    }
}
