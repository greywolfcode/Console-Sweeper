/**
 * store grid information for transfer
 */
public class CustomGrid 
{
    private int width;
    private int height;
    private int mines;
    public CustomGrid(int width, int height, int mines) 
    {
        this.width = width;
        this.height = height;
        this.mines = mines;
    }
    public int getWidth()
    {
        return this.width;
    }
    public int getHeight()
    {
        return this.height;
    }
    public int getMines()
    {
        return mines;
    }
}