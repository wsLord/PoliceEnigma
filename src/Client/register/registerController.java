package Client.register;
import Server.MongoDB;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.bson.Document;
import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.FindIterable;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Filters;
import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Projections.*;
import com.mongodb.client.model.Sorts;

import java.io.IOException;
import java.util.Arrays;
import java.util.function.Consumer;

import java.util.ArrayList;
import org.bson.types.ObjectId;
public class registerController
{
    @FXML
    public TextField userName,name,age, city, number,state,policeStationID,designation,aadhaar;
    public PasswordField password,confirmPassword;
    public Label registermsg;
    public ChoiceBox<String> gender;
    public Slider stars;

    Consumer<Document> printConsumer = new Consumer<Document>() {
        @Override
        public void accept(final Document document) {
            System.out.println(document.toJson());
        }
    };


    public void onActionGender()
    {
        String[] choices ={"Male","Female","Transgender"};
        gender.setItems(FXCollections.observableArrayList(choices));
    }
    public void registerOnAction() throws IOException {
        String usernamev = userName.getText();
        String namev = name.getText();//
        Integer agev = Integer.parseInt(age.getText());//
        String cityv = city.getText();//
        String numberv = number.getText();//
        String statev = state.getText();//
        String stationv = policeStationID.getText();
        String designationv = designation.getText();
        String passwordv = password.getText();
        String cpasswordv = confirmPassword.getText();
        String genderv = gender.getValue();//
        int starsv = (int) stars.getValue();
        String aadhaarv = aadhaar.getText();//

        if(!anyEmpty())
        {
            if(!passwordv.equals(cpasswordv))
            {
                registermsg.setVisible(true);
                registermsg.setText("Password and Confirm Password should be same!!");
            }
            else {
                Document tuser = MongoDB.personCollection.find(eq("aadhaarId", aadhaarv)).first();
                Document tt=MongoDB. officialCollection.find(eq("username", usernamev)).first();
                if (tuser != null) {
                    //no values found
                    registermsg.setVisible(true);
                    registermsg.setText("The aadhaar id is already present. please directly login");
                }
                else
                {
                    if(tt != null)
                    {
                        registermsg.setVisible(true);
                        registermsg.setText("the username is already in use");
                    }
                    else {
                        registermsg.setVisible(false);
                        Document newPerson = new Document("aadhaarId", aadhaarv)
                                .append("name", namev)
                                .append("age", agev)
                                .append("gender", genderv)
                                .append("contact", new Document("phone", numberv)
                                        .append("city", cityv)
                                        .append("state", statev));

                        MongoDB.personCollection.insertOne(newPerson);
                        System.out.println("Successfully Inserted Person");
                        ObjectId id = MongoDB.personCollection.find(eq("aadhaarId", aadhaarv)).first().getObjectId("_id");

                        System.out.println(id);
                        Document newOfficial = new Document("_id", id)
                                .append("aadhaarId",aadhaarv)
                                .append("username",usernamev)
                                .append("stationId",stationv)
                                .append("designation",designationv)
                                .append("password",passwordv)
                                .append("name", namev)
                                .append("age", agev)
                                .append("gender", genderv)
                                .append("stars",starsv)
                                .append("contact", new Document("phone", numberv)
                                        .append("city", cityv)
                                        .append("state", statev));
                        MongoDB.officialCollection.insertOne(newOfficial);
                        System.out.println("Successfully Inserted Official");

                        //Redirecting to login
                        Parent root = FXMLLoader.load(getClass().getResource("../login/login.fxml"));
                        Stage window = (Stage) name.getScene().getWindow();
                        window.setScene(new Scene(root, 600, 475));
                        window.setTitle("Login-PoliceEnigma");
                        window.show();
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
                password.getText().isBlank() || confirmPassword.getText().isBlank() ||aadhaar.getText().isBlank() || aadhaar.getText().isBlank();
    }
}
