//import standerd libraries
import java.util.Scanner;

public class ConsoleSweeper 
{
    public static void main(String[] args) 
    {
        //define state machine
        enum States
        {
            Menu,
            Options,
            Play,
        }
        //State variable
        States gameState = States.Menu;
        boolean clearScreen = true;
        //create grid
        Grid playGrid = new Grid();
        //create instance of font class
        Font font = new Font();
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
                gameState = States.Play;
            }
            else if (gameState == States.Play)
            {
                //dislay stuff and get action
                playGrid.displayGrid();
                System.out.println("f=flag o=open u=unflag");
                System.out.println("x;y (f1;1)");
                System.out.print("Your action: ");
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
                    //perform action. try catch in case inputted value is not valid
                    //replace this later
                    try
                    {
                        //split to get proper command
                        String letter = action.substring(0, 1);
                        String[] coords = action.substring(1).split(";");
                        int x = Integer.parseInt(coords[0]) - 1;
                        int y = Integer.parseInt(coords[1]) - 1;
                        playGrid.modifyCell(letter, x, y);
                    }
                    catch (Exception e)
                    {
                        continue;
                    }
                }
            }
        }
        input.close();
    }
}