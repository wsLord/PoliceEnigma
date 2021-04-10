package Client.home;

import Client.Main;
import Server.MongoDB;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class LogoutPopUpController
{
    Button yes, no;
    private Stage currwindow;

    public void setStage(Stage curr) {
        currwindow = curr;
    }

    public void onActionLogout() throws IOException {
//        Stage window = (Stage) yes.getScene().getWindow();
        currwindow.close();
        MongoDB.user = null;
        Parent root = FXMLLoader.load(getClass().getResource("../login/login.fxml"));
        Main.primaryStage.setTitle("PoliceEnigma");
        Main.primaryStage.setScene(new Scene(root, 600, 475));
        Main.primaryStage.show();
    }
    public void onActionKeepLoggedIn()
    {
        currwindow.close();
    }
}
