module org.example.todolistjavafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;
    requires org.xerial.sqlitejdbc;
    requires java.sql;
    requires org.kordamp.bootstrapfx.core;

    opens org.example.todolistjavafx to javafx.fxml;
    exports org.example.todolistjavafx.model;
    exports org.example.todolistjavafx.controllers;
    opens org.example.todolistjavafx.controllers to javafx.fxml;
    exports org.example.todolistjavafx;

}