package org.example.todolistjavafx.model;


import lombok.*;

@Data
@AllArgsConstructor
@ToString
public class Person {

    private int id;

    private String name;

    private String number;

    private String note;

    public Person(String name, String number, String note) {
        this.name = name;
        this.number = number;
        this.note = note;
    }
}
