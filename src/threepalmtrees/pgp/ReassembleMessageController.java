
package threepalmtrees.pgp;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.AnchorPane;
import util.SendMessageData;

/**
 * FXML Controller class
 *
 * @author Mirko
 */
public class ReassembleMessageController implements Initializable {

    
    @FXML private TextArea reassembleTextArea;
    @FXML private Button reassembleButton;
    @FXML private Button nextButton;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        reassembleTextArea.setEditable(false);
        nextButton.disableProperty().set(true);
        
        Tooltip reassembleTooltip = new Tooltip();
        reassembleTooltip.setText("This will reassemble messages");
        reassembleButton.setTooltip(reassembleTooltip);
        
        MainController.mainStatusLabel.setText("In this window you are reassembling messages");
        
        String style = "-fx-text-fill: white; -fx-font-size: 16px;";
        reassembleTextArea.setStyle(style);
        
        reassembleTextArea.setText(SendMessageData.segmentedData);
        reassembleTextArea.positionCaret(0);
    }

    
    public void reassembleMessages()
    {
        reassembleTextArea.setText(SendMessageData.beforeSegmentation);
        nextButton.disableProperty().set(false);
        reassembleTextArea.positionCaret(0);
    }
    
    
    public void nextScene() throws IOException
    {
        String nextSceneResource = "";
        
        if(SendMessageData.selectedMode == 100)
        {
            nextSceneResource = "VerifyMessage.fxml";
        }
        
        if(SendMessageData.selectedMode == 110)
        {
            nextSceneResource = "DecompressMessage.fxml";
        }
        
        if((SendMessageData.selectedMode == 101) || (SendMessageData.selectedMode == 111) ||
           (SendMessageData.selectedMode == 201) || (SendMessageData.selectedMode == 211) ||
           (SendMessageData.selectedMode == 301) || (SendMessageData.selectedMode == 311))
        {
            nextSceneResource = "ReconvertMessage.fxml";
        }
        
        if((SendMessageData.selectedMode == 200) || (SendMessageData.selectedMode == 210) ||
           (SendMessageData.selectedMode == 300) || (SendMessageData.selectedMode == 310))
        {
            nextSceneResource = "DecryptMessage.fxml";
        }
        
        AnchorPane nextScene = (AnchorPane) FXMLLoader.load(getClass().getResource(nextSceneResource));
        MainController.mainContentPane.getChildren().clear();
        MainController.mainContentPane.getChildren().add(nextScene);
        nextScene.toFront();
    }
    
    
}
