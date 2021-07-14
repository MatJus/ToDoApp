package pl.kul.todos.adapter.gui.persondetails;

import pl.kul.todos.adapter.gui.mainwindow.PersonDto;

public class PersonDetailsPresenter {
    private final PersonDetailsView personDetailsView;

    public PersonDetailsPresenter(PersonDetailsView personDetailsView) {
        this.personDetailsView = personDetailsView;
    }

    public void showItemDetails(PersonDto personDto) {
        personDetailsView.showPersonDetails(new PersonDetailsDto(
                personDto.getName(),
                personDto.getLastName(),
                personDto.isLogged() ? "Zalogowany" : "Wylogowany"
        ));
    }
}