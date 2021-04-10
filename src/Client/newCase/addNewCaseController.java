package Client.newCase;
import Client.Main;
import Server.MongoDB;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.FileChooser;
import org.bson.Document;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

public class addNewCaseController
{
    @FXML
    public MenuButton selected,selectedTags,selectedPermissions;
    public TextField tagField,permissionTextField,caseID,reportedBy,suspect,accused;
    public TextArea info;
    public Button addTagButton,addPermissionButton;
    public ChoiceBox<String> choiceBox;
    public Label msg;
    ArrayList<String>tags=new ArrayList<String>();
    ArrayList<String> permissionv=new ArrayList<String>();


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
            String ss=tagField.getText();
            MenuItem m1=new MenuItem(ss);
            selectedTags.setVisible(true);
            selectedTags.getItems().add(m1);
            tags.add(ss);
            tagField.setText("");
            tagField.setPromptText("Add tag");
        }
    }
    public void onActionAddPermission() throws IOException {

        if(permissionTextField.getText().equals(""))
        {
            permissionTextField.setPromptText("Required**");
        }
        else
        {
            String ss=permissionTextField.getText();
            MenuItem m1=new MenuItem(ss);
            selectedPermissions.setVisible(true);
            selectedPermissions.getItems().add(m1);
            permissionTextField.setText("");


                permissionv.add(ss);




            permissionTextField.setPromptText("Add person");


        }
    }
    public void onActionChoosePermission()
    {

        String[] choices={"Self","Police Station","Everyone"};
        choiceBox.setItems(FXCollections.observableArrayList(choices));
    }

    public void onActionSubmit() throws IOException
    {
        Boolean all=false;
        String permission=choiceBox.getValue();
        if(permission.equals("Self"))
        {
            permissionv.clear();
        }

        permissionv.add(MongoDB.user);

        if(permission.equals("Everyone"))
        {
            all=true;
        }
        String caseIDv,reportedByv,suspectv,accusedv,infor;
        caseIDv=caseID.getText();
        reportedByv=reportedBy.getText();
        suspectv=suspect.getText();
        accusedv=accused.getText();
        infor=info.getText();
        String station= (String) MongoDB.officialCollection.find(eq("username", MongoDB.user)).first().get("stationID");
        Document newCase = new Document("caseID", caseIDv)
                .append("allowAll",all)
                .append("isSolved",false)
                .append("reportedBy", reportedByv)
                .append("permit",permission)
                .append("stationId",station)
                .append("suspect", suspectv)
                .append("accused", accusedv)
                .append("info",infor)
                .append("Tags", tags)
                .append("permissionsOfIndividuals",permissionv);

        MongoDB.casesCollection.insertOne(newCase);
        System.out.println("Successfully Inserted Case");
        Parent root = FXMLLoader.load(getClass().getResource("../home/home.fxml"));
        Main.primaryStage.setScene(new Scene(root, 1138, 575));
        Main.primaryStage.setTitle("HOME-PoliceEnigma");
        Main.primaryStage.show();
    }
}
