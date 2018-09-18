
package threepalmtrees.pgp;

import java.io.IOException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import util.MyData;
import util.MyMessage;
import util.SendMessageData;

/**
 * FXML Controller class
 *
 * @author Mirko
 */
public class SendMessageController implements Initializable {

    @FXML private TextField subjectTextField;
    @FXML private ChoiceBox recipientChoiceBox;
    @FXML private TextArea messageTextArea;
    
    @FXML private ComboBox optionComboBox;
    @FXML private RadioButton zipRadioButton;
    @FXML private RadioButton rad64RadioButton;
    @FXML private Button nextButton;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        //String u1 = MyData.user1.getEmail();
        //String u2 = MyData.user2.getEmail();
        //String u3 = MyData.user3.getEmail();
        //String u4 = MyData.user4.getEmail();
        //String u5 = MyData.user5.getEmail();
        
        if(!MyData.currentUser.getEmail().equals(MyData.user1.getEmail())) { recipientChoiceBox.getItems().add(MyData.user1.getEmail()); }
        if(!MyData.currentUser.getEmail().equals(MyData.user2.getEmail())) { recipientChoiceBox.getItems().add(MyData.user2.getEmail()); }
        if(!MyData.currentUser.getEmail().equals(MyData.user3.getEmail())) { recipientChoiceBox.getItems().add(MyData.user3.getEmail()); }
        if(!MyData.currentUser.getEmail().equals(MyData.user4.getEmail())) { recipientChoiceBox.getItems().add(MyData.user4.getEmail()); }
        if(!MyData.currentUser.getEmail().equals(MyData.user5.getEmail())) { recipientChoiceBox.getItems().add(MyData.user5.getEmail()); }
        
        //recipientChoiceBox.getItems().addAll(u1, u2, u3, u4, u5);
        
        ObservableList<String> options = FXCollections.observableArrayList();
        options.addAll("sign message", "encrypt message", "sign and encrypt message");    
        optionComboBox.setItems(options);
        
        optionComboBox.setValue("sign message");
        zipRadioButton.setSelected(false);
        rad64RadioButton.setSelected(false);
        
        MainController.mainStatusLabel.setText("In this window you are composing your message and chosing PGP services");
        
        messageTextArea.setStyle("-fx-text-fill: white;");
    }    
    
    public void nextScene() throws IOException
    {
        String warning = "";
        boolean alert = false;
        
        if(subjectTextField.getText().isEmpty())
        {
            warning = warning + "Message Subject is missing!" + "\n";
            alert = true;
        }
        
        if(recipientChoiceBox.getSelectionModel().isEmpty())
        {
            warning = warning + "Recipient is not selected is missing!" + "\n";
            alert = true;
        }
        
        if(messageTextArea.getText().isEmpty())
        {
            warning = warning + "Message is empty!" + "\n";
            alert = true;
        }
        
        if(alert)
        {
            Alert alertBox = new Alert(Alert.AlertType.WARNING);
            alertBox.setTitle("Create Message Warning");
            alertBox.setHeaderText(null);
            alertBox.setContentText(warning);
            alertBox.showAndWait();
        
            return;
        }
        
        int zip = 0;
        int radix = 0;
        int mode = 0;
        if(zipRadioButton.isSelected()) { zip = 1; }
        if(rad64RadioButton.isSelected()) { radix = 1; }
        if(optionComboBox.getValue().equals("sign message")) { mode = 1; }
        if(optionComboBox.getValue().equals("encrypt message")) { mode = 2; }
        if(optionComboBox.getValue().equals("sign and encrypt message")) { mode = 3; }
        SendMessageData.setSelectedMode(mode, zip, radix);
        
        SendMessageData.sender = MyData.currentUser;
        String selectedRecipient = recipientChoiceBox.getSelectionModel().getSelectedItem().toString();
        SendMessageData.receiver = MyData.getUser(selectedRecipient);
        
        String filename = subjectTextField.getText();
        String data = messageTextArea.getText();
        MyMessage message = new MyMessage(filename, data);
        
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date entryDate = new Date();
        long time = entryDate.getTime();
        ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
        buffer.putLong(time);
        byte[] timeB = buffer.array();
        String entryDateString = dateFormat.format(time);
        
        SendMessageData.message = message;
        SendMessageData.message.setTimestamp(entryDateString);
        SendMessageData.message.setTimestampBytes(timeB);
        
        String nextSceneResource = "";
        
        if((SendMessageData.selectedMode == 100) || (SendMessageData.selectedMode == 101) ||
           (SendMessageData.selectedMode == 110) || (SendMessageData.selectedMode == 111) ||
           (SendMessageData.selectedMode == 300) || (SendMessageData.selectedMode == 301) ||
           (SendMessageData.selectedMode == 310) || (SendMessageData.selectedMode == 311))
        { 
            nextSceneResource = "SignMessage.fxml"; 
        }
        
        if((SendMessageData.selectedMode == 210) || (SendMessageData.selectedMode == 211))
        {
            nextSceneResource = "CompressMessage.fxml";
        }
        
        if((SendMessageData.selectedMode == 200) || (SendMessageData.selectedMode == 201)) 
        { 
            nextSceneResource = "EncryptMessage.fxml"; 
        }
        
        AnchorPane nextScene = (AnchorPane) FXMLLoader.load(getClass().getResource(nextSceneResource));
        MainController.mainContentPane.getChildren().clear();
        MainController.mainContentPane.getChildren().add(nextScene);
        nextScene.toFront();
        
        System.out.println("Selected mode is : " + SendMessageData.selectedMode);
    }
    
}
