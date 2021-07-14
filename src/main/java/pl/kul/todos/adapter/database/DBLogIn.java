package pl.kul.todos.adapter.database;


import com.mongodb.BasicDBObject;
import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import org.bson.Document;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBLogIn implements DBLogInInterface {

    public static int id;
    private DBLogicPresenter dbLogicPresenter;

    @Override
    public void setPresenter(DBLogicPresenter dbLogicPresenter) {
        this.dbLogicPresenter = dbLogicPresenter;
    }

    @Override
    public void tryLogIn(String login, String pass) {

        MongoClient dbConnection = null;
        try {
            dbConnection = DBConnection.getConnection();
        } catch (MongoException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Nie można połączyć się z bazą danych", ButtonType.OK)
                    .showAndWait();
            System.exit(1);
        }


        System.out.println("jestem tu2");

        MongoDatabase database = dbConnection.getDatabase("employees");
        MongoCollection collection = database.getCollection("workers");
        BasicDBObject regexQuery = new BasicDBObject();
        regexQuery.put("login", login);
        System.out.println("jestem tu2,5");
        MongoCursor<Document> cursor = collection.find(regexQuery).iterator();
        try {
            while (cursor.hasNext()) {
                System.out.println(cursor.next().toJson());
                System.out.println("jestem tu1");
            }
        } finally {
            cursor.close();
        }
        dbConnection.close();

/*
        try {
            query = "SELECT id, name, last_name, email, job, login, password "
                    + "FROM workers WHERE login = ? "
                    + "AND password = ? ";
            pstmt = dbConnection.prepareStatement(query);
            pstmt.setString(1, login);
            pstmt.setString(2, pass);
            rs = pstmt.executeQuery();

            if (!rs.next()) {
                pstmt.close();
                dbConnection.close();
                new Alert(Alert.AlertType.ERROR, "Błędne dane logowania", ButtonType.OK)
                        .showAndWait();
                dbLogicPresenter.wrongLogInDetails();
            } else {
                id = rs.getInt("id");
                query = "UPDATE workers SET status = ? WHERE id = ? ";
                pstmt = dbConnection.prepareStatement(query);
                pstmt.setBoolean(1, true);
                pstmt.setInt(2, id);
                pstmt.executeUpdate();
            }
            pstmt.close();
            dbConnection.close();
        } catch (MongoException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Nie można połączyć się z bazą danych", ButtonType.OK)
                    .showAndWait();
            System.exit(1);
        } finally {

        }

 */
        // dbLogicPresenter.getTasks(); Dokończyć
        dbLogicPresenter.logInSuccess();
    }
}