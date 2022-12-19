package en.bent07.MyAdventura.Commands;

import en.bent07.MyAdventura.Hero;

/**
 * Implements the command Stats.
 * Usable everywhere.
 * Prints stats of the hero instance.
 * @author Tomáš Beneda
 * @version ZS2022
 */
public class Stats implements ICommand{

    private String call = "stats";
    @Override
    public void action(Hero hero) {
            hero.info();
    }

    @Override
    public String getCall() {
        return call;
    }

}
