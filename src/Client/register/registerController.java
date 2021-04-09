package Client.register;
import Server.MongoDB;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
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
    public TextField usernametf, age, city, number,state,policeStation,designation;
    public PasswordField password,confirmPassword;
//    public TextArea address;
    public Label registermsg;

    Consumer<Document> printConsumer = new Consumer<Document>() {
        @Override
        public void accept(final Document document) {
            System.out.println(document.toJson());
        }
    };

    public void registerOnAction()
    {
        String username = usernametf.getText();
//        String namev = name.getText();
        Integer agev = Integer.parseInt(age.getText());
        String cityv = city.getText();
        String numberv = number.getText();
        String statev = state.getText();
        String stationv = policeStation.getText();
        String designationv = designation.getText();
        String passwordv = password.getText();
        String cpasswordv = confirmPassword.getText();

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
        if(!usernametf.getText().isBlank() && !age.getText().isBlank() &&
                !city.getText().isBlank() && !number.getText().isBlank() &&
                !state.getText().isBlank() && !policeStation.getText().isBlank() && !designation.getText().isBlank() &&
                !password.getText().isBlank() && !confirmPassword.getText().isBlank())
            return false;
        else
            return true;
    }
}
