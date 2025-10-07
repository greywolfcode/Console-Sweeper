//import stnaderd libraries
import java.util.HashMap;

public class Font 
{
    //create hashtable of charachters for font
    HashMap <String, String[]> font = new HashMap<String, String[]>();
    //init function- add charachter arrays to font hahsmap
    public Font()
    {
        //create and add font values
        String[] space = {"   ", "   ", "   ", "   ", "   "};
        this.font.put(" ", space);
        String[] a = {"███", "█ █", "███", "█ █", "█ █"};
        this.font.put("a", a);
        String[] b = {"██ ", "█ █", "██ ", "█ █", "██ "};
        this.font.put("b", b);
        String[] c = {" ██", "█  ", "█  ", "█  ", " ██"};
        this.font.put("c", c);
        String[] d = {"██ ", "█ █", "█ █", "█ █", "██ "};
        this.font.put("d", d);
        String[] e = {"███", "█  ", "██ ", "█", "███"};
        this.font.put("e", e);
        String[] f = {"███", "█  ", "██ ", "█  ", "█  "};
        this.font.put("f", f);
        String[] g = {" ██", "█  ", "█ █", "█ █", "██ "};
        this.font.put("g", g);
        String[] h = {"█ █", "█ █", "███", "█ █", "█ █"};
        this.font.put("h", h);
        String[] i = {"███", " █ ", " █ ", " █ ", "███"};
        this.font.put("i", i);
        String[] j = {"  █", "  █", "  █", "█ █", "███"};
        this.font.put("j", j);
        String[] k = {"█ █", "██ ", "█ ", "██ ", "█ █"};
        this.font.put("k", k);
        String[] l = {"█  ", "█  ", "█  ", "█  ", "███"};
        this.font.put("l", l);
        String[] m = {"█ █", "███", "█ █", "█ █", "█ █"};
        this.font.put("m", m);
        String[] n = {"███", "█ █", "█ █", "█ █", "█ █"};
        this.font.put("n", n);
        String[] o = {"███", "█ █", "█ █", "█ █", "███"};
        this.font.put("o", o);
        String[] p = {"███", "█ █", "███", "█  ", "█  "};
        this.font.put("p", p);
        String[] q = {"███", "█ █", "█ █", " ███", " ██"};
        this.font.put("q", q);
        String[] r = {"███", "█ █", "███", "██ ", "█ █"};
        this.font.put("r", r);
        String[] s = {"███", "█  ", "███", "  █", "███"};
        this.font.put("s", s);
        String[] t = {"███", " █ ", " █ ", " █ ", " █ "};
        this.font.put("t", t);
        String[] u = {"█ █", "█ █", "█ █", "█ █", "███"};
        this.font.put("u", u);
        String[] v = {"█ █", "█ █", "█ █", "█ █", " █ "};
        this.font.put("v", v);
        String[] w = {"█ █", "█ █", "█ █", "███", "█ █"};
        this.font.put("w", w);
        String[] x = {"█ █", "█ █", " █ ", "█ █", "█ █"};
        this.font.put("x", x);
        String[] y = {"█ █", "█ █", " █ ", " █ ", " █ "};
        this.font.put("y", y);
        String[] z = {"███", "  █", " █ ", "█  ", "███"};
        this.font.put("z", z);
        }
    //method to print text to screen
    public void print(String str)
    {
        
    }
    public void println(String str)
    {
        
    }
}