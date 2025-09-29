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
}