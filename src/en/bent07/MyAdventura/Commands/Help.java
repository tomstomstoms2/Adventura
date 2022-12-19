package en.bent07.MyAdventura.Commands;

import en.bent07.MyAdventura.Global;
import en.bent07.MyAdventura.Hero;
import en.bent07.MyAdventura.Print;

/**
 * Implements the command Help.
 * Usable everywhere.
 * Prints all available commands with short explanation.
 * @author Tomáš Beneda
 * @version ZS2022
 */
public class Help implements ICommand{

    private String call = "help";
    @Override
    public void action(Hero hero) {
        new Print("info:  Information about the room. (info/i)");
        new Print("home:  Travel back to the Main Room. (home/h/main/m)");
        new Print("stats: Show stats about your character. (stats)");
        new Print("farm:  Go to the Farm rooms while in Main Room. (farm/f)");
        new Print("boss:  Go fight the main boss while in Main Room. If you beat lvl " + Global.getBossWinLevel() + " boss, you will win the game. (boss/b)");
        new Print("armor: Upgrade your armor while in Shop. (armor/a)");
        new Print("weapon: Upgrade your weapon while in Shop. (weapon/w)");
        new Print("shop:  Go to the Shop while in the Main Room. (shop/s)");
        new Print("help:  Show this list. (help)");
        new Print("equip: Manage your equipment. (equip/eq)");
        new Print("end:   Ends the game. (end)");
        if(hero.isEndgame()) {
            new Print("PVP: Allows to battle other saved heroes. (pvp)");
        }
        new Print("max:   Allows you to quickly upgrade Weapon or Armor with all your gold while in shop. (max)");
    }

    @Override
    public String getCall() {
        return call;
    }

}
