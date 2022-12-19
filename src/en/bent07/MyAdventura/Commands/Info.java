package en.bent07.MyAdventura.Commands;

import en.bent07.MyAdventura.Hero;

/**
 * Implements the command Info.
 * Usable everywhere.
 * Prints info about the Room the hero is in.
 * @author Tomáš Beneda
 * @version ZS2022
 */
public class Info implements ICommand{
    private String call = "info";

    public String getCall() {
        return call;
    }



    @Override
    public void action(Hero hero) {
        hero.getRoom().getInfo();
    }
}
