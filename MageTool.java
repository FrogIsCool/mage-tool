import java.util.*;

/**
 * Main class for Mage Tool. Handles main loop, user input, and printing output.
 */
public class MageTool {
    /** Scanner for taking in input from console. **/
    private Scanner sysIn;
    /** Dice object for dice rolling functionality. **/
    private MageDice dice;
    /** Store the last input taken from the user. **/
    private String input;

    /**
     * Initalizes Scanners and dice.
     */
    public MageTool() {
        this.sysIn = new Scanner(System.in);
        this.dice = new MageDice();
    }

    /**
     * Prints to console opening message.
     */
    private static void printOnStart() {
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
     * @return The recieved input from the user
     */
    private static String getUserInput() {
        return "";
    }

    /**
     * Handles user interaction for each iteration of the main loop.
     *
     * @param input The input from the user for the current iteration
     * @return True if the input command was not a quit command
     */
    private static boolean doLoopIteration(String input) {
        switch(input.toLowerCase()) {
            case "q":
            case "quit":
                return false;
            default:
                System.out.println("Unkown command");
        }
        return true;
    }

    /**
     * Not currently used.
     * Print currently loaded character's stats.
     */
    private static void printCharacterStats() {
    }

    /**
     * The main program loop.
     */
    public static void main(String[] args) {
        printOnStart();
        doMainLoop();
    }
}
