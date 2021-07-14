package pl.kul.todos.adapter.gui.mainwindow;

import javafx.scene.layout.Pane;
import pl.kul.todos.adapter.database.DBLogicPresenterFactory;
import pl.kul.todos.adapter.gui.edititem.EditItemPresenterFactory;
import pl.kul.todos.adapter.gui.editperson.EditPersonPresenterFactory;
import pl.kul.todos.adapter.gui.importnewitem.ImportNewItemPresenterFactory;
import pl.kul.todos.adapter.gui.itemcompleted.ItemCompletedPresenterFactory;
import pl.kul.todos.adapter.gui.itemdetails.ItemDetailsPresenterFactory;
import pl.kul.todos.adapter.gui.login.LogInPresenterFactory;
import pl.kul.todos.adapter.gui.newitem.NewItemPresenterFactory;
import pl.kul.todos.adapter.gui.persondetails.PersonDetailsPresenterFactory;

public class MainWindowPresenterFactory {
    private final Pane rootLayout;
    private final NewItemPresenterFactory newItemPresenterFactory;
    private final EditItemPresenterFactory editItemPresenterFactory;
    private final ImportNewItemPresenterFactory importNewItemPresenterFactory;
    private final ItemCompletedPresenterFactory itemCompletedPresenterFactory;
    private final ItemDetailsPresenterFactory itemDetailsPresenterFactory;
    private final EditPersonPresenterFactory editPersonPresenterFactory;
    private final PersonDetailsPresenterFactory personDetailsPresenterFactory;
    private final LogInPresenterFactory logInPresenterFactory;
    private final DBLogicPresenterFactory dbLogicPresenterFactory;

    public MainWindowPresenterFactory(
            Pane rootLayout,
            NewItemPresenterFactory newItemPresenterFactory,
            EditItemPresenterFactory editItemPresenterFactory,
            ImportNewItemPresenterFactory importNewItemPresenterFactory,
            ItemCompletedPresenterFactory itemCompletedPresenterFactory,
            ItemDetailsPresenterFactory itemDetailsPresenterFactory,
            EditPersonPresenterFactory editPersonPresenterFactory,
            PersonDetailsPresenterFactory personDetailsPresenterFactory,
            LogInPresenterFactory logInPresenterFactory,
            DBLogicPresenterFactory dbLogicPresenterFactory) {
        this.rootLayout = rootLayout;
        this.newItemPresenterFactory = newItemPresenterFactory;
        this.editItemPresenterFactory = editItemPresenterFactory;
        this.importNewItemPresenterFactory = importNewItemPresenterFactory;
        this.itemCompletedPresenterFactory = itemCompletedPresenterFactory;
        this.itemDetailsPresenterFactory = itemDetailsPresenterFactory;
        this.editPersonPresenterFactory = editPersonPresenterFactory;
        this.personDetailsPresenterFactory = personDetailsPresenterFactory;
        this.logInPresenterFactory = logInPresenterFactory;
        this.dbLogicPresenterFactory = dbLogicPresenterFactory;
    }

    public MainWindowPresenter create(Todos todos, Logged logged) {
        MainWindowView mainWindowView = new FxMainWindowView(rootLayout);
        MainWindowPresenter presenter = new MainWindowPresenter(todos, logged, mainWindowView, newItemPresenterFactory, editItemPresenterFactory, importNewItemPresenterFactory, itemCompletedPresenterFactory, itemDetailsPresenterFactory, editPersonPresenterFactory, personDetailsPresenterFactory, logInPresenterFactory, dbLogicPresenterFactory);
        mainWindowView.setPresenter(presenter);

        return presenter;
    }
}
