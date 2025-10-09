//import standerd libraries
import java.util.HashMap;
import java.util.Arrays;

public class Font 
{
    //create hashtable of charachters for font
    HashMap <Character, String[]> font = new HashMap<Character, String[]>();
    //init function- add charachter arrays to font hahsmap
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
        //loop through given charachters
        boolean first = true;
        for (char c : str.toCharArray())
        {
            String[] charachter = this.font.get(c);
            //loop through each string in charachter
            //move to print next charachter unless it is the 1st iteration
            if (first)
            {
                //print number of lines required to print charachters
                CursorControl.savePos();
                int length = str.length() * 4;
                
                char[] array = new char[length];
                Arrays.fill(array, '.');
                String emptyLines = new String(array);
                for (int i=0; i < 5; i++)
                {
                    System.out.println(emptyLines);
                }
                CursorControl.restorePos();
                first = false;  
            }
            else
            {
                //move up to print next charachter
                CursorControl.moveHorz(1);
                CursorControl.moveVert(6);
                CursorControl.moveHorz(4);
                CursorControl.savePos();
            }
            for (String string: charachter)
            {
                System.out.print(string +" ");
                //move back and down
                CursorControl.moveHorz(-4);
                CursorControl.moveVert(-1);
            }
        }
        
    }
    public void println(String str)
    {
        this.print(str);
        System.out.print("\n");
    }
}