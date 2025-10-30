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
            Lost,
            Won;
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
                System.out.print("What would you like to do? ");
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
                int width;
                int height;
                int mines;
                CustomGrid data;
                String action;
                //loop until a correct input is given
                while (true)
                {
                    System.out.println("Options");
                    System.out.println("a: Beginner: 9x9, 10 mines");
                    System.out.println("b: Intermediate: 16x16, 40 mines");
                    System.out.println("c: Expert: 30x16, 99 mines");
                    System.out.println("d: Custom");
                    System.out.print("What would you like to do? ");
                    action = input.nextLine();
                    if (action.equals("a") || action.equals("b") || action.equals("c") || action.equals("d"))
                    {
                        break;
                    }
                }
                if (action.equals("a"))
                {
                    width = 9;
                    height = 9;
                    mines = 10;
                }
                else if (action.equals("b"))
                {
                    width = 16;
                    height = 16;
                    mines = 40;
                }
                else if(action.equals("c"))
                {
                    width = 30;
                    height = 16;
                    mines = 99;
                }
                else
                {
                    data = getCustomGrid(font, clearScreen);
                    width = data.getWidth();
                    height = data.getHeight();
                    mines = data.getMines();
                }
                //create grid object
                playGrid.setup(width, height, mines);
                gameState = States.FirstTurn;
            }
            else if(gameState == States.FirstTurn)
            {
                //display stuff and get action
                displayGrid(playGrid);
                String action = input.nextLine();
                if (validateAction(action))
                {
                    continue;
                }
                PlayerAction actionValues = processAction(playGrid, action);
                //generate mines around selected point
                playGrid.generateMines(actionValues.getX(), actionValues.getY());
                //run move on 1st click
                char state = playGrid.modifyCell(actionValues);
                if (state == 'w')
                {
                    gameState = States.Won;
                    continue;
                }
                //set state to normal play
                gameState = States.Play;
                
            }
            else if (gameState == States.Play)
            {
                //dislay stuff and get action
                displayGrid(playGrid);
                String action = input.nextLine();
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
                else if (action.equals("menu"))
                {
                    playGrid = new Grid();
                    gameState = States.Menu;
                }
                else if (action.equals("quit"))
                {
                    break;
                }
                else
                {
                    if (validateAction(action))
                    {
                        continue;
                    }
                    PlayerAction actionValues = processAction(playGrid, action);
                    //check if the player lost
                    char state = playGrid.modifyCell(actionValues);
                    if (state == 'l')
                    {
                        gameState = States.Lost;
                    }
                    else if (state == 'w')
                    {
                        gameState = States.Won;
                    }
                }
            }
            else if (gameState == States.Lost)
            {
                displayGrid(playGrid);
                System.out.print("\n"); //need newline for text to render correctly
                font.println("You Lose");
                System.out.println("Press enter to return to Menu");
                String action = input.nextLine();
                //reset grid
                playGrid = new Grid();
                gameState = States.Menu;
            }
            else if (gameState == States.Won)
            {
                displayGrid(playGrid);
                System.out.println();
                font.println("You Won!");
                System.out.println("press enter to return to Menu");
                String action = input.nextLine();
                //reset grid
                playGrid = new Grid();
                gameState = States.Menu;
            }
        }
        input.close();
    }
    /**
     * Checks if an input strig is a valid command
     * returns true it it is Invalid 
     * returns false if it is Valid
     */
    private static boolean validateAction(String action)
    {
        if (action.length() < 4)
        {
            return true;
        }
        else if (!action.substring(1).contains(";"))
        {
            return true;
        }
        else
        {
            String[] coords = action.substring(1).split(";");
            if (coords[0].matches("\\d+") && coords[1].matches("\\d+"))
            {
                return false;
            }
            return true;
        }
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
        System.out.println("Example: o1;2 (column;row)");
        System.out.print("Your action: ");
    }
    private static CustomGrid getCustomGrid(Font font, boolean clearScreen)
    {
        int width = 0;
        int height = 0;
        int mines = 0;
        //create scanner for getting ints
        Scanner intInput = new Scanner(System.in);
        //loop until valid input is given
        while (true)
        {
            if (clearScreen)
            {
                System.out.println("\033[2J");
                CursorControl.reset();
            }
            font.println("Console Sweeper");
            System.out.print("Width: ");
            width = intInput.nextInt();
            System.out.print("Height: ");
            height = intInput.nextInt();
            System.out.print("Mines: ");
            mines = intInput.nextInt();
            //validate input
            if (width == 0 || height == 0)
            {
                continue;
            }
            if (mines > width * height - 1)
            {
                continue;
            }
            break;
        }
        return new CustomGrid(width, height, mines);
    }
}