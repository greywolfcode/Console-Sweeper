/**
 * Methods for moving the console cursor
 */
public class CursorControl 
{
    private CursorControl(){}
    /**
     * Move cursor to specified coordinents
     * 1;1 is top left and default
     */
    static void goTo(int x, int y)
    {
        System.out.print("\033[" + y + ";" + x +"H");
    }
    /**
     * reset cursor to begining of 1st line
     */
    static void reset()
    {
        System.out.print("\033[H");
    }
    /**
     * Move cursor vertically
     * positive is down, negative is up
     */
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
    /**
     * Move cursor horizontally
     * positive is right, negative is left
     */
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
    /**
     * Save current cursor position
     */
    static void savePos()
    {
        System.out.print("\033[s");
    }
    /**
     * Restore cursor to previously saved position
     */
    static void restorePos()
    {
        System.out.print("\033[u");
    }
}