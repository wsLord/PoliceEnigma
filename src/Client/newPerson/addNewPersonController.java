package Client.newPerson;

import Client.Main;
import Server.MongoDB;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.bson.Document;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import static com.mongodb.client.model.Filters.eq;

public class addNewPersonController
{
    @FXML
    public ImageView personImage;
    public ChoiceBox<String> gender;
    public TextField name,age,aadhaarNo,contact,city,state;
    public Label msg;
    File file;
    public void onActionBack() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../home/home.fxml"));
        Main.primaryStage.setScene(new Scene(root, 1138, 575));
        Main.primaryStage.setTitle("HOME - PoliceEnigma");
        Main.primaryStage.show();
        System.out.println("back done");
    }
    public void onActionUploadImage() throws FileNotFoundException {
        FileChooser fileChooser = new FileChooser();
        Stage stage = new Stage();
        file = fileChooser.showOpenDialog(stage); //This file has to be added in database
        FileInputStream imageFile = new FileInputStream(file.getAbsolutePath());
        personImage.setImage(new Image(imageFile));
        personImage.setDisable(true);
    }
    public void onActionGender()
    {
        String[] choices = {"Male","Female","Transgender"};
        gender.setItems(FXCollections.observableArrayList(choices));
    }
    public void onActionSubmit() throws IOException {

        String namev = name.getText();//
        Integer agev = 0;
        if(!age.getText().isBlank()) {
            agev = Integer.parseInt(age.getText());//
        }
        String cityv = city.getText();//
        String numberv = contact.getText();//
        String statev = state.getText();//

        String genderv = gender.getValue();//
        String aadhaarv = aadhaarNo.getText();//

        if(!anyEmpty())
        {
            Document tuser = MongoDB.personCollection.find(eq("aadhaarId", aadhaarv)).first();

            if (tuser != null) {
                //no values found
                msg.setVisible(true);
                msg.setText("The aadhaar id is already present");
            }
            else {
                msg.setVisible(false);
                Document newPerson = new Document("aadhaarId", aadhaarv)
                        .append("name", namev)
                        .append("age", agev)
                        .append("gender", genderv)
                        .append("contact", new Document("phone", numberv)
                                .append("city", cityv)
                                .append("state", statev)
                                .append("PIN", pincodev);

                MongoDB.personCollection.insertOne(newPerson);
                System.out.println("Successfully Inserted Person");
                //Redirecting to Home
                Parent root = FXMLLoader.load(getClass().getResource("../home/home.fxml"));
                Main.primaryStage.setScene(new Scene(root, 1138, 575));
                Main.primaryStage.setTitle("HOME - PoliceEnigma");
                Main.primaryStage.show();
            }
        }
        else
        {
            msg.setText("All fields required");
        }
    }
    public boolean anyEmpty()
    {
        return  name.getText().isBlank() || age.getText().isBlank() ||
                city.getText().isBlank() || contact.getText().isBlank() ||
                state.getText().isBlank() || aadhaarNo.getText().isBlank() ||gender.getValue().isBlank();
    }
}
