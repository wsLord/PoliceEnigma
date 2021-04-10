package Client.profileSetting;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
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
        userName.setEditable(true);name.setEditable(true);age.setEditable(true);city.setEditable(true);
        number.setEditable(true);state.setEditable(true);policeStationID.setEditable(true);designation.setEditable(true);
        aadhaar.setEditable(true);gender.setDisable(false);stars.setDisable(false);
    }
    public void onActionSubmit()
    {

    }
    public void onActionChangePass()
    {

    }
}
