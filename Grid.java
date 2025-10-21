//import standard libraries
import java.lang.StringBuilder;
import java.util.Random;
import java.util.Queue;
import java.util.LinkedList;

public class Grid 
{
    int size;
    Cell[][] cells;
    StringBuilder topLine = new StringBuilder(" ┃");
    Cell[] mines;
    boolean firstTurn = true;
    /*constructor doesn't do anything*/
    public Grid(){}
    /*setup method*/
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
    private void decrementNearbyMines(int xMine, int yMine)
    {
        for (int y=yMine-1; y<=yMine+1; y++)
        {
            for (int x=xMine-1; x<=xMine+1; x++)
            {
                //decrement mine count if it is in range
                if ((x>=0 && y>= 0) && (x<this.size && y<this.size))
                {
                    this.cells[y][x].nearbyMines--;
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
            //update first turn to false
            this.firstTurn = false;
        }
    }
    private void openCell(int x, int y)
    {
        //explode all mines
        if (this.cells[y][x].type.equals("mine"))
        {
            //don't open mines if on 1st turn
            if (this.firstTurn)
            {
                //set current cell to not be a mine
                this.cells[y][x].type = "empty";
                //decrement mine counts around moved mine
                decrementNearbyMines(x, y);
                //find new spot for the mine
                moveMine(this.cells[y][x].mineNum);
                doCascade(x, y);
                return;
            }
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
    private void moveMine(int mineNum)
    {
        Random randGen = new Random();
        //loop until valid position is found
        int x;
        int y;
        while (true)
        {
            x = randGen.nextInt();
            y = randGen.nextInt();
            if (this.cells[y][x].type == "empty")
            {
                this.cells[y][x].type = "mine";
                this.cells[y][x].mineNum = mineNum;
                //replace mine in list with new mine
                this.mines[mineNum] = this.cells[y][x];
                //update surrounding numbers
                updateNearbyMines(x, y);
            }
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