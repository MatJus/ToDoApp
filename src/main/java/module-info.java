module todo.app.main {
        requires javafx.controls;
        requires com.rometools.rome;
        requires mysql.connector.java;
        requires java.sql;
        requires mongo.java.driver;

        opens pl.kul.todos.adapter.gui to javafx.graphics;
        }