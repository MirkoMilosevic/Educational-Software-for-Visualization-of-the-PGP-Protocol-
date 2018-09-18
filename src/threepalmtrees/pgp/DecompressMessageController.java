
package threepalmtrees.pgp;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import scheme.EncryptSchemeController;
import scheme.SignAndEncryptSchemeController;
import scheme.SignSchemeController;
import util.MyData;
import util.SchemeData;
import util.SendMessageData;

/**
 * FXML Controller class
 *
 * @author Mirko
 */
public class DecompressMessageController implements Initializable {

    @FXML private TextArea decompressTextArea;
    @FXML private Button decompressButton;
    @FXML private Button nextButton;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        String compressedString = "";
        for(int i=0; i<SendMessageData.compressedData.length; i++)
        {  
            int m = SendMessageData.compressedData[i] & 0xFF;
            String n = Character.toString((char) m);
            if(!n.equals("\n"))
            { compressedString += n; }
            if((i % 32) == 31) { compressedString += "\n"; }
        }
        
        String newLine = "\n";
        decompressTextArea.setText("---------- BEGIN PGP MESSAGE ----------");
        decompressTextArea.appendText(newLine);
        decompressTextArea.appendText(newLine);
        
        if((SendMessageData.selectedMode == 210) || (SendMessageData.selectedMode == 211) ||
           (SendMessageData.selectedMode == 310) || (SendMessageData.selectedMode == 311))
        {
            String sessionKeyString = "";
            for(int i=0; i<SendMessageData.sessionKey.length; i++)
            {
                int n = SendMessageData.sessionKey[i] & 0xFF;
                String p = Integer.toHexString(n);
                if((SendMessageData.sessionKey[i] & 0xF0) == 0) { sessionKeyString += "0"; }
                sessionKeyString += p;
                sessionKeyString += " ";
                if( ((i % 8) == 7) && (i != 15)) { sessionKeyString += "\n"; }
            }
            
            decompressTextArea.appendText("---------- BEGIN SYMETRIC KEY PACKET ----------");
            decompressTextArea.appendText(newLine);
            decompressTextArea.appendText(newLine);
            decompressTextArea.appendText("--- Key Id of Recipient's Public Key ---"); 
            decompressTextArea.appendText(newLine);
            decompressTextArea.appendText(SendMessageData.recipientPublicKeyId);
            decompressTextArea.appendText(newLine);
            decompressTextArea.appendText("--- Public Key Algorithm ---"); 
            decompressTextArea.appendText(newLine);
            decompressTextArea.appendText(SendMessageData.publicKeyAlgorithm);
            decompressTextArea.appendText(newLine);
            decompressTextArea.appendText("--- Symetric Algorithm ---"); 
            decompressTextArea.appendText(newLine);
            decompressTextArea.appendText(SendMessageData.symetricAlgorithm);
            decompressTextArea.appendText(newLine);
            decompressTextArea.appendText("--- Session Key ---");
            decompressTextArea.appendText(newLine);
            decompressTextArea.appendText(sessionKeyString);
            decompressTextArea.appendText(newLine);
            decompressTextArea.appendText(newLine);
            decompressTextArea.appendText("---------- END SYMETRIC KEY PACKET ----------");
            decompressTextArea.appendText(newLine);
            decompressTextArea.appendText(newLine);
        }
        
        decompressTextArea.appendText("------- BEGIN COMPRESSED DATA -------");
        decompressTextArea.appendText(newLine);
        decompressTextArea.appendText(newLine);
        decompressTextArea.appendText(compressedString);
        decompressTextArea.appendText(newLine);
        decompressTextArea.appendText(newLine);
        decompressTextArea.appendText("------- END COMPRESSED DATA -------");
        decompressTextArea.appendText(newLine);
        decompressTextArea.appendText(newLine);
        decompressTextArea.appendText("---------- END PGP MESSAGE ----------");
        
        decompressTextArea.setEditable(false);
        
        nextButton.disableProperty().set(true);
        
        Tooltip decompressTooltip = new Tooltip();
        decompressTooltip.setText("This will decompress your message");
        decompressButton.setTooltip(decompressTooltip);
        
        MainController.mainStatusLabel.setText("In this window you are decompresing your message");
        
        String style = "-fx-text-fill: white; -fx-font-size: 16px;";
        
        decompressTextArea.setStyle(style);
        
        if(SendMessageData.selectedMode == 110)
        {
            if(SignSchemeController.ssButton1 != null)
            {
                SignSchemeController.ss22.setTextFill(Color.RED);
                SignSchemeController.ss26.setTextFill(Color.RED);
                SignSchemeController.ss27.setTextFill(Color.RED);
                SignSchemeController.ss28.setTextFill(Color.RED);
                SignSchemeController.ss29.setTextFill(Color.RED);
                
                style = "-fx-border-color: red; -fx-border-width: 3 3 3 3;";
                SignSchemeController.ssButton5.setStyle(style);
                style = "-fx-border-width: 0;";
                SignSchemeController.ssButton3.setStyle(style);
            }
            
            SchemeData.sLabel[22] = true;
            SchemeData.sLabel[26] = true;
            SchemeData.sLabel[27] = true;
            SchemeData.sLabel[28] = true;
            SchemeData.sLabel[29] = true;
            SchemeData.sButton[3] = false;
            SchemeData.sButton[5] = true;
        }
        
        decompressTextArea.positionCaret(0);
    }    
    
    public void decompress()
    {
        String newLine = "\n";
        decompressTextArea.setText("---------- BEGIN PGP MESSAGE ----------");
        decompressTextArea.appendText(newLine);
        decompressTextArea.appendText(newLine);
        
        if((SendMessageData.selectedMode == 210) || (SendMessageData.selectedMode == 211) ||
           (SendMessageData.selectedMode == 310) || (SendMessageData.selectedMode == 311))
        {
            String sessionKeyString = "";
            for(int i=0; i<SendMessageData.sessionKey.length; i++)
            {
                int n = SendMessageData.sessionKey[i] & 0xFF;
                String p = Integer.toHexString(n);
                if((SendMessageData.sessionKey[i] & 0xF0) == 0) { sessionKeyString += "0"; }
                sessionKeyString += p;
                sessionKeyString += " ";
                if( ((i % 8) == 7) && (i != 15)) { sessionKeyString += "\n"; }
            }
            
            decompressTextArea.appendText("---------- BEGIN SYMETRIC KEY PACKET ----------");
            decompressTextArea.appendText(newLine);
            decompressTextArea.appendText(newLine);
            decompressTextArea.appendText("--- Key Id of Recipient's Public Key ---"); 
            decompressTextArea.appendText(newLine);
            decompressTextArea.appendText(SendMessageData.recipientPublicKeyId);
            decompressTextArea.appendText(newLine);
            decompressTextArea.appendText("--- Public Key Algorithm ---"); 
            decompressTextArea.appendText(newLine);
            decompressTextArea.appendText(SendMessageData.publicKeyAlgorithm);
            decompressTextArea.appendText(newLine);
            decompressTextArea.appendText("--- Symetric Algorithm ---"); 
            decompressTextArea.appendText(newLine);
            decompressTextArea.appendText(SendMessageData.symetricAlgorithm);
            decompressTextArea.appendText(newLine);
            decompressTextArea.appendText("--- Session Key ---");
            decompressTextArea.appendText(newLine);
            decompressTextArea.appendText(sessionKeyString);
            decompressTextArea.appendText(newLine);
            decompressTextArea.appendText(newLine);
            decompressTextArea.appendText("---------- END SYMETRIC KEY PACKET ----------");
            decompressTextArea.appendText(newLine);
            decompressTextArea.appendText(newLine);
            
            if((SendMessageData.selectedMode == 310) || (SendMessageData.selectedMode == 311))
            {
                String encryptedSignature = "";
                for(int i=0; i<SendMessageData.message.getSignature().getSignature().length; i++)
                {  
                    int m = SendMessageData.message.getSignature().getSignature()[i] & 0xFF;
                    String n = Character.toString((char) m);
                    if(!n.equals("\n"))
                    { encryptedSignature += n; }
                    if((i % 32) == 31) { encryptedSignature += "\n"; }
                }
                
                decompressTextArea.appendText("------- BEGIN SIGNATURE -------");
                decompressTextArea.appendText(newLine);
                decompressTextArea.appendText(newLine);
                decompressTextArea.appendText("--- Timestamp ---");
                decompressTextArea.appendText(newLine);
                decompressTextArea.appendText(SendMessageData.message.getSignature().getTimestamp());
                decompressTextArea.appendText(newLine);
                decompressTextArea.appendText("--- Public Key Algorithm ---");
                decompressTextArea.appendText(newLine);
                decompressTextArea.appendText(SendMessageData.signatureAlgorithm);
                decompressTextArea.appendText(newLine);
                decompressTextArea.appendText("--- Hash Algorithm ---");
                decompressTextArea.appendText(newLine);
                decompressTextArea.appendText(SendMessageData.hashAlgorithm);
                decompressTextArea.appendText(newLine);
                decompressTextArea.appendText("--- Key Id of Sender's Public Key ---");
                decompressTextArea.appendText(newLine);
                decompressTextArea.appendText(SendMessageData.keyId);
                decompressTextArea.appendText(newLine);
                decompressTextArea.appendText("--- Leading Two Octets of Message Digest ---");
                decompressTextArea.appendText(newLine);
                decompressTextArea.appendText(SendMessageData.message.getSignature().getLeading2octets());
                decompressTextArea.appendText(newLine);
                decompressTextArea.appendText("--- Message Digest ---");
                decompressTextArea.appendText(newLine);
                decompressTextArea.appendText(encryptedSignature);
                decompressTextArea.appendText(newLine);
                decompressTextArea.appendText(newLine);
                decompressTextArea.appendText("------- END SIGNATURE -------");
                decompressTextArea.appendText(newLine);
                decompressTextArea.appendText(newLine);
            }
        }
        
        if((SendMessageData.selectedMode == 110) || (SendMessageData.selectedMode == 111))
        {
            String encryptedSignature = "";
            for(int i=0; i<SendMessageData.message.getSignature().getSignature().length; i++)
                {  
                    int m = SendMessageData.message.getSignature().getSignature()[i] & 0xFF;
                    String n = Character.toString((char) m);
                    if(!n.equals("\n"))
                    { encryptedSignature += n; }
                    if((i % 32) == 31) { encryptedSignature += "\n"; }
                }
                
            decompressTextArea.appendText("------- BEGIN SIGNATURE -------");
            decompressTextArea.appendText(newLine);
            decompressTextArea.appendText(newLine);
            decompressTextArea.appendText("--- Timestamp ---");
            decompressTextArea.appendText(newLine);
            decompressTextArea.appendText(SendMessageData.message.getSignature().getTimestamp());
            decompressTextArea.appendText(newLine);
            decompressTextArea.appendText("--- Public Key Algorithm ---");
            decompressTextArea.appendText(newLine);
            decompressTextArea.appendText(SendMessageData.signatureAlgorithm);
            decompressTextArea.appendText(newLine);
            decompressTextArea.appendText("--- Hash Algorithm ---");
            decompressTextArea.appendText(newLine);
            decompressTextArea.appendText(SendMessageData.hashAlgorithm);
            decompressTextArea.appendText(newLine);
            decompressTextArea.appendText("--- Key Id of Sender's Public Key ---");
            decompressTextArea.appendText(newLine);
            decompressTextArea.appendText(SendMessageData.keyId);
            decompressTextArea.appendText(newLine);
            decompressTextArea.appendText("--- Leading Two Octets of Message Digest ---");
            decompressTextArea.appendText(newLine);
            decompressTextArea.appendText(SendMessageData.message.getSignature().getLeading2octets());
            decompressTextArea.appendText(newLine);
            decompressTextArea.appendText("--- Message Digest ---");
            decompressTextArea.appendText(newLine);
            decompressTextArea.appendText(encryptedSignature);
            decompressTextArea.appendText(newLine);
            decompressTextArea.appendText(newLine);
            decompressTextArea.appendText("------- END SIGNATURE -------");
            decompressTextArea.appendText(newLine);
            decompressTextArea.appendText(newLine);
        }
        
        decompressTextArea.appendText("------- BEGIN MESSAGE -------");
        decompressTextArea.appendText(newLine);
        decompressTextArea.appendText(newLine);
        decompressTextArea.appendText("---- Filename ----");
        decompressTextArea.appendText(newLine);
        decompressTextArea.appendText(SendMessageData.message.getFilename());
        decompressTextArea.appendText(newLine);
        decompressTextArea.appendText("---- Timestamp ----");
        decompressTextArea.appendText(newLine);
        decompressTextArea.appendText(SendMessageData.message.getTimestamp());
        decompressTextArea.appendText(newLine);
        decompressTextArea.appendText("---- Data ----");
        decompressTextArea.appendText(newLine);
        decompressTextArea.appendText(SendMessageData.message.getData());
        decompressTextArea.appendText(newLine);
        decompressTextArea.appendText(newLine);
        decompressTextArea.appendText("------- END MESSAGE -------");
        decompressTextArea.appendText(newLine);
        decompressTextArea.appendText(newLine);
        decompressTextArea.appendText("---------- END PGP MESSAGE ----------");
        
        nextButton.disableProperty().set(false);
        
        if((SendMessageData.selectedMode == 110) || (SendMessageData.selectedMode == 111))
        {
            if(SignSchemeController.ssButton1 != null)
            {
                SignSchemeController.ss30.setTextFill(Color.RED);
                SignSchemeController.ss33.setTextFill(Color.RED);
            }
            
            SchemeData.sLabel[30] = true;
            SchemeData.sLabel[33] = true;
        }
        
        if((SendMessageData.selectedMode == 210) || (SendMessageData.selectedMode == 211))
        {
            if(EncryptSchemeController.eeButton1 != null)
            {
                EncryptSchemeController.ee39.setTextFill(Color.RED);
                EncryptSchemeController.ee43.setTextFill(Color.RED);
                
                String style = "-fx-border-color: red; -fx-border-width: 3 3 3 3;";
                EncryptSchemeController.eeButton8.setStyle(style);
                style = "-fx-border-width: 0;";
                EncryptSchemeController.eeButton7.setStyle(style);
            }
             
            SchemeData.eLabel[39] = true;
            SchemeData.eLabel[43] = true;
            SchemeData.eButton[7] = false;
            SchemeData.eButton[8] = true;
        }
        
         if((SendMessageData.selectedMode == 310) || (SendMessageData.selectedMode == 311))
        {
            if(SignAndEncryptSchemeController.bbButton1 != null)
            {
                SignAndEncryptSchemeController.bb47.setTextFill(Color.RED);
                SignAndEncryptSchemeController.bb51.setTextFill(Color.RED);
            }
            
            SchemeData.bLabel[47] = true;
            SchemeData.bLabel[51] = true;
            
            if(SignAndEncryptSchemeController.bbButton1 != null)
            {
                String style = "-fx-border-color: red; -fx-border-width: 3 3 3 3;";
                SignAndEncryptSchemeController.bbButton9.setStyle(style);
                style = "-fx-border-width: 0;";
                SignAndEncryptSchemeController.bbButton8.setStyle(style);
            }
            
            SchemeData.bButton[8] = false;
            SchemeData.bButton[9] = true;
        }
        
         decompressTextArea.positionCaret(0);
    }
    
    
    public void nextScene() throws IOException
    {
        if((SendMessageData.selectedMode == 210) || (SendMessageData.selectedMode == 211))
        {
            MainController.mainContentPane.getChildren().clear();
        
            MyData.currentUser = MyData.getUser(SendMessageData.sender.getEmail());
            MainController.mainCurrentUserLabel.setText(MyData.currentUser.getName() + " " + MyData.currentUser.getSurname());
        
            String url = "images/" + MyData.currentUser.getName() + ".jpg";
            Image image = new Image(url);
            ImagePattern imagePattern = new ImagePattern(image);
            MainController.mainAvatarCircle.setFill(imagePattern);
            MainController.mainStatusLabel.setText("This is main window");
        
            MainController.mainSendMessageButton.disableProperty().set(false);
            MainController.mainManageKeysButton.disableProperty().set(false);
            MainController.mainBlockSizeButton.disableProperty().set(false);
            MainController.mainAboutButton.disableProperty().set(false);
            
            SendMessageData.selectedMode = 0;
            SchemeData.clearE();
        }
        
        if((SendMessageData.selectedMode == 110) || (SendMessageData.selectedMode == 111) ||
           (SendMessageData.selectedMode == 310) || (SendMessageData.selectedMode == 311))
        {
            String nextSceneResource = "VerifyMessage.fxml";
            
            AnchorPane nextScene = (AnchorPane) FXMLLoader.load(getClass().getResource(nextSceneResource));
            MainController.mainContentPane.getChildren().clear();
            MainController.mainContentPane.getChildren().add(nextScene);
            nextScene.toFront();
        }
    }
}
