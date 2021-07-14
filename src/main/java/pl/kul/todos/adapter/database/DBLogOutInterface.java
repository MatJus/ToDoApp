package pl.kul.todos.adapter.database;

public interface DBLogOutInterface {
    void setPresenter(DBLogicPresenter dbLogicPresenter);
    void tryLogOut();
}
