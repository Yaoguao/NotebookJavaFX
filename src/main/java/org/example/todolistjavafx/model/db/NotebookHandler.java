package org.example.todolistjavafx.model.db;

import org.example.todolistjavafx.model.Person;
import org.sqlite.JDBC;

import java.sql.*;
import java.util.*;

public class NotebookHandler {

    // Константа, в которой хранится адрес подключения
    private static final String CON_STR = "jdbc:sqlite:notebook.db";

    // Объект, в котором будет храниться соединение с БД
    private Connection connection;

    // Шаблон одиночка, чтобы не плодить множество
    // экземпляров класса DbHandler
    private static NotebookHandler instance = null;

    private NotebookHandler() throws SQLException {
            DriverManager.registerDriver(new JDBC());
            this.connection = DriverManager.getConnection(CON_STR);
    }

    public static synchronized NotebookHandler getInstance() throws SQLException {
        if (instance == null) {
            instance = new NotebookHandler();
        }
        return instance;
    }

    public List<Person> getAllPerson() {
        try (Statement statement = this.connection.createStatement()) {
            List<Person> persons = new ArrayList<>();

            ResultSet resultSet = statement.executeQuery("SELECT id, name, number, note FROM person");

            while (resultSet.next()) {
                persons.add(new Person(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("number"),
                        resultSet.getString("note")
                ));
            }
            return persons;
        } catch (SQLException e) {
            e.printStackTrace();

            return Collections.emptyList();
        }
    }

    public void addPerson(Person person) {
        try (PreparedStatement statement = this.connection
                .prepareStatement("INSERT INTO person (name, number, note) " + "VALUES (?,?,?)")) {

            statement.setObject(1, person.getName());
            statement.setObject(2, person.getNumber());
            statement.setObject(3, person.getNote());

            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletePerson(int id) {
        try (PreparedStatement statement = this.connection
                .prepareStatement("DELETE FROM person WHERE id = ?")) {

            statement.setObject(1, id);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void changePerson(Person person){
        try (PreparedStatement statement = this.connection
                .prepareStatement("UPDATE person SET name = ?, number = ?, note = ? WHERE id = ?")) {

            statement.setObject(1, person.getName());
            statement.setObject(2, person.getNumber());
            statement.setObject(3, person.getNote());
            statement.setObject(4, person.getId());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
