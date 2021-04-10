package Client.viewPerson;

import Client.Main;
import Server.MongoDB;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.io.IOException;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.*;

public class viewPersonController
{
    private ObjectId personID;
    private Boolean isOfficer;
    public AnchorPane officialPane;
    public HBox casesPane;
    public Button edit,submit,delete;
    public TextField name,age,aadhaar, phone,gender,city,state,pincode,username,designation;
    public TextField stationCity,stationID,stationPincode,stationState,stationName; //Station Details
    public Label msg;
    public Slider stars;

    public void checkTypeAndSet(ObjectId ID) {
        this.personID = ID;

        Document aadmi = MongoDB.personCollection.find(eq("_id", personID)).first();
        Document policewaala = MongoDB.officialCollection.find(eq("_id", personID)).first();

        assert aadmi != null;
        name.setText(aadmi.getString("name"));
        age.setText(aadmi.getString("age"));
        aadhaar.setText(aadmi.getString("aadhaarId"));
        gender.setText(aadmi.getString("gender"));
        phone.setText(aadmi.getString("phone"));
        city.setText(aadmi.getString("city"));
        state.setText(aadmi.getString("state"));
        pincode.setText(aadmi.getString("PIN"));

        if(policewaala != null) {
            this.isOfficer = true;
            username.setText(policewaala.getString("username"));
            designation.setText(policewaala.getString("designation"));
            stationID.setText(policewaala.getString("stationID"));
            stars.setValue(policewaala.getInteger("stars"));
        }
        else {
            officialPane.setVisible(false);
            this.isOfficer = false;
        }


    }

    public void onActionBack()
    {
    }
    public void onActionEdit()
    {
        edit.setVisible(false);submit.setVisible(true);

        name.setEditable(true);age.setEditable(true);
//        aadhaar.setEditable(true); Aadhaar not editable
        phone.setEditable(true);city.setEditable(true);state.setEditable(true);pincode.setEditable(true);

        if(isOfficer && MongoDB.user.equals("admin")) {
//            username.setEditable(true); username not editable
            designation.setEditable(true);stationID.setEditable(true);
            stars.setDisable(true);
        }


    }

    public void onActionSubmit()
    {
        MongoDB.personCollection.replaceOne(
                eq("_id", personID),
                new Document("aadhaarId", aadhaar.getText())
                        .append("name", name.getText())
                        .append("age", age.getText())
                        .append("gender", gender.getText())
                        .append("contact", new Document("phone", phone.getText())
                                .append("city", city.getText())
                                .append("state", state.getText())
                                .append("PIN", pincode.getText())));

        if(isOfficer) {
            MongoDB.officialCollection.updateOne(
                    eq("_id", personID),
                    combine(set("designation", designation.getText()),
                            set("stationID", stationID.getText()),
                            set("stars", (int)stars.getValue()),
                            currentDate("lastModified")));
        }

        checkTypeAndSet(personID);
        edit.setVisible(true);submit.setVisible(false);
        msg.setText("Changes Saved!");
    }

    public void onActionDelete() throws IOException {
        if(!isOfficer) {
            MongoDB.personCollection.deleteOne(eq("_id", personID));

            Stage window = (Stage) name.getScene().getWindow();
            window.close();

            window = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("./DeletePopUp.fxml"));
            window.setScene(new Scene(root, 600, 475));
            window.setTitle("Deleted");
            window.show();
        }
    }
}
