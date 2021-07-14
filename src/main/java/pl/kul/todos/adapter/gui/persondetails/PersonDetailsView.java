package pl.kul.todos.adapter.gui.persondetails;

public interface PersonDetailsView {
    void setPresenter(PersonDetailsPresenter presenter);

    void showPersonDetails(PersonDetailsDto personDetailsDto);
}