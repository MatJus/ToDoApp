package pl.kul.todos.adapter.database.gettasks;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import pl.kul.todos.adapter.database.DBConnection;
import pl.kul.todos.adapter.database.DBLogicPresenter;
import pl.kul.todos.adapter.database.finishtask.DBFinishTaskInterface;
import pl.kul.todos.adapter.gui.editperson.UpdatePersonDto;
import pl.kul.todos.adapter.gui.mainwindow.ItemDto;
import pl.kul.todos.adapter.gui.newitem.NewItemDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class DBGetTasks implements DBGetTasksInterface {

    private DBLogicPresenter dbLogicPresenter;

    @Override
    public void setPresenter(DBLogicPresenter dbLogicPresenter) {
        this.dbLogicPresenter = dbLogicPresenter;
    }

    @Override
    public void getTasks() {
        /*
        Connection dbConnection = null;
        try {
            dbConnection = DBConnection.getConnection();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (
                SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Nie można połączyć się z bazą danych", ButtonType.OK)
                    .showAndWait();
            System.exit(1);
        }

        PreparedStatement pstmt = null;
        String query = null;
        ResultSet rs = null;

        try {
            query = "SELECT uuid,  title, describe, status, summary "
                    + "FROM tasks ";
            pstmt = dbConnection.prepareStatement(query);
            rs = pstmt.executeQuery();

            if (!rs.next()) {
                pstmt.close();
                dbConnection.close();
                new Alert(Alert.AlertType.ERROR, "Błędne dane logowania", ButtonType.OK)
                        .showAndWait();
            } else {
                String status = rs.getString("status");
                boolean stat;
                if(status.equals("ukonczony"))
                    stat = true;
                else
                    stat = false;
                ItemDto itemDto = new ItemDto(UUID.fromString(rs.getString("uuid")), rs.getString("title"), rs.getString("describe"), stat, rs.getString("summary"));
                dbLogicPresenter.setTasks(itemDto);
                while(rs.next()) {
                    status = rs.getString("status");
                    if(status.equals("ukonczony"))
                        stat = true;
                    else
                        stat = false;
                    itemDto = new ItemDto(UUID.fromString(rs.getString("uuid")), rs.getString("title"), rs.getString("describe"), stat, rs.getString("summary"));
                    dbLogicPresenter.setTasks(itemDto);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Nie można połączyć się z bazą danych", ButtonType.OK)
                    .showAndWait();
            System.exit(1);
        }

         */
    }
}
