package Server;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

import java.net.UnknownHostException;

public class MongoDB {
    public static MongoClient mongoClient;
    public static MongoDatabase db;

    public static void startServer() throws UnknownHostException
    {
        mongoClient = new MongoClient( "localhost" , 27017 );
        System.out.println("server connection successfully done");

        //Connecting with database
        db = mongoClient.getDatabase("PoliceEnigma");
        System.out.println("Connect to database successfully");
        System.out.println("Database Name" + db.getName());
    }
}
