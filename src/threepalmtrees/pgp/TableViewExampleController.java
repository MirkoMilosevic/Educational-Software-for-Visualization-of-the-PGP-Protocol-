
package threepalmtrees.pgp;

import java.math.BigInteger;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import util.MyData;
import util.PrivateKeyEntry;
import util.PublicKeyEntry;

/**
 * FXML Controller class
 *
 * @author Mirko
 */
public class TableViewExampleController implements Initializable {

    @FXML private TableView<PrivateKeyEntry> puTableView;
    @FXML private TableColumn<PrivateKeyEntry, String> puC1;
    @FXML private TableColumn<PrivateKeyEntry, String> puC2;
    @FXML private TableColumn<PrivateKeyEntry, String> puC3;
    @FXML private TableColumn<PrivateKeyEntry, String> puC4;
    @FXML private TableColumn<PrivateKeyEntry, Void> puC5;
    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
       ObservableList<PrivateKeyEntry> publicKeys = FXCollections.observableArrayList(MyData.user1.getPrivateKeyRing());
        puC1.setCellValueFactory(
            new PropertyValueFactory<PrivateKeyEntry, String>("keyname")
        );
        puC2.setCellValueFactory(
            new PropertyValueFactory<PrivateKeyEntry, String>("timestamp")
        );
        puC3.setCellValueFactory(
            new PropertyValueFactory<PrivateKeyEntry, String>("userId")
        );
        puC4.setCellValueFactory(
            new PropertyValueFactory<PrivateKeyEntry, String>("keyId")
        );
       
        Callback<TableColumn<PrivateKeyEntry, Void>, TableCell<PrivateKeyEntry, Void>> cellFactory = new Callback<TableColumn<PrivateKeyEntry, Void>, TableCell<PrivateKeyEntry, Void>>() {
            @Override
            public TableCell<PrivateKeyEntry, Void> call(final TableColumn<PrivateKeyEntry, Void> param) {
                final TableCell<PrivateKeyEntry, Void> cell = new TableCell<PrivateKeyEntry, Void>() {

                    private final Button btn = new Button("Action");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            PrivateKeyEntry data = getTableView().getItems().get(getIndex());
                            System.out.println("selectedData: " + data.getKeyname());
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };

        puC5.setCellFactory(cellFactory);
        
        puTableView.setItems(publicKeys);
    }    
    
}
