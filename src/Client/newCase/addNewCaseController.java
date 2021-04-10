package Client.newCase;
import Client.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.FileChooser;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class addNewCaseController
{
    @FXML
    public MenuButton selected,selectedTags,selectedPermissions;
    public TextField tagField,permissionTextField,caseID,reportedBy,suspect,accused;
    public TextArea info;
    public Button addTagButton,addPermissionButton;
    public CheckBox permissionSelf,permissionStation,permissionEveryone;
    public void onActionSelectFiles()
    {
        FileChooser fileChooser = new FileChooser();
        Stage stage=new Stage();
        List<File> file = fileChooser.showOpenMultipleDialog(stage);
        for(File file1:file)
        {
            selected.setText("Selected files");
            MenuItem m1=new MenuItem(file1.getName());
            selected.getItems().add(m1);
        }
    }
    public void onActionBack() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../home/home.fxml"));
        Main.primaryStage.setScene(new Scene(root, 1138, 575));
        Main.primaryStage.setTitle("HOME-PoliceEnigma");
        Main.primaryStage.show();
    }
    public void onActionAddPerson()
    {

    }
    public void onActionAddTags()
    {
        tagField.setVisible(true);
        addTagButton.setVisible(true);
    }
    public void onActionSetTag()
    {
        if(tagField.getText().equals(""))
        {
            tagField.setPromptText("Required**");
        }
        else
        {
            MenuItem m1=new MenuItem(tagField.getText());
            selectedTags.setVisible(true);
            selectedTags.getItems().add(m1);
            tagField.setText("");
            tagField.setPromptText("Add tag");
        }
    }
    public void onActionAddPermission()
    {
        if(permissionTextField.getText().equals(""))
        {
            permissionTextField.setPromptText("Required**");
        }
        else
        {
            MenuItem m1=new MenuItem(permissionTextField.getText());
            selectedPermissions.setVisible(true);
            selectedPermissions.getItems().add(m1);
            permissionTextField.setText("");
            permissionTextField.setPromptText("Add person");
        }
    }
    public void onActionSubmit()
    {

    }
}
