
package threepalmtrees.pgp;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import util.KeySignature;

/**
 * FXML Controller class
 *
 * @author Mirko
 */
public class KeySignaturesController implements Initializable {

    @FXML private TableView<KeySignature> keySignatureTableView;
    @FXML private TableColumn<KeySignature, String> userIdColumn;
    @FXML private TableColumn<KeySignature, String> keyIdColumn;
    @FXML private TableColumn<KeySignature, String> signatureTrustColumn;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        userIdColumn.setCellValueFactory(
            new PropertyValueFactory<KeySignature, String>("userID")
        );
        keyIdColumn.setCellValueFactory(
            new PropertyValueFactory<KeySignature, String>("keyID")
        );
        signatureTrustColumn.setCellValueFactory(
            new PropertyValueFactory<KeySignature, String>("signatureTrust")
        );
        
        ObservableList<KeySignature> lista = FXCollections.observableArrayList(KeyRingController.currentKey.getSignatures());
        keySignatureTableView.setItems(lista);
        
    }    
    
}
