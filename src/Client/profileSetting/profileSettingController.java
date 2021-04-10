package Client.profileSetting;
import Server.MongoDB;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.mindrot.jbcrypt.BCrypt;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.geoIntersects;
import static com.mongodb.client.model.Updates.*;

public class profileSettingController
{
    private ObjectId personID;

    @FXML
    public TextField userName,name,age,city,pincode,phone,state,policeStationID,designation,aadhaar;
    public Label msg;
    public ChoiceBox<String> gender;
    public Slider stars;
    public Button edit,submit;
    public PasswordField oldPassword,newPassword,confirmPassword;

    public void SetAndDisplay() {
        String username = MongoDB.user;
        Document policewaala = MongoDB.officialCollection.find(eq("username", username)).first();

        personID = policewaala.getObjectId("_id");
        Document aadmi = MongoDB.personCollection.find(eq("_id", personID)).first();

        userName.setText(username);
        assert aadmi != null;
        name.setText(aadmi.getString("name"));
        age.setText(aadmi.getString("age"));
        aadhaar.setText(aadmi.getString("aadhaarId"));
        gender.setValue(aadmi.getString("gender"));
        phone.setText(aadmi.getString("phone"));
        city.setText(aadmi.getString("city"));
        state.setText(aadmi.getString("state"));
//        pincode.setText(aadmi.getString("PIN"));

        designation.setText(policewaala.getString("designation"));
        policeStationID.setText(policewaala.getString("stationID"));
        stars.setValue(policewaala.getInteger("stars"));
    }

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
        phone.setEditable(true);state.setEditable(true);policeStationID.setEditable(true);designation.setEditable(true);
        aadhaar.setEditable(false);gender.setDisable(true);stars.setDisable(true);
    }
    public void onActionSubmit()
    {
        MongoDB.personCollection.replaceOne(
                eq("_id", personID),
                new Document("aadhaarId", aadhaar.getText())
                        .append("name", name.getText())
                        .append("age", age.getText())
                        .append("gender", gender.getValue())
                        .append("contact", new Document("phone", phone.getText())
                                .append("city", city.getText())
                                .append("state", state.getText())
                                .append("PIN", pincode.getText())));

        MongoDB.officialCollection.updateOne(
                eq("_id", personID),
                combine(set("designation", designation.getText()),
                        set("stationID", policeStationID.getText()),
                        set("stars", (int)stars.getValue()),
                        currentDate("lastModified")));

        SetAndDisplay();
        edit.setVisible(true);submit.setVisible(false);
        msg.setText("Changes Saved!");
    }

    public void onActionChangePass()
    {
        if(oldPassword.getText().isBlank() || newPassword.getText().isBlank() || confirmPassword.getText().isBlank()) {
            msg.setText("Fields cannot be empty!");
        }
        else {
            String oldp = oldPassword.getText();
            String newp = newPassword.getText();
            String cnfp = confirmPassword.getText();
            if(newp.equals(cnfp)) {
                String hashpass = MongoDB.officialCollection.find(eq("_id", personID)).first().getString("password");
                // Check that an unencrypted password matches
                if (BCrypt.checkpw(oldp, hashpass)) {
                    // Hashing the password
                    String hashed = BCrypt.hashpw(newp, BCrypt.gensalt(12));

                    MongoDB.officialCollection.updateOne(
                            eq("_id", personID),
                            combine(set("password", hashed),
                                    currentDate("lastModified")));

                    SetAndDisplay();
                    msg.setText("Changes Saved!");
                }
                else {
                    msg.setText("Old Password is incorrect!");
                }
            }
            else {
                msg.setText("New Password and Confirm Password must be same!");
            }
        }
    }
}
