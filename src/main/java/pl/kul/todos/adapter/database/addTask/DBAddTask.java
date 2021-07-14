package pl.kul.todos.adapter.database.addTask;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import pl.kul.todos.adapter.database.DBConnection;
import pl.kul.todos.adapter.database.DBLogIn;
import pl.kul.todos.adapter.database.DBLogicPresenter;
import pl.kul.todos.adapter.gui.mainwindow.ItemDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBAddTask implements DBAddTaskInterface {

    private DBLogicPresenter dbLogicPresenter;

    @Override
    public void setPresenter(DBLogicPresenter dbLogicPresenter) {
        this.dbLogicPresenter = dbLogicPresenter;
    }

    @Override
    public void addTask(ItemDto itemDto) {
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

        int id = DBLogIn.id;

        try {
            System.out.println(itemDto.getDescription());
            query = "INSERT INTO tasks (`uuid`, `id_employee`, `title`, `describe`, `status`, `summary`) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";
            pstmt = dbConnection.prepareStatement(query);
            pstmt.setString(1, itemDto.getId().toString());
            pstmt.setInt(2, id);
            pstmt.setString(3, itemDto.getName());
            pstmt.setString(4, itemDto.getDescription());
            pstmt.setString(5, "aktywny");
            pstmt.setString(6, "-");
            pstmt.executeUpdate();

            pstmt.close();
            dbConnection.close();

        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Nie można połączyć się z bazą danych", ButtonType.OK)
                    .showAndWait();
            System.exit(1);
        }

         */
    }

}
