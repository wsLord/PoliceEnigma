package Client.home;
import Client.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class homeController
{
    public void onActionSetting()
    {

    }
    public void onActionProfile()
    {

    }
    public void onActionLogout()
    {

    }
    public void onActionAddPerson() throws IOException {
        //Redirecting to Add Person
        Parent root = FXMLLoader.load(getClass().getResource("../newPerson/addNewPerson.fxml"));
        Main.primaryStage.setScene(new Scene(root, 600, 475));
        Main.primaryStage.setTitle("Login-PoliceEnigma");
        Main.primaryStage.show();
    }
    public void onActionAddCase() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../newCase/addNewCase.fxml"));
        Main.primaryStage.setScene(new Scene(root, 600, 475));
        Main.primaryStage.setTitle("Login-PoliceEnigma");
        Main.primaryStage.show();
    }
    public void onActionAddPoliceStation()
    {

    }
    public void onActionCurrentCases()
    {

    }
    public void onActionSolvedCases()
    {

    }
    public void onActionUnsolvedCases()
    {

    }
    public void onActionSearch()
    {

    }
    public void onActionOCRScan()
    {

    }
    public void onActionFacialRecog()
    {

    }
}
