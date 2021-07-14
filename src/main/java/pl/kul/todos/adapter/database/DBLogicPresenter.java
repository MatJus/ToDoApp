package pl.kul.todos.adapter.database;

import javafx.application.Platform;
import pl.kul.todos.adapter.database.addTask.DBAddTaskInterface;
import pl.kul.todos.adapter.database.deletetask.DBDeleteTaskInterface;
import pl.kul.todos.adapter.database.finishtask.DBFinishTaskInterface;
import pl.kul.todos.adapter.database.gettasks.DBGetTasksInterface;
import pl.kul.todos.adapter.database.updatetask.DBUpdateTaskInterface;
import pl.kul.todos.adapter.gui.edititem.UpdateItemDto;
import pl.kul.todos.adapter.gui.editperson.UpdatePersonDto;
import pl.kul.todos.adapter.gui.itemcompleted.ItemCompletedDto;
import pl.kul.todos.adapter.gui.mainwindow.ItemDto;
import pl.kul.todos.adapter.gui.mainwindow.MainWindowPresenter;

import java.util.Set;
import java.util.UUID;

public class DBLogicPresenter {
    private final MainWindowPresenter mainWindowPresenter;
    private final DBPersonThreadInterface dbPersonThread;
    private final DBLogInInterface dbLogIn;
    private final DBLogOutInterface dbLogOut;
    private final DBAddTaskInterface dbAddTask;
    private final DBDeleteTaskInterface dbDeleteTask;
    private final DBUpdateTaskInterface dbUpdateTask;
    private final DBFinishTaskInterface dbFinishTask;
    private final DBGetTasksInterface dbGetTasksInterface;

    public DBLogicPresenter(MainWindowPresenter mainWindowPresenter,
                            DBPersonThreadInterface dbThread,
                            DBLogInInterface dbLogIn,
                            DBLogOutInterface dbLogOut,
                            DBAddTaskInterface dbAddTask,
                            DBDeleteTaskInterface dbDeleteTask,
                            DBUpdateTaskInterface dbUpdateTask,
                            DBFinishTaskInterface dbFinishTask,
                            DBGetTasksInterface dbGetTasksInterface) {
        this.mainWindowPresenter = mainWindowPresenter;
        this.dbPersonThread = dbThread;
        this.dbLogIn = dbLogIn;
        this.dbLogOut = dbLogOut;
        this.dbAddTask = dbAddTask;
        this.dbDeleteTask = dbDeleteTask;
        this.dbUpdateTask = dbUpdateTask;
        this.dbFinishTask = dbFinishTask;
        this.dbGetTasksInterface = dbGetTasksInterface;
    }

    public void runThread() {
        dbPersonThread.runPersonThread();
    }

    public void tryLogIn(String login, String pass) {
        dbLogIn.tryLogIn(login, pass);
    }

    public void tryLogOut() {
        dbLogOut.tryLogOut();
    }

    public void wrongLogInDetails() {
        mainWindowPresenter.logIn();
    }

    public void updatePerosnList(UUID uuid, UpdatePersonDto updatePersonDto) {
        Platform.runLater(() ->mainWindowPresenter.updatePersonAndStatus(uuid, updatePersonDto));
    }

    public void logInSuccess() {
        mainWindowPresenter.dbThread();
    }

    public void addTask(ItemDto itemDto) {
        dbAddTask.addTask(itemDto);
    }

    public void deleteTask(Set<UUID> selectedItemDtos) {
        selectedItemDtos.forEach(id -> dbDeleteTask.deleteTask(id));
    }

    public void updateTask(UUID itemToEditDtoUUID, UpdateItemDto updateItemDto) {
        dbUpdateTask.updateTask(itemToEditDtoUUID, updateItemDto);
    }

    public void finishTask(UUID itemToFinishUUID, String summary) {
        dbFinishTask.finishTask(itemToFinishUUID, summary);
    }

    public void setTasks(ItemDto itemDto) {
        mainWindowPresenter.addNewItemOnlyList(itemDto);
    }

    public void getTasks() {
        dbGetTasksInterface.getTasks();
    }
}
