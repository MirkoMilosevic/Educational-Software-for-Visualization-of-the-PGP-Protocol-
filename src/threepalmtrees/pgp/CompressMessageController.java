
package threepalmtrees.pgp;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.zip.Deflater;
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
public class CompressMessageController implements Initializable {

    
    @FXML private TextArea compressTextArea;
    @FXML private Button compressButton;
    @FXML private Button nextButton;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        if((SendMessageData.selectedMode == 210) || (SendMessageData.selectedMode == 211))
        {
            String newLine = "\n";
            compressTextArea.setText("---------- BEGIN PGP MESSAGE ----------");
            compressTextArea.appendText(newLine);
            compressTextArea.appendText(newLine);
            compressTextArea.appendText("------- BEGIN MESSAGE -------");
            compressTextArea.appendText(newLine);
            compressTextArea.appendText(newLine);
            compressTextArea.appendText("---- Filename ----");
            compressTextArea.appendText(newLine);
            compressTextArea.appendText(SendMessageData.message.getFilename());
            compressTextArea.appendText(newLine);
            compressTextArea.appendText("---- Timestamp ----");
            compressTextArea.appendText(newLine);
            compressTextArea.appendText(SendMessageData.message.getTimestamp());
            compressTextArea.appendText(newLine);
            compressTextArea.appendText("---- Data ----");
            compressTextArea.appendText(newLine);
            compressTextArea.appendText(SendMessageData.message.getData());
            compressTextArea.appendText(newLine);
            compressTextArea.appendText(newLine);
            compressTextArea.appendText("------- END MESSAGE -------");
            compressTextArea.appendText(newLine);
            compressTextArea.appendText(newLine);
            compressTextArea.appendText("---------- END PGP MESSAGE ----------");
        }
        
        if((SendMessageData.selectedMode == 110) || (SendMessageData.selectedMode == 111) ||
           (SendMessageData.selectedMode == 310) || (SendMessageData.selectedMode == 311))
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
        
            String newLine = "\n";
            compressTextArea.setText("---------- BEGIN PGP MESSAGE ----------");
            compressTextArea.appendText(newLine);
            compressTextArea.appendText(newLine);
            compressTextArea.appendText("------- BEGIN SIGNATURE -------");
            compressTextArea.appendText(newLine);
            compressTextArea.appendText(newLine);
            compressTextArea.appendText("--- Timestamp ---");
            compressTextArea.appendText(newLine);
            compressTextArea.appendText(SendMessageData.message.getSignature().getTimestamp());
            compressTextArea.appendText(newLine);
            compressTextArea.appendText("--- Public Key Algorithm ---");
            compressTextArea.appendText(newLine);
            compressTextArea.appendText(SendMessageData.signatureAlgorithm);
            compressTextArea.appendText(newLine);
            compressTextArea.appendText("--- Hash Algorithm ---");
            compressTextArea.appendText(newLine);
            compressTextArea.appendText(SendMessageData.hashAlgorithm);
            compressTextArea.appendText(newLine);
            compressTextArea.appendText("--- Key Id of Sender's Public Key ---");
            compressTextArea.appendText(newLine);
            compressTextArea.appendText(SendMessageData.keyId);
            compressTextArea.appendText(newLine);
            compressTextArea.appendText("--- Leading Two Octets of Message Digest ---");
            compressTextArea.appendText(newLine);
            compressTextArea.appendText(SendMessageData.message.getSignature().getLeading2octets());
            compressTextArea.appendText(newLine);
            compressTextArea.appendText("--- Message Digest ---");
            compressTextArea.appendText(newLine);
            compressTextArea.appendText(encryptedSignature);
            compressTextArea.appendText(newLine);
            compressTextArea.appendText(newLine);
            compressTextArea.appendText("------- END SIGNATURE -------");
            compressTextArea.appendText(newLine);
            compressTextArea.appendText(newLine);
            compressTextArea.appendText("------- BEGIN MESSAGE -------");
            compressTextArea.appendText(newLine);
            compressTextArea.appendText(newLine);
            compressTextArea.appendText("---- Filename ----");
            compressTextArea.appendText(newLine);
            compressTextArea.appendText(SendMessageData.message.getFilename());
            compressTextArea.appendText(newLine);
            compressTextArea.appendText("---- Timestamp ----");
            compressTextArea.appendText(newLine);
            compressTextArea.appendText(SendMessageData.message.getTimestamp());
            compressTextArea.appendText(newLine);
            compressTextArea.appendText("---- Data ----");
            compressTextArea.appendText(newLine);
            compressTextArea.appendText(SendMessageData.message.getData());
            compressTextArea.appendText(newLine);
            compressTextArea.appendText(newLine);
            compressTextArea.appendText("------- END MESSAGE -------");
            compressTextArea.appendText(newLine);
            compressTextArea.appendText(newLine);
            compressTextArea.appendText("---------- END PGP MESSAGE ----------");
        }
        
        compressTextArea.setEditable(false);
        
        nextButton.disableProperty().set(true);
        
        Tooltip compressTooltip = new Tooltip();
        compressTooltip.setText("This will compress your message");
        compressButton.setTooltip(compressTooltip);
        
        MainController.mainStatusLabel.setText("In this window you are compresing your message");
        
        String style = "-fx-text-fill: white; -fx-font-size: 16px;";
        
        compressTextArea.setStyle(style);
        
        if((SendMessageData.selectedMode == 210) || (SendMessageData.selectedMode == 211))
        {
            if(EncryptSchemeController.eeButton1 != null)
            {
                EncryptSchemeController.ee1.setTextFill(Color.RED);
                EncryptSchemeController.ee2.setTextFill(Color.RED);
                
                style = "-fx-border-color: red; -fx-border-width: 3 3 3 3;";
                EncryptSchemeController.eeButton1.setStyle(style);
            }
             
            SchemeData.eLabel[1] = true;
            SchemeData.eLabel[2] = true;
            SchemeData.eButton[1] = true;
        }
        
        compressTextArea.positionCaret(0);
    }    
    
    
    public void compressMessage() throws IOException
    {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream( );
        ByteArrayOutputStream compressedStream = new ByteArrayOutputStream( );
        
        if((SendMessageData.selectedMode == 210) || (SendMessageData.selectedMode == 211))
        {
            byte[] filename = SendMessageData.message.getFilename().getBytes();
            byte[] time = SendMessageData.message.getTimestampBytes();
            byte[] data = SendMessageData.message.getData().getBytes();
            
            outputStream.write(filename);
            outputStream.write(time);
            outputStream.write(data);
        }
        
        if((SendMessageData.selectedMode == 110) || (SendMessageData.selectedMode == 111) ||
           (SendMessageData.selectedMode == 310) || (SendMessageData.selectedMode == 311))
        {
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
        }
        
        byte[] input = outputStream.toByteArray();
        Deflater compressor = new Deflater();
        compressor.setInput(input);
        compressor.finish();
        
        
        byte[] buf = new byte[1024];
        while (!compressor.finished())
        {
            int count = compressor.deflate(buf);
            compressedStream.write(buf, 0, count);
        }
        
        SendMessageData.compressedData = compressedStream.toByteArray();
        
        System.out.println("Originalna duzina je " + input.length);
        System.out.println("Kompresovana duzina je " + SendMessageData.compressedData.length);
        
        formatTextArea();
        nextButton.disableProperty().set(false);
        
        if((SendMessageData.selectedMode == 111) || (SendMessageData.selectedMode == 110))
        {
            if(SignSchemeController.ssButton1 != null)
            {
                SignSchemeController.ss11.setTextFill(Color.RED);
                
                String style = "-fx-border-color: red; -fx-border-width: 3 3 3 3;";
                SignSchemeController.ssButton3.setStyle(style);
                style = "-fx-border-width: 0;";
                SignSchemeController.ssButton2.setStyle(style);
            }
             
            SchemeData.sLabel[11] = true;
            
            SchemeData.sButton[2] = false;
            SchemeData.sButton[3] = true;
            
        }
        
        if((SendMessageData.selectedMode == 210) || (SendMessageData.selectedMode == 211))
        {
            if(EncryptSchemeController.eeButton1 != null)
            {
                EncryptSchemeController.ee3.setTextFill(Color.RED);
                
                String style = "-fx-border-color: red; -fx-border-width: 3 3 3 3;";
                EncryptSchemeController.eeButton2.setStyle(style);
                style = "-fx-border-width: 0;";
                EncryptSchemeController.eeButton1.setStyle(style);
            }
             
            SchemeData.eLabel[3] = true;
            SchemeData.eButton[1] = false;
            SchemeData.eButton[2] = true;
        }
        
        if((SendMessageData.selectedMode == 310) || (SendMessageData.selectedMode == 311))
        {
            
            if(SignAndEncryptSchemeController.bbButton1 != null)
            {
                SignAndEncryptSchemeController.bb11.setTextFill(Color.RED);
            }
            
            SchemeData.bLabel[11] = true;
            
            if(SignAndEncryptSchemeController.bbButton1 != null)
            {
                String style = "-fx-border-color: red; -fx-border-width: 3 3 3 3;";
                SignAndEncryptSchemeController.bbButton3.setStyle(style);
                style = "-fx-border-width: 0;";
                SignAndEncryptSchemeController.bbButton2.setStyle(style);
            }
            
            SchemeData.bButton[2] = false;
            SchemeData.bButton[3] = true;
        }
        
        compressTextArea.positionCaret(0);
    }
    
    
    public void nextScene() throws IOException
    {
        int sum = SendMessageData.compressedData.length;
        
        String nextSceneResource = "";
        
        if(SendMessageData.selectedMode == 110)
        {
            if((MyData.maxBlockSize == 0) || (sum < MyData.maxBlockSize))
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Send Message");
                alert.setHeaderText(null);
                String message = "Message has been sent to " + SendMessageData.receiver.getName() + " " + SendMessageData.receiver.getSurname() + ".";
                alert.setContentText(message);
                alert.showAndWait();
                
                nextSceneResource = "DecompressMessage.fxml";
            }
            else
            {
                nextSceneResource = "SegmentMessage.fxml";
            }
        }
       
        
        if(SendMessageData.selectedMode == 111) 
        { 
            nextSceneResource = "ConvertMessage.fxml"; 
        }
        
        if((SendMessageData.selectedMode == 210) || (SendMessageData.selectedMode == 211) ||
           (SendMessageData.selectedMode == 310) || (SendMessageData.selectedMode == 311))
        {
            nextSceneResource = "EncryptMessage.fxml";
        }
        
        AnchorPane nextScene = (AnchorPane) FXMLLoader.load(getClass().getResource(nextSceneResource));
        MainController.mainContentPane.getChildren().clear();
        MainController.mainContentPane.getChildren().add(nextScene);
        nextScene.toFront();
        
        if(SendMessageData.selectedMode == 110)
        {
            if((MyData.maxBlockSize == 0) || (sum < MyData.maxBlockSize))
            {
                MyData.currentUser = MyData.getUser(SendMessageData.receiver.getEmail());
                MainController.mainCurrentUserLabel.setText(MyData.currentUser.getName() + " " + MyData.currentUser.getSurname());
        
                String url = "images/" + MyData.currentUser.getName() + ".jpg";
                Image image = new Image(url);
                ImagePattern imagePattern = new ImagePattern(image);
                MainController.mainAvatarCircle.setFill(imagePattern);
            }
        }
        
        
        if(SendMessageData.selectedMode == 110)
        {
            if(SignSchemeController.ssButton1 != null)
            {
                SignSchemeController.ss12.setTextFill(Color.RED);
                SignSchemeController.ss15.setTextFill(Color.RED);
                SignSchemeController.ss19.setTextFill(Color.RED);
                SignSchemeController.ss20.setTextFill(Color.RED);
                SignSchemeController.ss21.setTextFill(Color.RED);
            }
             
            SchemeData.sLabel[12] = true;
            SchemeData.sLabel[15] = true;
            SchemeData.sLabel[19] = true;
            SchemeData.sLabel[20] = true;
            SchemeData.sLabel[21] = true;
        }
        
        if(SendMessageData.selectedMode == 111)
        {
            if(SignSchemeController.ssButton1 != null)
            {
                SignSchemeController.ss12.setTextFill(Color.RED);
                SignSchemeController.ss16.setTextFill(Color.RED);
            }
             
            SchemeData.sLabel[12] = true;
            SchemeData.sLabel[16] = true;
        }
        
        if((SendMessageData.selectedMode == 210) || (SendMessageData.selectedMode == 211))
        {
            if(EncryptSchemeController.eeButton1 != null)
            {
                EncryptSchemeController.ee4.setTextFill(Color.RED);
                EncryptSchemeController.ee8.setTextFill(Color.RED);
            }
             
            SchemeData.eLabel[4] = true;
            SchemeData.eLabel[8] = true;
        }
        
        if((SendMessageData.selectedMode == 310) || (SendMessageData.selectedMode == 311))
        {
            
            if(SignAndEncryptSchemeController.bbButton1 != null)
            {
                SignAndEncryptSchemeController.bb12.setTextFill(Color.RED);
                SignAndEncryptSchemeController.bb16.setTextFill(Color.RED);
            }
            
            SchemeData.bLabel[12] = true;
            SchemeData.bLabel[16] = true;
        }
        
    }
    
    
    public void formatTextArea()
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
        compressTextArea.setText("---------- BEGIN PGP MESSAGE ----------");
        compressTextArea.appendText(newLine);
        compressTextArea.appendText(newLine);
        compressTextArea.appendText("------- BEGIN COMPRESSED DATA -------");
        compressTextArea.appendText(newLine);
        compressTextArea.appendText(newLine);
        compressTextArea.appendText(compressedString);
        compressTextArea.appendText(newLine);
        compressTextArea.appendText(newLine);
        compressTextArea.appendText("------- END COMPRESSED DATA -------");
        compressTextArea.appendText(newLine);
        compressTextArea.appendText(newLine);
        compressTextArea.appendText("---------- END PGP MESSAGE ----------");
    }
}
