package demo;

import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import util.SendMessageData;

/**
 * FXML Controller class
 *
 * @author Mirko
 */
public class DemoMainController implements Initializable {

    @FXML private Button optionAuthentication;
    @FXML private Button optionConfidentiality;
    @FXML private Button optionFullService;
    @FXML private Button optionRadix64;
    @FXML private Button optionRadixAndCompression;
    @FXML private Button optionSegmentation;
    @FXML private Button optionSessionKey;
    @FXML private Button optionPrivateRing;
    @FXML private Button optionPublicRing;
    @FXML private Button optionWebOfTrust;
    @FXML private Button optionNegative;
    
    @FXML private Button returnButton;
    
    @FXML private Button optionTest;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        SendMessageData.selectedMode = 0;
    }    
    
    public void returnToMainMenu(ActionEvent event) throws IOException
    {
        Parent mainParent = FXMLLoader.load(getClass().getResource("/threepalmtrees/pgp/Welcome.fxml"));
        Scene mainScene = new Scene(mainParent);
        Stage mainWindow = (Stage)((Node)event.getSource()).getScene().getWindow();
        mainWindow.setScene(mainScene);
        mainWindow.show();
    }
    
    public void authentication(ActionEvent event) throws IOException
    {
        SendMessageData.selectedMode = 100;
        DemoData.authentication = true;
        
        Parent mainParent = FXMLLoader.load(getClass().getResource("DemoAuthentication.fxml"));
        Scene mainScene = new Scene(mainParent);
        Stage mainWindow = (Stage)((Node)event.getSource()).getScene().getWindow();
        mainWindow.setScene(mainScene);
        mainWindow.show();
    }
    
    public void confidentiality(ActionEvent event) throws IOException
    {
        SendMessageData.selectedMode = 200;
        DemoData.confidentiality = true;
        
        Parent mainParent = FXMLLoader.load(getClass().getResource("DemoAuthentication.fxml"));
        Scene mainScene = new Scene(mainParent);
        Stage mainWindow = (Stage)((Node)event.getSource()).getScene().getWindow();
        mainWindow.setScene(mainScene);
        mainWindow.show();
    }
    
    public void fullService(ActionEvent event) throws IOException
    {
        SendMessageData.selectedMode = 311;
        DemoData.fullService = true;
        
        Parent mainParent = FXMLLoader.load(getClass().getResource("DemoAuthentication.fxml"));
        Scene mainScene = new Scene(mainParent);
        Stage mainWindow = (Stage)((Node)event.getSource()).getScene().getWindow();
        mainWindow.setScene(mainScene);
        mainWindow.show();
    }
    
    public void radix64(ActionEvent event) throws IOException
    {   
        Parent mainParent = FXMLLoader.load(getClass().getResource("DemoRadix64.fxml"));
        Scene mainScene = new Scene(mainParent);
        Stage mainWindow = (Stage)((Node)event.getSource()).getScene().getWindow();
        mainWindow.setScene(mainScene);
        mainWindow.show();
    }
    
    public void radixAndCompression(ActionEvent event) throws IOException
    {   
        Parent mainParent = FXMLLoader.load(getClass().getResource("DemoRadixCompression.fxml"));
        Scene mainScene = new Scene(mainParent);
        Stage mainWindow = (Stage)((Node)event.getSource()).getScene().getWindow();
        mainWindow.setScene(mainScene);
        mainWindow.show();
    }
    
    public void segmentation(ActionEvent event) throws IOException
    {        
        Parent mainParent = FXMLLoader.load(getClass().getResource("DemoSegmentation.fxml"));
        Scene mainScene = new Scene(mainParent);
        Stage mainWindow = (Stage)((Node)event.getSource()).getScene().getWindow();
        mainWindow.setScene(mainScene);
        mainWindow.show();
    }
    
    public void privateRing(ActionEvent event) throws IOException
    {        
        Parent mainParent = FXMLLoader.load(getClass().getResource("DemoPrivateKeyRing.fxml"));
        Scene mainScene = new Scene(mainParent);
        Stage mainWindow = (Stage)((Node)event.getSource()).getScene().getWindow();
        mainWindow.setScene(mainScene);
        mainWindow.show();
    }
    
    public void publicRing(ActionEvent event) throws IOException
    {        
        Parent mainParent = FXMLLoader.load(getClass().getResource("DemoPublicKey.fxml"));
        Scene mainScene = new Scene(mainParent);
        Stage mainWindow = (Stage)((Node)event.getSource()).getScene().getWindow();
        mainWindow.setScene(mainScene);
        mainWindow.show();
    }
    
    public void sessionKey(ActionEvent event) throws IOException
    {   
        Parent mainParent = FXMLLoader.load(getClass().getResource("DemoSessionKey.fxml"));
        Scene mainScene = new Scene(mainParent);
        Stage mainWindow = (Stage)((Node)event.getSource()).getScene().getWindow();
        mainWindow.setScene(mainScene);
        mainWindow.show();
    }
    
    public void webOfTrust(ActionEvent event) throws IOException
    {   
        Parent mainParent = FXMLLoader.load(getClass().getResource("DemoWebOfTrust.fxml"));
        Scene mainScene = new Scene(mainParent);
        Stage mainWindow = (Stage)((Node)event.getSource()).getScene().getWindow();
        mainWindow.setScene(mainScene);
        mainWindow.show();
    }
    
    public void negative(ActionEvent event) throws IOException
    {   
        Parent mainParent = FXMLLoader.load(getClass().getResource("DemoNegativeAuthentication.fxml"));
        Scene mainScene = new Scene(mainParent);
        Stage mainWindow = (Stage)((Node)event.getSource()).getScene().getWindow();
        mainWindow.setScene(mainScene);
        mainWindow.show();
    }
    
    public void test(ActionEvent event) throws IOException
    {   
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Test Mode");
        
        Button yesHints = new Button("Test with Hints");
        yesHints.setMinWidth(200);
        yesHints.setOnAction(e ->
                                    {
                                        DemoTestData.showHints = true;
                                        
                                        window.close();
                                        
                                        Parent mainParent = null;
                                        try {
                                            mainParent = FXMLLoader.load(getClass().getResource("DemoTest.fxml"));
                                        } catch (IOException ex) {
                                        Logger.getLogger(DemoMainController.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                        
                                        Scene mainScene = new Scene(mainParent);
                                        Stage mainWindow = new Stage();
                                        mainWindow.setScene(mainScene);
                                        //Stage mainWindow = (Stage)((Node)event.getSource()).getScene().getWindow();
                                        //mainWindow.setScene(mainScene);
                                        mainWindow.show();
                                    });
        
        Button noHints = new Button("Test without Hints");
        noHints.setMinWidth(200);
        noHints.setOnAction(e ->
                                    {
                                        DemoTestData.showHints = false;
                                        
                                        window.close();
                                        
                                        Parent mainParent = null;
                                        try {
                                            mainParent = FXMLLoader.load(getClass().getResource("DemoTest.fxml"));
                                        } catch (IOException ex) {
                                        Logger.getLogger(DemoMainController.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                        
                                        Scene mainScene = new Scene(mainParent);
                                        Stage mainWindow = (Stage)((Node)event.getSource()).getScene().getWindow();
                                        mainWindow.setScene(mainScene);
                                        mainWindow.show();
                                    });
        
        VBox layout = new VBox(10);
        layout.getChildren().addAll(yesHints, noHints);
        layout.setPadding(new Insets(10, 10, 10, 10));
        layout.setAlignment(Pos.CENTER);
        
        Scene scene = new Scene(layout, 350, 180);
        scene.getStylesheets().add("css/popup.css");
        window.setScene(scene);
        window.showAndWait();
          
    }
    
}
