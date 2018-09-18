package threepalmtrees.pgp;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.controlsfx.control.CheckComboBox;
import util.KeySignature;
import util.MyData;
import util.PrivateKeyEntry;
import util.SchemeData;
import util.User;
import util.SendMessageData;

/**
 * FXML Controller class
 *
 * @author Mirko
 */
public class MainController implements Initializable {

    @FXML private Button signOutButton;
    @FXML private Button sendMessageButton;
    @FXML private Button showSchemeButton;
    @FXML private Button myKeysButton;
    @FXML private Button keyRingButton;
    @FXML private Button manageKeysButton;
    @FXML private Button passwordsButton;
    @FXML private Button blockSizeButton;
    @FXML private Button aboutButton;
    
    
    @FXML private Label currentUserLabel;
    @FXML private Label statusLabel;
    @FXML private Circle avatarCircle;
    
    @FXML private AnchorPane contentPane;
    
    public static AnchorPane mainContentPane;
    public static Label mainCurrentUserLabel;
    public static Label mainStatusLabel;
    public static Button mainSendMessageButton;
    public static Button mainManageKeysButton;
    public static Button mainBlockSizeButton;
    public static Button mainAboutButton;
    public static Circle mainAvatarCircle;
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        currentUserLabel.setText(MyData.currentUser.getName() + " " + MyData.currentUser.getSurname());
        statusLabel.setText("This is main window");
        
        String imageUrl = "";
        if(MyData.currentUser.getName().equals("Julius")) { imageUrl = "images/Julius.jpg"; }
        if(MyData.currentUser.getName().equals("Alan")) { imageUrl = "images/Alan.jpg"; }
        if(MyData.currentUser.getName().equals("Phil")) { imageUrl = "images/Phil.jpg"; }
        if(MyData.currentUser.getName().equals("Don")) { imageUrl = "images/Don.jpg"; }
        if(MyData.currentUser.getName().equals("Clifford")) { imageUrl = "images/Clifford.jpg"; }
        
        Image image = new Image(imageUrl);
        ImagePattern imagePattern = new ImagePattern(image);
        avatarCircle.setFill(imagePattern);
          
        mainContentPane = contentPane;
        mainCurrentUserLabel = currentUserLabel;
        mainStatusLabel = statusLabel;
        mainAvatarCircle = avatarCircle;
        
        mainSendMessageButton = sendMessageButton;
        mainManageKeysButton = manageKeysButton;
        mainBlockSizeButton = blockSizeButton;
        mainAboutButton = aboutButton;        
        
        Tooltip myKeysTooltip = new Tooltip();
        myKeysTooltip.setText("View your PrivateKeyRing" + "\n" + "(keys you own)");
        myKeysButton.setTooltip(myKeysTooltip);
        
        Tooltip keyRingTooltip = new Tooltip();
        keyRingTooltip.setText("View your PublicKeyRing" + "\n" + "(public keys of other users)");
        keyRingButton.setTooltip(keyRingTooltip);
    }

    
    public void signOut(ActionEvent event) throws IOException
    {
        MyData.currentUser = null;
        System.out.println("Successful Sign Out!!!");
        Parent mainParent = FXMLLoader.load(getClass().getResource("Welcome.fxml"));
        Scene mainScene = new Scene(mainParent);
        Stage mainWindow = (Stage)((Node)event.getSource()).getScene().getWindow();
        mainWindow.setScene(mainScene);
        mainWindow.show();
        
        SendMessageData.selectedMode = 0;
        
        SchemeData.clearS();
        SchemeData.clearE();
    }
    
    
    public void sendMessage(ActionEvent event) throws IOException
    {
        
        AnchorPane nextvalscene = (AnchorPane) FXMLLoader.load(getClass().getResource("SendMessage.fxml"));
        contentPane.getChildren().clear();
        contentPane.getChildren().add(nextvalscene);
        nextvalscene.toFront();
        
        mainContentPane = contentPane;
        mainCurrentUserLabel = currentUserLabel;
        mainStatusLabel = statusLabel;
        
        sendMessageButton.disableProperty().set(true);
        manageKeysButton.disableProperty().set(true);
        blockSizeButton.disableProperty().set(true);
        aboutButton.disableProperty().set(true);
    }
    
    
    public void showScheme() throws IOException
    {
        String url = "";
        
        if((SendMessageData.selectedMode == 100) || (SendMessageData.selectedMode == 101) ||
           (SendMessageData.selectedMode == 110) || (SendMessageData.selectedMode == 111))
        { url = "/scheme/SignScheme.fxml"; }
        
        if((SendMessageData.selectedMode == 200) || (SendMessageData.selectedMode == 201) ||
           (SendMessageData.selectedMode == 210) || (SendMessageData.selectedMode == 211))
        { url = "/scheme/EncryptScheme.fxml"; }
        
        if((SendMessageData.selectedMode == 0) || (SendMessageData.selectedMode == 300) || (SendMessageData.selectedMode == 301) ||
           (SendMessageData.selectedMode == 310) || (SendMessageData.selectedMode == 311))
        { url = "/scheme/SignAndEncryptScheme.fxml"; }
        
        Parent parent = FXMLLoader.load(getClass().getResource(url));
        Scene scene = new Scene(parent);
        //Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        Stage window = new Stage();
        window.setScene(scene);
        window.setTitle("PGP Protocol Scheme");
        window.show();
    }
    
    
    public void showMyKeys(ActionEvent event) throws IOException
    {
        Parent parent = FXMLLoader.load(getClass().getResource("MyKeys.fxml"));
        Scene scene = new Scene(parent);
        //Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        Stage window = new Stage();
        window.setScene(scene);
        window.setTitle("Private KeyRing");
        window.show();
    }
    
    
    public void showKeyRing(ActionEvent event) throws IOException
    {
        Parent parent = FXMLLoader.load(getClass().getResource("KeyRing.fxml"));
        Scene scene = new Scene(parent);
        //Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        Stage window = new Stage();
        window.setScene(scene);
        window.setTitle("Public KeyRing");
        window.show();
    }
    
    
    public void manageKeys()
    {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Key Management");
        
        Button sendKeyButton = new Button("Send Key");
        sendKeyButton.setMinWidth(200);
        sendKeyButton.setOnAction(e ->
                                    {
                                        sendKey();
                                    });
        
        Button revokeKeyButton = new Button("Revoke Key");
        revokeKeyButton.setMinWidth(200);
        revokeKeyButton.setOnAction(e ->
                                    {
                                        revokeKey();
                                    });
        
        Button viewRevokeButton = new Button("View Revoked Keys");
        viewRevokeButton.setMinWidth(200);
        viewRevokeButton.setOnAction(e ->
                                    {
                                        Parent parent = null;
                                        try {
                                                parent = FXMLLoader.load(getClass().getResource("RevokedKeys.fxml"));
                                            } catch (IOException ex) { Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex); }
                                        Scene scene1 = new Scene(parent);
                                        //Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                                        Stage window1 = new Stage();
                                        window1.setScene(scene1);
                                        window1.show();
                                    });
        
        
        Button signKeyButton = new Button("Sign Key");
        signKeyButton.setMinWidth(200);
        signKeyButton.setOnAction(e ->
                                    {
                                        signKey();
                                    });
        
        Button closeButton = new Button("OK");
        closeButton.setOnAction(e -> window.close());
        
        VBox layout = new VBox(10);
        layout.getChildren().addAll(sendKeyButton, signKeyButton, revokeKeyButton, viewRevokeButton, closeButton);
        layout.setPadding(new Insets(10, 10, 10, 10));
        layout.setAlignment(Pos.CENTER);
        
        Scene scene = new Scene(layout, 400, 230);
        scene.getStylesheets().add("css/popup.css");
        window.setScene(scene);
        window.showAndWait();
    }
    
    
    public void sendKey()
    {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Send Key");
        
        VBox layout = new VBox(10);
        
        HBox firstRow = new HBox(10);
        HBox secondRow = new HBox(10);
        
        Label keyLabel = new Label("Select Key:");
        keyLabel.setPadding(new Insets(2, 5, 2, 5));
        keyLabel.setMinWidth(170);
        Label recipientLabel = new Label("Select Recipients:");
        recipientLabel.setPadding(new Insets(2, 5, 2, 5));
        recipientLabel.setMinWidth(170);
        Label status = new Label("");
        status.setPadding(new Insets(5,5,5,5));
        status.setStyle("-fx-background-color: linear-gradient(#ff5400, #be1d00);");
        status.setVisible(false);
        
        ChoiceBox<String> keysChoiceBox = new ChoiceBox<>();
        keysChoiceBox.setMinWidth(200);
        ChoiceBox<String> recipientChoiceBox = new ChoiceBox<>();
        recipientChoiceBox.setMinWidth(200);
        
        for(int i=0; i<MyData.currentUser.getPrivateKeyRing().size(); i++ )
        {
            if(MyData.currentUser.getPrivateKeyRing().get(i).isActive())
            {
               keysChoiceBox.getItems().add(MyData.currentUser.getPrivateKeyRing().get(i).getKeyId()); 
            }    
        }
        
        if(!MyData.user1.getEmail().equals(MyData.currentUser.getEmail())) 
        { recipientChoiceBox.getItems().add(MyData.user1.getEmail()); }
        if(!MyData.user2.getEmail().equals(MyData.currentUser.getEmail())) 
        { recipientChoiceBox.getItems().add(MyData.user2.getEmail()); }
        if(!MyData.user3.getEmail().equals(MyData.currentUser.getEmail())) 
        { recipientChoiceBox.getItems().add(MyData.user3.getEmail()); }
        if(!MyData.user4.getEmail().equals(MyData.currentUser.getEmail())) 
        { recipientChoiceBox.getItems().add(MyData.user4.getEmail()); }
        if(!MyData.user5.getEmail().equals(MyData.currentUser.getEmail())) 
        { recipientChoiceBox.getItems().add(MyData.user5.getEmail()); }
        
        firstRow.getChildren().addAll(keyLabel, keysChoiceBox);
        firstRow.setPadding(new Insets(10, 10, 10, 10));
        secondRow.getChildren().addAll(recipientLabel, recipientChoiceBox);
        secondRow.setPadding(new Insets(10, 10, 10, 10));
        
        Button sendKeyButton = new Button("Send Key");
        sendKeyButton.setStyle("-fx-background-color: linear-gradient(#ff5400, #be1d00);");
        sendKeyButton.setMinWidth(120);
        
        sendKeyButton.setOnAction(e -> 
                                    {
                                        if((keysChoiceBox.getSelectionModel().isEmpty()) || (recipientChoiceBox.getSelectionModel().isEmpty()))
                                        {
                                            Alert alertBox = new Alert(Alert.AlertType.WARNING);
                                            alertBox.setTitle("Send Key Warning");
                                            alertBox.setHeaderText(null);
                                            if(keysChoiceBox.getSelectionModel().isEmpty()) { alertBox.setContentText("Please select key!"); }
                                            if(recipientChoiceBox.getSelectionModel().isEmpty()) { alertBox.setContentText("Please select recipient!"); }
                                            alertBox.showAndWait();
        
                                            return;
                                        }
                                         
                                        String recipientEmail = recipientChoiceBox.getValue();
                                        String id = keysChoiceBox.getValue();
                                        User recipient = null;
                                        
                                        if(recipientEmail.equals(MyData.user1.getEmail())) { recipient = MyData.user1; }
                                        if(recipientEmail.equals(MyData.user2.getEmail())) { recipient = MyData.user2; }
                                        if(recipientEmail.equals(MyData.user3.getEmail())) { recipient = MyData.user3; }
                                        if(recipientEmail.equals(MyData.user4.getEmail())) { recipient = MyData.user4; }
                                        if(recipientEmail.equals(MyData.user5.getEmail())) { recipient = MyData.user5; }
                                        
                                        if(recipient.getPublicKeyById(id) == null)
                                        {
                                            PrivateKeyEntry key = MyData.currentUser.searchForKeyById(id);
                                            recipient.addKeyToPublicKeyRing(key, new Date().getTime(), MyData.currentUser.getEmail(), key.getKeyname(), key.getType());
                                            status.setVisible(true);
                                            status.setText(recipient.getName() + " " + recipient.getSurname() + " received key: " + key.getKeyId());
                                        }
                                        else
                                        {
                                            status.setVisible(true);
                                            status.setText(recipient.getName() + " " + recipient.getSurname() + " already has this key.");
                                        }
                                    });
        
        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> window.close());
        closeButton.setMinWidth(120);
        
        layout.getChildren().addAll(firstRow, secondRow, status, sendKeyButton, closeButton);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(10, 10, 10, 10));
        
        Scene scene = new Scene(layout, 420, 300);
        scene.getStylesheets().add("css/popup.css");
        window.setScene(scene);
        window.showAndWait();
    }
    
    
    public void revokeKey()
    {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Revoke Key");
        
        VBox layout = new VBox(10);
        
        HBox firstRow = new HBox(10);
        HBox secondRow = new HBox(10);
        HBox thirdRow = new HBox(10);
        
        Label keyLabel = new Label("Select Key:");
        keyLabel.setPadding(new Insets(2, 5, 2, 5));
        keyLabel.setMinWidth(200);
        Label recipientLabel = new Label("Select Recipient:");
        recipientLabel.setPadding(new Insets(2, 5, 2, 5));
        recipientLabel.setMinWidth(200);
        Label reasonLabel = new Label("Reason for Revocation:");
        reasonLabel.setPadding(new Insets(2, 5, 2, 5));
        reasonLabel.setMinWidth(200);
        
        ChoiceBox<String> keysChoiceBox = new ChoiceBox<>();
        keysChoiceBox.setMinWidth(260);
        ChoiceBox<String> recipientChoiceBox = new ChoiceBox<>();
        recipientChoiceBox.setMinWidth(260);
        ChoiceBox<String> reasonChoiceBox = new ChoiceBox<>();
        reasonChoiceBox.setMinWidth(260);
        
        for(int i=0; i<MyData.currentUser.getPrivateKeyRing().size(); i++ )
        {
            if(MyData.currentUser.getPrivateKeyRing().get(i).isActive())
            {
               keysChoiceBox.getItems().add(MyData.currentUser.getPrivateKeyRing().get(i).getKeyId()); 
            }    
        }
        
        reasonChoiceBox.getItems().addAll("NO REASON SPECIFIED", "KEY IS SUPERSEDED", "KEY MATERIAL HAS BEEN COMPROMISED", "KEY IS RETIRED AND NO LONGER USED");
        reasonChoiceBox.setValue("NO REASON SPECIFIED");
        
        if(!MyData.user1.getEmail().equals(MyData.currentUser.getEmail())) 
        { recipientChoiceBox.getItems().add(MyData.user1.getEmail()); }
        if(!MyData.user2.getEmail().equals(MyData.currentUser.getEmail())) 
        { recipientChoiceBox.getItems().add(MyData.user2.getEmail()); }
        if(!MyData.user3.getEmail().equals(MyData.currentUser.getEmail())) 
        { recipientChoiceBox.getItems().add(MyData.user3.getEmail()); }
        if(!MyData.user4.getEmail().equals(MyData.currentUser.getEmail())) 
        { recipientChoiceBox.getItems().add(MyData.user4.getEmail()); }
        if(!MyData.user5.getEmail().equals(MyData.currentUser.getEmail())) 
        { recipientChoiceBox.getItems().add(MyData.user5.getEmail()); }
        
        firstRow.getChildren().addAll(keyLabel, keysChoiceBox);
        firstRow.setPadding(new Insets(10, 10, 10, 10));
        secondRow.getChildren().addAll(recipientLabel, recipientChoiceBox);
        secondRow.setPadding(new Insets(10, 10, 10, 10));
        thirdRow.getChildren().addAll(reasonLabel, reasonChoiceBox);
        thirdRow.setPadding(new Insets(10, 10, 10, 10));
        
        Label status = new Label();
        status.setPadding(new Insets(5,5,5,5));
        status.setStyle("-fx-background-color: linear-gradient(#ff5400, #be1d00);");
        status.setVisible(false);
        
        Button revokeKeyButton = new Button("Revoke Key");
        revokeKeyButton.setStyle("-fx-background-color: linear-gradient(#ff5400, #be1d00);");
        revokeKeyButton.setMinWidth(120);
        
        revokeKeyButton.setOnAction(e -> 
                                    {
                                        if((keysChoiceBox.getSelectionModel().isEmpty()) || (recipientChoiceBox.getSelectionModel().isEmpty()))
                                        {
                                            Alert alertBox = new Alert(Alert.AlertType.WARNING);
                                            alertBox.setTitle("Send Key Warning");
                                            alertBox.setHeaderText(null);
                                            if(keysChoiceBox.getSelectionModel().isEmpty()) { alertBox.setContentText("Please select key!"); }
                                            if(recipientChoiceBox.getSelectionModel().isEmpty()) { alertBox.setContentText("Please select recipient!"); }
                                            alertBox.showAndWait();
        
                                            return;
                                        }
                                         
                                        String recipientEmail = recipientChoiceBox.getValue();
                                        String id = keysChoiceBox.getValue();
                                        User recipient = null;
                                        PrivateKeyEntry key = MyData.currentUser.searchForKeyById(id);
                                        
                                        key.setActive(false);
                                        key.setReasonForRevocation(reasonChoiceBox.getValue());
                                        
                                        if(recipientEmail.equals(MyData.user1.getEmail())) { recipient = MyData.user1; }
                                        if(recipientEmail.equals(MyData.user2.getEmail())) { recipient = MyData.user2; }
                                        if(recipientEmail.equals(MyData.user3.getEmail())) { recipient = MyData.user3; }
                                        if(recipientEmail.equals(MyData.user4.getEmail())) { recipient = MyData.user4; }
                                        if(recipientEmail.equals(MyData.user5.getEmail())) { recipient = MyData.user5; }
                                        
                                        if(recipient.getPublicKeyById(id) == null)
                                        { 
                                            status.setVisible(true);
                                            status.setText(recipient.getName() + " " + recipient.getSurname() + " does not have this key."); 
                                        }
                                        else
                                        {
                                            if(recipient.getPublicKeyById(id).isActive())
                                            {
                                                recipient.getPublicKeyById(id).setActive(false);
                                                recipient.getPublicKeyById(id).setReasonForRevocation(reasonChoiceBox.getValue());
                                                status.setVisible(true);
                                                status.setText(recipient.getName() + " " + recipient.getSurname() + " revoked this key.");
                                            }
                                            else
                                            { 
                                                status.setText(recipient.getName() + " " + recipient.getSurname() + " already notified."); 
                                            }
                                        } 
                                    });
        
        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> window.close());
        closeButton.setMinWidth(120);
        
        layout.getChildren().addAll(firstRow, secondRow, thirdRow, status, revokeKeyButton, closeButton);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(10, 10, 10, 10));
        
        Scene scene = new Scene(layout, 520, 320);
        scene.getStylesheets().add("css/popup.css");
        window.setScene(scene);
        window.showAndWait();
    }
    
    
    public void signKey()
    {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Sign Key");
        
        VBox layout = new VBox(10);
        
        HBox firstRow = new HBox(10);
        HBox secondRow = new HBox(10);
        HBox thirdRow = new HBox(10);
        
        Label keyLabel1 = new Label("Select key to sign:");
        keyLabel1.setPadding(new Insets(2, 5, 2, 5));
        keyLabel1.setMinWidth(200);
        
        Label keyLabel2 = new Label("Select key to sign with it:");
        keyLabel2.setPadding(new Insets(2, 5, 2, 5));
        keyLabel2.setMinWidth(200);
        
        Label recipientLabel = new Label("Select Recipient:");
        recipientLabel.setPadding(new Insets(2, 5, 2, 5));
        recipientLabel.setMinWidth(200);
        
        ChoiceBox<String> keysChoiceBox1 = new ChoiceBox<>();
        keysChoiceBox1.setMinWidth(200);
        
        for(int i = 0; i<MyData.currentUser.getPublicKeyRing().size(); i++ )
        {
            if(MyData.currentUser.getPublicKeyRing().get(i).isActive())
            {
                keysChoiceBox1.getItems().add(MyData.currentUser.getPublicKeyRing().get(i).getKeyId());
            }
        }
        
        ChoiceBox<String> recipientChoiceBox = new ChoiceBox<>();
        recipientChoiceBox.setMinWidth(200);
        
        ChoiceBox<String> keysChoiceBox2 = new ChoiceBox<>();
        keysChoiceBox2.setMinWidth(200);
        
        for(int i=0; i<MyData.currentUser.getPrivateKeyRing().size(); i++ )
        {
            if(MyData.currentUser.getPrivateKeyRing().get(i).isActive())
            {
               keysChoiceBox2.getItems().add(MyData.currentUser.getPrivateKeyRing().get(i).getKeyId()); 
            }    
        }
        
        recipientChoiceBox.getItems().add(MyData.user1.getEmail());
        recipientChoiceBox.getItems().add(MyData.user2.getEmail());
        recipientChoiceBox.getItems().add(MyData.user3.getEmail());
        recipientChoiceBox.getItems().add(MyData.user4.getEmail());
        recipientChoiceBox.getItems().add(MyData.user5.getEmail());
        
        Button signKeyButton = new Button("Sign Key");
        signKeyButton.setStyle("-fx-background-color: linear-gradient(#ff5400, #be1d00);");
        signKeyButton.setMinWidth(120);
        
        Label status = new Label();
        status.setPadding(new Insets(5,5,5,5));
        status.setStyle("-fx-background-color: linear-gradient(#ff5400, #be1d00);");
        status.setVisible(false);
        
        signKeyButton.setOnAction(e -> 
                                    {
                                        if((keysChoiceBox1.getSelectionModel().isEmpty()) || (recipientChoiceBox.getSelectionModel().isEmpty()) ||
                                           (keysChoiceBox2.getSelectionModel().isEmpty()))
                                        {
                                            Alert alertBox = new Alert(Alert.AlertType.WARNING);
                                            alertBox.setTitle("Send Key Warning");
                                            alertBox.setHeaderText(null);
                                            
                                            String warning = "";
                                            boolean warning1 = false;
                                            boolean warning2 = false;
                                            if(keysChoiceBox1.getSelectionModel().isEmpty()) 
                                            { 
                                                warning += "Please select key to sign!"; 
                                                warning1 = true; 
                                            }
                                            if(keysChoiceBox2.getSelectionModel().isEmpty()) 
                                            { 
                                                if(warning1) { warning += "\n" + "Please select key to sign with it!"; }
                                                else { warning += "Please select key to sign with it!"; }
                                                warning2 = true;
                                            }
                                            if(recipientChoiceBox.getSelectionModel().isEmpty())
                                            {
                                                if(warning2) { warning += "\n" + "Please select the recipient!"; }
                                                else if(warning1) { warning += "\n" + "Please select the recipient!"; }
                                                else { warning += "Please select the recipient!"; }
                                            }
                                            
                                            alertBox.setContentText(warning);
                                            alertBox.showAndWait();
                                            return;
                                        }
                                         
                                        String recipientEmail = recipientChoiceBox.getValue();
                                        String signerEmail = MyData.currentUser.getEmail();
                                        String keyToSign = keysChoiceBox1.getValue();
                                        String keyToSignWith = keysChoiceBox2.getValue();
                     
                                        User recipient = null;
                                        
                                        if(recipientEmail.equals(MyData.user1.getEmail())) { recipient = MyData.user1; }
                                        if(recipientEmail.equals(MyData.user2.getEmail())) { recipient = MyData.user2; }
                                        if(recipientEmail.equals(MyData.user3.getEmail())) { recipient = MyData.user3; }
                                        if(recipientEmail.equals(MyData.user4.getEmail())) { recipient = MyData.user4; }
                                        if(recipientEmail.equals(MyData.user5.getEmail())) { recipient = MyData.user5; }
                                        
                                        
                                        
                                        if(recipient.getPublicKeyById(keyToSign) == null)
                                        { 
                                            status.setVisible(true);
                                            status.setText(recipient.getName() + " " + recipient.getSurname() + " does not have this key."); 
                                        }
                                        else
                                        {
                                            if(recipient.getPublicKeyById(keyToSign).isActive())
                                            {
                                                KeySignature newSignature = new KeySignature(signerEmail, keyToSignWith);
                                                
                                                if(!recipient.getPublicKeyById(keyToSign).signatureAlreadyThere(newSignature))
                                                {
                                                    recipient.getPublicKeyById(keyToSign).getSignatures().add(newSignature);
                                                    status.setVisible(true);
                                                    status.setText(recipient.getName() + " " + recipient.getSurname() + " received new key signature.");
                                                    
                                                    recipient.setSignatureTrusts();
                                                    recipient.setKeyLegitimacy();
                                                }
                                                else
                                                {
                                                    status.setVisible(true);
                                                    status.setText(recipient.getName() + " " + recipient.getSurname() + " already has this signature.");
                                                }
                                            }
                                            else
                                            { 
                                                status.setVisible(true);
                                                status.setText(recipient.getName() + " " + recipient.getSurname() + " does not have this key anymore."); 
                                            }
                                        } 
                                    });
        
        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> window.close());
        closeButton.setMinWidth(120);
        
        firstRow.getChildren().addAll(keyLabel1, keysChoiceBox1);
        firstRow.setPadding(new Insets(10, 10, 10, 10));
        secondRow.getChildren().addAll(keyLabel2, keysChoiceBox2);
        secondRow.setPadding(new Insets(10, 10, 10, 10));
        thirdRow.getChildren().addAll(recipientLabel, recipientChoiceBox);
        thirdRow.setPadding(new Insets(10, 10, 10, 10));
        
        layout.getChildren().addAll(firstRow, secondRow, thirdRow, status, signKeyButton, closeButton);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(10, 10, 10, 10));
        
        Scene scene = new Scene(layout, 460, 380);
        scene.getStylesheets().add("css/popup.css");
        window.setScene(scene);
        window.showAndWait();
        
    }
    
    
    public void showPasswords(ActionEvent event) throws IOException
    {
        Parent parent = FXMLLoader.load(getClass().getResource("Passwords.fxml"));
        Scene scene = new Scene(parent);
        //Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        Stage window = new Stage();
        window.setScene(scene);
        window.setTitle("My Passwords");
        window.show();
    }
    
    
    public void setBlockSize(ActionEvent event)
    {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Set Maximum Block Size");
        
        VBox layout = new VBox(10);
        
        HBox firstRow = new HBox(10);
        
        Label keyLabel = new Label("Select Size:");
        keyLabel.setPadding(new Insets(2, 5, 2, 5));
        keyLabel.setMinWidth(100);
        
        ChoiceBox<String> sizeChoiceBox = new ChoiceBox<>();
        sizeChoiceBox.setMinWidth(120);
        ChoiceBox<String> recipientChoiceBox = new ChoiceBox<>();
        recipientChoiceBox.setMinWidth(170);
        sizeChoiceBox.getItems().addAll("Unlimited", "1000B", "2000B", "3000B");
        
        if(MyData.maxBlockSize == 0) { sizeChoiceBox.setValue("Unlimited"); }
        if(MyData.maxBlockSize == 1000) { sizeChoiceBox.setValue("1000B"); }
        if(MyData.maxBlockSize == 2000) { sizeChoiceBox.setValue("2000B"); }
        if(MyData.maxBlockSize == 3000) { sizeChoiceBox.setValue("3000B"); }
       
        firstRow.getChildren().addAll(keyLabel, sizeChoiceBox);
        firstRow.setPadding(new Insets(10, 10, 10, 10));;
        
        Button closeButton = new Button("Ok");
        closeButton.setOnAction(e -> 
                                    {
                                        if(sizeChoiceBox.getValue().equals("Unlimited")) { MyData.maxBlockSize = 0; }
                                        if(sizeChoiceBox.getValue().equals("1000B")) { MyData.maxBlockSize = 1000; }
                                        if(sizeChoiceBox.getValue().equals("2000B")) { MyData.maxBlockSize = 2000; }
                                        if(sizeChoiceBox.getValue().equals("3000B")) { MyData.maxBlockSize = 3000; }
                                        
                                        System.out.println("Max Block Size set to " + MyData.maxBlockSize);
                                        
                                        window.close();
                                    });
        
        layout.getChildren().addAll(firstRow, closeButton);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(10, 10, 10, 10));
        
        Scene scene = new Scene(layout, 260, 150);
        scene.getStylesheets().add("css/popup.css");
        window.setScene(scene);
        window.showAndWait();
    }
    
    
    public void showAbout(ActionEvent event) throws IOException
    {
        /*
        Parent parent = FXMLLoader.load(getClass().getResource("About.fxml"));
        Scene scene = new Scene(parent);
        //Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        Stage window = new Stage();
        window.setScene(scene);
        window.setTitle("About User");
        window.show();
        */
        
        
        AnchorPane nextvalscene = (AnchorPane) FXMLLoader.load(getClass().getResource("About.fxml"));
        contentPane.getChildren().clear();
        contentPane.getChildren().add(nextvalscene);
        nextvalscene.toFront();
        
    }
     
}
