package pl.kul.todos.adapter.gui.editperson;

public interface EditPersonView {
    void setPresenter(EditPersonPresenter editPersonPresenter);

    void showEditPerson(PersonToEditDto person);
}
