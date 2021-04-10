package Server;

import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import io.github.cdimascio.dotenv.Dotenv;
import org.bson.Document;

import java.net.UnknownHostException;

public class MongoDB {
    public static String user;

    public static MongoClient mongoClient;
    public static MongoDatabase db;
    public static MongoCollection<Document> personCollection;
    public static MongoCollection<Document> casesCollection;
    public static MongoCollection<Document> officialCollection;
    public static MongoCollection<Document> pstationCollection;

    public static void startServer() throws UnknownHostException
    {
        Dotenv dotenv = Dotenv.load();
        String host = dotenv.get("host");
        String port = dotenv.get("port");

        mongoClient = new MongoClient(host, Integer.parseInt(port));
        System.out.println("server connection successfully done");

        //Connecting with database
        db = mongoClient.getDatabase("PoliceEnigma");
        System.out.println("Connect to database successfully");
        System.out.println("Database Name" + db.getName());

        personCollection = db.getCollection("Persons");
        casesCollection = db.getCollection("Cases");
        officialCollection = db.getCollection("Official");
        pstationCollection = db.getCollection("Stations");
    }
}
