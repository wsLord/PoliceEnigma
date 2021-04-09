package Client.register;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
public class registerController
{
    @FXML
    public TextField username,age,city,number,state,policeStation,designation;
    public PasswordField password,confirmPassword;
    public TextArea address;
    public Label registermsg;
    public void registerOnAction()
    {
        if(!username.getText().isBlank() && !age.getText().isBlank() &&
                !city.getText().isBlank() && !number.getText().isBlank() &&
                !state.getText().isBlank() && !policeStation.getText().isBlank() && !designation.getText().isBlank() &&
                !password.getText().isBlank() && !confirmPassword.getText().isBlank())
        {

        }
        else
        {
            registermsg.setVisible(true);
            registermsg.setText("All fields required");
        }
    }
}
