package en.bent07.MyAdventura;

/**
 * Class Num executes computations usually including random numbers, chances and so on.
 * @author Tomáš Beneda
 * @version ZS2022
 */
public class Num {

    /**
     * Implements system that determines if something happens with probability of 1/chance
     * @param chance - integer that defines the chance of something happening in 1/chance probability
     * @return true for the thing happening, false for not happening
     */
    public boolean chance(int chance){
        if(chance <= 1){
            return true;
        }
        int multiple = 10000000;
        if(chance >= multiple /* || chance < 2*/){
            new Print("Invalid chance value. Chance can range from 2 to 9999999");
            return false;
        }
        return ((int)((Math.random()*multiple)%chance)) == 0;
    }

    /**
     * Returns random int from an interval
     * @param min lowest possible number
     * @param max highest possible number
     * @return random int from the interval
     */
    public int rand(int min, int max){
        return min + (int)(Math.random() * ((max - min) + 1));
    }


    /**
     * Computes random percentage of thing happening
     * @param percentage double that specifies the percent chance of something happening. EDIT: Changed to double for more precision
     * @return true if it happens, false if not
     */
    public boolean percentage(double percentage){
        if(percentage > 100){
            return true;
        }
        if(percentage < 0){
            return false;
        }
        return percentage > (Math.random()*100);
    }

}
