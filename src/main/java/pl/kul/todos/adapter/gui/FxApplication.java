package pl.kul.todos.adapter.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import pl.kul.todos.adapter.database.DBLogicPresenterFactory;
import pl.kul.todos.adapter.gui.edititem.EditItemPresenterFactory;
import pl.kul.todos.adapter.gui.editperson.EditPersonPresenterFactory;
import pl.kul.todos.adapter.gui.importnewitem.ImportNewItemPresenterFactory;
import pl.kul.todos.adapter.gui.itemcompleted.ItemCompletedPresenterFactory;
import pl.kul.todos.adapter.gui.itemdetails.ItemDetailsPresenterFactory;
import pl.kul.todos.adapter.gui.login.LogInPresenterFactory;
import pl.kul.todos.adapter.gui.mainwindow.*;
import pl.kul.todos.adapter.gui.newitem.NewItemPresenterFactory;
import pl.kul.todos.adapter.gui.persondetails.PersonDetailsPresenterFactory;
import pl.kul.todos.adapter.parser.FileParserFactory;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class FxApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane rootLayout = new VBox();
        Scene scene = new Scene(rootLayout);

        List<ItemDto> items = new LinkedList<>();

        List<PersonDto> persons = new LinkedList<>(List.of(
                new PersonDto(UUID.fromString("38400000-8cf0-11bd-b23e-10b96e4ef00d"), "", "", false),
                new PersonDto(UUID.fromString("f134c275-9fbd-48e6-b5fc-7d544096db6a"), "", "", false),
                new PersonDto(UUID.fromString("402e5f88-6963-4402-b03a-b1b3d8587f67"), "", "", false)
        ));

        // Dependencies
        FileParserFactory fileParserFactory = new FileParserFactory();

        // Model
        Todos todos = new Todos(items);
        Logged logged = new Logged(persons);

        // Presenters & views
        MainWindowPresenterFactory mainWindowPresenterFactory = new MainWindowPresenterFactory(
                rootLayout,
                new NewItemPresenterFactory(),
                new EditItemPresenterFactory(),
                new ImportNewItemPresenterFactory(fileParserFactory),
                new ItemCompletedPresenterFactory(),
                new ItemDetailsPresenterFactory(getHostServices()),
                new EditPersonPresenterFactory(),
                new PersonDetailsPresenterFactory(),
                new LogInPresenterFactory(),
                new DBLogicPresenterFactory()
        );

        mainWindowPresenterFactory.create(todos, logged)
                .logIn();

        primaryStage.setTitle("Edytor zadań");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
