
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
public class SignAndEncryptSchemeController implements Initializable {

    
    @FXML private Label bSenderLabel;
    @FXML private Label bRecipientLabel;
    
    @FXML private Button bMessage;
    @FXML private Button bHash;
    @FXML private Button bEncrypt;
    @FXML private Button bKey;
    @FXML private Button bPlus;
    @FXML private Button bSign;
    @FXML private Button bZip;
    @FXML private Button bCompress;
    @FXML private Button bEncryptMessage;
    @FXML private Button bKs;
    @FXML private Button bEncryptKs;
    @FXML private Button bKey1;
    @FXML private Button bPlus1;
    @FXML private Button bEncrypt1;
    @FXML private Button bToR64;
    @FXML private Button bConvert;
    
    @FXML private Button bMessage1;
    @FXML private Button bFromR64;
    @FXML private Button bEncrypt2;
    @FXML private Button bDecryptKs;
    @FXML private Button bKey2;
    @FXML private Button bDecryptMessage;
    @FXML private Button bKs1;
    @FXML private Button bCompress1;
    @FXML private Button bZip1;
    @FXML private Button bSign1;
    @FXML private Button bHash1;
    @FXML private Button bDecrypt;
    @FXML private Button bKey3;
    @FXML private Button bCompare;
    
    @FXML private Label b1;
    @FXML private Label b2;
    @FXML private Label b3;
    @FXML private Label b4;
    @FXML private Label b5;
    @FXML private Label b6;
    @FXML private Label b7;
    @FXML private Label b8;
    @FXML private Label b9;
    @FXML private Label b10;
    @FXML private Label b11;
    @FXML private Label b12;
    @FXML private Label b13;
    @FXML private Label b14;
    @FXML private Label b15;
    @FXML private Label b16;
    @FXML private Label b17;
    @FXML private Label b18;
    @FXML private Label b19;
    @FXML private Label b20;
    @FXML private Label b21;
    @FXML private Label b22;
    @FXML private Label b23;
    @FXML private Label b24;
    @FXML private Label b25;
    @FXML private Label b26;
    @FXML private Label b27;
    @FXML private Label b28;
    @FXML private Label b29;
    @FXML private Label b30;
    @FXML private Label b31;
    @FXML private Label b32;
    @FXML private Label b33;
    @FXML private Label b34;
    @FXML private Label b35;
    @FXML private Label b36;
    @FXML private Label b37;
    @FXML private Label b38;
    @FXML private Label b39;
    @FXML private Label b40;
    @FXML private Label b41;
    @FXML private Label b42;
    @FXML private Label b43;
    @FXML private Label b44;
    @FXML private Label b45;
    @FXML private Label b46;
    @FXML private Label b47;
    @FXML private Label b48;
    @FXML private Label b49;
    @FXML private Label b50;
    @FXML private Label b51;
    @FXML private Label b52;
    @FXML private Label b53;
    @FXML private Label b54;
    @FXML private Label b55;
    @FXML private Label b56;
    @FXML private Label b57;
    @FXML private Label b58;
    @FXML private Label b59;
    
    public static Button bbButton1;
    public static Button bbButton2;
    public static Button bbButton3;
    public static Button bbButton4;
    public static Button bbButton5;
    public static Button bbButton6;
    public static Button bbButton7;
    public static Button bbButton8;
    public static Button bbButton9;
    
    public static Label bb1;
    public static Label bb2;
    public static Label bb3;
    public static Label bb4;
    public static Label bb5;
    public static Label bb6;
    public static Label bb7;
    public static Label bb8;
    public static Label bb9;
    public static Label bb10;
    public static Label bb11;
    public static Label bb12;
    public static Label bb13;
    public static Label bb14;
    public static Label bb15;
    public static Label bb16;
    public static Label bb17;
    public static Label bb18;
    public static Label bb19;
    public static Label bb20;
    public static Label bb21;
    public static Label bb22;
    public static Label bb23;
    public static Label bb24;
    public static Label bb25;
    public static Label bb26;
    public static Label bb27;
    public static Label bb28;
    public static Label bb29;
    public static Label bb30;
    public static Label bb31;
    public static Label bb32;
    public static Label bb33;
    public static Label bb34;
    public static Label bb35;
    public static Label bb36;
    public static Label bb37;
    public static Label bb38;
    public static Label bb39;
    public static Label bb40;
    public static Label bb41;
    public static Label bb42;
    public static Label bb43;
    public static Label bb44;
    public static Label bb45;
    public static Label bb46;
    public static Label bb47;
    public static Label bb48;
    public static Label bb49;
    public static Label bb50;
    public static Label bb51;
    public static Label bb52;
    public static Label bb53;
    public static Label bb54;
    public static Label bb55;
    public static Label bb56;
    public static Label bb57;
    public static Label bb58;
    public static Label bb59;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        addTooltips();
        
        bb1 = b1;
        bb2 = b2;
        bb3 = b3;
        bb4 = b4;
        bb5 = b5;
        bb6 = b6;
        bb7 = b7;
        bb8 = b8;
        bb9 = b9;
        bb10 = b10; 
        bb11 = b11;
        bb12 = b12;
        bb13 = b13;
        bb14 = b14;
        bb15 = b15;
        bb16 = b16;
        bb17 = b17;
        bb18 = b18;
        bb19 = b19;
        bb20 = b20;
        bb21 = b21; 
        bb22 = b22;
        bb23 = b23;
        bb24 = b24;
        bb25 = b25;
        bb26 = b26;
        bb27 = b27;
        bb28 = b28;
        bb29 = b29;
        bb30 = b30;
        bb31 = b31;
        bb32 = b32;
        bb33 = b33;
        bb34 = b34;
        bb35 = b35;
        bb36 = b36;
        bb37 = b37;
        bb38 = b38;
        bb39 = b39;
        bb40 = b40;
        bb41 = b41;
        bb42 = b42;
        bb43 = b43;
        bb44 = b44;
        bb45 = b45;
        bb46 = b46;
        bb47 = b47;
        bb48 = b48;
        bb49 = b49;
        bb50 = b50;
        bb51 = b51; 
        bb52 = b52;
        bb53 = b53;
        bb54 = b54;
        bb55 = b55;
        bb56 = b56;
        bb57 = b57;
        bb58 = b58;
        bb59 = b59;
        
        bbButton1 = bMessage;
        bbButton2 = bSign;
        bbButton3 = bCompress;
        bbButton4 = bEncrypt1;
        bbButton5 = bConvert;
        bbButton6 = bMessage1;
        bbButton7 = bEncrypt2;
        bbButton8 = bCompress1;
        bbButton9 = bSign1;
        
        if(SchemeData.bLabel[1]) { b1.setTextFill(Color.RED); }
        if(SchemeData.bLabel[2]) { b2.setTextFill(Color.RED); }
        if(SchemeData.bLabel[3]) { b3.setTextFill(Color.RED); }
        if(SchemeData.bLabel[4]) { b4.setTextFill(Color.RED); }
        if(SchemeData.bLabel[5]) { b5.setTextFill(Color.RED); }
        if(SchemeData.bLabel[6]) { b6.setTextFill(Color.RED); }
        if(SchemeData.bLabel[7]) { b7.setTextFill(Color.RED); }
        if(SchemeData.bLabel[8]) { b8.setTextFill(Color.RED); }
        if(SchemeData.bLabel[9]) { b9.setTextFill(Color.RED); }
        if(SchemeData.bLabel[10]) { b10.setTextFill(Color.RED); }
        if(SchemeData.bLabel[11]) { b11.setTextFill(Color.RED); }
        if(SchemeData.bLabel[12]) { b12.setTextFill(Color.RED); }
        if(SchemeData.bLabel[13]) { b13.setTextFill(Color.RED); }
        if(SchemeData.bLabel[14]) { b14.setTextFill(Color.RED); }
        if(SchemeData.bLabel[15]) { b15.setTextFill(Color.RED); }
        if(SchemeData.bLabel[16]) { b16.setTextFill(Color.RED); }
        if(SchemeData.bLabel[17]) { b17.setTextFill(Color.RED); }
        if(SchemeData.bLabel[18]) { b18.setTextFill(Color.RED); }
        if(SchemeData.bLabel[19]) { b19.setTextFill(Color.RED); }
        if(SchemeData.bLabel[20]) { b20.setTextFill(Color.RED); }
        if(SchemeData.bLabel[21]) { b21.setTextFill(Color.RED); }
        if(SchemeData.bLabel[22]) { b22.setTextFill(Color.RED); }
        if(SchemeData.bLabel[23]) { b23.setTextFill(Color.RED); }
        if(SchemeData.bLabel[24]) { b24.setTextFill(Color.RED); }
        if(SchemeData.bLabel[25]) { b25.setTextFill(Color.RED); }
        if(SchemeData.bLabel[26]) { b26.setTextFill(Color.RED); }
        if(SchemeData.bLabel[27]) { b27.setTextFill(Color.RED); }
        if(SchemeData.bLabel[28]) { b28.setTextFill(Color.RED); }
        if(SchemeData.bLabel[29]) { b29.setTextFill(Color.RED); }
        if(SchemeData.bLabel[30]) { b30.setTextFill(Color.RED); }
        if(SchemeData.bLabel[31]) { b31.setTextFill(Color.RED); }
        if(SchemeData.bLabel[32]) { b32.setTextFill(Color.RED); }
        if(SchemeData.bLabel[33]) { b33.setTextFill(Color.RED); }
        if(SchemeData.bLabel[34]) { b34.setTextFill(Color.RED); }
        if(SchemeData.bLabel[35]) { b35.setTextFill(Color.RED); }
        if(SchemeData.bLabel[36]) { b36.setTextFill(Color.RED); }
        if(SchemeData.bLabel[37]) { b37.setTextFill(Color.RED); }
        if(SchemeData.bLabel[38]) { b38.setTextFill(Color.RED); }
        if(SchemeData.bLabel[39]) { b39.setTextFill(Color.RED); }
        if(SchemeData.bLabel[40]) { b40.setTextFill(Color.RED); }
        if(SchemeData.bLabel[41]) { b41.setTextFill(Color.RED); }
        if(SchemeData.bLabel[42]) { b42.setTextFill(Color.RED); }
        if(SchemeData.bLabel[43]) { b43.setTextFill(Color.RED); }
        if(SchemeData.bLabel[44]) { b44.setTextFill(Color.RED); }
        if(SchemeData.bLabel[45]) { b45.setTextFill(Color.RED); }
        if(SchemeData.bLabel[46]) { b46.setTextFill(Color.RED); }
        if(SchemeData.bLabel[47]) { b47.setTextFill(Color.RED); }
        if(SchemeData.bLabel[48]) { b48.setTextFill(Color.RED); }
        if(SchemeData.bLabel[49]) { b49.setTextFill(Color.RED); }
        if(SchemeData.bLabel[50]) { b50.setTextFill(Color.RED); }
        if(SchemeData.bLabel[51]) { b51.setTextFill(Color.RED); }
        if(SchemeData.bLabel[52]) { b52.setTextFill(Color.RED); }
        if(SchemeData.bLabel[53]) { b53.setTextFill(Color.RED); }
        if(SchemeData.bLabel[54]) { b54.setTextFill(Color.RED); }
        if(SchemeData.bLabel[55]) { b55.setTextFill(Color.RED); }
        if(SchemeData.bLabel[56]) { b56.setTextFill(Color.RED); }
        if(SchemeData.bLabel[57]) { b57.setTextFill(Color.RED); }
        if(SchemeData.bLabel[58]) { b58.setTextFill(Color.RED); }
        if(SchemeData.bLabel[59]) { b59.setTextFill(Color.RED); }
        
        Image messageImage = new Image("images/message.jpg");
        ImageView temp = new ImageView(messageImage);
        temp.setFitHeight(50);
        temp.setFitWidth(50);
        bMessage.setGraphic(temp);
        bMessage.setPadding(Insets.EMPTY);
        
        messageImage = new Image("images/sign.jpg");
        temp = new ImageView(messageImage);
        temp.setFitHeight(50);
        temp.setFitWidth(50);
        bSign.setGraphic(temp);
        bSign.setPadding(Insets.EMPTY);
        
        messageImage = new Image("images/sign.jpg");
        temp = new ImageView(messageImage);
        temp.setFitHeight(50);
        temp.setFitWidth(50);
        bSign1.setGraphic(temp);
        bSign1.setPadding(Insets.EMPTY);
        
        messageImage = new Image("images/compress.jpg");
        temp = new ImageView(messageImage);
        temp.setFitHeight(50);
        temp.setFitWidth(50);
        bCompress.setGraphic(temp);
        bCompress.setPadding(Insets.EMPTY);
        
        messageImage = new Image("images/compress.jpg");
        temp = new ImageView(messageImage);
        temp.setFitHeight(50);
        temp.setFitWidth(50);
        bCompress1.setGraphic(temp);
        bCompress1.setPadding(Insets.EMPTY);
        
        messageImage = new Image("images/convert.jpg");
        temp = new ImageView(messageImage);
        temp.setFitHeight(50);
        temp.setFitWidth(50);
        bConvert.setGraphic(temp);
        bConvert.setPadding(Insets.EMPTY);
        
        messageImage = new Image("images/encrypt.jpg");
        temp = new ImageView(messageImage);
        temp.setFitHeight(50);
        temp.setFitWidth(50);
        bEncrypt1.setGraphic(temp);
        bEncrypt1.setPadding(Insets.EMPTY);
        
        messageImage = new Image("images/encrypt.jpg");
        temp = new ImageView(messageImage);
        temp.setFitHeight(50);
        temp.setFitWidth(50);
        bEncrypt2.setGraphic(temp);
        bEncrypt2.setPadding(Insets.EMPTY);
        
        messageImage = new Image("images/publickey.jpg");
        temp = new ImageView(messageImage);
        temp.setFitHeight(30);
        temp.setFitWidth(30);
        bKey.setGraphic(temp);
        bKey.setPadding(Insets.EMPTY);
        
        messageImage = new Image("images/publickey.jpg");
        temp = new ImageView(messageImage);
        temp.setFitHeight(30);
        temp.setFitWidth(30);
        bKey1.setGraphic(temp);
        bKey1.setPadding(Insets.EMPTY);
        
        messageImage = new Image("images/publickey.jpg");
        temp = new ImageView(messageImage);
        temp.setFitHeight(30);
        temp.setFitWidth(30);
        bKey2.setGraphic(temp);
        bKey2.setPadding(Insets.EMPTY);
        
        messageImage = new Image("images/publickey.jpg");
        temp = new ImageView(messageImage);
        temp.setFitHeight(30);
        temp.setFitWidth(30);
        bKey3.setGraphic(temp);
        bKey3.setPadding(Insets.EMPTY);
        
        messageImage = new Image("images/secretkey.jpg");
        temp = new ImageView(messageImage);
        temp.setFitHeight(30);
        temp.setFitWidth(30);
        bKs.setGraphic(temp);
        bKs.setPadding(Insets.EMPTY);
        
        messageImage = new Image("images/secretkey.jpg");
        temp = new ImageView(messageImage);
        temp.setFitHeight(30);
        temp.setFitWidth(30);
        bKs1.setGraphic(temp);
        bKs1.setPadding(Insets.EMPTY);
        
        if((SendMessageData.selectedMode == 300) || (SendMessageData.selectedMode == 310))
        {
            messageImage = new Image("images/encrypt.jpg");
            temp = new ImageView(messageImage);
            temp.setFitHeight(50);
            temp.setFitWidth(50);
            bMessage1.setGraphic(temp);
            bMessage1.setPadding(Insets.EMPTY);
        }
        
        if((SendMessageData.selectedMode == 301) || (SendMessageData.selectedMode == 311) || (SendMessageData.selectedMode == 0))
        {
            messageImage = new Image("images/convert.jpg");
            temp = new ImageView(messageImage);
            temp.setFitHeight(50);
            temp.setFitWidth(50);
            bMessage1.setGraphic(temp);
            bMessage1.setPadding(Insets.EMPTY);
        }
        
        
        if(SchemeData.bButton[1])
        {
            String style = "-fx-border-color: red; -fx-border-width: 3 3 3 3;";
            bMessage.setStyle(style);
        }
        
        if(SchemeData.bButton[2])
        {
            String style = "-fx-border-color: red; -fx-border-width: 3 3 3 3;";
            bSign.setStyle(style);
        }
        
        if(SchemeData.bButton[3])
        {
            String style = "-fx-border-color: red; -fx-border-width: 3 3 3 3;";
            bCompress.setStyle(style);
        }
        
        if(SchemeData.bButton[4])
        {
            String style = "-fx-border-color: red; -fx-border-width: 3 3 3 3;";
            bEncrypt1.setStyle(style);
        }
        
        if(SchemeData.bButton[5])
        {
            String style = "-fx-border-color: red; -fx-border-width: 3 3 3 3;";
            bConvert.setStyle(style);
        }
        
        if(SchemeData.bButton[6])
        {
            String style = "-fx-border-color: red; -fx-border-width: 3 3 3 3;";
            bMessage1.setStyle(style);
        }
        
        if(SchemeData.bButton[7])
        {
            String style = "-fx-border-color: red; -fx-border-width: 3 3 3 3;";
            bEncrypt2.setStyle(style);
        }
        
        if(SchemeData.bButton[8])
        {
            String style = "-fx-border-color: red; -fx-border-width: 3 3 3 3;";
            bCompress1.setStyle(style);
        }
        
        if(SchemeData.bButton[9])
        {
            String style = "-fx-border-color: red; -fx-border-width: 3 3 3 3;";
            bSign1.setStyle(style);
        }
        
    }


    public void addTooltips()
    {
        Tooltip t1 = new Tooltip();
        t1.setText("Generate hash code of the message");
        bHash.setTooltip(t1);
        
        Tooltip t2 = new Tooltip();
        t2.setText("Encrypt hash code using sender's private key");
        bEncrypt.setTooltip(t2);
        
        Tooltip t3 = new Tooltip();
        t3.setText("Concatenate Signature component and message");
        bPlus.setTooltip(t3);
        
        Tooltip t4 = new Tooltip();
        t4.setText("Compress data");
        bZip.setTooltip(t4);
        
        Tooltip t5 = new Tooltip();
        t5.setText("Encrypt message using Session key");
        bEncryptMessage.setTooltip(t5);
        
        Tooltip t6 = new Tooltip();
        t6.setText("Encrypt Session key using recipient's public key");
        bEncryptKs.setTooltip(t6);
        
        Tooltip t7 = new Tooltip();
        t7.setText("Concatenate Session key component and encrypted message");
        bPlus1.setTooltip(t7);
        
        Tooltip t8 = new Tooltip();
        t8.setText("Convert data to Radix 64");
        bToR64.setTooltip(t8);
        
        Tooltip t9 = new Tooltip();
        t9.setText("Convert data from Radix 64");
        bFromR64.setTooltip(t9);
        
        Tooltip t10 = new Tooltip();
        t10.setText("Decrypt Session key using Recipient's private key");
        bDecryptKs.setTooltip(t10);
        
        Tooltip t11 = new Tooltip();
        t11.setText("Decrypt message using Session key");
        bDecryptMessage.setTooltip(t11);
        
        Tooltip t12 = new Tooltip();
        t12.setText("Decompress message");
        bZip1.setTooltip(t12);
        
        Tooltip t13 = new Tooltip();
        t13.setText("Generate hash code of the received message");
        bHash1.setTooltip(t13);
        
        Tooltip t14 = new Tooltip();
        t14.setText("Decrypt hash code from the Signature component using sender's public key");
        bDecrypt.setTooltip(t14);
        
        Tooltip t15 = new Tooltip();
        t15.setText("Compare generated hash code and one received in the Signature component");
        bCompare.setTooltip(t15);
    }
    
}
