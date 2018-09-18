
package threepalmtrees.pgp;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import util.MyData;

/**
 * FXML Controller class
 *
 * @author Mirko
 */
public class AboutController implements Initializable {

    @FXML private TextArea aboutTextArea;
    @FXML private Circle aboutCircle;
    @FXML private Label aboutLabel;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        String imageUrl = "";
        String text = "";
        
        if(MyData.currentUser.getName().equals("Julius")) 
        { 
            imageUrl = "images/Julius.jpg"; 
            
            text = "Julius Caesar may have been one of the world’s first cryptographers," + "\n" +
                   "and is considered the first person to use a substitution cipher." + "\n" +
                   "In his “substitution cipher” Caesar sent a message to Cicero during" + "\n" +
                   "the Gallic Wars, in which Roman letters replaced with Greek letters," + "\n" +
                   "rendering the message unintelligible to his enemies." + "\n" + "\n" +
                   "Substitution ciphers work by substituting each character in the " + "\n" +
                   "original “Plain Alphabet” with another corresponding character from" + "\n" +
                   "a “Cipher Alphabet”. For example, if we have the following cipher:" + "\n" + "\n" +
                   "Plain Alphabet: a b c d e f g h i j k l m n o p q r s t u v w x y z" + "\n" +
                   "Cipher Alphabet: x z a v o I d b y g e r s p c f h j k l m n q t u w" + "\n" + "\n" +
                   "then the message: “Hello, I am here” would be encrypted as: " + "\n" +
                   "“Borrc, y xs boro.” For a long time, substitution ciphers were an " + "\n" +
                   "effective form of cryptography, until 9th century Arab mathematicians" + "\n" +
                   "were able to break the encryption by studying the frequency at which" + "\n" +
                   "characters appear. As an example, in English, the most commonly" + "\n" +
                   "appearing characters are e, t, a, o, and i, and the least commonly" + "\n" +
                   "appearing characters are z, q, j, x, and k. By counting how often" + "\n" +
                   "a given character appears in a substitution encoded cipher, and" + "\n" +
                   "comparing it to the most common characters used in a language," + "\n" +
                   "cryptographers can begin to break a code.";
            
        }
        
        if(MyData.currentUser.getName().equals("Alan")) 
        { 
            imageUrl = "images/Alan.jpg";
            
            text = "Alan Turing, the English mathematician and computer scientist, is famous" + "\n" +
                   "for formalized the concepts of algorithms and proposed the idea of the" + "\n" +
                   "Turing machine. During World War 2, Turing worked at the UK Government" + "\n" +
                   "Code and Cypher School, trying to break Nazi Germany’s encoded messages." + "\n" +
                   "The German messages would be typed into cipher machines, known as Enigma," + "\n" +
                   "consisting of electrical pathways and rotors that would transform each" + "\n" +
                   "letter based on many possible permutations. The key to breaking the code" + "\n" +
                   "lay in identifying what combination of rotor settings and electrical" + "\n" +
                   "connections the machine had been set to on any given day. Turing" + "\n" +
                   "suggested that to decrypt the messages encoded by the Enigma machine," + "\n" +
                   "another electromechanical machine should be created. This machine, dubbed" + "\n" +
                   "the Bombe, would try many different combinations of the Engima rotors," + "\n" +
                   "detecting contradictions in the coded message until the correct" + "\n" +
                   "combination could be found. With Turing’s Bombe, even the previously"  + "\n" +
                   "“unbreakable” Enigma code could be deciphered.";
                   
        }
        
        if(MyData.currentUser.getName().equals("Phil")) 
        { 
            imageUrl = "images/Phil.jpg"; 
            
            text = "Philip R. \"Phil\" Zimmermann, Jr. (born February 12, 1954) is the" + "\n" +
                   "creator of Pretty Good Privacy (PGP), the most widely used email" + "\n" +
                   "encryption software in the world. He was born in Camden, New Jersey." + "\n" +
                   "His father was a concrete mixer truck driver. Zimmermann received a" + "\n" +
                   "B.S. degree in computer science from Florida Atlantic University" + "\n" +
                   "in Boca Raton, Florida in 1978, and thereafter moved to the San" + "\n" +
                   "Francisco Bay Area. In the 1980s, Zimmermann worked in Boulder," + "\n" +
                   "Colorado as a software engineer and was a part of the Nuclear Weapons" + "\n" +
                   "Freeze Campaign as a military policy analyst." + "\n" + "\n" +
                   "In 1991, he wrote the popular Pretty Good Privacy (PGP) program," + "\n" +
                   "and made it available (together with its source code) through public" + "\n" +
                   "FTP for download, the first widely available program implementing" + "\n" +
                   "public-key cryptography. Shortly thereafter, it became available" + "\n" +
                   "overseas via the Internet, though Zimmermann has said he had no" + "\n" +
                   "part in its distribution outside the United States. The very first" + "\n" +
                   "version of PGP included an encryption algorithm, BassOmatic," + "\n" +
                   "developed by Zimmermann.";
        }
        
        if(MyData.currentUser.getName().equals("Don")) 
        { 
            imageUrl = "images/Don.jpg";
            
            text = "Don Coppersmith (born c. 1950) is a cryptographer and mathematician." + "\n" +
                   "He was involved in the design of the Data Encryption Standard block" + "\n" +
                   "cipher at IBM, particularly the design of the S-boxes, strengthening" + "\n" +
                   "them against differential cryptanalysis. He has also worked on" + "\n" +
                   "algorithms for computing discrete logarithms, the cryptanalysis of" + "\n" +
                   "RSA, methods for rapid matrix multiplication and IBM's MARS cipher." + "\n" +
                   "Don is also a co-designer of the SEAL and Scream ciphers." + "\n" + "\n" +
                   "In 1972, Coppersmith obtained a Bachelor's degree in mathematics" + "\n" +
                   "at the Massachusetts Institute of Technology, and a Masters and PhD" + "\n" +
                   "in mathematics from Harvard University in 1975 and 1977 respectively." + "\n" +
                   "In 1998, he started Ponder This, an online monthly column on mathematical" + "\n" +
                   "puzzles and problems. In 2002, Coppersmith won the RSA Security Award" + "\n" +
                   "for Mathematics.";
        }
        
        if(MyData.currentUser.getName().equals("Clifford")) 
        { 
            imageUrl = "images/Clifford.jpg";
            
            text = "Clifford Christopher Cocks (born 28 December 1950) is a British" + "\n" +
                   "mathematician and cryptographer. In 1973, while working at the United" + "\n" +
                   "Kingdom Government Communications Headquarters (GCHQ), he invented" + "\n" +
                   "a public key cryptography algorithm equivalent to what would become" + "\n" +
                   "(in 1978) the RSA algorithm. The idea was classified information" + "\n" +
                   "and his insight remained hidden for 24 years, despite being" + "\n" +
                   "independently invented by Rivest, Shamir, and Adleman in 1977." + "\n" +
                   "Public-key cryptography using prime factorisation is now part" + "\n" +
                   "of nearly every Internet transaction.";
        }
        
        Image image = new Image(imageUrl);
        ImagePattern imagePattern = new ImagePattern(image);
        aboutCircle.setFill(imagePattern);
        
        aboutLabel.setText(MyData.currentUser.getName() + " " + MyData.currentUser.getSurname());
        MainController.mainStatusLabel.setText("In this window you can learn something about current user");
        
        String style = "-fx-text-fill: white; -fx-font-size: 16px;";
        aboutTextArea.setStyle(style);
        aboutTextArea.setEditable(false);
        aboutTextArea.setText(text);
       
    }    
    
}
