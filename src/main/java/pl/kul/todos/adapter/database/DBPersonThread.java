package pl.kul.todos.adapter.database;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import pl.kul.todos.adapter.gui.editperson.UpdatePersonDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class DBPersonThread implements DBPersonThreadInterface {
    private Thread t;
    private PreparedStatement pstmt;
    private String query;
    private ResultSet rs;
    private Connection dbConnection;
    public static Boolean running;

    private DBLogicPresenter dbLogicPresenter;

    @Override
    public void setPresenter(DBLogicPresenter dbLogicPresenter) {
        this.dbLogicPresenter = dbLogicPresenter;
    }

    @Override
    public void runPersonThread() {
/*
        running = true;
        dbConnection = null;
        try {
            dbConnection = DBConnection.getConnection();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Nie można połączyć się z bazą danych", ButtonType.OK)
                    .showAndWait();
            System.exit(1);
        }

        pstmt = null;
        query = null;
        rs = null;

        t = new Thread(() -> {
            while(running) {
                try {
                    query = "SELECT id, uuid, name, last_name, status "
                            + "FROM workers ";
                    pstmt = dbConnection.prepareStatement(query);
                    rs = pstmt.executeQuery();

                    if (!rs.next()) {
                        pstmt.close();
                        dbConnection.close();
                        new Alert(Alert.AlertType.ERROR, "Błędne dane logowania", ButtonType.OK)
                                .showAndWait();
                    } else {
                        UpdatePersonDto updatePersonDto = new UpdatePersonDto(rs.getString("name"), rs.getString("last_name"), rs.getBoolean("status"));
                        dbLogicPresenter.updatePerosnList(UUID.fromString(rs.getString("uuid")), updatePersonDto);
                        while(rs.next()) {
                            updatePersonDto = new UpdatePersonDto(rs.getString("name"), rs.getString("last_name"), rs.getBoolean("status"));
                            dbLogicPresenter.updatePerosnList(UUID.fromString(rs.getString("uuid")), updatePersonDto);
                        }
                    }
                    Thread.sleep(5000);
                } catch (SQLException e) {
                    e.printStackTrace();
                    new Alert(Alert.AlertType.ERROR, "Nie można połączyć się z bazą danych", ButtonType.OK)
                            .showAndWait();
                    System.exit(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            try {
                rs.close();
                dbConnection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        t.setDaemon(true);
        t.start();
    */
    }
}
