package en.bent07.MyAdventura.Commands;

import en.bent07.MyAdventura.Global;
import en.bent07.MyAdventura.Hero;
import en.bent07.MyAdventura.Print;
import en.bent07.MyAdventura.YesNo;



/**
 * Class that implements command that safely exits the program.
 * @author Tomáš Beneda
 * @version ZS2022
 */
public class End implements ICommand{
    private String call = "end";

    @Override
    public void action(Hero hero) {
        if(hero.isEndgame()){
            new Print("Do you want to end the game? Your hero will be saved. (yes/no)");
            //TEST MODE
            if(Global.isTest()){
                new Print("Come play again!");
                hero.setEnd(true);
                return;
            }
            if(new YesNo().isYes()){
                new Print("Come play again!");
                hero.setEnd(true);
            }
        }else{
            new Print("Do you want to end the game? You will lose your progress. (yes/no)");
            //TEST MODE
            if(Global.isTest()){
                new Print("Come play again!");
                System.exit(0);
            }
            if(new YesNo().isYes()){
                new Print("Come play again!");
                System.exit(0);
            }
        }

    }


    @Override
    public String getCall() {
        return call;
    }
}
