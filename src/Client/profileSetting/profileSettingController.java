package Client.profileSetting;
import Client.Main;
import Server.MongoDB;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.mindrot.jbcrypt.BCrypt;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.addToSet;
import static com.mongodb.client.model.Updates.combine;

public class profileSettingController
{
    @FXML
    public TextField userName,name,age, city, number,state,policeStationID,designation,aadhaar;
    public Label msg;
    public ChoiceBox<String> gender;
    public Slider stars;
    public Button edit,submit;
    public PasswordField oldPassword,newPassword,confirmPassword;
    public void onActionGender()
    {
        String[] choices ={"Male","Female","Transgender"};
        gender.setItems(FXCollections.observableArrayList(choices));
    }
    public void onActionBack()
    {

    }
    public void onActionEdit()
    {
        edit.setVisible(false);submit.setVisible(true);
        userName.setEditable(false);name.setEditable(true);age.setEditable(true);city.setEditable(true);
        number.setEditable(true);state.setEditable(true);policeStationID.setEditable(true);designation.setEditable(true);
        aadhaar.setEditable(false);gender.setDisable(false);stars.setDisable(true);
    }
    public void onActionSubmit()
    {
        String usernamev = userName.getText();
        String namev = name.getText();//
        Integer agev = 0;
        if(!age.getText().isBlank()) {
            agev = Integer.parseInt(age.getText());//
        }
        String cityv = city.getText();//
        String numberv = number.getText();//
        String statev = state.getText();//
        String stationcodev = policeStationID.getText();
        String designationv = designation.getText();
        String aadhaarv = aadhaar.getText();//

        String genderv = gender.getValue();//
        int starsv = (int) stars.getValue();



        if(!anyEmpty())
        {
            

//                Document tuser = MongoDB.personCollection.find(eq("aadhaarId", aadhaarv)).first();
//                Document tt=MongoDB. officialCollection.find(eq("username", usernamev)).first();
//                if (tuser == null) {
//                    //no values found
//                    registermsg.setVisible(true);
//                    registermsg.setText("addharId not present");
//                }
//                else
//                {
//                    if(tt != null)
//                    {
//                        registermsg.setVisible(true);
//                        registermsg.setText("the username is already in use");
//                    }
//                    else {
//                        registermsg.setVisible(false);
//                        Document cc = MongoDB.pstationCollection.find(eq("stationID", stationcodev)).first();
//                        if(cc == null)
//                        {
//                            registermsg.setVisible(true);
//                            registermsg.setText("Incorrect Police Station code/ Police Station is non existent");
//                        }
//                        else {
//                            // Hashing the password
//                            String hashed = BCrypt.hashpw(passwordv, BCrypt.gensalt(12));
//
//                            Document newPerson = new Document("aadhaarId", aadhaarv)
//                                    .append("name", namev)
//                                    .append("age", agev)
//                                    .append("gender", genderv)
//                                    .append("contact", new Document("phone", numberv)
//                                            .append("city", cityv)
//                                            .append("state", statev)
//                                            .append("PIN", pincodev));
//
//                            MongoDB.personCollection.insertOne(newPerson);
//                            System.out.println("Successfully Inserted Person");
//                            ObjectId id = MongoDB.personCollection.find(eq("aadhaarId", aadhaarv)).first().getObjectId("_id");
//
//                            System.out.println(id);
//                            Document newOfficial = new Document("_id", id)
//                                    .append("username", usernamev)
//                                    .append("stationID", stationcodev)
//                                    .append("designation", designationv)
//                                    .append("password", hashed)
//                                    .append("stars", starsv);
//
//                            MongoDB.officialCollection.insertOne(newOfficial);
//                            System.out.println("Successfully Inserted Official");
//                            MongoDB.pstationCollection.updateOne(
//                                    eq("stationID", stationcodev),
//                                    combine(addToSet("OfficialList", id)));
////
////                            MongoDB.officialCollection.updateOne(
////                                    eq("_id", personID),
////                                    combine(set("designation", designation.getText()),
////                                            set("stationID", stationID.getText()),
////                                            set("stars", (int)stars.getValue()),
////                                            currentDate("lastModified")));
//

                            //Redirecting to login
                            Parent root = FXMLLoader.load(getClass().getResource("../login/login.fxml"));
//                        Stage window = (Stage) name.getScene().getWindow();
                            Main.primaryStage.setScene(new Scene(root, 600, 475));
                            Main.primaryStage.setTitle("Login-PoliceEnigma");
                            Main.primaryStage.show();
                        }
                    }
                }

        }
        else
        {
            registermsg.setVisible(true);
            registermsg.setText("All fields required");
        }
    }
    public boolean anyEmpty()
    {
        return  userName.getText().isBlank() || name.getText().isBlank() || age.getText().isBlank() ||
                city.getText().isBlank() || number.getText().isBlank() ||
                state.getText().isBlank() || policeStationID.getText().isBlank() || designation.getText().isBlank() ||
                aadhaar.getText().isBlank() ||gender.getValue().isBlank();
    }
    public void onActionChangePass()
    {

    }
}
