package en.bent07.MyAdventura;


/**
 * Class storing global variables
 * May be bad idea and/or bad execution
 * EDIT: Maybe atleast get getters and setters
 * @author Tomáš Beneda
 * @version ZS2022
 */
public class Global {

    /**
     * Sets delay between output messages [miliseconds]. Always positive. args[0]
     */
    private static int delay = 200;

    /**
     * Determines if the game is running in Test mode = if the inputs are gotten from a file. args[2]
     */
    private static boolean test = false;

    /**
     * The level of boss required to win the game. Always positive multiple of 5. args[1]
     */
    private static int bossWinLevel = 25;


    public static int getDelay() {
        return delay;
    }

    public static void setDelay(int delay) {
        Global.delay = delay;
    }

    public static boolean isTest() {
        return test;
    }

    public static void setTest(boolean test) {
        Global.test = test;
    }

    public static int getBossWinLevel() {
        return bossWinLevel;
    }

    public static void setBossWinLevel(int bossWinLevel) {
        Global.bossWinLevel = bossWinLevel;
    }
}
