package pl.kul.todos.adapter.gui.editperson;

import javafx.geometry.Insets;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class FxEditPersonView implements EditPersonView {

    private EditPersonPresenter editPersonPresenter;

    @Override
    public void setPresenter(EditPersonPresenter editPersonPresenter) {
        this.editPersonPresenter = editPersonPresenter;
    }

    @Override
    public void showEditPerson(PersonToEditDto person) {
        TextField personNameTextField = new TextField(person.getName());
        TextField personSurnameTextField = new TextField(person.getSurname());

        GridPane content = new GridPane();
        content.setPadding(new Insets(5));
        content.setHgap(5);
        content.setVgap(5);
        content.addRow(0, new Label("Imie:"), personNameTextField);
        content.addRow(1, new Label("Nazwisko:"), personSurnameTextField);

        Dialog<UpdatePersonDto> itemDialog = new Dialog<>();
        itemDialog.setTitle("Edycja Pracownika");
        itemDialog.getDialogPane().setContent(content);
        itemDialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
        itemDialog.setResultConverter(buttonPressed -> buttonPressed == ButtonType.OK
                ? new UpdatePersonDto(personNameTextField.getText(), personSurnameTextField.getText(), null)
                : null);

        itemDialog.showAndWait()
                .ifPresent(updateItemDto -> editPersonPresenter.updatePersonDto(updateItemDto));
    }
}
