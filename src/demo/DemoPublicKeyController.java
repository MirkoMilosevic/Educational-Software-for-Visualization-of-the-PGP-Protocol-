
package demo;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.util.Duration;
import util.MyData;

/**
 * FXML Controller class
 *
 * @author Mirko
 */
public class DemoPublicKeyController implements Initializable {

    
    @FXML private Label user1;
    @FXML private Label user2;
    @FXML private Label user3;
    @FXML private Label keyId1;
    @FXML private Label keyId2;
    @FXML private Label keyId3;
    @FXML private Label ownerTrust1;
    @FXML private Label ownerTrust2;
    @FXML private Label ownerTrust3;
    @FXML private Label signatures;
    @FXML private Label signatureTrusts;
    @FXML private Label keyLegitimacy1;
    @FXML private Label keyLegitimacy2;
    @FXML private Label keyLegitimacy3;
    
    @FXML private TextFlow keyRingTextFlow;
    
    @FXML private Button signaturesButton;
    @FXML private Button signaturesButton2;
    @FXML private Button signatureTrustsButton;
    @FXML private Button signatureTrustsButton2;
    @FXML private Button nextButton;
    @FXML private Button backButton;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        signaturesButton.disableProperty().set(true);
        signatureTrustsButton.disableProperty().set(true);
        signaturesButton2.disableProperty().set(true);
        signatureTrustsButton2.disableProperty().set(true);
        nextButton.disableProperty().set(true);
        
        DemoData.publicKeyStepCounter = 0;
        keyRingTextFlow.getChildren().clear();
        
        writeText(DemoData.publicKeyStepCounter);
    }    
 
    
    public void next(ActionEvent event) throws IOException
    {
        nextButton.disableProperty().set(true);
        keyRingTextFlow.getChildren().clear();
        
        if(DemoData.publicKeyStepCounter < 7)
        { 
            writeText(DemoData.publicKeyStepCounter); 
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
        
        if(DemoData.publicKeyStepCounter == 1)
        {
            user2.setText(MyData.user2.getEmail());
            keyId2.setText("5226cba4b0f71249");
        }
        
        if(DemoData.publicKeyStepCounter == 2)
        {
            ownerTrust2.setText("USUALLY TRUSTED");
        }
        
        if(DemoData.publicKeyStepCounter == 4)
        {
            keyLegitimacy2.setText("UNKNOWN");
        }
        
        if(DemoData.publicKeyStepCounter == 5)
        {
            signaturesButton.disableProperty().set(false);
            signatureTrustsButton.disableProperty().set(false);
            
            user3.setText("phil.zimmermann@pgp.com");
            keyId3.setText("d2cf90e5552b853b");
            ownerTrust3.setText("ALWAYS TRUSTED");
            keyLegitimacy3.setText("UNKNOWN");   
        }
        
        if(DemoData.publicKeyStepCounter == 6)
        {
            keyLegitimacy2.setText("COMPLETE TRUST");
            
            nextButton.setText("Finish");
            nextButton.disableProperty().set(false);
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
    
    
    public void viewSignatures()
    {
        Alert alertBox = new Alert(Alert.AlertType.INFORMATION);
        alertBox.setTitle("Signatures");
        alertBox.setHeaderText(null);
        String signatures = "Signatures:" + "\n" + "\n" +
                            "User ID: phil.zimmermann@pgp.com" + "\n" + 
                            "Key ID: d2cf90e5552b853b";
        alertBox.setContentText(signatures);
        alertBox.showAndWait();
    }
    
    
    public void viewSignatureTrusts()
    {
        Alert alertBox = new Alert(Alert.AlertType.INFORMATION);
        alertBox.setTitle("Signature Trusts");
        alertBox.setHeaderText(null);
        String signatures = "Signature Trusts:" + "\n" + "\n" +
                            "Key ID: d2cf90e5552b853b" + "\n" + 
                            "Trust: ALWAYS TRUSTED";
        alertBox.setContentText(signatures);
        alertBox.showAndWait();
    }
    
    
    public void writeText(int index)
    {   
        String text = DemoData.publicKeyString[index];
        
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
                                                DemoData.publicKeyStepCounter++;
                                                return;
                                            } 
                                            else 
                                            {
                                                Text temp = new Text(Character.toString(text.charAt(i.get())));
                                                
                                                switch(index)
                                                {
                                                    case 0:
                                                    {
                                                        if( ((i.get() >= 0) && (i.get() < 16)) || ((i.get() > 59) && (i.get() < 72)) )
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
                                                    
                                                    case 1:
                                                    {
                                                        if( ((i.get() >= 204) && (i.get() < 265)) )
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
                                                    
                                                    case 2:
                                                    {
                                                        if( ((i.get() >= 0) && (i.get() < 13)) || ((i.get() > 69) && (i.get() < 96)) ||
                                                            ((i.get() > 117) && (i.get() < 131)) || ((i.get() > 172) && (i.get() < 229)) )
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
                                                        if((i.get() >= 0) && (i.get() < 32))
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
                                                    
                                                    case 4:
                                                    {
                                                        if( ((i.get() >= 0) && (i.get() < 15)) || ((i.get() > 75) && (i.get() < 106)) ||
                                                            ((i.get() > 148) && (i.get() < 201)) )
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
                                                        temp.setFont(Font.font ("Verdana", 15));
                                                        temp.setFill(Color.WHITE);
                                                        
                                                        break;
                                                    }
                                                    
                                                    case 6:
                                                    {
                                                        temp.setFont(Font.font ("Verdana", 15));
                                                        temp.setFill(Color.WHITE);
                                                        
                                                        break;
                                                    }
                                                    
                                                }
                                                
                                                keyRingTextFlow.getChildren().add(temp);
                                                i.set(i.get() + 1);
                                            }
                                          });
        timeline.getKeyFrames().add(keyFrame);
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }
    
    
}
