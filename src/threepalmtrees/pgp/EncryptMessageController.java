
package threepalmtrees.pgp;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.net.URL;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Security;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.RadioButton;
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
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.bouncycastle.asn1.x9.DHPublicKey;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.engines.ElGamalEngine;
import org.bouncycastle.jce.interfaces.ElGamalPublicKey;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.jce.spec.ElGamalParameterSpec;
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
public class EncryptMessageController implements Initializable {

    @FXML private Button createSessionKeyButton;
    @FXML private Button choosePublicKeyButton;
    @FXML private Button encryptMessageButton;
    @FXML private Button nextButton;
    @FXML private TextArea encryptTextArea;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        if((SendMessageData.selectedMode == 200) || (SendMessageData.selectedMode == 201))
        {
            String newLine = "\n";
            encryptTextArea.setText("---------- BEGIN PGP MESSAGE ----------");
            encryptTextArea.appendText(newLine);
            encryptTextArea.appendText(newLine);
            encryptTextArea.appendText("------- BEGIN MESSAGE -------");
            encryptTextArea.appendText(newLine);
            encryptTextArea.appendText(newLine);
            encryptTextArea.appendText("---- Filename ----");
            encryptTextArea.appendText(newLine);
            encryptTextArea.appendText(SendMessageData.message.getFilename());
            encryptTextArea.appendText(newLine);
            encryptTextArea.appendText("---- Timestamp ----");
            encryptTextArea.appendText(newLine);
            encryptTextArea.appendText(SendMessageData.message.getTimestamp());
            encryptTextArea.appendText(newLine);
            encryptTextArea.appendText("---- Data ----");
            encryptTextArea.appendText(newLine);
            encryptTextArea.appendText(SendMessageData.message.getData());
            encryptTextArea.appendText(newLine);
            encryptTextArea.appendText(newLine);
            encryptTextArea.appendText("------- END MESSAGE -------");
            encryptTextArea.appendText(newLine);
            encryptTextArea.appendText(newLine);
            encryptTextArea.appendText("---------- END PGP MESSAGE ----------");
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
            
            String newLine = "\n";
            encryptTextArea.setText("---------- BEGIN PGP MESSAGE ----------");
            encryptTextArea.appendText(newLine);
            encryptTextArea.appendText(newLine); 
            encryptTextArea.appendText("------- BEGIN SIGNATURE -------");
            encryptTextArea.appendText(newLine);
            encryptTextArea.appendText(newLine);
            encryptTextArea.appendText("--- Timestamp ---");
            encryptTextArea.appendText(newLine);
            encryptTextArea.appendText(SendMessageData.message.getSignature().getTimestamp());
            encryptTextArea.appendText(newLine);
            encryptTextArea.appendText("--- Public Key Algorithm ---");
            encryptTextArea.appendText(newLine);
            encryptTextArea.appendText(SendMessageData.signatureAlgorithm);
            encryptTextArea.appendText(newLine);
            encryptTextArea.appendText("--- Hash Algorithm ---");
            encryptTextArea.appendText(newLine);
            encryptTextArea.appendText(SendMessageData.hashAlgorithm);
            encryptTextArea.appendText(newLine);
            encryptTextArea.appendText("--- Key Id of Sender's Public Key ---");
            encryptTextArea.appendText(newLine);
            encryptTextArea.appendText(SendMessageData.keyId);
            encryptTextArea.appendText(newLine);
            encryptTextArea.appendText("--- Leading Two Octets of Message Digest ---");
            encryptTextArea.appendText(newLine);
            encryptTextArea.appendText(SendMessageData.message.getSignature().getLeading2octets());
            encryptTextArea.appendText(newLine);
            encryptTextArea.appendText("--- Message Digest ---");
            encryptTextArea.appendText(newLine);
            encryptTextArea.appendText(encryptedSignature);
            encryptTextArea.appendText(newLine);
            encryptTextArea.appendText(newLine);
            encryptTextArea.appendText("------- END SIGNATURE -------");
            encryptTextArea.appendText(newLine);
            encryptTextArea.appendText(newLine);
            encryptTextArea.appendText("------- BEGIN MESSAGE -------");
            encryptTextArea.appendText(newLine);
            encryptTextArea.appendText(newLine);
            encryptTextArea.appendText("---- Filename ----");
            encryptTextArea.appendText(newLine);
            encryptTextArea.appendText(SendMessageData.message.getFilename());
            encryptTextArea.appendText(newLine);
            encryptTextArea.appendText("---- Timestamp ----");
            encryptTextArea.appendText(newLine);
            encryptTextArea.appendText(SendMessageData.message.getTimestamp());
            encryptTextArea.appendText(newLine);
            encryptTextArea.appendText("---- Data ----");
            encryptTextArea.appendText(newLine);
            encryptTextArea.appendText(SendMessageData.message.getData());
            encryptTextArea.appendText(newLine);
            encryptTextArea.appendText(newLine);
            encryptTextArea.appendText("------- END MESSAGE -------");
            encryptTextArea.appendText(newLine);
            encryptTextArea.appendText(newLine);
            encryptTextArea.appendText("---------- END PGP MESSAGE ----------");
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
        
            String newLine = "\n";
            encryptTextArea.setText("---------- BEGIN PGP MESSAGE ----------");
            encryptTextArea.appendText(newLine);
            encryptTextArea.appendText(newLine);
            encryptTextArea.appendText("------- BEGIN COMPRESSED DATA -------");
            encryptTextArea.appendText(newLine);
            encryptTextArea.appendText(newLine);
            encryptTextArea.appendText(compressedString);
            encryptTextArea.appendText(newLine);
            encryptTextArea.appendText(newLine);
            encryptTextArea.appendText("------- END COMPRESSED DATA -------");
            encryptTextArea.appendText(newLine);
            encryptTextArea.appendText(newLine);
            encryptTextArea.appendText("---------- END PGP MESSAGE ----------");
        }
        
        encryptTextArea.setEditable(false);
        
        choosePublicKeyButton.disableProperty().set(true);
        encryptMessageButton.disableProperty().set(true);
        nextButton.disableProperty().set(true);
        
        Tooltip sessionKeyTooltip = new Tooltip();
        sessionKeyTooltip.setText("This will create a session key (one time key) for message encryption.");
        createSessionKeyButton.setTooltip(sessionKeyTooltip);
        
        Tooltip chooseKeyTooltip = new Tooltip();
        chooseKeyTooltip.setText("This will encrypt session key with recipient's public key.");
        choosePublicKeyButton.setTooltip(chooseKeyTooltip);
        
        Tooltip encryptTooltip = new Tooltip();
        encryptTooltip.setText("This will encrypt message with session key.");
        encryptMessageButton.setTooltip(encryptTooltip);
        
        MainController.mainStatusLabel.setText("In this window you are encrypting your message");
        
        String style = "-fx-text-fill: white; -fx-font-size: 16px;";
        
        encryptTextArea.setStyle(style);
        
        if((SendMessageData.selectedMode == 200) || (SendMessageData.selectedMode == 201))
        {
            if(EncryptSchemeController.eeButton1 != null)
            {
                EncryptSchemeController.ee1.setTextFill(Color.RED);
                EncryptSchemeController.ee5.setTextFill(Color.RED);
                EncryptSchemeController.ee6.setTextFill(Color.RED);
                EncryptSchemeController.ee7.setTextFill(Color.RED);
                EncryptSchemeController.ee8.setTextFill(Color.RED);
                
                style = "-fx-border-color: red; -fx-border-width: 3 3 3 3;";
                EncryptSchemeController.eeButton1.setStyle(style);
            }
             
            SchemeData.eLabel[1] = true;
            SchemeData.eLabel[5] = true;
            SchemeData.eLabel[6] = true;
            SchemeData.eLabel[7] = true;
            SchemeData.eLabel[8] = true;
            SchemeData.eButton[1] = true;
        }
        
        encryptTextArea.positionCaret(0);
    }    
    
    
    public void createSessionKey() throws IOException
    {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Create Session Key");
           
        VBox layout = new VBox(10);
        
        Label message = new Label("Type passphrase to create key:");
        message.setPadding(new Insets(2, 5, 2, 5));
        message.setStyle("-fx-background-color: linear-gradient(#ff5400, #be1d00);");
        message.setMinWidth(280);
        
        TextField passphrase = new TextField();
        passphrase.setMaxWidth(280);
        
        String seedString = "Salt value is: ";
        for(int i=0; i<SendMessageData.seed.length; i++)
        {
            int n = SendMessageData.seed[i] & 0xFF;
            String p = Integer.toHexString(n);
            if((SendMessageData.seed[i] & 0xF0) == 0) { seedString += "0"; }
            seedString += p;
            seedString += " ";
        }
        Label seed = new Label(seedString);
        seed.setPadding(new Insets(2, 5, 2, 5));
        seed.setMinWidth(280);
        
        Label chooseAlgLabel = new Label("Choose symetric-key algorithm:");
        chooseAlgLabel.setPadding(new Insets(2, 5, 2, 5));
        chooseAlgLabel.setStyle("-fx-background-color: linear-gradient(#ff5400, #be1d00);");
        chooseAlgLabel.setMinWidth(280);
        
        ChoiceBox<String> algorithmChoice = new ChoiceBox<>();
        algorithmChoice.getItems().addAll("CAST5", "TripleDES", "IDEA");
        algorithmChoice.setValue("CAST5");
        algorithmChoice.setMinWidth(280);
        
        RadioButton useMessage = new RadioButton("use message instead of passphrase");
        useMessage.setMinWidth(280);
        
        Label spacing = new Label("");
        spacing.setVisible(false);
        
        Button closeButton = new Button("OK");
        closeButton.setOnAction(e -> 
                                {
                                    if(passphrase.getText().isEmpty() && !useMessage.isSelected())
                                    {
                                        Alert alertBox = new Alert(Alert.AlertType.WARNING);
                                        alertBox.setTitle("Create Session Key Warning");
                                        alertBox.setHeaderText(null);
                                        alertBox.setContentText("Please type a passphrase!");
                                        alertBox.showAndWait();
        
                                        return;
                                    }
                                    
                                    byte[] packet = null;
                                    byte[] sessionKey = null;
                                    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                                    try 
                                    {
                                        if(useMessage.isSelected())
                                        {
                                            outputStream.write(SendMessageData.message.getData().getBytes());
                                        }
                                        else
                                        {
                                            outputStream.write(SendMessageData.seed);
                                            outputStream.write(passphrase.getText().getBytes());
                                        }
                                        
                                        packet = outputStream.toByteArray();
                                    } catch (IOException ex) { Logger.getLogger(EncryptMessageController.class.getName()).log(Level.SEVERE, null, ex); }
                                    
                                    MessageDigest md;
                                    try 
                                    {
                                        md = MessageDigest.getInstance("MD5");
                                        sessionKey = md.digest(packet);
                                    } catch (NoSuchAlgorithmException ex) { Logger.getLogger(EncryptMessageController.class.getName()).log(Level.SEVERE, null, ex); }
                                    
                                    SendMessageData.sessionKey = sessionKey;
                                    SendMessageData.symetricAlgorithm = algorithmChoice.getValue();
                                    
                                    
                                    formatTextArea1();
        
                                    if((SendMessageData.selectedMode == 200) || (SendMessageData.selectedMode == 201) ||
                                       (SendMessageData.selectedMode == 210) || (SendMessageData.selectedMode == 211))
                                    {
                                        if(EncryptSchemeController.eeButton1 != null)
                                        {
                                            EncryptSchemeController.ee9.setTextFill(Color.RED);
                                            EncryptSchemeController.ee10.setTextFill(Color.RED);
                                        }

                                        SchemeData.eLabel[9] = true;
                                        SchemeData.eLabel[10] = true;
                                    }

                                    if((SendMessageData.selectedMode == 300) || (SendMessageData.selectedMode == 301) ||
                                       (SendMessageData.selectedMode == 310) || (SendMessageData.selectedMode == 311))
                                    {

                                        if(SignAndEncryptSchemeController.bbButton1 != null)
                                        {
                                            SignAndEncryptSchemeController.bb17.setTextFill(Color.RED);
                                            SignAndEncryptSchemeController.bb18.setTextFill(Color.RED);
                                        }

                                        SchemeData.bLabel[17] = true;
                                        SchemeData.bLabel[18] = true;
                                    }

                                    encryptTextArea.positionCaret(0);
                                    
                                    
                                    window.close();
                                });
        
        layout.getChildren().addAll(message, passphrase, useMessage, seed, spacing, chooseAlgLabel, algorithmChoice, closeButton);
        layout.setAlignment(Pos.CENTER);
        
        Scene scene = new Scene(layout, 360, 360);
        scene.getStylesheets().add("css/popup.css");
        window.setScene(scene);
        window.showAndWait();
        
    }
    
    
    public void choosePublicKey() throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchProviderException, InvalidAlgorithmParameterException
    {
        if(Security.getProvider("BC") == null) { Security.addProvider(new BouncyCastleProvider()); }
        
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Encrypt Session Key");
           
        VBox layout = new VBox(10);
        
        Label message = new Label("Please choose recipient's public key:");
        message.setPadding(new Insets(2, 5, 2, 5));
        message.setStyle("-fx-background-color: linear-gradient(#ff5400, #be1d00);");
        message.setMinWidth(260);
        
        ChoiceBox<String> publicKeyIdChoice = new ChoiceBox<>();
        publicKeyIdChoice.setMinWidth(260);
        
        for(int i=0; i<MyData.currentUser.getPublicKeyRing().size(); i++ )
        {
            if(((MyData.currentUser.getPublicKeyRing().get(i).getType() == 1) || (MyData.currentUser.getPublicKeyRing().get(i).getType() == 3))  && 
                (MyData.currentUser.getPublicKeyRing().get(i).getUserId().equals(SendMessageData.receiver.getEmail())) &&
                (MyData.currentUser.getPublicKeyRing().get(i).isActive()))
            { publicKeyIdChoice.getItems().add(MyData.currentUser.getPublicKeyRing().get(i).getKeyId()); }
        }
        
        Button closeButton = new Button("OK");
        closeButton.setOnAction(e -> 
                                {
                                    if(publicKeyIdChoice.getSelectionModel().isEmpty())
                                    {
                                        Alert alertBox = new Alert(Alert.AlertType.WARNING);
                                        alertBox.setTitle("Encrypt Key Warning");
                                        alertBox.setHeaderText(null);
                                        alertBox.setContentText("Please select public key for encryption!");
                                        alertBox.showAndWait();
        
                                        return;
                                    }
                                    
                                    SendMessageData.recipientPublicKeyId = publicKeyIdChoice.getValue();
                                    
                                    if(MyData.currentUser.getPublicKeyById(SendMessageData.recipientPublicKeyId).getType() == 1)
                                    { 
                                        SendMessageData.publicKeyAlgorithm = "RSA"; 
                                    }
                                    else
                                    {
                                        SendMessageData.publicKeyAlgorithm = "ElGamal";
                                    }
            
                                    
                                    if(SendMessageData.publicKeyAlgorithm == "RSA")
                                    {
                                        Cipher cipher = null;
                                        try {
                                            cipher = Cipher.getInstance("RSA");
                                        } catch (NoSuchAlgorithmException ex) {
                                            Logger.getLogger(EncryptMessageController.class.getName()).log(Level.SEVERE, null, ex);
                                        } catch (NoSuchPaddingException ex) {
                                            Logger.getLogger(EncryptMessageController.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                        
                                        try {
                                            cipher.init(Cipher.ENCRYPT_MODE, (Key) MyData.currentUser.getPublicKeyById(SendMessageData.recipientPublicKeyId).getPublicKey());
                                        } catch (InvalidKeyException ex) {
                                            Logger.getLogger(EncryptMessageController.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                        
                                        try {
                                            SendMessageData.encryptedSessionKey = cipher.doFinal(SendMessageData.sessionKey);
                                        } catch (IllegalBlockSizeException ex) {
                                            Logger.getLogger(EncryptMessageController.class.getName()).log(Level.SEVERE, null, ex);
                                        } catch (BadPaddingException ex) {
                                            Logger.getLogger(EncryptMessageController.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                    }
                                    else if(SendMessageData.publicKeyAlgorithm == "ElGamal")
                                    {
                                        Cipher cipher = null;
                                        try {
                                            cipher = Cipher.getInstance("ElGamal/None/NoPadding", "BC");
                                        } catch (NoSuchAlgorithmException ex) {
                                            Logger.getLogger(EncryptMessageController.class.getName()).log(Level.SEVERE, null, ex);
                                        } catch (NoSuchProviderException ex) {
                                            Logger.getLogger(EncryptMessageController.class.getName()).log(Level.SEVERE, null, ex);
                                        } catch (NoSuchPaddingException ex) {
                                            Logger.getLogger(EncryptMessageController.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                        
                                        Key pubKey = (Key) MyData.currentUser.getPublicKeyById(SendMessageData.recipientPublicKeyId).getPublicKey();
                                        
                                        try {
                                            cipher.init(Cipher.ENCRYPT_MODE, pubKey);
                                        } catch (InvalidKeyException ex) {
                                            Logger.getLogger(EncryptMessageController.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                        
                                        byte[] encryptedKey = null;
                                        try {
                                            encryptedKey = cipher.doFinal(SendMessageData.sessionKey);
                                        } catch (IllegalBlockSizeException ex) {
                                            Logger.getLogger(EncryptMessageController.class.getName()).log(Level.SEVERE, null, ex);
                                        } catch (BadPaddingException ex) {
                                            Logger.getLogger(EncryptMessageController.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                        
                                        SendMessageData.encryptedSessionKey = encryptedKey;

                                        System.out.println("Prosao glupavi ElGamal!!!");

                                    }

                                    System.out.println("Proslo je sve OK!!!");

                                    formatTextArea2();

                                    if((SendMessageData.selectedMode == 200) || (SendMessageData.selectedMode == 201) ||
                                       (SendMessageData.selectedMode == 210) || (SendMessageData.selectedMode == 211))
                                    {
                                        if(EncryptSchemeController.eeButton1 != null)
                                        {
                                            EncryptSchemeController.ee11.setTextFill(Color.RED);
                                            EncryptSchemeController.ee12.setTextFill(Color.RED);
                                        }
                                        SchemeData.eLabel[11] = true;
                                        SchemeData.eLabel[12] = true;
                                    }

                                    if((SendMessageData.selectedMode == 300) || (SendMessageData.selectedMode == 301) ||
                                       (SendMessageData.selectedMode == 310) || (SendMessageData.selectedMode == 311))
                                    {

                                        if(SignAndEncryptSchemeController.bbButton1 != null)
                                        {
                                            SignAndEncryptSchemeController.bb19.setTextFill(Color.RED);
                                            SignAndEncryptSchemeController.bb20.setTextFill(Color.RED);
                                        }

                                        SchemeData.bLabel[19] = true;
                                        SchemeData.bLabel[20] = true; 
                                    }
                                    
                                    
                                    window.close();
                                });
        
        layout.getChildren().addAll(message, publicKeyIdChoice, closeButton);
        layout.setAlignment(Pos.CENTER);
        
        Scene scene = new Scene(layout, 300, 180);
        scene.getStylesheets().add("css/popup.css");
        window.setScene(scene);
        window.showAndWait();
        
    }
    
    
    public void encryptMessage() throws IOException, InvalidKeyException, NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException
    {
        String algorithm = "";
        
        if(SendMessageData.symetricAlgorithm.equals("CAST5")) { algorithm = "CAST5"; }
        if(SendMessageData.symetricAlgorithm.equals("IDEA")) { algorithm = "IDEA"; }
        if(SendMessageData.symetricAlgorithm.equals("TripleDES")) { algorithm = "DESede"; }
        
        byte[] packet = null;
        
        if((SendMessageData.selectedMode == 200) || (SendMessageData.selectedMode == 201))
        {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream( );
            
            byte[] filename = SendMessageData.message.getFilename().getBytes();
            byte[] time = SendMessageData.message.getTimestampBytes();
            byte[] data = SendMessageData.message.getData().getBytes();
            
            outputStream.write(filename);
            outputStream.write(time);
            outputStream.write(data);
    
            packet = outputStream.toByteArray();
        }
        
        if((SendMessageData.selectedMode == 300) || (SendMessageData.selectedMode == 301))
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
        
        if((SendMessageData.selectedMode == 210) || (SendMessageData.selectedMode == 211) ||
           (SendMessageData.selectedMode == 310) || (SendMessageData.selectedMode == 311))
        {   
            packet = SendMessageData.compressedData;
        }
        
        if(Security.getProvider("BC") == null) { Security.addProvider(new BouncyCastleProvider()); }
        
        if(SendMessageData.symetricAlgorithm.equals("CAST5"))
        {
            SecretKey secretKey = new SecretKeySpec(SendMessageData.sessionKey, 0, SendMessageData.sessionKey.length, "CAST5");
            Cipher cipher = Cipher.getInstance("CAST5", "BC");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] encryptedMessage = cipher.doFinal(packet); 
            SendMessageData.encryptedMessage = encryptedMessage;
        }
        
        if(SendMessageData.symetricAlgorithm.equals("IDEA"))
        {
            SecretKey secretKey = new SecretKeySpec(SendMessageData.sessionKey, 0, SendMessageData.sessionKey.length, "IDEA");
            Cipher cipher = Cipher.getInstance("IDEA", "BC");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] encryptedMessage = cipher.doFinal(packet); 
            SendMessageData.encryptedMessage = encryptedMessage;
        }
        
        if(SendMessageData.symetricAlgorithm.equals("TripleDES"))
        {
            SecretKey secretKey = new SecretKeySpec(SendMessageData.sessionKey, 0, SendMessageData.sessionKey.length, "DESede");
            Cipher cipher = Cipher.getInstance("DESede", "BC");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] encryptedMessage = cipher.doFinal(packet); 
            SendMessageData.encryptedMessage = encryptedMessage;
        }
        
        formatTextArea3(); 
        
        if((SendMessageData.selectedMode == 200) || (SendMessageData.selectedMode == 201) ||
           (SendMessageData.selectedMode == 210) || (SendMessageData.selectedMode == 211))
        {
            if(EncryptSchemeController.eeButton1 != null)
            {
                EncryptSchemeController.ee13.setTextFill(Color.RED);
                EncryptSchemeController.ee14.setTextFill(Color.RED);
                
                String style = "-fx-border-color: red; -fx-border-width: 3 3 3 3;";
                EncryptSchemeController.eeButton3.setStyle(style);
                
                if((SendMessageData.selectedMode == 210) || (SendMessageData.selectedMode == 211))
                {
                    style = "-fx-border-width: 0;";
                    EncryptSchemeController.eeButton2.setStyle(style);
                }
                else
                {
                    style = "-fx-border-width: 0;";
                    EncryptSchemeController.eeButton1.setStyle(style);
                }
            }
             
            SchemeData.eLabel[13] = true;
            SchemeData.eLabel[14] = true;
            if((SendMessageData.selectedMode == 210) || (SendMessageData.selectedMode == 211)) { SchemeData.eButton[2] = false; }
            else { SchemeData.eButton[1] = false; }
            SchemeData.eButton[3] = true;
        }
        
        if((SendMessageData.selectedMode == 300) || (SendMessageData.selectedMode == 301) ||
           (SendMessageData.selectedMode == 310) || (SendMessageData.selectedMode == 311))
        {
            
            if(SignAndEncryptSchemeController.bbButton1 != null)
            {
                SignAndEncryptSchemeController.bb21.setTextFill(Color.RED);
                SignAndEncryptSchemeController.bb22.setTextFill(Color.RED);
            }
            
            SchemeData.bLabel[21] = true;
            SchemeData.bLabel[22] = true;
            
            if(SignAndEncryptSchemeController.bbButton1 != null)
            {
                String style = "-fx-border-color: red; -fx-border-width: 3 3 3 3;";
                SignAndEncryptSchemeController.bbButton4.setStyle(style);
                
                if((SendMessageData.selectedMode == 310) || (SendMessageData.selectedMode == 311))
                {
                    style = "-fx-border-width: 0;";
                    SignAndEncryptSchemeController.bbButton3.setStyle(style);
                }
                else
                {
                    style = "-fx-border-width: 0;";
                    SignAndEncryptSchemeController.bbButton2.setStyle(style);
                }
            }
            
            SchemeData.bButton[2] = false;
            SchemeData.bButton[3] = false;
            SchemeData.bButton[4] = true;
        }
        
        encryptTextArea.positionCaret(0);
    }
    
    
    public void nextScene() throws IOException
    {
        String nextSceneResource = "";
        
        int s1 = SendMessageData.recipientPublicKeyId.getBytes().length;
        int s2 = SendMessageData.publicKeyAlgorithm.getBytes().length;
        int s3 = SendMessageData.symetricAlgorithm.getBytes().length;
        int s4 = SendMessageData.encryptedSessionKey.length;
        int s5 = SendMessageData.encryptedMessage.length;
        
        int sum = s1 + s2 + s3 + s4 + s5;
        
        if((SendMessageData.selectedMode == 200) || (SendMessageData.selectedMode == 210) ||
           (SendMessageData.selectedMode == 300) || (SendMessageData.selectedMode == 310))
        {
            if((MyData.maxBlockSize == 0) || (sum < MyData.maxBlockSize))
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Send Message");
                alert.setHeaderText(null);
                String message = "Message has been sent to " + SendMessageData.receiver.getName() + " " + SendMessageData.receiver.getSurname() + ".";
                alert.setContentText(message);
                alert.showAndWait();
            
                nextSceneResource = "DecryptMessage.fxml";
            
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
        }
        
        if((SendMessageData.selectedMode == 201) || (SendMessageData.selectedMode == 211) ||
           (SendMessageData.selectedMode == 301) || (SendMessageData.selectedMode == 311))
        {
            nextSceneResource = "ConvertMessage.fxml";
        }
        
        AnchorPane nextScene = (AnchorPane) FXMLLoader.load(getClass().getResource(nextSceneResource));
        MainController.mainContentPane.getChildren().clear();
        MainController.mainContentPane.getChildren().add(nextScene);
        nextScene.toFront();
        
        if((SendMessageData.selectedMode == 201) || (SendMessageData.selectedMode == 211))
        {
            if(EncryptSchemeController.eeButton1 != null)
            {
                EncryptSchemeController.ee15.setTextFill(Color.RED);
                EncryptSchemeController.ee16.setTextFill(Color.RED);
            }
             
            SchemeData.eLabel[15] = true;
            SchemeData.eLabel[16] = true;
        }
        
        if((SendMessageData.selectedMode == 200) || (SendMessageData.selectedMode == 210))
        {
            if(EncryptSchemeController.eeButton1 != null)
            {
                EncryptSchemeController.ee15.setTextFill(Color.RED);
                EncryptSchemeController.ee20.setTextFill(Color.RED);
                EncryptSchemeController.ee21.setTextFill(Color.RED);
                EncryptSchemeController.ee22.setTextFill(Color.RED);
                EncryptSchemeController.ee19.setTextFill(Color.RED);
            }
             
            SchemeData.eLabel[15] = true;
            SchemeData.eLabel[20] = true;
            SchemeData.eLabel[21] = true;
            SchemeData.eLabel[22] = true;
            SchemeData.eLabel[19] = true;
        }
        
        if((SendMessageData.selectedMode == 300) || (SendMessageData.selectedMode == 310))
        {
            
            if(SignAndEncryptSchemeController.bbButton1 != null)
            {
                SignAndEncryptSchemeController.bb23.setTextFill(Color.RED);
                SignAndEncryptSchemeController.bb27.setTextFill(Color.RED);
                SignAndEncryptSchemeController.bb28.setTextFill(Color.RED);
                SignAndEncryptSchemeController.bb29.setTextFill(Color.RED);
                SignAndEncryptSchemeController.bb30.setTextFill(Color.RED);
            }
            
            SchemeData.bLabel[23] = true;
            SchemeData.bLabel[27] = true;
            SchemeData.bLabel[28] = true;
            SchemeData.bLabel[29] = true;
            SchemeData.bLabel[30] = true;
        }
        
        if((SendMessageData.selectedMode == 301) || (SendMessageData.selectedMode == 311))
        {
            
            if(SignAndEncryptSchemeController.bbButton1 != null)
            {
                SignAndEncryptSchemeController.bb23.setTextFill(Color.RED);
                SignAndEncryptSchemeController.bb24.setTextFill(Color.RED);
            }
            
            SchemeData.bLabel[23] = true;
            SchemeData.bLabel[24] = true;
        }
        
    }
    
    
    public void formatTextArea1()
    {
        encryptTextArea.clear();
         
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
        encryptTextArea.setText("---------- BEGIN PGP MESSAGE ----------");
        encryptTextArea.appendText(newLine);
        encryptTextArea.appendText(newLine);
        encryptTextArea.appendText("---------- BEGIN SYMETRIC KEY PACKET ----------");
        encryptTextArea.appendText(newLine);
        encryptTextArea.appendText(newLine);
        encryptTextArea.appendText("--- Key Id of Recipient's Public Key ---"); 
        encryptTextArea.appendText(newLine);
        encryptTextArea.appendText("--- Public Key Algorithm ---"); 
        encryptTextArea.appendText(newLine);
        encryptTextArea.appendText("--- Symetric Algorithm ---"); 
        encryptTextArea.appendText(newLine);
        encryptTextArea.appendText(SendMessageData.symetricAlgorithm);
        encryptTextArea.appendText(newLine);
        encryptTextArea.appendText("--- Session Key ---");
        encryptTextArea.appendText(newLine);
        encryptTextArea.appendText(sessionKeyString);
        encryptTextArea.appendText(newLine);
        encryptTextArea.appendText(newLine);
        encryptTextArea.appendText("---------- END SYMETRIC KEY PACKET ----------");
        encryptTextArea.appendText(newLine);
        encryptTextArea.appendText(newLine);
        
        if((SendMessageData.selectedMode == 200) || (SendMessageData.selectedMode == 201))
        {
            encryptTextArea.appendText("------- BEGIN MESSAGE -------");
            encryptTextArea.appendText(newLine);
            encryptTextArea.appendText(newLine);
            encryptTextArea.appendText("---- Filename ----");
            encryptTextArea.appendText(newLine);
            encryptTextArea.appendText(SendMessageData.message.getFilename());
            encryptTextArea.appendText(newLine);
            encryptTextArea.appendText("---- Timestamp ----");
            encryptTextArea.appendText(newLine);
            encryptTextArea.appendText(SendMessageData.message.getTimestamp());
            encryptTextArea.appendText(newLine);
            encryptTextArea.appendText("---- Data ----");
            encryptTextArea.appendText(newLine);
            encryptTextArea.appendText(SendMessageData.message.getData());
            encryptTextArea.appendText(newLine);
            encryptTextArea.appendText(newLine);
            encryptTextArea.appendText("------- END MESSAGE -------");
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
             
            encryptTextArea.appendText("------- BEGIN SIGNATURE -------");
            encryptTextArea.appendText(newLine);
            encryptTextArea.appendText(newLine);
            encryptTextArea.appendText("--- Timestamp ---");
            encryptTextArea.appendText(newLine);
            encryptTextArea.appendText(SendMessageData.message.getSignature().getTimestamp());
            encryptTextArea.appendText(newLine);
            encryptTextArea.appendText("--- Public Key Algorithm ---");
            encryptTextArea.appendText(newLine);
            encryptTextArea.appendText(SendMessageData.signatureAlgorithm);
            encryptTextArea.appendText(newLine);
            encryptTextArea.appendText("--- Hash Algorithm ---");
            encryptTextArea.appendText(newLine);
            encryptTextArea.appendText(SendMessageData.hashAlgorithm);
            encryptTextArea.appendText(newLine);
            encryptTextArea.appendText("--- Key Id of Sender's Public Key ---");
            encryptTextArea.appendText(newLine);
            encryptTextArea.appendText(SendMessageData.keyId);
            encryptTextArea.appendText(newLine);
            encryptTextArea.appendText("--- Leading Two Octets of Message Digest ---");
            encryptTextArea.appendText(newLine);
            encryptTextArea.appendText(SendMessageData.message.getSignature().getLeading2octets());
            encryptTextArea.appendText(newLine);
            encryptTextArea.appendText("--- Message Digest ---");
            encryptTextArea.appendText(newLine);
            encryptTextArea.appendText(encryptedSignature);
            encryptTextArea.appendText(newLine);
            encryptTextArea.appendText(newLine);
            encryptTextArea.appendText("------- END SIGNATURE -------");
            encryptTextArea.appendText(newLine);
            encryptTextArea.appendText(newLine);
            encryptTextArea.appendText("------- BEGIN MESSAGE -------");
            encryptTextArea.appendText(newLine);
            encryptTextArea.appendText(newLine);
            encryptTextArea.appendText("---- Filename ----");
            encryptTextArea.appendText(newLine);
            encryptTextArea.appendText(SendMessageData.message.getFilename());
            encryptTextArea.appendText(newLine);
            encryptTextArea.appendText("---- Timestamp ----");
            encryptTextArea.appendText(newLine);
            encryptTextArea.appendText(SendMessageData.message.getTimestamp());
            encryptTextArea.appendText(newLine);
            encryptTextArea.appendText("---- Data ----");
            encryptTextArea.appendText(newLine);
            encryptTextArea.appendText(SendMessageData.message.getData());
            encryptTextArea.appendText(newLine);
            encryptTextArea.appendText(newLine);
            encryptTextArea.appendText("------- END MESSAGE -------");
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
       
            encryptTextArea.appendText("------- BEGIN COMPRESSED DATA -------");
            encryptTextArea.appendText(newLine);
            encryptTextArea.appendText(newLine);
            encryptTextArea.appendText(compressedString);
            encryptTextArea.appendText(newLine);
            encryptTextArea.appendText(newLine);
            encryptTextArea.appendText("------- END COMPRESSED DATA -------");
        }
        
        encryptTextArea.appendText(newLine);
        encryptTextArea.appendText(newLine);
        encryptTextArea.appendText("---------- END PGP MESSAGE ----------");
        
        choosePublicKeyButton.disableProperty().set(false);
        encryptTextArea.positionCaret(0);
    }
    
    
    public void formatTextArea2()
    {
        encryptTextArea.clear();
        
        String encryptedSessionKey = "";
        for(int i=0; i<SendMessageData.encryptedSessionKey.length; i++)
        {  
           int m = SendMessageData.encryptedSessionKey[i] & 0xFF;
           String n = Character.toString((char) m);
           if(!n.equals("\n"))
           { encryptedSessionKey += n; }
           if((i % 32) == 31) { encryptedSessionKey += "\n"; }
        }

        String newLine = "\n";
        encryptTextArea.setText("---------- BEGIN PGP MESSAGE ----------");
        encryptTextArea.appendText(newLine);
        encryptTextArea.appendText(newLine);
        encryptTextArea.appendText("---------- BEGIN SYMETRIC KEY PACKET ----------");
        encryptTextArea.appendText(newLine);
        encryptTextArea.appendText(newLine);
        encryptTextArea.appendText("--- Key Id of Recipient's Public Key ---"); 
        encryptTextArea.appendText(newLine);
        encryptTextArea.appendText(SendMessageData.recipientPublicKeyId);
        encryptTextArea.appendText(newLine);
        encryptTextArea.appendText("--- Public Key Algorithm ---"); 
        encryptTextArea.appendText(newLine);
        encryptTextArea.appendText(SendMessageData.publicKeyAlgorithm);
        encryptTextArea.appendText(newLine);
        encryptTextArea.appendText("--- Symetric Algorithm ---"); 
        encryptTextArea.appendText(newLine);
        encryptTextArea.appendText(SendMessageData.symetricAlgorithm);
        encryptTextArea.appendText(newLine);
        encryptTextArea.appendText("--- Session Key ---");
        encryptTextArea.appendText(newLine);
        encryptTextArea.appendText(encryptedSessionKey);
        encryptTextArea.appendText(newLine);
        encryptTextArea.appendText(newLine);
        encryptTextArea.appendText("---------- END SYMETRIC KEY PACKET ----------");
        encryptTextArea.appendText(newLine);
        encryptTextArea.appendText(newLine);
        
        if((SendMessageData.selectedMode == 200) || (SendMessageData.selectedMode == 201))
        {
            encryptTextArea.appendText("------- BEGIN MESSAGE -------");
            encryptTextArea.appendText(newLine);
            encryptTextArea.appendText(newLine);
            encryptTextArea.appendText("---- Filename ----");
            encryptTextArea.appendText(newLine);
            encryptTextArea.appendText(SendMessageData.message.getFilename());
            encryptTextArea.appendText(newLine);
            encryptTextArea.appendText("---- Timestamp ----");
            encryptTextArea.appendText(newLine);
            encryptTextArea.appendText(SendMessageData.message.getTimestamp());
            encryptTextArea.appendText(newLine);
            encryptTextArea.appendText("---- Data ----");
            encryptTextArea.appendText(newLine);
            encryptTextArea.appendText(SendMessageData.message.getData());
            encryptTextArea.appendText(newLine);
            encryptTextArea.appendText(newLine);
            encryptTextArea.appendText("------- END MESSAGE -------");
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
             
            encryptTextArea.appendText("------- BEGIN SIGNATURE -------");
            encryptTextArea.appendText(newLine);
            encryptTextArea.appendText(newLine);
            encryptTextArea.appendText("--- Timestamp ---");
            encryptTextArea.appendText(newLine);
            encryptTextArea.appendText(SendMessageData.message.getSignature().getTimestamp());
            encryptTextArea.appendText(newLine);
            encryptTextArea.appendText("--- Public Key Algorithm ---");
            encryptTextArea.appendText(newLine);
            encryptTextArea.appendText(SendMessageData.signatureAlgorithm);
            encryptTextArea.appendText(newLine);
            encryptTextArea.appendText("--- Hash Algorithm ---");
            encryptTextArea.appendText(newLine);
            encryptTextArea.appendText(SendMessageData.hashAlgorithm);
            encryptTextArea.appendText(newLine);
            encryptTextArea.appendText("--- Key Id of Sender's Public Key ---");
            encryptTextArea.appendText(newLine);
            encryptTextArea.appendText(SendMessageData.keyId);
            encryptTextArea.appendText(newLine);
            encryptTextArea.appendText("--- Leading Two Octets of Message Digest ---");
            encryptTextArea.appendText(newLine);
            encryptTextArea.appendText(SendMessageData.message.getSignature().getLeading2octets());
            encryptTextArea.appendText(newLine);
            encryptTextArea.appendText("--- Message Digest ---");
            encryptTextArea.appendText(newLine);
            encryptTextArea.appendText(encryptedSignature);
            encryptTextArea.appendText(newLine);
            encryptTextArea.appendText(newLine);
            encryptTextArea.appendText("------- END SIGNATURE -------");
            encryptTextArea.appendText(newLine);
            encryptTextArea.appendText(newLine);
            encryptTextArea.appendText("------- BEGIN MESSAGE -------");
            encryptTextArea.appendText(newLine);
            encryptTextArea.appendText(newLine);
            encryptTextArea.appendText("---- Filename ----");
            encryptTextArea.appendText(newLine);
            encryptTextArea.appendText(SendMessageData.message.getFilename());
            encryptTextArea.appendText(newLine);
            encryptTextArea.appendText("---- Timestamp ----");
            encryptTextArea.appendText(newLine);
            encryptTextArea.appendText(SendMessageData.message.getTimestamp());
            encryptTextArea.appendText(newLine);
            encryptTextArea.appendText("---- Data ----");
            encryptTextArea.appendText(newLine);
            encryptTextArea.appendText(SendMessageData.message.getData());
            encryptTextArea.appendText(newLine);
            encryptTextArea.appendText(newLine);
            encryptTextArea.appendText("------- END MESSAGE -------");
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
       
            encryptTextArea.appendText("------- BEGIN COMPRESSED DATA -------");
            encryptTextArea.appendText(newLine);
            encryptTextArea.appendText(newLine);
            encryptTextArea.appendText(compressedString);
            encryptTextArea.appendText(newLine);
            encryptTextArea.appendText(newLine);
            encryptTextArea.appendText("------- END COMPRESSED DATA -------");
        }
        
        encryptTextArea.appendText(newLine);
        encryptTextArea.appendText(newLine);
        encryptTextArea.appendText("---------- END PGP MESSAGE ----------");
        
        encryptMessageButton.disableProperty().set(false);
        encryptTextArea.positionCaret(0);
    }
    
    
    public void formatTextArea3()
    {
        encryptTextArea.clear();
        
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
        encryptTextArea.setText("---------- BEGIN PGP MESSAGE ----------");
        encryptTextArea.appendText(newLine);
        encryptTextArea.appendText(newLine);
        encryptTextArea.appendText("---------- BEGIN SYMETRIC KEY PACKET ----------");
        encryptTextArea.appendText(newLine);
        encryptTextArea.appendText(newLine);
        encryptTextArea.appendText("--- Key Id of Recipient's Public Key ---"); 
        encryptTextArea.appendText(newLine);
        encryptTextArea.appendText(SendMessageData.recipientPublicKeyId);
        encryptTextArea.appendText(newLine);
        encryptTextArea.appendText("--- Public Key Algorithm ---"); 
        encryptTextArea.appendText(newLine);
        encryptTextArea.appendText(SendMessageData.publicKeyAlgorithm);
        encryptTextArea.appendText(newLine);
        encryptTextArea.appendText("--- Symetric Algorithm ---"); 
        encryptTextArea.appendText(newLine);
        encryptTextArea.appendText(SendMessageData.symetricAlgorithm);
        encryptTextArea.appendText(newLine);
        encryptTextArea.appendText("--- Session Key ---");
        encryptTextArea.appendText(newLine);
        encryptTextArea.appendText(encryptedSessionKey);
        encryptTextArea.appendText(newLine);
        encryptTextArea.appendText(newLine);
        encryptTextArea.appendText("---------- END SYMETRIC KEY PACKET ----------");
        encryptTextArea.appendText(newLine);
        encryptTextArea.appendText(newLine);
        encryptTextArea.appendText("------- BEGIN ENCRYPTED DATA PACKET -------");
        encryptTextArea.appendText(newLine);
        encryptTextArea.appendText(newLine);
        encryptTextArea.appendText(encryptedData);
        encryptTextArea.appendText(newLine);
        encryptTextArea.appendText(newLine);
        encryptTextArea.appendText("------- END ENCRYPTED DATA PACKET -------");
        encryptTextArea.appendText(newLine);
        encryptTextArea.appendText(newLine);
        encryptTextArea.appendText("---------- END PGP MESSAGE ----------");
        
        nextButton.disableProperty().set(false);
        encryptTextArea.positionCaret(0);
    }
}
