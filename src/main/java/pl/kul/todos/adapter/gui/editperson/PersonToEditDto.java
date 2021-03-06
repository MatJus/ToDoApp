package pl.kul.todos.adapter.gui.editperson;

import java.util.UUID;

public class PersonToEditDto {

    private final UUID id;
    private final String name;
    private final String surname;

    public PersonToEditDto(UUID id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }
}
