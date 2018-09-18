
package threepalmtrees.pgp;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Base64;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
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
public class ConvertMessageController implements Initializable {

    @FXML private TextArea convertTextArea;
    @FXML private Button convertButton;
    @FXML private Button nextButton;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        String newLine = "\n";
        convertTextArea.setText("---------- BEGIN PGP MESSAGE ----------");
        convertTextArea.appendText(newLine);
        convertTextArea.appendText(newLine);
        
        //Slucajevi kada se vidi kljuc (sifrovano) i sifrovana poruka
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
            
            convertTextArea.appendText("---------- BEGIN SYMETRIC KEY PACKET ----------");
            convertTextArea.appendText(newLine);
            convertTextArea.appendText(newLine);
            convertTextArea.appendText("--- Key Id of Recipient's Public Key ---"); 
            convertTextArea.appendText(newLine);
            convertTextArea.appendText(SendMessageData.recipientPublicKeyId);
            convertTextArea.appendText(newLine);
            convertTextArea.appendText("--- Public Key Algorithm ---"); 
            convertTextArea.appendText(newLine);
            convertTextArea.appendText(SendMessageData.publicKeyAlgorithm);
            convertTextArea.appendText(newLine);
            convertTextArea.appendText("--- Symetric Algorithm ---"); 
            convertTextArea.appendText(newLine);
            convertTextArea.appendText(SendMessageData.symetricAlgorithm);
            convertTextArea.appendText(newLine);
            convertTextArea.appendText("--- Session Key ---");
            convertTextArea.appendText(newLine);
            convertTextArea.appendText(encryptedSessionKey);
            convertTextArea.appendText(newLine);
            convertTextArea.appendText(newLine);
            convertTextArea.appendText("---------- END SYMETRIC KEY PACKET ----------");
            convertTextArea.appendText(newLine);
            convertTextArea.appendText(newLine);
            convertTextArea.appendText("------- BEGIN ENCRYPTED DATA PACKET -------");
            convertTextArea.appendText(newLine);
            convertTextArea.appendText(newLine);
            convertTextArea.appendText(encryptedData);
            convertTextArea.appendText(newLine);
            convertTextArea.appendText(newLine);
            convertTextArea.appendText("------- END ENCRYPTED DATA PACKET -------");
        }
        
        //Slucaj kada se vidi potpis (potpisano, a nije sifrovano ili zipovano) i citljiva poruka
        if(SendMessageData.selectedMode == 101)
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
            
            convertTextArea.appendText("------- BEGIN SIGNATURE -------");
            convertTextArea.appendText(newLine);
            convertTextArea.appendText(newLine);
            convertTextArea.appendText("--- Timestamp ---");
            convertTextArea.appendText(newLine);
            convertTextArea.appendText(SendMessageData.message.getSignature().getTimestamp());
            convertTextArea.appendText(newLine);
            convertTextArea.appendText("--- Public Key Algorithm ---");
            convertTextArea.appendText(newLine);
            convertTextArea.appendText(SendMessageData.signatureAlgorithm);
            convertTextArea.appendText(newLine);
            convertTextArea.appendText("--- Hash Algorithm ---");
            convertTextArea.appendText(newLine);
            convertTextArea.appendText(SendMessageData.hashAlgorithm);
            convertTextArea.appendText(newLine);
            convertTextArea.appendText("--- Key Id of Sender's Public Key ---");
            convertTextArea.appendText(newLine);
            convertTextArea.appendText(SendMessageData.keyId);
            convertTextArea.appendText(newLine);
            convertTextArea.appendText("--- Leading Two Octets of Message Digest ---");
            convertTextArea.appendText(newLine);
            convertTextArea.appendText(SendMessageData.message.getSignature().getLeading2octets());
            convertTextArea.appendText(newLine);
            convertTextArea.appendText("--- Message Digest ---");
            convertTextArea.appendText(newLine);
            convertTextArea.appendText(encryptedSignature);
            convertTextArea.appendText(newLine);
            convertTextArea.appendText(newLine);
            convertTextArea.appendText("------- END SIGNATURE -------");
            convertTextArea.appendText(newLine);
            convertTextArea.appendText(newLine);
            convertTextArea.appendText("------- BEGIN MESSAGE -------");
            convertTextArea.appendText(newLine);
            convertTextArea.appendText(newLine);
            convertTextArea.appendText("---- Filename ----");
            convertTextArea.appendText(newLine);
            convertTextArea.appendText(SendMessageData.message.getFilename());
            convertTextArea.appendText(newLine);
            convertTextArea.appendText("---- Timestamp ----");
            convertTextArea.appendText(newLine);
            convertTextArea.appendText(SendMessageData.message.getTimestamp());
            convertTextArea.appendText(newLine);
            convertTextArea.appendText("---- Data ----");
            convertTextArea.appendText(newLine);
            convertTextArea.appendText(SendMessageData.message.getData());
            convertTextArea.appendText(newLine);
            convertTextArea.appendText(newLine);
            convertTextArea.appendText("------- END MESSAGE -------");
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
            
            convertTextArea.appendText("------- BEGIN COMPRESSED DATA -------");
            convertTextArea.appendText(newLine);
            convertTextArea.appendText(newLine);
            convertTextArea.appendText(compressedString);
            convertTextArea.appendText(newLine);
            convertTextArea.appendText(newLine);
            convertTextArea.appendText("------- END COMPRESSED DATA -------");
        }
        
        convertTextArea.appendText(newLine);
        convertTextArea.appendText(newLine);
        convertTextArea.appendText("---------- END PGP MESSAGE ----------");
        
        convertTextArea.setEditable(false);
        
        nextButton.disableProperty().set(true);
        
        Tooltip convertTooltip = new Tooltip();
        convertTooltip.setText("This will convert your message to Radix64");
        convertButton.setTooltip(convertTooltip);
        
        MainController.mainStatusLabel.setText("In this window you are converting your message to Radix64");
        
        String style = "-fx-text-fill: white; -fx-font-size: 16px;";
        
        convertTextArea.setStyle(style);
        convertTextArea.positionCaret(0);
    }    
    
    
    public void convertMessage() throws IOException
    {
        byte[] packet = null;
        
        if((SendMessageData.selectedMode == 201) || (SendMessageData.selectedMode == 211) ||
           (SendMessageData.selectedMode == 301) || (SendMessageData.selectedMode == 311))
       {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream( );
            
            byte[] id = SendMessageData.recipientPublicKeyId.getBytes();
            byte[] publicAlg = SendMessageData.publicKeyAlgorithm.getBytes();
            byte[] symAlg = SendMessageData.symetricAlgorithm.getBytes();
            byte[] encryptedKey = SendMessageData.encryptedSessionKey;
            byte[] encryptedData = SendMessageData.encryptedMessage;
            
            outputStream.write(id);
            outputStream.write(publicAlg);
            outputStream.write(symAlg);
            outputStream.write(encryptedKey);
            outputStream.write(encryptedData);
    
            packet = outputStream.toByteArray();
       }
        
        if(SendMessageData.selectedMode == 101)
        {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream( );
            
            byte[] signatureTime = SendMessageData.message.getSignature().getTimestampBytes();
            byte[] publicAlg = SendMessageData.signatureAlgorithm.getBytes();
            byte[] hashAlg = SendMessageData.hashAlgorithm.getBytes();
            byte[] id = SendMessageData.keyId.getBytes();
            byte[] leadingO = new byte[2];
            leadingO[0] = SendMessageData.message.getSignature().getHash()[0];
            leadingO[1] = SendMessageData.message.getSignature().getHash()[1];
            byte[] signature = SendMessageData.message.getSignature().getSignature();
            byte[] filename = SendMessageData.message.getFilename().getBytes();
            byte[] time = SendMessageData.message.getTimestampBytes();
            byte[] data = SendMessageData.message.getData().getBytes();
            
            outputStream.write(signatureTime);
            outputStream.write(publicAlg);
            outputStream.write(hashAlg);
            outputStream.write(id);
            outputStream.write(leadingO);
            outputStream.write(signature);
            outputStream.write(filename);
            outputStream.write(time);
            outputStream.write(data);
            
            packet = outputStream.toByteArray();
        }
        
        if(SendMessageData.selectedMode == 111)
        {
            packet = SendMessageData.compressedData;
        }
        
        String convertedString = Base64.getEncoder().encodeToString(packet);
        SendMessageData.convertedData = convertedString;
        
        formatTextArea();
        nextButton.disableProperty().set(false);
        
        if((SendMessageData.selectedMode == 101) || (SendMessageData.selectedMode == 111))
        {
            if(SignSchemeController.ssButton1 != null)
            {
                SignSchemeController.ss17.setTextFill(Color.RED);
                
                String style = "-fx-border-color: red; -fx-border-width: 3 3 3 3;";
                SignSchemeController.ssButton4.setStyle(style);
            }
             
            SchemeData.sLabel[17] = true;
            SchemeData.sButton[4] = true;
            
            if(SendMessageData.selectedMode == 101)
            {
                if(SignSchemeController.ssButton1 != null)
                {
                    String style = "-fx-border-width: 0;";
                    SignSchemeController.ssButton2.setStyle(style);
                }
                SchemeData.sButton[2] = false;
            }
            
            if(SendMessageData.selectedMode == 111)
            {
                if(SignSchemeController.ssButton1 != null)
                {
                    String style = "-fx-border-width: 0;";
                    SignSchemeController.ssButton3.setStyle(style);
                }
                SchemeData.sButton[3] = false;
            }
            
        }
        
     
        if((SendMessageData.selectedMode == 201) || (SendMessageData.selectedMode == 211))
        {
            if(EncryptSchemeController.eeButton1 != null)
            {
                EncryptSchemeController.ee17.setTextFill(Color.RED);
                
                String style = "-fx-border-color: red; -fx-border-width: 3 3 3 3;";
                EncryptSchemeController.eeButton4.setStyle(style);
                style = "-fx-border-width: 0;";
                EncryptSchemeController.eeButton3.setStyle(style);
            }
             
            SchemeData.eLabel[17] = true;
            SchemeData.eButton[3] = false;
            SchemeData.eButton[4] = true;
        }
        
        if((SendMessageData.selectedMode == 301) || (SendMessageData.selectedMode == 311))
        {
            
            if(SignAndEncryptSchemeController.bbButton1 != null)
            {
                SignAndEncryptSchemeController.bb25.setTextFill(Color.RED);
            }
            
            SchemeData.bLabel[25] = true;
            
            if(SignAndEncryptSchemeController.bbButton1 != null)
            {
                String style = "-fx-border-color: red; -fx-border-width: 3 3 3 3;";
                SignAndEncryptSchemeController.bbButton5.setStyle(style);
                style = "-fx-border-width: 0;";
                SignAndEncryptSchemeController.bbButton4.setStyle(style);
            }
            
            SchemeData.bButton[4] = false;
            SchemeData.bButton[5] = true;
        }
        
        convertTextArea.positionCaret(0);
    }
    
    
    public void nextScene() throws IOException
    {
        int sum = SendMessageData.convertedData.length();
        
        String nextSceneResource = "";
        
        if((MyData.maxBlockSize == 0) || (sum < MyData.maxBlockSize))
        {
            nextSceneResource = "ReconvertMessage.fxml";
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Send Message");
            alert.setHeaderText(null);
            String message = "Message has been sent to " + SendMessageData.receiver.getName() + " " + SendMessageData.receiver.getSurname() + ".";
            alert.setContentText(message);
            alert.showAndWait(); 
            
            MyData.currentUser = MyData.getUser(SendMessageData.receiver.getEmail());
            MainController.mainCurrentUserLabel.setText(MyData.currentUser.getName() + " " + MyData.currentUser.getSurname());
        
            String url = "images/" + MyData.currentUser.getName() + ".jpg";
            Image image = new Image(url);
            ImagePattern imagePattern = new ImagePattern(image);
            MainController.mainAvatarCircle.setFill(imagePattern);
        }
        else
        {
            nextSceneResource = "SegmentMessage.fxml";
        }
        
        AnchorPane nextScene = (AnchorPane) FXMLLoader.load(getClass().getResource(nextSceneResource));
        MainController.mainContentPane.getChildren().clear();
        MainController.mainContentPane.getChildren().add(nextScene);
        nextScene.toFront();
        
        if((SendMessageData.selectedMode == 101) || (SendMessageData.selectedMode == 111))
        {
            if(SignSchemeController.ssButton1 != null)
            {
                SignSchemeController.ss18.setTextFill(Color.RED);
                SignSchemeController.ss21.setTextFill(Color.RED);
            }
             
            SchemeData.sLabel[18] = true;
            SchemeData.sLabel[21] = true;
        }
        
        
        if((SendMessageData.selectedMode == 201) || (SendMessageData.selectedMode == 211))
        {
            if(EncryptSchemeController.eeButton1 != null)
            {
                EncryptSchemeController.ee18.setTextFill(Color.RED);
                EncryptSchemeController.ee19.setTextFill(Color.RED);
            }
             
            SchemeData.eLabel[18] = true;
            SchemeData.eLabel[19] = true;
        }
        
        if((SendMessageData.selectedMode == 301) || (SendMessageData.selectedMode == 311))
        {
            
            if(SignAndEncryptSchemeController.bbButton1 != null)
            {
                SignAndEncryptSchemeController.bb26.setTextFill(Color.RED);
                SignAndEncryptSchemeController.bb30.setTextFill(Color.RED);
            }
            
            SchemeData.bLabel[26] = true;
            SchemeData.bLabel[30] = true;
        }
    }
    
    
    public void formatTextArea()
    {
        //Da li mi je ovo potrebno ovde, i zasto ne?!
        /*
        String compressedString = "";
        for(int i=0; i<SendMessageData.compressedData.length; i++)
        {  
            int m = SendMessageData.compressedData[i] & 0xFF;
            String n = Character.toString((char) m);
            compressedString += n;
            if((i % 32) == 31) { compressedString += "\n"; }
        }
        */
        
        String newLine = "\n";
        convertTextArea.setText("---------- BEGIN PGP MESSAGE ----------");
        convertTextArea.appendText(newLine);
        convertTextArea.appendText(newLine);
        convertTextArea.appendText("------- BEGIN DATA IN RADIX64 -------");
        convertTextArea.appendText(newLine);
        convertTextArea.appendText(newLine);
        
        for(int i=0; i<SendMessageData.convertedData.length(); i++)
        {
            String temp = Character.toString(SendMessageData.convertedData.charAt(i));
            convertTextArea.appendText(temp);
            if((i % 32) == 31) { convertTextArea.appendText(newLine); }
        }
        
        convertTextArea.appendText(newLine);
        convertTextArea.appendText(newLine);
        convertTextArea.appendText("------- END DATA IN RADIX64 -------");
        convertTextArea.appendText(newLine);
        convertTextArea.appendText(newLine);
        convertTextArea.appendText("---------- END PGP MESSAGE ----------");
    }
}
