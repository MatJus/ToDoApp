package pl.kul.todos.adapter.database.deletetask;

import pl.kul.todos.adapter.database.DBLogicPresenter;
import java.util.UUID;

public interface DBDeleteTaskInterface {
    void setPresenter(DBLogicPresenter dbLogicPresenter);
    void deleteTask(UUID deleteUUID);
}
