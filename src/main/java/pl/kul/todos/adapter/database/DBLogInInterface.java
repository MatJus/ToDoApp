package pl.kul.todos.adapter.database;

public interface DBLogInInterface {
    void setPresenter(DBLogicPresenter dbLogicPresenter);
    void tryLogIn(String login, String pass);
}
