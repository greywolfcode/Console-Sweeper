//import standard libraries
import java.lang.StringBuilder;
import java.util.Random;

public class Grid 
{
    int size;
    Cell[][] cells;
    StringBuilder topLine = new StringBuilder(" ┃");
    public Grid(int gridSize)
    {
        this.size = gridSize;
        //create random number generator
        Random randGen = new Random();
        double randModifier = randGen.nextDouble() * 10;
        //create instances of cells for every part of the grid
        this.cells = new Cell[this.size][this.size];
        for (int y=0; y<this.cells.length; y++)
        {
            for (int x=0; x<this.cells[y].length; x++)
            {
                //get new random modifier to add a little bit more noise
                double randValue = randGen.nextDouble();
                //get noise value
                double noiseValue = PerlinNoise.noise2D((x + randModifier) / 10.0, (y + randModifier) / 10.0) * (randValue + 0.5);
                //check if random number is below noise value to use percent chance to make it a mine
                String cellType = "blank";
                if (randValue < noiseValue)
                {
                    cellType = "mine";
                }
                //create new cell object
                this.cells[y][x] = new Cell(x, y, noiseValue, cellType);
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