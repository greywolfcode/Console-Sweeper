//import standerd libraries
import java.util.HashMap;
import java.lang.StringBuilder;

public class Font 
{
    //create hashtable of charachters for font
    HashMap <Character, String[]> font = new HashMap<Character, String[]>();
    //constructor- add charachter arrays to font hahsmap
    public Font()
    {
        //create and add font values
        String[] space = {"   ", "   ", "   ", "   ", "   "};
        this.font.put(' ', space);
        String[] a = {"███", "█ █", "███", "█ █", "█ █"};
        this.font.put('a', a);
        String[] b = {"██ ", "█ █", "██ ", "█ █", "██ "};
        this.font.put('b', b);
        String[] c = {" ██", "█  ", "█  ", "█  ", " ██"};
        this.font.put('c', c);
        String[] d = {"██ ", "█ █", "█ █", "█ █", "██ "};
        this.font.put('d', d);
        String[] e = {"███", "█  ", "██ ", "█  ", "███"};
        this.font.put('e', e);
        String[] f = {"███", "█  ", "██ ", "█  ", "█  "};
        this.font.put('f', f);
        String[] g = {" ██", "█  ", "█ █", "█ █", "██ "};
        this.font.put('g', g);
        String[] h = {"█ █", "█ █", "███", "█ █", "█ █"};
        this.font.put('h', h);
        String[] i = {"███", " █ ", " █ ", " █ ", "███"};
        this.font.put('i', i);
        String[] j = {"  █", "  █", "  █", "█ █", "███"};
        this.font.put('j', j);
        String[] k = {"█ █", "██ ", "█ ", "██ ", "█ █"};
        this.font.put('k', k);
        String[] l = {"█  ", "█  ", "█  ", "█  ", "███"};
        this.font.put('l', l);
        String[] m = {"█ █", "███", "█ █", "█ █", "█ █"};
        this.font.put('m', m);
        String[] n = {"███", "█ █", "█ █", "█ █", "█ █"};
        this.font.put('n', n);
        String[] o = {"███", "█ █", "█ █", "█ █", "███"};
        this.font.put('o', o);
        String[] p = {"███", "█ █", "███", "█  ", "█  "};
        this.font.put('p', p);
        String[] q = {"███", "█ █", "█ █", " ███", " ██"};
        this.font.put('q', q);
        String[] r = {"███", "█ █", "███", "██ ", "█ █"};
        this.font.put('r', r);
        String[] s = {"███", "█  ", "███", "  █", "███"};
        this.font.put('s', s);
        String[] t = {"███", " █ ", " █ ", " █ ", " █ "};
        this.font.put('t', t);
        String[] u = {"█ █", "█ █", "█ █", "█ █", "███"};
        this.font.put('u', u);
        String[] v = {"█ █", "█ █", "█ █", "█ █", " █ "};
        this.font.put('v', v);
        String[] w = {"█ █", "█ █", "█ █", "███", "█ █"};
        this.font.put('w', w);
        String[] x = {"█ █", "█ █", " █ ", "█ █", "█ █"};
        this.font.put('x', x);
        String[] y = {"█ █", "█ █", " █ ", " █ ", " █ "};
        this.font.put('y', y);
        String[] z = {"███", "  █", " █ ", "█  ", "███"};
        this.font.put('z', z);
        }
    //method to print text to screen without newline
    public void print(String str)
    {   
        //all keys are lowercase
        str = str.toLowerCase();
        //split string at all new lines
        String[] lines = str.split("\n");
        //print each new line
        for (String line : lines)
        {
            printChars(line);
        }
    }
    //method to actually print charachters
    private void printChars(String str)
    {
        //create the string builder objects
        StringBuilder one = new StringBuilder();
        StringBuilder two = new StringBuilder();
        StringBuilder three = new StringBuilder();
        StringBuilder four = new StringBuilder();
        StringBuilder five = new StringBuilder();
        //loop through given charachters
        boolean first = true;
        for (char c : str.toCharArray())
        {
            String[] charachter = this.font.get(c);
            //loop through each string in charachter
            for (int i = 1; i < 6; i++)
            {
                if (i==1)
                {
                    one.append(charachter[0] + " ");
                }
                else if (i==2)
                {
                    two.append(charachter[1] + " ");
                }
                else if (i==3)
                {
                    three.append(charachter[2] + " ");
                }
                else if (i==4)
                {
                    four.append(charachter[3] + " ");
                }
                else if (i==5)
                {
                    five.append(charachter[4] + " ");
                }
            }
        }
        //display the strings
        System.out.println(one.toString());
        System.out.println(two.toString());
        System.out.println(three.toString());
        System.out.println(four.toString());
        System.out.println(five.toString());
        
    }
    public void println(String str)
    {
        this.print(str);
        System.out.print("\n");
    }
}