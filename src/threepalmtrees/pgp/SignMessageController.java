
package threepalmtrees.pgp;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URL;
import java.nio.ByteBuffer;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Signature;
import java.security.SignatureException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
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
public class SignMessageController implements Initializable {

    @FXML private TextArea signTextArea;
    @FXML private Button hashButton;
    @FXML private Button viewHashButton;
    @FXML private Button signButton;
    @FXML private Button nextButton;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        String newLine = "\n";
        signTextArea.setText("---------- BEGIN PGP MESSAGE ----------");
        signTextArea.appendText(newLine);
        signTextArea.appendText(newLine);
        signTextArea.appendText("------- BEGIN MESSAGE -------");
        signTextArea.appendText(newLine);
        signTextArea.appendText(newLine);
        signTextArea.appendText("---- Filename ----");
        signTextArea.appendText(newLine);
        signTextArea.appendText(SendMessageData.message.getFilename());
        signTextArea.appendText(newLine);
        signTextArea.appendText("---- Timestamp ----");
        signTextArea.appendText(newLine);
        signTextArea.appendText(SendMessageData.message.getTimestamp());
        signTextArea.appendText(newLine);
        signTextArea.appendText("---- Data ----");
        signTextArea.appendText(newLine);
        signTextArea.appendText(SendMessageData.message.getData());
        signTextArea.appendText(newLine);
        signTextArea.appendText(newLine);
        signTextArea.appendText("------- END MESSAGE -------");
        signTextArea.appendText(newLine);
        signTextArea.appendText(newLine);
        signTextArea.appendText("---------- END PGP MESSAGE ----------");
        
        signTextArea.setEditable(false);
        
        viewHashButton.disableProperty().set(true);
        signButton.disableProperty().set(true);
        nextButton.disableProperty().set(true);
        
        Tooltip hashTooltip = new Tooltip();
        hashTooltip.setText("This will calculate hash value of your message");
        hashButton.setTooltip(hashTooltip);
        
        Tooltip signTooltip = new Tooltip();
        signTooltip.setText("This will encrypt calculated hash valu" + "\n" + "with selected PrivateKey");
        signButton.setTooltip(signTooltip);
        
        MainController.mainStatusLabel.setText("In this window you are signing your message");
        
        String style = "-fx-text-fill: white; -fx-font-size: 16px;";
        
        signTextArea.setStyle(style);
        
        if((SendMessageData.selectedMode == 100) || (SendMessageData.selectedMode == 101) ||
           (SendMessageData.selectedMode == 110) || (SendMessageData.selectedMode == 111))
        {
            style = "-fx-border-color: red; -fx-border-width: 3 3 3 3;";
            
            if(SignSchemeController.ssButton1 != null)
            { SignSchemeController.ssButton1.setStyle(style); }
            SchemeData.sButton[1] = true;
        }
        
        if((SendMessageData.selectedMode == 300) || (SendMessageData.selectedMode == 301) ||
           (SendMessageData.selectedMode == 310) || (SendMessageData.selectedMode == 311))
        {
            style = "-fx-border-color: red; -fx-border-width: 3 3 3 3;";
            
            if(SignAndEncryptSchemeController.bbButton1 != null)
            { SignAndEncryptSchemeController.bbButton1.setStyle(style); }
            SchemeData.bButton[1] = true;
        }
        
        signTextArea.positionCaret(0);
    }

    
    
    public void calculateHash() throws NoSuchAlgorithmException, IOException
    {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Hash Algorithm");
        
        ChoiceBox<String> algorithmChoic = new ChoiceBox<>();
        algorithmChoic.getItems().addAll("MD5", "SHA-1", "SHA-224", "SHA-256");
        algorithmChoic.setValue("SHA-1");
        algorithmChoic.setMinWidth(220);
        
        Button closeButton = new Button("OK");
        closeButton.setOnAction(e -> 
                                {
                                    SendMessageData.hashAlgorithm = algorithmChoic.getValue();
                                    
                                    if(SendMessageData.hashAlgorithm.equals("MD5"))
                                    {
                                       Alert alertBox = new Alert(Alert.AlertType.WARNING);
                                        alertBox.setTitle("Hash Algorithm Warning");
                                        alertBox.setHeaderText(null);
                                        String s = "Caution, MD5 is found to be weak!";
                                        alertBox.setContentText(s);
                                        alertBox.showAndWait(); 
                                    }
                                    
                                    
                                    MessageDigest md = null;
                                    try {
                                            md = MessageDigest.getInstance(SendMessageData.hashAlgorithm);
                                        } catch (NoSuchAlgorithmException ex) {
                                    Logger.getLogger(SignMessageController.class.getName()).log(Level.SEVERE, null, ex);
                                    }
        
                                    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                                    
                                    try {
                                            outputStream.write(SendMessageData.message.getTimestampBytes());
                                        } catch (IOException ex) {
                                    Logger.getLogger(SignMessageController.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                    
                                    try {
                                            outputStream.write(SendMessageData.message.getData().getBytes());
                                        } catch (IOException ex) {
                                    Logger.getLogger(SignMessageController.class.getName()).log(Level.SEVERE, null, ex);
                                    }
        
                                    byte[] packet = outputStream.toByteArray();        
                                    byte[] hash = md.digest(packet);
                                    SendMessageData.message.getSignature().setHash(hash);
        
                                    String LeadingOctetString = "";
                                    for(int i=0; i<2; i++)
                                    {
                                        int n = hash[i] & 0xFF;
                                        String p = Integer.toHexString(n);
                                        if((hash[i] & 0xF0) == 0) { LeadingOctetString += "0"; }
                                        LeadingOctetString += p;
                                        LeadingOctetString += " ";
                                    }
                                    
                                    SendMessageData.message.getSignature().setLeading2octets(LeadingOctetString);
        
                                    BigInteger hashValue = new BigInteger(hash);     
                                    String hashValueString = hashValue.toString(16); 
        
                                    viewHashButton.disableProperty().set(false);
                                    signButton.disableProperty().set(false);
        
                                    System.out.println("Hash length in octets is " + hash.length + "!");
        
                                    if((SendMessageData.selectedMode == 100) || (SendMessageData.selectedMode == 101) ||
                                    (SendMessageData.selectedMode == 110) || (SendMessageData.selectedMode == 111))
                                    {
                                        if(SignSchemeController.ssButton1 != null)
                                        {
                                            SignSchemeController.ss1.setTextFill(Color.RED);
                                            SignSchemeController.ss2.setTextFill(Color.RED); 
                                        }
                                        
                                        SchemeData.sLabel[1] = true;
                                        SchemeData.sLabel[2] = true;
                                        
                                    }
        
                                    if((SendMessageData.selectedMode == 300) || (SendMessageData.selectedMode == 301) ||
                                       (SendMessageData.selectedMode == 310) || (SendMessageData.selectedMode == 311))
                                    {
                                        if(SignAndEncryptSchemeController.bbButton1 != null)
                                        {
                                            SignAndEncryptSchemeController.bb1.setTextFill(Color.RED);
                                            SignAndEncryptSchemeController.bb2.setTextFill(Color.RED);

                                        }

                                        SchemeData.bLabel[1] = true;
                                        SchemeData.bLabel[2] = true;
                                    }
                                    
                                    window.close();
                                });
       
        VBox layout = new VBox(10);
        Label message = new Label("Please chose hash algorithm:");
        message.setPadding(new Insets(2, 5, 2, 5));
        message.setStyle("-fx-background-color: linear-gradient(#ff5400, #be1d00);");
        message.setMinWidth(220);
        Label spacing = new Label("   ");
        layout.getChildren().addAll(message, algorithmChoic, closeButton);
        layout.setAlignment(Pos.CENTER);
        
        Scene scene = new Scene(layout, 260, 180);
        scene.getStylesheets().add("css/popup.css");
        window.setScene(scene);
        window.showAndWait();
        
        
            
    }
    
    public void viewHash()
    {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Calculated Hash Value");
       
        byte[] hashValue = SendMessageData.message.getSignature().getHash();
           
        String temp = "";   
        for(int i=0; i<hashValue.length; i++)
        {
           int n = hashValue[i] & 0xFF;
           String p = Integer.toHexString(n);
           if((hashValue[i] & 0xF0) == 0) { temp += "0"; }
           temp += p;
           temp += " ";
           if((i % 8) == 7) { temp += "\n"; }
        }
           
        Label label = new Label(temp);
        label.setPadding(new Insets(2, 5, 2, 5));
       
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
    
    public void signMessage() throws NoSuchAlgorithmException, InvalidKeyException, SignatureException, UnsupportedEncodingException
    {
        String message = SendMessageData.message.getData();
        byte[] messageBytes = message.getBytes();
        
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Create Signature");
           
        Label label = new Label("Please choose private key to be used for signing and enter password:");
        label.setPadding(new Insets(2,5,2,5));
        label.setStyle("-fx-background-color: linear-gradient(#ff5400, #be1d00);");
        //label.setMinWidth(400);
       
        ChoiceBox<String> privateKeyIdChoice = new ChoiceBox<>();
        privateKeyIdChoice.setMinWidth(400);
        
        TextField password = new TextField();
        password.setMaxWidth(400);
        
        for(int i=0; i<MyData.currentUser.getPrivateKeyRing().size(); i++ )
        {
            if((MyData.currentUser.getPrivateKeyRing().get(i).isActive()) &&
               ((MyData.currentUser.getPrivateKeyRing().get(i).getType() == 1) || (MyData.currentUser.getPrivateKeyRing().get(i).getType() == 2)))
            { privateKeyIdChoice.getItems().add(MyData.currentUser.getPrivateKeyRing().get(i).getKeyId()); }        
        }
        
        Button closeButton = new Button("OK");
        closeButton.setOnAction(e -> 
                                {
                                    if(privateKeyIdChoice.getSelectionModel().isEmpty())
                                    {
                                        Alert alertBox = new Alert(Alert.AlertType.WARNING);
                                        alertBox.setTitle("Sign Message Warning");
                                        alertBox.setHeaderText(null);
                                        alertBox.setContentText("Please select private key for signing!");
                                        alertBox.showAndWait();
        
                                        return;
                                    }
                                    
                                    SendMessageData.keyId = privateKeyIdChoice.getValue();
                                    
                                    if(password.getText().isEmpty())
                                    {
                                        Alert alertBox = new Alert(Alert.AlertType.WARNING);
                                        alertBox.setTitle("Sign Message Warning");
                                        alertBox.setHeaderText(null);
                                        alertBox.setContentText("Please enter password for this key!");
                                        alertBox.showAndWait();
        
                                        return;
                                    }
                                    else
                                    {
                                        String password1 = MyData.currentUser.searchForKeyById(SendMessageData.keyId).getPassword();
                                        
                                        String password2 = password.getText();
                                        
                                        if(!password1.equals(password2))
                                        {
                                            Alert alertBox = new Alert(Alert.AlertType.WARNING);
                                            alertBox.setTitle("Sign Message Warning");
                                            alertBox.setHeaderText(null);
                                            alertBox.setContentText("Wrong password for this key!");
                                            alertBox.showAndWait();
        
                                            return;
                                        }
                                    }
                                    
                                    if((MyData.currentUser.searchForKeyById(SendMessageData.keyId).getType() == 2) && (SendMessageData.hashAlgorithm.equals("MD5")))
                                    {
                                        Alert alertBox = new Alert(Alert.AlertType.WARNING);
                                        alertBox.setTitle("Sign Message Warning");
                                        alertBox.setHeaderText(null);
                                        String s = "You cannot use MD5 with DSA key!" + "\n" + "Caution, MD5 is found to be weak!";
                                        alertBox.setContentText(s);
                                        alertBox.showAndWait();
        
                                        return;
                                    }
                           
                                    if(MyData.currentUser.searchForKeyById(SendMessageData.keyId).getType() == 1)
                                    { SendMessageData.signatureAlgorithm = "RSA"; }
                                    else if(MyData.currentUser.searchForKeyById(SendMessageData.keyId).getType() == 2)
                                    { SendMessageData.signatureAlgorithm = "DSA"; }
                                    
                                    String hashAlg = "";
                                    if(SendMessageData.hashAlgorithm.equals("SHA-1"))
                                    { hashAlg = "SHA1"; }
                                    else if(SendMessageData.hashAlgorithm.equals("SHA-224"))
                                    { hashAlg = "SHA224"; }
                                    else if(SendMessageData.hashAlgorithm.equals("SHA-256"))
                                    { hashAlg = "SHA256"; }
                                    else if(SendMessageData.hashAlgorithm.equals("MD5"))
                                    { hashAlg = "MD5"; }

                                    String signaturePars = "";
                                    Signature signature = null;

                                    signaturePars = hashAlg + "with" + SendMessageData.signatureAlgorithm;
                                    
                                    try {
                                        signature = Signature.getInstance(signaturePars);
                                    } catch (NoSuchAlgorithmException ex) {
                                        Logger.getLogger(SignMessageController.class.getName()).log(Level.SEVERE, null, ex);
                                    }

                                    try {
                                        signature.initSign(MyData.currentUser.searchForKeyById(SendMessageData.keyId).getPrivateKey());
                                    } catch (InvalidKeyException ex) {
                                        Logger.getLogger(SignMessageController.class.getName()).log(Level.SEVERE, null, ex);
                                    }

                                    try {
                                        signature.update(SendMessageData.message.getData().getBytes());
                                    } catch (SignatureException ex) {
                                        Logger.getLogger(SignMessageController.class.getName()).log(Level.SEVERE, null, ex);
                                    }

                                    byte[] signatureBytes = null;
                                    try {
                                        signatureBytes = signature.sign();
                                    } catch (SignatureException ex) {
                                        Logger.getLogger(SignMessageController.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                    SendMessageData.message.getSignature().setSignature(signatureBytes);

                                    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                                    Date entryDate = new Date();

                                    long time = entryDate.getTime();
                                    ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
                                    buffer.putLong(time);
                                    byte[] timeB = buffer.array();

                                    String entryDateString = dateFormat.format(time);
                                    SendMessageData.message.getSignature().setTimestamp(entryDateString);
                                    SendMessageData.message.getSignature().setTimestampBytes(timeB);

                                    try {
                                        formatTextArea();
                                    } catch (UnsupportedEncodingException ex) {
                                        Logger.getLogger(SignMessageController.class.getName()).log(Level.SEVERE, null, ex);
                                    }

                                    nextButton.disableProperty().set(false);

                                    if((SendMessageData.selectedMode == 100) || (SendMessageData.selectedMode == 101) ||
                                       (SendMessageData.selectedMode == 110) || (SendMessageData.selectedMode == 111))
                                    {

                                        if(SignSchemeController.ssButton1 != null)
                                        {
                                            SignSchemeController.ss3.setTextFill(Color.RED);
                                            SignSchemeController.ss4.setTextFill(Color.RED);
                                            SignSchemeController.ss5.setTextFill(Color.RED);
                                            SignSchemeController.ss6.setTextFill(Color.RED);
                                            SignSchemeController.ss7.setTextFill(Color.RED);
                                            SignSchemeController.ss8.setTextFill(Color.RED);
                                        }

                                        SchemeData.sLabel[3] = true;
                                        SchemeData.sLabel[4] = true;
                                        SchemeData.sLabel[5] = true;
                                        SchemeData.sLabel[6] = true;
                                        SchemeData.sLabel[7] = true;
                                        SchemeData.sLabel[8] = true;

                                        if(SignSchemeController.ssButton1 != null)
                                        {
                                            String style = "-fx-border-color: red; -fx-border-width: 3 3 3 3;";
                                            SignSchemeController.ssButton2.setStyle(style);
                                            style = "-fx-border-width: 0;";
                                            SignSchemeController.ssButton1.setStyle(style);
                                        }

                                        SchemeData.sButton[1] = false;
                                        SchemeData.sButton[2] = true;
                                    }

                                    if((SendMessageData.selectedMode == 300) || (SendMessageData.selectedMode == 301) ||
                                       (SendMessageData.selectedMode == 310) || (SendMessageData.selectedMode == 311))
                                    {

                                        if(SignAndEncryptSchemeController.bbButton1 != null)
                                        {
                                            SignAndEncryptSchemeController.bb3.setTextFill(Color.RED);
                                            SignAndEncryptSchemeController.bb4.setTextFill(Color.RED);
                                            SignAndEncryptSchemeController.bb5.setTextFill(Color.RED);
                                            SignAndEncryptSchemeController.bb6.setTextFill(Color.RED);
                                            SignAndEncryptSchemeController.bb7.setTextFill(Color.RED);
                                            SignAndEncryptSchemeController.bb8.setTextFill(Color.RED);
                                        }

                                        SchemeData.bLabel[3] = true;
                                        SchemeData.bLabel[4] = true;
                                        SchemeData.bLabel[5] = true;
                                        SchemeData.bLabel[6] = true;
                                        SchemeData.bLabel[7] = true;
                                        SchemeData.bLabel[8] = true;

                                        if(SignAndEncryptSchemeController.bbButton1 != null)
                                        {
                                            String style = "-fx-border-color: red; -fx-border-width: 3 3 3 3;";
                                            SignAndEncryptSchemeController.bbButton2.setStyle(style);
                                            style = "-fx-border-width: 0;";
                                            SignAndEncryptSchemeController.bbButton1.setStyle(style);
                                        }

                                        SchemeData.bButton[1] = false;
                                        SchemeData.bButton[2] = true;
                                    }

                                    signTextArea.positionCaret(0);
                                    
                                    window.close();
                                });
       
        VBox layout = new VBox(10);
        //Label spacing = new Label("   ");
        layout.getChildren().addAll(label, privateKeyIdChoice, password, closeButton);
        layout.setAlignment(Pos.CENTER);
        
        Scene scene = new Scene(layout, 560, 240);
        scene.getStylesheets().add("css/popup.css");
        window.setScene(scene);
        window.showAndWait();
        
        
    }
    
    public void formatTextArea() throws UnsupportedEncodingException
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
        signTextArea.setText("---------- BEGIN PGP MESSAGE ----------");
        signTextArea.appendText(newLine);
        signTextArea.appendText(newLine);
        signTextArea.appendText("------- BEGIN SIGNATURE -------");
        signTextArea.appendText(newLine);
        signTextArea.appendText(newLine);
        signTextArea.appendText("--- Timestamp ---");
        signTextArea.appendText(newLine);
        signTextArea.appendText(SendMessageData.message.getSignature().getTimestamp());
        signTextArea.appendText(newLine);
        signTextArea.appendText("--- Public Key Algorithm ---");
        signTextArea.appendText(newLine);
        signTextArea.appendText(SendMessageData.signatureAlgorithm);
        signTextArea.appendText(newLine);
        signTextArea.appendText("--- Hash Algorithm ---");
        signTextArea.appendText(newLine);
        signTextArea.appendText(SendMessageData.hashAlgorithm);
        signTextArea.appendText(newLine);
        signTextArea.appendText("--- Key Id of Sender's Public Key ---");
        signTextArea.appendText(newLine);
        signTextArea.appendText(SendMessageData.keyId);
        signTextArea.appendText(newLine);
        signTextArea.appendText("--- Leading Two Octets of Message Digest ---");
        signTextArea.appendText(newLine);
        signTextArea.appendText(SendMessageData.message.getSignature().getLeading2octets());
        signTextArea.appendText(newLine);
        signTextArea.appendText("--- Message Digest ---");
        signTextArea.appendText(newLine);
        signTextArea.appendText(encryptedSignature);
        signTextArea.appendText(newLine);
        signTextArea.appendText(newLine);
        signTextArea.appendText("------- END SIGNATURE -------");
        signTextArea.appendText(newLine);
        signTextArea.appendText(newLine);
        signTextArea.appendText("------- BEGIN MESSAGE -------");
        signTextArea.appendText(newLine);
        signTextArea.appendText(newLine);
        signTextArea.appendText("---- Filename ----");
        signTextArea.appendText(newLine);
        signTextArea.appendText(SendMessageData.message.getFilename());
        signTextArea.appendText(newLine);
        signTextArea.appendText("---- Timestamp ----");
        signTextArea.appendText(newLine);
        signTextArea.appendText(SendMessageData.message.getTimestamp());
        signTextArea.appendText(newLine);
        signTextArea.appendText("---- Data ----");
        signTextArea.appendText(newLine);
        signTextArea.appendText(SendMessageData.message.getData());
        signTextArea.appendText(newLine);
        signTextArea.appendText(newLine);
        signTextArea.appendText("------- END MESSAGE -------");
        signTextArea.appendText(newLine);
        signTextArea.appendText(newLine);
        signTextArea.appendText("---------- END PGP MESSAGE ----------");
        
    }
   
    public void nextScene() throws IOException
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
        
        if(SendMessageData.selectedMode == 100)
        {
            if((MyData.maxBlockSize == 0) || (sum < MyData.maxBlockSize))
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Send Message");
                alert.setHeaderText(null);
                String message = "Message has been sent to " + SendMessageData.receiver.getName() + " " + SendMessageData.receiver.getSurname() + ".";
                alert.setContentText(message);
                alert.showAndWait();
            }
        }
        
        String nextSceneResource = "";
        
        if(SendMessageData.selectedMode == 100) 
        { 
            if((MyData.maxBlockSize == 0) || (sum < MyData.maxBlockSize))
            {
                nextSceneResource = "VerifyMessage.fxml";
            }
            else
            { 
                nextSceneResource = "SegmentMessage.fxml";
            }
        }
        
        if(SendMessageData.selectedMode == 101)
        {
            nextSceneResource = "ConvertMessage.fxml";
        }
        
        if((SendMessageData.selectedMode == 110) || (SendMessageData.selectedMode == 111) ||
           (SendMessageData.selectedMode == 310) || (SendMessageData.selectedMode == 311))
        {
            nextSceneResource = "CompressMessage.fxml";
        }
        
        if((SendMessageData.selectedMode == 300) || (SendMessageData.selectedMode == 301))
        {
            nextSceneResource = "EncryptMessage.fxml";
        }
        
        AnchorPane nextScene = (AnchorPane) FXMLLoader.load(getClass().getResource(nextSceneResource));
        MainController.mainContentPane.getChildren().clear();
        MainController.mainContentPane.getChildren().add(nextScene);
        nextScene.toFront();
        
        if(SendMessageData.selectedMode == 100)
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
        
        if(SendMessageData.selectedMode == 100)
        {
            if(SignSchemeController.ssButton1 != null)
            {
                SignSchemeController.ss9.setTextFill(Color.RED);
                SignSchemeController.ss13.setTextFill(Color.RED);
                SignSchemeController.ss14.setTextFill(Color.RED);
                SignSchemeController.ss19.setTextFill(Color.RED);
                SignSchemeController.ss20.setTextFill(Color.RED);
                SignSchemeController.ss21.setTextFill(Color.RED);
            }
            
            SchemeData.sLabel[9] = true;
            SchemeData.sLabel[13] = true;
            SchemeData.sLabel[14] = true;
            SchemeData.sLabel[19] = true;
            SchemeData.sLabel[20] = true;
            SchemeData.sLabel[21] = true;
        }
        
        if(SendMessageData.selectedMode == 101)
        {
            if(SignSchemeController.ssButton1 != null)
            {
                SignSchemeController.ss9.setTextFill(Color.RED);
                SignSchemeController.ss13.setTextFill(Color.RED);
                SignSchemeController.ss14.setTextFill(Color.RED);
                SignSchemeController.ss15.setTextFill(Color.RED);
                SignSchemeController.ss16.setTextFill(Color.RED);
            }
            
            SchemeData.sLabel[9] = true;
            SchemeData.sLabel[13] = true;
            SchemeData.sLabel[14] = true;
            SchemeData.sLabel[15] = true;
            SchemeData.sLabel[16] = true;
        }
        
        if((SendMessageData.selectedMode == 110) || (SendMessageData.selectedMode == 111))
        {
            if(SignSchemeController.ssButton1 != null)
            {
                SignSchemeController.ss9.setTextFill(Color.RED);
                SignSchemeController.ss10.setTextFill(Color.RED);
            }
            
            SchemeData.sLabel[9] = true;
            SchemeData.sLabel[10] = true;
        }
        
        if((SendMessageData.selectedMode == 310) || (SendMessageData.selectedMode == 311))
        {
            if(SignAndEncryptSchemeController.bbButton1 != null)
            {
                SignAndEncryptSchemeController.bb9.setTextFill(Color.RED);
                SignAndEncryptSchemeController.bb10.setTextFill(Color.RED);
            }
            
            SchemeData.bLabel[9] = true;
            SchemeData.bLabel[10] = true;
        }
        
        if((SendMessageData.selectedMode == 300) || (SendMessageData.selectedMode == 301))
        {
            if(SignAndEncryptSchemeController.bbButton1 != null)
            {
                SignAndEncryptSchemeController.bb9.setTextFill(Color.RED);
                SignAndEncryptSchemeController.bb13.setTextFill(Color.RED);
                SignAndEncryptSchemeController.bb14.setTextFill(Color.RED);
                SignAndEncryptSchemeController.bb15.setTextFill(Color.RED);
                SignAndEncryptSchemeController.bb16.setTextFill(Color.RED);
            }
            
            SchemeData.bLabel[9] = true;
            SchemeData.bLabel[13] = true;
            SchemeData.bLabel[14] = true;
            SchemeData.bLabel[15] = true;
            SchemeData.bLabel[16] = true;
        }
        
    }
    
}
