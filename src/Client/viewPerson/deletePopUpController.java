package Client.viewPerson;

import javafx.scene.control.Button;
import javafx.stage.Stage;

public class deletePopUpController
{
    public Button okbtn;

    public void onActionOK()
    {
        Stage window = (Stage) okbtn.getScene().getWindow();
        window.close();
    }
}
