
package threepalmtrees.pgp;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import util.MyData;
import util.PrivateKeyEntry;
import util.PublicKeyEntry;

/**
 * FXML Controller class
 *
 * @author Mirko
 */
public class RevokedKeysController implements Initializable {
   
    @FXML private TableView<PrivateKeyEntry> revokedKeysTable;
    @FXML private TableColumn<PrivateKeyEntry, String> descriptionColumn;
    @FXML private TableColumn<PrivateKeyEntry, String> keyIdColumn;
    @FXML private TableColumn<PrivateKeyEntry, String> reasonColumn;
    
    @FXML private TableView<PublicKeyEntry> revokedKeysTable1;
    @FXML private TableColumn<PublicKeyEntry, String> descriptionColumn1;
    @FXML private TableColumn<PublicKeyEntry, String> keyIdColumn1;
    @FXML private TableColumn<PublicKeyEntry, String> reasonColumn1;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        List<PrivateKeyEntry> activePrivate = new ArrayList<>();
        for(int i=0; i<MyData.currentUser.getPrivateKeyRing().size(); i++)
        {
            if(!MyData.currentUser.getPrivateKeyRing().get(i).isActive())
            { activePrivate.add(MyData.currentUser.getPrivateKeyRing().get(i)); }
        }
        
        List<PublicKeyEntry> activePublic = new ArrayList<>();
        for(int i=0; i<MyData.currentUser.getPublicKeyRing().size(); i++)
        {
            if(!MyData.currentUser.getPublicKeyRing().get(i).isActive())
            { activePublic.add(MyData.currentUser.getPublicKeyRing().get(i)); }
        }
        
        ObservableList<PrivateKeyEntry> privateKeys = FXCollections.observableArrayList(activePrivate);
        ObservableList<PublicKeyEntry> publicKeys = FXCollections.observableArrayList(activePublic);
        
        descriptionColumn.setCellValueFactory(
            new PropertyValueFactory<PrivateKeyEntry, String>("keyname")
        );
        keyIdColumn.setCellValueFactory(
            new PropertyValueFactory<PrivateKeyEntry, String>("keyId")
        );
        reasonColumn.setCellValueFactory(
            new PropertyValueFactory<PrivateKeyEntry, String>("reasonForRevocation")
        );
        
        
        
        
        descriptionColumn1.setCellValueFactory(
            new PropertyValueFactory<PublicKeyEntry, String>("description")
        );
        keyIdColumn1.setCellValueFactory(
            new PropertyValueFactory<PublicKeyEntry, String>("keyId")
        );
        reasonColumn1.setCellValueFactory(
            new PropertyValueFactory<PublicKeyEntry, String>("reasonForRevocation")
        );
        
        revokedKeysTable.setItems(privateKeys);
        revokedKeysTable1.setItems(publicKeys);
    }    

}
