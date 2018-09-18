
package threepalmtrees.pgp;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import util.MyData;
import util.PrivateKeyEntry;

/**
 * FXML Controller class
 *
 * @author Mirko
 */
public class PasswordsController implements Initializable {

    @FXML private Label passwordListLabel;
    @FXML private AnchorPane rootAnchorPane;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        String passwords = "";
        String newLine = "\n";
        for(int i=0; i<MyData.currentUser.getPrivateKeyRing().size(); i++)
        {
            PrivateKeyEntry temp = MyData.currentUser.getPrivateKeyRing().get(i);
            
            passwords = passwords + "Key ID: " + temp.getKeyId() + " - "
                                  + temp.getPassword() + newLine + newLine;
            
            //passwords = passwords + "Key ID:" + temp.getKeyId() + "" + " - "
            //                      + temp.getPassword() + " ("
            //                     + temp.getPasswordSecurityLevel() + " password example)" + newLine + newLine;
        }
        passwordListLabel.setText(passwords);
        rootAnchorPane.setStyle("-fx-background-color: #fffd7f;");
    }    
    
}
