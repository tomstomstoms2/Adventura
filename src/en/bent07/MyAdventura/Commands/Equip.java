package en.bent07.MyAdventura.Commands;

import en.bent07.MyAdventura.*;

import java.util.Scanner;

/**
 * Implements the command Equip.
 * Usable at home/MainRoom or in any of the farm rooms (FightRoom, EmptyRoom, ChestRoom).
 * Manages hero's equipment.
 * @author Tomáš Beneda
 * @version ZS2022
 */
public class Equip implements ICommand{
    private String call = "equip";

    /**
     * Shows hero's equipment.
     * Allows to change Equipped oddity. The hero can have only one at a time.
     * Usable everywhere
     * @param hero instance of hero
     */
    @Override
    public void action(Hero hero) {
        Scanner input = new Scanner(System.in);
        String in;
        if(hero.getBag().getRing().getLevel() == 0 && hero.getBag().getNecklace().getLevel() == 0 && hero.getBag().getOwned().size() <= 1){
            new Print("You don't have any equipment. Try finding some while farming.");
            return;
        }
        new Print("This is your equip:");
        if(hero.getBag().getRing().getLevel() != 0) {
            new Print("Ring lvl " + hero.getBag().getRing().getLevel());
            new Print("Ring stats: " + hero.getBag().getRing().getHealth() + " Health, " + hero.getBag().getRing().getDamage() + " Damage, " + hero.getBag().getRing().getSpeed() + " Speed");
        }
        if(hero.getBag().getNecklace().getLevel() != 0) {
            new Print("Necklace lvl " + hero.getBag().getNecklace().getLevel());
            new Print("Necklace stats: " + hero.getBag().getNecklace().getHealth() + " Health, " + hero.getBag().getNecklace().getDamage() + " Damage, " + hero.getBag().getNecklace().getSpeed() + " Speed");
        }
        if(hero.getBag().getOwned().size() <= 1){
            return;
        }
        if(hero.getBag().getOwned().size() == 11){
            new Print("OH! You have all the different possible oddities!");
            new Print("You can sacrifice them all, to get permanent +1 multiplier to all of their stats!");
            new Print("It will cost you " + 1000*hero.getOddityMult() + " Gold and you will lose all your Oddities.");
            new Print("Do you want to sacrifice them? (yes/no)");
            //TEST MODE
            if(Global.isTest()){
                if(hero.getGold() >= 1000*hero.getOddityMult()){
                    hero.addGold(-(1000*hero.getOddityMult()));
                    hero.upgradeOddityMult();
                    hero.getBag().getOwned().clear();
                    hero.getBag().addOwned(0);
                    hero.getBag().setOddity(0);
                    new Print("You sacrificed all your Oddities.\nYour oddity stats will now be " + hero.getOddityMult() + "x stronger.");
                    return;
                }else{
                    new Print("You don't have enough Gold to do this.");
                }
            }else if (new YesNo().isYes()) {
                if(hero.getGold() >= 1000*hero.getOddityMult()){
                    hero.addGold(-(1000*hero.getOddityMult()));
                    hero.upgradeOddityMult();
                    hero.getBag().getOwned().clear();
                    hero.getBag().addOwned(0);
                    hero.getBag().setOddity(0);
                    new Print("You sacrificed all your Oddities.\nYour oddity stats will now be " + hero.getOddityMult() + "x stronger.");
                    return;
                }else{
                    new Print("You don't have enough Gold to do this.");
                }
            }else {
                new Print("You chose not to and you returned back to playing.");
            }
        }
        if(hero.getBag().getOddity().getKey() == 0){
            new Print("You don't have any equipped Oddity.");
        }else {
            new Print("Oddity: " + hero.getBag().getOddity().getName());
            new Print("\"" + hero.getBag().getOddity().getLore() + "\"");
            new Print("Oddity stats: " + hero.getBag().getOddity().getHealth()*hero.getOddityMult() + " Health, " + hero.getBag().getOddity().getDamage()*hero.getOddityMult() + " Damage, " + hero.getBag().getOddity().getSpeed()*hero.getOddityMult() + " Speed");
        }
        new Print("Do you want to equip an Oddity? (yes/no)");
        //TEST MODE
        if(Global.isTest()){
            new Print("These are your Oddities:");
            //new Print("0: No Oddity");
            hero.getBag().ownedToString(hero);
            new Print("What Oddity do you want to equip? (number)");
            int set = new Num().rand(0,10);
            while(!hero.getBag().getOwned().contains(set)){
                set = new Num().rand(0,10);
            }
            if (hero.getBag().getOwned().contains(set)) {
                hero.getBag().setOddity(set);
                new Print("You equipped " + hero.getBag().getOddity().getName());
            } else {
                new Print("Please choose only your owned Oddities' numbers listed above.");
                new Print("You returned back to playing.");
            }
        }else if (new YesNo().isYes()) {
            new Print("These are your Oddities:");
            //new Print("0: No Oddity");
            hero.getBag().ownedToString(hero);
            new Print("What Oddity do you want to equip? (number)");
            in = input.nextLine();
            int set;
            try {
                set = Integer.parseInt(in);
            }catch (Exception e){
                new Print("You need to input number.");
                new Print("You returned back to playing.");
                return;
            }

            if (hero.getBag().getOwned().contains(set)) {
                hero.getBag().setOddity(set);
                new Print("You equipped " + hero.getBag().getOddity().getName());
            } else {
                new Print("Please choose only your owned Oddities' numbers listed above.");
                new Print("You returned back to playing.");
            }
        }
    }

    @Override
    public String getCall() {
        return call;
    }
}
