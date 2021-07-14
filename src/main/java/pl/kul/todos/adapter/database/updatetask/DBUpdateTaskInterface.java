package pl.kul.todos.adapter.database.updatetask;

import pl.kul.todos.adapter.database.DBLogicPresenter;
import pl.kul.todos.adapter.gui.edititem.UpdateItemDto;

import java.util.UUID;

public interface DBUpdateTaskInterface {
    void setPresenter(DBLogicPresenter dbLogicPresenter);
    void updateTask(UUID itemToEditDtoUUID, UpdateItemDto updateItemDto);
}
