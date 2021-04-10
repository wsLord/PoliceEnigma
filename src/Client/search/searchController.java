package Client.search;
import Client.viewPerson.viewPersonController;
import Server.MongoDB;
import com.mongodb.client.FindIterable;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.bson.Document;

import java.io.IOException;
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
    public TitledPane namePanel,stationPanel,placePanel,casePanel;
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
        displayLabel.setVisible(true);
        if(text.equals(""))
        {
            searchField.setText("");
            searchField.setPromptText("Search");
        }
        else
        {
            if(name.isSelected())
            {
                namePanel.setVisible(true);
                Document regQuery=new Document();
                regQuery.append("$regex", "^.*" +Pattern.quote(text));
                regQuery.append("$options", "i");
                Document findQuery=new Document();
                findQuery.append("name",regQuery);
                FindIterable<Document> result= MongoDB.personCollection.find(findQuery);
                VBox vBox=new VBox();
                for(Document r:result)
                {
                    HBox pane=new HBox();
                    Label lname=new Label();
                    Label Name=new Label();
                    lname.setText("Name:");
                    Name.setText(r.getString("name")+"\t");
                    Label laadhaar=new Label();
                    Label Aadhaar=new Label();
                    laadhaar.setText("Aadhaar No.:");
                    Aadhaar.setText(r.getString("aadhaarId"));
                    Button btn=new Button();
                    btn.setText("View Profile");
                    btn.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent mouseEvent) {
                            //Open view profile page in new window
                            Task task = new Task<Void>() {
                                @Override public Void call() throws InterruptedException, IOException {
                                    FXMLLoader loader = new FXMLLoader(getClass().getResource("../viewPerson/viewPersonController.fxml"));
                                    Parent root = (Parent) loader.load();
                                    viewPersonController vpc = loader.getController();
                                    vpc.checkTypeAndSet(r.getObjectId("_id"));
                                    Stage window = new Stage();
                                    window.setScene(new Scene(root, 600, 475));
                                    window.setTitle("Person Information - PoliceEnigma");
                                    window.show();

                                    return null;
                                }
                            };
                        }
                    });
                    pane.getChildren().addAll(lname,Name,laadhaar,Aadhaar,btn);
                    pane.setPadding(new Insets(10,10,10,10));
                    vBox.getChildren().add(pane);
                }
                namePanel.setContent(vBox);
            }
            if(caseID.isSelected())
            {
                casePanel.setVisible(true);
                Document regQuery=new Document();
                regQuery.append("$regex", "^.*" +Pattern.quote(text));
                regQuery.append("$options", "i");
                Document findQuery=new Document();
                findQuery.append("caseID",regQuery);
                FindIterable<Document> result= MongoDB.casesCollection.find(findQuery);
                VBox vBox=new VBox();
                for(Document r:result)
                {
                    HBox pane=new HBox();
                    Label lID=new Label();
                    Label ID=new Label();
                    lID.setText("Case ID:");
                    ID.setText(r.getString("caseID")+"\t");
                    Label ldate=new Label();
                    Label Date=new Label();
                    ldate.setText("Date:");
                    Date.setText(r.getString("date"));
                    Button btn=new Button();
                    btn.setText("View Case");
                    btn.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent mouseEvent) {
                            //Open view case page in new window
                        }
                    });
                    pane.getChildren().addAll(lID,ID,ldate,Date,btn);
                    pane.setPadding(new Insets(10,10,10,10));
                    vBox.getChildren().add(pane);
                }
                casePanel.setContent(vBox);
            }
            if(station.isSelected())
            {
                stationPanel.setVisible(true);
                Document regQuery=new Document();
                regQuery.append("$regex", "^.*" +Pattern.quote(text));
                regQuery.append("$options", "i");
                Document findQuery=new Document();
                findQuery.append("stationID",regQuery);
                FindIterable<Document> result= MongoDB.pstationCollection.find(findQuery);
                VBox vBox=new VBox();
                for(Document r:result)
                {
                    HBox pane=new HBox();
                    Label lID=new Label();
                    Label ID=new Label();
                    lID.setText("Police Station ID:");
                    ID.setText(r.getString("stationID")+"\t");
                    Label lcity=new Label();
                    Label City=new Label();
                    lcity.setText("City:");
                    City.setText(r.getString("city"));
                    pane.getChildren().addAll(lID,ID,lcity,City);
                    pane.setPadding(new Insets(10,10,10,10));
                    vBox.getChildren().add(pane);
                }
                stationPanel.setContent(vBox);
            }
            if(place.isSelected())
            {
                placePanel.setVisible(true);
                Document regQuery=new Document();
                regQuery.append("$regex", "^.*" +Pattern.quote(text));
                regQuery.append("$options", "i");
                Document findQuery=new Document();
                findQuery.append("city",regQuery);
                findQuery.append("state",regQuery);
                FindIterable<Document> result= MongoDB.personCollection.find(findQuery);
                VBox vBox=new VBox();
                for(Document r:result)
                {
                    HBox pane=new HBox();
                    Label lname=new Label();
                    Label Name=new Label();
                    lname.setText("Person name:");
                    Name.setText(r.getString("name")+"\t");
                    Label lcity=new Label();
                    Label City=new Label();
                    lcity.setText("City:");
                    City.setText(r.getString("city"));
                    Button btn=new Button();
                    btn.setText("View Person");
                    btn.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent mouseEvent) {
                            //Open view Person page in new window
                        }
                    });
                    pane.getChildren().addAll(lname,Name,lcity,btn);
                    pane.setPadding(new Insets(10,10,10,10));
                    vBox.getChildren().add(pane);
                }
                result=MongoDB.casesCollection.find(findQuery);
                for(Document r:result)
                {
                    HBox pane=new HBox();
                    Label lID=new Label();
                    Label ID=new Label();
                    lID.setText("Case ID:");
                    ID.setText(r.getString("caseID")+"\t");
                    Label lcity=new Label();
                    Label City=new Label();
                    lcity.setText("City:");
                    City.setText(r.getString("city"));
                    Button btn=new Button();
                    btn.setText("View Case");
                    btn.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent mouseEvent) {
                            //Open view case page in new window
                        }
                    });
                    pane.getChildren().addAll(lID,ID,lcity,City,btn);
                    pane.setPadding(new Insets(10,10,10,10));
                    vBox.getChildren().add(pane);
                }
                result=MongoDB.pstationCollection.find(findQuery);
                for(Document r:result)
                {
                    HBox pane=new HBox();
                    Label lID=new Label();
                    Label ID=new Label();
                    lID.setText("Police Station ID:");
                    ID.setText(r.getString("stationID")+"\t");
                    Label lcity=new Label();
                    Label City=new Label();
                    lcity.setText("City:");
                    City.setText(r.getString("city"));
                    pane.getChildren().addAll(lID,ID,lcity,City);
                    pane.setPadding(new Insets(10,10,10,10));
                    vBox.getChildren().add(pane);
                }
                placePanel.setContent(vBox);
            }
        }
    }
    public void onActionCancel()
    {
        searchField.setText("");
        options.setVisible(false);
        optionsPane.setVisible(false);
        searchField.setPromptText("Search");
    }
}

