
package threepalmtrees.pgp;

import java.math.BigInteger;
import java.net.URL;
import java.security.interfaces.RSAPublicKey;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.bouncycastle.jce.interfaces.ElGamalPublicKey;
import util.MyData;
import util.PrivateKeyEntry;

/**
 * FXML Controller class
 *
 * @author Mirko
 */
public class MyKeysController implements Initializable {

    @FXML private TableView<PrivateKeyEntry> privateKeyRingTable;
    @FXML private TableColumn<PrivateKeyEntry, String> descriptionColumn;
    @FXML private TableColumn<PrivateKeyEntry, String> timestampColumn;
    @FXML private TableColumn<PrivateKeyEntry, String> keyIdColumn;
    @FXML private TableColumn<PrivateKeyEntry, Void> publicKeyColumn;
    @FXML private TableColumn<PrivateKeyEntry, String> userIdColumn;
    @FXML private TableColumn<PrivateKeyEntry, Void> privateKeyColumn;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        
        List<PrivateKeyEntry> active = new ArrayList<>();
        for(int i=0; i<MyData.currentUser.getPrivateKeyRing().size(); i++)
        {
            if(MyData.currentUser.getPrivateKeyRing().get(i).isActive())
            { active.add(MyData.currentUser.getPrivateKeyRing().get(i)); }
        }
             
        //ObservableList<PrivateKeyEntry> privateKeys = FXCollections.observableArrayList(MyData.currentUser.getPrivateKeyRing());
        ObservableList<PrivateKeyEntry> privateKeys = FXCollections.observableArrayList(active);
        
        descriptionColumn.setCellValueFactory(
            new PropertyValueFactory<PrivateKeyEntry, String>("keyname")
        );
        timestampColumn.setCellValueFactory(
            new PropertyValueFactory<PrivateKeyEntry, String>("timestamp")
        );
        keyIdColumn.setCellValueFactory(
            new PropertyValueFactory<PrivateKeyEntry, String>("keyId")
        );
        userIdColumn.setCellValueFactory(
            new PropertyValueFactory<PrivateKeyEntry, String>("userId")
        );
       
        Callback<TableColumn<PrivateKeyEntry, Void>, TableCell<PrivateKeyEntry, Void>> cellFactory = new Callback<TableColumn<PrivateKeyEntry, Void>, TableCell<PrivateKeyEntry, Void>>() {
            @Override
            public TableCell<PrivateKeyEntry, Void> call(final TableColumn<PrivateKeyEntry, Void> param) {
                final TableCell<PrivateKeyEntry, Void> cell = new TableCell<PrivateKeyEntry, Void>() {

                    private final Button btn = new Button("View");

                    {
                        btn.setOnAction((ActionEvent event) -> 
                        {
                            PrivateKeyEntry data = getTableView().getItems().get(getIndex());
                            System.out.println("selectedData: " + data.getKeyname());
                            showPublicKey(data);
                        });
                        
                        String styleString = "-fx-background-color: \n" +
                                   "        linear-gradient(#686868 0%, #232723 25%, #373837 75%, #757575 100%),\n" +
                                   "        linear-gradient(#020b02, #3a3a3a),\n" +
                                   "        linear-gradient(#9d9e9d 0%, #6b6a6b 20%, #343534 80%, #242424 100%),\n" +
                                   "        linear-gradient(#8a8a8a 0%, #6b6a6b 20%, #343534 80%, #262626 100%),\n" +
                                   "        linear-gradient(#777777 0%, #606060 50%, #505250 51%, #2a2b2a 100%); \n" +
                                   "        -fx-font-weight: bold; \n" +
                                   "        -fx-text-fill: white;";
                    
                        btn.setStyle(styleString);
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) 
                    {
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

        Callback<TableColumn<PrivateKeyEntry, Void>, TableCell<PrivateKeyEntry, Void>> cellFactory2 = new Callback<TableColumn<PrivateKeyEntry, Void>, TableCell<PrivateKeyEntry, Void>>() {
            @Override
            public TableCell<PrivateKeyEntry, Void> call(final TableColumn<PrivateKeyEntry, Void> param) {
                final TableCell<PrivateKeyEntry, Void> cell = new TableCell<PrivateKeyEntry, Void>() {

                    private final Button btn = new Button("View");

                    {
                        btn.setOnAction((ActionEvent event) -> 
                        {
                            PrivateKeyEntry data = getTableView().getItems().get(getIndex());
                            System.out.println("selectedData: " + data.getKeyname());
                            showPrivateKey(data);
                        });
                        
                        String styleString = "-fx-background-color: \n" +
                                   "        linear-gradient(#686868 0%, #232723 25%, #373837 75%, #757575 100%),\n" +
                                   "        linear-gradient(#020b02, #3a3a3a),\n" +
                                   "        linear-gradient(#9d9e9d 0%, #6b6a6b 20%, #343534 80%, #242424 100%),\n" +
                                   "        linear-gradient(#8a8a8a 0%, #6b6a6b 20%, #343534 80%, #262626 100%),\n" +
                                   "        linear-gradient(#777777 0%, #606060 50%, #505250 51%, #2a2b2a 100%); \n" +
                                   "        -fx-font-weight: bold; \n" +
                                   "        -fx-text-fill: white;";
                    
                        btn.setStyle(styleString);
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) 
                    {
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
        
        publicKeyColumn.setCellFactory(cellFactory);
        privateKeyColumn.setCellFactory(cellFactory2);
        
        privateKeyRingTable.setItems(privateKeys);
    }    
    
    public void showPublicKey(PrivateKeyEntry data)
    {
       Stage window = new Stage();
       window.initModality(Modality.APPLICATION_MODAL);
       window.setTitle("Public key" + data.getKeyname());
       
       String puString = data.getPublicKey().toString();
       
       if(data.getType() == 1)
       {
           String params = "Sun RSA public key, 1024 bits" + "\n" + "modulus:";
           RSAPublicKey rsaKey = (RSAPublicKey) data.getPublicKey();
           
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
       }
       if(data.getType() == 3)
       {
           ElGamalPublicKey egKey = (ElGamalPublicKey) data.getPublicKey();
           BigInteger y = egKey.getY();
           BigInteger g = egKey.getParams().getG();
           BigInteger p = egKey.getParams().getP();
           
           String temp = "P: " + p.toString(16) + "\n" +
                         "G: " + g.toString(16) + "\n" +
                         "Y: " + y.toString(16);
           
           puString = temp;
       }
       
       Label label = new Label(puString);
       label.setPadding(new Insets(10, 10, 10, 10));
       
       Button closeButton = new Button("OK");
       closeButton.setOnAction(e -> window.close());
       
        VBox layout = new VBox(20);
        layout.setPadding(new Insets(10, 10, 10, 10));
        Label spacing = new Label("   ");
        spacing.setVisible(false);
        layout.getChildren().addAll(label, closeButton);
        layout.setAlignment(Pos.CENTER);
        
        Scene scene = new Scene(layout);
        scene.getStylesheets().add("css/popup.css");
        window.setScene(scene);
        window.showAndWait();
    }
    
    public void showPrivateKey(PrivateKeyEntry data)
    {
       Stage window = new Stage();
       window.initModality(Modality.APPLICATION_MODAL);
       window.setTitle("Private key" + data.getKeyname());
       
       byte[] privateKey = data.getEncryptedPrivateKey();
       
       String temp = "";
       for(int i=0; i<privateKey.length; i++)
       {
           int n = privateKey[i] & 0xFF;
           String p = Character.toString((char) n);
           if(!p.equals("\n")) { temp += p; }
           if((i % 64) == 63) { temp += "\n"; }
       }
       
       Label label = new Label(temp);
       label.setPadding(new Insets(10, 10, 10, 10));
       
       Button closeButton = new Button("OK");
       closeButton.setOnAction(e -> window.close());
       
        VBox layout = new VBox(20);
        layout.setPadding(new Insets(10, 10, 10, 10));
        Label spacing = new Label("   ");
        spacing.setVisible(false);
        layout.getChildren().addAll(label, closeButton);
        layout.setAlignment(Pos.CENTER);
        
        Scene scene = new Scene(layout, 500, 380);
        scene.getStylesheets().add("css/popup.css");
        window.setScene(scene);
        window.showAndWait();
    }
    
}
