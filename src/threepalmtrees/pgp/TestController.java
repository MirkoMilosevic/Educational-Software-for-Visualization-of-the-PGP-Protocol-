/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threepalmtrees.pgp;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import java.util.Arrays;

import java.io.*;
import java.security.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import util.MyData;


import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.security.Security;
import java.security.SignatureException;
import java.util.Date;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
      
import org.bouncycastle.bcpg.ArmoredOutputStream;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.jce.spec.ElGamalParameterSpec;
import org.bouncycastle.openpgp.PGPEncryptedData;
import org.bouncycastle.openpgp.PGPException;
import org.bouncycastle.openpgp.PGPKeyPair;
import org.bouncycastle.openpgp.PGPKeyRingGenerator;
import org.bouncycastle.openpgp.PGPPublicKey;
import org.bouncycastle.openpgp.PGPSignature;

/**
 * FXML Controller class
 *
 * @author Mirko
 */
public class TestController implements Initializable {

    @FXML private Button genKeyButton;
    @FXML private Button signMessageButton;
    @FXML private Button verifyButton;
    @FXML private Button generateAllKeysButton;
    @FXML private Button testKeysButton;
    @FXML private Button generateRSAKeysButton;
    @FXML private Button testRSAKeysButton;
    
    @FXML private Button generateElGamalButton;
    @FXML private Label statusLabel;
    
    @FXML private TextField messageTextField;
    @FXML private TextField signatureTextField;
    
    public static PrivateKey privateKey;
    public static PublicKey publicKey;
    public static byte[] signatureData;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        
    }    
   
    
    public void generateElGamal() throws NoSuchAlgorithmException, NoSuchProviderException, InvalidAlgorithmParameterException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException
    {
        if(Security.getProvider("BC") == null) { Security.addProvider(new BouncyCastleProvider()); }
        
        Security.setProperty("crypto.policy", "unlimited");
        
        KeyPairGenerator elgKpg = KeyPairGenerator.getInstance("ELGAMAL", "BC");
        
        //BigInteger g = new BigInteger("153d5d6172adb43045b68ae8e1de1070b6137005686d29d3d73a7749199681ee5b212c9b96bfdcfa5b20cd5e3fd2044895d609cf9b410b7a0f12ca1cb9a428cc", 16);
        //BigInteger p = new BigInteger("9494fec095f3b85ee286542b3836fc81a5dd0a0349b4c239dd38744d488cf8e31db8bcb7d33b41abb9e5a33cca9144b1cef332c94bf0573bf047a3aca98cdf3b", 16);
        
        StringBuilder sb = new StringBuilder();
                sb.append("FFFFFFFFFFFFFFFFC90FDAA22168C234C4C6628B80DC1CD1");
                sb.append("29024E088A67CC74020BBEA63B139B22514A08798E3404DD");
                sb.append("EF9519B3CD3A431B302B0A6DF25F14374FE1356D6D51C245");
                sb.append("E485B576625E7EC6F44C42E9A637ED6B0BFF5CB6F406B7ED");
                sb.append("EE386BFB5A899FA5AE9F24117C4B1FE649286651ECE45B3D");
                sb.append("C2007CB8A163BF0598DA48361C55D39A69163FA8FD24CF5F");
                sb.append("83655D23DCA3AD961C62F356208552BB9ED529077096966D");
                sb.append("670C354E4ABC9804F1746C08CA237327FFFFFFFFFFFFFFFF");
        
        BigInteger primeModulous = new BigInteger(sb.toString(), 16);
        BigInteger baseGenerator = new BigInteger("2", 16);
        
        ElGamalParameterSpec paramSpecs = new ElGamalParameterSpec(primeModulous, baseGenerator);
        elgKpg.initialize(paramSpecs);
        KeyPair elgKp = elgKpg.generateKeyPair();
        
        byte[] proba = "Proba".getBytes();
        Cipher cipher = Cipher.getInstance("ElGamal", "BC");
        cipher.init(Cipher.ENCRYPT_MODE, elgKp.getPublic());
        byte[] encrypted = cipher.doFinal(proba);
        statusLabel.setText(encrypted.toString());
        
        
    }
    
    
    
    
    public void generateKeyPair() throws NoSuchAlgorithmException, NoSuchProviderException, FileNotFoundException, IOException
    {
        KeyPairGenerator keyGenerator = KeyPairGenerator.getInstance("DSA", "SUN");
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG", "SUN");
        keyGenerator.initialize(1024, random);
        
        KeyPair keyPair = keyGenerator.generateKeyPair();
        PrivateKey prKey = keyPair.getPrivate();
        PublicKey puKey = keyPair.getPublic();
        
        privateKey = prKey;
        publicKey = puKey;
        
        System.out.println("Key Generation successful!!!");
        
    }
    
    public void signMessage() throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException, SignatureException
    {
        String message = messageTextField.getText();
        byte[] messageBytes = message.getBytes();
        
        Signature dsa = Signature.getInstance("SHA1withDSA", "SUN");
        dsa.initSign(privateKey);
        dsa.update(messageBytes);
        
        byte[] dsaSignature = dsa.sign();
        String signedMessage = dsaSignature.toString();
        signatureTextField.setText(signedMessage);
        
        System.out.println("Signing successful!!!");
        signatureData = dsaSignature;
    }
    
    public void verifySignature() throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException, SignatureException
    {
        String message = messageTextField.getText();
        byte[] messageBytes = message.getBytes(); 
        
        Signature signature = Signature.getInstance("SHA1withDSA", "SUN");
        signature.initVerify(publicKey);
        signature.update(messageBytes);
        
        boolean verify = signature.verify(signatureData);
        
        if(verify) 
        {
            System.out.println("Verification SUCCESSFUL!");
        }
        else
        {
            System.out.println("Verification UNSUCCESSFUL!!!");
        }
    }
    
    public void generateDSAKeyPair(String user) throws NoSuchAlgorithmException, NoSuchProviderException, FileNotFoundException, IOException
    {
        KeyPairGenerator keyGenerator = KeyPairGenerator.getInstance("DSA", "SUN");
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG", "SUN");
        keyGenerator.initialize(1024, random);
        
        KeyPair keyPair = keyGenerator.generateKeyPair();
        PublicKey puKey = keyPair.getPublic();
        PrivateKey prKey = keyPair.getPrivate();
        
        byte[] key1 = puKey.getEncoded();
        String output1 = "src/resource/" + user + "DSAPublicKey";
        FileOutputStream keyFileOutputStream1 = new FileOutputStream(output1);
        keyFileOutputStream1.write(key1);
        keyFileOutputStream1.close();
        
        byte[] key2 = prKey.getEncoded();
        String output2 = "src/resource/" + user + "DSAPrivateKey";
        FileOutputStream keyFileOutputStream2 = new FileOutputStream(output2);
        keyFileOutputStream2.write(key2);
        keyFileOutputStream2.close();
    }
    
    public void generateAllKeys() throws NoSuchAlgorithmException, NoSuchProviderException, IOException
    {
        generateDSAKeyPair("JuliusCaesar");
        generateDSAKeyPair("AlanTuring");
        generateDSAKeyPair("PhilZimmermann");
        generateDSAKeyPair("DonCoppersmith");
        generateDSAKeyPair("CliffordCocks");  
    }
    
    public void testOneUserKey(String user, PrivateKey pr, PublicKey pu) throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException, SignatureException
    {
        String message = messageTextField.getText();
        byte[] messageBytes = message.getBytes();
        
        Signature dsa = Signature.getInstance("SHA1withDSA", "SUN");
        dsa.initSign(pr);
        dsa.update(messageBytes);
        
        byte[] dsaSignature = dsa.sign();
        
        Signature signature = Signature.getInstance("SHA1withDSA", "SUN");
        signature.initVerify(pu);
        signature.update(messageBytes);
        
        boolean verify = signature.verify(dsaSignature);
        
        if(verify) 
        {
            System.out.println(user + ": Verification SUCCESSFUL!");
        }
        else
        {
            System.out.println(user + ": Verification UNSUCCESSFUL!!!");
        }
    }
    
    public void generateRSAKeyPair(String user) throws NoSuchAlgorithmException, NoSuchProviderException, FileNotFoundException, IOException
    {
        KeyPairGenerator keyGenerator = KeyPairGenerator.getInstance("RSA");
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG", "SUN");
        keyGenerator.initialize(1024, random);
        
        KeyPair keyPair = keyGenerator.generateKeyPair();
        PublicKey puKey = keyPair.getPublic();
        PrivateKey prKey = keyPair.getPrivate();
        
        byte[] key1 = puKey.getEncoded();
        String output1 = "src/resource/" + user + "RSAPublicKey";
        FileOutputStream keyFileOutputStream1 = new FileOutputStream(output1);
        keyFileOutputStream1.write(key1);
        keyFileOutputStream1.close();
        
        byte[] key2 = prKey.getEncoded();
        String output2 = "src/resource/" + user + "RSAPrivateKey";
        FileOutputStream keyFileOutputStream2 = new FileOutputStream(output2);
        keyFileOutputStream2.write(key2);
        keyFileOutputStream2.close();
    }
    
    public void generateAllRSAKeys() throws NoSuchAlgorithmException, NoSuchProviderException, IOException
    {
        generateRSAKeyPair("JuliusCaesar");
        generateRSAKeyPair("AlanTuring");
        generateRSAKeyPair("PhilZimmermann");
        generateRSAKeyPair("DonCoppersmith");
        generateRSAKeyPair("CliffordCocks");  
    }
    
    public void testOneUserRSAKey(String user, PrivateKey pr, PublicKey pu) throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException, SignatureException
    {
        String message = messageTextField.getText();
        byte[] messageBytes = message.getBytes();
        
        Signature rsa = Signature.getInstance("SHA1withRSA");
        rsa.initSign(pr);
        rsa.update(messageBytes);
        
        byte[] rsaSignature = rsa.sign();
        
        Signature signature = Signature.getInstance("SHA1withRSA");
        signature.initVerify(pu);
        signature.update(messageBytes);
        
        boolean verify = signature.verify(rsaSignature);
        
        if(verify) 
        {
            System.out.println(user + " RSA: Verification SUCCESSFUL!");
        }
        else
        {
            System.out.println(user + " RSA: Verification UNSUCCESSFUL!!!");
        }
    }
    
}
