package org.example.todolistjavafx.controllers;

import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lombok.Getter;
import org.example.todolistjavafx.NotebookApplication;
import org.example.todolistjavafx.model.Person;
import org.example.todolistjavafx.model.db.NotebookHandler;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class NotebookController implements Initializable {

    @FXML
    Button buttonAdd;

    @FXML
    Button buttonChange;

    @FXML
    Button buttonDelete;

    @FXML
    TableColumn tblColumnName;

    @FXML
    TableColumn tblColumnNumber;

    @FXML
    TableColumn tblColumnNote;

    @FXML
    TableView nbTableView;

    @FXML
    Label labelCount;

    @Getter
    ChangeController children;

    @FXML
    private void btnActionAdd(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(NotebookApplication.class.getResource("viewEdit.fxml"));
        createModalStage("WarningEmpty", fxmlLoader);
        updateTableView();
    }

    @FXML
    public void btnActionChange(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(NotebookApplication.class.getResource("viewChange.fxml"));
        if (nbTableView.getSelectionModel().isEmpty()) {
            createModalStage("WarningEmpty", new FXMLLoader(NotebookApplication.class.getResource("warningEmpty.fxml")));
            return;
        }

        TableView.TableViewSelectionModel<Person> selectionModel = nbTableView.getSelectionModel();
        Person person = selectionModel.getSelectedItem();

        System.out.println(person.getId());

        createModalStage("Change", fxmlLoader);

        children = fxmlLoader.getController();
        children.setParent(this);
        String name = children.getTextFieldChangeName().getText();
        String number = children.getTextFieldChangeNumber().getText();
        String note = children.getTextFieldChangeNote().getText();

        if (!name.isEmpty())
            person.setName(name);

        if (!number.isEmpty())
            person.setNumber(number);

        if (!note.isEmpty())
            person.setNote(note);

        try {
            NotebookHandler notebookHandler = NotebookHandler.getInstance();
            notebookHandler.changePerson(person);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        updateTableView();

    }

    @FXML
    public void btnActionDelete(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(NotebookApplication.class.getResource("warningEmpty.fxml"));
        if (nbTableView.getSelectionModel().isEmpty()) {
            createModalStage("WarningEmpty", fxmlLoader);
            return;
        }

        TableView.TableViewSelectionModel<Person> selectionModel = nbTableView.getSelectionModel();
        Person person = selectionModel.getSelectedItem();

        NotebookHandler notebookHandler = null;
        try {
            notebookHandler = NotebookHandler.getInstance();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        notebookHandler.deletePerson(person.getId());
        System.out.println(person);
        updateTableView();
    }


    public void updateTableView() {
        NotebookHandler notebookHandler = null;
        try {
            notebookHandler = NotebookHandler.getInstance();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        ObservableList<Person> obList = FXCollections.observableList(notebookHandler.getAllPerson());

        labelCount.setText("Количество элементов: " + obList.size());

        nbTableView.setItems(obList);
        nbTableView.refresh();

    }

    private void createModalStage(String title, FXMLLoader fxmlLoader) throws IOException {
        Parent root = fxmlLoader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));

        stage.initModality(Modality.WINDOW_MODAL);

        stage.initOwner(this.nbTableView.getScene().getWindow());
        stage.setTitle(title);

        stage.setResizable(false);
        stage.showAndWait();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tblColumnName.setCellValueFactory(new PropertyValueFactory<Person, String>("name"));
        tblColumnNumber.setCellValueFactory(new PropertyValueFactory<Person, String>("number"));
        tblColumnNote.setCellValueFactory(new PropertyValueFactory<Person, String>("note"));

        updateTableView();
    }


}