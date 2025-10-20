//import standard libraries
import java.lang.StringBuilder;
import java.util.Random;
import java.util.Stack;

public class Grid 
{
    int size;
    Cell[][] cells;
    StringBuilder topLine = new StringBuilder(" ┃");
    Cell[] mines;
    public Grid()
    {
    }
    public void setup(int gridSize, int maxMines)
    {
        this.size = gridSize;
        //create random number generator
        Random randGen = new Random();
        double randModifier = randGen.nextDouble() * 10;
        //create instances of cells for every part of the grid
        this.cells = new Cell[this.size][this.size];
        //create list of mines
        this.mines = new Cell[maxMines];
        //loop thorugh all coordinents
        int currentMines = 0;
        String cellType;
        for (int y=0; y<this.cells.length; y++)
        {
            for (int x=0; x<this.cells[y].length; x++)
            {
                //get new random modifier to add a little bit more noise
                double randValue = randGen.nextDouble();
                //get noise value
                double noiseValue = PerlinNoise.noise2D((x + randModifier) / 10.0, (y + randModifier) / 10.0) * (randValue);
                //check if random number is below noise value to use percent chance to make it a mine
                cellType = "empty";
                if (randValue < noiseValue && currentMines < maxMines)
                {
                    cellType = "mine";
                    currentMines ++;
                }
                //create new cell object
                this.cells[y][x] = new Cell(x, y, noiseValue, cellType);
                //need to create cell object first. Probably a better way to do this
                if (cellType.equals("mine"))
                {
                    this.mines[currentMines - 1] = this.cells[y][x];
                }
            }
        }
        //fill in remaining spaces with random values
        while (currentMines < maxMines)
        {
            int x = randGen.nextInt(this.size);
            int y = randGen.nextInt(this.size);
            if (!(this.cells[y][x].type.equals("mine")))
            {
                this.cells[y][x].type = "mine";
                this.mines[currentMines] = this.cells[y][x];
                currentMines++;
            }
        }
        //validate grid
        this.validateGrid();
        //create top line
        for (int i=0; i<this.cells[0].length; i++)
        {
            this.topLine.append((i + 1) + "┃");
        }
    }
    private void validateGrid()
    {
        //loop thorugh cells to set mine numbers
        for(Cell mine : this.mines)
        {
            //loop through surrounding cells. improve later?
            for (int y=mine.y-1; y<=mine.y+1; y++)
            {
                for (int x=mine.x-1; x<=mine.x+1; x++)
                {
                    //increase nearby mine count if it is not the mine
                    if ((x >=0 && y >= 0) && (x<this.size && y<this.size))
                    {
                        this.cells[y][x].nearbyMines++;
                    }
                }
            }
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
                line.append(cell.icon);
                line.append("┃");
            }
            System.out.println(line);

        }
    }
    public void viewGrid()
    {
        //loop through rows and show them
        for (Cell[] row:this.cells)
        {
            for (Cell cell:row)
            {
                cell.updateDefault();
            }
        }
    }
    public void hideGrid()
    {
        //loop though rows of cells and only show open ones
        for (Cell[] row:this.cells)
        {
            for (Cell cell:row)
            {
                if (!(cell.status.equals("open")))
                {
                    cell.restoreDefault();
                }
            }
        }
    }
    public void modifyCell(String command, int x, int y)
    {
        if (command.equals("f"))
        {
            this.cells[y][x].updateStatus("flagged");
        }
        else if (command.equals("u"))
        {
            this.cells[y][x].updateStatus("closed");
        }
        else if (command.equals("o"))
        {
            this.openCell(x, y);
        }
    }
    private void openCell(int x, int y)
    {
        //explode all mines
        if (this.cells[y][x].type.equals("mine"))
        {
            for (Cell mine:this.mines)
            {
                mine.updateStatus("open");
            }
        }
        else
        {
            //create stack to hold objects for cascade
            Stack<Cell> emptyCells = new Stack();
            //add selected cell to stack
            emptyCells.push(this.cells[y][x]);
            //loop until stack is empty
            int currentX = 0;
            int currentY = 0;
            Cell currentCell;
            while(!emptyCells.empty())
            {
                currentCell = emptyCells.pop();
                if (currentCell.nearbyMines == 0 && !currentCell.type.equals("open"))
                {
                    currentX = currentCell.x;
                    currentY = currentCell.y;
                    //put surrounding cells onto the stack. is there a better way to do this?
                    if (currentX + 1 < this.size)
                    {
                        emptyCells.push(this.cells[currentY][currentX+1]);
                    }
                    if (currentX - 1 >= 0)
                    {
                        emptyCells.push(this.cells[currentY][currentX-1]);
                    }
                    if (currentY + 1 < this.size)
                    {
                        emptyCells.push(this.cells[currentY+1][currentX]);
                    }
                    if (currentY - 1 >= 0)
                    {
                        emptyCells.push(this.cells[currentY-1][currentX]);
                    }
                }
                currentCell.updateStatus("open");
                System.out.println(emptyCells);
            }
        }
    }
}