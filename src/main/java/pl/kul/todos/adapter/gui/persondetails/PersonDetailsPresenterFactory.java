package pl.kul.todos.adapter.gui.persondetails;

import pl.kul.todos.adapter.gui.mainwindow.MainWindowPresenter;

public class PersonDetailsPresenterFactory {

    public PersonDetailsPresenter create(MainWindowPresenter mainWindowPresenter) {
        PersonDetailsView personDetailsView = new FxPersonDetailsView();
        PersonDetailsPresenter presenter = new PersonDetailsPresenter(personDetailsView);
        personDetailsView.setPresenter(presenter);

        return presenter;
    }
}