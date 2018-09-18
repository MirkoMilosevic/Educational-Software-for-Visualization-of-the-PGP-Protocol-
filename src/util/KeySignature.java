
package util;

/**
 *
 * @author Mirko
 */
public class KeySignature {
    
    private String userID;
    private String keyID;
    private String signatureTrust;
    
    public KeySignature(String user, String key)
    {
        userID = user;
        keyID = key;
        signatureTrust = "UNDEFINED";
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getKeyID() {
        return keyID;
    }

    public void setKeyID(String keyID) {
        this.keyID = keyID;
    }

    public String getSignatureTrust() {
        return signatureTrust;
    }

    public void setSignatureTrust(String signatureTrust) {
        this.signatureTrust = signatureTrust;
    }
    
    
    
}
