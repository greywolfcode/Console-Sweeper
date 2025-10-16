public class Cell 
{
    char icon = '■';
    //u2739
    double noiseValue;
    int x;
    int y;
    String type;
    public Cell(int x, int y, double noiseValue, String type)
    {
        this.noiseValue = noiseValue;
        this.x = x;
        this.y = y;
        this.type = type;
    }
}