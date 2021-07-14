package pl.kul.todos.adapter.database;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBLogOut implements DBLogOutInterface {

    private DBLogicPresenter dbLogicPresenter;

    @Override
    public void setPresenter(DBLogicPresenter dbLogicPresenter) {
        this.dbLogicPresenter = dbLogicPresenter;
    }

    @Override
    public void tryLogOut() {
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
            query = "UPDATE workers SET status = ? WHERE id = ? ";
            pstmt = dbConnection.prepareStatement(query);
            pstmt.setBoolean(1, false);
            pstmt.setInt(2, id);
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
