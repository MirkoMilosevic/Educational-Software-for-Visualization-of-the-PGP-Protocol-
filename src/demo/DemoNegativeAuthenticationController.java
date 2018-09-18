
package demo;

import static demo.DemoData.demoMessageData;
import static demo.DemoData.demoSignatureTimestamp;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;
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
public class DemoNegativeAuthenticationController implements Initializable {

    @FXML private Button nextButton;
    @FXML private Label userLabel;
    @FXML private Label hash1Label;
    @FXML private Label hash2Label;
    @FXML private TextArea negativeTextArea;
    @FXML private TextFlow negativeTextFlow;
    
    @FXML private Button backButton;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        negativeTextArea.setEditable(false);
        String style = "-fx-text-fill: white;";
        negativeTextArea.setStyle(style);
        
        DemoData.negativeStepCounter = 0;
        DemoData.negative = true;
        
        nextButton.disableProperty().set(true);
        negativeTextFlow.getChildren().clear();
        
        writeText(DemoData.negativeStepCounter);
    }    
    
    
    public void next(ActionEvent event) throws IOException, NoSuchAlgorithmException, InvalidKeyException, SignatureException
    {
        nextButton.disableProperty().set(true);
        negativeTextFlow.getChildren().clear();
        
        if(DemoData.negativeStepCounter < 7)
            { writeText(DemoData.negativeStepCounter); }
            else
            {
                DemoData.negative = false;
                
                Parent mainParent = FXMLLoader.load(getClass().getResource("/demo/DemoMain.fxml"));
                Scene mainScene = new Scene(mainParent);
                Stage mainWindow = (Stage)((Node)event.getSource()).getScene().getWindow();
                mainWindow.setScene(mainScene);
                mainWindow.show();
                return;
            }
        
        if(DemoData.negativeStepCounter == 1)
        {
            DemoData.initMessage();
            formatMessage();
            negativeTextArea.positionCaret(0);
        }
        
        if(DemoData.negativeStepCounter == 2)
        {
            DemoData.initSignature();
            formatSignature();
            negativeTextArea.positionCaret(0);
            
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            //outputStream.write(DemoData.demoSignatureTimestamp.getBytes());
            outputStream.write(DemoData.demoMessageData.getBytes());
            byte[] packet = outputStream.toByteArray();        
            byte[] hash = md.digest(packet);
        
            String Hash1 = "";
            for(int i=0; i<hash.length; i++)
            {
                int n = hash[i] & 0xFF;
                String p = Integer.toHexString(n);
                if((hash[i] & 0xF0) == 0) { Hash1 += "0"; }
                Hash1 += p;
                Hash1 += " ";
            }
            
            hash1Label.setText("Hash code of the original message: " + Hash1);
        }
        
        if(DemoData.negativeStepCounter == 3)
        {
            DemoData.demoMessageData = "Street: Francuska Number 139" + "\n" +
                                       "Telephone number: 236398" + "\n" +
                                       "Email: owner@threepalmtrees.com" + "\n" +
                                       "FAX: 016337756";
            
            formatSignature();
            negativeTextArea.positionCaret(0);
            
            userLabel.setText("Attacker");
        }
        
        if(DemoData.negativeStepCounter == 4)
        {
            userLabel.setText("Recipient");
            
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            //outputStream.write(DemoData.demoSignatureTimestamp.getBytes());
            outputStream.write(DemoData.demoMessageData.getBytes());
            byte[] packet = outputStream.toByteArray();        
            byte[] hash = md.digest(packet);
        
            String Hash2 = "";
            for(int i=0; i<hash.length; i++)
            {
                int n = hash[i] & 0xFF;
                String p = Integer.toHexString(n);
                if((hash[i] & 0xF0) == 0) { Hash2 += "0"; }
                Hash2 += p;
                Hash2 += " ";
            }
            
            hash2Label.setText("Hash code of the received message: " + Hash2);
        }
        
        if(DemoData.negativeStepCounter == 6)
        {
            nextButton.setText("Finish");
        }
        
    }
    
    
    public void returnToMenu(ActionEvent event) throws IOException
    {        
        DemoData.negative = false;
                
        Parent mainParent = FXMLLoader.load(getClass().getResource("/demo/DemoMain.fxml"));
        Scene mainScene = new Scene(mainParent);
        Stage mainWindow = (Stage)((Node)event.getSource()).getScene().getWindow();
        mainWindow.setScene(mainScene);
        mainWindow.show();
    }
    
    
    public void writeText(int index)
    {   
        String text = DemoData.negativeString[index];
        
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
                                                DemoData.negativeStepCounter++;
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
                                                        temp.setFont(Font.font ("Verdana", 15));
                                                        temp.setFill(Color.WHITE);
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
                                                        temp.setFont(Font.font ("Verdana", 15));
                                                        temp.setFill(Color.WHITE);
                                                        
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
                                                
                                                negativeTextFlow.getChildren().add(temp);
                                                i.set(i.get() + 1);
                                            }
                                          });
        timeline.getKeyFrames().add(keyFrame);
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }
    
    
    public void formatMessage()
    {
        String newLine = "\n";
        negativeTextArea.setText("---------- BEGIN PGP MESSAGE ----------");
        negativeTextArea.appendText(newLine);
        negativeTextArea.appendText(newLine);
        negativeTextArea.appendText("------- BEGIN MESSAGE -------");
        negativeTextArea.appendText(newLine);
        negativeTextArea.appendText(newLine);
        negativeTextArea.appendText("---- Filename ----");
        negativeTextArea.appendText(newLine);
        negativeTextArea.appendText(DemoData.demoMessageFilename);
        negativeTextArea.appendText(newLine);
        negativeTextArea.appendText("---- Timestamp ----");
        negativeTextArea.appendText(newLine);
        negativeTextArea.appendText(DemoData.demoMessageTimestamp);
        negativeTextArea.appendText(newLine);
        negativeTextArea.appendText("---- Data ----");
        negativeTextArea.appendText(newLine);
        negativeTextArea.appendText(DemoData.demoMessageData);
        negativeTextArea.appendText(newLine);
        negativeTextArea.appendText(newLine);
        negativeTextArea.appendText("------- END MESSAGE -------");
        negativeTextArea.appendText(newLine);
        negativeTextArea.appendText(newLine);
        negativeTextArea.appendText("---------- END PGP MESSAGE ----------");
    }
    
    public void formatSignature()
    {
        String newLine = "\n";
        negativeTextArea.setText("---------- BEGIN PGP MESSAGE ----------");
        negativeTextArea.appendText(newLine);
        negativeTextArea.appendText(newLine);
        negativeTextArea.appendText("------- BEGIN SIGNATURE -------");
        negativeTextArea.appendText(newLine);
        negativeTextArea.appendText(newLine);
        negativeTextArea.appendText("--- Timestamp ---");
        negativeTextArea.appendText(newLine);
        negativeTextArea.appendText(DemoData.demoSignatureTimestamp);
        negativeTextArea.appendText(newLine);
        negativeTextArea.appendText("--- Public Key Algorithm ---");
        negativeTextArea.appendText(newLine);
        negativeTextArea.appendText(DemoData.demoSignatureAlgorithm);
        negativeTextArea.appendText(newLine);
        negativeTextArea.appendText("--- Hash Algorithm ---");
        negativeTextArea.appendText(newLine);
        negativeTextArea.appendText(DemoData.demoHashAlgorithm);
        negativeTextArea.appendText(newLine);
        negativeTextArea.appendText("--- Key Id of Sender's Public Key ---");
        negativeTextArea.appendText(newLine);
        negativeTextArea.appendText(DemoData.demoSignatureKeyId);
        negativeTextArea.appendText(newLine);
        negativeTextArea.appendText("--- Leading Two Octets of Message Digest ---");
        negativeTextArea.appendText(newLine);
        negativeTextArea.appendText(DemoData.demoSignatureLeadingOctets);
        negativeTextArea.appendText(newLine);
        negativeTextArea.appendText("--- Message Digest ---");
        negativeTextArea.appendText(newLine);
        negativeTextArea.appendText(DemoData.demoSignatureMessageDigest);
        negativeTextArea.appendText(newLine);
        negativeTextArea.appendText(newLine);
        negativeTextArea.appendText("------- END SIGNATURE -------");
        negativeTextArea.appendText(newLine);
        negativeTextArea.appendText(newLine);
        negativeTextArea.appendText("------- BEGIN MESSAGE -------");
        negativeTextArea.appendText(newLine);
        negativeTextArea.appendText(newLine);
        negativeTextArea.appendText("---- Filename ----");
        negativeTextArea.appendText(newLine);
        negativeTextArea.appendText(DemoData.demoMessageFilename);
        negativeTextArea.appendText(newLine);
        negativeTextArea.appendText("---- Timestamp ----");
        negativeTextArea.appendText(newLine);
        negativeTextArea.appendText(DemoData.demoMessageTimestamp);
        negativeTextArea.appendText(newLine);
        negativeTextArea.appendText("---- Data ----");
        negativeTextArea.appendText(newLine);
        negativeTextArea.appendText(DemoData.demoMessageData);
        negativeTextArea.appendText(newLine);
        negativeTextArea.appendText(newLine);
        negativeTextArea.appendText("------- END MESSAGE -------");
        negativeTextArea.appendText(newLine);
        negativeTextArea.appendText(newLine);
        negativeTextArea.appendText("---------- END PGP MESSAGE ----------");
    }
    
    
}
