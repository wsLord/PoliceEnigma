package Client.viewCase;

import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
public class viewCaseController
{
    public BorderPane pane;
    public VBox files,timeline;
    public TextField caseID,reportedBy,suspect,accused;
    public TextArea info,witnesses,tags;
    public Button submit,edit;
    public CheckBox permissionSelf,permissionStation,permissionEveryone;
    public void onActionEdit()
    {
        caseID.setEditable(true);reportedBy.setEditable(true);suspect.setEditable(true);
        accused.setEditable(true);info.setEditable(true);witnesses.setEditable(true);tags.setEditable(true);
        permissionSelf.setDisable(false);permissionStation.setDisable(false);permissionEveryone.setDisable(false);
        edit.setVisible(false);
        submit.setVisible(true);
    }
    public void onActionSubmit()
    {

    }
}
