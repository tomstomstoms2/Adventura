package en.bent07.MyAdventura;

import java.util.Scanner;

/**
 * Simple class looking for yes/y/z input
 * @author Tomáš Beneda
 * @version ZS2022
 */
public class YesNo {
    private boolean yes;

    public YesNo(){
        Scanner input = new Scanner(System.in);
        String in;
        in = input.nextLine();
        if((in.equalsIgnoreCase("yes") || in.equalsIgnoreCase("y") || in.equalsIgnoreCase("z"))){
            this.yes = true;
        }else{
            this.yes = false;
        }
    }

    public boolean isYes(){
        return yes;
    }

}
