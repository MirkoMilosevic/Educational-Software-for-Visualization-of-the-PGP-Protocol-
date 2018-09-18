
package threepalmtrees.pgp;

import java.io.IOException;
import java.math.BigInteger;
import java.net.URL;
import java.security.interfaces.RSAPublicKey;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.bouncycastle.jce.interfaces.ElGamalPublicKey;
import util.MyData;
import util.PrivateKeyEntry;
import util.PublicKeyEntry;

/**
 * FXML Controller class
 *
 * @author Mirko
 */
public class KeyRingController implements Initializable {

    @FXML private TableView<PublicKeyEntry> publicKeyRingTable;
    @FXML private TableColumn<PublicKeyEntry, String> descriptionColumn;
    @FXML private TableColumn<PublicKeyEntry, String> timestampColumn;
    @FXML private TableColumn<PublicKeyEntry, String> keyIdColumn;
    @FXML private TableColumn<PublicKeyEntry, Void> publicKeyColumn;
    @FXML private TableColumn<PublicKeyEntry, String> userIdColumn;
    
    @FXML private TableColumn<PublicKeyEntry, String> ownerTrustColumn;
    @FXML private TableColumn<PublicKeyEntry, Void> changeTrustColumn;
    @FXML private TableColumn<PublicKeyEntry, Void> signaturesColumn;
    @FXML private TableColumn<PublicKeyEntry, String> keyLegitimacyColumn;
    
    public static PublicKeyEntry currentKey;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        
        List<PublicKeyEntry> active = new ArrayList<>();
        for(int i=0; i<MyData.currentUser.getPublicKeyRing().size(); i++)
        {
            if(MyData.currentUser.getPublicKeyRing().get(i).isActive())
            { active.add(MyData.currentUser.getPublicKeyRing().get(i)); }
        }
        
        //ObservableList<PublicKeyEntry> publicKeys = FXCollections.observableArrayList(MyData.currentUser.getPublicKeyRing());
        ObservableList<PublicKeyEntry> publicKeys = FXCollections.observableArrayList(active);
        
        descriptionColumn.setCellValueFactory(
            new PropertyValueFactory<PublicKeyEntry, String>("description")
        );
        timestampColumn.setCellValueFactory(
            new PropertyValueFactory<PublicKeyEntry, String>("timestamp")
        );
        keyIdColumn.setCellValueFactory(
            new PropertyValueFactory<PublicKeyEntry, String>("keyId")
        );
        userIdColumn.setCellValueFactory(
            new PropertyValueFactory<PublicKeyEntry, String>("userId")
        );
        
        ownerTrustColumn.setCellValueFactory(
          new PropertyValueFactory<PublicKeyEntry, String>("ownerTrust")
        );
        
        keyLegitimacyColumn.setCellValueFactory(
            new PropertyValueFactory<PublicKeyEntry, String>("keyLegitimacy")
        );
       
        Callback<TableColumn<PublicKeyEntry, Void>, TableCell<PublicKeyEntry, Void>> cellFactory = new Callback<TableColumn<PublicKeyEntry, Void>, TableCell<PublicKeyEntry, Void>>() {
            @Override
            public TableCell<PublicKeyEntry, Void> call(final TableColumn<PublicKeyEntry, Void> param) {
                final TableCell<PublicKeyEntry, Void> cell = new TableCell<PublicKeyEntry, Void>() {

                    private final Button btn = new Button("View");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            PublicKeyEntry data = getTableView().getItems().get(getIndex());
                            System.out.println("selectedData: " + data.getDescription());
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

        Callback<TableColumn<PublicKeyEntry, Void>, TableCell<PublicKeyEntry, Void>> cellFactory2 = new Callback<TableColumn<PublicKeyEntry, Void>, TableCell<PublicKeyEntry, Void>>() {
            @Override
            public TableCell<PublicKeyEntry, Void> call(final TableColumn<PublicKeyEntry, Void> param) {
                final TableCell<PublicKeyEntry, Void> cell = new TableCell<PublicKeyEntry, Void>() {

                    private final Button btn = new Button("View");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            
                            PublicKeyEntry data = getTableView().getItems().get(getIndex());
                            currentKey = data;
                            
                            Parent parent = null;
                            try {
                                parent = FXMLLoader.load(getClass().getResource("KeySignatures.fxml"));
                            } catch (IOException ex) {
                                Logger.getLogger(KeyRingController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            Scene scene = new Scene(parent);
                            Stage window = new Stage();
                            window.setScene(scene);
                            window.setTitle("Key Signatures");
                            window.show();
                            
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
        
        Callback<TableColumn<PublicKeyEntry, Void>, TableCell<PublicKeyEntry, Void>> cellFactory3 = new Callback<TableColumn<PublicKeyEntry, Void>, TableCell<PublicKeyEntry, Void>>() {
            @Override
            public TableCell<PublicKeyEntry, Void> call(final TableColumn<PublicKeyEntry, Void> param) {
                final TableCell<PublicKeyEntry, Void> cell = new TableCell<PublicKeyEntry, Void>() {

                    private final Button btn = new Button("Change");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            PublicKeyEntry data = getTableView().getItems().get(getIndex());
                            System.out.println("selectedData: " + data.getDescription());
                            
                            Stage window = new Stage();
                            window.initModality(Modality.APPLICATION_MODAL);
                            window.setTitle("Change key (" + data.getKeyId() + ") Owner Trust");
        
                            VBox layout = new VBox(10);
                            
                            Label message = new Label("Change Owner Trust to:");
                            message.setPadding(new Insets(2, 5, 2, 5));
                            message.setMinWidth(200);
                            
                            ChoiceBox<String> trusts = new ChoiceBox<>();
                            trusts.getItems().addAll("UNDEFINED" , "NOT TRUSTED", "USUALLY TRUSTED", "ALWAYS TRUSTED");
                            if(data.getOwnerTrust().equals("UNDEFINED")) { trusts.setValue("UNDEFINED"); }
                            if(data.getOwnerTrust().equals("NOT TRUSTED")) { trusts.setValue("NOT TRUSTED"); }
                            if(data.getOwnerTrust().equals("USUALLY TRUSTED")) { trusts.setValue("USUALLY TRUSTED"); }
                            if(data.getOwnerTrust().equals("ALWAYS TRUSTED")) { trusts.setValue("ALWAYS TRUSTED"); }
                            
                            Button ok = new Button("OK");
                            ok.setOnAction(e ->
                                                {
                                                    data.setOwnerTrust(trusts.getValue());
                                                    
                                                    MyData.currentUser.setSignatureTrusts();
                                                    MyData.currentUser.setKeyLegitimacy();
                                                    
                                                    window.close();
                                                });
                            
                            layout.getChildren().addAll(message, trusts, ok);
                            layout.setAlignment(Pos.CENTER);
                            layout.setPadding(new Insets(10, 10, 10, 10));
        
                            Scene scene = new Scene(layout, 240, 180);
                            scene.getStylesheets().add("css/popup.css");
                            window.setScene(scene);
                            window.showAndWait();
                            
                            publicKeyRingTable.getColumns().get(0).setVisible(false);
                            publicKeyRingTable.getColumns().get(0).setVisible(true);
                            
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
        signaturesColumn.setCellFactory(cellFactory2);
        changeTrustColumn.setCellFactory(cellFactory3);
        
        publicKeyRingTable.setItems(publicKeys);
    }    
  
    public void showPublicKey(PublicKeyEntry data)
    {
       Stage window = new Stage();
       window.initModality(Modality.APPLICATION_MODAL);
       window.setTitle("Public key" + data.getDescription());
       
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
       layout.getChildren().addAll(label, closeButton);
       layout.setAlignment(Pos.CENTER);
        
       Scene scene = new Scene(layout);
       scene.getStylesheets().add("css/popup.css");
       window.setScene(scene);
       window.showAndWait();
    }
    
}
