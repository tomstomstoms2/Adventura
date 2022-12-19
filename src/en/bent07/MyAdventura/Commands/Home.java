package en.bent07.MyAdventura.Commands;

import en.bent07.MyAdventura.Hero;
import en.bent07.MyAdventura.Print;

/**
 * Implements the command Home.
 * Usable everywhere, returns player back to home/MainRoom.
 * @author Tomáš Beneda
 * @version ZS2022
 */
public class Home implements ICommand{

    private String call = "home";
    @Override
    public void action(Hero hero) {
        hero.setRoom("home");
        new Print("You have returned to the Main Room.");
    }

    @Override
    public String getCall() {
        return call;
    }

}
