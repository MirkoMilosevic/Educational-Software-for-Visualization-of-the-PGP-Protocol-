
package demo;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.Base64;
import java.util.ResourceBundle;
import java.util.zip.Deflater;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Mirko
 */
public class DemoRadixCompressionController implements Initializable {

    @FXML private TextArea plainTextArea;
    @FXML private TextArea compressedTextArea;
    @FXML private TextArea convertedTextArea;
    
    @FXML private Label plainLabel;
    @FXML private Label compressedLabel;
    @FXML private Label convertedLabel;
    
    @FXML private Button nextButton;
    @FXML private Button compressAndConvertButton;
    @FXML private Button backButton;
    
    @FXML private Label label1;
    @FXML private Label label2;
    @FXML private Label label3;
    
    public static int counter;
    public static String plaintext;
    public static byte[] compressedData;
    public static String convertedData;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        counter = 0;
        plaintext = "The size of Compressed and Converted" + "\n" + 
                    "Message:" + "\n" + "\n" + 
                    "The use of radix 64 expands a message " + "\n" +
                    "by 33%. Fortunately, the session key" + "\n" +
                    "and signature portion of the message" + "\n" + 
                    "are relatively compact, and the plaintext" + "\n" + 
                    "message has been compressed. In fact," + "\n" + 
                    "the compression should be more than enough" + "\n" + 
                    "to compensate for the radix64 expansion." + "\n" +
                    "If we ignore the relatively small signature" + "\n" + 
                    "and key component, the typical overal effext" + "\n" + 
                    "of compression and expansion of a file of" + "\n" + 
                    "length N would be 1.33 x 0.5 x N = 0.665 x N." + "\n" +
                    "Thus, there is still an overal compression" + "\n" + 
                    "of about one-third.";
        
        plainTextArea.setText(plaintext);
        plainLabel.setText("Size of plaintext in B: " + plaintext.getBytes().length);
        
        plainTextArea.setStyle("-fx-text-fill: white;");
        compressedTextArea.setStyle("-fx-text-fill: white;");
        compressedTextArea.setEditable(false);
        convertedTextArea.setStyle("-fx-text-fill: white;");
        convertedTextArea.setEditable(false);
        
        compressAndConvertButton.disableProperty().set(true);
    }

    public void next(ActionEvent event) throws IOException
    {
        switch(counter)
        {
            case 0:
            {
                compress();
                counter++;
                break;
            }
            
            case 1:
            {
                convert();
                counter++;
                break;
            }
            
            case 2:
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Message");
                alert.setHeaderText(null);
                double n = ((double) convertedData.length())/((double) plaintext.length());
                DecimalFormat df2 = new DecimalFormat("#.##");
                String message = "In this case overal ratio of" + "\n" + 
                                 "plaintext/converter message size is " + df2.format(n) + "\n\n" + 
                                 "Try typing some other plaintext" + "\n" +
                                 "and see the effects of compression and conversion.";
                alert.setContentText(message);
                alert.showAndWait();
                
                nextButton.setText("Finish");
                compressAndConvertButton.disableProperty().set(false);
                
                counter++;
                break;
            }
            
            case 3:
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
    
    
    public void compressAndConvert() throws IOException
    {
        String warning = "";
        boolean alert = false;
        
        if(plainTextArea.getText().isEmpty())
        {
            warning = warning + "Please type some plaintext!" + "\n";
            alert = true;
        }
        
        if(alert)
        {
            Alert alertBox = new Alert(Alert.AlertType.WARNING);
            alertBox.setTitle("Plaintext Missing Warning");
            alertBox.setHeaderText(null);
            alertBox.setContentText(warning);
            alertBox.showAndWait();
        
            return;
        }
        else
        {
            plaintext = plainTextArea.getText();
            plainLabel.setText("Size of plaintext in B: " + plaintext.getBytes().length);
            compress();
            convert();
        }   
    }
    
    public void compress() throws IOException
    {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ByteArrayOutputStream compressedStream = new ByteArrayOutputStream();
                
        outputStream.write(plaintext.getBytes());
                
        byte[] input = outputStream.toByteArray();
        Deflater compressor = new Deflater();
        compressor.setInput(input);
        compressor.finish();
        
        
        byte[] buf = new byte[1024];
        while (!compressor.finished())
        {
            int count = compressor.deflate(buf);
            compressedStream.write(buf, 0, count);
        }
                
        byte[] compressed = compressedStream.toByteArray();
        compressedData = compressed;
                
        String compressedString = "";
        for(int i=0; i<compressed.length; i++)
        {  
            int m = compressed[i] & 0xFF;
            String n = Character.toString((char) m);
            compressedString += n;
            if((i % 32) == 31) { compressedString += "\n"; }
        }
                
        compressedTextArea.setText(compressedString);
        compressedLabel.setText("Size of compressed text in B: " + compressed.length);
    }
    
    public void convert() throws IOException
    {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        outputStream.write(compressedData);
        byte[] packet = outputStream.toByteArray();
        String convertedString = Base64.getEncoder().encodeToString(packet);
        convertedData = convertedString;
        
        convertedTextArea.setText("");
        for(int i=0; i<convertedString.length(); i++)
        {
            String temp = Character.toString(convertedString.charAt(i));
            convertedTextArea.appendText(temp);
            if((i % 32) == 31) { convertedTextArea.appendText("\n"); }
        }
                
        convertedLabel.setText("Size of converted text in B: " + convertedString.length());
    }
    
}
