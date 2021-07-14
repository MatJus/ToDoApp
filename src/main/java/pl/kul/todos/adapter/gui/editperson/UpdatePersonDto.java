package pl.kul.todos.adapter.gui.editperson;

import java.util.UUID;

public class UpdatePersonDto {
    private final String name;
    private final String lastName;
    private final Boolean status;

    public UpdatePersonDto(String name, String lastName, Boolean status){
        this.name = name;
        this.lastName = lastName;
        this.status = status;
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
