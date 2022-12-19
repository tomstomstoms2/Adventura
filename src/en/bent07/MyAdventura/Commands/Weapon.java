package en.bent07.MyAdventura.Commands;

import en.bent07.MyAdventura.Global;
import en.bent07.MyAdventura.Hero;
import en.bent07.MyAdventura.Print;
import en.bent07.MyAdventura.YesNo;

/**
 * Implements the command Weapon.
 * Usable in shop, asks player for confirmation, then exchanges gold for weapon upgrade.
 * @author Tomáš Beneda
 * @version ZS2022
 */
public class Weapon implements ICommand{

    private String call = "weapon";
    @Override
    public void action(Hero hero) {

        if(hero.getRoom() == hero.getRoomList().findRoom("shop")){
            new Print("Next weapon upgrade costs " + Math.round(hero.getWeapon()*1.5) + " Gold.");
            new Print("Do you want to buy it? (yes/no)");
            //TEST MODE
            if(Global.isTest()){
                if(hero.getGold() >= (int)Math.round(hero.getWeapon()*1.5)){
                    hero.addGold(-(int)(Math.round(hero.getWeapon()*1.5)));
                    hero.upgradeWeapon();
                    new Print("Your weapon was upgraded to level " + hero.getWeapon() + ".");
                    if(hero.getProfession().getName().equals("Mage") && hero.getWeapon() > hero.getArmor() && (hero.getWeapon() - hero.getArmor())%5 == 0){
                        new Print("Every 5 levels that your weapon exceedes your armor, you will lose 3 speed. That happened now. You lost 3 Speed.");
                    }
                }else {
                    new Print("You don't have enough gold for this upgrade.");
                }
            }else if(new YesNo().isYes()){
                if(hero.getGold() >= (int)Math.round(hero.getWeapon()*1.5)){
                    hero.addGold(-(int)(Math.round(hero.getWeapon()*1.5)));
                    hero.upgradeWeapon();
                    new Print("Your weapon was upgraded to level " + hero.getWeapon() + ".");
                    if(hero.getProfession().getName().equals("Mage") && hero.getWeapon() > hero.getArmor() && (hero.getWeapon() - hero.getArmor())%5 == 0){
                        new Print("Every 5 levels that your weapon exceedes your armor, you will lose 3 speed. That happened now. You lost 3 Speed.");
                    }
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
