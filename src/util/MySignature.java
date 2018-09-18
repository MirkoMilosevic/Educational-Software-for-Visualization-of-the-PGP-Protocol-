
package util;

import java.security.Signature;

/**
 *
 * @author Mirko
 */
public class MySignature 
{
    private String timestamp;
    private byte[] timestampBytes;
    private String senderKeyId;
    private String leading2octets;
    private String messageDigest;
    private byte[] signature;
    private byte[] hash;

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getSenderKeyId() {
        return senderKeyId;
    }

    public void setSenderKeyId(String senderKeyId) {
        this.senderKeyId = senderKeyId;
    }

    public String getLeading2octets() {
        return leading2octets;
    }

    public void setLeading2octets(String leading2octets) {
        this.leading2octets = leading2octets;
    }

    public String getMessageDigest() {
        return messageDigest;
    }

    public void setMessageDigest(String messageDigest) {
        this.messageDigest = messageDigest;
    }

    public byte[] getHash() {
        return hash;
    }

    public void setHash(byte[] hash) {
        this.hash = hash;
    }

    public byte[] getSignature() {
        return signature;
    }

    public void setSignature(byte[] signature) {
        this.signature = signature;
    }

    public byte[] getTimestampBytes() {
        return timestampBytes;
    }

    public void setTimestampBytes(byte[] timestampBytes) {
        this.timestampBytes = timestampBytes;
    }
    
    
}
