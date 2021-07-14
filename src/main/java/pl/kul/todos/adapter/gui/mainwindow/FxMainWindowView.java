package pl.kul.todos.adapter.gui.mainwindow;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

class FxMainWindowView implements MainWindowView {
    private final ListView<ItemDto> itemListView;
    private ListView<PersonDto> personListView;

    private MainWindowPresenter presenter;

    public FxMainWindowView(Pane parent) {

        MenuBar menuBar = new MenuBar();
        Menu menuFile = new Menu("Plik");
        MenuItem closeMenuItem = new MenuItem("Zakończ");
        closeMenuItem.setOnAction(event -> presenter.closeApplication());
        menuFile.getItems().add(closeMenuItem);

        Menu menuHelp = new Menu("Pomoc");
        MenuItem aboutMenuItem = new MenuItem("O programie");

        String info = "Projek zaliczeniowy z przedmiotu praktyka programowania\n" +
                "Autorzy: Michał Kupisz, Mateusz Jusiak, Michał Lipski\n" +
                "Kul 2019";
        Alert a = new Alert(Alert.AlertType.INFORMATION, info, ButtonType.OK);
        a.setTitle("Inforamcje");
        a.setHeaderText("Informacje o programie");

        aboutMenuItem.setOnAction(event -> a.showAndWait());
        menuHelp.getItems().add(aboutMenuItem);

        menuBar.getMenus().addAll(menuFile, menuHelp);

        Label tasksToDoLabel = new Label("Zadania do wykonania");

        itemListView = new ListView<>();
        itemListView.setMinHeight(200);
        itemListView.setCellFactory(new Callback<ListView<ItemDto>, ListCell<ItemDto>>() {
            @Override
            public ListCell<ItemDto> call(ListView<ItemDto> param) {
                return new ItemDtoListCell(
                        itemDto -> presenter.markDone(itemDto),
                        clickedItemDto -> presenter.showDetails(clickedItemDto.getId())
                );
            }
        });

        MenuItem importFromFileMenuItem = new MenuItem("Z pliku");
        importFromFileMenuItem.setOnAction(event -> presenter.importItems("FILE"));

        MenuItem importFromRssMenuItem = new MenuItem("Z kanału RSS");
        importFromRssMenuItem.setOnAction(event -> presenter.importItems("RSS"));

        MenuButton importItemsButton = new MenuButton(
                "\uD83D\uDCC2",
                null,
                importFromFileMenuItem,
                importFromRssMenuItem
        );
        importItemsButton.setVisible(false);
        Button addItemButton = new Button("\u002b");
        Button editItemButton = new Button("\u270E");
        Button removeItemButton = new Button("\u2672");

        Label taskDescriptionLabel = new Label("Opis szczegółowy zadania");

        TextArea itemDescription = new TextArea();
        itemDescription.setEditable(false);
        itemDescription.setMinWidth(500);

        itemListView.getSelectionModel()
                .getSelectedItems()
                .addListener((ListChangeListener<ItemDto>) c -> {
                    ObservableList<ItemDto> selectedItemDtos = itemListView.getSelectionModel().getSelectedItems();

                    editItemButton.disableProperty().setValue(selectedItemDtos.size() != 1);
                    removeItemButton.disableProperty().setValue(selectedItemDtos.isEmpty());
                    if(selectedItemDtos.size() != 0)
                        itemDescription.setText(selectedItemDtos.get(0).getDescription());
                });

        editItemButton.setDisable(true);
        removeItemButton.setDisable(true);

        addItemButton.setOnAction(event -> presenter.addNewItem());
        editItemButton.setOnAction(event -> presenter.editSelectedItem(itemListView.getSelectionModel().getSelectedItem()));
        removeItemButton.setOnAction(event -> {
            Set<UUID> selectedItems = itemListView.getSelectionModel().getSelectedItems()
                    .stream()
                    .map(itemDto -> itemDto.getId())
                    .collect(Collectors.toSet());
            presenter.removeSelectedItems(selectedItems);
            itemDescription.setText("");
        });

        HBox actionButtons = new HBox();
        actionButtons.setPadding(new Insets(5));
        actionButtons.setSpacing(5);
        actionButtons.setAlignment(Pos.BASELINE_RIGHT);
        actionButtons.getChildren().addAll(
                importItemsButton,
                addItemButton,
                editItemButton,
                removeItemButton
        );

        Label onlinePersonsLabel = new Label("Zalogowani");

        personListView = new ListView<>();
        personListView.setMinHeight(200);
        personListView.setCellFactory(new Callback<ListView<PersonDto>, ListCell<PersonDto>>() {
            @Override
            public ListCell<PersonDto> call(ListView<PersonDto> param) {
                return new PersonDtoListCell(
                        clickedPersonDto -> presenter.showDetailsPerson(clickedPersonDto.getId())
                );
            }
        });

        Button editPersonButton = new Button("Edit");
        Button logOutButton = new Button("Wyloguj \u2B6E");
        Button closeButton = new Button("Zamknij ❌");

        personListView.getSelectionModel()
                .getSelectedItems()
                .addListener((ListChangeListener<PersonDto>) c -> {
                    ObservableList<PersonDto> selectedItemDtos = personListView.getSelectionModel().getSelectedItems();
                    editPersonButton.disableProperty().setValue(selectedItemDtos.size() != 1);
                });

        editPersonButton.setDisable(true);
        editPersonButton.setOnAction(event -> presenter.editSelectedPerson(personListView.getSelectionModel().getSelectedItem()));
        closeButton.setOnAction(event -> presenter.closeApplication());
        logOutButton.setOnAction(event -> presenter.logIn());

        HBox logOutButtons = new HBox();
        logOutButtons.setPadding(new Insets(5));
        logOutButtons.setSpacing(5);
        logOutButtons.setAlignment(Pos.BASELINE_RIGHT);
        logOutButtons.getChildren().addAll(
                editPersonButton,
                logOutButton,
                closeButton
        );

        VBox leftSidelayout = new VBox();
        VBox.setVgrow(leftSidelayout, Priority.SOMETIMES);
        leftSidelayout.setPadding(new Insets(5));
        leftSidelayout.setSpacing(5);
        leftSidelayout.getChildren().addAll(
                tasksToDoLabel,
                itemListView,
                actionButtons,
                onlinePersonsLabel,
                personListView,
                logOutButtons
        );

        VBox rightSidelayout = new VBox();
        VBox.setVgrow(rightSidelayout, Priority.SOMETIMES);
        rightSidelayout.setPadding(new Insets(5));
        rightSidelayout.setSpacing(5);
        rightSidelayout.getChildren().addAll(
                taskDescriptionLabel,
                itemDescription
        );

        HBox layout = new HBox();
        HBox.setHgrow(layout, Priority.ALWAYS);
        layout.setPadding(new Insets(5));
        layout.setSpacing(5);
        layout.getChildren().addAll(
                leftSidelayout,
                rightSidelayout
        );

        itemListView.prefHeightProperty()
                .bind(leftSidelayout.heightProperty().subtract(personListView.heightProperty()));
        itemDescription.prefWidthProperty()
                .bind(layout.widthProperty().subtract(leftSidelayout.widthProperty()));
        itemDescription.prefHeightProperty()
                .bind(layout.heightProperty());

        parent.getChildren().add(menuBar);
        parent.getChildren().add(layout);

    }

    @Override
    public void setPresenter(MainWindowPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void displayTodoItems(List<ItemDto> todos) {
        itemListView.setItems(FXCollections.observableList(todos));
    }

    @Override
    public void displayLoggedPersons(List<PersonDto> logged) {
        personListView.setItems(FXCollections.observableList(logged));
    }
}
