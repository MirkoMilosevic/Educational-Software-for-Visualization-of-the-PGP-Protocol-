
package threepalmtrees.pgp;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import util.MyData;
import util.SendMessageData;
import util.User;

/**
 *
 * @author Mirko
 */
public class WelcomeController implements Initializable {
    
    @FXML private ChoiceBox signInChoiceBox;
    @FXML private Button signInButton;
    @FXML private Button demoButton;
    @FXML private Circle logoCircle;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        String u1 = MyData.user1.getName() + " " + MyData.user1.getSurname();
        String u2 = MyData.user2.getName() + " " + MyData.user2.getSurname();
        String u3 = MyData.user3.getName() + " " + MyData.user3.getSurname();
        String u4 = MyData.user4.getName() + " " + MyData.user4.getSurname();
        String u5 = MyData.user5.getName() + " " + MyData.user5.getSurname();
        
        signInChoiceBox.getItems().addAll(u1, u2, u3, u4, u5);
        
        Image image = new Image("images/logo.jpg");
        ImagePattern imagePattern = new ImagePattern(image);
        logoCircle.setFill(imagePattern);
        
        SendMessageData.selectedMode = 0;
    }    
 
public void signIn(ActionEvent event) throws IOException
{
    if(signInChoiceBox.getSelectionModel().isEmpty())
    {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Sign In Warning");
        alert.setHeaderText(null);
        alert.setContentText("Please select user!");
        alert.showAndWait();
        
        return;
    }
    
    String selectedUser = signInChoiceBox.getSelectionModel().getSelectedItem().toString();
    MyData.setCurrentUser(selectedUser);
    
    Parent mainParent = FXMLLoader.load(getClass().getResource("Main.fxml"));
    Scene mainScene = new Scene(mainParent);
    Stage mainWindow = (Stage)((Node)event.getSource()).getScene().getWindow();
    mainWindow.setScene(mainScene);
    mainWindow.show();
}

public void demoMode(ActionEvent event) throws IOException
{
    Parent mainParent = FXMLLoader.load(getClass().getResource("/demo/DemoMain.fxml"));
    Scene mainScene = new Scene(mainParent);
    Stage mainWindow = (Stage)((Node)event.getSource()).getScene().getWindow();
    mainWindow.setScene(mainScene);
    mainWindow.show();
}


}
