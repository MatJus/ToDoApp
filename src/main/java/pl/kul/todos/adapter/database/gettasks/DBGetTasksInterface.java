package pl.kul.todos.adapter.database.gettasks;

import pl.kul.todos.adapter.database.DBLogicPresenter;

import java.util.UUID;

public interface DBGetTasksInterface {
    void setPresenter(DBLogicPresenter dbLogicPresenter);
    void getTasks();
}
