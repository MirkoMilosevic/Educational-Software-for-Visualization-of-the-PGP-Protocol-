
package threepalmtrees.pgp;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.net.URL;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Modality;
import javafx.stage.Stage;
import scheme.SignAndEncryptSchemeController;
import scheme.SignSchemeController;
import static threepalmtrees.pgp.TestController.publicKey;
import static threepalmtrees.pgp.TestController.signatureData;
import util.MyData;
import util.PublicKeyEntry;
import util.SchemeData;
import util.SendMessageData;

/**
 * FXML Controller class
 *
 * @author Mirko
 */
public class VerifyMessageController implements Initializable {

    @FXML private TextArea verifyTextArea;
    @FXML private Button calculateHashButton;
    @FXML private Button viewHashButton;
    @FXML private Button verifyMessageButton;
    @FXML private Button nextButton;
  
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
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
        verifyTextArea.setText("---------- BEGIN PGP MESSAGE ----------");
        verifyTextArea.appendText(newLine);
        verifyTextArea.appendText(newLine);
        
        if((SendMessageData.selectedMode == 300) || (SendMessageData.selectedMode == 301) ||
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
            
            verifyTextArea.appendText("---------- BEGIN SYMETRIC KEY PACKET ----------");
            verifyTextArea.appendText(newLine);
            verifyTextArea.appendText(newLine);
            verifyTextArea.appendText("--- Key Id of Recipient's Public Key ---"); 
            verifyTextArea.appendText(newLine);
            verifyTextArea.appendText(SendMessageData.recipientPublicKeyId);
            verifyTextArea.appendText(newLine);
            verifyTextArea.appendText("--- Public Key Algorithm ---"); 
            verifyTextArea.appendText(newLine);
            verifyTextArea.appendText(SendMessageData.publicKeyAlgorithm);
            verifyTextArea.appendText(newLine);
            verifyTextArea.appendText("--- Symetric Algorithm ---"); 
            verifyTextArea.appendText(newLine);
            verifyTextArea.appendText(SendMessageData.symetricAlgorithm);
            verifyTextArea.appendText(newLine);
            verifyTextArea.appendText("--- Session Key ---");
            verifyTextArea.appendText(newLine);
            verifyTextArea.appendText(sessionKeyString);
            verifyTextArea.appendText(newLine);
            verifyTextArea.appendText(newLine);
            verifyTextArea.appendText("---------- END SYMETRIC KEY PACKET ----------");
            verifyTextArea.appendText(newLine);
            verifyTextArea.appendText(newLine);
        }
        
        verifyTextArea.appendText("------- BEGIN SIGNATURE -------");
        verifyTextArea.appendText(newLine);
        verifyTextArea.appendText(newLine);
        verifyTextArea.appendText("--- Timestamp ---");
        verifyTextArea.appendText(newLine);
        verifyTextArea.appendText(SendMessageData.message.getSignature().getTimestamp());
        verifyTextArea.appendText(newLine);
        verifyTextArea.appendText("--- Public Key Algorithm ---");
        verifyTextArea.appendText(newLine);
        verifyTextArea.appendText(SendMessageData.signatureAlgorithm);
        verifyTextArea.appendText(newLine);
        verifyTextArea.appendText("--- Hash Algorithm ---");
        verifyTextArea.appendText(newLine);
        verifyTextArea.appendText(SendMessageData.hashAlgorithm);
        verifyTextArea.appendText(newLine);
        verifyTextArea.appendText("--- Key Id of Sender's Public Key ---");
        verifyTextArea.appendText(newLine);
        verifyTextArea.appendText(SendMessageData.keyId);
        verifyTextArea.appendText(newLine);
        verifyTextArea.appendText("--- Leading Two Octets of Message Digest ---");
        verifyTextArea.appendText(newLine);
        verifyTextArea.appendText(SendMessageData.message.getSignature().getLeading2octets());
        verifyTextArea.appendText(newLine);
        verifyTextArea.appendText("--- Message Digest ---");
        verifyTextArea.appendText(newLine);
        verifyTextArea.appendText(encryptedSignature);
        verifyTextArea.appendText(newLine);
        verifyTextArea.appendText(newLine);
        verifyTextArea.appendText("------- END SIGNATURE -------");
        verifyTextArea.appendText(newLine);
        verifyTextArea.appendText(newLine);
        verifyTextArea.appendText("------- BEGIN MESSAGE -------");
        verifyTextArea.appendText(newLine);
        verifyTextArea.appendText(newLine);
        verifyTextArea.appendText("---- Filename ----");
        verifyTextArea.appendText(newLine);
        verifyTextArea.appendText(SendMessageData.message.getFilename());
        verifyTextArea.appendText(newLine);
        verifyTextArea.appendText("---- Timestamp ----");
        verifyTextArea.appendText(newLine);
        verifyTextArea.appendText(SendMessageData.message.getTimestamp());
        verifyTextArea.appendText(newLine);
        verifyTextArea.appendText("---- Data ----");
        verifyTextArea.appendText(newLine);
        verifyTextArea.appendText(SendMessageData.message.getData());
        verifyTextArea.appendText(newLine);
        verifyTextArea.appendText(newLine);
        verifyTextArea.appendText("------- END MESSAGE -------");
        verifyTextArea.appendText(newLine);
        verifyTextArea.appendText(newLine);
        verifyTextArea.appendText("---------- END PGP MESSAGE ----------");
        
        verifyTextArea.setEditable(false);
        
        viewHashButton.disableProperty().set(true);
        verifyMessageButton.disableProperty().set(true);
        nextButton.disableProperty().set(true);
        
        Tooltip hashTooltip = new Tooltip();
        hashTooltip.setText("This will calculate hash value" + "\n" 
                             + "of the received message");
        calculateHashButton.setTooltip(hashTooltip);
        
        Tooltip verifyTooltip = new Tooltip();
        verifyTooltip.setText("This will decrypt signature" + "\n" 
                              + "with selected PublicKey" + "\n" 
                              + "and compare received hash value" + "\n" 
                              + "with calculated value");
        verifyMessageButton.setTooltip(verifyTooltip);
        
        MainController.mainStatusLabel.setText("In this window receiver is verifying the sender and message");
        
        String style = "-fx-text-fill: white; -fx-font-size: 16px;";
        verifyTextArea.setStyle(style);
        
        if(SendMessageData.selectedMode == 100)
        {
            if(SignSchemeController.ssButton1 != null)
            {
                SignSchemeController.ss22.setTextFill(Color.RED);
                SignSchemeController.ss26.setTextFill(Color.RED);
                SignSchemeController.ss27.setTextFill(Color.RED);
                SignSchemeController.ss31.setTextFill(Color.RED);
                SignSchemeController.ss32.setTextFill(Color.RED);
                SignSchemeController.ss33.setTextFill(Color.RED);
                SignSchemeController.ss34.setTextFill(Color.RED);
                SignSchemeController.ss37.setTextFill(Color.RED);
                SignSchemeController.ss38.setTextFill(Color.RED);
                
                style = "-fx-border-color: red; -fx-border-width: 3 3 3 3;";
                SignSchemeController.ssButton7.setStyle(style);
                style = "-fx-border-width: 0;";
                SignSchemeController.ssButton2.setStyle(style);
            }
            
            SchemeData.sLabel[22] = true;
            SchemeData.sLabel[26] = true;
            SchemeData.sLabel[27] = true;
            SchemeData.sLabel[31] = true;
            SchemeData.sLabel[32] = true;
            SchemeData.sLabel[33] = true;
            SchemeData.sLabel[34] = true;
            SchemeData.sLabel[37] = true;
            SchemeData.sLabel[38] = true;
            SchemeData.sButton[2] = false;
            SchemeData.sButton[7] = true;
        }
        
        if(SendMessageData.selectedMode == 101)
        {
            if(SignSchemeController.ssButton1 != null)
            {
                SignSchemeController.ss34.setTextFill(Color.RED);
                SignSchemeController.ss37.setTextFill(Color.RED);
                SignSchemeController.ss38.setTextFill(Color.RED);
                
                style = "-fx-border-color: red; -fx-border-width: 3 3 3 3;";
                SignSchemeController.ssButton7.setStyle(style);
                style = "-fx-border-width: 0;";
                SignSchemeController.ssButton6.setStyle(style);
            }
          
            SchemeData.sLabel[34] = true;
            SchemeData.sLabel[37] = true;
            SchemeData.sLabel[38] = true;
            SchemeData.sButton[6] = false;
            SchemeData.sButton[7] = true;
        }
        
        if((SendMessageData.selectedMode == 110) || (SendMessageData.selectedMode == 111))
        {
            if(SignSchemeController.ssButton1 != null)
            {
                SignSchemeController.ss34.setTextFill(Color.RED);
                SignSchemeController.ss37.setTextFill(Color.RED);
                SignSchemeController.ss38.setTextFill(Color.RED);
                
                style = "-fx-border-color: red; -fx-border-width: 3 3 3 3;";
                SignSchemeController.ssButton7.setStyle(style);
                style = "-fx-border-width: 0;";
                SignSchemeController.ssButton5.setStyle(style);
            }
          
            SchemeData.sLabel[34] = true;
            SchemeData.sLabel[37] = true;
            SchemeData.sLabel[38] = true;
            SchemeData.sButton[5] = false;
            SchemeData.sButton[7] = true;
        }
        
        if((SendMessageData.selectedMode == 300) || (SendMessageData.selectedMode == 301) ||
           (SendMessageData.selectedMode == 310) || (SendMessageData.selectedMode == 311))
        {
            if(SignAndEncryptSchemeController.bbButton1 != null)
            {
                SignAndEncryptSchemeController.bb52.setTextFill(Color.RED);
                SignAndEncryptSchemeController.bb55.setTextFill(Color.RED);
                SignAndEncryptSchemeController.bb56.setTextFill(Color.RED);
            }
            
            SchemeData.bLabel[52] = true;
            SchemeData.bLabel[55] = true;
            SchemeData.bLabel[56] = true;
        }
        
        verifyTextArea.positionCaret(0);
    } 
    
    public void calculateHash() throws NoSuchAlgorithmException, IOException
    {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        outputStream.write(SendMessageData.message.getTimestampBytes());
        outputStream.write(SendMessageData.message.getData().getBytes());
        byte[] packet = outputStream.toByteArray();        
        
        MessageDigest md = MessageDigest.getInstance(SendMessageData.hashAlgorithm);
        byte[] hash = md.digest(packet);
        
        String temp = "";   
        for(int i=0; i<hash.length; i++)
        {
           int n = hash[i] & 0xFF;
           String p = Integer.toHexString(n);
           if((n & 0xF0) == 0) { temp += "0"; }
           temp += p;
           temp += " ";
           if((i % 8) == 7) { temp += "\n"; }
        }
        
        /*
        BigInteger hashValue = new BigInteger(hash);
        String hashValueString = hashValue.toString(16);
        */

        SendMessageData.calculatedHashValue = temp;
        
        viewHashButton.disableProperty().set(false);
        verifyMessageButton.disableProperty().set(false);
        
        if((SendMessageData.selectedMode == 100) || (SendMessageData.selectedMode == 101) ||
           (SendMessageData.selectedMode == 110) || (SendMessageData.selectedMode == 111))
        {
            if(SignSchemeController.ssButton1 != null)
            {
                SignSchemeController.ss35.setTextFill(Color.RED);
                SignSchemeController.ss36.setTextFill(Color.RED);
            }
          
            SchemeData.sLabel[35] = true;
            SchemeData.sLabel[36] = true;
        }
        
        if((SendMessageData.selectedMode == 300) || (SendMessageData.selectedMode == 301) ||
           (SendMessageData.selectedMode == 310) || (SendMessageData.selectedMode == 311))
        {
            if(SignAndEncryptSchemeController.bbButton1 != null)
            {
                SignAndEncryptSchemeController.bb53.setTextFill(Color.RED);
                SignAndEncryptSchemeController.bb54.setTextFill(Color.RED);
            }
            
            SchemeData.bLabel[53] = true;
            SchemeData.bLabel[54] = true;
        }
        
    }
    
    public void viewHash()
    {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Calculated Hash Value");
           
        Label label = new Label(SendMessageData.calculatedHashValue);
        label.setPadding(new Insets(2,5,2,5));
       
        Button closeButton = new Button("OK");
        closeButton.setOnAction(e -> window.close());
       
        VBox layout = new VBox(10);
        Label spacing = new Label("   ");
        layout.getChildren().addAll(label, closeButton);
        layout.setAlignment(Pos.CENTER);
        
        Scene scene = new Scene(layout, 360, 180);
        scene.getStylesheets().add("css/popup.css");
        window.setScene(scene);
        window.showAndWait();
    }
    
    public void verifyMessage()
    {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Verify Signature");
           
        Label label = new Label("Please chose public key to be used for verification:");
        label.setPadding(new Insets(2,5,2,5));
        label.setStyle("-fx-background-color: linear-gradient(#ff5400, #be1d00);");
        label.setMinWidth(360);
       
        ChoiceBox<String> publicKeyIdChoice = new ChoiceBox<>();
        publicKeyIdChoice.setMinWidth(360);
        
        for(int i=0; i<SendMessageData.receiver.getPublicKeyRing().size(); i++ )
        {
            String senderId = SendMessageData.sender.getEmail();
            String currentEntryId = SendMessageData.receiver.getPublicKeyRing().get(i).getUserId();
            
            if((senderId.equals(currentEntryId)) && 
               (SendMessageData.receiver.getPublicKeyRing().get(i).isActive()) &&
               ((SendMessageData.receiver.getPublicKeyRing().get(i).getType() == 1)|| (SendMessageData.receiver.getPublicKeyRing().get(i).getType() == 2)))
                {
                    publicKeyIdChoice.getItems().add(SendMessageData.receiver.getPublicKeyRing().get(i).getKeyId());
                }   
        }
        
        
        Button closeButton = new Button("OK");
       
        closeButton.setOnAction(e -> 
                                {   
                                    if(publicKeyIdChoice.getSelectionModel().isEmpty())
                                    {
                                        Alert alertBox = new Alert(Alert.AlertType.WARNING);
                                        alertBox.setTitle("Verify Message Warning");
                                        alertBox.setHeaderText(null);
                                        alertBox.setContentText("Please select public key for verification!");
                                        alertBox.showAndWait();
        
                                        return;
                                    }
                                    
                                    String choosenKey = publicKeyIdChoice.getValue();
                                    
                                    SendMessageData.publicKeyId = publicKeyIdChoice.getValue();
                                    
                                    String message = SendMessageData.message.getData();
                                    byte[] messageBytes = message.getBytes(); 
                                    
                                    String hashAlg = "";
                                    if(SendMessageData.hashAlgorithm.equals("SHA-1"))
                                    { hashAlg = "SHA1"; }
                                    else if(SendMessageData.hashAlgorithm.equals("SHA-224"))
                                    { hashAlg = "SHA224"; }
                                    else if(SendMessageData.hashAlgorithm.equals("SHA-256"))
                                    { hashAlg = "SHA256"; }
                                    else if(SendMessageData.hashAlgorithm.equals("MD5"))
                                    { hashAlg = "MD5"; }
                                    
                                    String signaturePars = hashAlg + "with" + SendMessageData.signatureAlgorithm;
                                    
                                    PublicKey puKey = SendMessageData.receiver.getPublicKeyById(choosenKey).getPublicKey();
                                    
                                    Signature signature = null; 
                                    
                                    boolean keyType = true;
        
                                    try {
                                         signature = Signature.getInstance(signaturePars);
                                    } catch (NoSuchAlgorithmException ex) {
                                        //Logger.getLogger(VerifyMessageController.class.getName()).log(Level.SEVERE, null, ex);
                                        System.out.println("ERROR NoSuchAlgorithm!!!");
                                    }
                                    
                                    
                                    try {
                                        signature.initVerify(puKey);
                                    } catch (InvalidKeyException ex) {
                                        //Logger.getLogger(VerifyMessageController.class.getName()).log(Level.SEVERE, null, ex);
                                        System.out.println("ERROR InvalidKey!!!");
                                        
                                        Alert alert = new Alert(AlertType.ERROR);
                                        alert.setTitle("Error");
                                        alert.setHeaderText(null);
                                        alert.setContentText("Wrong key type used!!!" + "\n" + "Verification UNSUCCESSFUL!!!" );
                                        keyType = false;
                                        
                                        alert.showAndWait();
                                    }
                                    
                                    try {
                                        signature.update(messageBytes);
                                        } catch (SignatureException ex) {
                                            //Logger.getLogger(VerifyMessageController.class.getName()).log(Level.SEVERE, null, ex);
                                            System.out.println("ERROR Signature Exception, message update!!!");
                                        }
        
                                    boolean verify = false;
                                    try {
                                        verify = signature.verify(SendMessageData.message.getSignature().getSignature());
                                        } catch (SignatureException ex) {
                                            //Logger.getLogger(VerifyMessageController.class.getName()).log(Level.SEVERE, null, ex);
                                            System.out.println("ERROR Signature Exception, signature verify!!!");
                                        }
        
                                    if(verify && keyType) 
                                    {
                                        Alert alert = new Alert(AlertType.INFORMATION);
                                        alert.setTitle("Signature Verification");
                                        alert.setHeaderText(null);
                                        String message2 = "Verification SUCCESSFUL!" + "\n" +
                                        "Key Legitimacy: " + SendMessageData.receiver.getPublicKeyById(SendMessageData.keyId).getKeyLegitimacy();
                                        alert.setContentText(message2);

                                        alert.showAndWait();
                                        
                                        System.out.println("Verification SUCCESSFUL!");
                                    }
                                    else if(keyType)
                                    {
                                        Alert alert = new Alert(AlertType.WARNING);
                                        alert.setTitle("Signature Verification");
                                        alert.setHeaderText(null);
                                        alert.setContentText("Verification UNSUCCESSFUL!!!");

                                        alert.showAndWait();
                                        
                                        System.out.println("Verification UNSUCCESSFUL!!!");
                                    }
                                    
                                    if(SendMessageData.receiver.getPublicKeyById(SendMessageData.keyId) == null)
                                    {
                                        Alert alertBox = new Alert(Alert.AlertType.WARNING);
                                        alertBox.setTitle("Verify Message Warning");
                                        alertBox.setHeaderText(null);
                                        String message2 = "You do not have needed public key in your Public Key Ring!!!";
                                        alertBox.setContentText(message2);
                                        alertBox.showAndWait();
                                    }
                                    
                                    nextButton.disableProperty().set(false);
        
                                    if((SendMessageData.selectedMode == 100) || (SendMessageData.selectedMode == 101) ||
                                        (SendMessageData.selectedMode == 110) || (SendMessageData.selectedMode == 111))
                                    {
                                        if(SignSchemeController.ssButton1 != null)
                                        {
                                            SignSchemeController.ss39.setTextFill(Color.RED);
                                            SignSchemeController.ss40.setTextFill(Color.RED);
                                            SignSchemeController.ss41.setTextFill(Color.RED);
                                        }

                                        SchemeData.sLabel[39] = true;
                                        SchemeData.sLabel[40] = true;
                                        SchemeData.sLabel[41] = true;
                                    }

                                    if((SendMessageData.selectedMode == 300) || (SendMessageData.selectedMode == 301) ||
                                       (SendMessageData.selectedMode == 310) || (SendMessageData.selectedMode == 311))
                                    {
                                        if(SignAndEncryptSchemeController.bbButton1 != null)
                                        {
                                            SignAndEncryptSchemeController.bb57.setTextFill(Color.RED);
                                            SignAndEncryptSchemeController.bb58.setTextFill(Color.RED);
                                            SignAndEncryptSchemeController.bb59.setTextFill(Color.RED);
                                        }

                                        SchemeData.bLabel[57] = true;
                                        SchemeData.bLabel[58] = true;
                                        SchemeData.bLabel[59] = true;
                                    }
                                    
                                    window.close();
                                    });
        
        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, publicKeyIdChoice, closeButton);
        layout.setAlignment(Pos.CENTER);
        
        Scene scene = new Scene(layout, 420, 200);
        scene.getStylesheets().add("css/popup.css");
        window.setScene(scene);
        window.showAndWait();
        
    }
    
    public void finish()
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
        
        SchemeData.clearS();
        SchemeData.clearB();
    }
    
}
