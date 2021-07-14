package pl.kul.todos.adapter.gui.login;

import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

public class FxLogInView implements LogInView {

    private LogInPresenter presenter;

    @Override
    public void setPresenter(LogInPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showLogInWindow() {
        TextField loginTextField = new TextField();
        TextField passTextField = new TextField();

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
        content.addRow(0, new Label("Login:"), loginTextField);
        content.addRow(1, new Label("Hasło:"), passTextField);

        Dialog<PersonToLogIn> personToLogInDialog = new Dialog<>();
        personToLogInDialog.setTitle("Panel logowania");
        personToLogInDialog.getDialogPane().setContent(content);
        personToLogInDialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
        personToLogInDialog.setResultConverter(buttonPressed -> buttonPressed == ButtonType.OK
                ? new PersonToLogIn(loginTextField.getText(), passTextField.getText())
                : null);

        personToLogInDialog.getDialogPane().lookupButton(ButtonType.OK).addEventFilter(ActionEvent.ACTION, event -> {
            String errorMessage = null;

            if (loginTextField.getText() == null || loginTextField.getText().isBlank()) {
                errorMessage = "Login nie może być pusty";
            }

            if (passTextField.getText() == null || passTextField.getText().isBlank()) {
                errorMessage = "Hasło nie może być puste";
            }

            if (errorMessage != null) {
                event.consume();

                new Alert(Alert.AlertType.ERROR, errorMessage, ButtonType.OK)
                        .showAndWait();
            }
        });

        personToLogInDialog.getDialogPane().lookupButton(ButtonType.CANCEL).addEventFilter(ActionEvent.ACTION, event -> {
            presenter.closeApplication();
        });

        personToLogInDialog.showAndWait()
                .ifPresent(personToLogIn -> presenter.logIn(personToLogIn));

    }
}
