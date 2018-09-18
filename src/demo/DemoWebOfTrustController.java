
package demo;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Mirko
 */
public class DemoWebOfTrustController implements Initializable {

    @FXML private Label userLabel;
    @FXML private Label user1;
    @FXML private Label user2;
    @FXML private Label user3;
    @FXML private Label user4;
    @FXML private Label user5;
    
    @FXML private Label key1;
    @FXML private Label key2;
    @FXML private Label key3;
    @FXML private Label key4;
    
    @FXML private Label legitimacy;
    @FXML private Label trust1;
    @FXML private Label trust2;
    @FXML private Label trust3;
    
    @FXML private Label l1;
    @FXML private Label l2;
    @FXML private Label l3;
    @FXML private Label l4;
    
    @FXML private TextFlow trustTextFlow;

    @FXML private Button nextButton;
    @FXML private Button backButton;
    
    @FXML private ChoiceBox juliusChoiceBox;
    @FXML private ChoiceBox donChoiceBox;
    @FXML private ChoiceBox cliffordChoiceBox;
    
    @FXML private Circle circle1;
    @FXML private Circle circle2;
    @FXML private Circle circle3;
    @FXML private Circle circle4;
    @FXML private Circle circle5;
    
    public static double t1;
    public static double t2;
    public static double t3;
    public static boolean u1;
    public static boolean u2;
    public static boolean u3;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        DemoData.webOfTrustStepCounter = 0;
        nextButton.disableProperty().set(true);
        trustTextFlow.getChildren().clear();
        
        writeText(DemoData.webOfTrustStepCounter);
        
        String imageUrl = "images/Julius.jpg";
        Image image1 = new Image(imageUrl);
        ImagePattern imagePattern1 = new ImagePattern(image1);
        circle3.setFill(imagePattern1);
        
        imageUrl = "images/Alan.jpg";
        Image image2 = new Image(imageUrl);
        ImagePattern imagePattern2 = new ImagePattern(image2);
        circle2.setFill(imagePattern2);
        
        imageUrl = "images/Phil.jpg";
        Image image3 = new Image(imageUrl);
        ImagePattern imagePattern3 = new ImagePattern(image3);
        circle1.setFill(imagePattern3);
        
        imageUrl = "images/Don.jpg";
        Image image4 = new Image(imageUrl);
        ImagePattern imagePattern4 = new ImagePattern(image4);
        circle5.setFill(imagePattern4);
        
        imageUrl = "images/Clifford.jpg";
        Image image5 = new Image(imageUrl);
        ImagePattern imagePattern5 = new ImagePattern(image5);
        circle4.setFill(imagePattern5);
        
        l1.setVisible(false);
        l2.setVisible(false);
        l3.setVisible(false);
        l4.setVisible(false);
        
        t1 = 0;
        t2 = 0;
        t3 = 0;
        u1 = false;
        u2 = false;
        u3 = false;
        
        juliusChoiceBox.getItems().addAll("UNDEFINED", "NOT TRUSTED", "USUALLY TRUSTED", "ALWAYS TRUSTED");
        donChoiceBox.getItems().addAll("UNDEFINED", "NOT TRUSTED", "USUALLY TRUSTED", "ALWAYS TRUSTED");
        cliffordChoiceBox.getItems().addAll("UNDEFINED", "NOT TRUSTED", "USUALLY TRUSTED", "ALWAYS TRUSTED");
        juliusChoiceBox.setValue("USUALLY TRUSTED");
        donChoiceBox.setValue("NOT TRUSTED");
        cliffordChoiceBox.setValue("USUALLY TRUSTED");
        juliusChoiceBox.disableProperty().set(true);
        donChoiceBox.disableProperty().set(true);
        cliffordChoiceBox.disableProperty().set(true);
        
        
        juliusChoiceBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() 
        {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) 
            {
                if(newValue == "UNDEFINED")
                {
                    trust1.setText("Owner Trust: UNDEFINED");
                    l2.setVisible(false);
                    u1 = false;
                    t1 = 0;
                }
                if(newValue == "NOT TRUSTED")
                {
                    trust1.setText("Owner Trust: NOT TRUSTED");
                    l2.setVisible(true);
                    l2.setTextFill(Color.RED);
                    u1 = true;
                    t1 = 0;
                }
                if(newValue == "USUALLY TRUSTED")
                {
                    trust1.setText("Owner Trust: USUALLY TRUSTED");
                    l2.setVisible(true);
                    l2.setTextFill(Color.BLACK);
                    u1 = true;
                    t1 = 0.5;
                }
                if(newValue == "ALWAYS TRUSTED")
                {
                    trust1.setText("Owner Trust: ALWAYS TRUSTED");
                    l2.setVisible(true);
                    l2.setTextFill(Color.GREEN);
                    u1 = true;
                    t1 = 1;
                }
                
                setLegitimacy();
            }
        });
        
        donChoiceBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() 
        {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) 
            {
                System.out.println("Promenio sam vrednost na " + newValue);
                if(newValue == "UNDEFINED")
                {
                    trust2.setText("Owner Trust: UNDEFINED");
                    l3.setVisible(false);
                    u2 = false;
                    t2 = 0;
                }
                if(newValue == "NOT TRUSTED")
                {
                    trust2.setText("Owner Trust: NOT TRUSTED");
                    l3.setVisible(true);
                    l3.setTextFill(Color.RED);
                    u2 = true;
                    t2 = 0;
                }
                if(newValue == "USUALLY TRUSTED")
                {
                    trust2.setText("Owner Trust: USUALLY TRUSTED");
                    l3.setVisible(true);
                    l3.setTextFill(Color.BLACK);
                    u2 = true;
                    t2 = 0.5;
                }
                if(newValue == "ALWAYS TRUSTED")
                {
                    trust2.setText("Owner Trust: ALWAYS TRUSTED");
                    l3.setVisible(true);
                    l3.setTextFill(Color.GREEN);
                    u2 = true;
                    t2 = 1;
                }
                
                setLegitimacy();
            }
        });
        
        cliffordChoiceBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() 
        {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) 
            {
                System.out.println("Promenio sam vrednost na " + newValue);
                if(newValue == "UNDEFINED")
                {
                    trust3.setText("Owner Trust: UNDEFINED");
                    l4.setVisible(false);
                    u3 = false;
                    t3 = 0;
                }
                if(newValue == "NOT TRUSTED")
                {
                    trust3.setText("Owner Trust: NOT TRUSTED");
                    l4.setVisible(true);
                    l4.setTextFill(Color.RED);
                    u3 = true;
                    t3 = 0;
                }
                if(newValue == "USUALLY TRUSTED")
                {
                    trust3.setText("Owner Trust: USUALLY TRUSTED");
                    l4.setVisible(true);
                    l4.setTextFill(Color.BLACK);
                    u3 = true;
                    t3 = 0.5;
                }
                if(newValue == "ALWAYS TRUSTED")
                {
                    trust3.setText("Owner Trust: ALWAYS TRUSTED");
                    l4.setVisible(true);
                    l4.setTextFill(Color.GREEN);
                    u3 = true;
                    t3 = 1;
                }
                
                setLegitimacy();
            }
        });
         
        
    }


    public void next(ActionEvent event) throws IOException
    {
        nextButton.disableProperty().set(true);
        trustTextFlow.getChildren().clear();
        
        if(DemoData.webOfTrustStepCounter < 10)
        { 
            writeText(DemoData.webOfTrustStepCounter); 
        }
        else
        {       
            Parent mainParent = FXMLLoader.load(getClass().getResource("/demo/DemoMain.fxml"));
            Scene mainScene = new Scene(mainParent);
            Stage mainWindow = (Stage)((Node)event.getSource()).getScene().getWindow();
            mainWindow.setScene(mainScene);
            mainWindow.show();
            return;
        }
        
        if(DemoData.webOfTrustStepCounter == 4)
        {
            l1.setVisible(true);
            l1.setTextFill(Color.GREEN);
            legitimacy.setText("Legitimacy: COMPLETE TRUST");
        }
        
        if(DemoData.webOfTrustStepCounter == 5)
        {
            l1.setVisible(false);
            legitimacy.setText("Legitimacy: UNKNOWN");
        }
        
        if(DemoData.webOfTrustStepCounter == 6)
        {
            l2.setVisible(true);
            l2.setTextFill(Color.BLACK);
            legitimacy.setText("Legitimacy: MARGINAL TRUST");
            t1 = 0.5;
            u1 = true;
        }
        
        if(DemoData.webOfTrustStepCounter == 7)
        {
            l3.setVisible(true);
            l3.setTextFill(Color.RED);
            t2 = 0;
            u2 = true;
        }
        
        if(DemoData.webOfTrustStepCounter == 8)
        {
            l4.setVisible(true);
            l4.setTextFill(Color.BLACK);
            legitimacy.setText("Legitimacy: COMPLETE TRUST");
            t3 = 0.5;
            u3 = true;
        }
        
        if(DemoData.webOfTrustStepCounter == 9)
        {
            nextButton.setText("Finish");
            nextButton.disableProperty().set(false);
            
            juliusChoiceBox.disableProperty().set(false);
            donChoiceBox.disableProperty().set(false);
            cliffordChoiceBox.disableProperty().set(false);
        }
    
    }
    
    
    public void returnToMenu(ActionEvent event) throws IOException
    {      
        Parent mainParent = FXMLLoader.load(getClass().getResource("/demo/DemoMain.fxml"));
        Scene mainScene = new Scene(mainParent);
        Stage mainWindow = (Stage)((Node)event.getSource()).getScene().getWindow();
        mainWindow.setScene(mainScene);
        mainWindow.show();
    }
    
    
    public void setLegitimacy()
    {
        if(!(u1) && !(u2) && !(u3))
        {
            legitimacy.setText("Legitimacy: UNKNOWN");
            return;
        }
        
        if((t1 + t2 + t3) < 0.5)
        {
            legitimacy.setText("Legitimacy: NOT TRUSTED");
            return;
        }
        else if((t1 + t2 + t3) < 1)
        {
            legitimacy.setText("Legitimacy: MARGINAL TRUST");
            return;
        }
        else
        {
            legitimacy.setText("Legitimacy: COMPLETE TRUST");
        }
    }
    
    
    public void writeText(int index)
    {   
        String text = DemoData.webOfTrustString[index];
        
        final IntegerProperty i = new SimpleIntegerProperty(0);
        Timeline timeline = new Timeline();
        KeyFrame keyFrame = new KeyFrame(
                                         Duration.millis(20),
                                         event -> 
                                         {
                                            if (i.get() >= text.length()) 
                                            {
                                                timeline.stop();
                                                nextButton.disableProperty().set(false);
                                                DemoData.webOfTrustStepCounter++;
                                                return;
                                            } 
                                            else 
                                            {
                                                Text temp = new Text(Character.toString(text.charAt(i.get())));
                                                
                                                switch(index)
                                                {
                                                    case 0:
                                                    {
                                                        temp.setFont(Font.font ("Verdana", 15));
                                                        temp.setFill(Color.WHITE);
                                                        
                                                        break;
                                                    }
                                                    
                                                    case 1:
                                                    {
                                                        temp.setFont(Font.font ("Verdana", 15));
                                                        temp.setFill(Color.WHITE);
                                                        
                                                        break;
                                                    }
                                                    
                                                    case 2:
                                                    {
                                                        if( ((i.get() > 40) && (i.get() < 51)) )
                                                        {
                                                            temp.setFont(Font.font ("Verdana", FontWeight.BOLD, 15));
                                                            temp.setFill(Color.YELLOW);
                                                        }
                                                        else
                                                        {
                                                            temp.setFont(Font.font ("Verdana", 15));
                                                            temp.setFill(Color.WHITE);
                                                        }
                                                        break;
                                                    }
                                                    
                                                    case 3:
                                                    {
                                                        temp.setFont(Font.font ("Verdana", 15));
                                                        temp.setFill(Color.WHITE);
                                                        
                                                        break;
                                                    }
                                                    
                                                    case 4:
                                                    {
                                                        if( ((i.get() > 6) && (i.get() < 12)) || ((i.get() > 59) && (i.get() < 72)) || 
                                                            ((i.get() > 124) && (i.get() < 139)) )
                                                        {
                                                            temp.setFont(Font.font ("Verdana", FontWeight.BOLD, 15));
                                                            temp.setFill(Color.YELLOW);
                                                        }
                                                        else
                                                        {
                                                            temp.setFont(Font.font ("Verdana", 15));
                                                            temp.setFill(Color.WHITE);
                                                        }
                                                        break;
                                                    }
                                                    
                                                    case 5:
                                                    {
                                                        if( ((i.get() > 30) && (i.get() < 61)) )
                                                        {
                                                            temp.setFont(Font.font ("Verdana", FontWeight.BOLD, 15));
                                                            temp.setFill(Color.YELLOW);
                                                        }
                                                        else
                                                        {
                                                            temp.setFont(Font.font ("Verdana", 15));
                                                            temp.setFill(Color.WHITE);
                                                        }
                                                        break;
                                                    }
                                                    
                                                    case 6:
                                                    {
                                                        temp.setFont(Font.font ("Verdana", 15));
                                                        temp.setFill(Color.WHITE);
                                                        
                                                        break;
                                                    }
                                                    
                                                    case 7:
                                                    {
                                                        temp.setFont(Font.font ("Verdana", 15));
                                                        temp.setFill(Color.WHITE);
                                                        
                                                        break;
                                                    }
                                                    
                                                    case 8:
                                                    {
                                                        temp.setFont(Font.font ("Verdana", 15));
                                                        temp.setFill(Color.WHITE);
                                                        
                                                        break;
                                                    }
                                                    
                                                    case 9:
                                                    {
                                                        temp.setFont(Font.font ("Verdana", 15));
                                                        temp.setFill(Color.WHITE);
                                                        
                                                        break;
                                                    }
                                                }
                                                
                                                trustTextFlow.getChildren().add(temp);
                                                i.set(i.get() + 1);
                                            }
                                          });
        timeline.getKeyFrames().add(keyFrame);
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }
    
    
}
