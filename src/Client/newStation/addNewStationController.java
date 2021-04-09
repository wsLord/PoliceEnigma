package Client.newStation;

import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;

public class addNewStationController
{
    public MenuButton addedOfficials;
    public TextField officialID;
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
            MenuItem m1=new MenuItem(officialID.getText());
            addedOfficials.setVisible(true);
            addedOfficials.getItems().add(m1);
            officialID.setText("");
            officialID.setPromptText("Official ID");
        }
    }
    public void onActionSubmit()
    {

    }
}