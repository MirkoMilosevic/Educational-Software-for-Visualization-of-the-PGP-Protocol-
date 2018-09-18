
package demo;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;
import java.security.Signature;
import java.security.SignatureException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.zip.Deflater;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import util.MyData;
import util.SendMessageData;

/**
 *
 * @author Mirko
 */
public class DemoData {
    
    public static String demoMessageData;
    public static String demoMessageFilename;
    public static String demoMessageTimestamp;
    
    public static String demoSignatureTimestamp;
    public static String demoHashAlgorithm;
    public static String demoSignatureAlgorithm;
    public static String demoSignatureKeyId;
    public static String demoSignatureLeadingOctets;
    public static String demoSignatureMessageDigest;
    public static byte[] demoSignatureBytes;
   
    public static String demoEncryptionKeyId;
    public static String demoEncryptionPublicAlgorithm;
    public static String demoEncryptionSymetricAlgorithm;
    public static String demoEncryptionSessionKey;
    public static String demoEncryptionSessionKeyEncrypted;
    public static byte[] demoSessionKeyBytes;
    
    public static String demoCompressedData;
    public static String demoEncryptedData;
    public static String demoConvertedData;
    
    public static void initMessage()
    {
        if(segmentation)
        {
            demoMessageData = "One interesting fact for you." + "\n" +
                              "These are the first 1.045 digits of Pi." + "\n" + "\n" +
                              "pi= 3.14159 26535 89793 23846 26433" + "\n" +
                              "83279 50288 41971 69399 37510 58209" + "\n" + 
                              "74944 59230 78164 06286 20899 86280" + "\n" +
                              "34825 34211 70679 82148 08651 32823" + "\n" + 
                              "06647 09384 46095 50582 23172 53594" + "\n" +
                              "08128 48111 74502 84102 70193 85211" + "\n" +
                              "05559 64462 29489 54930 38196 44288" + "\n" +
                              "10975 66593 34461 28475 64823 37867" + "\n" + 
                              "83165 27120 19091 45648 56692 34603" + "\n" +
                              "48610 45432 66482 13393 60726 02491" + "\n" + 
                              "41273 72458 70066 06315 58817 48815" + "\n" +
                              "20920 96282 92540 91715 36436 78925" + "\n" +
                              "90360 01133 05305 48820 46652 13841" + "\n" + 
                              "46951 94151 16094 33057 27036 57595" + "\n" +
                              "91953 09218 61173 81932 61179 31051" + "\n" +
                              "18548 07446 23799 62749 56735 18857" + "\n" + 
                              "52724 89122 79381 83011 94912 98336" + "\n" +
                              "73362 44065 66430 86021 39494 63952" + "\n" + 
                              "24737 19070 21798 60943 70277 05392" + "\n" +
                              "17176 29317 67523 84674 81846 76694" + "\n" +
                              "05132 00056 81271 45263 56082 77857" + "\n" + 
                              "71342 75778 96091 73637 17872 14684" + "\n" +
                              "40901 22495 34301 46549 58537 10507" + "\n" +
                              "92279 68925 89235 42019 95611 21290" + "\n" + 
                              "21960 86403 44181 59813 62977 47713" + "\n" +
                              "09960 51870 72113 49999 99837 29780" + "\n" +
                              "49951 05973 17328 16096 31859 50244" + "\n" + 
                              "59455 34690 83026 42522 30825 33446" + "\n" +
                              "85035 26193 11881 71010 00313 78387" + "\n" +
                              "52886 58753 32083 81420 61717 76691" + "\n" + 
                              "47303 59825 34904 28755 46873 11595" + "\n" +
                              "62863 88235 37875 93751 95778 18577" + "\n" +
                              "80532 17122 68066 13001 92787 66111" + "\n" + 
                              "95909 21642 01989 38095 25720 10654 " + "\n" +
                              "85863 27886 59361 53381 82796 82303 ";
        
            demoMessageFilename = "Digits of PI";
        }
        else if(confidentiality)
        {
            demoMessageData = "Encryption is a great way to protect " + "\n" +
                              "information, but it all depends on you" + "\n" +
                              "and your passwords! A lost or forgotten" + "\n" +
                              "password can make a difference. Wonder " + "\n" +
                              "why all those websites you sign up for" + "\n" +
                              "require a password? This is your access" + "\n" +
                              "to the public key, and gives the company" + "\n" +
                              "the ability to use the private key on" + "\n" +
                              "your private information. Use a password" + "\n" +
                              "that is difficult to guess, but easy" + "\n" +
                              "enough to remember so you do not have" + "\n" +
                              "to write it down. Also, try to use" + "\n" +
                              "different passwords for the many" + "\n" +
                              "websites you use.";
            demoMessageFilename = "A Little Bit About Encryption";
        }
        else if(authentication)
        {
            demoMessageData = "When a signer electronically signs" + "\n" +
                              "a document, the signature is created" + "\n" +
                              "using the signer’s private key, which" + "\n" +
                              "is always securely kept by the signer." + "\n" +
                              "The mathematical algorithm acts like" + "\n" +
                              "a cipher, creating data matching the" + "\n" +
                              "signed document, called a hash, and" + "\n" +
                              "encrypting that data. The resulting" + "\n" +
                              "encrypted data is the digital signature." + "\n" +
                              "The signature is also marked with the" + "\n" +
                              "time that the document was signed." + "\n" +
                              "If the document changes after signing," + "\n" +
                              "the digital signature is invalidated.";
            demoMessageFilename = "How do digital signatures work?";
        }
        else if(fullService)
        {
            demoMessageData = "Phil Zimmermann created the first version" + "\n" +
                              "of PGP encryption in 1991. The name," + "\n" +
                              "\"Pretty Good Privacy\" was inspired" + "\n" +
                              "by the name of a grocery store," + "\n" +
                              "\"Ralph's Pretty Good Grocery\"," + "\n" +
                              "featured in radio host Garrison Keillor's" + "\n" +
                              "fictional town, Lake Wobegon. This first" + "\n" +
                              "version included a symmetric-key algorithm" + "\n" +
                              "that Zimmermann had designed himself," + "\n" +
                              "named BassOmatic after a Saturday Night" + "\n" +
                              "Live sketch. Zimmermann had been a" + "\n" +
                              "long-time anti-nuclear activist," + "\n" +
                              "and created PGP encryption so that" + "\n" +
                              "similarly inclined people might securely" + "\n" +
                              "use BBSs and securely store messages and" + "\n" +
                              "files. No license was required for its" + "\n" +
                              "non-commercial use. There was not even" + "\n" +
                              "a nominal charge, and the complete source" + "\n" +
                              "code was included with all copies.";
            demoMessageFilename = "Early History of PGP";
        }
        else if(negative)
        {
            demoMessageData = "Street: Francuska Number 139" + "\n" +
                              "Telephone number: 336398" + "\n" +
                              "Email: owner@threepalmtrees.com" + "\n" +
                              "FAX: 016337756";
            demoMessageFilename = "My new office info";
        }
        else
        {
            demoMessageData = "This is some random message!!!" + "\n" +
                              "This is secret username: asdasdasdasdasdasd" + "\n" +
                              "This is secret password: 123132123123123123";
        
            demoMessageFilename = "This is message subject!";
        }
         
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date entryDate = new Date();
        long time = entryDate.getTime();
        demoMessageTimestamp = dateFormat.format(time);
          
    }
    
    public static void initSignature() throws NoSuchAlgorithmException, IOException, InvalidKeyException, SignatureException
    {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date entryDate = new Date();
        long time = entryDate.getTime();
        demoSignatureTimestamp = dateFormat.format(time);
        
        demoHashAlgorithm = "SHA-1";
        demoSignatureAlgorithm = "RSA";
        
        MessageDigest md = MessageDigest.getInstance("SHA-1");
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        outputStream.write(demoSignatureTimestamp.getBytes());
        outputStream.write(demoMessageData.getBytes());
        byte[] packet = outputStream.toByteArray();        
        byte[] hash = md.digest(packet);
        
        String LeadingOctetString = "";
        for(int i=0; i<2; i++)
        {
            int n = hash[i] & 0xFF;
            String p = Integer.toHexString(n);
            if((hash[i] & 0xF0) == 0) { LeadingOctetString += "0"; }
            LeadingOctetString += p;
            LeadingOctetString += " ";
        }
        
        demoSignatureLeadingOctets = LeadingOctetString;
        
        demoSignatureKeyId = "2fb8076d4d55c5ad" ;
        
        Signature signature = Signature.getInstance("SHA1withRSA");
        signature.initSign(MyData.user1.searchForKeyById(demoSignatureKeyId).getPrivateKey());
        signature.update(demoMessageData.getBytes());
        byte[] signatureBytes = signature.sign();
        
        demoSignatureBytes = signatureBytes;
        
        String encryptedSignature = "";
        for(int i=0; i<signatureBytes.length; i++)
        {  
           int m = signatureBytes[i] & 0xFF;
           String n = Character.toString((char) m);
           if(!n.equals("\n"))
           { encryptedSignature += n; }
           if((i % 32) == 31) { encryptedSignature += "\n"; }
        }
        
        demoSignatureMessageDigest = encryptedSignature;
        
    }
    
    public static void initData() throws IOException, NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException
    {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream( );
        ByteArrayOutputStream compressedStream = new ByteArrayOutputStream( );
        
        byte[] signatureTime = demoSignatureTimestamp.getBytes();
        byte[] publicAlg = demoSignatureAlgorithm.getBytes();
        byte[] hashAlg = demoHashAlgorithm.getBytes();
        byte[] id = demoSignatureKeyId.getBytes();
        byte[] leadingO = demoSignatureLeadingOctets.getBytes();
        byte[] signature = demoSignatureBytes;
        byte[] filename = demoMessageFilename.getBytes();
        byte[] time = demoMessageTimestamp.getBytes();
        byte[] data = demoMessageData.getBytes();
            
        outputStream.write(signatureTime);
        outputStream.write(publicAlg);
        outputStream.write(hashAlg);
        outputStream.write(id);
        outputStream.write(leadingO);
        outputStream.write(signature);
        outputStream.write(filename);
        outputStream.write(time);
        outputStream.write(data);
        
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
        
        byte[] compressedData = compressedStream.toByteArray();
        
        String compressedString = "";
        for(int i=0; i<compressedData.length; i++)
        {  
            int m = compressedData[i] & 0xFF;
            String n = Character.toString((char) m);
            if(!n.equals("\n"))
            { compressedString += n; }
            if((i % 32) == 31) { compressedString += "\n"; }
        }
        
        demoCompressedData = compressedString;
        
        demoEncryptionKeyId = "d2cf90e5552b853b";
        demoEncryptionPublicAlgorithm = "RSA";
        demoEncryptionSymetricAlgorithm = "CAST5";
        
        ByteArrayOutputStream outputStream2 = new ByteArrayOutputStream();
        outputStream2.write(SendMessageData.seed);
        String pass = "This is super secret passphrase!!!";
        outputStream2.write(pass.getBytes());
        byte[] packet = outputStream2.toByteArray();
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] sessionKey = md.digest(packet);
        
        demoSessionKeyBytes = sessionKey;
            
        String sessionKeyString = "";
        for(int i=0; i<demoSessionKeyBytes.length; i++)
        {
            int n = demoSessionKeyBytes[i] & 0xFF;
            String p = Integer.toHexString(n);
            if((demoSessionKeyBytes[i] & 0xF0) == 0) { sessionKeyString += "0"; }
            sessionKeyString += p;
            sessionKeyString += " ";
            if( ((i % 8) == 7) && (i != 15)) { sessionKeyString += "\n"; }
        }
        
        demoEncryptionSessionKey = sessionKeyString;
        
        
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, (Key) MyData.user1.getPublicKeyById(demoEncryptionKeyId).getPublicKey());
        byte[] encryptedKey = cipher.doFinal(demoSessionKeyBytes);
        
        String encryptedSessionKey = "";
        for(int i=0; i<encryptedKey.length; i++)
        {  
           int m = encryptedKey[i] & 0xFF;
           String n = Character.toString((char) m);
           if(!n.equals("\n"))
           { encryptedSessionKey += n; }
           if((i % 32) == 31) { encryptedSessionKey += "\n"; }
        }
        
        demoEncryptionSessionKeyEncrypted = encryptedSessionKey;
        
        byte[] packet2 = null;
        
        if(SendMessageData.selectedMode == 200)
        {
            ByteArrayOutputStream outputStream3 = new ByteArrayOutputStream( );
            outputStream3.write(filename);
            outputStream3.write(time);
            outputStream3.write(data);
            packet2 = outputStream3.toByteArray();
        }
        
        if(SendMessageData.selectedMode == 311)
        {
            ByteArrayOutputStream outputStream3 = new ByteArrayOutputStream( );
            outputStream3.write(demoCompressedData.getBytes());
            /*
            outputStream3.write(signatureTime);
            outputStream3.write(publicAlg);
            outputStream3.write(hashAlg);
            outputStream3.write(id);
            outputStream3.write(leadingO);
            outputStream3.write(signature);
            outputStream3.write(filename);
            outputStream3.write(time);
            outputStream3.write(data);
            */
            
            packet2 = outputStream3.toByteArray();
        }
        
        if(segmentation)
        {
            ByteArrayOutputStream outputStream3 = new ByteArrayOutputStream( );
            
            outputStream3.write(signatureTime);
            outputStream3.write(publicAlg);
            outputStream3.write(hashAlg);
            outputStream3.write(id);
            outputStream3.write(leadingO);
            outputStream3.write(signature);
            outputStream3.write(filename);
            outputStream3.write(time);
            outputStream3.write(data);
            
            packet2 = outputStream3.toByteArray();
        }
        
        if(Security.getProvider("BC") == null) { Security.addProvider(new BouncyCastleProvider()); }
        
        SecretKey secretKey = new SecretKeySpec(demoSessionKeyBytes, 0, demoSessionKeyBytes.length, "CAST5");
        Cipher cipher2 = Cipher.getInstance("CAST5", "BC");
        cipher2.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedMessage = cipher2.doFinal(packet2); 
        
        String encryptedData = "";
        for(int i=0; i<encryptedMessage.length; i++)
        {
           int m = encryptedMessage[i] & 0xFF;
           String n = Character.toString((char) m);
           if(!n.equals("\n"))
           { encryptedData += n; }
           if((i % 32) == 31) { encryptedData += "\n"; }
        }
        
        demoEncryptedData = encryptedData;
        
        ByteArrayOutputStream outputStream4 = new ByteArrayOutputStream( );
            
        outputStream4.write(demoEncryptionKeyId.getBytes());
        outputStream4.write(demoEncryptionPublicAlgorithm.getBytes());
        outputStream4.write(demoEncryptionSymetricAlgorithm.getBytes());
        outputStream4.write(demoEncryptionSessionKeyEncrypted.getBytes());
        outputStream4.write(encryptedMessage);
    
        byte[] packet3 = outputStream4.toByteArray();
        
        String convertedString = Base64.getEncoder().encodeToString(packet3);
        
        String temp2 = "";
        for(int i=0; i<convertedString.length(); i++)
        {
            String temp = Character.toString(convertedString.charAt(i));
            temp2 += temp;
            if((i % 32) == 31) { temp2 += "\n"; }
        }
        
        demoConvertedData = temp2;
 
    }
    
    //Data za autentikaciju
    public static int authenticationStepCointer = 0;
    public static String[] authenticationString = new String[10];
    public static boolean authentication = false;
    static {
            authenticationString[0] = "Message authentication is a service used to verify the integrity of a message." + "\n" + "\n" +
                                      "Message authentication assures that data received are exactly as sent by" +
                                      "and that the purported identity of the sender is valid.";
        
            authenticationString[1] = "The sender creates a message.";
        
            authenticationString[2] = "A hash code of a message is created using one of the hash functions" +
                                      "(MD5, SHA-1, SHA-224, SHA-256)." + "\n" + "\n" +
                                      "Hash code is a fixed-length value and depends on the message and time of creation.";
        
            authenticationString[3] = "The hash code is then encrypted with sender's private key," +
                                      " and the result is prepended to the message." + "\n" + "\n" +
                                      "Encryption algorithm used depends on the type of the key that is used (RSA, DSA)";
        
            authenticationString[4] = "The signature component includes the following:" + "\n" +
                                      "Timestamp: the time at which the signature was made." + "\n" +
                                      "Public Key Algorithm: which algorithm was used for encryption of the hash code." + "\n" +
                                      "Hash Algorithm: which hash funtion was used.";
        
            authenticationString[5] = "Key Id of Sender's Public Key: which sender's public key should be used for decryption." + "\n" +
                                      "Leading Two Octets: to enable the recipient to determine if the correct public key" +
                                      "was used to decrypt the hash code." + "\n" +
                                      "Message Digest: generated hash code encrypted with senders's public key.";
        
            authenticationString[6] = "This message is sent to the recipient.";
        
            authenticationString[7] = "The recipient generates a new hash code for the message.";
        
            authenticationString[8] = "Then, recipient uses sender's public key with given id to decypt message digest." + "\n" + "\n" +
                                      "Note: For successful authentication, recipient must have appropriate key in his PublicKey Ring.";
        
            authenticationString[9] = "The recipient compares generated and decrypted hash code," +
                                      "and if they match verification is successful.";
            }
    
    
    //Data za sifrovanje
    public static int confidentialityStepCounter = 0;
    public static String[] confidentialityString = new String[8];
    public static boolean confidentiality = false;
    static {
            confidentialityString[0] = "Confidentiality is provided by encrypting messages to be transmitted." + "\n" + "\n" +
                                       "It assures that the message can be read only by recipient.";
            
            confidentialityString[1] = "The sender creates a message.";
            
            confidentialityString[2] = "The sender chooses a passphrase. Hash code of this passphrase is generated " +
                                       "and used as Session Key which is used only once." + "\n" + "\n" +
                                       "Note: The message itself can be used instead of passphrase.";
            
            confidentialityString[3] = "The message is encrypted using one of the symmetric algorithms" +
                                       "(CAST5, IDEA, TripleDES)." + "\n" +
                                       "The Session Key is encrypted with RSA or ElGamal using recipient's public key. " +
                                       "Encrypted Session Key is prepended to the message.";
            
            confidentialityString[4] = "The Key component includes the following:" + "\n" +
                                       "Key Id of Recipient's Public Key: which key should recipient use to decrypt Session Key" + "\n" +
                                       "Public Key Algorithm: which algorithm was used for encryption of the Session Key." + "\n" +
                                       "Symetric Algorithm: which algorithm was used for encryption of the message." + "\n" +
                                       "Session Key: encrypted Session Key.";
            
            confidentialityString[5] = "This message is sent to the recipient.";
            
            confidentialityString[6] = "The recipient uses his private key to decrypt and recover the Session Key.";
            
            confidentialityString[7] = "The Session Key is used to decrypt the message.";
           }
    
    
    //Data za oba servisa
    public static int fullServiceStepCounter = 0;
    public static String[] fullServiceString = new String[18];
    public static boolean fullService = false;
    static {
            fullServiceString[0] = "This example will include Compression and Conversion, which are optional.";
            
            fullServiceString[1] = "The sender creates a message.";
            
            fullServiceString[2] = "A hash code of a message is created using one of the hash functions" +
                                   "(MD5, SHA-1, SHA-224, SHA-265).";
            
            fullServiceString[3] = "The hash code is then encrypted with sender's private key." + "\n" + "\n" +
                                   "The result is prepended to the message.";
            
            fullServiceString[4] = "The message is compressed using ZIP algorithm.";
                                   
            fullServiceString[5] = "PGP compresses the message after applying the signature but before encryption." + "\n" +
                                   "It is preferable to sign an uncompressed message so that one can store only " +
                                   "the uncomressed message together with the signature for future verification." + "\n" +
                                   "Message encryption is applied after compression to strengthen security. " +
                                   "Cryptanalysis is more difficult this way.";
            
            fullServiceString[6] = "The sender chooses a passphrase. Hash code of this passphrase is generated " +
                                   "and used as Session Key which is used only once.";
            
            fullServiceString[7] = "The message is encrypted using on of the symmetric algorithms " +
                                   "(CAST5, IDEA, TripleDES)." + "\n" + "\n" +
                                   "The Session Key is encrypted with RSA or ElGamal using recipient's public key." + "\n" + "\n" +
                                   "Encrypted Session Key is prepended to the message.";
            
            fullServiceString[8] = "When some data is ecrypted, the result is a stream of arbitrary 8-bit octets. " +
                                   "However, many electronic mail systems only permit the use of blocks " +
                                   "consisting of ASCII text.";
            
            fullServiceString[9] = "The message is converted to a stream of printable ASCII characters." + "\n" + "\n" +
                                   "The scheme used for this purpose is Radix-64 conversion. " +
                                   "Each group of three octets of binary data is mapped into four ASCII characters.";
            
            fullServiceString[10] = "This message is sent to the recipient.";
            
            fullServiceString[11] = "The message is reconverted from Radix-64.";
            
            fullServiceString[12] = "The recipient uses his private key to decrypt and recover the Session Key.";
            
            fullServiceString[13] = "The Session Key is used to decrypt the message.";
            
            fullServiceString[14] = "The message is decompressed.";
            
            fullServiceString[15] = "The recipient generates a new hash code for the message.";
        
            fullServiceString[16] = "Then, recipient uses sender's public key with given id to decypt message digest.";
        
            fullServiceString[17] = "The recipient compares generated and decrypted hash code, " +
                                    "and if they match verification is successful.";
           }
    
    //Data za private key ring
    public static int privateKeyRingStepCointer = 0;
    public static String[] privateKeyRingString = new String[10];
    static {
            privateKeyRingString[0] = "Keys need to be stored and organized in a systematic way for efficient and effective use." + "\n" + 
                                      "The scheme used in PGP is to provide a pair of data structures for each user, " + 
                                      "one to store the public/private key pairs owned by that user and one to store " +
                                      "the public keys of other users known to this user. These data structures are " +
                                      "referred to, respectively, as the private-key ring and the public-key ring.";
            
            privateKeyRingString[1] = "Here you will see one entry in the private-key ring of the user Julius Caesar." + "\n" + "\n" +
                                      "We can view the key ring as a table, in which each row represents one of the public/private " + 
                                      "key pair owned by this user. Each row contains the following entries:";
            
            privateKeyRingString[2] = "User ID: Typically, this will be the user's e-mail address." + "\n" + "\n" +  
                                      "However, the user may choose to associate a different name with each pair " + 
                                      "or to reuse the same User ID more than once.";
            
            privateKeyRingString[3] = "Key ID: The least significant 64 bits of the public key for this entry.";
            
            privateKeyRingString[4] = "Timestamp: The date/time when this key pair was generated.";
            
            privateKeyRingString[5] = "Public Key: The public-key portion of the pair.";
            
            privateKeyRingString[6] = "Private Key: The private-key portion of the pair, this field is encrypted.";
            
            privateKeyRingString[7] = "The user selects a password to be used for encrypting private key.";
            
            privateKeyRingString[8] = "When the system generates a new public/private key pair, it asks the user for the password." + "\n" + 
                                      "Using SHA-1, a 160-bit hash code is generated from the password and the password " + 
                                      "is discarded.";
            
            privateKeyRingString[9] = "The system encrypts the private key using CAST5 with 128 bits of the hash code as the " + 
                                      "key. The hash code is then discarded, and the private key is stored in the private-key ring.";
        
    }
    
    
    //Data za segmentaciju
    public static int segmentationStepCounter = 0;
    public static boolean segmentation = false;
    public static String[] segmentationString = new String[6];
    static {
            segmentationString[0] = "E-mail facilities often are restricted to a maximum message length. " + 
                                    "Any message longer than that must be broken up into smaller segments, " + 
                                    "each of which is mailed separately.";
            
            segmentationString[1] = "PGP automatically subdivides a message that is too large into segments " + 
                                    "that are small enough to send via e-mail. The segmentation is done after " +
                                    "all of the other processing, including the Radix-64 conversion. " + 
                                    "Thus, the session key component and signature component appear only once " +
                                    "at the beginning of the first segment.";
            
            segmentationString[2] = "At the receiving end, PGP must reassemble the entire original block " + 
                                    "before doing anything else.";
            
            segmentationString[3] = "This is one plaintext message. The size of current message is 1332B.";
            
            segmentationString[4] = "After signing, encryption and conversion to Radix-64, the size is 2421B.";
            
            segmentationString[5] = "Let's assume that the maximum length of the message is 1000B." + "\n" + "\n" +
                                    "This means that the current message must be broken up into 3 messages.";
        
    }
    
    
    //Data za kljuc sesije
    public static int sessionKeyStepCounter = 0;
    public static String[] sessionKeyString = new String[7];
    static {
            sessionKeyString[0] = "String to key (S2K) specifiers are used to convert passphrase strings " + 
                                  "into symetric-key encryption/decryption keys.";
            
            sessionKeyString[1] = "Simple S2K hashes the passphrase to produce the session key." + "\n" + "\n" +
                                  "The manner in which this is done depends on the size of the session key " +
                                  "(which will depend on the cipher used) and the size of the hash code.";
            
            sessionKeyString[2] = "If the hash code size is greater than the session key size, the high-order " + 
                                  "(leftmost) octets of the hash code are used as the key." + "\n" + "\n" +
                                  "If the hash code size is less than the key size, multiple instances of the " +
                                  "hash code are created and concatenated.";
            
            sessionKeyString[3] = "Salted S2K is like Simple S2K, except that the input to the hash function " + 
                                  "consists of the 8 octets of salt from the S2K specifier, followed by the passphrase." + "\n" + "\n" +
                                  "Note: Salt value is some random value specified in the S2K.";
            
            sessionKeyString[4] = "This example will show how the Session Key for CAST5 encryption woulde be created " +
                                  "using SHA-1 hash algorithm.";
            
            sessionKeyString[5] = "The hash code of salt value and passphrase is generated." + "\n" + "\n" +
                                  "Output of SHA-1 is 160 bits.";
            
            sessionKeyString[6] = "Key for CAST5 (CAST-128) is 128 bits long." + "\n" + "\n" +
                                  "This means that 4 octets on the right are discarded";
        
    }
    
    
    //Data za Public Key Ring
    public static int publicKeyStepCounter = 0;
    public static String[] publicKeyString = new String[7];
    static {
            publicKeyString[0] = "Public Key Ring is a structure used to store public keys of other users " + 
                                 "that are known to this user.";
            
            publicKeyString[1] = "Timestamp, Key ID, Public Key and User ID fields have the same meaning " +
                                 "as in the Private Key Ring and some of them will be ignored in this example." + "\n" + "\n" +
                                 "Different fields that one Public Key Ring entry has are Owner Trust, Key Legitimacy," + "\n" +
                                 "Signatures and Signature Trusts.";
            
            publicKeyString[2] = "Owner Trust: Indicates the degree to which this public key is trusted to sign " + 
                                 "other public keys. Owner Trust field is user defined." + "\n" + "\n" +
                                 "This field can have 4 different values: UNDEFINED, NOT TRUSTED, USUALLY TRUSTED," + "\n" +
                                 "ALWAYS TRUSTED.";
            
            publicKeyString[3] = "Signatures and Signature Trusts: Ziro or more signatures that the Public Key Ring owner " + 
                                 "has collected. In turn, each signature has associated with it a signature trust field, " +
                                 "that indicates the degree to which this user trust the signer.";
            
            publicKeyString[4] = "Key Legitimacy: Indicates the extent to which PGP will trust that this is a valid " +
                                 "public key for this user." + "\n" + "\n" +
                                 "This field can have 4 different values: UNKNOWN, NOT TRUSTED, MARGINAL TRUST," + "\n" + 
                                 "COMPLETE TRUST";
            
            publicKeyString[5] = "Let's assume that the user has set the value of Owner Trust field of the key " + 
                                 "d2cf90e5552b853b owned by Phil Zimmermann to ALWAYS TRUSTED and then Phil " + 
                                 "Zimmermann signed the key with Key ID 5226cba4b0f71249 owned by Alan Turing." + "\n" + 
                                 "You can see that the Signature Trust for this Signature is the same as the Owner Trust " + 
                                 "of the key that was used for signing (d2cf90e5552b853b - ALWAYS TRUSTED).";
            
            publicKeyString[6] = "Key Legitimacy field has changed to COMPLETE TRUST because the key has been signed " + 
                                 "by other key which Owner Trust is set to ALWAYS TRUSTED.";
        
    }
    
    
    //Data za web of trust
    public static int webOfTrustStepCounter = 0;
    public static String[] webOfTrustString = new String[10];
    static {
            webOfTrustString[0] = "When the new public key is entered, one or more signatures may be attached to it." + "\n" + "\n" +
                                  "More signatures may be added later.";
            
            webOfTrustString[1] = "When a signature is inserted into the entry, PGP searches the Public Key Ring " + 
                                  "to see if the author of this signature is among the known public key owners. " +
                                  "If so, the Owner Trust value for this owner is assigned to the Signature Trust " + 
                                  "field for this signature. If not, an UNKNOWN value is assigned";
            
            webOfTrustString[2] = "The value of the key legitimacy field is calculated on the basis of the Signature " + 
                                  "Trust fields present in this entry.";
            
            webOfTrustString[3] = "In this example Phil Zimmermann is the owner of this Public Key Ring." + "\n" + "\n" + 
                                  "Public Key of the user Alan Turing is the new entry to this ring and " +
                                  "the three keys at the bottom of the screen are already in this ring.";
            
            webOfTrustString[4] = "If the owner of the key ring, in this case Phil Zimmermann, signes a key " +
                                  "Legitimacy field for that key automatically becomes COMPLETE TRUST." + "\n" + "\n" +
                                  "Note: Signature Trust of the signature that was made by the owner of the public key ring is ULTIMATE TRUST.";
            
            webOfTrustString[5] = "In other cases, PGP computes a weight sum of the trust values." + "\n" + 
                                  "Here a trust value of NOT TRUSTED signature is 0, USUALLY TRUSTED 0.5 " +
                                  "and ALWAYS TRUSTED 1. Legitimacy becomes MARGINAL TRUST when sum reaches 0.5 " + 
                                  "and it becomes COMPLETE TRUST when sum reaches 1." + "\n" + "\n" +
                                  "Note: Weight values can be user-configurable parameters.";
            
            webOfTrustString[6] = "When the user Julius Caesar signes the new key with the key which Owner Trust " + 
                                  "is set to USUALLY TRUSTED, the Legitimacy field changes to MARGINAL TRUST.";
            
            webOfTrustString[7] = "Then the user Don Coppersmith signes the new key with the key which Owner Trust " + 
                                  "is set to NOT TRUSTED, the Legitimacy field does not change because the weight of " + 
                                  "NOT TRUSTED signature is 0.";
            
            webOfTrustString[8] = "Then the user Clifford Cocks signes the new key with the key which Owner Trust " + 
                                  "is set to USUALLY TRUSTED, the Legitimacy field changes to COMPLETE TRUST " + 
                                  "because the sum of the Signature Trust weights has reached 1 (0.5 + 0 + 0.5).";
            
            webOfTrustString[9] = "Try changing Owner Trust values of the three key and see how it affects the " + 
                                  "Key Legitimacy field of the new key.";
        
    }
    
    
    //Data za negativnu autentikaciju
    public static int negativeStepCounter = 0;
    public static String[] negativeString = new String[7];
    public static boolean negative = false;
    static {
            negativeString[0] = "In this example you will see the strength of message authentication.";
        
            negativeString[1] = "The sender wants to inform the recipient about his new office space. So he creates a message with information about it.";
        
            negativeString[2] = "The sender does not care if someone else can read this info, but he wants to be sure that the data won't be changed." + "\n" + "\n" +
                                "For this purpose, sender signes this message and sends it.";
        
            negativeString[3] = "Attacker intercepts the message and changes only one digit of telephone number (336398 to 236398)";
        
            negativeString[4] = "The recipient receives the message and calculates the hash code of it.";
        
            negativeString[5] = "Then he decrypts the signature and compares two hash code values.";
        
            negativeString[6] = "These values do not match and recipient knows that this message cannot be trusted." + "\n" + "\n" +
                                "Notice how minimal difference in messages changes the hash code completely.";
            }
    
    
}
