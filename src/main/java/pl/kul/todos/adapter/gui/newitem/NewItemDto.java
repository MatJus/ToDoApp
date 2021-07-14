package pl.kul.todos.adapter.gui.newitem;

import pl.kul.todos.adapter.gui.mainwindow.ItemDto;

public class NewItemDto {

    private final String title;
    private final String description;

    public NewItemDto(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public ItemDto toItem() {
        return new ItemDto(title, description);
    }
}
