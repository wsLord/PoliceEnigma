package Client.newStation;

import Common.Official;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.ArrayList;

public class addNewStationController
{
    public MenuButton addedOfficials;
    public TextField officialID,name,city,state,pincode;
    public TextArea area;
    ArrayList<String>Officials;
    public void onActionBack()
    {

    }
    public void onActionAddOfficials()
    {
        if(officialID.getText().equals(""))
        {
            officialID.setPromptText("Required**");
        }
        else
        {
            String ss=officialID.getText();
            MenuItem m1=new MenuItem(ss);
            addedOfficials.setVisible(true);
            addedOfficials.getItems().add(m1);
            Officials.add(ss);
            officialID.setText("");
            officialID.setPromptText("Official ID");
        }
    }
    public void onActionSubmit()
    {

    }
}