
package scheme;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import util.SchemeData;
import util.SendMessageData;

/**
 * FXML Controller class
 *
 * @author Mirko
 */
public class EncryptSchemeController implements Initializable {

    
    @FXML private Label eSenderLabel;
    @FXML private Label eRecipientLabel;
    
    @FXML private Button eMessage;
    @FXML private Button eZip;
    @FXML private Button eCompress;
    @FXML private Button eEncryptMessage;
    @FXML private Button eKs;
    @FXML private Button eEncryptKs;
    @FXML private Button eKey;
    @FXML private Button ePlus;
    @FXML private Button eEncrypt;
    @FXML private Button eToR64;
    @FXML private Button eConvert;
    
    @FXML private Button eMessage1;
    @FXML private Button eFromR64;
    @FXML private Button eEncrypt1;
    @FXML private Button eDecryptKs;
    @FXML private Button eKey1;
    @FXML private Button eKs1;
    @FXML private Button eDecryptMessage;
    @FXML private Button eCompress1;
    @FXML private Button eZip1;
    @FXML private Button eMessage2;
    
    @FXML private Label e1;
    @FXML private Label e2;
    @FXML private Label e3;
    @FXML private Label e4;
    @FXML private Label e5;
    @FXML private Label e6;
    @FXML private Label e7;
    @FXML private Label e8;
    @FXML private Label e9;
    @FXML private Label e10;
    @FXML private Label e11;
    @FXML private Label e12;
    @FXML private Label e13;
    @FXML private Label e14;
    @FXML private Label e15;
    @FXML private Label e16;
    @FXML private Label e17;
    @FXML private Label e18;
    @FXML private Label e19;
    @FXML private Label e20;
    @FXML private Label e21;
    @FXML private Label e22;
    @FXML private Label e23;
    @FXML private Label e24;
    @FXML private Label e25;
    @FXML private Label e26;
    @FXML private Label e27;
    @FXML private Label e28;
    @FXML private Label e29;
    @FXML private Label e30;
    @FXML private Label e31;
    @FXML private Label e32;
    @FXML private Label e33;
    @FXML private Label e34;
    @FXML private Label e35;
    @FXML private Label e36;
    @FXML private Label e37;
    @FXML private Label e38;
    @FXML private Label e39;
    @FXML private Label e40;
    @FXML private Label e41;
    @FXML private Label e42;
    @FXML private Label e43;
    
    public static Button eeButton1;
    public static Button eeButton2;
    public static Button eeButton3;
    public static Button eeButton4;
    public static Button eeButton5;
    public static Button eeButton6;
    public static Button eeButton7;
    public static Button eeButton8;
    
    public static Label ee1;
    public static Label ee2;
    public static Label ee3;
    public static Label ee4;
    public static Label ee5;
    public static Label ee6;
    public static Label ee7;
    public static Label ee8;
    public static Label ee9;
    public static Label ee10;
    public static Label ee11;
    public static Label ee12;
    public static Label ee13;
    public static Label ee14;
    public static Label ee15;
    public static Label ee16;
    public static Label ee17;
    public static Label ee18;
    public static Label ee19;
    public static Label ee20;
    public static Label ee21;
    public static Label ee22;
    public static Label ee23;
    public static Label ee24;
    public static Label ee25;
    public static Label ee26;
    public static Label ee27;
    public static Label ee28;
    public static Label ee29;
    public static Label ee30;
    public static Label ee31;
    public static Label ee32;
    public static Label ee33;
    public static Label ee34;
    public static Label ee35;
    public static Label ee36;
    public static Label ee37;
    public static Label ee38;
    public static Label ee39;
    public static Label ee40;
    public static Label ee41;
    public static Label ee42;
    public static Label ee43;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        addTooltips();
        
        ee1 = e1;
        ee2 = e2;
        ee3 = e3;
        ee4 = e4;
        ee5 = e5;
        ee6 = e6;
        ee7 = e7;
        ee8 = e8;
        ee9 = e9;
        ee10 = e10; 
        ee11 = e11;
        ee12 = e12;
        ee13 = e13;
        ee14 = e14;
        ee15 = e15;
        ee16 = e16;
        ee17 = e17;
        ee18 = e18;
        ee19 = e19;
        ee20 = e20;
        ee21 = e21; 
        ee22 = e22;
        ee23 = e23;
        ee24 = e24;
        ee25 = e25;
        ee26 = e26;
        ee27 = e27;
        ee28 = e28;
        ee29 = e29;
        ee30 = e30;
        ee31 = e31;
        ee32 = e32;
        ee33 = e33;
        ee34 = e34;
        ee35 = e35;
        ee36 = e36;
        ee37 = e37;
        ee38 = e38;
        ee39 = e39;
        ee40 = e40;
        ee41 = e41;
        ee42 = e42;
        ee43 = e43;
        
        eeButton1 = eMessage;
        eeButton2 = eCompress;
        eeButton3 = eEncrypt;
        eeButton4 = eConvert;
        eeButton5 = eMessage1;
        eeButton6 = eEncrypt1;
        eeButton7 = eCompress1;
        eeButton8 = eMessage2;
        
        if(SchemeData.eLabel[1]) { e1.setTextFill(Color.RED); }
        if(SchemeData.eLabel[2]) { e2.setTextFill(Color.RED); }
        if(SchemeData.eLabel[3]) { e3.setTextFill(Color.RED); }
        if(SchemeData.eLabel[4]) { e4.setTextFill(Color.RED); }
        if(SchemeData.eLabel[5]) { e5.setTextFill(Color.RED); }
        if(SchemeData.eLabel[6]) { e6.setTextFill(Color.RED); }
        if(SchemeData.eLabel[7]) { e7.setTextFill(Color.RED); }
        if(SchemeData.eLabel[8]) { e8.setTextFill(Color.RED); }
        if(SchemeData.eLabel[9]) { e9.setTextFill(Color.RED); }
        if(SchemeData.eLabel[10]) { e10.setTextFill(Color.RED); }
        if(SchemeData.eLabel[11]) { e11.setTextFill(Color.RED); }
        if(SchemeData.eLabel[12]) { e12.setTextFill(Color.RED); }
        if(SchemeData.eLabel[13]) { e13.setTextFill(Color.RED); }
        if(SchemeData.eLabel[14]) { e14.setTextFill(Color.RED); }
        if(SchemeData.eLabel[15]) { e15.setTextFill(Color.RED); }
        if(SchemeData.eLabel[16]) { e16.setTextFill(Color.RED); }
        if(SchemeData.eLabel[17]) { e17.setTextFill(Color.RED); }
        if(SchemeData.eLabel[18]) { e18.setTextFill(Color.RED); }
        if(SchemeData.eLabel[19]) { e19.setTextFill(Color.RED); }
        if(SchemeData.eLabel[20]) { e20.setTextFill(Color.RED); }
        if(SchemeData.eLabel[21]) { e21.setTextFill(Color.RED); }
        if(SchemeData.eLabel[22]) { e22.setTextFill(Color.RED); }
        if(SchemeData.eLabel[23]) { e23.setTextFill(Color.RED); }
        if(SchemeData.eLabel[24]) { e24.setTextFill(Color.RED); }
        if(SchemeData.eLabel[25]) { e25.setTextFill(Color.RED); }
        if(SchemeData.eLabel[26]) { e26.setTextFill(Color.RED); }
        if(SchemeData.eLabel[27]) { e27.setTextFill(Color.RED); }
        if(SchemeData.eLabel[28]) { e28.setTextFill(Color.RED); }
        if(SchemeData.eLabel[29]) { e29.setTextFill(Color.RED); }
        if(SchemeData.eLabel[30]) { e30.setTextFill(Color.RED); }
        if(SchemeData.eLabel[31]) { e31.setTextFill(Color.RED); }
        if(SchemeData.eLabel[32]) { e32.setTextFill(Color.RED); }
        if(SchemeData.eLabel[33]) { e33.setTextFill(Color.RED); }
        if(SchemeData.eLabel[34]) { e34.setTextFill(Color.RED); }
        if(SchemeData.eLabel[35]) { e35.setTextFill(Color.RED); }
        if(SchemeData.eLabel[36]) { e36.setTextFill(Color.RED); }
        if(SchemeData.eLabel[37]) { e37.setTextFill(Color.RED); }
        if(SchemeData.eLabel[38]) { e38.setTextFill(Color.RED); }
        if(SchemeData.eLabel[39]) { e39.setTextFill(Color.RED); }
        if(SchemeData.eLabel[40]) { e40.setTextFill(Color.RED); }
        if(SchemeData.eLabel[41]) { e41.setTextFill(Color.RED); }
        if(SchemeData.eLabel[42]) { e42.setTextFill(Color.RED); }
        if(SchemeData.eLabel[43]) { e43.setTextFill(Color.RED); }
        
        Image messageImage = new Image("images/message.jpg");
        ImageView temp = new ImageView(messageImage);
        temp.setFitHeight(50);
        temp.setFitWidth(50);
        eMessage.setGraphic(temp);
        eMessage.setPadding(Insets.EMPTY);
        
        messageImage = new Image("images/message.jpg");
        temp = new ImageView(messageImage);
        temp.setFitHeight(50);
        temp.setFitWidth(50);
        eMessage2.setGraphic(temp);
        eMessage2.setPadding(Insets.EMPTY);
        
        messageImage = new Image("images/compress.jpg");
        temp = new ImageView(messageImage);
        temp.setFitHeight(50);
        temp.setFitWidth(50);
        eCompress.setGraphic(temp);
        eCompress.setPadding(Insets.EMPTY);
        
        messageImage = new Image("images/compress.jpg");
        temp = new ImageView(messageImage);
        temp.setFitHeight(50);
        temp.setFitWidth(50);
        eCompress1.setGraphic(temp);
        eCompress1.setPadding(Insets.EMPTY);
        
        messageImage = new Image("images/encrypt.jpg");
        temp = new ImageView(messageImage);
        temp.setFitHeight(50);
        temp.setFitWidth(50);
        eEncrypt.setGraphic(temp);
        eEncrypt.setPadding(Insets.EMPTY);
        
        messageImage = new Image("images/encrypt.jpg");
        temp = new ImageView(messageImage);
        temp.setFitHeight(50);
        temp.setFitWidth(50);
        eEncrypt1.setGraphic(temp);
        eEncrypt1.setPadding(Insets.EMPTY);
        
        messageImage = new Image("images/convert.jpg");
        temp = new ImageView(messageImage);
        temp.setFitHeight(50);
        temp.setFitWidth(50);
        eConvert.setGraphic(temp);
        eConvert.setPadding(Insets.EMPTY);
        
        messageImage = new Image("images/publickey.jpg");
        temp = new ImageView(messageImage);
        temp.setFitHeight(30);
        temp.setFitWidth(30);
        eKey.setGraphic(temp);
        eKey.setPadding(Insets.EMPTY);
        
        messageImage = new Image("images/publickey.jpg");
        temp = new ImageView(messageImage);
        temp.setFitHeight(30);
        temp.setFitWidth(30);
        eKey1.setGraphic(temp);
        eKey1.setPadding(Insets.EMPTY);
        
        messageImage = new Image("images/secretkey.jpg");
        temp = new ImageView(messageImage);
        temp.setFitHeight(30);
        temp.setFitWidth(30);
        eKs.setGraphic(temp);
        eKs.setPadding(Insets.EMPTY);
        
        messageImage = new Image("images/secretkey.jpg");
        temp = new ImageView(messageImage);
        temp.setFitHeight(30);
        temp.setFitWidth(30);
        eKs1.setGraphic(temp);
        eKs1.setPadding(Insets.EMPTY);
        
        if((SendMessageData.selectedMode == 200) || (SendMessageData.selectedMode == 210))
        {
            messageImage = new Image("images/encrypt.jpg");
            temp = new ImageView(messageImage);
            temp.setFitHeight(50);
            temp.setFitWidth(50);
            eMessage1.setGraphic(temp);
            eMessage1.setPadding(Insets.EMPTY);
        }
        
        if((SendMessageData.selectedMode == 201) || (SendMessageData.selectedMode == 211))
        {
            messageImage = new Image("images/convert.jpg");
            temp = new ImageView(messageImage);
            temp.setFitHeight(50);
            temp.setFitWidth(50);
            eMessage1.setGraphic(temp);
            eMessage1.setPadding(Insets.EMPTY);
        }
        
        if(SchemeData.eButton[1])
        {
            String style = "-fx-border-color: red; -fx-border-width: 3 3 3 3;";
            eMessage.setStyle(style);
        }
        
        if(SchemeData.eButton[2])
        {
            String style = "-fx-border-color: red; -fx-border-width: 3 3 3 3;";
            eCompress.setStyle(style);
        }
        
        if(SchemeData.eButton[3])
        {
            String style = "-fx-border-color: red; -fx-border-width: 3 3 3 3;";
            eEncrypt.setStyle(style);
        }
        
        if(SchemeData.eButton[4])
        {
            String style = "-fx-border-color: red; -fx-border-width: 3 3 3 3;";
            eConvert.setStyle(style);
        }
        
        if(SchemeData.eButton[5])
        {
            String style = "-fx-border-color: red; -fx-border-width: 3 3 3 3;";
            eMessage1.setStyle(style);
        }
        
        if(SchemeData.eButton[6])
        {
            String style = "-fx-border-color: red; -fx-border-width: 3 3 3 3;";
            eEncrypt1.setStyle(style);
        }
        
        if(SchemeData.eButton[7])
        {
            String style = "-fx-border-color: red; -fx-border-width: 3 3 3 3;";
            eCompress1.setStyle(style);
        }
        
        if(SchemeData.eButton[8])
        {
            String style = "-fx-border-color: red; -fx-border-width: 3 3 3 3;";
            eMessage2.setStyle(style);
        }
        
    }

    
    public void addTooltips()
    {
        Tooltip t1 = new Tooltip();
        t1.setText("Compress message");
        eZip.setTooltip(t1);
        
        Tooltip t2 = new Tooltip();
        t2.setText("Encrypt message using Session key");
        eEncryptMessage.setTooltip(t2);
        
        Tooltip t3 = new Tooltip();
        t3.setText("Encrypt Session key using recipient's public key");
        eEncryptKs.setTooltip(t3);
        
        Tooltip t4 = new Tooltip();
        t4.setText("Concatenate Session key component and encrypted message");
        ePlus.setTooltip(t4);
        
        Tooltip t5 = new Tooltip();
        t5.setText("Convert data to Radix 64");
        eToR64.setTooltip(t5);
        
        Tooltip t6 = new Tooltip();
        t6.setText("Convert data from Radix 64");
        eFromR64.setTooltip(t6);
        
        Tooltip t7 = new Tooltip();
        t7.setText("Decrypt Session key using Recipient's private key");
        eDecryptKs.setTooltip(t7);
        
        Tooltip t8 = new Tooltip();
        t8.setText("Decrypt message using Session key");
        eDecryptMessage.setTooltip(t8);
        
        Tooltip t9 = new Tooltip();
        t9.setText("Decompress message");
        eZip1.setTooltip(t9);
    }
    
}
