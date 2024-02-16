package org.example.todolistjavafx.interfaces;

import org.example.todolistjavafx.model.Person;

public interface INotebook {
    void add(Person person);

    void del(Person person);

    void change(Person person);
}
