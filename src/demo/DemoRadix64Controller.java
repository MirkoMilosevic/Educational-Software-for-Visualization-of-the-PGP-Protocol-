
package demo;

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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Mirko
 */
public class DemoRadix64Controller implements Initializable {

    @FXML private Label input;
    @FXML private Label hex1;
    @FXML private Label hex2;
    @FXML private Label hex3;
    @FXML private Label hex4;
    @FXML private Label hex5;
    @FXML private Label hex6;
    @FXML private Label eight1;
    @FXML private Label eight2;
    @FXML private Label eight3;
    @FXML private Label eight4;
    @FXML private Label eight5;
    @FXML private Label eight6;
    @FXML private Label six1;
    @FXML private Label six2;
    @FXML private Label six3;
    @FXML private Label six4;
    @FXML private Label six5;
    @FXML private Label six6;
    @FXML private Label six7;
    @FXML private Label six8;
    @FXML private Label dec1;
    @FXML private Label dec2;
    @FXML private Label dec3;
    @FXML private Label dec4;
    @FXML private Label dec5;
    @FXML private Label dec6;
    @FXML private Label dec7;
    @FXML private Label dec8;
    @FXML private Label out1;
    @FXML private Label out2;
    @FXML private Label out3;
    @FXML private Label out4;
    @FXML private Label out5;
    @FXML private Label out6;
    @FXML private Label out7;
    @FXML private Label out8;
    
    @FXML private Button nextButton;
    @FXML private Button tableButton;
    @FXML private Button backButton;
    
    public static int counter;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        input.setText("Ox14FB9C03D9FE");
        counter = 0;
        
    }    
    
    public void next(ActionEvent event) throws IOException
    {
        switch(counter)
        {
            case 0:
            {
                setHex("14", "FB", "9C", "03", "D9", "FE");
                counter++;
                break;
            }
            
            case 1:
            {
                setEight("00010100", "11111011", "10011100", "00000011", "11011001", "11111110");
                counter++;
                break;
            }
            
            case 2:
            {
                setSix("000101", "001111", "101110", "011100", "000000", "111101", "100111", "111110");
                counter++;
                break;
            }
            
            case 3:
            {
                setDec("05", "15", "46", "28", "00", "61", "37", "62");
                counter++;
                break;
            }
            
            case 4:
            {
                setOut("F", "P", "u", "c", "A", "9", "l", "+");
                counter++;
                break;
            }
            
            case 5:
            {
                input.setText("Ox14FB9C03D9");
                setHex("", "", "", "", "", "");
                setEight("", "", "", "", "", "");
                setSix("", "", "", "", "", "", "", "");
                setDec("", "", "", "", "", "", "", "");
                setOut("", "", "", "", "", "", "", "");
                counter++;
                break;
            }
            
            case 6:
            {
                setHex("14", "FB", "9C", "03", "D9", "");
                counter++;
                break;
            }
            
            case 7:
            {
                setEight("00010100", "11111011", "10011100", "00000011", "11011001", "");
                counter++;
                break;
            }
            
            case 8:
            {
                setSix("000101", "001111", "101110", "011100", "000000", "111101", "1001", "");
                counter++;
                break;
            }
            
            case 9:
            {
                setSix("000101", "001111", "101110", "011100", "000000", "111101", "100100", "");
                counter++;
                break;
            }
            
            case 10:
            {
                setDec("05", "15", "46", "28", "00", "61", "36", "");
                counter++;
                break;
            }
            
            case 11:
            {
                setOut("F", "P", "u", "c", "A", "9", "k", "");
                counter++;
                break;
            }
            
            case 12:
            {
                setOut("F", "P", "u", "c", "A", "9", "k", "=");
                counter++;
                break;
            }
            
            case 13:
            {
                input.setText("Ox14FB9C03");
                setHex("", "", "", "", "", "");
                setEight("", "", "", "", "", "");
                setSix("", "", "", "", "", "", "", "");
                setDec("", "", "", "", "", "", "", "");
                setOut("", "", "", "", "", "", "", "");
                counter++;
                break;
            }
            
            case 14:
            {
                setHex("14", "FB", "9C", "03", "", "");
                counter++;
                break;
            }
            
            case 15:
            {
                setEight("00010100", "11111011", "10011100", "00000011", "", "");
                counter++;
                break;
            }
            
            case 16:
            {
                setSix("000101", "001111", "101110", "011100", "000000", "11", "", "");
                counter++;
                break;
            }
            
            case 17:
            {
                setSix("000101", "001111", "101110", "011100", "000000", "110000", "", "");
                counter++;
                break;
            }
            
            case 18:
            {
                setDec("05", "15", "46", "28", "00", "48", "", "");
                counter++;
                break;
            }
            
            case 19:
            {
                setOut("F", "P", "u", "c", "A", "w", "", "");
                counter++;
                break;
            }
            
            case 20:
            {
                setOut("F", "P", "u", "c", "A", "w", "=", "=");
                counter++;
                nextButton.setText("Finish");
                break;
            }
            
            case 21:
            {
                Parent mainParent = FXMLLoader.load(getClass().getResource("/demo/DemoMain.fxml"));
                Scene mainScene = new Scene(mainParent);
                Stage mainWindow = (Stage)((Node)event.getSource()).getScene().getWindow();
                mainWindow.setScene(mainScene);
                mainWindow.show();
                return;
            }
        }
    }
    
    
    public void returnToMenu(ActionEvent event) throws IOException
    {        
        Parent mainParent = FXMLLoader.load(getClass().getResource("/demo/DemoMain.fxml"));
        Scene mainScene = new Scene(mainParent);
        Stage mainWindow = (Stage)((Node)event.getSource()).getScene().getWindow();
        mainWindow.setScene(mainScene);
        mainWindow.show();
    }
    
    
    public void setHex(String h1, String h2, String h3, String h4, String h5, String h6)
    {
        hex1.setText(h1);
        hex2.setText(h2);
        hex3.setText(h3);
        hex4.setText(h4);
        hex5.setText(h5);
        hex6.setText(h6);
    }
    
    public void setEight(String h1, String h2, String h3, String h4, String h5, String h6)
    {
        eight1.setText(h1);
        eight2.setText(h2);
        eight3.setText(h3);
        eight4.setText(h4);
        eight5.setText(h5);
        eight6.setText(h6);
    }
    
    public void setSix(String h1, String h2, String h3, String h4, String h5, String h6, String h7, String h8)
    {
        six1.setText(h1);
        six2.setText(h2);
        six3.setText(h3);
        six4.setText(h4);
        six5.setText(h5);
        six6.setText(h6);
        six7.setText(h7);
        six8.setText(h8);
    }
    
    public void setDec(String h1, String h2, String h3, String h4, String h5, String h6, String h7, String h8)
    {
        dec1.setText(h1);
        dec2.setText(h2);
        dec3.setText(h3);
        dec4.setText(h4);
        dec5.setText(h5);
        dec6.setText(h6);
        dec7.setText(h7);
        dec8.setText(h8);
    }
    
    public void setOut(String h1, String h2, String h3, String h4, String h5, String h6, String h7, String h8)
    {
        out1.setText(h1);
        out2.setText(h2);
        out3.setText(h3);
        out4.setText(h4);
        out5.setText(h5);
        out6.setText(h6);
        out7.setText(h7);
        out8.setText(h8);
    }
    
    public void viewTable() throws IOException
    {
        Parent parent = FXMLLoader.load(getClass().getResource("/demo/DemoRadixTable.fxml"));
        Scene scene = new Scene(parent);
        //Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        Stage window = new Stage();
        window.setScene(scene);
        window.setTitle("Radix64 Conversion Table");
        window.show();
    }
    
}
