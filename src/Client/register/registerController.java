package Client.register;
import Server.MongoDB;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
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
import java.util.Arrays;
import java.util.function.Consumer;

import java.util.ArrayList;

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
    public void registerOnAction()
    {
        String username = userName.getText();
        String namev = name.getText();
        Integer agev = Integer.parseInt(age.getText());
        String cityv = city.getText();
        String numberv = number.getText();
        String statev = state.getText();
        String stationv = policeStationID.getText();
        String designationv = designation.getText();
        String passwordv = password.getText();
        String cpasswordv = confirmPassword.getText();
        String genderv = gender.getValue();
        int starsv = (int) stars.getValue();
        String aadhaarv = aadhaar.getText();

        if(!anyEmpty())
        {
            if(!passwordv.equals(cpasswordv))
            {
                registermsg.setVisible(true);
                registermsg.setText("Password and Confirm Password should be same!!");
            }
            else {
                Document tuser = MongoDB.personCollection.find(eq("userName", username)).first();
                if (tuser != null) {
                    //no values found
                    registermsg.setVisible(true);
                    registermsg.setText("Already used username. Please try some other username!");
                }
                else
                {
                    Document newPerson = new Document("userName", username)
                            .append("age", agev)
                            .append("station", stationv)
                            .append("contact", new Document("phone", numberv)
                                    .append("city", cityv)
                                    .append("state", statev))
                            .append("designation", designationv)
                            .append("password", passwordv);

                    MongoDB.personCollection.insertOne(newPerson);
                    System.out.println("Successful");
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
        return  gender.getValue().isBlank() || userName.getText().isBlank() || name.getText().isBlank() || age.getText().isBlank() ||
                city.getText().isBlank() || number.getText().isBlank() ||
                state.getText().isBlank() || policeStationID.getText().isBlank() || designation.getText().isBlank() ||
                password.getText().isBlank() || confirmPassword.getText().isBlank();
    }
}
