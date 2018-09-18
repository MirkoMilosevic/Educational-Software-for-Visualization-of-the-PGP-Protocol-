
package threepalmtrees.pgp;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Security;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

/**
 * FXML Controller class
 *
 * @author Mirko
 */
public class KeyGeneratorController implements Initializable {

    @FXML private TextField usernameText;
    @FXML private TextField algorithmText;
    @FXML private TextField indexText;
    
    @FXML private Button generateKeyPairButton;
    
    @FXML private Label statusKeyPairLabel;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void generateKeyPair() throws NoSuchAlgorithmException, NoSuchProviderException, FileNotFoundException, IOException
    {
        if(Security.getProvider("BC") == null) { Security.addProvider(new BouncyCastleProvider()); }
        
        String user = usernameText.getText();
        String algorithm = algorithmText.getText();
        String index = indexText.getText();
        
        KeyPairGenerator keyGenerator = null;
        if(algorithm.equalsIgnoreCase("DSA")) { keyGenerator = KeyPairGenerator.getInstance("DSA", "SUN"); }
        else if (algorithm.equalsIgnoreCase("RSA")) { keyGenerator = KeyPairGenerator.getInstance("RSA"); }
        else if (algorithm.equalsIgnoreCase("ElGamal")) { keyGenerator = KeyPairGenerator.getInstance("ElGamal", "BC"); }
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG", "SUN");
        
        if(algorithm.equalsIgnoreCase("ElGamal"))
        {
            keyGenerator.initialize(256, random);
        }
        else
        {
            keyGenerator.initialize(1024, random);
        }
        
        //keyGenerator.initialize(1024, random);
        
        KeyPair keyPair = keyGenerator.generateKeyPair();
        PublicKey puKey = keyPair.getPublic();
        PrivateKey prKey = keyPair.getPrivate();
        
        byte[] key1 = puKey.getEncoded();
        String output1 = "src/resource/" + user + algorithm + "PublicKey" + index;
        FileOutputStream keyFileOutputStream1 = new FileOutputStream(output1);
        keyFileOutputStream1.write(key1);
        keyFileOutputStream1.close();
        
        byte[] key2 = prKey.getEncoded();
        String output2 = "src/resource/" + user + algorithm + "PrivateKey" + index;
        FileOutputStream keyFileOutputStream2 = new FileOutputStream(output2);
        keyFileOutputStream2.write(key2);
        keyFileOutputStream2.close();
        
        String message = algorithm + " KeyPair number " + index + " was generated for user " + user + "!";
        statusKeyPairLabel.setText(message);
    }
    
}
