package pl.kul.todos.adapter.gui.persondetails;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

class FxPersonDetailsView implements PersonDetailsView{
    private PersonDetailsPresenter presenter;

    @Override
    public void setPresenter(PersonDetailsPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showPersonDetails(PersonDetailsDto personDetailsDto) {
        ColumnConstraints leftColumn = new ColumnConstraints();
        leftColumn.setMinWidth(ColumnConstraints.CONSTRAIN_TO_PREF);
        leftColumn.setHalignment(HPos.RIGHT);

        ColumnConstraints rightColumn = new ColumnConstraints();
        rightColumn.setHgrow(Priority.ALWAYS);

        GridPane content = new GridPane();
        content.setMinWidth(450);
        content.getColumnConstraints().addAll(leftColumn, rightColumn);
        content.setPadding(new Insets(5));
        content.setHgap(5);
        content.setVgap(5);
        content.addRow(0, new Label("Imię:"), new Label(personDetailsDto.getName()));
        content.addRow(1, new Label("Nazwisko:"), new Label(personDetailsDto.getSurname()));
        content.addRow(2, new Label("Status:"), new Label(personDetailsDto.getStatus()));

        Dialog<PersonDetailsDto> itemDialog = new Dialog<>();
        itemDialog.setTitle("Szczegóły Pracownika");
        itemDialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        itemDialog.getDialogPane().setContent(content);

        itemDialog.show();
    }
}