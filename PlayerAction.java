/**
 * This class stores the player's action in a portable manner
 */
public class PlayerAction 
{
    int x;
    int y;
    String letter;
    public PlayerAction(int x, int y, String letter)
    {
        this.x = x;
        this.y = y;
        this.letter = letter;
    }
}