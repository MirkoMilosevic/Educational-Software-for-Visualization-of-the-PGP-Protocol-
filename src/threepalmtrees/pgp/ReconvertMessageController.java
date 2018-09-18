
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import scheme.EncryptSchemeController;
import scheme.SignAndEncryptSchemeController;
import scheme.SignSchemeController;
import util.SchemeData;
import util.SendMessageData;

/**
 * FXML Controller class
 *
 * @author Mirko
 */
public class ReconvertMessageController implements Initializable {

    @FXML private TextArea reconvertTextArea;
    @FXML private Button reconvertButton;
    @FXML private Button nextButton;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        String newLine = "\n";
        reconvertTextArea.setText("---------- BEGIN PGP MESSAGE ----------");
        reconvertTextArea.appendText(newLine);
        reconvertTextArea.appendText(newLine);
        reconvertTextArea.appendText("------- BEGIN DATA IN RADIX64 -------");
        reconvertTextArea.appendText(newLine);
        reconvertTextArea.appendText(newLine);
        
        for(int i=0; i<SendMessageData.convertedData.length(); i++)
        {
            String temp = Character.toString(SendMessageData.convertedData.charAt(i));
            reconvertTextArea.appendText(temp);
            if((i % 32) == 31) { reconvertTextArea.appendText(newLine); }
        }
        
        reconvertTextArea.appendText(newLine);
        reconvertTextArea.appendText(newLine);
        reconvertTextArea.appendText("------- END DATA IN RADIX64 -------");
        reconvertTextArea.appendText(newLine);
        reconvertTextArea.appendText(newLine);
        reconvertTextArea.appendText("---------- END PGP MESSAGE ----------");
        
        reconvertTextArea.setEditable(false);
        
        nextButton.disableProperty().set(true);
        
        Tooltip reconvertTooltip = new Tooltip();
        reconvertTooltip.setText("This will reconvert your message from Radix64");
        reconvertButton.setTooltip(reconvertTooltip);
        
        MainController.mainStatusLabel.setText("In this window you are reconverting your message from Radix64");
        
        String style = "-fx-text-fill: white; -fx-font-size: 16px;";
        reconvertTextArea.setStyle(style);
        
        if((SendMessageData.selectedMode == 101) || (SendMessageData.selectedMode == 111))
        {
            if(SignSchemeController.ssButton1 != null)
            {
                SignSchemeController.ss22.setTextFill(Color.RED);
                SignSchemeController.ss23.setTextFill(Color.RED);
                
                style = "-fx-border-color: red; -fx-border-width: 3 3 3 3;";
                SignSchemeController.ssButton5.setStyle(style);
                style = "-fx-border-width: 0;";
                SignSchemeController.ssButton4.setStyle(style);
            }
            
            SchemeData.sLabel[22] = true;
            SchemeData.sLabel[23] = true;
            SchemeData.sButton[4] = false;
            SchemeData.sButton[5] = true;
        }
        
        if((SendMessageData.selectedMode == 201) || (SendMessageData.selectedMode == 211))
        {
            if(EncryptSchemeController.eeButton1 != null)
            {
                EncryptSchemeController.ee23.setTextFill(Color.RED);
                EncryptSchemeController.ee24.setTextFill(Color.RED);
                
                style = "-fx-border-color: red; -fx-border-width: 3 3 3 3;";
                EncryptSchemeController.eeButton5.setStyle(style);
                style = "-fx-border-width: 0;";
                EncryptSchemeController.eeButton4.setStyle(style);
            }
             
            SchemeData.eLabel[23] = true;
            SchemeData.eLabel[24] = true;
            SchemeData.eButton[4] = false;
            SchemeData.eButton[5] = true;
        }
        
        if((SendMessageData.selectedMode == 301) || (SendMessageData.selectedMode == 311))
        {
            
            if(SignAndEncryptSchemeController.bbButton1 != null)
            {
                SignAndEncryptSchemeController.bb31.setTextFill(Color.RED);
                SignAndEncryptSchemeController.bb32.setTextFill(Color.RED);
            }
            
            SchemeData.bLabel[31] = true;
            SchemeData.bLabel[32] = true;
            
            if(SignAndEncryptSchemeController.bbButton1 != null)
            {
                style = "-fx-border-color: red; -fx-border-width: 3 3 3 3;";
                SignAndEncryptSchemeController.bbButton6.setStyle(style);
                style = "-fx-border-width: 0;";
                SignAndEncryptSchemeController.bbButton5.setStyle(style);
            }
            
            SchemeData.bButton[5] = false;
            SchemeData.bButton[6] = true;
        }
        
        reconvertTextArea.positionCaret(0);
    }    
   
    
    public void reconvert()
    {
        if(SendMessageData.selectedMode == 101)
        {
            String encryptedSignature = "";
            for(int i=0; i<SendMessageData.message.getSignature().getSignature().length; i++)
            {  
                int m = SendMessageData.message.getSignature().getSignature()[i] & 0xFF;
                String n = Character.toString((char) m);
                if(!n.equals("\n"))
                { encryptedSignature += n; }
                if((i % 16) == 15) { encryptedSignature += "\n"; }
            }
        
            String newLine = "\n";
            reconvertTextArea.setText("---------- BEGIN PGP MESSAGE ----------");
            reconvertTextArea.appendText(newLine);
            reconvertTextArea.appendText(newLine);
            reconvertTextArea.appendText("------- BEGIN SIGNATURE -------");
            reconvertTextArea.appendText(newLine);
            reconvertTextArea.appendText(newLine);
            reconvertTextArea.appendText("--- Timestamp ---");
            reconvertTextArea.appendText(newLine);
            reconvertTextArea.appendText(SendMessageData.message.getSignature().getTimestamp());
            reconvertTextArea.appendText(newLine);
            reconvertTextArea.appendText("--- Public Key Algorithm ---");
            reconvertTextArea.appendText(newLine);
            reconvertTextArea.appendText(SendMessageData.signatureAlgorithm);
            reconvertTextArea.appendText(newLine);
            reconvertTextArea.appendText("--- Hash Algorithm ---");
            reconvertTextArea.appendText(newLine);
            reconvertTextArea.appendText(SendMessageData.hashAlgorithm);
            reconvertTextArea.appendText(newLine);
            reconvertTextArea.appendText("--- Key Id of Sender's Public Key ---");
            reconvertTextArea.appendText(newLine);
            reconvertTextArea.appendText(SendMessageData.keyId);
            reconvertTextArea.appendText(newLine);
            reconvertTextArea.appendText("--- Leading Two Octets of Message Digest ---");
            reconvertTextArea.appendText(newLine);
            reconvertTextArea.appendText(SendMessageData.message.getSignature().getLeading2octets());
            reconvertTextArea.appendText(newLine);
            reconvertTextArea.appendText("--- Message Digest ---");
            reconvertTextArea.appendText(newLine);
            reconvertTextArea.appendText(encryptedSignature);
            reconvertTextArea.appendText(newLine);
            reconvertTextArea.appendText(newLine);
            reconvertTextArea.appendText("------- END SIGNATURE -------");
            reconvertTextArea.appendText(newLine);
            reconvertTextArea.appendText(newLine);
            reconvertTextArea.appendText("------- BEGIN MESSAGE -------");
            reconvertTextArea.appendText(newLine);
            reconvertTextArea.appendText(newLine);
            reconvertTextArea.appendText("---- Filename ----");
            reconvertTextArea.appendText(newLine);
            reconvertTextArea.appendText(SendMessageData.message.getFilename());
            reconvertTextArea.appendText(newLine);
            reconvertTextArea.appendText("---- Timestamp ----");
            reconvertTextArea.appendText(newLine);
            reconvertTextArea.appendText(SendMessageData.message.getTimestamp());
            reconvertTextArea.appendText(newLine);
            reconvertTextArea.appendText("---- Data ----");
            reconvertTextArea.appendText(newLine);
            reconvertTextArea.appendText(SendMessageData.message.getData());
            reconvertTextArea.appendText(newLine);
            reconvertTextArea.appendText(newLine);
            reconvertTextArea.appendText("------- END MESSAGE -------");
            reconvertTextArea.appendText(newLine);
            reconvertTextArea.appendText(newLine);
            reconvertTextArea.appendText("---------- END PGP MESSAGE ----------");
        }
        
        if(SendMessageData.selectedMode == 111)
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
            reconvertTextArea.setText("---------- BEGIN PGP MESSAGE ----------");
            reconvertTextArea.appendText(newLine);
            reconvertTextArea.appendText(newLine);
            reconvertTextArea.appendText("------- BEGIN COMPRESSED DATA -------");
            reconvertTextArea.appendText(newLine);
            reconvertTextArea.appendText(newLine);
            reconvertTextArea.appendText(compressedString);
            reconvertTextArea.appendText(newLine);
            reconvertTextArea.appendText(newLine);
            reconvertTextArea.appendText("------- END COMPRESSED DATA -------");
            reconvertTextArea.appendText(newLine);
            reconvertTextArea.appendText(newLine);
            reconvertTextArea.appendText("---------- END PGP MESSAGE ----------");
        }
        
        if((SendMessageData.selectedMode == 201) || (SendMessageData.selectedMode == 211) ||
           (SendMessageData.selectedMode == 301) || (SendMessageData.selectedMode == 311))
        {
            String encryptedSessionKey = "";
            for(int i=0; i<SendMessageData.encryptedSessionKey.length; i++)
            {  
                int m = SendMessageData.encryptedSessionKey[i] & 0xFF;
                String n = Character.toString((char) m);
                if(!n.equals("\n"))
                { encryptedSessionKey += n; }
                if((i % 32) == 31) { encryptedSessionKey += "\n"; }
            }
        
            String encryptedData = "";
            for(int i=0; i<SendMessageData.encryptedMessage.length; i++)
            {
                int m = SendMessageData.encryptedMessage[i] & 0xFF;
                String n = Character.toString((char) m);
                if(!n.equals("\n"))
                { encryptedData += n; }
                if((i % 32) == 31) { encryptedData += "\n"; }
            }

            String newLine = "\n";
            reconvertTextArea.setText("---------- BEGIN PGP MESSAGE ----------");
            reconvertTextArea.appendText(newLine);
            reconvertTextArea.appendText(newLine);
            reconvertTextArea.appendText("---------- BEGIN SYMETRIC KEY PACKET ----------");
            reconvertTextArea.appendText(newLine);
            reconvertTextArea.appendText(newLine);
            reconvertTextArea.appendText("--- Key Id of Recipient's Public Key ---"); 
            reconvertTextArea.appendText(newLine);
            reconvertTextArea.appendText(SendMessageData.recipientPublicKeyId);
            reconvertTextArea.appendText(newLine);
            reconvertTextArea.appendText("--- Public Key Algorithm ---"); 
            reconvertTextArea.appendText(newLine);
            reconvertTextArea.appendText(SendMessageData.publicKeyAlgorithm);
            reconvertTextArea.appendText(newLine);
            reconvertTextArea.appendText("--- Symetric Algorithm ---"); 
            reconvertTextArea.appendText(newLine);
            reconvertTextArea.appendText(SendMessageData.symetricAlgorithm);
            reconvertTextArea.appendText(newLine);
            reconvertTextArea.appendText("--- Session Key ---");
            reconvertTextArea.appendText(newLine);
            reconvertTextArea.appendText(encryptedSessionKey);
            reconvertTextArea.appendText(newLine);
            reconvertTextArea.appendText(newLine);
            reconvertTextArea.appendText("---------- END SYMETRIC KEY PACKET ----------");
            reconvertTextArea.appendText(newLine);
            reconvertTextArea.appendText(newLine);
            reconvertTextArea.appendText("------- BEGIN ENCRYPTED DATA PACKET -------");
            reconvertTextArea.appendText(newLine);
            reconvertTextArea.appendText(newLine);
            reconvertTextArea.appendText(encryptedData);
            reconvertTextArea.appendText(newLine);
            reconvertTextArea.appendText(newLine);
            reconvertTextArea.appendText("------- END ENCRYPTED DATA PACKET -------");
            reconvertTextArea.appendText(newLine);
            reconvertTextArea.appendText(newLine);
            reconvertTextArea.appendText("---------- END PGP MESSAGE ----------");
        }
        
        nextButton.disableProperty().set(false);
        
        if((SendMessageData.selectedMode == 101) || (SendMessageData.selectedMode == 111))
        {
            if(SignSchemeController.ssButton1 != null)
            {
                SignSchemeController.ss24.setTextFill(Color.RED);
                
                String style = "-fx-border-color: red; -fx-border-width: 3 3 3 3;";
                SignSchemeController.ssButton6.setStyle(style);
                style = "-fx-border-width: 0;";
                SignSchemeController.ssButton5.setStyle(style);
            }
            
            SchemeData.sLabel[24] = true;
            SchemeData.sButton[5] = false;
            SchemeData.sButton[6] = true;
        }
        
        if((SendMessageData.selectedMode == 201) || (SendMessageData.selectedMode == 211))
        {
            if(EncryptSchemeController.eeButton1 != null)
            {
                EncryptSchemeController.ee25.setTextFill(Color.RED);
                EncryptSchemeController.ee29.setTextFill(Color.RED);
                
                String style = "-fx-border-color: red; -fx-border-width: 3 3 3 3;";
                EncryptSchemeController.eeButton6.setStyle(style);
                style = "-fx-border-width: 0;";
                EncryptSchemeController.eeButton5.setStyle(style);
            }
             
            SchemeData.eLabel[25] = true;
            SchemeData.eLabel[29] = true;
            SchemeData.eButton[5] = false;
            SchemeData.eButton[6] = true;
        }
        
        if((SendMessageData.selectedMode == 301) || (SendMessageData.selectedMode == 311))
        {
            
            if(SignAndEncryptSchemeController.bbButton1 != null)
            {
                SignAndEncryptSchemeController.bb33.setTextFill(Color.RED);
                SignAndEncryptSchemeController.bb37.setTextFill(Color.RED);
            }
            
            SchemeData.bLabel[33] = true;
            SchemeData.bLabel[37] = true;
            
            if(SignAndEncryptSchemeController.bbButton1 != null)
            {
                String style = "-fx-border-color: red; -fx-border-width: 3 3 3 3;";
                SignAndEncryptSchemeController.bbButton7.setStyle(style);
                style = "-fx-border-width: 0;";
                SignAndEncryptSchemeController.bbButton6.setStyle(style);
            }
            
            SchemeData.bButton[6] = false;
            SchemeData.bButton[7] = true;
        }
        
        reconvertTextArea.positionCaret(0);
    }
    
    
    public void nextScene() throws IOException
    {
        String nextSceneResource = "";
        
        if(SendMessageData.selectedMode == 101)
        { nextSceneResource = "VerifyMessage.fxml"; }
        
        if(SendMessageData.selectedMode == 111)
        { nextSceneResource = "DecompressMessage.fxml"; }
        
        if((SendMessageData.selectedMode == 201) || (SendMessageData.selectedMode == 211) ||
           (SendMessageData.selectedMode == 301) || (SendMessageData.selectedMode == 311))
        { nextSceneResource = "DecryptMessage.fxml"; }
        
        AnchorPane nextScene = (AnchorPane) FXMLLoader.load(getClass().getResource(nextSceneResource));
        MainController.mainContentPane.getChildren().clear();
        MainController.mainContentPane.getChildren().add(nextScene);
        nextScene.toFront();
        
        if(SendMessageData.selectedMode == 101)
        {
            if(SignSchemeController.ssButton1 != null)
            {
                SignSchemeController.ss25.setTextFill(Color.RED);
                SignSchemeController.ss28.setTextFill(Color.RED);
                SignSchemeController.ss31.setTextFill(Color.RED);
                SignSchemeController.ss32.setTextFill(Color.RED);
                SignSchemeController.ss33.setTextFill(Color.RED);
            }
            
            SchemeData.sLabel[25] = true;
            SchemeData.sLabel[28] = true;
            SchemeData.sLabel[31] = true;
            SchemeData.sLabel[32] = true;
            SchemeData.sLabel[33] = true;
        }
        
        if(SendMessageData.selectedMode == 111)
        {
            if(SignSchemeController.ssButton1 != null)
            {
                SignSchemeController.ss25.setTextFill(Color.RED);
                SignSchemeController.ss29.setTextFill(Color.RED);
            }
            
            SchemeData.sLabel[25] = true;
            SchemeData.sLabel[29] = true;
        }
        
    }
}
