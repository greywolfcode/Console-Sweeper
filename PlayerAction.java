/**
 * This class stores the player's action in a portable manner
 */
public class PlayerAction 
{
    private int x;
    private int y;
    private String letter;
    
    public PlayerAction(int x, int y, String letter)
    {
        this.x = x;
        this.y = y;
        this.letter = letter;
    }
    public int getX()
    {
        return this.x;
    }
    public int getY()
    {
        return this.y;
    }
    public String getLetter()
    {
        return letter;
    }
}