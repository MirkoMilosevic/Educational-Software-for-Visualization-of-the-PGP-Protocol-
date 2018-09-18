
package util;

/**
 *
 * @author Mirko
 */
public class SchemeData {
    
    public static boolean[] sLabel = new boolean[42];
    public static boolean[] sButton = new boolean[8];
    
    public static boolean[] eLabel = new boolean[44];
    public static boolean[] eButton = new boolean[9];
    
    public static boolean[] bLabel = new boolean[60];
    public static boolean[] bButton = new boolean[10];
    
    public static void clearS()
    {
        for(int i=1; i<42; i++)
        {
            sLabel[i] = false;
        }
        
        for(int j=1; j<8; j++)
        {
            sButton[j] = false;
        }
    }
    
    public static void clearE()
    {
        for(int i=1; i<44; i++)
        {
            eLabel[i] = false;
        }
        
        for(int j=1; j<9; j++)
        {
            eButton[j] = false;
        }
    }
    
     public static void clearB()
    {
        for(int i=1; i<60; i++)
        {
            bLabel[i] = false;
        }
        
        for(int j=1; j<10; j++)
        {
            bButton[j] = false;
        }
    }
    
}
