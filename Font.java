//import stnaderd libraries
import java.util.HashMap;

public class Font 
{
    //create hashtable of charachtes for font
    
    HashMap <String, String[]> font = new HashMap<String, String[]>();
    
    public Font()
    {
        //add font values
        String[] a = {"███", "█ █", "███", "█ █", "█ █"};
        this.font.put("a", a);
        String[] b = {"", "", "", "", ""};
        this.font.put("b", b);
        String[] c = {"", "", "", "", ""};
        this.font.put("c", c);
        String[] d = {"", "", "", "", ""};
        this.font.put("d", d);
        String[] e = {"", "", "", "", ""};
        this.font.put("e", e);
        String[] f = {"", "", "", "", ""};
        this.font.put("f", f);
    }
    //method to print text to screen
    public void print(String str)
    {
        
    }
    public void println(String str)
    {
        
    }
}