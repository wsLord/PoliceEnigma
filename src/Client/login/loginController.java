package Client.login;
import Client.Main;
import Server.MongoDB;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.bson.Document;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;

import static com.mongodb.client.model.Filters.eq;

public class loginController
{
    @FXML
    public TextField username;
    public PasswordField loginpassword;
    public Label loginmsg;

    public void loginOnAction() throws IOException {
        if(!username.getText().isBlank() && !loginpassword.getText().isBlank())
        {
            //IF everything is ok
            String usernamev = username.getText();
            String passwordv = loginpassword.getText();
            Document tuserName = MongoDB.officialCollection.find(eq("username", usernamev)).first();
            if(tuserName != null)
            {
                String hashpass = tuserName.getString("password");
                // Check that an unencrypted password matches
                if (BCrypt.checkpw(passwordv, hashpass))
                {
                    loginmsg.setVisible(false);
                    MongoDB.user = usernamev;
                    Parent root = FXMLLoader.load(getClass().getResource("../home/home.fxml"));
                    Main.primaryStage.setScene(new Scene(root, 1138, 575));
                    Main.primaryStage.setTitle("HOME-PoliceEnigma");
                    Main.primaryStage.show();
                }
                else
                {
                    loginmsg.setVisible(true);
                    loginmsg.setText("Password or Username is incorrect");
                }

            }
            else
            {
                loginmsg.setVisible(true);
                loginmsg.setText("Username is invalid");
            }
        }
        else
        {
            loginmsg.setVisible(true);
            loginmsg.setText("All fields required!");
        }
    }
    public void registerOnAction() throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("../register/register.fxml"));
        Main.primaryStage.setScene(new Scene(root, 1138, 575));
        Main.primaryStage.setTitle("Register-PoliceEnigma");
        Main.primaryStage.show();
    }
}