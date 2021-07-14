package pl.kul.todos.adapter.gui.newitem;

import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

class FxNewItemView implements NewItemView {
    private NewItemPresenter presenter;

    @Override
    public void setPresenter(NewItemPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showCreator() {
        TextField itemTitleTextField = new TextField();
        TextArea itemDescriptionTextArea = new TextArea();

        ColumnConstraints leftColumn = new ColumnConstraints();
        leftColumn.setMinWidth(ColumnConstraints.CONSTRAIN_TO_PREF);
        leftColumn.setHalignment(HPos.RIGHT);

        ColumnConstraints rightColumn = new ColumnConstraints();
        rightColumn.setHgrow(Priority.ALWAYS);

        GridPane content = new GridPane();
        content.getColumnConstraints().addAll(leftColumn, rightColumn);
        content.setPadding(new Insets(5));
        content.setHgap(5);
        content.setVgap(5);
        content.addRow(0, new Label("Tytuł:"), itemTitleTextField);
        content.addRow(1, new Label("Opis:"), itemDescriptionTextArea);

        Dialog<NewItemDto> itemDialog = new Dialog<>();
        itemDialog.setTitle("Kreator elementu");
        itemDialog.getDialogPane().setContent(content);
        itemDialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
        itemDialog.setResultConverter(buttonPressed -> buttonPressed == ButtonType.OK
                ? new NewItemDto(itemTitleTextField.getText(), itemDescriptionTextArea.getText())
                : null);

        itemDialog.getDialogPane().lookupButton(ButtonType.OK).addEventFilter(ActionEvent.ACTION, event -> {
            String errorMessage = null;

            if (itemTitleTextField.getText() == null || itemTitleTextField.getText().isBlank()) {
                errorMessage = "Tytuł nie może być pusty";
            }

            if (itemDescriptionTextArea.getText() == null || itemDescriptionTextArea.getText().isBlank()) {
                errorMessage = "Opis nie może być pusty";
            }

            if (errorMessage != null) {
                event.consume();

                new Alert(Alert.AlertType.ERROR, errorMessage, ButtonType.OK)
                        .showAndWait();
            }
        });

        itemDialog.showAndWait()
                .ifPresent(presenter::createNewItem);
    }
}