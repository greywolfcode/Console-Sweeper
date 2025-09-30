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
    }
    //method to print text to screen
    public void print(String str)
    {
        
    }
    public void println(String str)
    {
        
    }
}