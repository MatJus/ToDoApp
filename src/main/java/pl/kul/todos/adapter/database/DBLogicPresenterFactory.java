package pl.kul.todos.adapter.database;


import pl.kul.todos.adapter.database.addTask.DBAddTask;
import pl.kul.todos.adapter.database.addTask.DBAddTaskInterface;
import pl.kul.todos.adapter.database.deletetask.DBDeleteTask;
import pl.kul.todos.adapter.database.deletetask.DBDeleteTaskInterface;
import pl.kul.todos.adapter.database.finishtask.DBFinishTask;
import pl.kul.todos.adapter.database.finishtask.DBFinishTaskInterface;
import pl.kul.todos.adapter.database.gettasks.DBGetTasks;
import pl.kul.todos.adapter.database.gettasks.DBGetTasksInterface;
import pl.kul.todos.adapter.database.updatetask.DBUpdateTask;
import pl.kul.todos.adapter.database.updatetask.DBUpdateTaskInterface;
import pl.kul.todos.adapter.gui.mainwindow.MainWindowPresenter;

public class DBLogicPresenterFactory {
    public DBLogicPresenter create(MainWindowPresenter mainWindowPresenter) {
        DBPersonThreadInterface dbPersonThread = new DBPersonThread();
        DBLogInInterface dbLogIn = new DBLogIn();
        DBLogOutInterface dbLogOut = new DBLogOut();
        DBAddTaskInterface dbAddTask = new DBAddTask();
        DBDeleteTaskInterface dbDeleteTask = new DBDeleteTask();
        DBUpdateTaskInterface dbUpdateTask = new DBUpdateTask();
        DBFinishTaskInterface dbFinishTask = new DBFinishTask();
        DBGetTasksInterface dbGetTasks = new DBGetTasks();

        DBLogicPresenter dbPersonPresenter = new DBLogicPresenter(mainWindowPresenter, dbPersonThread, dbLogIn, dbLogOut, dbAddTask, dbDeleteTask, dbUpdateTask, dbFinishTask, dbGetTasks);

        dbPersonThread.setPresenter(dbPersonPresenter);
        dbLogIn.setPresenter(dbPersonPresenter);

        return dbPersonPresenter;
    }
}
