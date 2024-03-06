import java.util.*;

/**
 * Main class for Mage Tool. Handles main loop, user input, and printing output.
 */
public class MageTool {
    /** Scanner for taking in input from console. **/
    private static Scanner sysInScnr = new Scanner(System.in);
    /** Dice object for dice rolling functionality. **/
    private static MageDice dice = new MageDice();
    /** Store the last input taken from the user. **/
    //private static String input;

    /**
     * Prints to console opening message.
     */
    private static void printStartMessage() {
    }

	/**
	 * Prints exit message.
	 */
	private static void printExitMessage() {
		System.out.println("Exiting");
	}

    /**
     * Prints a list of commands and what they do.
     */
    private static void printHelpMenu() {
    }

    /**
     * Runs the main loop. Ask for input, handle input, print results, repeat.
     */
    private static void doMainLoop() {
        while(doLoopIteration(getUserInput()));
    }

    /**
     * Gets input from user for each iteration of the main loop.
     *
     * @return The received input from the user
     */
    private static String getUserInput() {
		return sysInScnr.nextLine();
    }

    /**
     * Handles user interaction for each iteration of the main loop.
     *
     * @param rawInput The input from the user for the current iteration
     * @return True if the input command was not quit 
     */
    private static boolean doLoopIteration(String rawInput) {
		String[] input = rawInput.toLowerCase().split(" ");
        switch(input[0]) {
			case "r":
				// dice roll method
				break;
			case "quit":
            case "q":
                return false;
            default:
                System.out.println("Unknown command");
        }
        return true;
    }

	/**
	 * Handles dice rolls and prints the results.
	 *
	 * @param input The full dice roll command received from the user
	 */
	private static void doDiceRoll() {
	}

    /**
     * The main program loop.
     */
    public static void main(String[] args) {
        printStartMessage();
        doMainLoop();
		printExitMessage();
    }
}
