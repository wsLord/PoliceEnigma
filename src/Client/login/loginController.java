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
    public void loginOnAction()
    {
        if(!username.getText().isBlank() && !loginpassword.getText().isBlank())
        {

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
        Stage primaryStage=new Stage();
        primaryStage.setTitle("Register-PoliceEnigma");
        primaryStage.setScene(new Scene(root, 1138, 575));
        primaryStage.show();
    }
}
