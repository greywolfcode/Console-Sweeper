//static class
public class CursorControl 
{
    private CursorControl()
    {
    }
    //set cursor to specific position
    static void goTo(int x, int y)
    {
        System.out.print("\033[" + y + ";" + x +"H");
    }
    //resets cursor to begining of 1st line
    static void reset()
    {
        System.out.print("\033[H");
    }
    static void moveVert(int y)
    {
        if (y < 0)
        {
            System.out.print("\033[" + (-1 * y) + "B");
        }
        else
        {
            System.out.print("\033[" + y + "A");
        }
    }
    static void moveHorz(int x)
    {
        if (x < 0)
        {
            System.out.print("\033[" + (-1 * x) + "D");
        }
        else
        {
            System.out.println("\033[" + x + "C");
        }
    }
    static void savePos()
    {
        System.out.print("\033[s");
    }
    static void restorePos()
    {
        System.out.print("\033[u");
    }
}