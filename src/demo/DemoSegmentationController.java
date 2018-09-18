
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

/**
 * FXML Controller class
 *
 * @author Mirko
 */
public class DemoSegmentationController implements Initializable {

    @FXML private Button nextButton;
    @FXML private Label segmentationLabel;
    @FXML private TextArea segmentationTextArea;
    @FXML private TextFlow segmentationTextFlow;
    @FXML private Button backButton;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        segmentationTextArea.setEditable(false);
        String style = "-fx-text-fill: white;";
        segmentationTextArea.setStyle(style);
        
        nextButton.disableProperty().set(true);
        segmentationTextFlow.getChildren().clear();
        
        DemoData.segmentationStepCounter = 0;
        DemoData.segmentation = true;
        DemoData.initMessage();
        
        try {
            DemoData.initSignature();
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(DemoSegmentationController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DemoSegmentationController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeyException ex) {
            Logger.getLogger(DemoSegmentationController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SignatureException ex) {
            Logger.getLogger(DemoSegmentationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            DemoData.initData();
        } catch (IOException ex) {
            Logger.getLogger(DemoSegmentationController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(DemoSegmentationController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchProviderException ex) {
            Logger.getLogger(DemoSegmentationController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchPaddingException ex) {
            Logger.getLogger(DemoSegmentationController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeyException ex) {
            Logger.getLogger(DemoSegmentationController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalBlockSizeException ex) {
            Logger.getLogger(DemoSegmentationController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BadPaddingException ex) {
            Logger.getLogger(DemoSegmentationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("Velicina osnovnog bloka je " + DemoData.demoMessageData.getBytes().length);
        System.out.println("Velicina konvertovanog bloka je " + DemoData.demoConvertedData.getBytes().length);
        
        writeText(DemoData.segmentationStepCounter);
        
    }   
    
    public void next(ActionEvent event) throws IOException
    {
        nextButton.disableProperty().set(true);
        segmentationTextFlow.getChildren().clear();
        
        if(DemoData.segmentationStepCounter < 6)
            { writeText(DemoData.segmentationStepCounter); }
            else
            {
                DemoData.segmentation = false;
                
                Parent mainParent = FXMLLoader.load(getClass().getResource("/demo/DemoMain.fxml"));
                Scene mainScene = new Scene(mainParent);
                Stage mainWindow = (Stage)((Node)event.getSource()).getScene().getWindow();
                mainWindow.setScene(mainScene);
                mainWindow.show();
                return;
            }
        
        if(DemoData.segmentationStepCounter == 3)
        {
            segmentationTextArea.setText(DemoData.demoMessageData);
            segmentationLabel.setText("Plaintext: 1332B");
        }
        
        if(DemoData.segmentationStepCounter == 4)
        {
            String newLine = "\n";
            segmentationTextArea.setText("---------- BEGIN PGP MESSAGE ----------");
            segmentationTextArea.appendText(newLine);
            segmentationTextArea.appendText(newLine);
            segmentationTextArea.appendText("------- BEGIN DATA IN RADIX64 -------");
            segmentationTextArea.appendText(newLine);
            segmentationTextArea.appendText(newLine);
        
            for(int i=0; i<DemoData.demoConvertedData.length(); i++)
            {
                String temp = Character.toString(DemoData.demoConvertedData.charAt(i));
                segmentationTextArea.appendText(temp);
                //if((i % 32) == 31) { segmentationTextArea.appendText(newLine); }
            }
        
            segmentationTextArea.appendText(newLine);
            segmentationTextArea.appendText(newLine);
            segmentationTextArea.appendText("------- END DATA IN RADIX64 -------");
            segmentationTextArea.appendText(newLine);
            segmentationTextArea.appendText(newLine);
            segmentationTextArea.appendText("---------- END PGP MESSAGE ----------");
            
            segmentationTextArea.positionCaret(0);
            
            segmentationLabel.setText("Plaintext: 1332B; PGP message: 2421B");
        }
        
        if(DemoData.segmentationStepCounter == 5)
        {
            String newLine = "\n";
            segmentationTextArea.setText("---------- BEGIN PGP MESSAGE SEGMENT 1/3 ----------");
            segmentationTextArea.appendText(newLine);
            segmentationTextArea.appendText(newLine);
            for(int i=0; i<1000; i++)
            {
                String temp = Character.toString(DemoData.demoConvertedData.charAt(i));
                segmentationTextArea.appendText(temp);
                //if((i % 32) == 31) { segmentationTextArea.appendText(newLine); }
            }
            segmentationTextArea.appendText(newLine);
            segmentationTextArea.appendText(newLine);
            segmentationTextArea.appendText("---------- END PGP MESSAGE SEGMENT 1/3----------");
            segmentationTextArea.appendText(newLine);
            segmentationTextArea.appendText(newLine);
            segmentationTextArea.appendText("---------- BEGIN PGP MESSAGE SEGMENT 2/3 ----------");
            segmentationTextArea.appendText(newLine);
            segmentationTextArea.appendText(newLine);
            for(int i=1000; i<2000; i++)
            {
                String temp = Character.toString(DemoData.demoConvertedData.charAt(i));
                segmentationTextArea.appendText(temp);
                //if((i % 32) == 31) { segmentationTextArea.appendText(newLine); }
            }
            segmentationTextArea.appendText(newLine);
            segmentationTextArea.appendText(newLine);
            segmentationTextArea.appendText("---------- END PGP MESSAGE SEGMENT 2/3----------");
            segmentationTextArea.appendText(newLine);
            segmentationTextArea.appendText(newLine);
            segmentationTextArea.appendText("---------- BEGIN PGP MESSAGE SEGMENT 3/3 ----------");
            segmentationTextArea.appendText(newLine);
            segmentationTextArea.appendText(newLine);
            for(int i=2000; i<DemoData.demoConvertedData.length(); i++)
            {
                String temp = Character.toString(DemoData.demoConvertedData.charAt(i));
                segmentationTextArea.appendText(temp);
                //if((i % 32) == 31) { segmentationTextArea.appendText(newLine); }
            }
            segmentationTextArea.appendText(newLine);
            segmentationTextArea.appendText(newLine);
            segmentationTextArea.appendText("---------- END PGP MESSAGE SEGMENT 3/3----------");
            
            segmentationTextArea.positionCaret(0);
            
            segmentationLabel.setText("2421/1000 => 3 segments");
            
            nextButton.setText("Finish");
        }
        
    }
    
    
    public void returnToMenu(ActionEvent event) throws IOException
    {        
        DemoData.authentication = false;
        DemoData.confidentiality = false;
        DemoData.fullService = false;
        
        DemoData.segmentation = false;
                
        Parent mainParent = FXMLLoader.load(getClass().getResource("/demo/DemoMain.fxml"));
        Scene mainScene = new Scene(mainParent);
        Stage mainWindow = (Stage)((Node)event.getSource()).getScene().getWindow();
        mainWindow.setScene(mainScene);
        mainWindow.show();
    }
    
    
    public void writeText(int index)
    {   
        String text = DemoData.segmentationString[index];
        
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
                                                DemoData.segmentationStepCounter++;
                                                return;
                                            } 
                                            else 
                                            {
                                                Text temp = new Text(Character.toString(text.charAt(i.get())));
                                                
                                                switch(index)
                                                {
                                                    case 0:
                                                    {
                                                        if( ((i.get() > 43) && (i.get() < 51)) || ((i.get() > 104) && (i.get() < 114)) || 
                                                            ((i.get() > 127) && (i.get() < 137)) || ((i.get() > 161) && (i.get() < 173)) )
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
                                                        if( ((i.get() > 30) && (i.get() < 41)) )
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
                                                        temp.setFont(Font.font ("Verdana", 15));
                                                        temp.setFill(Color.WHITE);
                                                        
                                                        break;
                                                    }
                                                    
                                                    case 4:
                                                    {
                                                        temp.setFont(Font.font ("Verdana", 15));
                                                        temp.setFill(Color.WHITE);
                                                        
                                                        break;
                                                    }
                                                    
                                                    case 5:
                                                    {
                                                        temp.setFont(Font.font ("Verdana", 15));
                                                        temp.setFill(Color.WHITE);
                                                        
                                                        break;
                                                    }
                                                    
                                                }
                                                
                                                segmentationTextFlow.getChildren().add(temp);
                                                i.set(i.get() + 1);
                                            }
                                          });
        timeline.getKeyFrames().add(keyFrame);
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }
    
}
