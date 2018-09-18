
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
public class SignSchemeController implements Initializable {

    @FXML private Label sSenderLabel;
    @FXML private Label sRecipientLabel;
    
    @FXML private Button sMessage;
    @FXML private Button sHash;
    @FXML private Button sEncrypt;
    @FXML private Button sKey;
    @FXML private Button sPlus;
    @FXML private Button sSign;
    @FXML private Button sZip;
    @FXML private Button sCompress;
    @FXML private Button sToR64;
    @FXML private Button sConvert;
    
    @FXML private Button sMessage1;
    @FXML private Button sFromR64;
    @FXML private Button sCompress1;
    @FXML private Button sZip1;
    @FXML private Button sSign1;
    @FXML private Button sHash1;
    @FXML private Button sDecrypt;
    @FXML private Button sKey1;
    @FXML private Button sCompare;
    
    @FXML private Label s1;
    @FXML private Label s2;
    @FXML private Label s3;
    @FXML private Label s4;
    @FXML private Label s5;
    @FXML private Label s6;
    @FXML private Label s7;
    @FXML private Label s8;
    @FXML private Label s9;
    @FXML private Label s10;
    @FXML private Label s11;
    @FXML private Label s12;
    @FXML private Label s13;
    @FXML private Label s14;
    @FXML private Label s15;
    @FXML private Label s16;
    @FXML private Label s17;
    @FXML private Label s18;
    @FXML private Label s19;
    @FXML private Label s20;
    @FXML private Label s21;
    @FXML private Label s22;
    @FXML private Label s23;
    @FXML private Label s24;
    @FXML private Label s25;
    @FXML private Label s26;
    @FXML private Label s27;
    @FXML private Label s28;
    @FXML private Label s29;
    @FXML private Label s30;
    @FXML private Label s31;
    @FXML private Label s32;
    @FXML private Label s33;
    @FXML private Label s34;
    @FXML private Label s35;
    @FXML private Label s36;
    @FXML private Label s37;
    @FXML private Label s38;
    @FXML private Label s39;
    @FXML private Label s40;
    @FXML private Label s41;
    
    public static Button ssButton1;
    public static Button ssButton2;
    public static Button ssButton3;
    public static Button ssButton4;
    public static Button ssButton5;
    public static Button ssButton6;
    public static Button ssButton7;
    
    public static Label ss1;
    public static Label ss2;
    public static Label ss3;
    public static Label ss4;
    public static Label ss5;
    public static Label ss6;
    public static Label ss7;
    public static Label ss8;
    public static Label ss9;
    public static Label ss10;
    public static Label ss11;
    public static Label ss12;
    public static Label ss13;
    public static Label ss14;
    public static Label ss15;
    public static Label ss16;
    public static Label ss17;
    public static Label ss18;
    public static Label ss19;
    public static Label ss20;
    public static Label ss21;
    public static Label ss22;
    public static Label ss23;
    public static Label ss24;
    public static Label ss25;
    public static Label ss26;
    public static Label ss27;
    public static Label ss28;
    public static Label ss29;
    public static Label ss30;
    public static Label ss31;
    public static Label ss32;
    public static Label ss33;
    public static Label ss34;
    public static Label ss35;
    public static Label ss36;
    public static Label ss37;
    public static Label ss38;
    public static Label ss39;
    public static Label ss40;
    public static Label ss41;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        addTooltips();
        
        ss1 = s1;
        ss2 = s2;
        ss3 = s3;
        ss4 = s4;
        ss5 = s5;
        ss6 = s6;
        ss7 = s7;
        ss8 = s8;
        ss9 = s9;
        ss10 = s10; 
        ss11 = s11;
        ss12 = s12;
        ss13 = s13;
        ss14 = s14;
        ss15 = s15;
        ss16 = s16;
        ss17 = s17;
        ss18 = s18;
        ss19 = s19;
        ss20 = s20;
        ss21 = s21; 
        ss22 = s22;
        ss23 = s23;
        ss24 = s24;
        ss25 = s25;
        ss26 = s26;
        ss27 = s27;
        ss28 = s28;
        ss29 = s29;
        ss30 = s30;
        ss31 = s31;
        ss32 = s32;
        ss33 = s33;
        ss34 = s34;
        ss35 = s35;
        ss36 = s36;
        ss37 = s37;
        ss38 = s38;
        ss39 = s39;
        ss40 = s40;
        ss41 = s41;
        
        ssButton1 = sMessage;
        ssButton2 = sSign;
        ssButton3 = sCompress;
        ssButton4 = sConvert;
        ssButton5 = sMessage1;
        ssButton6 = sCompress1;
        ssButton7 = sSign1; 
        
        if(SchemeData.sLabel[1]) { s1.setTextFill(Color.RED); }
        if(SchemeData.sLabel[2]) { s2.setTextFill(Color.RED); }
        if(SchemeData.sLabel[3]) { s3.setTextFill(Color.RED); }
        if(SchemeData.sLabel[4]) { s4.setTextFill(Color.RED); }
        if(SchemeData.sLabel[5]) { s5.setTextFill(Color.RED); }
        if(SchemeData.sLabel[6]) { s6.setTextFill(Color.RED); }
        if(SchemeData.sLabel[7]) { s7.setTextFill(Color.RED); }
        if(SchemeData.sLabel[8]) { s8.setTextFill(Color.RED); }
        if(SchemeData.sLabel[9]) { s9.setTextFill(Color.RED); }
        if(SchemeData.sLabel[10]) { s10.setTextFill(Color.RED); }
        if(SchemeData.sLabel[11]) { s11.setTextFill(Color.RED); }
        if(SchemeData.sLabel[12]) { s12.setTextFill(Color.RED); }
        if(SchemeData.sLabel[13]) { s13.setTextFill(Color.RED); }
        if(SchemeData.sLabel[14]) { s14.setTextFill(Color.RED); }
        if(SchemeData.sLabel[15]) { s15.setTextFill(Color.RED); }
        if(SchemeData.sLabel[16]) { s16.setTextFill(Color.RED); }
        if(SchemeData.sLabel[17]) { s17.setTextFill(Color.RED); }
        if(SchemeData.sLabel[18]) { s18.setTextFill(Color.RED); }
        if(SchemeData.sLabel[19]) { s19.setTextFill(Color.RED); }
        if(SchemeData.sLabel[20]) { s20.setTextFill(Color.RED); }
        if(SchemeData.sLabel[21]) { s21.setTextFill(Color.RED); }
        if(SchemeData.sLabel[22]) { s22.setTextFill(Color.RED); }
        if(SchemeData.sLabel[23]) { s23.setTextFill(Color.RED); }
        if(SchemeData.sLabel[24]) { s24.setTextFill(Color.RED); }
        if(SchemeData.sLabel[25]) { s25.setTextFill(Color.RED); }
        if(SchemeData.sLabel[26]) { s26.setTextFill(Color.RED); }
        if(SchemeData.sLabel[27]) { s27.setTextFill(Color.RED); }
        if(SchemeData.sLabel[28]) { s28.setTextFill(Color.RED); }
        if(SchemeData.sLabel[29]) { s29.setTextFill(Color.RED); }
        if(SchemeData.sLabel[30]) { s30.setTextFill(Color.RED); }
        if(SchemeData.sLabel[31]) { s31.setTextFill(Color.RED); }
        if(SchemeData.sLabel[32]) { s32.setTextFill(Color.RED); }
        if(SchemeData.sLabel[33]) { s33.setTextFill(Color.RED); }
        if(SchemeData.sLabel[34]) { s34.setTextFill(Color.RED); }
        if(SchemeData.sLabel[35]) { s35.setTextFill(Color.RED); }
        if(SchemeData.sLabel[36]) { s36.setTextFill(Color.RED); }
        if(SchemeData.sLabel[37]) { s37.setTextFill(Color.RED); }
        if(SchemeData.sLabel[38]) { s38.setTextFill(Color.RED); }
        if(SchemeData.sLabel[39]) { s39.setTextFill(Color.RED); }
        if(SchemeData.sLabel[40]) { s40.setTextFill(Color.RED); }
        if(SchemeData.sLabel[41]) { s41.setTextFill(Color.RED); }
        
        Image messageImage = new Image("images/message.jpg");
        ImageView temp = new ImageView(messageImage);
        temp.setFitHeight(50);
        temp.setFitWidth(50);
        sMessage.setGraphic(temp);
        sMessage.setPadding(Insets.EMPTY);
        
        messageImage = new Image("images/sign.jpg");
        temp = new ImageView(messageImage);
        temp.setFitHeight(50);
        temp.setFitWidth(50);
        sSign.setGraphic(temp);
        sSign.setPadding(Insets.EMPTY);
        
        messageImage = new Image("images/compress.jpg");
        temp = new ImageView(messageImage);
        temp.setFitHeight(50);
        temp.setFitWidth(50);
        sCompress.setGraphic(temp);
        sCompress.setPadding(Insets.EMPTY);
        
        messageImage = new Image("images/convert.jpg");
        temp = new ImageView(messageImage);
        temp.setFitHeight(50);
        temp.setFitWidth(50);
        sConvert.setGraphic(temp);
        sConvert.setPadding(Insets.EMPTY);
        
        messageImage = new Image("images/publickey.jpg");
        temp = new ImageView(messageImage);
        temp.setFitHeight(30);
        temp.setFitWidth(30);
        sKey.setGraphic(temp);
        sKey.setPadding(Insets.EMPTY);
        
        messageImage = new Image("images/sign.jpg");
        temp = new ImageView(messageImage);
        temp.setFitHeight(50);
        temp.setFitWidth(50);
        sSign1.setGraphic(temp);
        sSign1.setPadding(Insets.EMPTY);
        
        messageImage = new Image("images/publickey.jpg");
        temp = new ImageView(messageImage);
        temp.setFitHeight(30);
        temp.setFitWidth(30);
        sKey1.setGraphic(temp);
        sKey1.setPadding(Insets.EMPTY);
        
        if(SendMessageData.selectedMode == 100)
        {
            messageImage = new Image("images/sign.jpg");
            temp = new ImageView(messageImage);
            temp.setFitHeight(50);
            temp.setFitWidth(50);
            sMessage1.setGraphic(temp);
            sMessage1.setPadding(Insets.EMPTY);   
        }
        
        if(SendMessageData.selectedMode == 101)
        {
            messageImage = new Image("images/convert.jpg");
            temp = new ImageView(messageImage);
            temp.setFitHeight(50);
            temp.setFitWidth(50);
            sMessage1.setGraphic(temp);
            sMessage1.setPadding(Insets.EMPTY);
            
            messageImage = new Image("images/sign.jpg");
            temp = new ImageView(messageImage);
            temp.setFitHeight(50);
            temp.setFitWidth(50);
            sCompress1.setGraphic(temp);
            sCompress1.setPadding(Insets.EMPTY);
        }
        
        if(SendMessageData.selectedMode == 110)
        {
            messageImage = new Image("images/compress.jpg");
            temp = new ImageView(messageImage);
            temp.setFitHeight(50);
            temp.setFitWidth(50);
            sMessage1.setGraphic(temp);
            sMessage1.setPadding(Insets.EMPTY);
        }
        
        if(SendMessageData.selectedMode == 111)
        {
            messageImage = new Image("images/convert.jpg");
            temp = new ImageView(messageImage);
            temp.setFitHeight(50);
            temp.setFitWidth(50);
            sMessage1.setGraphic(temp);
            sMessage1.setPadding(Insets.EMPTY);
            
            messageImage = new Image("images/compress.jpg");
            temp = new ImageView(messageImage);
            temp.setFitHeight(50);
            temp.setFitWidth(50);
            sCompress1.setGraphic(temp);
            sCompress1.setPadding(Insets.EMPTY);
        }
        
        if(SchemeData.sButton[1])
        {
            String style = "-fx-border-color: red; -fx-border-width: 3 3 3 3;";
            sMessage.setStyle(style);
        }
        
        if(SchemeData.sButton[2])
        {
            String style = "-fx-border-color: red; -fx-border-width: 3 3 3 3;";
            sSign.setStyle(style);
        }
        
        if(SchemeData.sButton[3])
        {
            String style = "-fx-border-color: red; -fx-border-width: 3 3 3 3;";
            sCompress.setStyle(style);
        }
        
        if(SchemeData.sButton[4])
        {
            String style = "-fx-border-color: red; -fx-border-width: 3 3 3 3;";
            sConvert.setStyle(style);
        }
        
        if(SchemeData.sButton[5])
        {
            String style = "-fx-border-color: red; -fx-border-width: 3 3 3 3;";
            sMessage1.setStyle(style);
        }
        
        if(SchemeData.sButton[6])
        {
            String style = "-fx-border-color: red; -fx-border-width: 3 3 3 3;";
            sCompress1.setStyle(style);
        }
        
        if(SchemeData.sButton[7])
        {
            String style = "-fx-border-color: red; -fx-border-width: 3 3 3 3;";
            sSign1.setStyle(style);
        }
        
    }    
    
    
    public void addTooltips()
    {
        Tooltip t1 = new Tooltip();
        t1.setText("Generate hash code of the message");
        sHash.setTooltip(t1);
        
        Tooltip t2 = new Tooltip();
        t2.setText("Encrypt hash code using sender's private key");
        sEncrypt.setTooltip(t2);
        
        Tooltip t3 = new Tooltip();
        t3.setText("Concatenate Signature component and message");
        sPlus.setTooltip(t3);
        
        Tooltip t4 = new Tooltip();
        t4.setText("Compress data");
        sZip.setTooltip(t4);
        
        Tooltip t5 = new Tooltip();
        t5.setText("Convert data to Radix 64");
        sToR64.setTooltip(t5);
        
        Tooltip t6 = new Tooltip();
        t6.setText("Convert data from Radix 64");
        sFromR64.setTooltip(t6);
        
        Tooltip t7 = new Tooltip();
        t7.setText("Decompress data");
        sZip1.setTooltip(t7);
        
        Tooltip t8 = new Tooltip();
        t8.setText("Generate hash code of the received message");
        sHash1.setTooltip(t8);
        
        Tooltip t9 = new Tooltip();
        t9.setText("Decrypt hash code from the Signature component using sender's public key");
        sDecrypt.setTooltip(t9);
        
        Tooltip t10 = new Tooltip();
        t10.setText("Compare generated hash code and one received in the Signature component");
        sCompare.setTooltip(t10);
    }
    
}
