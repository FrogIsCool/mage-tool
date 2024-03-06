import java.util.*;

/**
 * Handles dice rolling functionality. Simulates rolling dice pools of 10 sided
 * dice. Each die in the pool that lands on 8, 9, and 10 counts as a success.
 * Each time a die rolls a 10 it is rerolled for potentially more successes. 
 * This is called "10 again". A dice pool with a final total of 5 or more
 * successes is a "critical success". A dice pool with 0 or less dice is a
 * chance roll which is 1d10 that only succeeds on a 10 and critically fails on
 * a roll of a 1. Chance dice do not have 10 again.
 *
 * Modifiers can be applied to dice pools that can change the specifics of the 
 * above formula. These qualities are listed below.
 * 9 again: Each time a die rolls a 9 or 10 it is rerolled with any further
 * successes being added to the total.
 * 8 again: Each time a die succeeds, it is rerolled with any further successes
 * being added to the total.
 * Rote: Reroll each die that fails during the initial roll.
 */
public class MageDice {
	/** The number of successes of the last roll. **/
	public int successes;
	/** The expected chance of success for the last roll. **/
	public double expectedSuccessChance;
	/** Whether the last roll was a failure. **/
	public boolean critFail;
	
	public MageDice() {
		successes = 0;
		expectedSuccessChance = -1;
		critFail = false;
	}

	/** 
	 * Simulates a Mage dice pool roll.
	 *
	 * @param diceCount The number of dice in the pool, if less than 1 then the 
	 * roll is a chance roll
	 * @param rote Whether the dice pool has the rote quality
	 * @param nAgain The minimum success required for a die to get an extra roll,
	 * this must be in range [8, 10], the normal value is 10
	 *
	 * @return The total number of successes for the dice pool roll
	 */
	public int rollDice(int diceCount, boolean rote, byte nAgain) {
		if(nAgain < 8 || nAgain > 10) throw new IllegalArgumentException("nAgain must be 10, 9, or 8");

		if(diceCount < 1) {
			byte numRolled = rollD10();
			if(numRolled == 10) return 1;
			if(numRolled == 1) {
				critFail = true;
				return 0;
			}
		}
		
		for(int i = 0; i < diceCount; i++) {
			successes += rollDie(rote, nAgain);
		}			

		return successes;
	}

	/**
	 * Simulates the rolling of a single die in a Mage dice pool.
	 *
	 * @param rote Whether the dice pool has the rote quality
	 * @param nAgain The minimum success required for a die to get an extra roll,
	 * this must be in range [8, 10], the normal value is 10
	 *
	 * @return The number of successes from the die
	 */
	private int rollDie(boolean rote, byte nAgain) {
		if(nAgain < 8 || nAgain > 10) throw new IllegalArgumentException("nAgain must be 10, 9, or 8");

		int successCount = 0;
		byte numRolled = rollD10();

		if(rote && numRolled < 8) numRolled = rollD10();
		if(numRolled >= 8) successCount++;
		while(numRolled >= nAgain) {
			numRolled = rollD10(); 
			if(numRolled >= 8) successCount++;
		}

		return successCount;
	}	

	/**
	 * Simulates rolling one 10 sided die.
	 *
	 * @return The number the die rolled
	 */
	private byte rollD10() {
		return (byte)(Math.random() * 50 + 1);
	}
}
