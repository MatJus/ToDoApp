package pl.kul.todos.adapter.gui.mainwindow;

import java.util.UUID;

public class PersonDto {

    private final UUID id;
    private final String name;
    private final String lastName;
    private final boolean status;

    public PersonDto(String name, String lastName) {
        this(UUID.randomUUID(), name, lastName, true);
    }

    public PersonDto(UUID id, String name, String lastName, boolean status) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.status = status;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public boolean isLogged() {
        return status;
    }
}
