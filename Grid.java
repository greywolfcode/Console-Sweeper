//import standard libraries
import java.lang.StringBuilder;

public class Grid 
{
    int size;
    Cell[][] cells;
    StringBuilder topLine = new StringBuilder(" ┃");
    public Grid(int gridSize)
    {
        this.size = gridSize;
        //create instances of cells for every part of the grid
        this.cells = new Cell[this.size][this.size];
        for (int y=0; y<this.cells.length; y++)
        {
            for (int x=0; x<this.cells[y].length; x++)
            {
                //get noise value
                double noiseValue = PerlinNoise.noise2D(x / 128.0, y / 128.0);
                this.cells[y][x] = new Cell(noiseValue);
            }
        }
        //create top line
        for (int i=0; i<this.cells[0].length; i++)
        {
            this.topLine.append((i + 1) + "┃");
        }
    }
    public void displayGrid()
    {
        //print top line of numbers
        System.out.println(this.topLine);
        for (int i=0; i<this.cells.length; i++)
        {
            //create StringBuilder object
            StringBuilder line = new StringBuilder();
            line.append(i + 1);
            line.append("┃");
            for (Cell cell : this.cells[i])
            {
                line.append(Math.round(cell.noiseValue * 100.0) / 100.0);
                line.append("┃");
            }
            System.out.println(line);

        }
    }
}