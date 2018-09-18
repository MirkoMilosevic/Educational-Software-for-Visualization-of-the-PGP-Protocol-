
package threepalmtrees.pgp;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Modality;
import javafx.stage.Stage;
import scheme.EncryptSchemeController;
import scheme.SignAndEncryptSchemeController;
import util.MyData;
import util.SchemeData;
import util.SendMessageData;

/**
 * FXML Controller class
 *
 * @author Mirko
 */
public class DecryptMessageController implements Initializable {

    
    @FXML private Button decryptSessionKeyButton;
    @FXML private Button decryptMessageButton;
    @FXML private Button nextButton;
    @FXML private TextArea decryptTextArea;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
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
        decryptTextArea.setText("---------- BEGIN PGP MESSAGE ----------");
        decryptTextArea.appendText(newLine);
        decryptTextArea.appendText(newLine);
        decryptTextArea.appendText("---------- BEGIN SYMETRIC KEY PACKET ----------");
        decryptTextArea.appendText(newLine);
        decryptTextArea.appendText(newLine);
        decryptTextArea.appendText("--- Key Id of Recipient's Public Key ---"); 
        decryptTextArea.appendText(newLine);
        decryptTextArea.appendText(SendMessageData.recipientPublicKeyId);
        decryptTextArea.appendText(newLine);
        decryptTextArea.appendText("--- Public Key Algorithm ---"); 
        decryptTextArea.appendText(newLine);
        decryptTextArea.appendText(SendMessageData.publicKeyAlgorithm);
        decryptTextArea.appendText(newLine);
        decryptTextArea.appendText("--- Symetric Algorithm ---"); 
        decryptTextArea.appendText(newLine);
        decryptTextArea.appendText(SendMessageData.symetricAlgorithm);
        decryptTextArea.appendText(newLine);
        decryptTextArea.appendText("--- Session Key ---");
        decryptTextArea.appendText(newLine);
        decryptTextArea.appendText(encryptedSessionKey);
        decryptTextArea.appendText(newLine);
        decryptTextArea.appendText(newLine);
        decryptTextArea.appendText("---------- END SYMETRIC KEY PACKET ----------");
        decryptTextArea.appendText(newLine);
        decryptTextArea.appendText(newLine);
        decryptTextArea.appendText("------- BEGIN ENCRYPTED DATA PACKET -------");
        decryptTextArea.appendText(newLine);
        decryptTextArea.appendText(newLine);
        decryptTextArea.appendText(encryptedData);
        decryptTextArea.appendText(newLine);
        decryptTextArea.appendText(newLine);
        decryptTextArea.appendText("------- END ENCRYPTED DATA PACKET -------");
        decryptTextArea.appendText(newLine);
        decryptTextArea.appendText(newLine);
        decryptTextArea.appendText("---------- END PGP MESSAGE ----------");
        
        decryptTextArea.setEditable(false);
        
        decryptMessageButton.disableProperty().set(true);
        nextButton.disableProperty().set(true);
        
        Tooltip decryptKeyTooltip = new Tooltip();
        decryptKeyTooltip.setText("This will decrypt Session Key using your Private Key.");
        decryptSessionKeyButton.setTooltip(decryptKeyTooltip);
        
        Tooltip decryptTooltip = new Tooltip();
        decryptTooltip.setText("This will decrypt message usin Session Key.");
        decryptMessageButton.setTooltip(decryptTooltip);
        
        MainController.mainStatusLabel.setText("In this window you are decrypting your message");
        
        String style = "-fx-text-fill: white; -fx-font-size: 16px;";
        
        decryptTextArea.setStyle(style);
        
        if((SendMessageData.selectedMode == 200) || (SendMessageData.selectedMode == 210))
        {
            if(EncryptSchemeController.eeButton1 != null)
            {
                EncryptSchemeController.ee23.setTextFill(Color.RED);
                EncryptSchemeController.ee26.setTextFill(Color.RED);
                EncryptSchemeController.ee27.setTextFill(Color.RED);
                EncryptSchemeController.ee28.setTextFill(Color.RED);
                EncryptSchemeController.ee29.setTextFill(Color.RED);
                
                style = "-fx-border-color: red; -fx-border-width: 3 3 3 3;";
                EncryptSchemeController.eeButton6.setStyle(style);
                style = "-fx-border-width: 0;";
                EncryptSchemeController.eeButton3.setStyle(style); 
            }
             
            SchemeData.eLabel[23] = true;
            SchemeData.eLabel[26] = true;
            SchemeData.eLabel[27] = true;
            SchemeData.eLabel[28] = true;
            SchemeData.eLabel[29] = true;
            SchemeData.eButton[3] = false;
            SchemeData.eButton[6] = true;
        }
        
        if((SendMessageData.selectedMode == 300) || (SendMessageData.selectedMode == 310))
        {
            
            if(SignAndEncryptSchemeController.bbButton1 != null)
            {
                SignAndEncryptSchemeController.bb31.setTextFill(Color.RED);
                SignAndEncryptSchemeController.bb34.setTextFill(Color.RED);
                SignAndEncryptSchemeController.bb35.setTextFill(Color.RED);
                SignAndEncryptSchemeController.bb36.setTextFill(Color.RED);
                SignAndEncryptSchemeController.bb37.setTextFill(Color.RED);
            }
            
            SchemeData.bLabel[31] = true;
            SchemeData.bLabel[34] = true;
            SchemeData.bLabel[35] = true;
            SchemeData.bLabel[36] = true;
            SchemeData.bLabel[37] = true;
            
            if(SignAndEncryptSchemeController.bbButton1 != null)
            {
                style = "-fx-border-color: red; -fx-border-width: 3 3 3 3;";
                SignAndEncryptSchemeController.bbButton7.setStyle(style);
                style = "-fx-border-width: 0;";
                SignAndEncryptSchemeController.bbButton4.setStyle(style);
            }
            
            SchemeData.bButton[4] = false;
            SchemeData.bButton[7] = true;
        }
        
        decryptTextArea.positionCaret(0);
    }    
    
    
    public void decryptSessionKey()
    {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Decrypt SessionKey");
           
        Label label = new Label("Please choose private key for decryption and enter password:");
        label.setPadding(new Insets(2,5,2,5));
        label.setStyle("-fx-background-color: linear-gradient(#ff5400, #be1d00);");
        //label.setMinWidth(400);
        
        TextField password = new TextField();
        password.setMaxWidth(400);
       
        ChoiceBox<String> privateKeyIdChoice = new ChoiceBox<>();
        privateKeyIdChoice.setMinWidth(400);
        for(int i=0; i<SendMessageData.receiver.getPrivateKeyRing().size(); i++ )
        { 
            if( ((SendMessageData.receiver.getPrivateKeyRing().get(i).getType() == 1) || (SendMessageData.receiver.getPrivateKeyRing().get(i).getType() == 3)) &&
                 (SendMessageData.receiver.getPrivateKeyRing().get(i).isActive()))
            {
                privateKeyIdChoice.getItems().add(SendMessageData.receiver.getPrivateKeyRing().get(i).getKeyId());
            }
        }
        
        Button closeButton = new Button("OK");
       
        closeButton.setOnAction(e ->
                                {
                                    if(!SendMessageData.receiver.searchForKeyById(SendMessageData.recipientPublicKeyId).isActive())
                                    {
                                        Alert alertBox2 = new Alert(Alert.AlertType.WARNING);
                                        alertBox2.setTitle("Decrypt SessionKey Warning");
                                        alertBox2.setHeaderText(null);
                                        String message = "Sender has used key that you have revoked!!!" + "\n" + "\n" +
                                                         "Sign Out to continue.";
                                        alertBox2.setContentText(message);
                                        alertBox2.showAndWait();
                                        
                                        return;
                                    }
                                    
                                    if(privateKeyIdChoice.getSelectionModel().isEmpty())
                                    {
                                        Alert alertBox = new Alert(Alert.AlertType.WARNING);
                                        alertBox.setTitle("Decrypt SessionKey Warning");
                                        alertBox.setHeaderText(null);
                                        alertBox.setContentText("Please select private key for SessionKey decryption!");
                                        alertBox.showAndWait();
        
                                        return;
                                    }
                                    
                                    if(password.getText().isEmpty())
                                    {
                                        Alert alertBox = new Alert(Alert.AlertType.WARNING);
                                        alertBox.setTitle("Decrypt SessionKey Warning");
                                        alertBox.setHeaderText(null);
                                        alertBox.setContentText("Please enter password for this key!");
                                        alertBox.showAndWait();
        
                                        return;
                                    }
                                    else
                                    {
                                        String password1 = SendMessageData.receiver.searchForKeyById(privateKeyIdChoice.getValue()).getPassword();
                                        
                                        String password2 = password.getText();
                                        
                                        if(!password1.equals(password2))
                                        {
                                            Alert alertBox = new Alert(Alert.AlertType.WARNING);
                                            alertBox.setTitle("Decrypt SessionKey Warning");
                                            alertBox.setHeaderText(null);
                                            alertBox.setContentText("Wrong password for this key!");
                                            alertBox.showAndWait();
        
                                            return;
                                        }
                                    }
                                    
                                    if(privateKeyIdChoice.getValue().equals(SendMessageData.recipientPublicKeyId))
                                    {
                                        formatTextArea1();
                                        decryptMessageButton.disableProperty().set(false);
                                        
                                        if((SendMessageData.selectedMode == 200) || (SendMessageData.selectedMode == 201) ||
                                            (SendMessageData.selectedMode == 210) || (SendMessageData.selectedMode == 211))
                                         {
                                             if(EncryptSchemeController.eeButton1 != null)
                                             {
                                                 EncryptSchemeController.ee30.setTextFill(Color.RED);
                                                 EncryptSchemeController.ee31.setTextFill(Color.RED);
                                                 EncryptSchemeController.ee32.setTextFill(Color.RED);
                                                 EncryptSchemeController.ee33.setTextFill(Color.RED);
                                                 EncryptSchemeController.ee34.setTextFill(Color.RED);
                                                 EncryptSchemeController.ee35.setTextFill(Color.RED);
                                             }

                                             SchemeData.eLabel[30] = true;
                                             SchemeData.eLabel[31] = true;
                                             SchemeData.eLabel[32] = true;
                                             SchemeData.eLabel[33] = true;
                                             SchemeData.eLabel[34] = true;
                                             SchemeData.eLabel[35] = true;
                                         }

                                         if((SendMessageData.selectedMode == 300) || (SendMessageData.selectedMode == 301) ||
                                            (SendMessageData.selectedMode == 310) || (SendMessageData.selectedMode == 311))
                                         {

                                             if(SignAndEncryptSchemeController.bbButton1 != null)
                                             {
                                                 SignAndEncryptSchemeController.bb38.setTextFill(Color.RED);
                                                 SignAndEncryptSchemeController.bb39.setTextFill(Color.RED);
                                                 SignAndEncryptSchemeController.bb40.setTextFill(Color.RED);
                                                 SignAndEncryptSchemeController.bb41.setTextFill(Color.RED);
                                                 SignAndEncryptSchemeController.bb42.setTextFill(Color.RED);
                                                 SignAndEncryptSchemeController.bb43.setTextFill(Color.RED);
                                             }

                                             SchemeData.bLabel[38] = true;
                                             SchemeData.bLabel[39] = true;
                                             SchemeData.bLabel[40] = true;
                                             SchemeData.bLabel[41] = true;
                                             SchemeData.bLabel[42] = true;
                                             SchemeData.bLabel[43] = true;
                                         }

                                         decryptTextArea.positionCaret(0);
                                        
                                        window.close();
                                    }
                                    else
                                    {
                                        Alert alertBox = new Alert(Alert.AlertType.WARNING);
                                        alertBox.setTitle("Decrypt SessionKey Warning");
                                        alertBox.setHeaderText(null);
                                        alertBox.setContentText("Wrong Private Key used!!!");
                                        alertBox.showAndWait();
                                        
                                        window.close();
                                    }
                                });
        
        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, privateKeyIdChoice, password, closeButton);
        layout.setAlignment(Pos.CENTER);
        
        Scene scene = new Scene(layout, 540, 200);
        scene.getStylesheets().add("css/popup.css");
        window.setScene(scene);
        window.showAndWait();
    }
    
    
    public void decryptMessage()
    {
        decryptTextArea.clear();
         
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
        
        String newLine = "\n";
        decryptTextArea.setText("---------- BEGIN PGP MESSAGE ----------");
        decryptTextArea.appendText(newLine);
        decryptTextArea.appendText(newLine);
        decryptTextArea.appendText("---------- BEGIN SYMETRIC KEY PACKET ----------");
        decryptTextArea.appendText(newLine);
        decryptTextArea.appendText(newLine);
        decryptTextArea.appendText("--- Key Id of Recipient's Public Key ---"); 
        decryptTextArea.appendText(newLine);
        decryptTextArea.appendText(SendMessageData.recipientPublicKeyId);
        decryptTextArea.appendText(newLine);
        decryptTextArea.appendText("--- Public Key Algorithm ---"); 
        decryptTextArea.appendText(newLine);
        decryptTextArea.appendText(SendMessageData.publicKeyAlgorithm);
        decryptTextArea.appendText(newLine);
        decryptTextArea.appendText("--- Symetric Algorithm ---"); 
        decryptTextArea.appendText(newLine);
        decryptTextArea.appendText(SendMessageData.symetricAlgorithm);
        decryptTextArea.appendText(newLine);
        decryptTextArea.appendText("--- Session Key ---");
        decryptTextArea.appendText(newLine);
        decryptTextArea.appendText(sessionKeyString);
        decryptTextArea.appendText(newLine);
        decryptTextArea.appendText(newLine);
        decryptTextArea.appendText("---------- END SYMETRIC KEY PACKET ----------");
        decryptTextArea.appendText(newLine);
        decryptTextArea.appendText(newLine);
        
        if((SendMessageData.selectedMode == 200) || (SendMessageData.selectedMode == 201))
        {
            decryptTextArea.appendText("------- BEGIN MESSAGE -------");
            decryptTextArea.appendText(newLine);
            decryptTextArea.appendText(newLine);
            decryptTextArea.appendText("---- Filename ----");
            decryptTextArea.appendText(newLine);
            decryptTextArea.appendText(SendMessageData.message.getFilename());
            decryptTextArea.appendText(newLine);
            decryptTextArea.appendText("---- Timestamp ----");
            decryptTextArea.appendText(newLine);
            decryptTextArea.appendText(SendMessageData.message.getTimestamp());
            decryptTextArea.appendText(newLine);
            decryptTextArea.appendText("---- Data ----");
            decryptTextArea.appendText(newLine);
            decryptTextArea.appendText(SendMessageData.message.getData());
            decryptTextArea.appendText(newLine);
            decryptTextArea.appendText(newLine);
            decryptTextArea.appendText("------- END MESSAGE -------");
        }
        
        if((SendMessageData.selectedMode == 300) || (SendMessageData.selectedMode == 301))
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
            
            decryptTextArea.appendText("------- BEGIN SIGNATURE -------");
            decryptTextArea.appendText(newLine);
            decryptTextArea.appendText(newLine);
            decryptTextArea.appendText("--- Timestamp ---");
            decryptTextArea.appendText(newLine);
            decryptTextArea.appendText(SendMessageData.message.getSignature().getTimestamp());
            decryptTextArea.appendText(newLine);
            decryptTextArea.appendText("--- Public Key Algorithm ---");
            decryptTextArea.appendText(newLine);
            decryptTextArea.appendText(SendMessageData.signatureAlgorithm);
            decryptTextArea.appendText(newLine);
            decryptTextArea.appendText("--- Hash Algorithm ---");
            decryptTextArea.appendText(newLine);
            decryptTextArea.appendText(SendMessageData.hashAlgorithm);
            decryptTextArea.appendText(newLine);
            decryptTextArea.appendText("--- Key Id of Sender's Public Key ---");
            decryptTextArea.appendText(newLine);
            decryptTextArea.appendText(SendMessageData.keyId);
            decryptTextArea.appendText(newLine);
            decryptTextArea.appendText("--- Leading Two Octets of Message Digest ---");
            decryptTextArea.appendText(newLine);
            decryptTextArea.appendText(SendMessageData.message.getSignature().getLeading2octets());
            decryptTextArea.appendText(newLine);
            decryptTextArea.appendText("--- Message Digest ---");
            decryptTextArea.appendText(newLine);
            decryptTextArea.appendText(encryptedSignature);
            decryptTextArea.appendText(newLine);
            decryptTextArea.appendText(newLine);
            decryptTextArea.appendText("------- END SIGNATURE -------");
            decryptTextArea.appendText(newLine);
            decryptTextArea.appendText(newLine);
            decryptTextArea.appendText("------- BEGIN MESSAGE -------");
            decryptTextArea.appendText(newLine);
            decryptTextArea.appendText(newLine);
            decryptTextArea.appendText("---- Filename ----");
            decryptTextArea.appendText(newLine);
            decryptTextArea.appendText(SendMessageData.message.getFilename());
            decryptTextArea.appendText(newLine);
            decryptTextArea.appendText("---- Timestamp ----");
            decryptTextArea.appendText(newLine);
            decryptTextArea.appendText(SendMessageData.message.getTimestamp());
            decryptTextArea.appendText(newLine);
            decryptTextArea.appendText("---- Data ----");
            decryptTextArea.appendText(newLine);
            decryptTextArea.appendText(SendMessageData.message.getData());
            decryptTextArea.appendText(newLine);
            decryptTextArea.appendText(newLine);
            decryptTextArea.appendText("------- END MESSAGE -------");
        }
        
        if((SendMessageData.selectedMode == 210) || (SendMessageData.selectedMode == 211) ||
           (SendMessageData.selectedMode == 310) || (SendMessageData.selectedMode == 311))
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
            
            decryptTextArea.appendText("------- BEGIN COMPRESSED DATA -------");
            decryptTextArea.appendText(newLine);
            decryptTextArea.appendText(newLine);
            decryptTextArea.appendText(compressedString);
            decryptTextArea.appendText(newLine);
            decryptTextArea.appendText(newLine);
            decryptTextArea.appendText("------- END COMPRESSED DATA -------");
        }
    
        decryptTextArea.appendText(newLine);
        decryptTextArea.appendText(newLine);
        decryptTextArea.appendText("---------- END PGP MESSAGE ----------");
        
        nextButton.disableProperty().set(false);
        
        if((SendMessageData.selectedMode == 200) || (SendMessageData.selectedMode == 201))
        {
            if(EncryptSchemeController.eeButton1 != null)
            {
                EncryptSchemeController.ee36.setTextFill(Color.RED);
                EncryptSchemeController.ee40.setTextFill(Color.RED);
                EncryptSchemeController.ee41.setTextFill(Color.RED);
                EncryptSchemeController.ee42.setTextFill(Color.RED);
                EncryptSchemeController.ee43.setTextFill(Color.RED);
                
                String style = "-fx-border-color: red; -fx-border-width: 3 3 3 3;";
                EncryptSchemeController.eeButton8.setStyle(style);
                style = "-fx-border-width: 0;";
                EncryptSchemeController.eeButton6.setStyle(style);
            }
             
            SchemeData.eLabel[36] = true;
            SchemeData.eLabel[40] = true;
            SchemeData.eLabel[41] = true;
            SchemeData.eLabel[42] = true;
            SchemeData.eLabel[43] = true;
            SchemeData.eButton[6] = false;
            SchemeData.eButton[8] = true;
        }
        
        if((SendMessageData.selectedMode == 210) || (SendMessageData.selectedMode == 211))
        {
            if(EncryptSchemeController.eeButton1 != null)
            {
                EncryptSchemeController.ee36.setTextFill(Color.RED);
                EncryptSchemeController.ee37.setTextFill(Color.RED);
                
                String style = "-fx-border-color: red; -fx-border-width: 3 3 3 3;";
                EncryptSchemeController.eeButton7.setStyle(style);
                style = "-fx-border-width: 0;";
                EncryptSchemeController.eeButton6.setStyle(style);
            }
             
            SchemeData.eLabel[36] = true;
            SchemeData.eLabel[37] = true;
            SchemeData.eButton[6] = false;
            SchemeData.eButton[7] = true;
        }
        
        if((SendMessageData.selectedMode == 300) || (SendMessageData.selectedMode == 301))
        {
            if(SignAndEncryptSchemeController.bbButton1 != null)
            {
                SignAndEncryptSchemeController.bb44.setTextFill(Color.RED);
                SignAndEncryptSchemeController.bb48.setTextFill(Color.RED);
                SignAndEncryptSchemeController.bb49.setTextFill(Color.RED);
                SignAndEncryptSchemeController.bb50.setTextFill(Color.RED);
                SignAndEncryptSchemeController.bb51.setTextFill(Color.RED);
            }
            
            SchemeData.bLabel[44] = true;
            SchemeData.bLabel[48] = true;
            SchemeData.bLabel[49] = true;
            SchemeData.bLabel[50] = true;
            SchemeData.bLabel[51] = true;
            
            if(SignAndEncryptSchemeController.bbButton1 != null)
            {
                String style = "-fx-border-color: red; -fx-border-width: 3 3 3 3;";
                SignAndEncryptSchemeController.bbButton9.setStyle(style);
                style = "-fx-border-width: 0;";
                SignAndEncryptSchemeController.bbButton7.setStyle(style);
            }
            
            SchemeData.bButton[7] = false;
            SchemeData.bButton[9] = true;
        }
        
        if((SendMessageData.selectedMode == 310) || (SendMessageData.selectedMode == 311))
        {
            if(SignAndEncryptSchemeController.bbButton1 != null)
            {
                SignAndEncryptSchemeController.bb44.setTextFill(Color.RED);
                SignAndEncryptSchemeController.bb45.setTextFill(Color.RED);
            }
            
            SchemeData.bLabel[44] = true;
            SchemeData.bLabel[45] = true;
            
            if(SignAndEncryptSchemeController.bbButton1 != null)
            {
                String style = "-fx-border-color: red; -fx-border-width: 3 3 3 3;";
                SignAndEncryptSchemeController.bbButton8.setStyle(style);
                style = "-fx-border-width: 0;";
                SignAndEncryptSchemeController.bbButton7.setStyle(style);
            }
            
            SchemeData.bButton[7] = false;
            SchemeData.bButton[8] = true;
        }
        
        decryptTextArea.positionCaret(0);
    }
    
    
    public void nextScene() throws IOException
    {
        if((SendMessageData.selectedMode == 200) || (SendMessageData.selectedMode == 201))
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
        
        if((SendMessageData.selectedMode == 300) || (SendMessageData.selectedMode == 301))
        {
            String nextSceneResource = "VerifyMessage.fxml";
            
            AnchorPane nextScene = (AnchorPane) FXMLLoader.load(getClass().getResource(nextSceneResource));
            MainController.mainContentPane.getChildren().clear();
            MainController.mainContentPane.getChildren().add(nextScene);
            nextScene.toFront();
        }
        
        if((SendMessageData.selectedMode == 210) || (SendMessageData.selectedMode == 211) ||
           (SendMessageData.selectedMode == 310) || (SendMessageData.selectedMode == 311))
        {
            String nextSceneResource = "DecompressMessage.fxml";
            
            AnchorPane nextScene = (AnchorPane) FXMLLoader.load(getClass().getResource(nextSceneResource));
            MainController.mainContentPane.getChildren().clear();
            MainController.mainContentPane.getChildren().add(nextScene);
            nextScene.toFront();
        }
        
        if((SendMessageData.selectedMode == 210) || (SendMessageData.selectedMode == 211))
        {
            if(EncryptSchemeController.eeButton1 != null)
            {
                EncryptSchemeController.ee38.setTextFill(Color.RED);
                EncryptSchemeController.ee37.setTextFill(Color.RED);
            }
             
            SchemeData.eLabel[38] = true;
        }
        
         if((SendMessageData.selectedMode == 310) || (SendMessageData.selectedMode == 311))
        {
            if(SignAndEncryptSchemeController.bbButton1 != null)
            {
                SignAndEncryptSchemeController.bb46.setTextFill(Color.RED);
            }
            
            SchemeData.bLabel[46] = true;
        }
        
    }
    
    
    public void formatTextArea1()
    {
        decryptTextArea.clear();
         
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
        decryptTextArea.setText("---------- BEGIN PGP MESSAGE ----------");
        decryptTextArea.appendText(newLine);
        decryptTextArea.appendText(newLine);
        decryptTextArea.appendText("---------- BEGIN SYMETRIC KEY PACKET ----------");
        decryptTextArea.appendText(newLine);
        decryptTextArea.appendText(newLine);
        decryptTextArea.appendText("--- Key Id of Recipient's Public Key ---"); 
        decryptTextArea.appendText(newLine);
        decryptTextArea.appendText(SendMessageData.recipientPublicKeyId);
        decryptTextArea.appendText(newLine);
        decryptTextArea.appendText("--- Public Key Algorithm ---"); 
        decryptTextArea.appendText(newLine);
        decryptTextArea.appendText(SendMessageData.publicKeyAlgorithm);
        decryptTextArea.appendText(newLine);
        decryptTextArea.appendText("--- Symetric Algorithm ---"); 
        decryptTextArea.appendText(newLine);
        decryptTextArea.appendText(SendMessageData.symetricAlgorithm);
        decryptTextArea.appendText(newLine);
        decryptTextArea.appendText("--- Session Key ---");
        decryptTextArea.appendText(newLine);
        decryptTextArea.appendText(sessionKeyString);
        decryptTextArea.appendText(newLine);
        decryptTextArea.appendText(newLine);
        decryptTextArea.appendText("---------- END SYMETRIC KEY PACKET ----------");
        decryptTextArea.appendText(newLine);
        decryptTextArea.appendText(newLine);
        decryptTextArea.appendText("------- BEGIN ENCRYPTED DATA PACKET -------");
        decryptTextArea.appendText(newLine);
        decryptTextArea.appendText(newLine);
        decryptTextArea.appendText(encryptedData);
        decryptTextArea.appendText(newLine);
        decryptTextArea.appendText(newLine);
        decryptTextArea.appendText("------- END ENCRYPTED DATA PACKET -------");
        decryptTextArea.appendText(newLine);
        decryptTextArea.appendText(newLine);
        decryptTextArea.appendText("---------- END PGP MESSAGE ----------");
    }
    
}
