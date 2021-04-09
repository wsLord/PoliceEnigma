package Client.login;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class loginController
{
    @FXML
    public TextField username;
    public PasswordField loginpassword;
    public Label loginmsg;
    public Stage window;
    public void loginOnAction() throws IOException {
        if(!username.getText().isBlank() && !loginpassword.getText().isBlank())
        {
            //IF everything is ok
            Parent root = FXMLLoader.load(getClass().getResource("../home/home.fxml"));
            window= (Stage) username.getScene().getWindow();
            window.setScene(new Scene(root, 1138, 575));
            window.setTitle("HOME-PoliceEnigma");
            window.show();
        }
        else
        {
            loginmsg.setVisible(true);
            loginmsg.setText("All fields required");
        }
    }
    public void registerOnAction() throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("../register/register.fxml"));
        window= (Stage) username.getScene().getWindow();
        window.setScene(new Scene(root, 1138, 575));
        window.setTitle("Register-PoliceEnigma");
        window.show();
    }
}
