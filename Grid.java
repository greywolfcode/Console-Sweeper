//import standard libraries
import java.lang.StringBuilder;
import java.util.Random;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Collections;

/**
 * Stores all methods for munipulating the console sweeper grid
 */
public class Grid 
{
    int size;
    Cell[][] cells;
    StringBuilder topLine = new StringBuilder(" ┃");
    Cell[] mines;
    int maxMines;
    /**constructor doesn't do anything*/
    public Grid(){}
    /**setup method*/
    public void setup(int gridSize, int maxMines)
    {
        this.size = gridSize;
        this.maxMines = maxMines;
        //create instances of cells for every part of the grid
        this.cells = new Cell[this.size][this.size];
        //create list of mines
        this.mines = new Cell[maxMines];
        //loop thorugh all coordinents
        for (int y=0; y<this.cells.length; y++)
        {
            for (int x=0; x<this.cells[y].length; x++)
            {
                //create new cell object
                this.cells[y][x] = new Cell(x, y);
            }
        }
        //create top line
        for (int i=0; i<this.cells[0].length; i++)
        {
            this.topLine.append((i + 1) + "┃");
        }
    }
    /**
     * This should be called once after the first move of a game
     */
    public void generateMines(int restrictedX, int restrictedY)
    {
        //create random number generator
        Random randGen = new Random();
        double randModifier = randGen.nextDouble() * 10;
        int currentMines = 0;
        String cellType;
        //create set of non-mine cells
        LinkedList<Cell> nonMines = new LinkedList<>();
        //loop through all cells
        for (Cell[] row : this.cells)
        {
            for (Cell cell : row)
            {
                //get random modifier to add a little bit more noise
                double randValue = randGen.nextDouble();
                //get noise value
                double noiseValue = PerlinNoise.noise2D((cell.x + randModifier) / 10.0, (cell.y + randModifier) / 10.0) * (randValue);
                //check if random number is below noise value to use percent chance to make it a mine
                cellType = "empty";
                if (randValue < noiseValue && currentMines < this.maxMines)
                {
                    //add cell to array of mine cells; prevent cell from being clicked on cell
                    if (cell.x != restrictedX && cell.y != restrictedY)
                    {
                        cellType = "mine";
                        this.mines[currentMines] = cell;
                        currentMines++;
                    }
                }
                if (cellType.equals("empty") && cell.x != restrictedX && cell.y != restrictedY)
                {
                    nonMines.add(cell);
                }
                cell.generateCell(noiseValue, cellType);
            }
        }
        //shuffle list to get random order
        Collections.shuffle(nonMines);
        //fill in remaining spaces with random values
        while (currentMines < this.maxMines)
        {
            //get out clause, just in case
            if (nonMines.size() == 0)
                break;
            Cell newMine = nonMines.removeFirst();
            newMine.type = "mine";
            this.mines[currentMines] = newMine;
            currentMines++;
        }
        validateGrid();
    }
    private void validateGrid()
    {
        //loop thorugh cells to set mine numbers
        for(Cell mine : this.mines)
        {
            updateNearbyMines(mine.x, mine.y);
        }
    }
    private void updateNearbyMines(int xMine, int yMine)
    {
        //loop through surrounding cells. improve later?
        for (int y=yMine-1; y<=yMine+1; y++)
        {
            for (int x=xMine-1; x<=xMine+1; x++)
            {
                //increase nearby mine count if it is in range
                if ((x >=0 && y >= 0) && (x<this.size && y<this.size))
                {
                    this.cells[y][x].nearbyMines++;
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
    public void modifyCell(PlayerAction action)
    {
        if (action.letter.equals("f"))
        {
            this.cells[action.y][action.x].updateStatus("flagged");
        }
        else if (action.letter.equals("u"))
        {
            this.cells[action.y][action.x].updateStatus("closed");
        }
        else if (action.letter.equals("o"))
        {
            this.openCell(action.x, action.y);
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
            doCascade(x, y);
        }
    }
    private void doCascade(int x, int y)
    {
        //create stack to hold objects for cascade
        Queue<int[]> cellsToSearch = new LinkedList<>();
        //add selected cell to stack
        cellsToSearch.add(new int[] {x, y});
        int[] coords;
        //loop until queue is empty
        while(!cellsToSearch.isEmpty())
        {
            coords = cellsToSearch.poll();
            this.cells[coords[1]][coords[0]].updateStatus("open");
            if (this.cells[coords[1]][coords[0]].nearbyMines == 0)
            {
                //put surrounding cells onto the stack. is there a better way to do this?
                if (coords[0] + 1 < this.size)
                {
                    if (!this.cells[coords[1]][coords[0]+1].status.equals("open"))
                    {
                        cellsToSearch.add(new int[] {coords[0]+1, coords[1]});
                    }        
                }
                if (coords[0] - 1 >= 0)
                {
                    if (!this.cells[coords[1]][coords[0]-1].status.equals("open"))
                    {
                        cellsToSearch.add(new int[] {coords[0]-1, coords[1]});
                    }
                }
                if (coords[1] + 1 < this.size)
                {
                    if (!this.cells[coords[1]+1][coords[0]].status.equals("open"))
                    {
                        cellsToSearch.add(new int[] {coords[0], coords[1]+1});
                    }
                }
                if (coords[1] - 1 >= 0)
                {
                    if (!this.cells[coords[1]-1][coords[0]].status.equals("open"))
                    {
                        cellsToSearch.add(new int[] {coords[0], coords[1]-1});
                    }
                }
            }
        }
    }
}