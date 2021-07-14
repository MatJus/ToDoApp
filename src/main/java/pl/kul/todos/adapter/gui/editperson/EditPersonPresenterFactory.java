package pl.kul.todos.adapter.gui.editperson;

import pl.kul.todos.adapter.gui.mainwindow.MainWindowPresenter;

public class EditPersonPresenterFactory {
    public EditPersonPresenter create(MainWindowPresenter mainWindowPresenter, PersonToEditDto itemToEditDto) {
        EditPersonView editPersonView = new FxEditPersonView();
        EditPersonPresenter presenter = new EditPersonPresenter(mainWindowPresenter, itemToEditDto, editPersonView);

        editPersonView.setPresenter(presenter);

        return presenter;
    }
}
