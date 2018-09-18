
package util;

import java.math.BigInteger;
import java.security.PublicKey;
import java.util.ArrayList;

/**
 *
 * @author Mirko
 */
public class PublicKeyEntry 
{
    private String description;
    private String timestamp;
    private String keyId;
    private PublicKey publicKey;
    private String userId;
    private String keyLegitimacy;
    private int type;
    private boolean active;
    private ArrayList<KeySignature> signatures;
    private String ownerTrust;
    
    private String reasonForRevocation;

    public PublicKeyEntry()
    {
        signatures = new ArrayList<KeySignature>();
        ownerTrust = "UNDEFINED";
        keyLegitimacy = "UNKNOWN";
        reasonForRevocation = "";
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

    public String getOwnerTrust() {
        return ownerTrust;
    }

    public void setOwnerTrust(String ownerTrust) {
        this.ownerTrust = ownerTrust;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getKeyLegitimacy() {
        return keyLegitimacy;
    }

    public void setKeyLegitimacy(String keyLegitimacy) {
        this.keyLegitimacy = keyLegitimacy;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public ArrayList<KeySignature> getSignatures() {
        return signatures;
    }

    public void setSignatures(ArrayList<KeySignature> signatures) {
        this.signatures = signatures;
    }

    public String getReasonForRevocation() {
        return reasonForRevocation;
    }

    public void setReasonForRevocation(String reasonForRevocation) {
        this.reasonForRevocation = reasonForRevocation;
    }
    
    
    
    public boolean signatureAlreadyThere(KeySignature ks)
    {
        if(!signatures.isEmpty())
        {
            for(int i=0; i<signatures.size(); i++)
            {
                if( (signatures.get(i).getUserID().equals(ks.getUserID())) && 
                    (signatures.get(i).getKeyID().equals(ks.getKeyID())) )
                {
                    return true;
                }
            }
            return false;
        }
        
        return false;
    }
    
    /*
    public void setSignatureTrusts(User u)
    {
        if(!signatures.isEmpty())
        {
            for(int i=0; i<signatures.size(); i++)
            {
                if(u.searchForKeyById(signatures.get(i).getKeyID()) != null)
                {
                    signatures.get(i).setSignatureTrust("ULTIMATE TRUST");
                }
                else if(u.getPublicKeyById(signatures.get(i).getKeyID()) != null)
                {
                    signatures.get(i).setSignatureTrust(u.getPublicKeyById(signatures.get(i).getKeyID()).getOwnerTrust());
                }
                else
                {
                    signatures.get(i).setSignatureTrust("KEY UNKNOWN");
                }
            }
        }
    }
    
    public void calculateLegitimacy()
    {
        if(!signatures.isEmpty())
        {
            double weightSum = 0;
            boolean ownerSignedKey = false;
            boolean undefined = true;
            
            for(int i=0; i<signatures.size(); i++)
            {
                if(signatures.get(i).getSignatureTrust().equals("ULTIMATE TRUST"))
                {
                    ownerSignedKey = true;
                    undefined = false;
                }
                else
                {
                    if(signatures.get(i).getSignatureTrust().equals("NOT TRUSTED"))
                    {
                        weightSum += 0;
                        undefined = false;
                    }
                    
                    if(signatures.get(i).getSignatureTrust().equals("USUALLY TRUSTED"))
                    {
                        weightSum += 0.5;
                        undefined = false;
                    }
                    
                    if(signatures.get(i).getSignatureTrust().equals("ALWAYS TRUSTED"))
                    {
                        weightSum += 1;
                        undefined = false;
                    }
                }
            }
            
            if(undefined)
            {
                this.setKeyLegitimacy("UNDEFINED");
                return;
            }
            
            if(ownerSignedKey)
            {
                this.setKeyLegitimacy("COMPLETE TRUST");
                return;
            }
            
            if(weightSum < 0.5)
            {
                this.setKeyLegitimacy("NOT TRUSTED");
                return;
            }
            
            if(weightSum < 1)
            {
                this.setKeyLegitimacy("MARGINAL TRUST");
            }
            else
            {
                this.setKeyLegitimacy("COMPLETE TRUST");
            }
            
        }
    }

*/
    
}
