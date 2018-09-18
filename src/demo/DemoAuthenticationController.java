
package demo;

import java.io.IOException;
import java.net.URL;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SignatureException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.Transition;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
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
public class DemoAuthenticationController implements Initializable {

    @FXML private Button nextButton;
    @FXML private Button schemeButton;
    @FXML private Label userLabel;
    @FXML private TextArea demoATextArea;
    @FXML private TextFlow textFlow;
    
    @FXML private Button backButton;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        demoATextArea.setEditable(false);
        String style = "-fx-text-fill: white;";
        demoATextArea.setStyle(style);
        
        DemoData.authenticationStepCointer = 0;
        DemoData.confidentialityStepCounter = 0;
        DemoData.fullServiceStepCounter = 0;
        
        SchemeData.clearS();
        SchemeData.clearE();
        SchemeData.clearB();
        
        nextButton.disableProperty().set(true);
        textFlow.getChildren().clear();
        
        
        if(SendMessageData.selectedMode == 100)
        {
            writeTextAuthentication(DemoData.authenticationStepCointer);
            
            if(SignSchemeController.ssButton1 != null)
            {
                style = "-fx-border-color: red; -fx-border-width: 3 3 3 3;";
                SignSchemeController.ssButton1.setStyle(style);
            }
            
            SchemeData.sButton[1] = true;
        }
        
        
        if(SendMessageData.selectedMode == 200)
        {
            writeTextConfidentiality(DemoData.confidentialityStepCounter);
            
            if(EncryptSchemeController.eeButton1 != null)
            {
                style = "-fx-border-color: red; -fx-border-width: 3 3 3 3;";
                EncryptSchemeController.eeButton1.setStyle(style);
            }
            
            SchemeData.eButton[1] = true;
        }
        
        
        if(SendMessageData.selectedMode == 311)
        {
            writeTextFullService(DemoData.fullServiceStepCounter);
            
            if(SignAndEncryptSchemeController.bbButton1 != null)
            {
                style = "-fx-border-color: red; -fx-border-width: 3 3 3 3;";
                SignAndEncryptSchemeController.bbButton4.setStyle(style);
            }
            
            SchemeData.bButton[1] = true;
        }
        
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
    
    
    public void next(ActionEvent event) throws InterruptedException, NoSuchAlgorithmException, IOException, InvalidKeyException, SignatureException, NoSuchProviderException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException
    {
        nextButton.disableProperty().set(true);
        textFlow.getChildren().clear();
        
        
        if(SendMessageData.selectedMode == 100)
        {
            if(DemoData.authenticationStepCointer < 10)
            { writeTextAuthentication(DemoData.authenticationStepCointer); }
            else
            {
                SendMessageData.selectedMode = 0;
                SchemeData.clearS();
                
                DemoData.authentication = false;
                DemoData.confidentiality = false;
                DemoData.fullService = false;
                
                Parent mainParent = FXMLLoader.load(getClass().getResource("/demo/DemoMain.fxml"));
                Scene mainScene = new Scene(mainParent);
                Stage mainWindow = (Stage)((Node)event.getSource()).getScene().getWindow();
                mainWindow.setScene(mainScene);
                mainWindow.show();
                return;
            }
            
            if(DemoData.authenticationStepCointer == 2)
            { 
                DemoData.initMessage();
                formatMessage(); 
                demoATextArea.positionCaret(0);
            }
            
            if(DemoData.authenticationStepCointer == 3)
            {
                if(SignSchemeController.ssButton1 != null)
                {
                    SignSchemeController.ss1.setTextFill(Color.RED);
                    SignSchemeController.ss2.setTextFill(Color.RED);
                }
                
                SchemeData.sLabel[1] = true;
                SchemeData.sLabel[2] = true;
            }
            
            if(DemoData.authenticationStepCointer == 4)
            { 
                DemoData.initSignature();
                formatSignature();
                demoATextArea.positionCaret(0);
                
                if(SignSchemeController.ssButton1 != null)
                {
                    SignSchemeController.ss3.setTextFill(Color.RED);
                    SignSchemeController.ss4.setTextFill(Color.RED);
                    SignSchemeController.ss5.setTextFill(Color.RED);
                    SignSchemeController.ss6.setTextFill(Color.RED);
                    SignSchemeController.ss7.setTextFill(Color.RED);
                    SignSchemeController.ss8.setTextFill(Color.RED);
                    
                    String style = "-fx-border-color: red; -fx-border-width: 3 3 3 3;";
                    SignSchemeController.ssButton2.setStyle(style);
                    style = "-fx-border-width: 0;";
                    SignSchemeController.ssButton1.setStyle(style);
                }
                
                SchemeData.sLabel[3] = true;
                SchemeData.sLabel[4] = true;
                SchemeData.sLabel[5] = true;
                SchemeData.sLabel[6] = true;
                SchemeData.sLabel[7] = true;
                SchemeData.sLabel[8] = true;
                
                SchemeData.sButton[1] = false;
                SchemeData.sButton[2] = true;
            }
            
            if(DemoData.authenticationStepCointer == 6)
            { 
                userLabel.setText("Recipient");
            }
            
            if(DemoData.authenticationStepCointer == 7)
            { 
                if(SignSchemeController.ssButton1 != null)
                {
                    SignSchemeController.ss9.setTextFill(Color.RED);
                    SignSchemeController.ss13.setTextFill(Color.RED);
                    SignSchemeController.ss14.setTextFill(Color.RED);
                    SignSchemeController.ss19.setTextFill(Color.RED);
                    SignSchemeController.ss20.setTextFill(Color.RED);
                    SignSchemeController.ss21.setTextFill(Color.RED);
                    SignSchemeController.ss22.setTextFill(Color.RED);
                    SignSchemeController.ss26.setTextFill(Color.RED);
                    SignSchemeController.ss27.setTextFill(Color.RED);
                    SignSchemeController.ss31.setTextFill(Color.RED);
                    SignSchemeController.ss32.setTextFill(Color.RED);
                    SignSchemeController.ss33.setTextFill(Color.RED);
                    
                    String style = "-fx-border-color: red; -fx-border-width: 3 3 3 3;";
                    SignSchemeController.ssButton7.setStyle(style);
                    style = "-fx-border-width: 0;";
                    SignSchemeController.ssButton2.setStyle(style);
                }
                
                SchemeData.sLabel[9] = true;
                SchemeData.sLabel[13] = true;
                SchemeData.sLabel[14] = true;
                SchemeData.sLabel[19] = true;
                SchemeData.sLabel[20] = true;
                SchemeData.sLabel[21] = true;
                SchemeData.sLabel[22] = true;
                SchemeData.sLabel[26] = true;
                SchemeData.sLabel[27] = true;
                SchemeData.sLabel[31] = true;
                SchemeData.sLabel[32] = true;
                SchemeData.sLabel[33] = true;
                
                SchemeData.sButton[2] = false;
                SchemeData.sButton[7] = true;
            }
            
            if(DemoData.authenticationStepCointer == 8)
            {
                if(SignSchemeController.ssButton1 != null)
                {
                    SignSchemeController.ss34.setTextFill(Color.RED);
                    SignSchemeController.ss35.setTextFill(Color.RED);
                    SignSchemeController.ss36.setTextFill(Color.RED);
                }
                
                SchemeData.sLabel[34] = true;
                SchemeData.sLabel[35] = true;
                SchemeData.sLabel[36] = true;
            }
            
            if(DemoData.authenticationStepCointer == 9)
            {
                nextButton.setText("Finish");
                nextButton.disableProperty().set(false);
                
                if(SignSchemeController.ssButton1 != null)
                {
                    SignSchemeController.ss37.setTextFill(Color.RED);
                    SignSchemeController.ss38.setTextFill(Color.RED);
                    SignSchemeController.ss39.setTextFill(Color.RED);
                    SignSchemeController.ss40.setTextFill(Color.RED);
                    SignSchemeController.ss41.setTextFill(Color.RED);
                }
                
                SchemeData.sLabel[37] = true;
                SchemeData.sLabel[38] = true;
                SchemeData.sLabel[39] = true;
                SchemeData.sLabel[40] = true;
                SchemeData.sLabel[41] = true;
            }
            
        }
        
        
        if(SendMessageData.selectedMode == 200)
        {
            if(DemoData.confidentialityStepCounter < 8)
            { writeTextConfidentiality(DemoData.confidentialityStepCounter); }
            
            if(DemoData.confidentialityStepCounter == 2)
            { 
                DemoData.initMessage();
                DemoData.initSignature();
                formatMessage(); 
                demoATextArea.positionCaret(0);
            }
            
            if(DemoData.confidentialityStepCounter == 3)
            { 
                DemoData.initData();
                formatEncyption1();
                demoATextArea.positionCaret(0);
                
                if(EncryptSchemeController.eeButton1 != null)
                {
                    EncryptSchemeController.ee1.setTextFill(Color.RED);
                    EncryptSchemeController.ee5.setTextFill(Color.RED);
                    EncryptSchemeController.ee6.setTextFill(Color.RED);
                    EncryptSchemeController.ee7.setTextFill(Color.RED);
                    EncryptSchemeController.ee8.setTextFill(Color.RED);
                    EncryptSchemeController.ee9.setTextFill(Color.RED);
                    EncryptSchemeController.ee10.setTextFill(Color.RED);
                }
                
                SchemeData.eLabel[1] = true;
                SchemeData.eLabel[5] = true;
                SchemeData.eLabel[6] = true;
                SchemeData.eLabel[7] = true;
                SchemeData.eLabel[8] = true;
                SchemeData.eLabel[9] = true;
                SchemeData.eLabel[10] = true;
            }
            
            if(DemoData.confidentialityStepCounter == 4)
            { 
                formatEncyption2();
                demoATextArea.positionCaret(0);
                
                if(EncryptSchemeController.eeButton1 != null)
                {
                    EncryptSchemeController.ee11.setTextFill(Color.RED);
                    EncryptSchemeController.ee12.setTextFill(Color.RED);
                    EncryptSchemeController.ee13.setTextFill(Color.RED);
                    EncryptSchemeController.ee14.setTextFill(Color.RED);
                    
                    String style = "-fx-border-color: red; -fx-border-width: 3 3 3 3;";
                    EncryptSchemeController.eeButton3.setStyle(style);
                    style = "-fx-border-width: 0;";
                    EncryptSchemeController.eeButton1.setStyle(style);
                }
                
                SchemeData.eLabel[11] = true;
                SchemeData.eLabel[12] = true;
                SchemeData.eLabel[13] = true;
                SchemeData.eLabel[14] = true;
                
                SchemeData.eButton[1] = false;
                SchemeData.eButton[3] = true;
            }
            
            if(DemoData.confidentialityStepCounter == 5)
            { 
                userLabel.setText("Recipient");
            }
            
            if(DemoData.confidentialityStepCounter == 6)
            {
                if(EncryptSchemeController.eeButton1 != null)
                {
                    EncryptSchemeController.ee15.setTextFill(Color.RED);
                    EncryptSchemeController.ee19.setTextFill(Color.RED);
                    EncryptSchemeController.ee20.setTextFill(Color.RED);
                    EncryptSchemeController.ee21.setTextFill(Color.RED);
                    EncryptSchemeController.ee22.setTextFill(Color.RED);
                    EncryptSchemeController.ee23.setTextFill(Color.RED);
                    EncryptSchemeController.ee26.setTextFill(Color.RED);
                    EncryptSchemeController.ee27.setTextFill(Color.RED);
                    EncryptSchemeController.ee28.setTextFill(Color.RED);
                    EncryptSchemeController.ee29.setTextFill(Color.RED);
                    
                    String style = "-fx-border-color: red; -fx-border-width: 3 3 3 3;";
                    EncryptSchemeController.eeButton6.setStyle(style);
                    style = "-fx-border-width: 0;";
                    EncryptSchemeController.eeButton3.setStyle(style);
                }
                
                SchemeData.eLabel[15] = true;
                SchemeData.eLabel[19] = true;
                SchemeData.eLabel[20] = true;
                SchemeData.eLabel[21] = true;
                SchemeData.eLabel[22] = true;
                SchemeData.eLabel[23] = true;
                SchemeData.eLabel[26] = true;
                SchemeData.eLabel[27] = true;
                SchemeData.eLabel[28] = true;
                SchemeData.eLabel[29] = true;
                
                SchemeData.eButton[3] = false;
                SchemeData.eButton[6] = true;
            }
            
            if(DemoData.confidentialityStepCounter == 7)
            { 
                formatEncyption3();
                demoATextArea.positionCaret(0);
                
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
            
            if(DemoData.confidentialityStepCounter == 8)
            { 
                formatEncryption4();
                demoATextArea.positionCaret(0);
                nextButton.setText("Finish");
                nextButton.disableProperty().set(false);
                DemoData.confidentialityStepCounter++;
                
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
                
                return;
            }
            
            if(DemoData.confidentialityStepCounter == 9)
            {
                SendMessageData.selectedMode = 0;
                SchemeData.clearE();
                
                DemoData.authentication = false;
                DemoData.confidentiality = false;
                DemoData.fullService = false;
                
                Parent mainParent = FXMLLoader.load(getClass().getResource("/demo/DemoMain.fxml"));
                Scene mainScene = new Scene(mainParent);
                Stage mainWindow = (Stage)((Node)event.getSource()).getScene().getWindow();
                mainWindow.setScene(mainScene);
                mainWindow.show();
                return;
            }
        }
        
        
        if(SendMessageData.selectedMode == 311)
        {
            if(DemoData.fullServiceStepCounter < 18)
            { writeTextFullService(DemoData.fullServiceStepCounter); }
            
            if(DemoData.fullServiceStepCounter == 2)
            {
                DemoData.initMessage();
                formatMessage();
                demoATextArea.positionCaret(0);
            }
            
            if(DemoData.fullServiceStepCounter == 3)
            {
                if(SignAndEncryptSchemeController.bbButton1 != null)
                {
                    SignAndEncryptSchemeController.bb1.setTextFill(Color.RED);
                    SignAndEncryptSchemeController.bb2.setTextFill(Color.RED);
                }
            
                SchemeData.bLabel[1] = true;
                SchemeData.bLabel[2] = true;
            }
            
            if(DemoData.fullServiceStepCounter == 4)
            { 
                DemoData.initSignature();
                formatSignature();
                demoATextArea.positionCaret(0);
                
                if(SignAndEncryptSchemeController.bbButton1 != null)
                {
                    SignAndEncryptSchemeController.bb3.setTextFill(Color.RED);
                    SignAndEncryptSchemeController.bb4.setTextFill(Color.RED);
                    SignAndEncryptSchemeController.bb5.setTextFill(Color.RED);
                    SignAndEncryptSchemeController.bb6.setTextFill(Color.RED);
                    SignAndEncryptSchemeController.bb7.setTextFill(Color.RED);
                    SignAndEncryptSchemeController.bb8.setTextFill(Color.RED);
                    
                    String style = "-fx-border-color: red; -fx-border-width: 3 3 3 3;";
                    SignAndEncryptSchemeController.bbButton2.setStyle(style);
                    style = "-fx-border-width: 0;";
                    SignAndEncryptSchemeController.bbButton1.setStyle(style);
                }
            
                SchemeData.bLabel[3] = true;
                SchemeData.bLabel[4] = true;
                SchemeData.bLabel[5] = true;
                SchemeData.bLabel[6] = true;
                SchemeData.bLabel[7] = true;
                SchemeData.bLabel[8] = true;
                
                SchemeData.bButton[1] = false;
                SchemeData.bButton[2] = true;
            }
            
            if(DemoData.fullServiceStepCounter == 5)
            {
                DemoData.initData();
                formatCompressed();
                demoATextArea.positionCaret(0);
                
                if(SignAndEncryptSchemeController.bbButton1 != null)
                {
                    SignAndEncryptSchemeController.bb9.setTextFill(Color.RED);
                    SignAndEncryptSchemeController.bb10.setTextFill(Color.RED);
                    SignAndEncryptSchemeController.bb11.setTextFill(Color.RED);
                    
                    String style = "-fx-border-color: red; -fx-border-width: 3 3 3 3;";
                    SignAndEncryptSchemeController.bbButton3.setStyle(style);
                    style = "-fx-border-width: 0;";
                    SignAndEncryptSchemeController.bbButton2.setStyle(style);
                }
            
                SchemeData.bLabel[9] = true;
                SchemeData.bLabel[10] = true;
                SchemeData.bLabel[11] = true;
                
                SchemeData.bButton[2] = false;
                SchemeData.bButton[3] = true;
            }
            
            if(DemoData.fullServiceStepCounter == 7)
            {
                formatFull1();
                demoATextArea.positionCaret(0);
                
                if(SignAndEncryptSchemeController.bbButton1 != null)
                {
                    SignAndEncryptSchemeController.bb12.setTextFill(Color.RED);
                    SignAndEncryptSchemeController.bb16.setTextFill(Color.RED);
                    SignAndEncryptSchemeController.bb17.setTextFill(Color.RED);
                    SignAndEncryptSchemeController.bb18.setTextFill(Color.RED);
                }
            
                SchemeData.bLabel[12] = true;
                SchemeData.bLabel[16] = true;
                SchemeData.bLabel[17] = true;
                SchemeData.bLabel[18] = true;
            }
            
            if(DemoData.fullServiceStepCounter == 8)
            {
                formatFull2();
                demoATextArea.positionCaret(0);
                
                if(SignAndEncryptSchemeController.bbButton1 != null)
                {
                    SignAndEncryptSchemeController.bb19.setTextFill(Color.RED);
                    SignAndEncryptSchemeController.bb20.setTextFill(Color.RED);
                    SignAndEncryptSchemeController.bb21.setTextFill(Color.RED);
                    SignAndEncryptSchemeController.bb22.setTextFill(Color.RED);
                    
                    String style = "-fx-border-color: red; -fx-border-width: 3 3 3 3;";
                    SignAndEncryptSchemeController.bbButton4.setStyle(style);
                    style = "-fx-border-width: 0;";
                    SignAndEncryptSchemeController.bbButton3.setStyle(style);
                }
            
                SchemeData.bLabel[19] = true;
                SchemeData.bLabel[20] = true;
                SchemeData.bLabel[21] = true;
                SchemeData.bLabel[22] = true;
                
                SchemeData.bButton[3] = false;
                SchemeData.bButton[4] = true;
            }
            
            if(DemoData.fullServiceStepCounter == 9)
            {
                formatConverted();
                demoATextArea.positionCaret(0);
            }
            
            if(DemoData.fullServiceStepCounter == 10)
            {
                userLabel.setText("Recipient");
                
                if(SignAndEncryptSchemeController.bbButton1 != null)
                {
                    SignAndEncryptSchemeController.bb23.setTextFill(Color.RED);
                    SignAndEncryptSchemeController.bb24.setTextFill(Color.RED);
                    SignAndEncryptSchemeController.bb25.setTextFill(Color.RED);
                    
                    String style = "-fx-border-color: red; -fx-border-width: 3 3 3 3;";
                    SignAndEncryptSchemeController.bbButton5.setStyle(style);
                    style = "-fx-border-width: 0;";
                    SignAndEncryptSchemeController.bbButton4.setStyle(style);
                }
            
                SchemeData.bLabel[23] = true;
                SchemeData.bLabel[24] = true;
                SchemeData.bLabel[25] = true;
                
                SchemeData.bButton[4] = false;
                SchemeData.bButton[5] = true;
            }
            
            if(DemoData.fullServiceStepCounter == 11)
            {
                if(SignAndEncryptSchemeController.bbButton1 != null)
                {
                    SignAndEncryptSchemeController.bb26.setTextFill(Color.RED);
                    SignAndEncryptSchemeController.bb30.setTextFill(Color.RED);
                    
                    String style = "-fx-border-color: red; -fx-border-width: 3 3 3 3;";
                    SignAndEncryptSchemeController.bbButton6.setStyle(style);
                    style = "-fx-border-width: 0;";
                    SignAndEncryptSchemeController.bbButton5.setStyle(style);
                }
            
                SchemeData.bLabel[26] = true;
                SchemeData.bLabel[30] = true;
                
                SchemeData.bButton[5] = false;
                SchemeData.bButton[6] = true;
            }
            
            if(DemoData.fullServiceStepCounter == 12)
            {
                formatFull2();
                demoATextArea.positionCaret(0);
                
                if(SignAndEncryptSchemeController.bbButton1 != null)
                {
                    SignAndEncryptSchemeController.bb31.setTextFill(Color.RED);
                    SignAndEncryptSchemeController.bb32.setTextFill(Color.RED);
                    SignAndEncryptSchemeController.bb33.setTextFill(Color.RED);
                    SignAndEncryptSchemeController.bb37.setTextFill(Color.RED);
                    
                    String style = "-fx-border-color: red; -fx-border-width: 3 3 3 3;";
                    SignAndEncryptSchemeController.bbButton7.setStyle(style);
                    style = "-fx-border-width: 0;";
                    SignAndEncryptSchemeController.bbButton6.setStyle(style);
                }
            
                SchemeData.bLabel[31] = true;
                SchemeData.bLabel[32] = true;
                SchemeData.bLabel[33] = true;
                SchemeData.bLabel[37] = true;
                
                SchemeData.bButton[6] = false;
                SchemeData.bButton[7] = true;
            }
            
            if(DemoData.fullServiceStepCounter == 13)
            {
                formatFull3();
                demoATextArea.positionCaret(0);
                
                if(SignAndEncryptSchemeController.bbButton1 != null)
                {
                    SignAndEncryptSchemeController.bb38.setTextFill(Color.RED);
                    SignAndEncryptSchemeController.bb39.setTextFill(Color.RED);
                    SignAndEncryptSchemeController.bb40.setTextFill(Color.RED);
                    SignAndEncryptSchemeController.bb41.setTextFill(Color.RED);
                    SignAndEncryptSchemeController.bb42.setTextFill(Color.RED);
                }
            
                SchemeData.bLabel[38] = true;
                SchemeData.bLabel[39] = true;
                SchemeData.bLabel[40] = true;
                SchemeData.bLabel[41] = true;
                SchemeData.bLabel[42] = true;
            }
            
            if(DemoData.fullServiceStepCounter == 14)
            {
                formatFull4();
                demoATextArea.positionCaret(0);
                
                if(SignAndEncryptSchemeController.bbButton1 != null)
                {
                    SignAndEncryptSchemeController.bb43.setTextFill(Color.RED);
                    SignAndEncryptSchemeController.bb44.setTextFill(Color.RED);
                    SignAndEncryptSchemeController.bb45.setTextFill(Color.RED);
                    
                    String style = "-fx-border-color: red; -fx-border-width: 3 3 3 3;";
                    SignAndEncryptSchemeController.bbButton8.setStyle(style);
                    style = "-fx-border-width: 0;";
                    SignAndEncryptSchemeController.bbButton7.setStyle(style);
                }
            
                SchemeData.bLabel[43] = true;
                SchemeData.bLabel[44] = true;
                SchemeData.bLabel[45] = true;
                
                SchemeData.bButton[7] = false;
                SchemeData.bButton[8] = true;
            }
            
            if(DemoData.fullServiceStepCounter == 15)
            {
                formatFull5();
                demoATextArea.positionCaret(0);
                
                if(SignAndEncryptSchemeController.bbButton1 != null)
                {
                    SignAndEncryptSchemeController.bb46.setTextFill(Color.RED);
                    SignAndEncryptSchemeController.bb47.setTextFill(Color.RED);
                    SignAndEncryptSchemeController.bb51.setTextFill(Color.RED);
                    
                    String style = "-fx-border-color: red; -fx-border-width: 3 3 3 3;";
                    SignAndEncryptSchemeController.bbButton9.setStyle(style);
                    style = "-fx-border-width: 0;";
                    SignAndEncryptSchemeController.bbButton8.setStyle(style);
                }
            
                SchemeData.bLabel[46] = true;
                SchemeData.bLabel[47] = true;
                SchemeData.bLabel[51] = true;
                
                SchemeData.bButton[8] = false;
                SchemeData.bButton[9] = true;
            }
            
            if(DemoData.fullServiceStepCounter == 16)
            {
                if(SignAndEncryptSchemeController.bbButton1 != null)
                {
                    SignAndEncryptSchemeController.bb52.setTextFill(Color.RED);
                    SignAndEncryptSchemeController.bb53.setTextFill(Color.RED);
                    SignAndEncryptSchemeController.bb54.setTextFill(Color.RED);
                }
            
                SchemeData.bLabel[52] = true;
                SchemeData.bLabel[53] = true;
                SchemeData.bLabel[54] = true;
            }
            
            if(DemoData.fullServiceStepCounter == 17)
            {
                nextButton.setText("Finish");
                nextButton.disableProperty().set(false);
                DemoData.confidentialityStepCounter++;
                
                if(SignAndEncryptSchemeController.bbButton1 != null)
                {
                    SignAndEncryptSchemeController.bb55.setTextFill(Color.RED);
                    SignAndEncryptSchemeController.bb56.setTextFill(Color.RED);
                    SignAndEncryptSchemeController.bb57.setTextFill(Color.RED);
                    SignAndEncryptSchemeController.bb58.setTextFill(Color.RED);
                    SignAndEncryptSchemeController.bb59.setTextFill(Color.RED);
                }
            
                SchemeData.bLabel[55] = true;
                SchemeData.bLabel[56] = true;
                SchemeData.bLabel[57] = true;
                SchemeData.bLabel[58] = true;
                SchemeData.bLabel[59] = true;
                
                return;
            }
            
            if(DemoData.fullServiceStepCounter == 18)
            {
                SendMessageData.selectedMode = 0;
                SchemeData.clearB();
                
                DemoData.authentication = false;
                DemoData.confidentiality = false;
                DemoData.fullService = false;
                
                Parent mainParent = FXMLLoader.load(getClass().getResource("/demo/DemoMain.fxml"));
                Scene mainScene = new Scene(mainParent);
                Stage mainWindow = (Stage)((Node)event.getSource()).getScene().getWindow();
                mainWindow.setScene(mainScene);
                mainWindow.show();
                return;
            } 
        }
        
    }
    
    
    public void returnToMenu(ActionEvent event) throws IOException
    {
        SendMessageData.selectedMode = 0;
        SchemeData.clearS();
        SchemeData.clearE();
        SchemeData.clearB();
                
        DemoData.authentication = false;
        DemoData.confidentiality = false;
        DemoData.fullService = false;
                
        Parent mainParent = FXMLLoader.load(getClass().getResource("/demo/DemoMain.fxml"));
        Scene mainScene = new Scene(mainParent);
        Stage mainWindow = (Stage)((Node)event.getSource()).getScene().getWindow();
        mainWindow.setScene(mainScene);
        mainWindow.show();
    }
    
    
    public void writeTextAuthentication(int index)
    {   
        String text = DemoData.authenticationString[index];
        
        final IntegerProperty i = new SimpleIntegerProperty(0);
        Timeline timeline = new Timeline();
        KeyFrame keyFrame = new KeyFrame(
                                         Duration.millis(20),
                                         event -> 
                                         {
                                            if (i.get() >= text.length()) 
                                            {
                                                timeline.stop();
                                                nextButton.disableProperty().set(false);
                                                DemoData.authenticationStepCointer++;
                                                return;
                                            } 
                                            else 
                                            {
                                                Text temp = new Text(Character.toString(text.charAt(i.get())));
                                                
                                                switch(index)
                                                {
                                                    case 0:
                                                    {
                                                        if( ((i.get() >= 0) && (i.get() < 22)) || ((i.get() > 54) && (i.get() < 64)) )
                                                        {
                                                            temp.setFont(Font.font ("Verdana", FontWeight.BOLD, 15));
                                                            temp.setFill(Color.YELLOW);
                                                        }
                                                        else
                                                        {
                                                            temp.setFont(Font.font ("Verdana", 15));
                                                            temp.setFill(Color.WHITE);
                                                        }
                                                        break;
                                                    }
                                                    
                                                    case 1:
                                                    {
                                                        temp.setFont(Font.font ("Verdana", 15));
                                                        temp.setFill(Color.WHITE);
                                                        
                                                        break;
                                                    }
                                                    
                                                    case 2:
                                                    {
                                                        if( ((i.get() > 1) && (i.get() < 11)) || ((i.get() > 114) && (i.get() < 133)) )
                                                        {
                                                            temp.setFont(Font.font ("Verdana", FontWeight.BOLD, 15));
                                                            temp.setFill(Color.YELLOW);
                                                        }
                                                        else
                                                        {
                                                            temp.setFont(Font.font ("Verdana", 15));
                                                            temp.setFill(Color.WHITE);
                                                        }
                                                        break;
                                                    }
                                                    
                                                    case 3:
                                                    {
                                                        if((i.get() > 21) && (i.get() < 57))
                                                        {
                                                            temp.setFont(Font.font ("Verdana", FontWeight.BOLD, 15));
                                                            temp.setFill(Color.YELLOW);
                                                        }
                                                        else
                                                        {
                                                            temp.setFont(Font.font ("Verdana", 15));
                                                            temp.setFill(Color.WHITE);
                                                        }
                                                        break;
                                                    }
                                                    
                                                    case 4:
                                                    {
                                                        if( ((i.get() > 47) && (i.get() < 58)) || ((i.get() > 100) && (i.get() < 122)) || 
                                                             ((i.get() > 180) && (i.get() < 196)) )
                                                        {
                                                            temp.setFont(Font.font ("Verdana", FontWeight.BOLD, 15));
                                                            temp.setFill(Color.YELLOW);
                                                        }
                                                        else
                                                        {
                                                            temp.setFont(Font.font ("Verdana", 15));
                                                            temp.setFill(Color.WHITE);
                                                        }
                                                        break;
                                                    }
                                                    
                                                    case 5:
                                                    {
                                                        if( ((i.get() >= 0) && (i.get() < 31)) || ((i.get() > 87) && (i.get() < 108)) || 
                                                            ((i.get() > 204) && (i.get() < 220)) )
                                                        {
                                                            temp.setFont(Font.font ("Verdana", FontWeight.BOLD, 15));
                                                            temp.setFill(Color.YELLOW);
                                                        }
                                                        else
                                                        {
                                                            temp.setFont(Font.font ("Verdana", 15));
                                                            temp.setFill(Color.WHITE);
                                                        }
                                                        break;
                                                    }
                                                    
                                                    case 6:
                                                    {
                                                        temp.setFont(Font.font ("Verdana", 15));
                                                        temp.setFill(Color.WHITE);
                                                        
                                                        break;
                                                    }
                                                    
                                                    case 7:
                                                    {
                                                        if((i.get() > 13) && (i.get() < 34))
                                                        {
                                                            temp.setFont(Font.font ("Verdana", FontWeight.BOLD, 15));
                                                            temp.setFill(Color.YELLOW);
                                                        }
                                                        else
                                                        {
                                                            temp.setFont(Font.font ("Verdana", 15));
                                                            temp.setFill(Color.WHITE);
                                                        }
                                                        break;
                                                    }
                                                    
                                                    case 8:
                                                    {
                                                        if((i.get() > 57) && (i.get() < 79))
                                                        {
                                                            temp.setFont(Font.font ("Verdana", FontWeight.BOLD, 15));
                                                            temp.setFill(Color.YELLOW);
                                                        }
                                                        else
                                                        {
                                                            temp.setFont(Font.font ("Verdana", 15));
                                                            temp.setFill(Color.WHITE);
                                                        }
                                                        break;
                                                    }
                                                    
                                                    case 9:
                                                    {
                                                        if((i.get() > 13) && (i.get() < 22))
                                                        {
                                                            temp.setFont(Font.font ("Verdana", FontWeight.BOLD, 15));
                                                            temp.setFill(Color.YELLOW);
                                                        }
                                                        else
                                                        {
                                                            temp.setFont(Font.font ("Verdana", 15));
                                                            temp.setFill(Color.WHITE);
                                                        }
                                                        break;
                                                    }
                                                }
                                                
                                                textFlow.getChildren().add(temp);
                                                i.set(i.get() + 1);
                                            }
                                          });
        timeline.getKeyFrames().add(keyFrame);
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }
    
    public void writeTextConfidentiality(int index)
    {   
        String text = DemoData.confidentialityString[index];
        
        final IntegerProperty i = new SimpleIntegerProperty(0);
        Timeline timeline = new Timeline();
        KeyFrame keyFrame = new KeyFrame(
                                         Duration.millis(20),
                                         event -> 
                                         {
                                            if (i.get() >= text.length()) 
                                            {
                                                timeline.stop();
                                                nextButton.disableProperty().set(false);
                                                DemoData.confidentialityStepCounter++;
                                                return;
                                            } 
                                            else 
                                            {
                                                Text temp = new Text(Character.toString(text.charAt(i.get())));
                                                
                                                switch(index)
                                                {
                                                    case 0:
                                                    {
                                                        if( ((i.get() >= 0) && (i.get() < 15)) || ((i.get() > 30) && (i.get() < 50)) )
                                                        {
                                                            temp.setFont(Font.font ("Verdana", FontWeight.BOLD, 15));
                                                            temp.setFill(Color.YELLOW);
                                                        }
                                                        else
                                                        {
                                                            temp.setFont(Font.font ("Verdana", 15));
                                                            temp.setFill(Color.WHITE);
                                                        }
                                                        break;
                                                    }
                                                    
                                                    case 1:
                                                    {
                                                        temp.setFont(Font.font ("Verdana", 15));
                                                        temp.setFill(Color.WHITE);
                                                        
                                                        break;
                                                    }
                                                    
                                                    case 2:
                                                    {
                                                        if( ((i.get() > 20) && (i.get() < 31)) || ((i.get() > 86) && (i.get() < 98)) )
                                                        {
                                                            temp.setFont(Font.font ("Verdana", FontWeight.BOLD, 15));
                                                            temp.setFill(Color.YELLOW);
                                                        }
                                                        else
                                                        {
                                                            temp.setFont(Font.font ("Verdana", 15));
                                                            temp.setFill(Color.WHITE);
                                                        }
                                                        break;
                                                    }
                                                    
                                                    case 3:
                                                    {
                                                        if( ((i.get() > 3) && (i.get() < 25)) || ((i.get() > 87) && (i.get() < 117)) )
                                                        {
                                                            temp.setFont(Font.font ("Verdana", FontWeight.BOLD, 15));
                                                            temp.setFill(Color.YELLOW);
                                                        }
                                                        else
                                                        {
                                                            temp.setFont(Font.font ("Verdana", 15));
                                                            temp.setFill(Color.WHITE);
                                                        }
                                                        break;
                                                    }
                                                    
                                                    case 4:
                                                    {
                                                        if( ((i.get() > 41) && (i.get() < 75)) || ((i.get() > 129) && (i.get() < 151)) || 
                                                             ((i.get() > 210) && (i.get() < 231)) || ((i.get() > 286) && (i.get() < 300)) )
                                                        {
                                                            temp.setFont(Font.font ("Verdana", FontWeight.BOLD, 15));
                                                            temp.setFill(Color.YELLOW);
                                                        }
                                                        else
                                                        {
                                                            temp.setFont(Font.font ("Verdana", 15));
                                                            temp.setFill(Color.WHITE);
                                                        }
                                                        break;
                                                    }
                                                    
                                                    case 5:
                                                    {
                                                        temp.setFont(Font.font ("Verdana", 15));
                                                        temp.setFill(Color.WHITE);
                                                        
                                                        break;
                                                    }
                                                    
                                                    case 6:
                                                    {
                                                        if( ((i.get() > 22) && (i.get() < 34)) || ((i.get() > 37) && (i.get() < 75)) )
                                                        {
                                                            temp.setFont(Font.font ("Verdana", FontWeight.BOLD, 15));
                                                            temp.setFill(Color.YELLOW);
                                                        }
                                                        else
                                                        {
                                                            temp.setFont(Font.font ("Verdana", 15));
                                                            temp.setFill(Color.WHITE);
                                                        }
                                                        break;
                                                    }
                                                    
                                                    case 7:
                                                    {
                                                        if( ((i.get() > 3) && (i.get() < 15)) || ((i.get() > 26) && (i.get() < 46)) )
                                                        {
                                                            temp.setFont(Font.font ("Verdana", FontWeight.BOLD, 15));
                                                            temp.setFill(Color.YELLOW);
                                                        }
                                                        else
                                                        {
                                                            temp.setFont(Font.font ("Verdana", 15));
                                                            temp.setFill(Color.WHITE);
                                                        }
                                                        break;
                                                    }
                                                }
                                                
                                                textFlow.getChildren().add(temp);
                                                i.set(i.get() + 1);
                                            }
                                          });
        timeline.getKeyFrames().add(keyFrame);
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }
    
    public void writeTextFullService(int index)
    {   
        String text = DemoData.fullServiceString[index];
        
        final IntegerProperty i = new SimpleIntegerProperty(0);
        Timeline timeline = new Timeline();
        KeyFrame keyFrame = new KeyFrame(
                                         Duration.millis(20),
                                         event -> 
                                         {
                                            if (i.get() >= text.length()) 
                                            {
                                                timeline.stop();
                                                nextButton.disableProperty().set(false);
                                                DemoData.fullServiceStepCounter++;
                                                return;
                                            } 
                                            else 
                                            {
                                                Text temp = new Text(Character.toString(text.charAt(i.get())));
                                                
                                                switch(index)
                                                {
                                                    case 0:
                                                    {
                                                        if( ((i.get() > 25) && (i.get() < 37)) || ((i.get() > 41) && (i.get() < 52)) )
                                                        {
                                                            temp.setFont(Font.font ("Verdana", FontWeight.BOLD, 15));
                                                            temp.setFill(Color.YELLOW);
                                                        }
                                                        else
                                                        {
                                                            temp.setFont(Font.font ("Verdana", 15));
                                                            temp.setFill(Color.WHITE);
                                                        }
                                                        break;
                                                    }
                                                    
                                                    case 1:
                                                    {
                                                        temp.setFont(Font.font ("Verdana", 15));
                                                        temp.setFill(Color.WHITE);
                                                        
                                                        break;
                                                    }
                                                    
                                                    case 2:
                                                    {
                                                        if((i.get() > 1) && (i.get() < 11)) 
                                                        {
                                                            temp.setFont(Font.font ("Verdana", FontWeight.BOLD, 15));
                                                            temp.setFill(Color.YELLOW);
                                                        }
                                                        else
                                                        {
                                                            temp.setFont(Font.font ("Verdana", 15));
                                                            temp.setFill(Color.WHITE);
                                                        }
                                                        break;
                                                    }
                                                    
                                                    case 3:
                                                    {
                                                        if((i.get() > 21) && (i.get() < 57))
                                                        {
                                                            temp.setFont(Font.font ("Verdana", FontWeight.BOLD, 15));
                                                            temp.setFill(Color.YELLOW);
                                                        }
                                                        else
                                                        {
                                                            temp.setFont(Font.font ("Verdana", 15));
                                                            temp.setFill(Color.WHITE);
                                                        }
                                                        break;
                                                    }
                                                    
                                                    case 4:
                                                    {
                                                        if((i.get() > 14) && (i.get() < 25))
                                                        {
                                                            temp.setFont(Font.font ("Verdana", FontWeight.BOLD, 15));
                                                            temp.setFill(Color.YELLOW);
                                                        }
                                                        else
                                                        {
                                                            temp.setFont(Font.font ("Verdana", 15));
                                                            temp.setFill(Color.WHITE);
                                                        }
                                                        break;
                                                    }
                                                    
                                                    case 5:
                                                    {
                                                        if((i.get() > 26) && (i.get() < 77))
                                                        {
                                                            temp.setFont(Font.font ("Verdana", FontWeight.BOLD, 15));
                                                            temp.setFill(Color.YELLOW);
                                                        }
                                                        else
                                                        {
                                                            temp.setFont(Font.font ("Verdana", 15));
                                                            temp.setFill(Color.WHITE);
                                                        }
                                                        break;
                                                    }
                                                    
                                                    case 6:
                                                    {
                                                        if( ((i.get() > 20) && (i.get() < 31)) || ((i.get() > 86) && (i.get() < 98)) )
                                                        {
                                                            temp.setFont(Font.font ("Verdana", FontWeight.BOLD, 15));
                                                            temp.setFill(Color.YELLOW);
                                                        }
                                                        else
                                                        {
                                                            temp.setFont(Font.font ("Verdana", 15));
                                                            temp.setFill(Color.WHITE);
                                                        }
                                                        break;
                                                    }
                                                    
                                                    case 7:
                                                    {
                                                        if( ((i.get() > 3) && (i.get() < 25)) || ((i.get() > 88) && (i.get() < 118)) )
                                                        {
                                                            temp.setFont(Font.font ("Verdana", FontWeight.BOLD, 15));
                                                            temp.setFill(Color.YELLOW);
                                                        }
                                                        else
                                                        {
                                                            temp.setFont(Font.font ("Verdana", 15));
                                                            temp.setFill(Color.WHITE);
                                                        }
                                                        break;
                                                    }
                                                    
                                                    case 8:
                                                    {
                                                        if( ((i.get() > 43) && (i.get() < 76)) || ((i.get() > 158) && (i.get() < 170)) )
                                                        {
                                                            temp.setFont(Font.font ("Verdana", FontWeight.BOLD, 15));
                                                            temp.setFill(Color.YELLOW);
                                                        }
                                                        else
                                                        {
                                                            temp.setFont(Font.font ("Verdana", 15));
                                                            temp.setFill(Color.WHITE);
                                                        }
                                                        break;
                                                    }
                                                    
                                                    case 9:
                                                    {
                                                        if((i.get() > 104) && (i.get() < 113))
                                                        {
                                                            temp.setFont(Font.font ("Verdana", FontWeight.BOLD, 15));
                                                            temp.setFill(Color.YELLOW);
                                                        }
                                                        else
                                                        {
                                                            temp.setFont(Font.font ("Verdana", 15));
                                                            temp.setFill(Color.WHITE);
                                                        }
                                                        break;
                                                    }
                                                    
                                                    case 10:
                                                    {
                                                        temp.setFont(Font.font ("Verdana", 15));
                                                        temp.setFill(Color.WHITE);
                                                        
                                                        break;
                                                    }
                                                    
                                                    case 11:
                                                    {
                                                        if((i.get() > 14) && (i.get() < 26))
                                                        {
                                                            temp.setFont(Font.font ("Verdana", FontWeight.BOLD, 15));
                                                            temp.setFill(Color.YELLOW);
                                                        }
                                                        else
                                                        {
                                                            temp.setFont(Font.font ("Verdana", 15));
                                                            temp.setFill(Color.WHITE);
                                                        }
                                                        break;
                                                    }
                                                    
                                                    case 12:
                                                    {
                                                        if( ((i.get() > 22) && (i.get() < 34)) || ((i.get() > 37) && (i.get() < 75)) )
                                                        {
                                                            temp.setFont(Font.font ("Verdana", FontWeight.BOLD, 15));
                                                            temp.setFill(Color.YELLOW);
                                                        }
                                                        else
                                                        {
                                                            temp.setFont(Font.font ("Verdana", 15));
                                                            temp.setFill(Color.WHITE);
                                                        }
                                                        break;
                                                    }
                                                    
                                                    case 13:
                                                    {
                                                        if( ((i.get() > 3) && (i.get() < 15)) || ((i.get() > 26) && (i.get() < 46)) )
                                                        {
                                                            temp.setFont(Font.font ("Verdana", FontWeight.BOLD, 15));
                                                            temp.setFill(Color.YELLOW);
                                                        }
                                                        else
                                                        {
                                                            temp.setFont(Font.font ("Verdana", 15));
                                                            temp.setFill(Color.WHITE);
                                                        }
                                                        break;
                                                    }
                                                    
                                                    case 14:
                                                    {
                                                        if((i.get() > 14) && (i.get() < 27))
                                                        {
                                                            temp.setFont(Font.font ("Verdana", FontWeight.BOLD, 15));
                                                            temp.setFill(Color.YELLOW);
                                                        }
                                                        else
                                                        {
                                                            temp.setFont(Font.font ("Verdana", 15));
                                                            temp.setFill(Color.WHITE);
                                                        }
                                                        break;
                                                    }
                                                    
                                                    case 15:
                                                    {
                                                        if((i.get() > 13) && (i.get() < 34))
                                                        {
                                                            temp.setFont(Font.font ("Verdana", FontWeight.BOLD, 15));
                                                            temp.setFill(Color.YELLOW);
                                                        }
                                                        else
                                                        {
                                                            temp.setFont(Font.font ("Verdana", 15));
                                                            temp.setFill(Color.WHITE);
                                                        }
                                                        break;
                                                    }
                                                    
                                                    case 16:
                                                    {
                                                        if((i.get() > 57) && (i.get() < 79))
                                                        {
                                                            temp.setFont(Font.font ("Verdana", FontWeight.BOLD, 15));
                                                            temp.setFill(Color.YELLOW);
                                                        }
                                                        else
                                                        {
                                                            temp.setFont(Font.font ("Verdana", 15));
                                                            temp.setFill(Color.WHITE);
                                                        }
                                                        break;
                                                    }
                                                    
                                                    case 17:
                                                    {
                                                        if((i.get() > 13) && (i.get() < 22))
                                                        {
                                                            temp.setFont(Font.font ("Verdana", FontWeight.BOLD, 15));
                                                            temp.setFill(Color.YELLOW);
                                                        }
                                                        else
                                                        {
                                                            temp.setFont(Font.font ("Verdana", 15));
                                                            temp.setFill(Color.WHITE);
                                                        }
                                                        break;
                                                    }
                                                    
                                                }
                                                
                                                textFlow.getChildren().add(temp);
                                                i.set(i.get() + 1);
                                            }
                                          });
        timeline.getKeyFrames().add(keyFrame);
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }
    
    public void formatMessage()
    {
        String newLine = "\n";
        demoATextArea.setText("---------- BEGIN PGP MESSAGE ----------");
        demoATextArea.appendText(newLine);
        demoATextArea.appendText(newLine);
        demoATextArea.appendText("------- BEGIN MESSAGE -------");
        demoATextArea.appendText(newLine);
        demoATextArea.appendText(newLine);
        demoATextArea.appendText("---- Filename ----");
        demoATextArea.appendText(newLine);
        demoATextArea.appendText(DemoData.demoMessageFilename);
        demoATextArea.appendText(newLine);
        demoATextArea.appendText("---- Timestamp ----");
        demoATextArea.appendText(newLine);
        demoATextArea.appendText(DemoData.demoMessageTimestamp);
        demoATextArea.appendText(newLine);
        demoATextArea.appendText("---- Data ----");
        demoATextArea.appendText(newLine);
        demoATextArea.appendText(DemoData.demoMessageData);
        demoATextArea.appendText(newLine);
        demoATextArea.appendText(newLine);
        demoATextArea.appendText("------- END MESSAGE -------");
        demoATextArea.appendText(newLine);
        demoATextArea.appendText(newLine);
        demoATextArea.appendText("---------- END PGP MESSAGE ----------");
    }
    
    public void formatSignature()
    {
        String newLine = "\n";
        demoATextArea.setText("---------- BEGIN PGP MESSAGE ----------");
        demoATextArea.appendText(newLine);
        demoATextArea.appendText(newLine);
        demoATextArea.appendText("------- BEGIN SIGNATURE -------");
        demoATextArea.appendText(newLine);
        demoATextArea.appendText(newLine);
        demoATextArea.appendText("--- Timestamp ---");
        demoATextArea.appendText(newLine);
        demoATextArea.appendText(DemoData.demoSignatureTimestamp);
        demoATextArea.appendText(newLine);
        demoATextArea.appendText("--- Public Key Algorithm ---");
        demoATextArea.appendText(newLine);
        demoATextArea.appendText(DemoData.demoSignatureAlgorithm);
        demoATextArea.appendText(newLine);
        demoATextArea.appendText("--- Hash Algorithm ---");
        demoATextArea.appendText(newLine);
        demoATextArea.appendText(DemoData.demoHashAlgorithm);
        demoATextArea.appendText(newLine);
        demoATextArea.appendText("--- Key Id of Sender's Public Key ---");
        demoATextArea.appendText(newLine);
        demoATextArea.appendText(DemoData.demoSignatureKeyId);
        demoATextArea.appendText(newLine);
        demoATextArea.appendText("--- Leading Two Octets of Message Digest ---");
        demoATextArea.appendText(newLine);
        demoATextArea.appendText(DemoData.demoSignatureLeadingOctets);
        demoATextArea.appendText(newLine);
        demoATextArea.appendText("--- Message Digest ---");
        demoATextArea.appendText(newLine);
        demoATextArea.appendText(DemoData.demoSignatureMessageDigest);
        demoATextArea.appendText(newLine);
        demoATextArea.appendText(newLine);
        demoATextArea.appendText("------- END SIGNATURE -------");
        demoATextArea.appendText(newLine);
        demoATextArea.appendText(newLine);
        demoATextArea.appendText("------- BEGIN MESSAGE -------");
        demoATextArea.appendText(newLine);
        demoATextArea.appendText(newLine);
        demoATextArea.appendText("---- Filename ----");
        demoATextArea.appendText(newLine);
        demoATextArea.appendText(DemoData.demoMessageFilename);
        demoATextArea.appendText(newLine);
        demoATextArea.appendText("---- Timestamp ----");
        demoATextArea.appendText(newLine);
        demoATextArea.appendText(DemoData.demoMessageTimestamp);
        demoATextArea.appendText(newLine);
        demoATextArea.appendText("---- Data ----");
        demoATextArea.appendText(newLine);
        demoATextArea.appendText(DemoData.demoMessageData);
        demoATextArea.appendText(newLine);
        demoATextArea.appendText(newLine);
        demoATextArea.appendText("------- END MESSAGE -------");
        demoATextArea.appendText(newLine);
        demoATextArea.appendText(newLine);
        demoATextArea.appendText("---------- END PGP MESSAGE ----------");
    }
    
    public void formatEncyption1()
    {
        String newLine = "\n";
        demoATextArea.setText("---------- BEGIN PGP MESSAGE ----------");
        demoATextArea.appendText(newLine);
        demoATextArea.appendText(newLine);
        demoATextArea.appendText("---------- BEGIN SYMETRIC KEY PACKET ----------");
        demoATextArea.appendText(newLine);
        demoATextArea.appendText(newLine);
        demoATextArea.appendText("--- Key Id of Recipient's Public Key ---"); 
        demoATextArea.appendText(newLine);
        demoATextArea.appendText("--- Public Key Algorithm ---"); 
        demoATextArea.appendText(newLine);
        demoATextArea.appendText("--- Symetric Algorithm ---"); 
        demoATextArea.appendText(newLine);
        demoATextArea.appendText(DemoData.demoEncryptionSymetricAlgorithm);
        demoATextArea.appendText(newLine);
        demoATextArea.appendText("--- Session Key ---");
        demoATextArea.appendText(newLine);
        demoATextArea.appendText(DemoData.demoEncryptionSessionKey);
        demoATextArea.appendText(newLine);
        demoATextArea.appendText(newLine);
        demoATextArea.appendText("---------- END SYMETRIC KEY PACKET ----------");
        demoATextArea.appendText(newLine);
        demoATextArea.appendText(newLine);
        demoATextArea.appendText("------- BEGIN MESSAGE -------");
        demoATextArea.appendText(newLine);
        demoATextArea.appendText(newLine);
        demoATextArea.appendText("---- Filename ----");
        demoATextArea.appendText(newLine);
        demoATextArea.appendText(DemoData.demoMessageFilename);
        demoATextArea.appendText(newLine);
        demoATextArea.appendText("---- Timestamp ----");
        demoATextArea.appendText(newLine);
        demoATextArea.appendText(DemoData.demoMessageTimestamp);
        demoATextArea.appendText(newLine);
        demoATextArea.appendText("---- Data ----");
        demoATextArea.appendText(newLine);
        demoATextArea.appendText(DemoData.demoMessageData);
        demoATextArea.appendText(newLine);
        demoATextArea.appendText(newLine);
        demoATextArea.appendText("------- END MESSAGE -------");
        demoATextArea.appendText(newLine);
        demoATextArea.appendText(newLine);
        demoATextArea.appendText("---------- END PGP MESSAGE ----------");
    }
    
    public void formatEncyption2()
    {
        String newLine = "\n";
        demoATextArea.setText("---------- BEGIN PGP MESSAGE ----------");
        demoATextArea.appendText(newLine);
        demoATextArea.appendText(newLine);
        demoATextArea.appendText("---------- BEGIN SYMETRIC KEY PACKET ----------");
        demoATextArea.appendText(newLine);
        demoATextArea.appendText(newLine);
        demoATextArea.appendText("--- Key Id of Recipient's Public Key ---"); 
        demoATextArea.appendText(newLine);
        demoATextArea.appendText(DemoData.demoEncryptionKeyId);
        demoATextArea.appendText(newLine);
        demoATextArea.appendText("--- Public Key Algorithm ---"); 
        demoATextArea.appendText(newLine);
        demoATextArea.appendText(DemoData.demoEncryptionPublicAlgorithm);
        demoATextArea.appendText(newLine);
        demoATextArea.appendText("--- Symetric Algorithm ---"); 
        demoATextArea.appendText(newLine);
        demoATextArea.appendText(DemoData.demoEncryptionSymetricAlgorithm);
        demoATextArea.appendText(newLine);
        demoATextArea.appendText("--- Session Key ---");
        demoATextArea.appendText(newLine);
        demoATextArea.appendText(DemoData.demoEncryptionSessionKeyEncrypted);
        demoATextArea.appendText(newLine);
        demoATextArea.appendText(newLine);
        demoATextArea.appendText("---------- END SYMETRIC KEY PACKET ----------");
        demoATextArea.appendText(newLine);
        demoATextArea.appendText(newLine);
        demoATextArea.appendText("------- BEGIN ENCRYPTED DATA PACKET -------");
        demoATextArea.appendText(newLine);
        demoATextArea.appendText(newLine);
        demoATextArea.appendText(DemoData.demoEncryptedData);
        demoATextArea.appendText(newLine);
        demoATextArea.appendText(newLine);
        demoATextArea.appendText("------- END ENCRYPTED DATA PACKET -------");
        demoATextArea.appendText(newLine);
        demoATextArea.appendText(newLine);
        demoATextArea.appendText("---------- END PGP MESSAGE ----------");
    }
    
    public void formatEncyption3()
    {
        String newLine = "\n";
        demoATextArea.setText("---------- BEGIN PGP MESSAGE ----------");
        demoATextArea.appendText(newLine);
        demoATextArea.appendText(newLine);
        demoATextArea.appendText("---------- BEGIN SYMETRIC KEY PACKET ----------");
        demoATextArea.appendText(newLine);
        demoATextArea.appendText(newLine);
        demoATextArea.appendText("--- Key Id of Recipient's Public Key ---"); 
        demoATextArea.appendText(newLine);
        demoATextArea.appendText(DemoData.demoEncryptionKeyId);
        demoATextArea.appendText(newLine);
        demoATextArea.appendText("--- Public Key Algorithm ---"); 
        demoATextArea.appendText(newLine);
        demoATextArea.appendText(DemoData.demoEncryptionPublicAlgorithm);
        demoATextArea.appendText(newLine);
        demoATextArea.appendText("--- Symetric Algorithm ---"); 
        demoATextArea.appendText(newLine);
        demoATextArea.appendText(DemoData.demoEncryptionSymetricAlgorithm);
        demoATextArea.appendText(newLine);
        demoATextArea.appendText("--- Session Key ---");
        demoATextArea.appendText(newLine);
        demoATextArea.appendText(DemoData.demoEncryptionSessionKey);
        demoATextArea.appendText(newLine);
        demoATextArea.appendText(newLine);
        demoATextArea.appendText("---------- END SYMETRIC KEY PACKET ----------");
        demoATextArea.appendText(newLine);
        demoATextArea.appendText(newLine);
        demoATextArea.appendText("------- BEGIN ENCRYPTED DATA PACKET -------");
        demoATextArea.appendText(newLine);
        demoATextArea.appendText(newLine);
        demoATextArea.appendText(DemoData.demoEncryptedData);
        demoATextArea.appendText(newLine);
        demoATextArea.appendText(newLine);
        demoATextArea.appendText("------- END ENCRYPTED DATA PACKET -------");
        demoATextArea.appendText(newLine);
        demoATextArea.appendText(newLine);
        demoATextArea.appendText("---------- END PGP MESSAGE ----------");
    }
    
    public void formatEncryption4()
    {
        String newLine = "\n";
        demoATextArea.setText("---------- BEGIN PGP MESSAGE ----------");
        demoATextArea.appendText(newLine);
        demoATextArea.appendText(newLine);
        demoATextArea.appendText("---------- BEGIN SYMETRIC KEY PACKET ----------");
        demoATextArea.appendText(newLine);
        demoATextArea.appendText(newLine);
        demoATextArea.appendText("--- Key Id of Recipient's Public Key ---"); 
        demoATextArea.appendText(newLine);
        demoATextArea.appendText(DemoData.demoEncryptionKeyId);
        demoATextArea.appendText(newLine);
        demoATextArea.appendText("--- Public Key Algorithm ---"); 
        demoATextArea.appendText(newLine);
        demoATextArea.appendText(DemoData.demoEncryptionPublicAlgorithm);
        demoATextArea.appendText(newLine);
        demoATextArea.appendText("--- Symetric Algorithm ---"); 
        demoATextArea.appendText(newLine);
        demoATextArea.appendText(DemoData.demoEncryptionSymetricAlgorithm);
        demoATextArea.appendText(newLine);
        demoATextArea.appendText("--- Session Key ---");
        demoATextArea.appendText(newLine);
        demoATextArea.appendText(DemoData.demoEncryptionSessionKey);
        demoATextArea.appendText(newLine);
        demoATextArea.appendText(newLine);
        demoATextArea.appendText("---------- END SYMETRIC KEY PACKET ----------");
        demoATextArea.appendText(newLine);
        demoATextArea.appendText(newLine);
        demoATextArea.appendText("------- BEGIN MESSAGE -------");
        demoATextArea.appendText(newLine);
        demoATextArea.appendText(newLine);
        demoATextArea.appendText("---- Filename ----");
        demoATextArea.appendText(newLine);
        demoATextArea.appendText(DemoData.demoMessageFilename);
        demoATextArea.appendText(newLine);
        demoATextArea.appendText("---- Timestamp ----");
        demoATextArea.appendText(newLine);
        demoATextArea.appendText(DemoData.demoMessageTimestamp);
        demoATextArea.appendText(newLine);
        demoATextArea.appendText("---- Data ----");
        demoATextArea.appendText(newLine);
        demoATextArea.appendText(DemoData.demoMessageData);
        demoATextArea.appendText(newLine);
        demoATextArea.appendText(newLine);
        demoATextArea.appendText("------- END MESSAGE -------");
        demoATextArea.appendText(newLine);
        demoATextArea.appendText(newLine);
        demoATextArea.appendText("---------- END PGP MESSAGE ----------");
    }
    
    public void formatCompressed()
    {
        String newLine = "\n";
        demoATextArea.setText("---------- BEGIN PGP MESSAGE ----------");
        demoATextArea.appendText(newLine);
        demoATextArea.appendText(newLine);
        demoATextArea.appendText("------- BEGIN COMPRESSED DATA -------");
        demoATextArea.appendText(newLine);
        demoATextArea.appendText(newLine);
        demoATextArea.appendText(DemoData.demoCompressedData);
        demoATextArea.appendText(newLine);
        demoATextArea.appendText(newLine);
        demoATextArea.appendText("------- END COMPRESSED DATA -------");
        demoATextArea.appendText(newLine);
        demoATextArea.appendText(newLine);
        demoATextArea.appendText("---------- END PGP MESSAGE ----------");
    }
    
    public void formatFull1()
    {
        String newLine = "\n";
        demoATextArea.setText("---------- BEGIN PGP MESSAGE ----------");
        demoATextArea.appendText(newLine);
        demoATextArea.appendText(newLine);
        demoATextArea.appendText("---------- BEGIN SYMETRIC KEY PACKET ----------");
        demoATextArea.appendText(newLine);
        demoATextArea.appendText(newLine);
        demoATextArea.appendText("--- Key Id of Recipient's Public Key ---"); 
        demoATextArea.appendText(newLine);
        demoATextArea.appendText("--- Public Key Algorithm ---"); 
        demoATextArea.appendText(newLine);
        demoATextArea.appendText("--- Symetric Algorithm ---"); 
        demoATextArea.appendText(newLine);
        demoATextArea.appendText(DemoData.demoEncryptionSymetricAlgorithm);
        demoATextArea.appendText(newLine);
        demoATextArea.appendText("--- Session Key ---");
        demoATextArea.appendText(newLine);
        demoATextArea.appendText(DemoData.demoEncryptionSessionKey);
        demoATextArea.appendText(newLine);
        demoATextArea.appendText(newLine);
        demoATextArea.appendText("---------- END SYMETRIC KEY PACKET ----------");
        demoATextArea.appendText(newLine);
        demoATextArea.appendText(newLine);
        demoATextArea.appendText("------- BEGIN COMPRESSED DATA -------");
        demoATextArea.appendText(newLine);
        demoATextArea.appendText(newLine);
        demoATextArea.appendText(DemoData.demoCompressedData);
        demoATextArea.appendText(newLine);
        demoATextArea.appendText(newLine);
        demoATextArea.appendText("------- END COMPRESSED DATA -------");
        demoATextArea.appendText(newLine);
        demoATextArea.appendText(newLine);
        demoATextArea.appendText("---------- END PGP MESSAGE ----------");
    }
    
    public void formatFull2()
    {
        String newLine = "\n";
        demoATextArea.setText("---------- BEGIN PGP MESSAGE ----------");
        demoATextArea.appendText(newLine);
        demoATextArea.appendText(newLine);
        demoATextArea.appendText("---------- BEGIN SYMETRIC KEY PACKET ----------");
        demoATextArea.appendText(newLine);
        demoATextArea.appendText(newLine);
        demoATextArea.appendText("--- Key Id of Recipient's Public Key ---"); 
        demoATextArea.appendText(newLine);
        demoATextArea.appendText(DemoData.demoEncryptionKeyId);
        demoATextArea.appendText(newLine);
        demoATextArea.appendText("--- Public Key Algorithm ---"); 
        demoATextArea.appendText(newLine);
        demoATextArea.appendText(DemoData.demoEncryptionPublicAlgorithm);
        demoATextArea.appendText(newLine);
        demoATextArea.appendText("--- Symetric Algorithm ---"); 
        demoATextArea.appendText(newLine);
        demoATextArea.appendText(DemoData.demoEncryptionSymetricAlgorithm);
        demoATextArea.appendText(newLine);
        demoATextArea.appendText("--- Session Key ---");
        demoATextArea.appendText(newLine);
        demoATextArea.appendText(DemoData.demoEncryptionSessionKeyEncrypted);
        demoATextArea.appendText(newLine);
        demoATextArea.appendText(newLine);
        demoATextArea.appendText("---------- END SYMETRIC KEY PACKET ----------");
        demoATextArea.appendText(newLine);
        demoATextArea.appendText(newLine);
        demoATextArea.appendText("------- BEGIN ENCRYPTED DATA PACKET -------");
        demoATextArea.appendText(newLine);
        demoATextArea.appendText(newLine);
        demoATextArea.appendText(DemoData.demoEncryptedData);
        demoATextArea.appendText(newLine);
        demoATextArea.appendText(newLine);
        demoATextArea.appendText("------- END ENCRYPTED DATA PACKET -------");
        demoATextArea.appendText(newLine);
        demoATextArea.appendText(newLine);
        demoATextArea.appendText("---------- END PGP MESSAGE ----------");
    }
    
    public void formatConverted()
    {
        String newLine = "\n";
        demoATextArea.setText("---------- BEGIN PGP MESSAGE ----------");
        demoATextArea.appendText(newLine);
        demoATextArea.appendText(newLine);
        demoATextArea.appendText("------- BEGIN DATA IN RADIX64 -------");
        demoATextArea.appendText(newLine);
        demoATextArea.appendText(newLine);
        demoATextArea.appendText(DemoData.demoConvertedData);
        demoATextArea.appendText(newLine);
        demoATextArea.appendText(newLine);
        demoATextArea.appendText("------- END DATA IN RADIX64 -------");
        demoATextArea.appendText(newLine);
        demoATextArea.appendText(newLine);
        demoATextArea.appendText("---------- END PGP MESSAGE ----------");
    }
    
    public void formatFull3()
    {
        String newLine = "\n";
        demoATextArea.setText("---------- BEGIN PGP MESSAGE ----------");
        demoATextArea.appendText(newLine);
        demoATextArea.appendText(newLine);
        demoATextArea.appendText("---------- BEGIN SYMETRIC KEY PACKET ----------");
        demoATextArea.appendText(newLine);
        demoATextArea.appendText(newLine);
        demoATextArea.appendText("--- Key Id of Recipient's Public Key ---"); 
        demoATextArea.appendText(newLine);
        demoATextArea.appendText(DemoData.demoEncryptionKeyId);
        demoATextArea.appendText(newLine);
        demoATextArea.appendText("--- Public Key Algorithm ---"); 
        demoATextArea.appendText(newLine);
        demoATextArea.appendText(DemoData.demoEncryptionPublicAlgorithm);
        demoATextArea.appendText(newLine);
        demoATextArea.appendText("--- Symetric Algorithm ---"); 
        demoATextArea.appendText(newLine);
        demoATextArea.appendText(DemoData.demoEncryptionSymetricAlgorithm);
        demoATextArea.appendText(newLine);
        demoATextArea.appendText("--- Session Key ---");
        demoATextArea.appendText(newLine);
        demoATextArea.appendText(DemoData.demoEncryptionSessionKey);
        demoATextArea.appendText(newLine);
        demoATextArea.appendText(newLine);
        demoATextArea.appendText("---------- END SYMETRIC KEY PACKET ----------");
        demoATextArea.appendText(newLine);
        demoATextArea.appendText(newLine);
        demoATextArea.appendText("------- BEGIN ENCRYPTED DATA PACKET -------");
        demoATextArea.appendText(newLine);
        demoATextArea.appendText(newLine);
        demoATextArea.appendText(DemoData.demoEncryptedData);
        demoATextArea.appendText(newLine);
        demoATextArea.appendText(newLine);
        demoATextArea.appendText("------- END ENCRYPTED DATA PACKET -------");
        demoATextArea.appendText(newLine);
        demoATextArea.appendText(newLine);
        demoATextArea.appendText("---------- END PGP MESSAGE ----------");
    }
    
    public void formatFull4()
    {
        String newLine = "\n";
        demoATextArea.setText("---------- BEGIN PGP MESSAGE ----------");
        demoATextArea.appendText(newLine);
        demoATextArea.appendText(newLine);
        demoATextArea.appendText("---------- BEGIN SYMETRIC KEY PACKET ----------");
        demoATextArea.appendText(newLine);
        demoATextArea.appendText(newLine);
        demoATextArea.appendText("--- Key Id of Recipient's Public Key ---"); 
        demoATextArea.appendText(newLine);
        demoATextArea.appendText(DemoData.demoEncryptionKeyId);
        demoATextArea.appendText(newLine);
        demoATextArea.appendText("--- Public Key Algorithm ---"); 
        demoATextArea.appendText(newLine);
        demoATextArea.appendText(DemoData.demoEncryptionPublicAlgorithm);
        demoATextArea.appendText(newLine);
        demoATextArea.appendText("--- Symetric Algorithm ---"); 
        demoATextArea.appendText(newLine);
        demoATextArea.appendText(DemoData.demoEncryptionSymetricAlgorithm);
        demoATextArea.appendText(newLine);
        demoATextArea.appendText("--- Session Key ---");
        demoATextArea.appendText(newLine);
        demoATextArea.appendText(DemoData.demoEncryptionSessionKey);
        demoATextArea.appendText(newLine);
        demoATextArea.appendText(newLine);
        demoATextArea.appendText("---------- END SYMETRIC KEY PACKET ----------");
        demoATextArea.appendText(newLine);
        demoATextArea.appendText(newLine);
        demoATextArea.appendText("------- BEGIN COMPRESSED DATA PACKET -------");
        demoATextArea.appendText(newLine);
        demoATextArea.appendText(newLine);
        demoATextArea.appendText(DemoData.demoCompressedData);
        demoATextArea.appendText(newLine);
        demoATextArea.appendText(newLine);
        demoATextArea.appendText("------- END COMPRESSED DATA PACKET -------");
        demoATextArea.appendText(newLine);
        demoATextArea.appendText(newLine);
        demoATextArea.appendText("---------- END PGP MESSAGE ----------");
    }
    
    public void formatFull5()
    {
        String newLine = "\n";
        demoATextArea.setText("---------- BEGIN PGP MESSAGE ----------");
        demoATextArea.appendText(newLine);
        demoATextArea.appendText(newLine);
        demoATextArea.appendText("---------- BEGIN SYMETRIC KEY PACKET ----------");
        demoATextArea.appendText(newLine);
        demoATextArea.appendText(newLine);
        demoATextArea.appendText("--- Key Id of Recipient's Public Key ---"); 
        demoATextArea.appendText(newLine);
        demoATextArea.appendText(DemoData.demoEncryptionKeyId);
        demoATextArea.appendText(newLine);
        demoATextArea.appendText("--- Public Key Algorithm ---"); 
        demoATextArea.appendText(newLine);
        demoATextArea.appendText(DemoData.demoEncryptionPublicAlgorithm);
        demoATextArea.appendText(newLine);
        demoATextArea.appendText("--- Symetric Algorithm ---"); 
        demoATextArea.appendText(newLine);
        demoATextArea.appendText(DemoData.demoEncryptionSymetricAlgorithm);
        demoATextArea.appendText(newLine);
        demoATextArea.appendText("--- Session Key ---");
        demoATextArea.appendText(newLine);
        demoATextArea.appendText(DemoData.demoEncryptionSessionKey);
        demoATextArea.appendText(newLine);
        demoATextArea.appendText(newLine);
        demoATextArea.appendText("---------- END SYMETRIC KEY PACKET ----------");
        demoATextArea.appendText(newLine);
        demoATextArea.appendText(newLine);
        demoATextArea.appendText("------- BEGIN SIGNATURE -------");
        demoATextArea.appendText(newLine);
        demoATextArea.appendText(newLine);
        demoATextArea.appendText("--- Timestamp ---");
        demoATextArea.appendText(newLine);
        demoATextArea.appendText(DemoData.demoSignatureTimestamp);
        demoATextArea.appendText(newLine);
        demoATextArea.appendText("--- Public Key Algorithm ---");
        demoATextArea.appendText(newLine);
        demoATextArea.appendText(DemoData.demoSignatureAlgorithm);
        demoATextArea.appendText(newLine);
        demoATextArea.appendText("--- Hash Algorithm ---");
        demoATextArea.appendText(newLine);
        demoATextArea.appendText(DemoData.demoHashAlgorithm);
        demoATextArea.appendText(newLine);
        demoATextArea.appendText("--- Key Id of Sender's Public Key ---");
        demoATextArea.appendText(newLine);
        demoATextArea.appendText(DemoData.demoSignatureKeyId);
        demoATextArea.appendText(newLine);
        demoATextArea.appendText("--- Leading Two Octets of Message Digest ---");
        demoATextArea.appendText(newLine);
        demoATextArea.appendText(DemoData.demoSignatureLeadingOctets);
        demoATextArea.appendText(newLine);
        demoATextArea.appendText("--- Message Digest ---");
        demoATextArea.appendText(newLine);
        demoATextArea.appendText(DemoData.demoSignatureMessageDigest);
        demoATextArea.appendText(newLine);
        demoATextArea.appendText(newLine);
        demoATextArea.appendText("------- END SIGNATURE -------");
        demoATextArea.appendText(newLine);
        demoATextArea.appendText(newLine);
        demoATextArea.appendText("------- BEGIN MESSAGE -------");
        demoATextArea.appendText(newLine);
        demoATextArea.appendText(newLine);
        demoATextArea.appendText("---- Filename ----");
        demoATextArea.appendText(newLine);
        demoATextArea.appendText(DemoData.demoMessageFilename);
        demoATextArea.appendText(newLine);
        demoATextArea.appendText("---- Timestamp ----");
        demoATextArea.appendText(newLine);
        demoATextArea.appendText(DemoData.demoMessageTimestamp);
        demoATextArea.appendText(newLine);
        demoATextArea.appendText("---- Data ----");
        demoATextArea.appendText(newLine);
        demoATextArea.appendText(DemoData.demoMessageData);
        demoATextArea.appendText(newLine);
        demoATextArea.appendText(newLine);
        demoATextArea.appendText("------- END MESSAGE -------");
        demoATextArea.appendText(newLine);
        demoATextArea.appendText(newLine);
        demoATextArea.appendText("---------- END PGP MESSAGE ----------");
    }
    
}
