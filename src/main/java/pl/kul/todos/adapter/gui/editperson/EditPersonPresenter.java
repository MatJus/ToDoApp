package pl.kul.todos.adapter.gui.editperson;

import pl.kul.todos.adapter.gui.mainwindow.MainWindowPresenter;

public class EditPersonPresenter {
    private final EditPersonView editPersonView;
    private final MainWindowPresenter mainWindowPresenter;
    private final PersonToEditDto personToEditDto;

    public EditPersonPresenter(MainWindowPresenter mainWindowPresenter, PersonToEditDto personToEditDto, EditPersonView editPersonView) {
        this.mainWindowPresenter = mainWindowPresenter;
        this.personToEditDto = personToEditDto;
        this.editPersonView = editPersonView;
    }

    public void showEditItem() {
        editPersonView.showEditPerson(personToEditDto);
    }

    public void updatePersonDto(UpdatePersonDto updatePersonDto) {
        mainWindowPresenter.updatePerson(personToEditDto.getId(), updatePersonDto);
    }
}
