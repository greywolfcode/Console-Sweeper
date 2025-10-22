//import standerd libraries
import java.util.Scanner;

public class ConsoleSweeper 
{
    public static void main(String[] args) 
    {
        //define state machine states
        enum States
        {
            Menu,
            Options,
            FirstTurn,
            Play,
        }
        boolean clearScreen = true;
        Grid playGrid = new Grid();
        //create instance of font class
        Font font = new Font();
        //State variable
        States gameState = States.Menu;
        //define input scanner
        Scanner input = new Scanner(System.in);
        //Main loop
        while (true)
        {
            //clear screen
            if (clearScreen)
            {
                System.out.print("\033[2J");
                CursorControl.reset();
            }
            font.println("Console Sweeper");
            //state machine
            if (gameState == States.Menu)
            {
                System.out.println();
                System.out.println("Options:");
                System.out.println("a: Play Console Sweeper");
                System.out.println("b: Exit Program");
                System.out.print("What would you like to do?");
                //Get user input
                String action = input.nextLine();
                //check what user input is
                if (action.equals("a"))
                {
                    gameState = States.Options;
                }
                else if (action.equals("b"))
                {
                    break;
                }
            }
            else if (gameState == States.Options)
            {
                //create grid object
                playGrid.setup(12, 20);
                gameState = States.FirstTurn;
            }
            else if(gameState == States.FirstTurn)
            {
                //display stuff and get action
                displayGrid(playGrid);
                String action = input.nextLine();
                if (action.length() < 4)
                {
                    continue;
                }
                PlayerAction actionValues = processAction(playGrid, action);
                //generate mines around selected point
                playGrid.generateMines(actionValues.x, actionValues.y);
                //run move on 1st click
                playGrid.modifyCell(actionValues);
                //set state to normal play
                gameState = States.Play;
                
            }
            else if (gameState == States.Play)
            {
                //dislay stuff and get action
                displayGrid(playGrid);
                String action = input.nextLine();
                //parse command
                if (action.length() < 4)
                {
                    continue;
                }
                //check for cheat codes
                if (action.equals("blackSheepWall"))
                {
                    playGrid.viewGrid();
                }
                else if (action.equals("theFogReturns"))
                {
                    playGrid.hideGrid();
                }
                else if (action.equals("noClear"))
                {
                    clearScreen = false;
                }
                else if (action.equals("doClear"))
                {
                    clearScreen = true;
                }
                else
                {
                    PlayerAction actionValues = processAction(playGrid, action);
                    playGrid.modifyCell(actionValues);
                }
            }
        }
        input.close();
    }
    private static PlayerAction processAction(Grid grid, String action)
    {
        //split to get proper command
        String letter = action.substring(0, 1);
        String[] coords = action.substring(1).split(";");
        int x = Integer.parseInt(coords[0]) - 1;
        int y = Integer.parseInt(coords[1]) - 1;
        return new PlayerAction(x, y, letter);
    }
    private static void displayGrid(Grid grid)
    {
        grid.displayGrid();
        System.out.println("f=flag o=open u=unflag");
        System.out.println("x;y (f1;1)");
        System.out.print("Your action: ");
    }
}