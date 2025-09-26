public class Grid 
{
    int size;
    Cell[][] cells;
    public Grid(int gridSize)
    {
        size = gridSize;
        //create instances of cells for every part of the grid
        cells = new Cell[size][size];
    }
    public static void main(String[] args) 
    {
    }
}