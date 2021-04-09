package Client.viewPerson;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

public class viewPersonController
{
    public AnchorPane officialPane;
    public HBox casesPane;
    public Button edit,submit;
    public TextField name,age,aadhaar,contact,gender,city,state,pincode,username,designation,stationID;
    public void onActionBack()
    {

    }
    public void onActionEdit()
    {
        edit.setVisible(false);submit.setVisible(true);
        name.setEditable(true);age.setEditable(true);aadhaar.setEditable(true);contact.setEditable(true);
        city.setEditable(true);state.setEditable(true);pincode.setEditable(true);username.setEditable(true);
        designation.setEditable(true);stationID.setEditable(true);
    }
    public  void onActionSubmit()
    {

    }
}
