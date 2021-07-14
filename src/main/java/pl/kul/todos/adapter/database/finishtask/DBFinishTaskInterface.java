package pl.kul.todos.adapter.database.finishtask;

import pl.kul.todos.adapter.database.DBLogicPresenter;
import pl.kul.todos.adapter.gui.itemcompleted.ItemCompletedDto;

import java.util.UUID;

public interface DBFinishTaskInterface {
    void setPresenter(DBLogicPresenter dbLogicPresenter);
    void finishTask(UUID itemToEditDtoUUID, String summary);
}
