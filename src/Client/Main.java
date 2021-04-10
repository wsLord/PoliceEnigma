package Client;

import Server.MongoDB;
import com.mongodb.client.MongoDatabase;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public static Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        Main.primaryStage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("../Client/login/login.fxml"));
        primaryStage.setTitle("PoliceEnigma");
        primaryStage.setScene(new Scene(root, 600, 475));
        primaryStage.show();
    }
    public static void main(String[] args) {


//        MongoDB server = new MongoDB();
        try {
//            server.startServer();
            MongoDB.startServer();


        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        launch(args);
    }
}
