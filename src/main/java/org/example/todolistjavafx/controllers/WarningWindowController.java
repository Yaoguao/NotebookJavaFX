package org.example.todolistjavafx.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class WarningWindowController {

    @FXML
    private Button buttonQuit;
    @FXML
    private void btnActionQuit(){
        Stage stage = (Stage) buttonQuit.getScene().getWindow();
        stage.close();
    }

}
