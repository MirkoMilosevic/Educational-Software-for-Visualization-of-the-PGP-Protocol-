
package util;

/**
 *
 * @author Mirko
 */
public class MyMessage 
{
    private String filename;
    private String timestamp;
    private byte[] timestampBytes;
    private String data;
    private MySignature signature;

    public MyMessage(String filename, String data)
    {
        this.filename = filename;
        this.data = data;
        this.signature = new MySignature();
    }
    
    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public MySignature getSignature() {
        return signature;
    }

    public void setSignature(MySignature signature) {
        this.signature = signature;
    }

    public byte[] getTimestampBytes() {
        return timestampBytes;
    }

    public void setTimestampBytes(byte[] timestampBytes) {
        this.timestampBytes = timestampBytes;
    }
    
    
    
}
