
package util;

/**
 *
 * @author Mirko
 */
public class SendMessageData 
{
    public static User sender;
    public static User receiver;
    public static MyMessage message;
    
    
    //Potpisivanje
    public static String hashAlgorithm;
    public static String keyId;
    public static String signatureAlgorithm;
    public static String calculatedHashValue;
    public static String publicKeyId;
    
    
    //Redosled prikazivanja prozora 
    public static int selectedMode = 0;
    
    
    //Sifrovanje
    public static byte[] seed = new byte[] 
    {
        (byte)0x33, (byte)0xa4, (byte)0x1f, (byte)0x7c, 
        (byte)0xb6, (byte)0x35, (byte)0xfd, (byte)0x03
    };
    public static byte[] sessionKey;
    public static byte[] encryptedSessionKey;
    public static String symetricAlgorithm;
    public static String publicKeyAlgorithm;
    public static String recipientPublicKeyId;
    public static byte[] encryptedMessage;
    
    
    //Kompresija
    public static byte[] compressedData;
    
    //Konverzija u Radix64
    public static String convertedData;
    
    //Segmentacija
    public static String beforeSegmentation;
    public static String segmentedData;
    
    
    public static void setSelectedMode(int mode, int zip, int r64)
    {
        selectedMode = 0;
        if(mode == 1) { selectedMode = 100; }
        if(mode == 2) { selectedMode = 200; }
        if(mode == 3) { selectedMode = 300; }
        
        if(r64 == 1) { selectedMode += 1; }
        if(zip == 1) { selectedMode += 10; }   
    }

    
    public static byte[] getSeed() {
        return seed;
    }

    
    public static void setSeed(byte[] seed) {
        SendMessageData.seed = seed;
    }
    
     
}
