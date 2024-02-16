package org.example.todolistjavafx.model;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Getter;
import org.example.todolistjavafx.interfaces.INotebook;

import java.util.ArrayList;

public class PersonList implements INotebook {

    private ArrayList<Person> persons = new ArrayList<>();

    @Getter
    private ObservableList<Person> obList = FXCollections.observableList(persons);

    public void fillList(){
//        persons.add(new Person("Vasa","Papich", "Zeln"));
//        persons.add(new Person("Peta","jphone", "GAY"));
//        persons.add(new Person("Sergay","Jhon", "Sema"));
//        persons.add(new Person("VaDicksa","Alx", "alaa"));
//        persons.add(new Person("Russkiisa","Leha", "chebureki"));
    }
    @Override
    public void add(Person person) {
        persons.add(person);
    }

    @Override
    public void del(Person person) {
        persons.remove(person);
    }

    @Override
    public void change(Person person) {

    }
}
