import java.util.*;

/**
 * Main class for Mage Tool. Handles main loop, user input, and printing output.
 */
public class MageTool {
    /** Scanner for taking in input from console. **/
    private static Scanner sysInScnr = new Scanner(System.in);
    /** Dice object for dice rolling functionality. **/
    private static MageDice dice = new MageDice();

    /**
     * Prints opening message.
     */
    private static void printStartMessage() {
        System.out.println("MageTool 1.0.0");
        System.out.println("An app for Mage: The Awakening 2e");
        System.out.println("Type help or h for available commands\n");
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
        System.out.println("MageTool uses the following commands:");
        System.out.println("\nroll [number of dice] [options]");
        System.out.println("r: Applies rote quality to roll\n9: Applies 9-Again quality to roll\n8: Applies 8-Again quality to roll");
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
     *
     * @return true if the input command was not quit 
     */
    private static boolean doLoopIteration(String rawInput) {
        String[] input = rawInput.toLowerCase().split(" ");
        switch(input[0]) {
            case "help":
            case "h":
                printHelpMenu();
                break;
            case "roll":
            case "r":
                doDiceRoll(input);
                break;
            case "quit":
            case "q":
                return false;
            default:
                System.out.println("Unknown command");
        }
        System.out.println();
        return true;
    }

    /**
     * Handles dice rolls and prints the results.
     *
     * @param input The full input from the user split by " "
     *
     * @return true if the dice roll was successful
     */
    private static boolean doDiceRoll(String[] input) {
        int diceCount;
        boolean rote = false;
        byte nAgain = 10;

        try {
            diceCount = Integer.parseInt(input[1]);
        } catch (NumberFormatException e) {
            System.out.printf("\"%s\" is not a number.\nroll's first argument should the number of dice in the pool.\n", input[1]);
            return false;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("roll requires a first argument which is the number of dice in the pool.");
            return false;
        }

        String rollOptions = input.length == 3 ? input[2] : "";
        for(char option : rollOptions.toCharArray()) {
            switch(option) {
                case 'r':
                    rote = true;
                    break;
                case '9':
                    nAgain = 9;
                    break;
                case '8':
                    nAgain = 8;
                    break;
                default:                    
                    System.out.printf("\"%s\" is not a valid option for dice rolls. Try the help command for more info.\n", option);
                    return false;
            }
        }

        dice.rollDice(diceCount, rote, nAgain);
        printRollResult();
        return true;        
    }

    /**
     * Prints the results of the last dice roll made by the tool.
     */
    private static void printRollResult() {
        if(dice.diceCount > 0) {
            System.out.printf("Rolled %d dice and got %d successes.\n", dice.diceCount, dice.successes);
            if(dice.critSuccess) System.out.println("An exceptional success!");
        } else {
            System.out.printf("Rolled a chance die and got %d successes.\n", dice.successes);
            if(dice.critFail) System.out.println("A dramatic failure!");
        }
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
