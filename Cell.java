/**
 * Stores data for each cell of the console sweeper grid
 */
public class Cell 
{
    private char icon = '■';
    private char defaultIcon = icon;
    private double noiseValue = 0.0;
    private int x;
    private int y;
    private int nearbyMines = 0;
    private String type = "empty";
    private String status = "closed";
    
    public Cell(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
    public void generateCell(double noiseValue, String type)
    {
        this.noiseValue = noiseValue;
        this.type = type;
    }
    public void updateDefault()
    {
        //replace default icon and icon with the cell's open icon
        if (this.type.equals("mine"))
        {
            this.defaultIcon = '✹';
            
        }
        else if (this.nearbyMines > 0)
        {
            this.defaultIcon = (char)(this.nearbyMines + '0');
        }
        else
        {
            this.defaultIcon = '□';
        }
        this.icon = this.defaultIcon;
    }
    public void restoreDefault()
    {
        this.defaultIcon = '■';
        this.icon = this.defaultIcon;
    }
    public void updateStatus(String status)
    {
        if (!(this.status.equals("open")))
        {
            this.status = status;
            //unflag
            if (this.status.equals("closed"))
            {
                this.icon = this.defaultIcon;
            }
            //flag
            else if (this.status.equals("flagged"))
            {
                this.icon = '⚑';
            }
            //open tile
            else if (this.status.equals("open"))
            {
                //show mine if it is a mine
                if (this.type.equals("mine"))
                {
                    this.icon = '✹';
                }
                //show number of nearby mines
                else if (this.nearbyMines > 0)
                {
                    //cast to char value. It cannot be over 8, so no issues
                    this.icon = (char)(this.nearbyMines + '0');
                }
                //show empty block otherwise
                else
                {
                    this.icon = '□';
                }
            }
        }
    }
    public int getX()
    {
        return this.x;
    }
    public int getY()
    {
        return this.y;
    }
    public int getNearbyMines()
    {
        return this.nearbyMines;
    }
    public String getType()
    {
        return this.type;
    }
    public String getStatus()
    {
        return this.status;
    }
    public char getIcon()
    {
        return this.icon;
    }
    public void incNearbyMines()
    {
        this.nearbyMines++;
    }
    public void setType(String newType)
    {
        this.type = newType;
    }
}