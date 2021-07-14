package pl.kul.todos.adapter.gui.login;

import pl.kul.todos.adapter.database.DBLogIn;
import pl.kul.todos.adapter.gui.mainwindow.MainWindowPresenter;

public class LogInPresenter {

    private final LogInView logInView;
    private final MainWindowPresenter mainWindowPresenter;

    public LogInPresenter(LogInView logInView,
                          MainWindowPresenter mainWindowPresenter) {
        this.logInView = logInView;
        this.mainWindowPresenter = mainWindowPresenter;
    }

    public void showLogIn() {
        logInView.showLogInWindow();
    }

    public void closeApplication() {
        mainWindowPresenter.closeApplication();
    }

    public void logIn(PersonToLogIn personToLogIn) {
        mainWindowPresenter.tryLogIn(personToLogIn);
    }
}