package Client.search;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class searchController
{
    public TextField searchField;
    public Label displayLabel;
    public CheckBox caseID,name,station,pincode,place;
    public VBox options;
    public ScrollPane optionsPane;
    public void onActionBack()
    {

    }
    public void onActionSearch()
    {
        String text=searchField.getText();
        List<String> results = new ArrayList<>(); //Search for text in database and add it in results array
        options=new VBox();
        options.setVisible(true);
        optionsPane.setVisible(true);
        optionsPane.setContent(options);
        for(String r:results)
        {
            Label label=new Label();
            label.setText(r);
            label.setCursor(Cursor.HAND);
            label.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    searchField.setText(label.getText());
                }
            });
            options.getChildren().add(label);
        }
    }
    public void onActionDisplay()
    {
        String text=searchField.getText();
        options.setVisible(false);
        optionsPane.setVisible(false);
        if(text.equals(""))
        {
            searchField.setText("");
            searchField.setPromptText("Search");
        }
        else
        {
            List<String> results = new ArrayList<>(); //Search and add
        }
        displayLabel.setVisible(true);
    }
    public void onActionCancel()
    {
        searchField.setText("");
        options.setVisible(false);
        optionsPane.setVisible(false);
        searchField.setPromptText("Search");
    }
}

