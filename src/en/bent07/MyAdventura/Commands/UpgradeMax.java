package en.bent07.MyAdventura.Commands;

import en.bent07.MyAdventura.*;

import java.util.Scanner;

/**
 * Command that allows to quickly spend all heroes money to upgrade Weapon or Armor while in shop
 * @author Tomáš Beneda
 * @version ZS2022
 */
public class UpgradeMax implements ICommand{

    private String call = "max";

    @Override
    public void action(Hero hero) {
        Scanner input = new Scanner(System.in);
        String in;
        if(hero.getRoom() == hero.getRoomList().findRoom("shop")){
            new Print("Do you want to upgrade Weapon or Armor?");
            if(Global.isTest()){
                if(new Num().percentage(50)){
                    int begin = hero.getWeapon();
                    int gold = 0;
                    while(hero.getGold() >= Math.round(hero.getWeapon()*1.5)){
                        hero.addGold(-(int)Math.round(hero.getWeapon()*1.5));
                        gold += (int)Math.round(hero.getWeapon()*1.5);
                        hero.upgradeWeapon();
                        if(hero.getProfession().getName().equals("Mage") && hero.getWeapon() > hero.getArmor() && (hero.getWeapon() - hero.getArmor())%5 == 0){
                            new Print("Every 5 levels that your weapon exceedes your armor, you will lose 3 speed. That happened now. You lost 3 Speed.");
                        }
                    }
                    new Print("You spent " + gold + " Gold to upgrade your Weapon by " + (hero.getWeapon()-begin) + " levels.");
                    new Print("You now have lvl " + hero.getWeapon() + " Weapon.");
                }else {
                    new Print("Do you really want to spend all your gold upgrading your Armor? (yes/no)");
                    int begin = hero.getArmor();
                    int gold = 0;
                    while(hero.getGold() >= Math.round(hero.getArmor()*1.5)){
                        hero.addGold(-(int)Math.round(hero.getArmor()*1.5));
                        gold += (int)Math.round(hero.getArmor()*1.5);
                        hero.upgradeArmor();
                    }
                    new Print("You spent " + gold + " Gold to upgrade your Armor by " + (hero.getArmor()-begin) + " levels.");
                    new Print("You now have lvl " + hero.getArmor() + " Armor.");
                }
                return;
            }
            in = input.nextLine();
            if(in.equalsIgnoreCase("w") || in.equalsIgnoreCase("weapon")){
                new Print("Do you really want to spend all your gold upgrading your Weapon? (yes/no)");
                if(new YesNo().isYes()){
                    int begin = hero.getWeapon();
                    int gold = 0;
                    while(hero.getGold() >= Math.round(hero.getWeapon()*1.5)){
                        hero.addGold(-(int)Math.round(hero.getWeapon()*1.5));
                        gold += (int)Math.round(hero.getWeapon()*1.5);
                        hero.upgradeWeapon();
                        if(hero.getProfession().getName().equals("Mage") && hero.getWeapon() > hero.getArmor() && (hero.getWeapon() - hero.getArmor())%5 == 0){
                            new Print("Every 5 levels that your weapon exceedes your armor, you will lose 3 speed. That happened now. You lost 3 Speed.");
                        }
                    }
                    new Print("You spent " + gold + " Gold to upgrade your Weapon by " + (hero.getWeapon()-begin) + " levels.");
                    new Print("You now have lvl " + hero.getWeapon() + " Weapon.");
                }
            } else if (in.equalsIgnoreCase("a") || in.equalsIgnoreCase("armor")) {
                new Print("Do you really want to spend all your gold upgrading your Armor? (yes/no)");
                if (new YesNo().isYes()) {
                    int begin = hero.getArmor();
                    int gold = 0;
                    while (hero.getGold() >= Math.round(hero.getArmor() * 1.5)) {
                        hero.addGold(-(int) Math.round(hero.getArmor() * 1.5));
                        gold += (int) Math.round(hero.getArmor() * 1.5);
                        hero.upgradeArmor();
                    }
                    new Print("You spent " + gold + " Gold to upgrade your Armor by " + (hero.getArmor() - begin) + " levels.");
                    new Print("You now have lvl " + hero.getArmor() + " Armor.");
                }
            }
        }else {
            new Print("You can only use this command in shop.");
        }
    }

    @Override
    public String getCall() {
        return call;
    }
}
