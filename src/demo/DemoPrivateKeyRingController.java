
package demo;

import java.io.IOException;
import java.math.BigInteger;
import java.net.URL;
import java.security.interfaces.RSAPublicKey;
import java.util.ResourceBundle;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
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
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import util.MyData;
import util.PrivateKeyEntry;

/**
 * FXML Controller class
 *
 * @author Mirko
 */
public class DemoPrivateKeyRingController implements Initializable {

    @FXML private Label descriptionLabel1;
    @FXML private Label descriptionLabel2;
    @FXML private Label userLabel1;
    @FXML private Label userLabel2;
    @FXML private Label keyIdLabel1;
    @FXML private Label keyIdLabel2;
    @FXML private Label timeLabel1;
    @FXML private Label timeLabel2;
    @FXML private Label publicLabel;
    @FXML private Button publicButton;
    @FXML private Label privateLabel;
    @FXML private Button privateButton;
    @FXML private TextFlow keyRingTextFlow;
    @FXML private Button nextButton;
    @FXML private Button backButton;
    
    public static PrivateKeyEntry key;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        publicButton.disableProperty().set(true);
        privateButton.disableProperty().set(true);
        nextButton.disableProperty().set(true);
        
        DemoData.privateKeyRingStepCointer = 0;
        keyRingTextFlow.getChildren().clear();
        
        writeText(DemoData.privateKeyRingStepCointer);
        
        key = MyData.user1.searchForKeyByName("JuliusCaesarRSA1");
    }    
    
    
    public void next(ActionEvent event) throws IOException
    {
        nextButton.disableProperty().set(true);
        keyRingTextFlow.getChildren().clear();
        
        if(DemoData.privateKeyRingStepCointer < 10)
        { 
            writeText(DemoData.privateKeyRingStepCointer); 
        }
        else
        {       
            Parent mainParent = FXMLLoader.load(getClass().getResource("/demo/DemoMain.fxml"));
            Scene mainScene = new Scene(mainParent);
            Stage mainWindow = (Stage)((Node)event.getSource()).getScene().getWindow();
            mainWindow.setScene(mainScene);
            mainWindow.show();
            return;
        }
        
        if(DemoData.privateKeyRingStepCointer == 1)
        {
            descriptionLabel2.setText(key.getKeyname());
        }
        
        if(DemoData.privateKeyRingStepCointer == 2)
        {
            userLabel2.setText(key.getUserId());
        }
        
        if(DemoData.privateKeyRingStepCointer == 3)
        {
            keyIdLabel2.setText(key.getKeyId());
        }
        
        if(DemoData.privateKeyRingStepCointer == 4)
        {
            timeLabel2.setText(key.getTimestamp());
        }
        
        if(DemoData.privateKeyRingStepCointer == 5)
        {
            publicButton.disableProperty().set(false);
        }
        
        if(DemoData.privateKeyRingStepCointer == 6)
        {
            privateButton.disableProperty().set(false);
        }
        
        if(DemoData.privateKeyRingStepCointer == 9)
        {
            nextButton.setText("Finish");
            nextButton.disableProperty().set(false);
        }
        
    }
    
    
    public void returnToMenu(ActionEvent event) throws IOException
    {        
        Parent mainParent = FXMLLoader.load(getClass().getResource("/demo/DemoMain.fxml"));
        Scene mainScene = new Scene(mainParent);
        Stage mainWindow = (Stage)((Node)event.getSource()).getScene().getWindow();
        mainWindow.setScene(mainScene);
        mainWindow.show();
    }
    
    
    public void viewPublicKey()
    {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Public key " + key.getKeyname());
       
        String puString;
       
        String params = "Sun RSA public key, 1024 bits" + "\n" + "modulus:";
        RSAPublicKey rsaKey = (RSAPublicKey) key.getPublicKey();
           
        BigInteger m = rsaKey.getModulus();
        String modulus = m.toString(16);
        String temp = "";
        for(int i=0; i<modulus.length(); i++)
        {
            if((i % 64) == 0) { temp += "\n"; }
            temp += modulus.charAt(i);
        }
        temp += "\n";
           
        String exp = "public exponent: " + rsaKey.getPublicExponent().toString(10);
           
        String fullKey = params + temp + exp;
        puString = fullKey;
       
        Label label = new Label(puString);
       
        Button closeButton = new Button("OK");
        closeButton.setOnAction(e -> window.close());
       
        VBox layout = new VBox(20);
        layout.setPadding(new Insets(10, 10, 10, 10));
        Label spacing = new Label("   ");
        spacing.setVisible(false);
        layout.getChildren().addAll(label, closeButton,spacing);
        layout.setAlignment(Pos.CENTER);
        
        Scene scene = new Scene(layout);
        scene.getStylesheets().add("css/popup.css");
        window.setScene(scene);
        window.showAndWait();
    }
    
    public void viewPrivateKey()
    {
        Stage window = new Stage();
       window.initModality(Modality.APPLICATION_MODAL);
       window.setTitle("Private key " + key.getKeyname());
       
       byte[] privateKey = key.getEncryptedPrivateKey();
       
       String temp = "";
       for(int i=0; i<privateKey.length; i++)
       {
           int n = privateKey[i] & 0xFF;
           String p = Character.toString((char) n);
           if(!p.equals("\n")) { temp += p; }
           if((i % 64) == 63) { temp += "\n"; }
       }
       
       
       Label label = new Label(temp);
       
       Button closeButton = new Button("OK");
       closeButton.setOnAction(e -> window.close());
       
        VBox layout = new VBox(20);
        layout.setPadding(new Insets(10, 10, 10, 10));
        Label spacing = new Label("   ");
        spacing.setVisible(false);
        layout.getChildren().addAll(label, closeButton,spacing);
        layout.setAlignment(Pos.CENTER);
        
        Scene scene = new Scene(layout);
        scene.getStylesheets().add("css/popup.css");
        window.setScene(scene);
        window.showAndWait();
    }
    
    public void writeText(int index)
    {   
        String text = DemoData.privateKeyRingString[index];
        
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
                                                DemoData.privateKeyRingStepCointer++;
                                                return;
                                            } 
                                            else 
                                            {
                                                Text temp = new Text(Character.toString(text.charAt(i.get())));
                                                
                                                switch(index)
                                                {
                                                    case 0:
                                                    {
                                                        if( ((i.get() > 209) && (i.get() < 228)) || ((i.get() > 264) && (i.get() < 295)) ||
                                                            ((i.get() > 356) && (i.get() < 373)) || ((i.get() > 381) && (i.get() < 397)) )
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
                                                        if( ((i.get() >= 0) && (i.get() < 7)) || ((i.get() > 36) && (i.get() < 58)) )
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
                                                        if((i.get() >= 0) && (i.get() < 6))
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
                                                        if( ((i.get() >= 0) && (i.get() < 10)) )
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
                                                        if( ((i.get() >= 0) && (i.get() < 10)) )
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
                                                        if( ((i.get() >= 0) && (i.get() < 11)) || ((i.get() > 63) && (i.get() < 74)) )
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
                                                        if((i.get() > 18) && (i.get() < 27))
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
                                                        if( ((i.get() > 81) && (i.get() < 90)) || ((i.get() > 114) && (i.get() < 124)) )
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
                                                        if((i.get() > 10) && (i.get() < 35))
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
                                                
                                                keyRingTextFlow.getChildren().add(temp);
                                                i.set(i.get() + 1);
                                            }
                                          });
        timeline.getKeyFrames().add(keyFrame);
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }
    
}
