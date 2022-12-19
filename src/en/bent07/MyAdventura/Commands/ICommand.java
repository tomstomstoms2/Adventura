package en.bent07.MyAdventura.Commands;

import en.bent07.MyAdventura.Hero;

/**
 * Interface setting main methods for the game Command.
 * @author Tomáš Beneda
 * @version ZS2022
 */
public interface ICommand {

    public void action(Hero hero);
    public String getCall();

}
