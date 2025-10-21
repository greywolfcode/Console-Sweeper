public class Cell 
{
    char icon = '■';
    char defaultIcon = icon;
    double noiseValue;
    int x;
    int y;
    int nearbyMines = 0;
    //store index in mine array
    int mineNum = 0;
    String type = "empty";
    String status = "closed";
    public Cell(int x, int y, double noiseValue, String type)
    {
        this.noiseValue = noiseValue;
        this.x = x;
        this.y = y;
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
                    //cast to char value. It cannot be over 8, so n issues
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
}