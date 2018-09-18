
package util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 *
 * @author Mirko
 */
public class MyData
{
    
    public static User user1;
    public static User user2;
    public static User user3;
    public static User user4;
    public static User user5;
   
    public static User currentUser;
    
    public static int maxBlockSize = 0;
    
    public static void initMyData() throws IOException, NoSuchAlgorithmException, NoSuchProviderException, InvalidKeySpecException, FileNotFoundException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException
    {
        maxBlockSize = 0;
        
        user1 = new User("Julius", "Caesar", "julius.caesar@caesarcipher.com");
        user2 = new User("Alan", "Turing", "alan.turing@turingbombe.com");
        user3 = new User("Phil", "Zimmermann", "phil.zimmermann@pgp.com");
        user4 = new User("Don", "Coppersmith", "don.coppersmith@des.com");
        user5 = new User("Clifford", "Cocks", "clifford.cocks@rsa.com");
        
        Long date = new Long("1513011915104");
        
        user1.addKeyToPrivateKeyRing("RSA", "1", date, "123", 1);
        date = new Long("1513011917104");
        user1.addKeyToPrivateKeyRing("DSA", "1", date, "jc_ab!", 2);
        date = new Long("1513011919104");
        user1.addKeyToPrivateKeyRing("DSA", "2", date, "Jc.ab#35K", 3);  
        date = new Long("1513012919104");
        user1.addKeyToPrivateKeyRing("ElGamal", "1", date , "JCEG1", 1);
        date = new Long("1513013019104");
        user1.addKeyToPrivateKeyRing("ElGamal", "2", date , "JCEG2", 1);
        
        date = new Long("1513011937104");
        user2.addKeyToPrivateKeyRing("RSA", "1", date, "aTuring", 1);
        date = new Long("1513011938104");
        user2.addKeyToPrivateKeyRing("RSA", "2", date, "41gB__M.2", 3);
        date = new Long("1513011939104");
        user2.addKeyToPrivateKeyRing("DSA", "1", date, "t.42Al@B!", 3);
        date = new Long("1513011939904");
        user2.addKeyToPrivateKeyRing("DSA", "2", date, "a367T5", 2);
        date = new Long("1513012919104");
        user2.addKeyToPrivateKeyRing("ElGamal", "1", date , "ATEG1", 1);
        date = new Long("1513013019104");
        user2.addKeyToPrivateKeyRing("ElGamal", "2", date , "ATEG2", 1);
        
        date = new Long("1513011942104");
        user3.addKeyToPrivateKeyRing("RSA", "1", date, "icecream", 1);
        date = new Long("1513011943104");
        user3.addKeyToPrivateKeyRing("RSA", "2", date, "ice!3cream", 2);
        date = new Long("1513011944104");
        user3.addKeyToPrivateKeyRing("RSA", "3", date, "IcE_cr#3eAM", 3);
        date = new Long("1513011945104");
        user3.addKeyToPrivateKeyRing("DSA", "1", date, "PZ_pgp!" , 2);
        date = new Long("1513011946104");
        user3.addKeyToPrivateKeyRing("DSA", "2", date, "456-1ab@Pg!", 3);
        date = new Long("1513011947104");
        user3.addKeyToPrivateKeyRing("DSA", "3", date, "pgp_protocol", 1);
        date = new Long("1513011948104");
        user3.addKeyToPrivateKeyRing("DSA", "4", date, "z.p3g3p4", 2);
        date = new Long("1513012919104");
        user3.addKeyToPrivateKeyRing("ElGamal", "1", date , "PZEG1", 1);
        date = new Long("1513013019104");
        user3.addKeyToPrivateKeyRing("ElGamal", "2", date , "PZEG2", 1);
        
        date = new Long("1513011952104");
        user4.addKeyToPrivateKeyRing("RSA", "1", date, "33!D_cprSMT#", 3);
        date = new Long("1513011955104");
        user4.addKeyToPrivateKeyRing("RSA", "2", date, "don.coppersmith@des.com", 1);
        date = new Long("1513011958104");
        user4.addKeyToPrivateKeyRing("DSA", "1", date, "don.39TH", 2);
        date = new Long("1513012919104");
        user4.addKeyToPrivateKeyRing("ElGamal", "1", date , "DCEG1", 1);
        date = new Long("1513013019104");
        user4.addKeyToPrivateKeyRing("ElGamal", "2", date , "DCEG2", 1);
        
        date = new Long("1513011972104");
        user5.addKeyToPrivateKeyRing("DSA", "1", date, "M@Y_!567_passWORD", 3);
        date = new Long("1513011975104");
        user5.addKeyToPrivateKeyRing("DSA", "2", date, "M.y_SeCRet", 2);
        date = new Long("1513011978104");
        user5.addKeyToPrivateKeyRing("DSA", "3", date, "my_secret_password", 1);
        date = new Long("1513012919104");
        user5.addKeyToPrivateKeyRing("ElGamal", "1", date , "CCEG1", 1);
        date = new Long("1513013019104");
        user5.addKeyToPrivateKeyRing("ElGamal", "2", date , "CCEG2", 1);
        
        String keyname;
        
        date = new Long("1513011960104");
        keyname = user2.getName() + user2.getSurname() + "RSA1";
        user1.addKeyToPublicKeyRing(user2.searchForKeyByName(keyname), date, user2.getEmail(), keyname, 1);
        date = new Long("1513011961104");
        keyname = user2.getName() + user2.getSurname() + "DSA1";
        user1.addKeyToPublicKeyRing(user2.searchForKeyByName(keyname), date, user2.getEmail(), keyname, 2);
        date = new Long("1513011962104");
        keyname = user3.getName() + user3.getSurname() + "RSA2";
        user1.addKeyToPublicKeyRing(user3.searchForKeyByName(keyname), date, user3.getEmail(), keyname, 1);
        date = new Long("1513011963104");
        keyname = user3.getName() + user3.getSurname() + "DSA1";
        user1.addKeyToPublicKeyRing(user3.searchForKeyByName(keyname), date, user3.getEmail(), keyname, 2);
        date = new Long("1513011964104");
        keyname = user3.getName() + user3.getSurname() + "DSA4";
        user1.addKeyToPublicKeyRing(user3.searchForKeyByName(keyname), date, user3.getEmail(), keyname, 2);
        date = new Long("1513011965104");
        keyname = user4.getName() + user4.getSurname() + "RSA2";
        user1.addKeyToPublicKeyRing(user4.searchForKeyByName(keyname), date, user4.getEmail(), keyname, 1);
        date = new Long("1513011966104");
        keyname = user4.getName() + user4.getSurname() + "DSA1";
        user1.addKeyToPublicKeyRing(user4.searchForKeyByName(keyname), date, user4.getEmail(), keyname, 2);
        date = new Long("1513011967104");
        keyname = user5.getName() + user5.getSurname() + "DSA3";
        user1.addKeyToPublicKeyRing(user5.searchForKeyByName(keyname), date, user5.getEmail(), keyname, 2);
        date = new Long("1513014019104");
        keyname = user2.getName() + user2.getSurname() + "ElGamal1";
        user1.addKeyToPublicKeyRing(user2.searchForKeyByName(keyname), date, user2.getEmail(), keyname, 3);
        date = new Long("1513014029104");
        keyname = user3.getName() + user3.getSurname() + "ElGamal1";
        user1.addKeyToPublicKeyRing(user3.searchForKeyByName(keyname), date, user3.getEmail(), keyname, 3);
        date = new Long("1513014039104");
        keyname = user4.getName() + user4.getSurname() + "ElGamal1";
        user1.addKeyToPublicKeyRing(user4.searchForKeyByName(keyname), date, user4.getEmail(), keyname, 3);
        date = new Long("1513014049104");
        keyname = user5.getName() + user5.getSurname() + "ElGamal1";
        user1.addKeyToPublicKeyRing(user5.searchForKeyByName(keyname), date, user5.getEmail(), keyname, 3);
        
        date = new Long("1513011968104");
        keyname = user1.getName() + user1.getSurname() + "RSA1";
        user2.addKeyToPublicKeyRing(user1.searchForKeyByName(keyname), date, user1.getEmail(), keyname, 1);
        date = new Long("1513011969104");
        keyname = user1.getName() + user1.getSurname() + "DSA1";
        user2.addKeyToPublicKeyRing(user1.searchForKeyByName(keyname), date, user1.getEmail(), keyname, 2); 
        date = new Long("1513011970104");
        keyname = user3.getName() + user3.getSurname() + "RSA1";
        user2.addKeyToPublicKeyRing(user3.searchForKeyByName(keyname), date, user3.getEmail(), keyname, 1);
        date = new Long("1513011971104");
        keyname = user3.getName() + user3.getSurname() + "RSA3";
        user2.addKeyToPublicKeyRing(user3.searchForKeyByName(keyname), date, user3.getEmail(), keyname, 1);
        date = new Long("1513011972104");
        keyname = user3.getName() + user3.getSurname() + "DSA2";
        user2.addKeyToPublicKeyRing(user3.searchForKeyByName(keyname), date, user3.getEmail(), keyname, 2);
        date = new Long("1513011973104");
        keyname = user3.getName() + user3.getSurname() + "DSA3";
        user2.addKeyToPublicKeyRing(user3.searchForKeyByName(keyname), date, user3.getEmail(), keyname, 2);       
        date = new Long("1513011974104");
        keyname = user4.getName() + user4.getSurname() + "RSA1";
        user2.addKeyToPublicKeyRing(user4.searchForKeyByName(keyname), date, user4.getEmail(), keyname, 1);
        date = new Long("1513011975104");
        keyname = user4.getName() + user4.getSurname() + "DSA1";
        user2.addKeyToPublicKeyRing(user4.searchForKeyByName(keyname), date, user4.getEmail(), keyname, 2);        
        date = new Long("1513011976104");
        keyname = user5.getName() + user5.getSurname() + "DSA1";
        user2.addKeyToPublicKeyRing(user5.searchForKeyByName(keyname), date, user5.getEmail(), keyname, 2);
        date = new Long("1513011977104");
        keyname = user5.getName() + user5.getSurname() + "DSA2";
        user2.addKeyToPublicKeyRing(user5.searchForKeyByName(keyname), date, user5.getEmail(), keyname, 2);
        date = new Long("1513014019104");
        keyname = user1.getName() + user1.getSurname() + "ElGamal1";
        user2.addKeyToPublicKeyRing(user1.searchForKeyByName(keyname), date, user1.getEmail(), keyname, 3);
        date = new Long("1513014029104");
        keyname = user3.getName() + user3.getSurname() + "ElGamal1";
        user2.addKeyToPublicKeyRing(user3.searchForKeyByName(keyname), date, user3.getEmail(), keyname, 3);
        date = new Long("1513014039104");
        keyname = user4.getName() + user4.getSurname() + "ElGamal1";
        user2.addKeyToPublicKeyRing(user4.searchForKeyByName(keyname), date, user4.getEmail(), keyname, 3);
        date = new Long("1513014049104");
        keyname = user5.getName() + user5.getSurname() + "ElGamal1";
        user2.addKeyToPublicKeyRing(user5.searchForKeyByName(keyname), date, user5.getEmail(), keyname, 3);
        
        date = new Long("1513011978104");
        keyname = user1.getName() + user1.getSurname() + "RSA1";
        user3.addKeyToPublicKeyRing(user1.searchForKeyByName(keyname), date, user1.getEmail(), keyname, 1);
        date = new Long("1513011979104");
        keyname = user1.getName() + user1.getSurname() + "DSA1";
        user3.addKeyToPublicKeyRing(user1.searchForKeyByName(keyname), date, user1.getEmail(), keyname, 2);
        date = new Long("1513011980104");
        keyname = user1.getName() + user1.getSurname() + "DSA2";
        user3.addKeyToPublicKeyRing(user1.searchForKeyByName(keyname), date, user1.getEmail(), keyname, 2);        
        date = new Long("1513011981104");
        keyname = user2.getName() + user2.getSurname() + "RSA1";
        user3.addKeyToPublicKeyRing(user2.searchForKeyByName(keyname), date, user2.getEmail(), keyname, 1);
        date = new Long("1513011982104");
        keyname = user2.getName() + user2.getSurname() + "RSA2";
        user3.addKeyToPublicKeyRing(user2.searchForKeyByName(keyname), date, user2.getEmail(), keyname, 1);
        date = new Long("1513011983104");
        keyname = user2.getName() + user2.getSurname() + "DSA1";
        user3.addKeyToPublicKeyRing(user2.searchForKeyByName(keyname), date, user2.getEmail(), keyname, 2);
        date = new Long("1513011984104");
        keyname = user2.getName() + user2.getSurname() + "DSA2";
        user3.addKeyToPublicKeyRing(user2.searchForKeyByName(keyname), date, user2.getEmail(), keyname, 2);        
        date = new Long("1513011985104");
        keyname = user4.getName() + user4.getSurname() + "RSA1";
        user3.addKeyToPublicKeyRing(user4.searchForKeyByName(keyname), date, user4.getEmail(), keyname, 1);
        date = new Long("1513011986104");
        keyname = user4.getName() + user4.getSurname() + "RSA2";
        user3.addKeyToPublicKeyRing(user4.searchForKeyByName(keyname), date, user4.getEmail(), keyname, 1);
        date = new Long("1513011987104");
        keyname = user4.getName() + user4.getSurname() + "DSA1";
        user3.addKeyToPublicKeyRing(user4.searchForKeyByName(keyname), date, user4.getEmail(), keyname, 2);        
        date = new Long("1513011988104");
        keyname = user5.getName() + user5.getSurname() + "DSA1";
        user3.addKeyToPublicKeyRing(user5.searchForKeyByName(keyname), date, user5.getEmail(), keyname, 2);
        date = new Long("1513011989104");
        keyname = user5.getName() + user5.getSurname() + "DSA2";
        user3.addKeyToPublicKeyRing(user5.searchForKeyByName(keyname), date, user5.getEmail(), keyname, 2);
        date = new Long("1513011990104");
        keyname = user5.getName() + user5.getSurname() + "DSA3";
        user3.addKeyToPublicKeyRing(user5.searchForKeyByName(keyname), date, user5.getEmail(), keyname, 2);
        date = new Long("1513014019104");
        keyname = user1.getName() + user1.getSurname() + "ElGamal1";
        user3.addKeyToPublicKeyRing(user1.searchForKeyByName(keyname), date, user1.getEmail(), keyname, 3);
        date = new Long("1513014029104");
        keyname = user2.getName() + user2.getSurname() + "ElGamal1";
        user3.addKeyToPublicKeyRing(user2.searchForKeyByName(keyname), date, user2.getEmail(), keyname, 3);
        date = new Long("1513014039104");
        keyname = user4.getName() + user4.getSurname() + "ElGamal1";
        user3.addKeyToPublicKeyRing(user4.searchForKeyByName(keyname), date, user4.getEmail(), keyname, 3);
        date = new Long("1513014049104");
        keyname = user5.getName() + user5.getSurname() + "ElGamal1";
        user3.addKeyToPublicKeyRing(user5.searchForKeyByName(keyname), date, user5.getEmail(), keyname, 3);
        date = new Long("1513014059104");
        keyname = user1.getName() + user1.getSurname() + "ElGamal2";
        user3.addKeyToPublicKeyRing(user1.searchForKeyByName(keyname), date, user1.getEmail(), keyname, 3);
        date = new Long("1513014069104");
        keyname = user2.getName() + user2.getSurname() + "ElGamal2";
        user3.addKeyToPublicKeyRing(user2.searchForKeyByName(keyname), date, user2.getEmail(), keyname, 3);
        date = new Long("1513014079104");
        keyname = user4.getName() + user4.getSurname() + "ElGamal2";
        user3.addKeyToPublicKeyRing(user4.searchForKeyByName(keyname), date, user4.getEmail(), keyname, 3);
        date = new Long("1513014089104");
        keyname = user5.getName() + user5.getSurname() + "ElGamal2";
        user3.addKeyToPublicKeyRing(user5.searchForKeyByName(keyname), date, user5.getEmail(), keyname, 3);
        
        date = new Long("1513011990104");
        keyname = user1.getName() + user1.getSurname() + "RSA1";
        user4.addKeyToPublicKeyRing(user1.searchForKeyByName(keyname), date, user1.getEmail(), keyname, 1);
        date = new Long("1513011990504");
        keyname = user1.getName() + user1.getSurname() + "DSA1";
        user4.addKeyToPublicKeyRing(user1.searchForKeyByName(keyname), date, user1.getEmail(), keyname, 2);        
        date = new Long("1513011990704");
        keyname = user2.getName() + user2.getSurname() + "RSA1";
        user4.addKeyToPublicKeyRing(user2.searchForKeyByName(keyname), date, user2.getEmail(), keyname, 1);
        date = new Long("1513011990904");
        keyname = user2.getName() + user2.getSurname() + "DSA2";
        user4.addKeyToPublicKeyRing(user2.searchForKeyByName(keyname), date, user2.getEmail(), keyname, 2);        
        date = new Long("1513011991004");
        keyname = user3.getName() + user3.getSurname() + "RSA3";
        user4.addKeyToPublicKeyRing(user3.searchForKeyByName(keyname), date, user3.getEmail(), keyname, 1);
        date = new Long("1513011991404");
        keyname = user3.getName() + user3.getSurname() + "DSA3";
        user4.addKeyToPublicKeyRing(user3.searchForKeyByName(keyname), date, user3.getEmail(), keyname, 2);        
        date = new Long("1513011991604");
        keyname = user5.getName() + user5.getSurname() + "DSA1";
        user4.addKeyToPublicKeyRing(user5.searchForKeyByName(keyname), date, user5.getEmail(), keyname, 2);
        date = new Long("1513011991804");
        keyname = user5.getName() + user5.getSurname() + "DSA2";
        user4.addKeyToPublicKeyRing(user5.searchForKeyByName(keyname), date, user5.getEmail(), keyname, 2);
        date = new Long("1513011992504");
        keyname = user5.getName() + user5.getSurname() + "DSA3";
        user4.addKeyToPublicKeyRing(user5.searchForKeyByName(keyname), date, user5.getEmail(), keyname, 2);
        date = new Long("1513014019104");
        keyname = user1.getName() + user1.getSurname() + "ElGamal1";
        user4.addKeyToPublicKeyRing(user1.searchForKeyByName(keyname), date, user1.getEmail(), keyname, 3);
        date = new Long("1513014029104");
        keyname = user3.getName() + user3.getSurname() + "ElGamal1";
        user4.addKeyToPublicKeyRing(user3.searchForKeyByName(keyname), date, user3.getEmail(), keyname, 3);
        date = new Long("1513014039104");
        keyname = user2.getName() + user2.getSurname() + "ElGamal1";
        user4.addKeyToPublicKeyRing(user2.searchForKeyByName(keyname), date, user2.getEmail(), keyname, 3);
        date = new Long("1513014049104");
        keyname = user5.getName() + user5.getSurname() + "ElGamal1";
        user4.addKeyToPublicKeyRing(user5.searchForKeyByName(keyname), date, user5.getEmail(), keyname, 3);
        
        date = new Long("1513011992704");
        keyname = user1.getName() + user1.getSurname() + "RSA1";
        user5.addKeyToPublicKeyRing(user1.searchForKeyByName(keyname), date, user1.getEmail(), keyname, 1);        
        date = new Long("1513011992904");
        keyname = user2.getName() + user2.getSurname() + "RSA2";
        user5.addKeyToPublicKeyRing(user2.searchForKeyByName(keyname), date, user2.getEmail(), keyname, 1);
        date = new Long("1513011994004");
        keyname = user2.getName() + user2.getSurname() + "DSA1";
        user5.addKeyToPublicKeyRing(user2.searchForKeyByName(keyname), date, user2.getEmail(), keyname, 2);
        date = new Long("1513011994304");
        keyname = user2.getName() + user2.getSurname() + "DSA2";
        user5.addKeyToPublicKeyRing(user2.searchForKeyByName(keyname), date, user2.getEmail(), keyname, 2);        
        date = new Long("1513011994404");
        keyname = user3.getName() + user3.getSurname() + "RSA2";
        user5.addKeyToPublicKeyRing(user3.searchForKeyByName(keyname), date, user3.getEmail(), keyname, 1);
        date = new Long("1513011994604");
        keyname = user3.getName() + user3.getSurname() + "DSA4";
        user5.addKeyToPublicKeyRing(user3.searchForKeyByName(keyname), date, user3.getEmail(), keyname, 2);        
        date = new Long("1513011994704");
        keyname = user4.getName() + user4.getSurname() + "RSA1";
        user5.addKeyToPublicKeyRing(user4.searchForKeyByName(keyname), date, user4.getEmail(), keyname, 1);
        date = new Long("1513011994904");
        keyname = user4.getName() + user4.getSurname() + "RSA2";
        user5.addKeyToPublicKeyRing(user4.searchForKeyByName(keyname), date, user4.getEmail(), keyname, 1);
        date = new Long("1513011995004");
        keyname = user4.getName() + user4.getSurname() + "DSA1";
        user5.addKeyToPublicKeyRing(user4.searchForKeyByName(keyname), date, user4.getEmail(), keyname, 2);
        date = new Long("1513014019104");
        keyname = user1.getName() + user1.getSurname() + "ElGamal1";
        user5.addKeyToPublicKeyRing(user1.searchForKeyByName(keyname), date, user1.getEmail(), keyname, 3);
        date = new Long("1513014029104");
        keyname = user3.getName() + user3.getSurname() + "ElGamal1";
        user5.addKeyToPublicKeyRing(user3.searchForKeyByName(keyname), date, user3.getEmail(), keyname, 3);
        date = new Long("1513014039104");
        keyname = user4.getName() + user4.getSurname() + "ElGamal1";
        user5.addKeyToPublicKeyRing(user4.searchForKeyByName(keyname), date, user4.getEmail(), keyname, 3);
        date = new Long("1513014049104");
        keyname = user2.getName() + user2.getSurname() + "ElGamal1";
        user5.addKeyToPublicKeyRing(user2.searchForKeyByName(keyname), date, user2.getEmail(), keyname, 3);
        
    }
    
    public static void setCurrentUser(String username)
    {
        if(username.equals(user1.getName() + " " + user1.getSurname())) currentUser = user1;
        if(username.equals(user2.getName() + " " + user2.getSurname())) currentUser = user2;
        if(username.equals(user3.getName() + " " + user3.getSurname())) currentUser = user3;
        if(username.equals(user4.getName() + " " + user4.getSurname())) currentUser = user4;
        if(username.equals(user5.getName() + " " + user5.getSurname())) currentUser = user5;
    }
    
    public static User getUser(String email)
    {
        if(email.equals(user1.getEmail())) return user1;
        if(email.equals(user2.getEmail())) return user2;
        if(email.equals(user3.getEmail())) return user3;
        if(email.equals(user4.getEmail())) return user4;
        if(email.equals(user5.getEmail())) return user5;
        
        return null;
    }
     
}
