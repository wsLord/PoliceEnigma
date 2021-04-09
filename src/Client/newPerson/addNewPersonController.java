package Client.newPerson;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class addNewPersonController
{
    @FXML
    public ImageView personImage;
    public ChoiceBox<String> gender;
    public TextField name,age,aadhaarNo,pinCode;
    public TextArea info,address;
    public void onActionBack()
    {

    }
    public void onActionUploadImage() throws FileNotFoundException {
        FileChooser fileChooser = new FileChooser();
        Stage stage=new Stage();
        File file=fileChooser.showOpenDialog(stage);
        FileInputStream imageFile=new FileInputStream(file.getAbsolutePath());
        personImage.setImage(new Image(imageFile));
        personImage.setDisable(true);
    }
    public void onActionGender()
    {
        String[] choices ={"Male","Female","Transgender"};
        gender.setItems(FXCollections.observableArrayList(choices));
    }
    public void onActionSubmit()
    {

    }
}
