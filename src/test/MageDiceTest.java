import org.junit.Test;
import static org.junit.Assert.assertTrue;

/**
 * JUnit class for MageDice. As this is testing a random class, these tests are
 * all flaky. The tests check number of results within a statistical range so,
 * if the code is correct, they should only fail in very rare outlier instances.
 */
public class MageDiceTest {

    @Test
    public void rollDiceSuccessCountTest() {
        MageDice dice = new MageDice();
        int successes = 0;

        for(int i = 0; i < 1000000; i++) {
            successes += dice.rollDice(1, false, (byte)10);
        }

        assertTrue(successes > 333333 - 333333*0.01 && successes < 333333 + 333333*0.01);
    }

    @Test
    public void rollDiceRoteSuccessCountTest() {
        MageDice dice = new MageDice();
        int successes = 0;

        for(int i = 0; i < 1000000; i++) {
            dice.rollDice(1, true, (byte)10);
            if(dice.successes > 0) successes++;
        }

        assertTrue(successes > 510000 - 510000*0.01 && successes < 510000 + 510000*0.01);
    }


    @Test
    public void rollDiceCritSuccessTest() {
        MageDice dice = new MageDice();
        int critSuccesses = 0;

        for(int i = 0; i < 1000000; i++) {
            dice.rollDice(5, false, (byte)10);
            if(dice.critSuccess) critSuccesses++;
        }

        assertTrue(critSuccesses > 20200 - 20200*0.02 && critSuccesses < 20200 + 20200*0.02);
   }


    @Test
    public void rollDiceCritFailTest() {
        MageDice dice = new MageDice();
        int successes = 0;
        int critFails = 0;

        for(int i = 0; i < 1000000; i++) {
            successes += dice.rollDice(0, false, (byte)10);
            if(dice.critFail) critFails++;
        }

        assertTrue(successes > 100000 - 100000*0.01 && successes < 100000 + 100000*0.01);
        assertTrue(critFails > 100000 - 100000*0.01 && critFails < 100000 + 100000*0.01);
    }
}
