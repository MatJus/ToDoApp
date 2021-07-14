package pl.kul.todos.adapter.database.addTask;

import pl.kul.todos.adapter.database.DBLogicPresenter;
import pl.kul.todos.adapter.gui.mainwindow.ItemDto;

public interface DBAddTaskInterface {
    void setPresenter(DBLogicPresenter dbLogicPresenter);
    void addTask(ItemDto itemDto);
}
