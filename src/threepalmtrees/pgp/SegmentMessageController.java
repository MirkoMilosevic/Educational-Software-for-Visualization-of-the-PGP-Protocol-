
package threepalmtrees.pgp;

import java.io.IOException;
import java.net.URL;
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
import javafx.scene.paint.ImagePattern;
import util.MyData;
import util.SendMessageData;

/**
 * FXML Controller class
 *
 * @author Mirko
 */
public class SegmentMessageController implements Initializable {

    @FXML private TextArea segmentTextArea;
    @FXML private Button segmentButton;
    @FXML private Button nextButton;
    
    public static int numberOfSegments;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        segmentTextArea.setEditable(false);
        nextButton.disableProperty().set(true);
        
        numberOfSegments = 0;
        
        Tooltip segmentTooltip = new Tooltip();
        segmentTooltip.setText("This will segment your message");
        segmentButton.setTooltip(segmentTooltip);
        
        MainController.mainStatusLabel.setText("In this window you are segmenting your message");
        
        String style = "-fx-text-fill: white; -fx-font-size: 16px;";
        segmentTextArea.setStyle(style);
        
        
        if(SendMessageData.selectedMode == 100)
        {
            String encryptedSignature = "";
            for(int i=0; i<SendMessageData.message.getSignature().getSignature().length; i++)
            {  
                int m = SendMessageData.message.getSignature().getSignature()[i] & 0xFF;
                String n = Character.toString((char) m);
                encryptedSignature += n;
                if((i % 32) == 31) { encryptedSignature += "\n"; }
            }
        
            String newLine = "\n";
            segmentTextArea.setText("---------- BEGIN PGP MESSAGE ----------");
            segmentTextArea.appendText(newLine);
            segmentTextArea.appendText(newLine);
            segmentTextArea.appendText("------- BEGIN SIGNATURE -------");
            segmentTextArea.appendText(newLine);
            segmentTextArea.appendText(newLine);
            segmentTextArea.appendText("--- Timestamp ---");
            segmentTextArea.appendText(newLine);
            segmentTextArea.appendText(SendMessageData.message.getSignature().getTimestamp());
            segmentTextArea.appendText(newLine);
            segmentTextArea.appendText("--- Public Key Algorithm ---");
            segmentTextArea.appendText(newLine);
            segmentTextArea.appendText(SendMessageData.signatureAlgorithm);
            segmentTextArea.appendText(newLine);
            segmentTextArea.appendText("--- Hash Algorithm ---");
            segmentTextArea.appendText(newLine);
            segmentTextArea.appendText(SendMessageData.hashAlgorithm);
            segmentTextArea.appendText(newLine);
            segmentTextArea.appendText("--- Key Id of Sender's Public Key ---");
            segmentTextArea.appendText(newLine);
            segmentTextArea.appendText(SendMessageData.keyId);
            segmentTextArea.appendText(newLine);
            segmentTextArea.appendText("--- Leading Two Octets of Message Digest ---");
            segmentTextArea.appendText(newLine);
            segmentTextArea.appendText(SendMessageData.message.getSignature().getLeading2octets());
            segmentTextArea.appendText(newLine);
            segmentTextArea.appendText("--- Message Digest ---");
            segmentTextArea.appendText(newLine);
            segmentTextArea.appendText(encryptedSignature);
            segmentTextArea.appendText(newLine);
            segmentTextArea.appendText(newLine);
            segmentTextArea.appendText("------- END SIGNATURE -------");
            segmentTextArea.appendText(newLine);
            segmentTextArea.appendText(newLine);
            segmentTextArea.appendText("------- BEGIN MESSAGE -------");
            segmentTextArea.appendText(newLine);
            segmentTextArea.appendText(newLine);
            segmentTextArea.appendText("---- Filename ----");
            segmentTextArea.appendText(newLine);
            segmentTextArea.appendText(SendMessageData.message.getFilename());
            segmentTextArea.appendText(newLine);
            segmentTextArea.appendText("---- Timestamp ----");
            segmentTextArea.appendText(newLine);
            segmentTextArea.appendText(SendMessageData.message.getTimestamp());
            segmentTextArea.appendText(newLine);
            segmentTextArea.appendText("---- Data ----");
            segmentTextArea.appendText(newLine);
            segmentTextArea.appendText(SendMessageData.message.getData());
            segmentTextArea.appendText(newLine);
            segmentTextArea.appendText(newLine);
            segmentTextArea.appendText("------- END MESSAGE -------");
            segmentTextArea.appendText(newLine);
            segmentTextArea.appendText(newLine);
            segmentTextArea.appendText("---------- END PGP MESSAGE ----------");
            
            SendMessageData.beforeSegmentation = segmentTextArea.getText();
        }
        
        
        if(SendMessageData.selectedMode == 110)
        {
            String compressedString = "";
            for(int i=0; i<SendMessageData.compressedData.length; i++)
            {  
                int m = SendMessageData.compressedData[i] & 0xFF;
                String n = Character.toString((char) m);
                compressedString += n;
                if((i % 32) == 31) { compressedString += "\n"; }
            }
        
            String newLine = "\n";
            segmentTextArea.setText("---------- BEGIN PGP MESSAGE ----------");
            segmentTextArea.appendText(newLine);
            segmentTextArea.appendText(newLine);
            segmentTextArea.appendText("------- BEGIN COMPRESSED DATA -------");
            segmentTextArea.appendText(newLine);
            segmentTextArea.appendText(newLine);
            segmentTextArea.appendText(compressedString);
            segmentTextArea.appendText(newLine);
            segmentTextArea.appendText(newLine);
            segmentTextArea.appendText("------- END COMPRESSED DATA -------");
            segmentTextArea.appendText(newLine);
            segmentTextArea.appendText(newLine);
            segmentTextArea.appendText("---------- END PGP MESSAGE ----------");
            
            SendMessageData.beforeSegmentation = segmentTextArea.getText();
        }
        
        
        if((SendMessageData.selectedMode == 101) || (SendMessageData.selectedMode == 111) ||
           (SendMessageData.selectedMode == 201) || (SendMessageData.selectedMode == 211) ||
           (SendMessageData.selectedMode == 301) || (SendMessageData.selectedMode == 311))
        {
            String newLine = "\n";
            segmentTextArea.setText("---------- BEGIN PGP MESSAGE ----------");
            segmentTextArea.appendText(newLine);
            segmentTextArea.appendText(newLine);
            segmentTextArea.appendText("------- BEGIN DATA IN RADIX64 -------");
            segmentTextArea.appendText(newLine);
            segmentTextArea.appendText(newLine);
        
            for(int i=0; i<SendMessageData.convertedData.length(); i++)
            {
                String temp = Character.toString(SendMessageData.convertedData.charAt(i));
                segmentTextArea.appendText(temp);
                if((i % 32) == 31) { segmentTextArea.appendText(newLine); }
            }
        
            segmentTextArea.appendText(newLine);
            segmentTextArea.appendText(newLine);
            segmentTextArea.appendText("------- END DATA IN RADIX64 -------");
            segmentTextArea.appendText(newLine);
            segmentTextArea.appendText(newLine);
            segmentTextArea.appendText("---------- END PGP MESSAGE ----------");
            
            SendMessageData.beforeSegmentation = segmentTextArea.getText();
        }
        
        
        if((SendMessageData.selectedMode == 200) || (SendMessageData.selectedMode == 210) ||
           (SendMessageData.selectedMode == 300) || (SendMessageData.selectedMode == 310))
        {
            String encryptedSessionKey = "";
            for(int i=0; i<SendMessageData.encryptedSessionKey.length; i++)
            {  
                int m = SendMessageData.encryptedSessionKey[i] & 0xFF;
                String n = Character.toString((char) m);
                encryptedSessionKey += n;
                if((i % 32) == 31) { encryptedSessionKey += "\n"; }
            }
        
            String encryptedData = "";
            for(int i=0; i<SendMessageData.encryptedMessage.length; i++)
            {
                int m = SendMessageData.encryptedMessage[i] & 0xFF;
                String n = Character.toString((char) m);
                encryptedData += n;
                if((i % 32) == 31) { encryptedData += "\n"; }
            }

            String newLine = "\n";
            segmentTextArea.setText("---------- BEGIN PGP MESSAGE ----------");
            segmentTextArea.appendText(newLine);
            segmentTextArea.appendText(newLine);
            segmentTextArea.appendText("---------- BEGIN SYMETRIC KEY PACKET ----------");
            segmentTextArea.appendText(newLine);
            segmentTextArea.appendText(newLine);
            segmentTextArea.appendText("--- Key Id of Recipient's Public Key ---"); 
            segmentTextArea.appendText(newLine);
            segmentTextArea.appendText(SendMessageData.recipientPublicKeyId);
            segmentTextArea.appendText(newLine);
            segmentTextArea.appendText("--- Public Key Algorithm ---"); 
            segmentTextArea.appendText(newLine);
            segmentTextArea.appendText(SendMessageData.publicKeyAlgorithm);
            segmentTextArea.appendText(newLine);
            segmentTextArea.appendText("--- Symetric Algorithm ---"); 
            segmentTextArea.appendText(newLine);
            segmentTextArea.appendText(SendMessageData.symetricAlgorithm);
            segmentTextArea.appendText(newLine);
            segmentTextArea.appendText("--- Session Key ---");
            segmentTextArea.appendText(newLine);
            segmentTextArea.appendText(encryptedSessionKey);
            segmentTextArea.appendText(newLine);
            segmentTextArea.appendText(newLine);
            segmentTextArea.appendText("---------- END SYMETRIC KEY PACKET ----------");
            segmentTextArea.appendText(newLine);
            segmentTextArea.appendText(newLine);
            segmentTextArea.appendText("------- BEGIN ENCRYPTED DATA PACKET -------");
            segmentTextArea.appendText(newLine);
            segmentTextArea.appendText(newLine);
            segmentTextArea.appendText(encryptedData);
            segmentTextArea.appendText(newLine);
            segmentTextArea.appendText(newLine);
            segmentTextArea.appendText("------- END ENCRYPTED DATA PACKET -------");
            segmentTextArea.appendText(newLine);
            segmentTextArea.appendText(newLine);
            segmentTextArea.appendText("---------- END PGP MESSAGE ----------");
            
            SendMessageData.beforeSegmentation = segmentTextArea.getText();
        }
        
        segmentTextArea.positionCaret(0);
    }    
    
    
    public void segmentMessage()
    {
        if(SendMessageData.selectedMode == 100)
        {
            int s1 = SendMessageData.message.getSignature().getTimestampBytes().length;
            int s2 = SendMessageData.signatureAlgorithm.getBytes().length;
            int s3 = SendMessageData.hashAlgorithm.getBytes().length;
            int s4 = SendMessageData.keyId.getBytes().length;
            int s5 = 2;
            int s6 = SendMessageData.message.getSignature().getSignature().length;
            int s7 = SendMessageData.message.getFilename().getBytes().length;
            int s8 = SendMessageData.message.getTimestampBytes().length;
            int s9 = SendMessageData.message.getData().getBytes().length;
            int sum = s1 + s2 + s3 + s4 + s5 + s6 + s7 + s8 + s9;
            int part = s1 + s2 + s3 + s4 + s5 + s6 + s7 + s8;
            
            System.out.println("Velicina poruke je " + sum);
            System.out.println("Velicina hedera je " + part);
            
            int num = (sum / MyData.maxBlockSize) + 1;
            int segmentCount = 1;
            numberOfSegments = num;
            
            String encryptedSignature = "";
            for(int i=0; i<SendMessageData.message.getSignature().getSignature().length; i++)
            {  
                int m = SendMessageData.message.getSignature().getSignature()[i] & 0xFF;
                String n = Character.toString((char) m);
                encryptedSignature += n;
                if((i % 32) == 31) { encryptedSignature += "\n"; }
            }
        
            String newLine = "\n";
            segmentTextArea.setText("---------- BEGIN PGP MESSAGE SEGMENT 1/" + num + " ----------");
            segmentTextArea.appendText(newLine);
            segmentTextArea.appendText(newLine);
            segmentTextArea.appendText("------- BEGIN SIGNATURE -------");
            segmentTextArea.appendText(newLine);
            segmentTextArea.appendText(newLine);
            segmentTextArea.appendText("--- Timestamp ---");
            segmentTextArea.appendText(newLine);
            segmentTextArea.appendText(SendMessageData.message.getSignature().getTimestamp());
            segmentTextArea.appendText(newLine);
            segmentTextArea.appendText("--- Public Key Algorithm ---");
            segmentTextArea.appendText(newLine);
            segmentTextArea.appendText(SendMessageData.signatureAlgorithm);
            segmentTextArea.appendText(newLine);
            segmentTextArea.appendText("--- Hash Algorithm ---");
            segmentTextArea.appendText(newLine);
            segmentTextArea.appendText(SendMessageData.hashAlgorithm);
            segmentTextArea.appendText(newLine);
            segmentTextArea.appendText("--- Key Id of Sender's Public Key ---");
            segmentTextArea.appendText(newLine);
            segmentTextArea.appendText(SendMessageData.keyId);
            segmentTextArea.appendText(newLine);
            segmentTextArea.appendText("--- Leading Two Octets of Message Digest ---");
            segmentTextArea.appendText(newLine);
            segmentTextArea.appendText(SendMessageData.message.getSignature().getLeading2octets());
            segmentTextArea.appendText(newLine);
            segmentTextArea.appendText("--- Message Digest ---");
            segmentTextArea.appendText(newLine);
            segmentTextArea.appendText(encryptedSignature);
            segmentTextArea.appendText(newLine);
            segmentTextArea.appendText(newLine);
            segmentTextArea.appendText("------- END SIGNATURE -------");
            segmentTextArea.appendText(newLine);
            segmentTextArea.appendText(newLine);
            segmentTextArea.appendText("------- BEGIN MESSAGE -------");
            segmentTextArea.appendText(newLine);
            segmentTextArea.appendText(newLine);
            segmentTextArea.appendText("---- Filename ----");
            segmentTextArea.appendText(newLine);
            segmentTextArea.appendText(SendMessageData.message.getFilename());
            segmentTextArea.appendText(newLine);
            segmentTextArea.appendText("---- Timestamp ----");
            segmentTextArea.appendText(newLine);
            segmentTextArea.appendText(SendMessageData.message.getTimestamp());
            segmentTextArea.appendText(newLine);
            segmentTextArea.appendText("---- Data ----");
            segmentTextArea.appendText(newLine);
            
            
            int counter1 = MyData.maxBlockSize - part;
            int counter2 = 0;
            int segment = 1;
            while(counter2 < counter1)
            {
                String temp = Character.toString(SendMessageData.message.getData().charAt(counter2));
                
                if((SendMessageData.message.getData().charAt(counter2) != '\n'))
                { segmentTextArea.appendText(temp); }
                if((counter2 % 32) == 31) { segmentTextArea.appendText(newLine); }
                
                counter2++;
            }
            
            segmentTextArea.appendText(newLine);
            segmentTextArea.appendText(newLine);
            segmentTextArea.appendText("---------- END PGP MESSAGE SEGMENT 1/" + num + " ----------");
            
            
            segmentCount++;
            
            int blockS = 0;
            
            for(int i = counter2; i < SendMessageData.message.getData().length(); i++)
            {
                if(blockS == 0 )
                {
                    segmentTextArea.appendText(newLine);
                    segmentTextArea.appendText(newLine);
                    segmentTextArea.appendText("---------- BEGIN PGP MESSAGE SEGMENT " + segmentCount + "/" + num + " ----------");
                    segmentTextArea.appendText(newLine);
                    segmentTextArea.appendText(newLine);   
                }
                
                String temp = Character.toString(SendMessageData.message.getData().charAt(i));
                if((SendMessageData.message.getData().charAt(i) != '\n'))
                { segmentTextArea.appendText(temp); }
                if((blockS % 32) == 31) { segmentTextArea.appendText(newLine); }
                
                if((blockS % (MyData.maxBlockSize)) == (MyData.maxBlockSize-1) )
                {
                    segmentTextArea.appendText(newLine);
                    segmentTextArea.appendText(newLine);
                    segmentTextArea.appendText("---------- END PGP MESSAGE SEGMENT " + segmentCount + "/" + num + " ----------");
                    segmentTextArea.appendText(newLine);
                    segmentTextArea.appendText(newLine);
                    
                    segmentCount++;
                    blockS = 0;
                }
                else
                {
                    blockS++;
                }
            }
            
            segmentTextArea.appendText(newLine);
            segmentTextArea.appendText(newLine);
            segmentTextArea.appendText("------- END MESSAGE -------");
            segmentTextArea.appendText(newLine);
            segmentTextArea.appendText(newLine);
            segmentTextArea.appendText("---------- END PGP MESSAGE SEGMENT " + num + "/" + num + " ----------");
            segmentTextArea.appendText(newLine);
            segmentTextArea.appendText(newLine);
            
            SendMessageData.segmentedData = segmentTextArea.getText();
 
        }
        
        
        if(SendMessageData.selectedMode == 110)
        {
            int sum = SendMessageData.compressedData.length;
            int blockS = 0;
            int num = (sum / MyData.maxBlockSize) + 1;
            int segmentCount = 1;
            
            numberOfSegments = num;
            
            System.out.println("Velicina poruke je " + sum);
            
            String newLine = "\n";
            segmentTextArea.setText("");
            boolean begining = true;
            
            for(int i = 0; i < SendMessageData.compressedData.length; i++)
            {
                if(blockS == 0 )
                {
                    if(begining)
                    {
                        segmentTextArea.appendText("---------- BEGIN PGP MESSAGE SEGMENT " + segmentCount + "/" + num + " ----------");
                        segmentTextArea.appendText(newLine);
                        segmentTextArea.appendText(newLine);
                        
                        begining = false;
                    }
                    else
                    {
                        segmentTextArea.appendText(newLine);
                        segmentTextArea.appendText(newLine);
                        segmentTextArea.appendText("---------- BEGIN PGP MESSAGE SEGMENT " + segmentCount + "/" + num + " ----------");
                        segmentTextArea.appendText(newLine);
                        segmentTextArea.appendText(newLine);
                    }    
                }
                
                int m = SendMessageData.compressedData[i] & 0xFF;
                String n = Character.toString((char) m);
                segmentTextArea.appendText(n);
                if((i % 32) == 31) { segmentTextArea.appendText(newLine); }
                
                if((blockS % (MyData.maxBlockSize)) == (MyData.maxBlockSize-1) )
                {
                    segmentTextArea.appendText(newLine);
                    segmentTextArea.appendText(newLine);
                    segmentTextArea.appendText("---------- END PGP MESSAGE SEGMENT " + segmentCount + "/" + num + " ----------");
                    segmentTextArea.appendText(newLine);
                    segmentTextArea.appendText(newLine);
                    
                    segmentCount++;
                    blockS = 0;
                }
                else
                {
                    blockS++;
                } 
            }
            
            segmentTextArea.appendText(newLine);
            segmentTextArea.appendText(newLine);
            segmentTextArea.appendText("------- END MESSAGE -------");
            segmentTextArea.appendText(newLine);
            segmentTextArea.appendText(newLine);
            segmentTextArea.appendText("---------- END PGP MESSAGE SEGMENT " + num + "/" + num + " ----------");
            segmentTextArea.appendText(newLine);
            segmentTextArea.appendText(newLine);
            
            SendMessageData.segmentedData = segmentTextArea.getText();
        }
        
        
        if((SendMessageData.selectedMode == 101) || (SendMessageData.selectedMode == 111) ||
           (SendMessageData.selectedMode == 201) || (SendMessageData.selectedMode == 211) ||
           (SendMessageData.selectedMode == 301) || (SendMessageData.selectedMode == 311))
        {
            int sum = SendMessageData.convertedData.length();
            int blockS = 0;
            int num = (sum / MyData.maxBlockSize) + 1;
            int segmentCount = 1;
            
            numberOfSegments = num;
            
            System.out.println("Velicina poruke je " + sum);
            
            String newLine = "\n";
            segmentTextArea.setText("");
            boolean begining = true;
            
            for(int i = 0; i < SendMessageData.convertedData.length(); i++)
            {
                if(blockS == 0 )
                {
                    if(begining)
                    {
                        segmentTextArea.appendText("---------- BEGIN PGP MESSAGE SEGMENT " + segmentCount + "/" + num + " ----------");
                        segmentTextArea.appendText(newLine);
                        segmentTextArea.appendText(newLine);
                        
                        begining = false;
                    }
                    else
                    {
                        segmentTextArea.appendText(newLine);
                        segmentTextArea.appendText(newLine);
                        segmentTextArea.appendText("---------- BEGIN PGP MESSAGE SEGMENT " + segmentCount + "/" + num + " ----------");
                        segmentTextArea.appendText(newLine);
                        segmentTextArea.appendText(newLine);
                    }    
                }
                
                String temp = Character.toString(SendMessageData.convertedData.charAt(i));
                segmentTextArea.appendText(temp);
                if((i % 32) == 31) { segmentTextArea.appendText(newLine); }
                
                if((blockS % (MyData.maxBlockSize)) == (MyData.maxBlockSize-1) )
                {
                    segmentTextArea.appendText(newLine);
                    segmentTextArea.appendText(newLine);
                    segmentTextArea.appendText("---------- END PGP MESSAGE SEGMENT " + segmentCount + "/" + num + " ----------");
                    segmentTextArea.appendText(newLine);
                    segmentTextArea.appendText(newLine);
                    
                    segmentCount++;
                    blockS = 0;
                }
                else
                {
                    blockS++;
                } 
            }
            
            segmentTextArea.appendText(newLine);
            segmentTextArea.appendText(newLine);
            segmentTextArea.appendText("------- END MESSAGE -------");
            segmentTextArea.appendText(newLine);
            segmentTextArea.appendText(newLine);
            segmentTextArea.appendText("---------- END PGP MESSAGE SEGMENT " + num + "/" + num + " ----------");
            segmentTextArea.appendText(newLine);
            segmentTextArea.appendText(newLine);
            
            SendMessageData.segmentedData = segmentTextArea.getText();
        }
        
        
        if((SendMessageData.selectedMode == 200) || (SendMessageData.selectedMode == 210) ||
           (SendMessageData.selectedMode == 300) || (SendMessageData.selectedMode == 310))
        {
            int s1 = SendMessageData.recipientPublicKeyId.getBytes().length;
            int s2 = SendMessageData.publicKeyAlgorithm.getBytes().length;
            int s3 = SendMessageData.symetricAlgorithm.getBytes().length;
            int s4 = SendMessageData.encryptedSessionKey.length;
            int s5 = SendMessageData.encryptedMessage.length;
        
            int sum = s1 + s2 + s3 + s4 + s5;
            int part = s1 + s2 + s3 + s4;
            
            System.out.println("Velicina poruke je " + sum);
            System.out.println("Velicina hedera je " + part);
            
            int num = (sum / MyData.maxBlockSize) + 1;
            int segmentCount = 1;
            
            numberOfSegments = num;
            
            String encryptedSessionKey = "";
            for(int i=0; i<SendMessageData.encryptedSessionKey.length; i++)
            {  
                int m = SendMessageData.encryptedSessionKey[i] & 0xFF;
                String n = Character.toString((char) m);
                encryptedSessionKey += n;
                if((i % 32) == 31) { encryptedSessionKey += "\n"; }
            }
        
            String encryptedData = "";
            for(int i=0; i<SendMessageData.encryptedMessage.length; i++)
            {
                int m = SendMessageData.encryptedMessage[i] & 0xFF;
                String n = Character.toString((char) m);
                encryptedData += n;
                if((i % 32) == 31) { encryptedData += "\n"; }
            }
        
            String newLine = "\n";
            segmentTextArea.setText("---------- BEGIN PGP MESSAGE ----------");
            segmentTextArea.appendText(newLine);
            segmentTextArea.appendText(newLine);
            segmentTextArea.appendText("---------- BEGIN SYMETRIC KEY PACKET ----------");
            segmentTextArea.appendText(newLine);
            segmentTextArea.appendText(newLine);
            segmentTextArea.appendText("--- Key Id of Recipient's Public Key ---"); 
            segmentTextArea.appendText(newLine);
            segmentTextArea.appendText(SendMessageData.recipientPublicKeyId);
            segmentTextArea.appendText(newLine);
            segmentTextArea.appendText("--- Public Key Algorithm ---"); 
            segmentTextArea.appendText(newLine);
            segmentTextArea.appendText(SendMessageData.publicKeyAlgorithm);
            segmentTextArea.appendText(newLine);
            segmentTextArea.appendText("--- Symetric Algorithm ---"); 
            segmentTextArea.appendText(newLine);
            segmentTextArea.appendText(SendMessageData.symetricAlgorithm);
            segmentTextArea.appendText(newLine);
            segmentTextArea.appendText("--- Session Key ---");
            segmentTextArea.appendText(newLine);
            segmentTextArea.appendText(encryptedSessionKey);
            segmentTextArea.appendText(newLine);
            segmentTextArea.appendText(newLine);
            segmentTextArea.appendText("---------- END SYMETRIC KEY PACKET ----------");
            segmentTextArea.appendText(newLine);
            segmentTextArea.appendText(newLine);
            segmentTextArea.appendText("------- BEGIN ENCRYPTED DATA PACKET -------");
            segmentTextArea.appendText(newLine);
            segmentTextArea.appendText(newLine);
            
            
            int counter1 = MyData.maxBlockSize - part;
            int counter2 = 0;
            int segment = 1;
            while(counter2 < counter1)
            {
                String temp = Character.toString(encryptedData.charAt(counter2));
                
                if(encryptedData.charAt(counter2) != '\n')
                { segmentTextArea.appendText(temp); }
                if((counter2 % 32) == 31) { segmentTextArea.appendText(newLine); }
                
                counter2++;
            }
            
            segmentTextArea.appendText(newLine);
            segmentTextArea.appendText(newLine);
            segmentTextArea.appendText("---------- END PGP MESSAGE SEGMENT 1/" + num + " ----------");
            
            
            segmentCount++;
            
            int blockS = 0;
            
            for(int i = counter2; i < encryptedData.length(); i++)
            {
                if(blockS == 0 )
                {
                    segmentTextArea.appendText(newLine);
                    segmentTextArea.appendText(newLine);
                    segmentTextArea.appendText("---------- BEGIN PGP MESSAGE SEGMENT " + segmentCount + "/" + num + " ----------");
                    segmentTextArea.appendText(newLine);
                    segmentTextArea.appendText(newLine);   
                }
                
                String temp = Character.toString(encryptedData.charAt(i));
                if(encryptedData.charAt(i) != '\n')
                { segmentTextArea.appendText(temp); }
                if((blockS % 32) == 31) { segmentTextArea.appendText(newLine); }
                
                if((blockS % (MyData.maxBlockSize)) == (MyData.maxBlockSize-1) )
                {
                    segmentTextArea.appendText(newLine);
                    segmentTextArea.appendText(newLine);
                    segmentTextArea.appendText("---------- END PGP MESSAGE SEGMENT " + segmentCount + "/" + num + " ----------");
                    segmentTextArea.appendText(newLine);
                    segmentTextArea.appendText(newLine);
                    
                    segmentCount++;
                    blockS = 0;
                }
                else
                {
                    blockS++;
                }
            }
            
            segmentTextArea.appendText(newLine);
            segmentTextArea.appendText(newLine);
            segmentTextArea.appendText("------- END ENCRYPTED DATA PACKET -------");
            segmentTextArea.appendText(newLine);
            segmentTextArea.appendText(newLine);
            segmentTextArea.appendText("---------- END PGP MESSAGE SEGMENT " + num + "/" + num + " ----------");
            segmentTextArea.appendText(newLine);
            segmentTextArea.appendText(newLine);
            
            SendMessageData.segmentedData = segmentTextArea.getText();
        }
        
        nextButton.disableProperty().set(false);
        
        segmentTextArea.positionCaret(0);
    }
    
    public void nextScene() throws IOException
    {
            String nextSceneResource = "ReassembleMessage.fxml";
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Send Message");
            alert.setHeaderText(null);
            
            String message = "Message was too big so it was segmented into " + numberOfSegments + " messages." + "\n" +
            "Each message will be sent separately to " + SendMessageData.receiver.getName() + " " + SendMessageData.receiver.getSurname() + ".";
            alert.setContentText(message);
            alert.showAndWait(); 
            
            MyData.currentUser = MyData.getUser(SendMessageData.receiver.getEmail());
            MainController.mainCurrentUserLabel.setText(MyData.currentUser.getName() + " " + MyData.currentUser.getSurname());
        
            String url = "images/" + MyData.currentUser.getName() + ".jpg";
            Image image = new Image(url);
            ImagePattern imagePattern = new ImagePattern(image);
            MainController.mainAvatarCircle.setFill(imagePattern);
            
            AnchorPane nextScene = (AnchorPane) FXMLLoader.load(getClass().getResource(nextSceneResource));
            MainController.mainContentPane.getChildren().clear();
            MainController.mainContentPane.getChildren().add(nextScene);
            nextScene.toFront();
    }
    
}
