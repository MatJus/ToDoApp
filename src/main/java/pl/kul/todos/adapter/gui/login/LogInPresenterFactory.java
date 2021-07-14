package pl.kul.todos.adapter.gui.login;

import pl.kul.todos.adapter.gui.mainwindow.MainWindowPresenter;

public class LogInPresenterFactory {
    public LogInPresenter create(MainWindowPresenter mainWindowPresenter) {
        LogInView logInView = new FxLogInView();
        LogInPresenter presenter = new LogInPresenter(logInView, mainWindowPresenter);
        logInView.setPresenter(presenter);

        return presenter;
    }
}
