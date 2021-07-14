package pl.kul.todos.adapter.database;


import com.mongodb.MongoClientURI;
import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class DBConnection {

    public static MongoClient getConnection() throws MongoException {
/*
        Connection con = null;

        // load the Driver Class
        Class.forName(DB_DRIVER_CLASS);

        // create the connection now
        con = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

        System.out.println("DB Connection created successfully");

        return con;
 */
        //"mongodb+srv://mateusz:mateusz123@cluster01-ekgsm.mongodb.net/"
        MongoClient mongoClient = MongoClients.create(
                "mongodb+srv://mateusz:mateusz123@cluster01.ekgsm.mongodb.net/employees?retryWrites=true&w=majority");
        MongoDatabase database = mongoClient.getDatabase("employees");
        System.out.println(database.getName());
        return mongoClient;
    }
}
