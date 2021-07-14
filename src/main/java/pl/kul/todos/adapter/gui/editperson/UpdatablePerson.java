package pl.kul.todos.adapter.gui.editperson;

import pl.kul.todos.adapter.gui.mainwindow.PersonDto;

public interface UpdatablePerson {
    void updatePerson(PersonDto original, PersonDto updated);
}
