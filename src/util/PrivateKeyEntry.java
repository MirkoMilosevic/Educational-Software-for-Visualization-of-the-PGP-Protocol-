
package util;

import java.math.BigInteger;
import java.security.PrivateKey;
import java.security.PublicKey;

/**
 *
 * @author Mirko
 */
public class PrivateKeyEntry 
{
    private String keyname;
    private String password;
    private String passwordSecurityLevel;
    private PrivateKey privateKey;
    private int type;
   
    private String timestamp;
    private String keyId;
    private PublicKey publicKey;
    private byte[] encryptedPrivateKey;
    private String encryptedPrivateKeyString;
    private String userId;
    
    private boolean active;
    
    private String reasonForRevocation;
    
    public PrivateKeyEntry()
    {
        reasonForRevocation = "";
    }
   

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getKeyId() {
        return keyId;
    }

    public void setKeyId(String keyId) {
        this.keyId = keyId;
    }

    public PublicKey getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(PublicKey publicKey) {
        this.publicKey = publicKey;
    }

    public byte[] getEncryptedPrivateKey() {
        return encryptedPrivateKey;
    }

    public void setEncryptedPrivateKey(byte[] encryptedPrivateKey) {
        this.encryptedPrivateKey = encryptedPrivateKey;
    }

    public PrivateKey getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(PrivateKey privateKey) {
        this.privateKey = privateKey;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getKeyname() {
        return keyname;
    }

    public void setKeyname(String keyname) {
        this.keyname = keyname;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getPasswordSecurityLevel() {
        return passwordSecurityLevel;
    }

    public void setPasswordSecurityLevel(String passwordSecurityLevel) {
        this.passwordSecurityLevel = passwordSecurityLevel;
    }

    public String getEncryptedPrivateKeyString() {
        return encryptedPrivateKeyString;
    }

    public void setEncryptedPrivateKeyString(String encryptedPrivateKeyString) {
        this.encryptedPrivateKeyString = encryptedPrivateKeyString;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getReasonForRevocation() {
        return reasonForRevocation;
    }

    public void setReasonForRevocation(String reasonForRevocation) {
        this.reasonForRevocation = reasonForRevocation;
    }
     
    
    
}
