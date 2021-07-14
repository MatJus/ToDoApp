package pl.kul.todos.adapter.gui.mainwindow;

import java.util.Comparator;

class SignedOffLastComparator implements Comparator<PersonDto> {
    @Override
    public int compare(PersonDto o1, PersonDto o2) {
        if (o1.isLogged()) {
            return o2.isLogged() ? 0 : 1;
        } else {
            return o2.isLogged() ? -1 : 0;
        }
    }
}