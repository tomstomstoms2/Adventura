package en.bent07.MyAdventura.Commands;

import en.bent07.MyAdventura.Global;
import en.bent07.MyAdventura.Hero;
import en.bent07.MyAdventura.Print;
import en.bent07.MyAdventura.YesNo;


/**
 * Implements the command Armor.
 * Usable in shop, asks player for confirmation, then exchanges gold for armor upgrade.
 * @author Tomáš Beneda
 * @version ZS2022
 */
public class Armor implements ICommand{

    private String call = "armor";
    @Override
    public void action(Hero hero) {

        if(hero.getRoom() == hero.getRoomList().findRoom("shop")){
            new Print("Next armor upgrade costs " + Math.round(hero.getArmor()*1.5) + " Gold.");
            new Print("Do you want to buy it? (yes/no)");
            //TEST MODE
            if(Global.isTest()){
                if(hero.getGold() >= (int)Math.round(hero.getArmor()*1.5)){
                    hero.addGold(-(int)(Math.round(hero.getArmor()*1.5)));
                    hero.upgradeArmor();
                    new Print("Your armor was upgraded to level " + hero.getArmor() + ".");
                }else {
                    new Print("You don't have enough gold for this upgrade.");
                }
            }else if(new YesNo().isYes()){
                if(hero.getGold() >= (int)Math.round(hero.getArmor()*1.5)){
                    hero.addGold(-(int)(Math.round(hero.getArmor()*1.5)));
                    hero.upgradeArmor();
                    new Print("Your armor was upgraded to level " + hero.getArmor() + ".");
                }else {
                    new Print("You don't have enough gold for this upgrade.");
                }
            }
        }else {
            new Print("You can use this command only in Shop.");
        }
    }

    @Override
    public String getCall() {
        return call;
    }
}
