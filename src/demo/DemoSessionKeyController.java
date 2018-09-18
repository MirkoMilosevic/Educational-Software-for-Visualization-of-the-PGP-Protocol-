
package demo;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.util.Duration;
import util.SendMessageData;

/**
 * FXML Controller class
 *
 * @author Mirko
 */
public class DemoSessionKeyController implements Initializable {

    @FXML private Label typeLabel;
    @FXML private Label hashAlgLabel;
    @FXML private Label saltLabel;
    @FXML private Label passphraseLabel;
    @FXML private Label hashCodeLabel;
    @FXML private Label sessionKeyLabel;
    @FXML private Label s2kLabel;
    
    @FXML private TextFlow sessionKeyTextFlow;
    @FXML private Button nextButton;
    @FXML private Button backButton;
    
    public static byte[] hashCode;
    public static String passphrase;
    public static String salt;
    
    public static String hashCodeString;
    public static String sessionKeyString;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        DemoData.sessionKeyStepCounter = 0;
        nextButton.disableProperty().set(true);
        sessionKeyTextFlow.getChildren().clear();
        
        try {
            createSessionKey();
        } catch (IOException ex) {
            Logger.getLogger(DemoSessionKeyController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(DemoSessionKeyController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        writeText(DemoData.sessionKeyStepCounter);
    } 
    
    
    public void next(ActionEvent event) throws IOException
    {
        nextButton.disableProperty().set(true);
        sessionKeyTextFlow.getChildren().clear();
        
        if(DemoData.sessionKeyStepCounter < 7)
        { 
            writeText(DemoData.sessionKeyStepCounter); 
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
        
        if(DemoData.sessionKeyStepCounter == 1)
        {
            typeLabel.setText("Simple");
            hashAlgLabel.setText("MD5");
        }
        
        if(DemoData.sessionKeyStepCounter == 3)
        {
            typeLabel.setText("Salted");
            saltLabel.setText(salt);
            hashAlgLabel.setText("MD5");
        }
        
        if(DemoData.sessionKeyStepCounter == 4)
        {
            typeLabel.setText("Salted");
            saltLabel.setText(salt);
            hashAlgLabel.setText("SHA-1");
            passphraseLabel.setText(passphrase);
        }
        
        if(DemoData.sessionKeyStepCounter == 5)
        {
             hashCodeLabel.setText(hashCodeString);
        }
        
        if(DemoData.sessionKeyStepCounter == 6)
        {
            sessionKeyLabel.setText(sessionKeyString);
            
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
    
    
    public void writeText(int index)
    {   
        String text = DemoData.sessionKeyString[index];
        
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
                                                DemoData.sessionKeyStepCounter++;
                                                return;
                                            } 
                                            else 
                                            {
                                                Text temp = new Text(Character.toString(text.charAt(i.get())));
                                                
                                                switch(index)
                                                {
                                                    case 0:
                                                    {
                                                        if( ((i.get() >= 0) && (i.get() < 30)) || ((i.get() > 50) && (i.get() < 61)) ||
                                                            ((i.get() > 74) && (i.get() < 87)) )
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
                                                        if( ((i.get() > 10) && (i.get() < 17)) || ((i.get() > 47) && (i.get() < 59)) )
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
                                                        if( ((i.get() > 24) && (i.get() < 32)) || ((i.get() > 63) && (i.get() < 85)) ||
                                                            ((i.get() > 156) && (i.get() < 161)) || ((i.get() > 180) && (i.get() < 199)) ||
                                                            ((i.get() > 232) && (i.get() < 245)) )
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
                                                        if( ((i.get() >= 0) && (i.get() < 10)) || ((i.get() > 89) && (i.get() < 106)) )
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
                                                
                                                sessionKeyTextFlow.getChildren().add(temp);
                                                i.set(i.get() + 1);
                                            }
                                          });
        timeline.getKeyFrames().add(keyFrame);
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }
    
    
    public void createSessionKey() throws IOException, NoSuchAlgorithmException
    {   
        String seedString = "";
        for(int i=0; i<SendMessageData.seed.length; i++)
        {
            int n = SendMessageData.seed[i] & 0xFF;
            String p = Integer.toHexString(n);
            if((SendMessageData.seed[i] & 0xF0) == 0) { seedString += "0"; }
            seedString += p;
            seedString += " ";
        }
       
        salt = seedString;
        
        String pass = "This is super secret passphrase, 123@pgp!!!";
        passphrase = pass;
        
        byte[] packet = null;
        byte[] hash = null;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        outputStream.write(SendMessageData.seed);
        outputStream.write(passphrase.getBytes());
        packet = outputStream.toByteArray();
        MessageDigest md = MessageDigest.getInstance("SHA-1");
        hash = md.digest(packet);
        
        hashCode = hash;
        
        String hashString = "";
        for(int i=0; i<hash.length; i++)
        {
            int n = hash[i] & 0xFF;
            String p = Integer.toHexString(n);
            if((hash[i] & 0xF0) == 0) { hashString += "0"; }
            hashString += p;
            hashString += " ";
        }
        
        hashCodeString = hashString;
        
        String keyString = "";
        for(int i=0; i<hash.length-4; i++)
        {
            int n = hash[i] & 0xFF;
            String p = Integer.toHexString(n);
            if((hash[i] & 0xF0) == 0) { keyString += "0"; }
            keyString += p;
            keyString += " ";
        }
        
        sessionKeyString = keyString;
       
    }
    
    
}
