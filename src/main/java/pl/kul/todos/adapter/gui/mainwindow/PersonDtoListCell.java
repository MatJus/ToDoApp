package pl.kul.todos.adapter.gui.mainwindow;

import javafx.geometry.HPos;
import javafx.scene.control.ListCell;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.util.function.Consumer;

public class PersonDtoListCell extends ListCell<PersonDto> {
    private final Consumer<PersonDto> onPersonClicked;


    public PersonDtoListCell(Consumer<PersonDto> onPersonClicked) {
        this.onPersonClicked = onPersonClicked;
    }

    @Override
    protected void updateItem(PersonDto person, boolean empty) {
        super.updateItem(person, empty);

        if (empty || person == null) {
            setText(null);
            setGraphic(null);
        } else {
            Text personName = new Text(person.getName() + " " + person.getLastName());
            if(person.isLogged())
                personName.setFill(Color.DARKGREEN);
            else
                personName.setFill(Color.RED);
            personName.setStrikethrough(!person.isLogged());
            personName.setOnMouseClicked(event -> {
                if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2) {
                    onPersonClicked.accept(person);
                }
            });

            GridPane itemGrid = new GridPane();
            GridPane.setHgrow(itemGrid, Priority.ALWAYS);
            itemGrid.addRow(
                    itemGrid.getRowCount(),
                    personName
            );

            ColumnConstraints titleColumn = new ColumnConstraints();
            titleColumn.setHgrow(Priority.ALWAYS);
            titleColumn.setHalignment(HPos.LEFT);

            ColumnConstraints buttonsColumn = new ColumnConstraints();
            buttonsColumn.setHalignment(HPos.RIGHT);

            itemGrid.getColumnConstraints()
                    .addAll(titleColumn, buttonsColumn);

            setGraphic(itemGrid);
        }
    }

}