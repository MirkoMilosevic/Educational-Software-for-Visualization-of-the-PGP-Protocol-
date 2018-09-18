
package util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Security;
import java.security.interfaces.DSAPublicKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.EncodedKeySpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.bouncycastle.jce.interfaces.ElGamalPublicKey;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

/**
 *
 * @author Mirko
 */
public class User 
{
 
    private String name;
    private String surname;
    private String email;
    
    private List <PublicKeyEntry> publicKeyRing;
    private List<PrivateKeyEntry> privateKeyRing;

    public User(String name, String surname, String email) throws IOException, NoSuchAlgorithmException, NoSuchProviderException, InvalidKeySpecException 
    {
        this.name = name;
        this.surname = surname;
        this.email = email;
      
        this.publicKeyRing = new ArrayList<>();
        this.privateKeyRing = new ArrayList<>();
    }
    
    
    public void addKeyToPrivateKeyRing(String algorithm, String index, long date, String pass, int security) throws FileNotFoundException, IOException, NoSuchAlgorithmException, NoSuchProviderException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException
    {
        if(Security.getProvider("BC") == null) { Security.addProvider(new BouncyCastleProvider()); }
        
        String input1 = "src/resource/" + name + surname + algorithm + "PublicKey" + index;
        FileInputStream keyFileInputStream1 = new FileInputStream(input1);
       
        String input2 = "src/resource/" + name + surname + algorithm + "PrivateKey" + index;
        FileInputStream keyFileInputStream2 = new FileInputStream(input2);
        
        
        /*
        InputStream keyFileInputStream3 = getClass().getResourceAsStream("/resource/" + name + surname + algorithm + "PublicKey" + index);
        InputStream keyFileInputStream4 = getClass().getResourceAsStream("/resource/" + name + surname + algorithm + "PrivateKey" + index);
        
        byte[] encodedPublicKey = new byte[keyFileInputStream3.available()];
        keyFileInputStream3.read(encodedPublicKey);
        keyFileInputStream3.close();
        
        byte[] encodedPrivateKey = new byte[keyFileInputStream4.available()];
        keyFileInputStream4.read(encodedPrivateKey);
        keyFileInputStream4.close();
        */
        
        
        byte[] encodedPublicKey = new byte[keyFileInputStream1.available()];  
        keyFileInputStream1.read(encodedPublicKey);
        keyFileInputStream1.close();
        
        X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(encodedPublicKey);
        
        System.out.println("Dohvatio SPEC za public!!!");
        
        KeyFactory keyFactory1 = null;
        if(algorithm.equalsIgnoreCase("DSA")) { keyFactory1 = KeyFactory.getInstance("DSA", "SUN"); }
        else if (algorithm.equalsIgnoreCase("RSA")) { keyFactory1 = KeyFactory.getInstance("RSA"); }
        else if (algorithm.equalsIgnoreCase("ElGamal")) { keyFactory1 = KeyFactory.getInstance("ElGamal" , "BC"); }
        else { System.out.println("ERROR while reading Public Key!!!"); }
        
        PublicKey publicKey = keyFactory1.generatePublic(publicKeySpec);
        
        byte[] encodedPrivateKey = new byte[keyFileInputStream2.available()];  
        keyFileInputStream2.read(encodedPrivateKey);
        keyFileInputStream2.close();
        
        EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(encodedPrivateKey);
        
        KeyFactory keyFactory2 = null;
        if(algorithm.equalsIgnoreCase("DSA")) { keyFactory2 = KeyFactory.getInstance("DSA", "SUN"); }
        else if (algorithm.equalsIgnoreCase("RSA")) { keyFactory2 = KeyFactory.getInstance("RSA"); }
        else if (algorithm.equalsIgnoreCase("ElGamal")) { keyFactory2 = KeyFactory.getInstance("ElGamal" , "BC"); }
        else { System.out.println("ERROR while reading Private Key!!!"); }
        
        PrivateKey privateKey = keyFactory2.generatePrivate(privateKeySpec);
        
        PrivateKeyEntry newEntry = new PrivateKeyEntry();
        newEntry.setPrivateKey(privateKey);
        newEntry.setPublicKey(publicKey);
        
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date entryDate = new Date(date);
        String entryDateString = dateFormat.format(entryDate);
        newEntry.setTimestamp(entryDateString);
        
        newEntry.setUserId(this.email);
        
        String keyname = this.name + this.surname + algorithm + index;
        newEntry.setKeyname(keyname);
        
        if(algorithm.equalsIgnoreCase("DSA")) 
        { 
            newEntry.setType(2);
            
            DSAPublicKey publicDSAKey = (DSAPublicKey) publicKey; 
            
            /*
            byte[] p = publicDSAKey.getParams().getP().toByteArray();
            byte[] q = publicDSAKey.getParams().getQ().toByteArray();
            byte[] g = publicDSAKey.getParams().getG().toByteArray();
            byte[] y = publicDSAKey.getY().toByteArray();
            
            int length = p.length + q.length + g.length + y.length + 10;
            byte packetLength = (byte) length;
            
            byte[] header = new byte[] {(byte) 0x99, packetLength, 0x04 };
            
            byte[] timeBytes = ByteBuffer.allocate(8).putLong(date).array();
            
            byte[] algorithmByte = new byte[] {(byte) 17};
            
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream( );
            outputStream.write(header);
            outputStream.write(timeBytes);
            outputStream.write(algorithmByte);
            outputStream.write(p);
            outputStream.write(q);
            outputStream.write(g);
            outputStream.write(y);
            
            byte[] packet = outputStream.toByteArray();
            
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] sha1OfPacket = md.digest(packet);
            */
            
            
            BigInteger temp = new BigInteger("FFFFFFFFFFFFFFFF", 16);
            //BigInteger sha1Temp = new BigInteger(sha1OfPacket);
            BigInteger y = publicDSAKey.getY();
            BigInteger id = y.and(temp);
            
            String m = id.toString(16);
            newEntry.setKeyId(m);  
        }
        else if (algorithm.equalsIgnoreCase("RSA")) 
        { 
            newEntry.setType(1);
            
            RSAPublicKey publicRSAKey = (RSAPublicKey) publicKey;
            BigInteger modulus = publicRSAKey.getModulus();
            BigInteger temp = new BigInteger("FFFFFFFFFFFFFFFF", 16);
            BigInteger id = modulus.and(temp);
            
            String m = id.toString(16);
            newEntry.setKeyId(m);
        }
        else if (algorithm.equalsIgnoreCase("ElGamal")) 
        { 
            newEntry.setType(3);
            
            ElGamalPublicKey publicEGKey = (ElGamalPublicKey) publicKey;
            BigInteger y = publicEGKey.getY();
            BigInteger temp = new BigInteger("FFFFFFFFFFFFFFFF", 16);
            BigInteger id = y.and(temp);
            
            String m = id.toString(16);
            newEntry.setKeyId(m);
        }
        
        newEntry.setPassword(pass);
        
        MessageDigest passwordHash = MessageDigest.getInstance("SHA-1");
        byte[] hash = passwordHash.digest(pass.getBytes());
        byte[] key = new byte[16];
        for(int i=0; i<16; i++) { key[i] = hash[i]; }
        
        if(Security.getProvider("BC") == null) { Security.addProvider(new BouncyCastleProvider()); }
        
        SecretKey secretKey = new SecretKeySpec(key, 0, key.length, "CAST5");
        
        Cipher cipher = Cipher.getInstance("CAST5", "BC");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedKey = cipher.doFinal(privateKey.getEncoded());
        newEntry.setEncryptedPrivateKey(encryptedKey);
        newEntry.setEncryptedPrivateKeyString(encryptedKey.toString());
        
        if(security == 1) { newEntry.setPasswordSecurityLevel("weak"); }
        if(security == 2) { newEntry.setPasswordSecurityLevel("medium"); }
        if(security == 3) { newEntry.setPasswordSecurityLevel("strong"); }
        
        newEntry.setActive(true);
         
        this.privateKeyRing.add(newEntry);
        
        if(algorithm.equals("ElGamal")) { System.out.println("Dodat ElGamal kljuc!!!"); }
    }

    
    public PrivateKeyEntry searchForKeyByName(String keyname)
    {
        for(int i=0; i<privateKeyRing.size(); i++)
        {
            if(privateKeyRing.get(i).getKeyname().equals(keyname)) 
            {
                //System.out.println("Key found!");
                return privateKeyRing.get(i);
            }
        }
        
        System.out.println("Key NOT found!");
        return null;
    }
    
    
    public PrivateKeyEntry searchForKeyById(String id)
    {
        for(int i=0; i<privateKeyRing.size(); i++)
        {
            if(privateKeyRing.get(i).getKeyId().equals(id)) 
            {
                //System.out.println("Key found!");
                return privateKeyRing.get(i);
            }
        }
        
        System.out.println("Key NOT found!");
        return null;
    }
    
    
    public PublicKeyEntry getPublicKeyById(String id)
    {
        for(int i=0; i<publicKeyRing.size(); i++)
        {
            if(publicKeyRing.get(i).getKeyId().equals(id)) 
            {
                //System.out.println("Key found!");
                return publicKeyRing.get(i);
            }
        }
        
        System.out.println("Key NOT found!");
        return null;
    }
    
    
    public void addKeyToPublicKeyRing(PrivateKeyEntry privateKeyEntry, long date, String user, String keyname, int type)
    {
        PublicKeyEntry newEntry = new PublicKeyEntry();
        newEntry.setPublicKey(privateKeyEntry.getPublicKey());
        
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date entryDate = new Date(date);
        String entryDateString = dateFormat.format(entryDate);
        newEntry.setTimestamp(entryDateString);
        
        newEntry.setUserId(user);
        newEntry.setDescription(keyname);
        newEntry.setKeyId(privateKeyEntry.getKeyId());
        newEntry.setType(type);
        newEntry.setActive(true);
        
        this.publicKeyRing.add(newEntry);
       // System.out.println("Key added to PublicKeyRing of user " + this.name + " " + this.surname + "!!!");
    }
    
    
    public void setSignatureTrusts()
    {
        for(int i=0; i<publicKeyRing.size(); i++)
        {
            PublicKeyEntry pk = publicKeyRing.get(i);
            
            if(!pk.getSignatures().isEmpty())
            {
                for(int j=0; j<pk.getSignatures().size(); j++)
                {
                    if(this.searchForKeyById(pk.getSignatures().get(j).getKeyID()) != null)
                    {
                        pk.getSignatures().get(j).setSignatureTrust("ULTIMATE TRUST");
                    }
                    else if(this.getPublicKeyById(pk.getSignatures().get(j).getKeyID()) != null)
                    {
                        pk.getSignatures().get(j).setSignatureTrust(this.getPublicKeyById(pk.getSignatures().get(j).getKeyID()).getOwnerTrust());
                    }
                    else
                    {
                        pk.getSignatures().get(j).setSignatureTrust("KEY UNKNOWN");
                    }
                }
            }
        }
    }
    
    
    public void setKeyLegitimacy()
    {
        for(int i=0; i<publicKeyRing.size(); i++)
        {
            PublicKeyEntry pk = publicKeyRing.get(i);
            
            if(!pk.getSignatures().isEmpty())
            {
                double weightSum = 0;
                boolean ownerSignedKey = false;
                boolean undefined = true;
            
                for(int j=0; j<pk.getSignatures().size(); j++)
                {
                    if(pk.getSignatures().get(j).getSignatureTrust().equals("ULTIMATE TRUST"))
                    {
                        ownerSignedKey = true;
                        undefined = false;
                        System.out.println("Nasao kljuc sa ultimate poverenjem!!!");
                    }
                    else
                    {
                        if(pk.getSignatures().get(j).getSignatureTrust().equals("NOT TRUSTED"))
                        {
                            weightSum += 0;
                            undefined = false;
                            System.out.println("Suma je " + weightSum);
                        }
                    
                        if(pk.getSignatures().get(j).getSignatureTrust().equals("USUALLY TRUSTED"))
                        {
                            weightSum += 0.5;
                            undefined = false;
                            System.out.println("Suma je " + weightSum);
                        }
                    
                        if(pk.getSignatures().get(j).getSignatureTrust().equals("ALWAYS TRUSTED"))
                        {
                            weightSum += 1;
                            undefined = false;
                            System.out.println("Suma je " + weightSum);
                        }
                    }
                }
            
                if(undefined)
                {
                    pk.setKeyLegitimacy("UNDEFINED");
                }
                else if(ownerSignedKey)
                {
                    pk.setKeyLegitimacy("COMPLETE TRUST");
                }
                else if(weightSum < 0.5)
                {
                    pk.setKeyLegitimacy("NOT TRUSTED");
                }
                else if(weightSum < 1)
                {
                    pk.setKeyLegitimacy("MARGINAL TRUST");
                }
                else
                {
                    pk.setKeyLegitimacy("COMPLETE TRUST");
                }
            
            }
        }
    }
    
    
    public String getName() 
    {
        return name;
    }

    public void setName(String name) 
    {
        this.name = name;
    }

    public String getSurname() 
    {
        return surname;
    }

    public void setSurname(String surname) 
    {
        this.surname = surname;
    }

    public String getEmail() 
    {
        return email;
    }

    public void setEmail(String email) 
    {
        this.email = email;
    }

    public List<PublicKeyEntry> getPublicKeyRing() {
        return publicKeyRing;
    }

    public void setPublicKeyRing(List<PublicKeyEntry> publicKeyRing) {
        this.publicKeyRing = publicKeyRing;
    }

    public List<PrivateKeyEntry> getPrivateKeyRing() {
        return privateKeyRing;
    }

    public void setPrivateKeyRing(List<PrivateKeyEntry> privateKeyRing) {
        this.privateKeyRing = privateKeyRing;
    }

   
}
