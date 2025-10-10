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
        }
        //State variable
        States gameState = States.Menu;
        //create instance of font class
        Font font = new Font();
        //define input scanner
        Scanner input = new Scanner(System.in);
        //Main loop
        while (true)
        {
            //clear screen
            System.out.print("\033[2J");
            CursorControl.reset();
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
                Grid playGrid = new Grid(8);
                playGrid.displayGrid();
                String action = input.nextLine();
            }
        }
        input.close();
    }
}