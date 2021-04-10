package Client.newStation;

import Client.Main;
import Common.Official;
import Server.MongoDB;
import Server.UniqueIDGenerator;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import org.bson.Document;

import java.io.IOException;
import java.util.ArrayList;

import static com.mongodb.client.model.Filters.eq;

public class addNewStationController
{
    public MenuButton addedOfficials;
    public TextField officialID,name,city,state,pincode;
    public TextArea area;
    public Label msg;
    ArrayList<String>Officials=new ArrayList<String>();
    public void onActionBack()
    {

    }
    public void onActionAddOfficials()
    {
        if(officialID.getText().equals(""))
        {
            officialID.setPromptText("Required**");
        }
        else
        {
            String ss=officialID.getText();
            Document tt= MongoDB.officialCollection.find(eq("username", ss)).first();
            if (tt == null) {
                //no values found
                msg.setVisible(true);
                msg.setText("No such official exist");
            }
           else {
                MenuItem m1 = new MenuItem(ss);
                addedOfficials.setVisible(true);
                addedOfficials.getItems().add(m1);
                Officials.add(ss);
                officialID.setText("");
                officialID.setPromptText("Official ID");
            }
        }
    }
    public void onActionSubmit() throws IOException {
        String namev, cityv, statev, pincodev, areav;

        namev = name.getText();
        cityv = city.getText();
        statev = state.getText();
        pincodev = pincode.getText();
        areav = area.getText();

//        if (tt != null||aa!=null) {
////        no values found
//                msg.setVisible(true);
//                msg.setText("Pincode or stationName already present");
//            System.out.println("Pincode or stationName already present");
//        }
//        else{
        String newSCode = UniqueIDGenerator.randomUUID(5, -1, '*');

        Document newPStation = new Document("stationName", namev)
                .append("stationID", newSCode)
                .append("city", cityv)
                .append("state", statev)
                .append("PIN", pincodev)
                .append("area", areav)
                .append("OfficialList", Officials);
        MongoDB.pstationCollection.insertOne(newPStation);
        System.out.println("Successfully Inserted Station");
        Parent root = FXMLLoader.load(getClass().getResource("../home/home.fxml"));
        Main.primaryStage.setScene(new Scene(root, 1138, 575));
        Main.primaryStage.setTitle("HOME - PoliceEnigma");
        Main.primaryStage.show();
//    }
    }
}