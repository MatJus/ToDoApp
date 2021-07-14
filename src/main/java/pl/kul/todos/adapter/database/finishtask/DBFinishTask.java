package pl.kul.todos.adapter.database.finishtask;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import pl.kul.todos.adapter.database.DBConnection;
import pl.kul.todos.adapter.database.DBLogicPresenter;
import pl.kul.todos.adapter.gui.itemcompleted.ItemCompletedDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class DBFinishTask implements DBFinishTaskInterface{

    private DBLogicPresenter dbLogicPresenter;

    @Override
    public void setPresenter(DBLogicPresenter dbLogicPresenter) {
        this.dbLogicPresenter = dbLogicPresenter;
    }

    @Override
    public void finishTask(UUID itemToFinishUUID, String summary) {
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
            query = "UPDATE tasks SET status = ?, summary = ? WHERE uuid = ? ";
            pstmt = dbConnection.prepareStatement(query);
            pstmt.setString(1, "ukonczony");
            pstmt.setString(2, summary);
            pstmt.setString(3, itemToFinishUUID.toString());
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
