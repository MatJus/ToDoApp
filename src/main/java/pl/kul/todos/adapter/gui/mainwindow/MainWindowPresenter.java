package pl.kul.todos.adapter.gui.mainwindow;

import javafx.application.Platform;
import pl.kul.todos.adapter.database.DBLogicPresenterFactory;
import pl.kul.todos.adapter.database.DBPersonThread;
import pl.kul.todos.adapter.gui.edititem.EditItemPresenterFactory;
import pl.kul.todos.adapter.gui.edititem.ItemToEditDto;
import pl.kul.todos.adapter.gui.edititem.UpdateItemDto;
import pl.kul.todos.adapter.gui.editperson.EditPersonPresenterFactory;
import pl.kul.todos.adapter.gui.editperson.PersonToEditDto;
import pl.kul.todos.adapter.gui.editperson.UpdatePersonDto;
import pl.kul.todos.adapter.gui.importnewitem.ImportNewItemPresenter;
import pl.kul.todos.adapter.gui.importnewitem.ImportNewItemPresenterFactory;
import pl.kul.todos.adapter.gui.itemcompleted.ItemCompletedPresenterFactory;
import pl.kul.todos.adapter.gui.itemdetails.ItemDetailsPresenterFactory;
import pl.kul.todos.adapter.gui.login.LogInPresenterFactory;
import pl.kul.todos.adapter.gui.login.PersonToLogIn;
import pl.kul.todos.adapter.gui.newitem.NewItemPresenterFactory;
import pl.kul.todos.adapter.gui.persondetails.PersonDetailsPresenterFactory;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class MainWindowPresenter {
    private final Todos todos;
    private final Logged logged;
    private final MainWindowView mainWindowView;
    private final NewItemPresenterFactory newItemPresenterFactory;
    private final EditItemPresenterFactory editItemPresenterFactory;
    private final ImportNewItemPresenterFactory importNewItemPresenterFactory;
    private final ItemCompletedPresenterFactory itemCompletedPresenterFactory;
    private final ItemDetailsPresenterFactory itemDetailsPresenterFactory;
    private final EditPersonPresenterFactory editPersonPresenterFactory;
    private final PersonDetailsPresenterFactory personDetailsPresenterFactory;
    private final LogInPresenterFactory logInPresenterFactory;
    private final DBLogicPresenterFactory dbLogicPresenterFactory;

    public MainWindowPresenter(
            Todos todos,
            Logged logged,
            MainWindowView mainWindowView,
            NewItemPresenterFactory newItemPresenterFactory,
            EditItemPresenterFactory editItemPresenterFactory,
            ImportNewItemPresenterFactory importNewItemPresenterFactory,
            ItemCompletedPresenterFactory itemCompletedPresenterFactory,
            ItemDetailsPresenterFactory itemDetailsPresenterFactory,
            EditPersonPresenterFactory editPersonPresenterFactory,
            PersonDetailsPresenterFactory personDetailsPresenterFactory,
            LogInPresenterFactory logInPresenterFactory,
            DBLogicPresenterFactory dbLogicPresenterFactory) {
        this.todos = todos;
        this.logged = logged;
        this.mainWindowView = mainWindowView;
        this.newItemPresenterFactory = newItemPresenterFactory;
        this.editItemPresenterFactory = editItemPresenterFactory;
        this.importNewItemPresenterFactory = importNewItemPresenterFactory;
        this.itemCompletedPresenterFactory = itemCompletedPresenterFactory;
        this.itemDetailsPresenterFactory = itemDetailsPresenterFactory;
        this.editPersonPresenterFactory = editPersonPresenterFactory;
        this.personDetailsPresenterFactory = personDetailsPresenterFactory;
        this.logInPresenterFactory = logInPresenterFactory;
        this.dbLogicPresenterFactory = dbLogicPresenterFactory;

        mainWindowView.displayTodoItems(todos.getItems());
        mainWindowView.displayLoggedPersons(logged.getPersons());
    }

    public void addNewItem() {
        newItemPresenterFactory.create(this)
                .showNewItemCreator();
    }

    public void addNewItem(ItemDto newItem) {
        dbLogicPresenterFactory.create(this).addTask(newItem);
        todos.addItem(newItem);
        displayTodoItems(todos.getItems());
    }

    public void addNewItemOnlyList(ItemDto newItem) {
        todos.addItem(newItem);
        displayTodoItems(todos.getItems());
    }

    public void editSelectedItem(ItemDto selectedItemDto) {
        ItemToEditDto itemToEditDto = new ItemToEditDto(selectedItemDto.getId(), selectedItemDto.getName());

        editItemPresenterFactory.create(this, itemToEditDto)
                .showEditItem();
    }

    public void removeSelectedItems(Set<UUID> selectedItemDtos) {
        dbLogicPresenterFactory.create(this).deleteTask(selectedItemDtos);
        todos.removeItems(selectedItemDtos);
        displayTodoItems(todos.getItems());
    }

    public void updateItem(UUID id, UpdateItemDto updated) {
        dbLogicPresenterFactory.create(this).updateTask(id, updated);
        todos.updateItem(id, updated);
        displayTodoItems(todos.getItems());
    }

    public void importItems(String sourceType) {
        ImportNewItemPresenter importNewItemPresenter = importNewItemPresenterFactory.create(this, sourceType);
        importNewItemPresenter.showImporter();
    }

    public void markDone(ItemDto itemDto) {
        itemCompletedPresenterFactory.create(this)
                .showItemCompletedCreator(itemDto);
    }

    public void markDone(UUID id, String summary) {
        dbLogicPresenterFactory.create(this).finishTask(id, summary);
        todos.markAsDone(id, summary);
        displayTodoItems(todos.getItems());
    }

    public void displayTodoItems(List<ItemDto> items) {
        List<ItemDto> sortedItems = items.stream()
                .sorted(new DoneItemsLastComparator())
                .collect(Collectors.toList());

        mainWindowView.displayTodoItems(sortedItems);
    }

    public void showDetails(UUID id) {
        itemDetailsPresenterFactory.create(this)
                .showItemDetails(todos.getItem(id));
    }

    public void addNewItem(List<ItemDto> items) {
        todos.addItems(items);
        displayTodoItems(todos.getItems());
    }

    public void showDetailsPerson(UUID id) {
        personDetailsPresenterFactory.create(this)
                .showItemDetails(logged.getPerson(id));
    }

    public void editSelectedPerson(PersonDto selectedPersonDto) {
        PersonToEditDto personToEditDto = new PersonToEditDto(selectedPersonDto.getId(), selectedPersonDto.getName(), selectedPersonDto.getLastName());
        editPersonPresenterFactory.create(this, personToEditDto)
                .showEditItem();
    }

    public void updatePerson(UUID id, UpdatePersonDto updated) {
        logged.updatePerson(id, updated);
        displayLoggedPersons(logged.getPersons());
    }

    public void updatePersonAndStatus(UUID id, UpdatePersonDto updated) {
        logged.updatePersonAndStatus(id, updated);
        displayLoggedPersons(logged.getPersons());
    }

    public void displayLoggedPersons(List<PersonDto> persons) {
        List<PersonDto> sortedPersons = persons.stream()
                .sorted(new SignedOffLastComparator())
                .collect(Collectors.toList());

        mainWindowView.displayLoggedPersons(sortedPersons);
    }

    public void closeApplication() {
        DBPersonThread.running = false;
        dbLogicPresenterFactory.create(this)
                .tryLogOut();

        Platform.exit();
    }

    public void logIn() {
        dbLogicPresenterFactory.create(this).tryLogOut();
        logInPresenterFactory.create(this)
                .showLogIn();
    }

    public void dbThread() {
        dbLogicPresenterFactory.create(this)
                .runThread();
    }

    public void tryLogIn(PersonToLogIn personToLogIn) {
        dbLogicPresenterFactory.create(this)
                .tryLogIn(personToLogIn.getLogin(), personToLogIn.getPass());
    }
}
