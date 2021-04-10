package Client.search;
import Server.MongoDB;
import com.mongodb.client.FindIterable;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class searchController
{
    public TextField searchField;
    public Label displayLabel;
    public CheckBox caseID,name,station,place;
    public VBox options;
    public ScrollPane optionsPane;
    public void onActionBack()
    {

    }
    public void onActionSearch()
    {
        String text=searchField.getText();
        List<String> results = new ArrayList<>(); //Search for text in database and add it in results array
        if(name.isSelected())
        {
            Document regQuery=new Document();
            regQuery.append("$regex", "^.*" +Pattern.quote(text));
            regQuery.append("$options", "i");
            Document findQuery=new Document();
            findQuery.append("name",regQuery);
            FindIterable<Document> result= MongoDB.personCollection.find(findQuery);
            for(Document r:result)
            {
                results.add(r.getString("name"));
            }
        }
        if(caseID.isSelected())
        {
            Document regQuery=new Document();
            regQuery.append("$regex", "^.*" +Pattern.quote(text));
            regQuery.append("$options", "i");
            Document findQuery=new Document();
            findQuery.append("caseID",regQuery);
            FindIterable<Document> result= MongoDB.casesCollection.find(findQuery);
            for(Document r:result)
            {
                results.add(r.getString("caseID"));
            }
        }
        if(station.isSelected())
        {
            Document regQuery=new Document();
            regQuery.append("$regex", "^.*" +Pattern.quote(text));
            regQuery.append("$options", "i");
            Document findQuery=new Document();
            findQuery.append("stationID",regQuery);
            FindIterable<Document> result= MongoDB.pstationCollection.find(findQuery);
            for(Document r:result)
            {
                results.add(r.getString("stationID"));
            }
        }
        if (place.isSelected())
        {
            Document regQuery=new Document();
            regQuery.append("$regex", "^.*" +Pattern.quote(text));
            regQuery.append("$options", "i");
            Document findQuery=new Document();
            findQuery.append("city",regQuery);
            findQuery.append("state",regQuery);
            FindIterable<Document> result= MongoDB.personCollection.find(findQuery);
            for(Document r:result)
            {
                results.add(r.getString("city"));
                results.add(r.getString("state"));
            }
            result=MongoDB.casesCollection.find(findQuery);
            for(Document r:result)
            {
                results.add(r.getString("city"));
                results.add(r.getString("state"));
            }
            result=MongoDB.pstationCollection.find(findQuery);
            for(Document r:result)
            {
                results.add(r.getString("city"));
                results.add(r.getString("state"));
            }
        }
        options=new VBox();
        options.setVisible(true);
        optionsPane.setVisible(true);
        optionsPane.setContent(options);
        for(String r:results)
        {
            Label label=new Label();
            label.setText(r);
            label.setCursor(Cursor.HAND);
            label.setFont(Font.font(15));
            label.setTextFill(Color.BLUEVIOLET);
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

