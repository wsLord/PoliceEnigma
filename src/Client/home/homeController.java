package Client.home;
import Client.Main;
import Server.MongoDB;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class homeController
{
    public Label msg;
    public void onActionSetting()
    {

    }
    public void onActionProfile()
    {

    }
    public void onActionLogout() throws IOException {
        Stage window = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("./LogoutPopUp.fxml"));
        window.setScene(new Scene(root, 600, 475));
        window.setTitle("Log Out?");
        window.show();
    }
    public void onActionAddPerson() throws IOException {
        //Redirecting to Add Person
        Parent root = FXMLLoader.load(getClass().getResource("../newPerson/addNewPerson.fxml"));
        Main.primaryStage.setScene(new Scene(root, 600, 475));
        Main.primaryStage.setTitle("New Person - PoliceEnigma");
        Main.primaryStage.show();
    }
    public void onActionAddCase() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../newCase/addNewCase.fxml"));
        Main.primaryStage.setScene(new Scene(root, 600, 475));
        Main.primaryStage.setTitle("New Case - PoliceEnigma");
        Main.primaryStage.show();
    }
    public void onActionAddPoliceStation() throws IOException {
        if(MongoDB.user.equals("admin")) {
            Parent root = FXMLLoader.load(getClass().getResource("../newStation/addNewStation.fxml"));
            Main.primaryStage.setScene(new Scene(root, 600, 475));
            Main.primaryStage.setTitle("New Station - PoliceEnigma");
            Main.primaryStage.show();
        }
        else
        {
            System.out.println("Not admin go back");
        }
    }
    public void onActionShowCases()
    {

    }
    public void onActionSearch() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../search/search.fxml"));
        Main.primaryStage.setScene(new Scene(root, 600, 475));
        Main.primaryStage.setTitle("Search - PoliceEnigma");
        Main.primaryStage.show();
    }
    public void onActionOCRScan()
    {

    }
    public void onActionFacialRecog()
    {

    }
}
